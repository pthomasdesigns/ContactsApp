package com.pthomasdesigns.libcontactandroid;

import java.util.List;

public interface NativeInterface {
    public void loadLibrary(String name);
    public void nativeInit(ContactSdk sdk);
    public void nativeShutdown();
    public List<Contact> getAllContacts();
    public int addContact(String firstName, String lastName, String phoneNumber);
    public void setUpdateContactListener(ContactSdk sdk);
}
