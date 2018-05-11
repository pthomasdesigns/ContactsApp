package com.pthomasdesigns.contactsapp;

import android.app.Application;

import com.pthomasdesigns.libcontactandroid.ContactSdk;

public class ContactsApp extends Application {
    private ContactSdk contactSdk;

    @Override
    public  void onCreate () {
        super.onCreate();
        contactSdk = ContactSdk.initialize();
    }

    public ContactSdk getContactSdk() {
        return contactSdk;
    }
}
