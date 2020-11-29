/* jcpdfwrapper.c */

#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_Jcpdf_helloFromC
  (JNIEnv * env, jobject jobj)
{
    printf("Hello from C!\n");
}
