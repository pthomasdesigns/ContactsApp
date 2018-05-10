//
// Created by TTHOMAS on 5/9/2018.
//

#ifndef CONTACTSAPP_MOCKCONTACTUPDATE_H
#define CONTACTSAPP_MOCKCONTACTUPDATE_H

#include <thread>
#include <vector>
#include "ContactSdk.h"
#include "../jni/UpdateContactListenerJni.h"

class MockContactUpdate {
public:
    static const char* kTAG;
    void static start(JavaVM *jvm, UpdateContactListenerJni::UpdateContactCallback callback);
    void static stop();

private:
    void static threadFunc(JavaVM *jvm);

    static std::vector<std::string> phoneNumbers;
    static std::thread thread;
    static UpdateContactListenerJni::UpdateContactCallback callback;
    static bool isRunning;
};

#endif //CONTACTSAPP_MOCKCONTACTUPDATE_H
