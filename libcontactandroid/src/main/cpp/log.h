//
// Created by TTHOMAS on 5/8/2018.
//

#ifndef CONTACTSAPP_LOG_H
#define CONTACTSAPP_LOG_H

#include <android/log.h>

// Android log function wrappers

#define LOGI(...) \
  ((void)__android_log_print(ANDROID_LOG_INFO, kTAG, __VA_ARGS__))
#define LOGW(...) \
  ((void)__android_log_print(ANDROID_LOG_WARN, kTAG, __VA_ARGS__))
#define LOGE(...) \
  ((void)__android_log_print(ANDROID_LOG_ERROR, kTAG, __VA_ARGS__))

#endif //CONTACTSAPP_LOG_H
