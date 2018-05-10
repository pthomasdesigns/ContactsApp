//
// Created by TTHOMAS on 5/7/2018.
//

#ifndef CONTACTSAPP_CONTACTSDK_H
#define CONTACTSAPP_CONTACTSDK_H

#include <list>
#include <vector>
#include "Contact.h"

class ContactSdk {
public:
    static const char* kTAG;

    static void addContact(Contact contact);
    static std::vector<Contact> getAllContacts();

};

#endif //CONTACTSAPP_CONTACTSDK_H
