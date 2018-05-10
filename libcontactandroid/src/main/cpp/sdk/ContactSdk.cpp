//
// Created by TTHOMAS on 5/7/2018.
//

#include <jni.h>

#include "ContactSdk.h"
#include "../log.h"
#include "MockContactUpdate.h"
#include "MockContacts.h"

const char* ContactSdk::kTAG = "ContactSdk";

void ContactSdk::addContact(Contact contact) {
    LOGI(kTAG, "addContact");
    MockContacts::addContact(contact);
}

std::vector<Contact> ContactSdk::getAllContacts() {
    LOGI(kTAG, "getAllContacts");
    return MockContacts::getAllContacts();
}

