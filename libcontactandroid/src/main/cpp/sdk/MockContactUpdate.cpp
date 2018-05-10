//
// Created by TTHOMAS on 5/9/2018.
//

#include <stdlib.h>
#include <time.h>
#include "MockContactUpdate.h"
#include "MockContacts.h"
#include "../log.h"

const char* MockContactUpdate::kTAG = "MockContactUpdate";
std::thread MockContactUpdate::thread;
UpdateContactListenerJni::UpdateContactCallback MockContactUpdate::callback = nullptr;
bool MockContactUpdate::isRunning = false;
std::vector<std::string> MockContactUpdate::phoneNumbers =
        {"+1234567890",
         "+2345678901",
         "+3456789012",
         "+4567890123",
         "+5678901234",
         "+6789012345",
         "+7890123456"};

void  MockContactUpdate::start(JavaVM *jvm, UpdateContactListenerJni::UpdateContactCallback callback) {
    if (!isRunning) {
        srand(time(NULL));
        MockContactUpdate::callback = callback;
        isRunning = true;
        thread = std::thread(threadFunc, jvm);
    }
}

void MockContactUpdate::stop() {
    if (isRunning) {
        isRunning = false;
        thread.join();
    }
}

void MockContactUpdate::threadFunc(JavaVM *jvm) {
    JNIEnv *env;
    int getEnvStat = jvm->GetEnv((void **)&env, JNI_VERSION_1_6);
    if (getEnvStat == JNI_EDETACHED) {
        if (jvm->AttachCurrentThread(&env, NULL) != 0) {
            LOGI(kTAG, "AttachCurrentThread failed");
        }
    }
    while (isRunning) {
        if (callback) {
            int contactIndex = rand() % MockContacts::size();
            Contact oldContact = MockContacts::getContact(contactIndex);
            Contact newContact = oldContact;

            int numbexIndex = rand() % phoneNumbers.size();
            newContact.setPhoneNumber(phoneNumbers[numbexIndex]);

            MockContacts::addContact(newContact);
            callback (env, oldContact, newContact);
        }
        std::this_thread::sleep_for(std::chrono::seconds(10));
    }

    jvm->DetachCurrentThread();
}