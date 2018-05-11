package com.pthomasdesigns.libcontactandroid;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.util.List;

import android.os.Handler;
import android.util.Log;

public class ContactSdk {

    private static Handler bgHandler;
    private static HandlerThread bgThread;
    private static Handler uiHandler;
    private static UpdateContactListener ucListener;
    private static ContactSdk instance = null;
    private static NativeInterface nativeInterface;

    private static int SUCCESS = 1;
    private static int FAILURE = 0;
    private static final String TAG = "ContactSdk";
    private static final String LIBRARY_NAME = "android-contact-sdk";

    private ContactSdk() {
    }

    public static ContactSdk initialize(NativeInterface nativeInterface) {
        if (instance == null) {
            synchronized (ContactSdk.class) {
                if (instance == null) {
                    instance = new ContactSdk();
                    ContactSdk.nativeInterface = nativeInterface;
                    ContactSdk.nativeInterface.loadLibrary(LIBRARY_NAME);
                    createHandlers();
                    ContactSdk.nativeInterface.nativeInit(instance);
                }
            }
        }
        return instance;
    }

    private static void createHandlers() {
        bgThread = new HandlerThread("ContackSdk-Background-Thread");
        bgThread.start();
        bgHandler = new Handler(bgThread.getLooper());
        uiHandler = new Handler(Looper.getMainLooper());
    }

    public void shutdown() {
        bgThread.quit();
        nativeInterface.nativeShutdown();
    }

    public interface AddContactListener {
        void onContactAdded(Contact contact);
    }

    public interface UpdateContactListener {
        void onContactUpdated(Contact newContact, Contact oldContact);
    }

    public interface GetAllContactsListener {
        void onSuccess(List<Contact> allContacts);
    }

    public void getAllContacts(final GetAllContactsListener listener) {
        bgHandler.post(new Runnable() {
            @Override
            public void run() {
                final List<Contact> contacts = nativeInterface.getAllContacts();
                if (listener != null) {
                    uiHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onSuccess(contacts);
                        }
                    });
                }
            }
        });
    }

    public void addContact(final Contact contact, final AddContactListener listener) {
        bgHandler.post(new Runnable() {
            @Override
            public void run() {
                if (nativeInterface.addContact(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber()) == SUCCESS) {
                    if (listener != null) {
                        uiHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onContactAdded(contact);
                            }
                        });
                    }
                } else {
                    Log.e(TAG, "addContact Failed");
                }
            }
        });
    }

    public void setUpdateContactListener(UpdateContactListener listener) {
        ucListener = listener;
        nativeInterface.setUpdateContactListener(this);
    }

    public static void onContactUpdated(final Contact oldContact, final Contact newContact) {
        if (ucListener != null) {
            uiHandler.post(new Runnable() {
                @Override
                public void run() {
                    ucListener.onContactUpdated(newContact, oldContact);
                }
            });
        }
    }
}
