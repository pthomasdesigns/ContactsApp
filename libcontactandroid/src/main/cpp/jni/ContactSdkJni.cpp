//
// Created by TTHOMAS on 5/8/2018.
//

#include "ContactSdkJni.h"
#include "../log.h"
#include "../sdk/Contact.h"
#include "ContactJni.h"
#include "../sdk/ContactSdk.h"
#include "UpdateContactListenerJni.h"

static const char* kTAG = "ContactSdkJni";

JNIEXPORT void JNICALL Java_com_pthomasdesigns_libcontactandroid_ContactSdk_nativeInit (JNIEnv *env, jclass, jobject sdk) {

    ContactJni::initialize(env, sdk);
    UpdateContactListenerJni::initialize(env, sdk);
    LOGI(kTAG, "Native initialization complete");
}

JNIEXPORT void JNICALL Java_com_pthomasdesigns_libcontactandroid_ContactSdk_nativeShutdown
        (JNIEnv *env, jclass) {
    ContactJni::shutdown(env);
    UpdateContactListenerJni::shutdown(env);
    LOGI(kTAG, "Native shutdown complete");
}

JNIEXPORT jint JNICALL Java_com_pthomasdesigns_libcontactandroid_ContactSdk_addContactInternal
        (JNIEnv *env, jclass contactSdk, jstring firstName, jstring lastName, jstring phoneNumber) {

    Contact contact = ContactJni::java2cpp(env, firstName, lastName, phoneNumber);
    if (contact.isValid()) {
        ContactSdk::addContact(contact);
        return SUCCESS;
    } else {
        return FAILURE;
    }
}

JNIEXPORT jobject JNICALL Java_com_pthomasdesigns_libcontactandroid_ContactSdk_getAllContactsInternal
        (JNIEnv *env, jclass) {

    std::vector<Contact> clist = ContactSdk::getAllContacts();
    jobject jlist = ContactJni::cpp2java(env, clist);
    return jlist;
}

JNIEXPORT void JNICALL Java_com_pthomasdesigns_libcontactandroid_ContactSdk_setUpdateContactListenerInternal
        (JNIEnv *env, jclass, jobject listener) {

    UpdateContactListenerJni::java2cpp(env, listener);
}

