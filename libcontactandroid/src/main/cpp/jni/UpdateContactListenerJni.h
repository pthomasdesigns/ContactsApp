//
// Created by TTHOMAS on 5/9/2018.
//

#ifndef CONTACTSAPP_UPDATECONTACTLISTENERJNI_H
#define CONTACTSAPP_UPDATECONTACTLISTENERJNI_H


#include <jni.h>
#include "../sdk/Contact.h"

class UpdateContactListenerJni {
public:
    static void initialize(JNIEnv* env, jobject sdk);
    static void shutdown(JNIEnv* env);
    static void java2cpp(JNIEnv *env, jobject listener);
    static void cpp2java(JNIEnv *env);

    typedef void (*UpdateContactCallback) (JNIEnv *env, Contact newContact, Contact oldContact);

private:
    static JavaVM *jvm;
    static void callback(JNIEnv *env, Contact oldContact, Contact newContact);
    static jclass contactSdkClass;
    static jmethodID contactSdk_onContactUpdated;
};


#endif //CONTACTSAPP_UPDATECONTACTLISTENERJNI_H
