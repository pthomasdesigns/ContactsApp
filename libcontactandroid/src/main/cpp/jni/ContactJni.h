//
// Created by TTHOMAS on 5/8/2018.
//

#ifndef CONTACTSAPP_CONTACTJNI_H
#define CONTACTSAPP_CONTACTJNI_H


#include <jni.h>
#include <vector>
#include "../sdk/Contact.h"

class ContactJni {

public:
    static void initialize(JNIEnv* env, jobject sdk);
    static void shutdown(JNIEnv* env);

    static Contact java2cpp(JNIEnv* env, jstring firstName, jstring lastName, jstring phoneNumber);
    static jobject cpp2java(JNIEnv* env, const Contact contact);
    static jobject cpp2java(JNIEnv* env, std::vector<Contact> contacts);

private:
    static jclass java_util_ArrayList;
    static jmethodID java_util_ArrayList_;
    static jmethodID java_util_ArrayList_add;

    static jclass contactClass;
    static jmethodID contactClass_;

};


#endif //CONTACTSAPP_CONTACTJNI_H
