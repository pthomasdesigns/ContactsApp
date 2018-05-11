package com.pthomasdesigns.libcontactandroid;

import java.util.List;

public class NativeInterfaceImpl implements NativeInterface {
    public void loadLibrary(String name) {
        System.loadLibrary(name);
    }
    public void nativeInit(ContactSdk sdk) {
        this.nativeInitInternal(sdk);
    }
    public void nativeShutdown() {
        nativeShutdownInternal();
    }
    public List<Contact> getAllContacts(){
        return getAllContactsInternal();
    }
    public int addContact(String firstName, String lastName, String phoneNumber) {
        return addContactInternal(firstName, lastName, phoneNumber);
    }
    public void setUpdateContactListener(ContactSdk sdk) {
        setUpdateContactListenerInternal(sdk);
    }

    // native methods
    private static native void nativeInitInternal(ContactSdk sdk);
    private static native void nativeShutdownInternal();
    private static native List<Contact> getAllContactsInternal();
    private static native int addContactInternal(String firstName, String lastName, String phoneNumber);
    private static native void setUpdateContactListenerInternal(ContactSdk sdk);
}
