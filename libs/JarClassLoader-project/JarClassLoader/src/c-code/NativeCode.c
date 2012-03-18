#include "com_jdotsoft_jarloader_test_NativeCode.h"

JNIEXPORT jstring JNICALL 
  Java_com_jdotsoft_jarloader_test_NativeCode_getString(JNIEnv *env, jobject ob)
{
  return (*env)->NewStringUTF(env, "String from native code");
}
