package com.pthomasdesigns.libcontactandroid;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import java.util.List;

import android.os.Handler;
import android.util.Log;

public class ContactSdk {

    private Handler bgHandler;
    private static HandlerThread bgThread;
    private static Handler uiHandler;
    private static UpdateContactListener ucListener;
    private static ContactSdk instance = null;

    private static int SUCCESS = 1;
    private static int FAILURE = 0;
    private static String TAG = "ContactSdk";

    private ContactSdk() {

        System.loadLibrary("android-contact-sdk");
        bgThread = new HandlerThread("ContackSdk-Background-Thread");
        bgThread.start();
        bgHandler = new Handler(bgThread.getLooper());
        uiHandler = new Handler(Looper.getMainLooper());
    }

    public static ContactSdk initialize() {
        if (instance == null) {
            synchronized (ContactSdk.class) {
                if (instance == null) {
                    instance = new ContactSdk();
                }
            }
        }
        nativeInit(instance);
        return instance;
    }

    public void shutdown() {
        nativeShutdown();
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
                final List<Contact> contacts = getAllContactsInternal();
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
                if (addContactInternal(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber()) == SUCCESS) {
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
        setUpdateContactListenerInternal(this);
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

    // native methods
    private static native void nativeInit(ContactSdk sdk);
    private static native void nativeShutdown();
    private static native List<Contact> getAllContactsInternal();
    private static native int addContactInternal(String firstName, String lastName, String phoneNumber);
    private static native void setUpdateContactListenerInternal(ContactSdk sdk);
}
