//Check "modified UTF8" not a problem for us
#include <jni.h>
#include <stdio.h>
#include "cpdflibwrapper.h"

/* CHAPTER 0. Preliminaries */
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
    return (*env)->NewStringUTF(env, cpdf_version()); //No need to check for null, since last statement in function.
}

JNIEXPORT void JNICALL Java_Jcpdf_setFast
  (JNIEnv * env, jobject jobj)
{
    cpdf_setFast();
}

JNIEXPORT void JNICALL Java_Jcpdf_setSlow
  (JNIEnv * env, jobject jobj)
{
    cpdf_setSlow();
}

JNIEXPORT int JNICALL Java_Jcpdf_fromFile
  (JNIEnv * env, jobject jobj, jstring filename, jstring userpw)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int pdf = cpdf_fromFile(str_filename, str_userpw);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    return pdf;
}
