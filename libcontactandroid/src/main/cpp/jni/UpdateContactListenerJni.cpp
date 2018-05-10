//
// Created by TTHOMAS on 5/9/2018.
//

#include "UpdateContactListenerJni.h"
#include "../sdk/MockContactUpdate.h"
#include "ContactJni.h"

JavaVM* UpdateContactListenerJni::jvm;
jclass UpdateContactListenerJni::contactSdkClass;
jmethodID UpdateContactListenerJni::contactSdk_onContactUpdated;

void UpdateContactListenerJni::initialize(JNIEnv* env, jobject sdk) {
    env->GetJavaVM(&jvm);
    contactSdkClass
        = static_cast<jclass>(env->NewGlobalRef(env->GetObjectClass(sdk)));

    contactSdk_onContactUpdated
        = env->GetStaticMethodID(contactSdkClass,
             "onContactUpdated",
             "(Lcom/pthomasdesigns/libcontactandroid/Contact;Lcom/pthomasdesigns/libcontactandroid/Contact;)V");
}

void UpdateContactListenerJni::shutdown(JNIEnv* env) {
    MockContactUpdate::stop();
    if(contactSdkClass != nullptr) {
        env->DeleteGlobalRef(contactSdkClass);
    }
}

void UpdateContactListenerJni::java2cpp(JNIEnv *env, jobject listener) {
    if (listener != nullptr) {
        MockContactUpdate::start(jvm, callback);
    } else {
        MockContactUpdate::stop();
    }
}

void UpdateContactListenerJni::callback(JNIEnv *env, Contact oldContact, Contact newContact) {
    jobject joldContact = ContactJni::cpp2java(env, oldContact);
    jobject jnewContact = ContactJni::cpp2java(env, newContact);

    if ((joldContact != nullptr) && (jnewContact != nullptr)) {
        env->CallStaticVoidMethod(contactSdkClass,
                                  contactSdk_onContactUpdated,
                                  joldContact, jnewContact);
        if (env->ExceptionCheck()) {
            env->ExceptionDescribe();
        }
    }

    if (joldContact != nullptr) {
        env->DeleteLocalRef(joldContact);
    }
    if (jnewContact != nullptr) {
        env->DeleteLocalRef(jnewContact);
    }
}