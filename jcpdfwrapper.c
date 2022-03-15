/* jcpdfwrapper.c */

#include <jni.h>
#include <stdio.h>
#include "cpdflibwrapper.h"

JNIEXPORT void JNICALL Java_Jcpdf_helloFromC
  (JNIEnv * env, jobject jobj)
{
    char *argv[] = { "jcpdf", NULL };
    cpdf_startup(argv);
    printf("Hello from C!\n");
}
