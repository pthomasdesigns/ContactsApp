//
// Created by TTHOMAS on 5/8/2018.
//

#include <list>
#include <vector>
#include "ContactJni.h"

jclass ContactJni::java_util_ArrayList;
jmethodID ContactJni::java_util_ArrayList_;
jmethodID ContactJni::java_util_ArrayList_add;

jclass ContactJni::contactClass;
jmethodID ContactJni::contactClass_;

void ContactJni::initialize(JNIEnv *env, jobject sdk) {
    java_util_ArrayList      = static_cast<jclass>(env->NewGlobalRef(env->FindClass("java/util/ArrayList")));
    java_util_ArrayList_     = env->GetMethodID(java_util_ArrayList, "<init>", "(I)V");
    java_util_ArrayList_add  = env->GetMethodID(java_util_ArrayList, "add", "(Ljava/lang/Object;)Z");

    contactClass = static_cast<jclass>(env->NewGlobalRef(env->FindClass("com/pthomasdesigns/libcontactandroid/Contact")));
    contactClass_ = env->GetMethodID(contactClass, "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
}

void ContactJni::shutdown(JNIEnv *env) {
    if(java_util_ArrayList != nullptr) {
        env->DeleteGlobalRef(java_util_ArrayList);
    }
    if(contactClass != nullptr) {
        env->DeleteGlobalRef(contactClass);
    }
}

Contact ContactJni::java2cpp(JNIEnv* env, jstring firstName, jstring lastName, jstring phoneNumber) {
    Contact result;
    const char* arg = nullptr;

    arg = env->GetStringUTFChars(firstName, nullptr);
    if (!arg) return result;
    result.setFirstName(arg);
    env->ReleaseStringUTFChars(firstName, arg);

    arg = env->GetStringUTFChars(lastName, nullptr);
    if (!arg) return result;
    result.setLastName(arg);
    env->ReleaseStringUTFChars(lastName, arg);

    arg = env->GetStringUTFChars(phoneNumber, nullptr);
    if (!arg) return result;
    result.setPhoneNumber(arg);
    env->ReleaseStringUTFChars(phoneNumber, arg);

    result.setValid();
    return result;

}
jobject ContactJni::cpp2java(JNIEnv* env, const Contact contact) {
    jstring jFirstName = env->NewStringUTF(contact.getFirstName().c_str());
    jstring jLastName = env->NewStringUTF(contact.getLastName().c_str());
    jstring jPhoneNumber = env->NewStringUTF(contact.getPhoneNumber().c_str());
    jobject jContact = env->NewObject(contactClass,
                                      contactClass_,
                                      jFirstName, jLastName, jPhoneNumber);
    env->DeleteLocalRef(jFirstName);
    env->DeleteLocalRef(jLastName);
    env->DeleteLocalRef(jPhoneNumber);

    return jContact;
}

jobject ContactJni::cpp2java(JNIEnv* env, const std::vector<Contact> contactList) {
    jobject result = env->NewObject(java_util_ArrayList, java_util_ArrayList_, contactList.size());
    for (auto contact : contactList) {
        jobject jcontact = cpp2java(env, contact);
        env->CallBooleanMethod(result, java_util_ArrayList_add, jcontact);
        if (env->ExceptionCheck()) {
            env->ExceptionDescribe();
        }
        env->DeleteLocalRef(jcontact);
    }
    return result;
}