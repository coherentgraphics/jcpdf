/* jcpdfwrapper.c */

#include <jni.h>
#include <stdio.h>
#include "cpdflibwrapper.h"

JNIEXPORT void JNICALL Java_Jcpdf_startup
  (JNIEnv * env, jobject jobj)
{
    char *argv[] = { "jcpdf", NULL };
    cpdf_startup(argv);
}

JNIEXPORT jstring JNICALL Java_Jcpdf_version
  (JNIEnv * env, jobject jobj)
{
    jstring result;
    result = (*env)->NewStringUTF(env, cpdf_version());
    return result;
}
