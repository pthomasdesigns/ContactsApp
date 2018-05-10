//
// Created by TTHOMAS on 5/10/2018.
//

#ifndef CONTACTSAPP_MOCKCONTACTS_H
#define CONTACTSAPP_MOCKCONTACTS_H


#include <vector>
#include "Contact.h"

class MockContacts {
public:
    static std::vector<Contact> getAllContacts() { return contacts; }
    static void addContact(Contact c) { contacts.push_back(c); }
    static Contact getContact(int i);
    static int size() { return contacts.size(); }

private:
    static std::vector<Contact> contacts;


};


#endif //CONTACTSAPP_MOCKCONTACTS_H
