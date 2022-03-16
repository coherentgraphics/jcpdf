//Check "modified UTF8" not a problem for us
//Check all of chapter 10: traps and pitfalls in Java book
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

JNIEXPORT int JNICALL Java_Jcpdf_fromFileLazy
  (JNIEnv * env, jobject jobj, jstring filename, jstring userpw)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int pdf = cpdf_fromFileLazy(str_filename, str_userpw);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    return pdf;
}

JNIEXPORT int JNICALL Java_Jcpdf_blankDocument
  (JNIEnv * env, jobject jobj, jdouble w, jdouble h, jint pages)
{
    int pdf = cpdf_blankDocument(w, h, pages);
    return pdf;
}

JNIEXPORT int JNICALL Java_Jcpdf_blankDocumentPaper
  (JNIEnv * env, jobject jobj, jint papersize, jint pages)
{
    int pdf = cpdf_blankDocumentPaper(papersize, pages);
    return pdf;
}

JNIEXPORT int JNICALL Java_Jcpdf_startEnumeratePDFs
  (JNIEnv * env, jobject jobj)
{
    int n = cpdf_startEnumeratePDFs();
    return n;
}

JNIEXPORT int JNICALL Java_Jcpdf_enumeratePDFsKey
  (JNIEnv * env, jobject jobj, jint n)
{
    int i = cpdf_enumeratePDFsKey(n);
    return i;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_enumeratePDFsInfo
  (JNIEnv * env, jobject jobj, jint n)
{
    return (*env)->NewStringUTF(env, cpdf_enumeratePDFsInfo(n));
}

JNIEXPORT void JNICALL Java_Jcpdf_endEnumeratePDFs
  (JNIEnv * env, jobject jobj, jint n)
{
    cpdf_endEnumeratePDFs();
    return;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_ptOfCm
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfCm(f);
    return result;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_ptOfMm
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfMm(f);
    return result;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_ptOfIn
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfIn(f);
    return result;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_inOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_inOfPt(f);
    return result;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_cmOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_cmOfPt(f);
    return result;
}

JNIEXPORT jdouble JNICALL Java_Jcpdf_mmOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_mmOfPt(f);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_range
  (JNIEnv * env, jobject jobj, jint f, jint t)
{
    jint result = cpdf_range(f, t);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_all
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jint result = cpdf_all(pdf);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_even
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_even(r);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_odd
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_odd(r);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_rangeUnion
  (JNIEnv * env, jobject jobj, jint r, jint s)
{
    jint result = cpdf_rangeUnion(r, s);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_difference
  (JNIEnv * env, jobject jobj, jint r, jint s)
{
    jint result = cpdf_difference(r, s);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_removeDuplicates
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_removeDuplicates(r);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_rangeLength
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_rangeLength(r);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_rangeGet
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jint result = cpdf_rangeGet(r, n);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_rangeAdd
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jint result = cpdf_rangeAdd(r, n);
    return result;
}

JNIEXPORT jboolean JNICALL Java_Jcpdf_isInRange
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jboolean result = cpdf_isInRange(r, n);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_parsePagespec
  (JNIEnv * env, jobject jobj, jint pdf, jstring pagespec)
{
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jint result = cpdf_parsePagespec(pdf, str_pagespec);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_validatePagespec
  (JNIEnv * env, jobject jobj, jstring pagespec)
{
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jboolean result = cpdf_validatePagespec(str_pagespec);
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_stringOfPagespec
  (JNIEnv * env, jobject jobj, jint pdf, jint r)
{
    return (*env)->NewStringUTF(env, cpdf_stringOfPagespec(pdf, r));
}

JNIEXPORT jint JNICALL Java_Jcpdf_blankRange
  (JNIEnv * env, jobject jobj)
{
    jint result = cpdf_blankRange();
    return result;
}
