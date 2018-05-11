package com.pthomasdesigns.contactsapp;

import android.app.Application;

import com.pthomasdesigns.libcontactandroid.ContactSdk;
import com.pthomasdesigns.libcontactandroid.NativeInterfaceImpl;

public class ContactsApp extends Application {
    private ContactSdk contactSdk;

    @Override
    public  void onCreate () {
        super.onCreate();
        contactSdk = ContactSdk.initialize(new NativeInterfaceImpl());
    }

    public ContactSdk getContactSdk() {
        return contactSdk;
    }
}
