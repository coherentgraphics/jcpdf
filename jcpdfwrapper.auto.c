#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "cpdflibwrapper.h"

/* RESOLVE jboolean vs jint - is it a problem? */

/* __AUTODEF pdf->float->unit
JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jdouble res)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_#(pdf, res);
    checkerror(env);
    return result;
}
*/

/* __AUTODEF pdf->range->int->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jint angle)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, range, angle);
    checkerror(env);
}
*/

/* __AUTODEF pdf->string->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    cpdf_#(pdf, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}
*/

/* __AUTODEF string->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    cpdf_#(str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}
*/

/* __AUTODEF pdf->string->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}
*/

/* __AUTODEF pdf->string
JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    char* result = cpdf_#(pdf);
    checkerror(env);
    return jbytearray_of_string(env, result);
}
*/

/* __AUTODEF pdf->int
JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    jint result = cpdf_#(pdf);
    checkerror(env);
    return result;
}
*/

/* __AUTODEF pdf->int->int
JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jint permission)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_#(pdf, permission);
    checkerror(env);
    return result;
}
*/

/* __AUTODEF pdf->data->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    cpdf_#(pdf, memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}
*/

/* __AUTODEF pdf->data
JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int len = 0;
    void* memory = cpdf_#(pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}
*/

/* __AUTODEF pdf->float->data
JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jdouble res)
{
    int pdf = getPDF(env, jobj, opdf);
    int len = 0;
    void* memory = cpdf_#(pdf, &len, res);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}
*/

/* __AUTODEF int->pdf->data
JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jint filesize)
{
    int pdf = getPDF(env, jobj, opdf);
    int len = 0;
    void* memory = cpdf_#(filesize, pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}
*/

/* __AUTODEF pdf->range->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, range);
    checkerror(env);
}
*/

/* __AUTODEF pdf->range->double->double->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jdouble dx, jdouble dy)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, range, dx, dy);
    checkerror(env);
}
*/

/* __AUTODEF float->float
JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_#(f);
    checkerror(env);
    return result;
}
*/

/* __AUTODEF pdf->int->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jint layout)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, layout);
    checkerror(env);
}
*/

/* __AUTODEF pdf->bool->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jboolean flag)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, flag);
    checkerror(env);
}
*/

/* __AUTODEF pdf->int->write4
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jint pagenumber, jdoubleArray data)
{
    int pdf = getPDF(env, jobj, opdf);
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_#(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}
*/

/* __AUTODEF pdf->range->4doubles->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}
*/

/* __AUTODEF pdf->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_#(pdf);
    checkerror(env);
}
*/

/* __AUTODEF unit->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj)
{
    cpdf_#();
    checkerror(env);
}
*/

/* __AUTODEF unit->string
JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj)
{
    jstring str = (*env)->NewStringUTF(env, cpdf_#());
    checkerror(env);
    return str;
}
*/

/* __AUTODEF int->unit
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_#(pdf);
}
*/

/* __AUTODEF string->string->pdf
JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int pdf = cpdf_#(str, str2);
    free(str);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return makePDF(env, jobj, pdf);
}
*/

/* __AUTODEF int->int
JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jint n)
{
    int i = cpdf_#(n);
    checkerror(env);
    return i;
}
*/

/* __AUTODEF int->string
JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_~
  (JNIEnv * env, jobject jobj, jint n)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_#(n));
    checkerror(env);
    return result;
}
*/

/* Internal helper functions */

jobject makePDF(JNIEnv * env, jobject jobj, jint pdf)
{
    jclass class = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$Pdf");
    if (class == NULL) return NULL;
    jmethodID cid = (*env)->GetMethodID(env, class, "<init>", "(Lcom/coherentpdf/Jcpdf;I)V");
    if (cid == NULL) return NULL;
    return (*env)->NewObject(env, class, cid, jobj, pdf);
}

int getPDF(JNIEnv * env, jobject jobj, jobject opdf)
{
   jclass class = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$Pdf");
   if (class == NULL) fprintf(stderr, "***getpdf: class null\n");
   jfieldID fid = (*env)->GetFieldID(env, class, "pdf", "I");
   if (class == NULL) fprintf(stderr, "***getpdf: fid null\n");
   int result = (*env)->GetIntField(env, opdf, fid);
   return result;
}

jobject makeRange(JNIEnv * env, jobject jobj, jint range)
{
    jclass class = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$Range");
    if (class == NULL) return NULL;
    jmethodID cid = (*env)->GetMethodID(env, class, "<init>", "(Lcom/coherentpdf/Jcpdf;I)V");
    if (cid == NULL) return NULL;
    return (*env)->NewObject(env, class, cid, jobj, range);
}

int getRange(JNIEnv * env, jobject jobj, jobject orange)
{
   jclass class = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$Range");
   if (class == NULL) fprintf(stderr, "***getpdf: class null\n");
   jfieldID fid = (*env)->GetFieldID(env, class, "range", "I");
   if (class == NULL) fprintf(stderr, "***getpdf: fid null\n");
   int result = (*env)->GetIntField(env, orange, fid);
   return result;
}

void checkerror(JNIEnv *env)
{
  if (cpdf_fLastError() != 0)
  {
      cpdf_clearError();
      jclass cls = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$CpdfError");
      if (cls != NULL) (*env)->ThrowNew(env, cls, cpdf_fLastErrorString());
      (*env)->DeleteLocalRef(env, cls);
  }
}

char * cstring_of_jbytes(jbyte * bytes, int length)
{
    char* memory = (char *) bytes;
    char* str = malloc((length + 1) * sizeof(char));
    for (int x = 0; x < length; x++) {str[x] = memory[x];};
    str[length] = 0;
    return str;
}

jbyteArray jbytearray_of_string(JNIEnv *env, char* str)
{
    int len = strlen(str);
    jbyteArray data = (*env)->NewByteArray(env, len);
    jbyte *bytes = (*env)->GetByteArrayElements(env, data, 0);
    for (int i = 0; i < len; i++) { bytes[i] = str[i]; }
    (*env)->ReleaseByteArrayElements(env, data, bytes, 0);
    return data;
}

/* __AUTO deletePdf int->unit */
/* __AUTO deleteRange int->unit */
/* __AUTO onExit unit->unit */

/* CHAPTER 0. Preliminaries */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startup
  (JNIEnv * env, jobject jobj)
{
    char *argv[] = { "jcpdf", NULL };
    cpdf_startup(argv);
    checkerror(env);
}

/* __AUTO version unit->string */
/* __AUTO setFast unit->unit */
/* __AUTO setSlow unit->unit */
/* __AUTO embedStd14 int->unit */
/* __AUTO XembedStd14Dir string->unit */
/* __AUTO JSONUTF8 int->unit */

/* CHAPTER 1. Basics */

/* __AUTO XfromFile string->string->pdf */
/* __AUTO XfromFileLazy string->string->pdf */

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_toMemory
  (JNIEnv * env, jobject obj, jobject opdf, jboolean linearize, jboolean make_id)
{
    int pdf = getPDF(env, obj, opdf);
    int len = 0;
    void* memory = cpdf_toMemory(pdf, linearize, make_id, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_XfromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int result = cpdf_fromMemory(memory, length, str2);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

void* memory;

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_fromMemoryLazyRelease
  (JNIEnv *env, jobject jobj, jbyteArray data)
{
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_XfromMemoryLazy
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2)
{
    int length = (*env)->GetArrayLength(env, data);
    memory = (*env)->GetByteArrayElements(env, data, 0); 
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int result = cpdf_fromMemoryLazy(memory, length, str2);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}


JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startEnumeratePDFs
  (JNIEnv * env, jobject jobj)
{
    int n = cpdf_startEnumeratePDFs();
    checkerror(env);
    return n;
}

/* __AUTO enumeratePDFsKey int->int */
/* __AUTO enumeratePDFsInfo int->string */
/* __AUTO endEnumeratePDFs unit->unit */
/* __AUTO ptOfCm float->float */
/* __AUTO ptOfMm float->float */
/* __AUTO ptOfIn float->float */
/* __AUTO inOfPt float->float */
/* __AUTO cmOfPt float->float */
/* __AUTO mmOfPt float->float */

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_range
  (JNIEnv * env, jobject jobj, jint f, jint t)
{
    jint result = cpdf_range(f, t);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_all
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    jint result = cpdf_all(pdf);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject
  JNICALL Java_com_coherentpdf_Jcpdf_even
  (JNIEnv * env, jobject jobj, jobject or)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_even(r);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_odd
  (JNIEnv * env, jobject jobj, jobject or)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_odd(r);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_rangeUnion
  (JNIEnv * env, jobject jobj, jobject or, jobject os)
{
    int r = getRange(env, jobj, or);
    int s = getRange(env, jobj, os);
    jint result = cpdf_rangeUnion(r, s);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_difference
  (JNIEnv * env, jobject jobj, jobject or, jobject os)
{
    int r = getRange(env, jobj, or);
    int s = getRange(env, jobj, os);
    jint result = cpdf_difference(r, s);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_removeDuplicates
  (JNIEnv * env, jobject jobj, jobject or)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_removeDuplicates(r);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeLength
  (JNIEnv * env, jobject jobj, jobject or)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_rangeLength(r);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeGet
  (JNIEnv * env, jobject jobj, jobject or, jint n)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_rangeGet(r, n);
    checkerror(env);
    return result;
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_rangeAdd
  (JNIEnv * env, jobject jobj, jobject or, jint n)
{
    int r = getRange(env, jobj, or);
    jint result = cpdf_rangeAdd(r, n);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_isInRange
  (JNIEnv * env, jobject jobj, jobject or, jint n)
{
    int r = getRange(env, jobj, or);
    jboolean result = cpdf_isInRange(r, n);
    checkerror(env);
    return result;
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_parsePagespec
  (JNIEnv * env, jobject jobj, jobject opdf, jstring pagespec)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jint result = cpdf_parsePagespec(pdf, str_pagespec);
    checkerror(env);
    return makeRange(env, jobj, result);
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_validatePagespec
  (JNIEnv * env, jobject jobj, jstring pagespec)
{
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jboolean result = cpdf_validatePagespec(str_pagespec);
    checkerror(env);
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_XstringOfPagespec
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or)
{
    int pdf = getPDF(env, jobj, opdf);
    int r = getRange(env, jobj, or);
    char* str = cpdf_stringOfPagespec(pdf, r);
    checkerror(env);
    return jbytearray_of_string(env, str);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_blankRange
  (JNIEnv * env, jobject jobj)
{
    jint result = cpdf_blankRange();
    checkerror(env);
    return makeRange(env, jobj, result);
}

/* __AUTO pages pdf->int */

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_XpagesFast
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    jint result = cpdf_pagesFast(str, str2);
    free(str);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XtoFile
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data, jboolean linearize, jboolean make_id)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    cpdf_toFile(pdf, str, linearize, make_id);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XtoFileExt
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data, jboolean linearize, jboolean make_id, jboolean preserve_objstm, jboolean create_objstm, jboolean compress_objstm)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    cpdf_toFileExt(pdf, str, linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XtoFileEncrypted
  (JNIEnv * env, jobject jobj, jobject opdf, jint encryption_method, jintArray intdata, jbyteArray data, jbyteArray data2, jboolean linearize, jboolean makeid, jbyteArray data3)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int length3 = (*env)->GetArrayLength(env, data3);
    jbyte* memory3 = (*env)->GetByteArrayElements(env, data3, 0);
    char* str3 = cstring_of_jbytes(memory3, length3);
    int pdf = getPDF(env, jobj, opdf);
    int len = (*env)->GetArrayLength(env, intdata);
    int* perms = (*env)->GetIntArrayElements(env, intdata, 0); 
    cpdf_toFileEncrypted(pdf, encryption_method, perms, len, str, str2, linearize, makeid, str3);
    (*env)->ReleaseIntArrayElements(env, intdata, perms, 0);
    free(str);
    free(str2);
    free(str3);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data3, (jbyte *) memory3, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XtoFileEncryptedExt
  (JNIEnv * env, jobject jobj, jobject opdf, jint encryption_method, jintArray intdata, jbyteArray data, jbyteArray data2, jboolean linearize, jboolean makeid, jboolean preserve_objstm, jboolean generate_objstm, jboolean compress_objstm, jbyteArray data3)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int length3 = (*env)->GetArrayLength(env, data3);
    jbyte* memory3 = (*env)->GetByteArrayElements(env, data3, 0);
    char* str3 = cstring_of_jbytes(memory3, length3);
    int pdf = getPDF(env, jobj, opdf);
    int len = (*env)->GetArrayLength(env, intdata);
    int* perms = (*env)->GetIntArrayElements(env, intdata, 0); 
    cpdf_toFileEncryptedExt(pdf, encryption_method, perms, len, str, str2, linearize, makeid, preserve_objstm, generate_objstm, compress_objstm, str3);
    (*env)->ReleaseIntArrayElements(env, intdata, perms, 0);
    free(str);
    free(str2);
    free(str3);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data3, (jbyte *) memory3, 0);
    checkerror(env);
}

/* __AUTO encryptionKind pdf->int */
/* __AUTO hasPermission pdf->int->int */
/* __AUTO XdecryptPdf pdf->string->unit */
/* __AUTO XdecryptPdfOwner pdf->string->unit */
/* __AUTO isEncrypted pdf->int */

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_XisLinearized
  (JNIEnv * env, jobject jobj, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    jboolean result = cpdf_isLinearized(str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
    return result;
}

/* CHAPTER 2. Merging and Splitting */

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_mergeSimple
  (JNIEnv * env, jobject jobj, jobjectArray data)
{
    int len = (*env)->GetArrayLength(env, data);
    int* perms = malloc(len * sizeof(int));
    for (int x = 0; x < len; x++)
    {
        perms[x] = getPDF(env, jobj, (*env)->GetObjectArrayElement(env, data, x));
    }
    int result = cpdf_mergeSimple(perms, len);
    free(perms);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_merge
  (JNIEnv * env, jobject jobj, jobjectArray data, jboolean retain_numbering, jboolean remove_duplicate_fonts)
{
    int len = (*env)->GetArrayLength(env, data);
    int* perms = malloc(len * sizeof(int));
    for (int x = 0; x < len; x++)
    {
        perms[x] = getPDF(env, jobj, (*env)->GetObjectArrayElement(env, data, x));
    }
    int result = cpdf_merge(perms, len, retain_numbering, remove_duplicate_fonts);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_mergeSame
  (JNIEnv * env, jobject jobj, jobjectArray data, jboolean retain_numbering, jboolean remove_duplicate_fonts, jobjectArray data2)
{
    int len = (*env)->GetArrayLength(env, data);
    int* perms = malloc(len * sizeof(int));
    for (int x = 0; x < len; x++)
    {
        perms[x] = getPDF(env, jobj, (*env)->GetObjectArrayElement(env, data, x));
    }
    int* ranges = malloc(len * sizeof(int));
    for (int x = 0; x < len; x++)
    {
        ranges[x] = getRange(env, jobj, (*env)->GetObjectArrayElement(env, data2, x));
    }
    int result = cpdf_mergeSame(perms, len, retain_numbering, remove_duplicate_fonts, ranges);
    free(perms);
    free(ranges);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_selectPages
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int pdf = getPDF(env, jobj, opdf);
    int range = getRange(env, jobj, orange);
    int result = cpdf_selectPages(pdf, range);
    checkerror(env);
    return makePDF(env, jobj, result);
}

/* CHAPTER 3. Pages */

/* __AUTO scalePages pdf->range->double->double->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleToFit
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jdouble w, jdouble h, jdouble scale)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_scaleToFit(pdf, range, w, h, scale);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleToFitPaper
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jint papersize, jdouble scale)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_scaleToFitPaper(pdf, range, papersize, scale);
    checkerror(env);
}

/* __AUTO shiftContents pdf->range->double->double->unit */
/* __AUTO shiftBoxes pdf->range->double->double->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleContents
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jint anchor, jdouble p1, jdouble p2, jdouble scale)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_scaleContents(pdf, range, p, scale);
    checkerror(env);
}



/* __AUTO rotate pdf->range->int->unit */
/* __AUTO rotateBy pdf->range->int->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_rotateContents
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jdouble angle)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_rotateContents(pdf, range, angle);
    checkerror(env);
}


/* __AUTO upright pdf->range->unit */
/* __AUTO hFlip pdf->range->unit */
/* __AUTO vFlip pdf->range->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_crop
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jdouble x, jdouble y, jdouble w, jdouble h)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_crop(pdf, range, x, y, w, h);
    checkerror(env);
}

/* __AUTO removeCrop pdf->range->unit */
/* __AUTO removeTrim pdf->range->unit */
/* __AUTO removeArt pdf->range->unit */
/* __AUTO removeBleed pdf->range->unit */
/* __AUTO trimMarks pdf->range->unit */
/* __AUTO showBoxes pdf->range->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hardBox
  (JNIEnv * env, jobject jobj, jobject opdf, jobject or, jstring box)
{
    int range = getRange(env, jobj, or);
    int pdf = getPDF(env, jobj, opdf);
    const char *str_box = (*env)->GetStringUTFChars(env, box, 0);
    cpdf_hardBox(pdf, range, str_box);
    checkerror(env);
}

/* CHAPTER 5. Compression */

/* __AUTO compress pdf->unit */
/* __AUTO decompress pdf->unit */
/* __AUTO squeezeInMemory pdf->unit */

/* CHAPTER 6. Bookmarks */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetBookmarkInfo
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_startGetBookmarkInfo(pdf);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_numberBookmarks
  (JNIEnv * env, jobject jobj)
{
    int result = cpdf_numberBookmarks();
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getBookmarkLevel
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getBookmarkLevel(serial);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getBookmarkPage
  (JNIEnv * env, jobject jobj, jobject opdf, jint serial)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_getBookmarkPage(pdf, serial);
    checkerror(env);
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_XgetBookmarkText
  (JNIEnv * env, jobject jobj, jint serial)
{
    char* result = cpdf_getBookmarkText(serial);
    checkerror(env);
    return jbytearray_of_string(env, result);
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_getBookmarkOpenStatus
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getBookmarkOpenStatus(serial);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetBookmarkInfo
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetBookmarkInfo();
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startSetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint n)
{
    cpdf_startSetBookmarkInfo(n);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarkLevel
  (JNIEnv * env, jobject jobj, jint serial, jint level)
{
    cpdf_setBookmarkLevel(serial, level);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarkPage
  (JNIEnv * env, jobject jobj, jobject opdf, jint serial, jint pagenum)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_setBookmarkPage(pdf, serial, pagenum);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarkOpenStatus
  (JNIEnv * env, jobject jobj, jint serial, jboolean open)
{
    cpdf_setBookmarkOpenStatus(serial, open);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XsetBookmarkText
  (JNIEnv * env, jobject jobj, jint serial, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    cpdf_setBookmarkText(serial, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endSetBookmarkInfo
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_endSetBookmarkInfo(pdf);
    checkerror(env);
}

/* __AUTO getBookmarksJSON pdf->data */
/* __AUTO setBookmarksJSON pdf->data->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XtableOfContents
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray font, jdouble fontsize, jbyteArray data, jboolean bookmark)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, font);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, font, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    cpdf_tableOfContents(pdf, str2, fontsize, str, bookmark);
    free(str);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, font, (jbyte *) memory2, 0);
    checkerror(env);
}

/* CHAPTER 7. Presentations */

/* Not included in the library version */

/* CHAPTER 8. Logos, Watermarks and Stamps */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampOn
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2, jobject orange)
{
    int pdf = getPDF(env, jobj, opdf);
    int pdf2 = getPDF(env, jobj, opdf2);
    int range = getRange(env, jobj, orange);
    cpdf_stampOn(pdf, pdf2, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampUnder
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2, jobject orange)
{
    int pdf = getPDF(env, jobj, opdf);
    int pdf2 = getPDF(env, jobj, opdf2);
    int range = getRange(env, jobj, orange);
    cpdf_stampUnder(pdf, pdf2, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampExtended
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2, jobject orange, jboolean isover, jboolean scale_stamp_to_fit, jint anchor, jdouble p1, jdouble p2, jboolean relative_to_cropbox)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    int pdf2 = getPDF(env, jobj, opdf2);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_stampExtended(pdf, pdf2, range, isover, scale_stamp_to_fit, p, relative_to_cropbox);
    checkerror(env);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_combinePages
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2)
{
    int pdf = getPDF(env, jobj, opdf);
    int pdf2 = getPDF(env, jobj, opdf2);
    int result = cpdf_combinePages(pdf, pdf2);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XaddText
  (JNIEnv * env, jobject jobj, jboolean metrics, jobject opdf, jobject orange, jbyteArray data, jint anchor, jdouble p1, jdouble p2, jdouble linespacing, jint bates, jbyteArray data3, jdouble fontsize, jdouble r, jdouble g, jdouble b, jboolean underneath, jboolean cropbox, jboolean outline, jdouble opacity, jint justification, jboolean midline, jboolean topline, jbyteArray data2, jdouble linewidth, jboolean embed_fonts)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int length3 = (*env)->GetArrayLength(env, data3);
    jbyte* memory3 = (*env)->GetByteArrayElements(env, data3, 0);
    char* str3 = cstring_of_jbytes(memory3, length3);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_addText(metrics, pdf, range, str, p, linespacing, bates, str3, fontsize, r, g, b, underneath, cropbox, outline, opacity, justification, midline, topline, str2, linewidth, embed_fonts);
    free(str);
    free(str2);
    free(str3);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data3, (jbyte *) memory3, 0);
    checkerror(env);
}

  JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XaddTextSimple
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange, jbyteArray data, jint anchor, jdouble p1, jdouble p2, jbyteArray data2, jdouble fontsize)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    cpdf_addTextSimple(pdf, range, str, p, str2, fontsize);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);    
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeText
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeText(pdf, range);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_XtextWidth
  (JNIEnv * env, jobject jobj, jbyteArray data2, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    int w = cpdf_textWidth(str2, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return w;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_stampAsXObject
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange, jobject ostamp_pdf)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    int stamp_pdf = getPDF(env, jobj, ostamp_pdf);
    jstring result = (*env)->NewStringUTF(env, cpdf_stampAsXObject(pdf, range, stamp_pdf));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XaddContent
  (JNIEnv * env, jobject jobj, jbyteArray data, jboolean before, jobject opdf, jobject orange)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_addContent(str, before, pdf, range);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

/* CHAPTER 9. Multipage facilities */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_impose
  (JNIEnv * env, jobject jobj, jobject opdf, jdouble x, jdouble y, jboolean fit, jboolean columns, jboolean rtl, jboolean btt, jboolean center, jdouble margin, jdouble spacing, jdouble linewidth)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_impose(pdf, x, y, fit, columns, rtl, btt, center, margin, spacing, linewidth);
    checkerror(env);
}

/* __AUTO twoUp pdf->unit */
/* __AUTO twoUpStack pdf->unit */
/* __AUTO padBefore pdf->range->unit */
/* __AUTO padAfter pdf->range->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padEvery
  (JNIEnv * env, jobject jobj, jobject opdf, int n)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_padEvery(pdf, n);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padMultiple
  (JNIEnv * env, jobject jobj, jobject opdf, int n)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_padMultiple(pdf, n);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padMultipleBefore
  (JNIEnv * env, jobject jobj, jobject opdf, int n)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_padMultipleBefore(pdf, n);
    checkerror(env);
}

/* CHAPTER 10. Annotations */

/* __AUTO annotationsJSON pdf->data */
/* __AUTO removeAnnotations pdf->range->unit */
/* __AUTO setAnnotationsJSON pdf->data->unit */

/* CHAPTER 11. Document Information and Metadata */

/* __AUTO getVersion pdf->int */
/* __AUTO getMajorVersion pdf->int */
/* __AUTO XgetTitle pdf->string */
/* __AUTO XgetAuthor pdf->string */
/* __AUTO XgetSubject pdf->string */
/* __AUTO XgetKeywords pdf->string */
/* __AUTO XgetCreator pdf->string */
/* __AUTO XgetProducer pdf->string */
/* __AUTO XgetCreationDate pdf->string */
/* __AUTO XgetModificationDate pdf->string */
/* __AUTO XgetTitleXMP pdf->string */
/* __AUTO XgetAuthorXMP pdf->string */
/* __AUTO XgetSubjectXMP pdf->string */
/* __AUTO XgetKeywordsXMP pdf->string */
/* __AUTO XgetCreatorXMP pdf->string */
/* __AUTO XgetProducerXMP pdf->string */
/* __AUTO XgetCreationDateXMP pdf->string */
/* __AUTO XgetModificationDateXMP pdf->string */
/* __AUTO pageInfoJSON pdf->data */
/* __AUTO XsetTitle pdf->string->unit */
/* __AUTO XsetAuthor pdf->string->unit */
/* __AUTO XsetSubject pdf->string->unit */
/* __AUTO XsetKeywords pdf->string->unit */
/* __AUTO XsetCreator pdf->string->unit */
/* __AUTO XsetProducer pdf->string->unit */
/* __AUTO XsetCreationDate pdf->string->unit */
/* __AUTO XsetModificationDate pdf->string->unit */
/* __AUTO XsetTitleXMP pdf->string->unit */
/* __AUTO XsetAuthorXMP pdf->string->unit */
/* __AUTO XsetSubjectXMP pdf->string->unit */
/* __AUTO XsetKeywordsXMP pdf->string->unit */
/* __AUTO XsetCreatorXMP pdf->string->unit */
/* __AUTO XsetProducerXMP pdf->string->unit */
/* __AUTO XsetCreationDateXMP pdf->string->unit */
/* __AUTO XsetModificationDateXMP pdf->string->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getDateComponents
  (JNIEnv * env, jobject jobj, jstring str, jintArray data)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    int year, month, day, hour, minute, second, hour_offset, minute_offset;
    int* a = (*env)->GetIntArrayElements(env, data, 0); 
    cpdf_getDateComponents(str_str, &year, &month, &day, &hour, &minute, &second, &hour_offset, &minute_offset);
    a[0] = year;
    a[1] = month;
    a[2] = day;
    a[3] = hour;
    a[4] = minute;
    a[5] = second;
    a[6] = hour_offset;
    a[7] = minute_offset;
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseIntArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_dateStringOfComponents
  (JNIEnv * env, jobject jobj, jint a, jint b, jint c, jint d, jint e, jint f, jint g, jint h)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_dateStringOfComponents(a, b, c, d, e, f, g, h));
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getPageRotation
  (JNIEnv * env, jobject jobj, jobject opdf, jint pagenumber)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_getPageRotation(pdf, pagenumber);
    checkerror(env);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_hasBox
  (JNIEnv * env, jobject jobj, jobject opdf, jint pagenumber, jstring boxname)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_boxname = (*env)->GetStringUTFChars(env, boxname, 0);
    int result = cpdf_hasBox(pdf, pagenumber, str_boxname);
    (*env)->ReleaseStringUTFChars(env, boxname, str_boxname);
    checkerror(env);
    return result;
}

/* __AUTO numAnnots pdf->int->int */
/* __AUTO getMediaBox pdf->int->write4 */
/* __AUTO getCropBox pdf->int->write4 */
/* __AUTO getArtBox pdf->int->write4 */
/* __AUTO getTrimBox pdf->int->write4 */
/* __AUTO getBleedBox pdf->int->write4 */
/* __AUTO setMediabox pdf->range->4doubles->unit */
/* __AUTO setCropBox pdf->range->4doubles->unit */
/* __AUTO setArtBox pdf->range->4doubles->unit */
/* __AUTO setBleedBox pdf->range->4doubles->unit */
/* __AUTO setTrimBox pdf->range->4doubles->unit */
/* __AUTO markTrapped pdf->unit */
/* __AUTO markUntrapped pdf->unit */
/* __AUTO markTrappedXMP pdf->unit */
/* __AUTO markUntrappedXMP pdf->unit */
/* __AUTO setPageLayout pdf->int->unit */
/* __AUTO setPageMode pdf->int->unit */
/* __AUTO hideToolbar pdf->bool->unit */
/* __AUTO hideMenubar pdf->bool->unit */
/* __AUTO hideWindowUi pdf->bool->unit */
/* __AUTO fitWindow pdf->bool->unit */
/* __AUTO centerWindow pdf->bool->unit */
/* __AUTO displayDocTitle pdf->bool->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_openAtPage
  (JNIEnv * env, jobject jobj, jobject opdf, jboolean fit, jint pagenumber)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_openAtPage(pdf, fit, pagenumber);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XsetMetadataFromFile
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_setMetadataFromFile(pdf, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_getMetadata
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int len = 0;
    void* memory = cpdf_getMetadata(pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMetadataFromByteArray
  (JNIEnv * env, jobject jobj, jobject opdf, jbyteArray data)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    signed char* memory = (*env)->GetByteArrayElements(env, data, 0); 
    cpdf_setMetadataFromByteArray(pdf, memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}

/* __AUTO removeMetadata pdf->unit */
/* __AUTO createMetadata pdf->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMetadataDate
  (JNIEnv * env, jobject jobj, jobject opdf, jstring date)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_date = (*env)->GetStringUTFChars(env, date, 0);
    cpdf_setMetadataDate(pdf, str_date);
    (*env)->ReleaseStringUTFChars(env, date, str_date);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XaddPageLabels
  (JNIEnv * env, jobject jobj, jobject opdf, jint style, jbyteArray data, jint offset, jobject orange, jboolean progress)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_addPageLabels(pdf, style, str, offset, range, progress);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removePageLabels
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removePageLabels(pdf);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startGetPageLabels
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_startGetPageLabels(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getPageLabelOffset
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelOffset(n);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getPageLabelStyle
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelStyle(n);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getPageLabelRange
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelRange(n);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetPageLabels
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetPageLabels();
    checkerror(env);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_XgetPageLabelPrefix
  (JNIEnv * env, jobject jobj, jint n)
{
    char* result = cpdf_getPageLabelPrefix(n);
    checkerror(env);
    return jbytearray_of_string(env, result);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_XgetPageLabelStringForPage
  (JNIEnv * env, jobject jobj, jobject opdf, jint n)
{
    int pdf = getPDF(env, jobj, opdf);
    char* result = cpdf_getPageLabelStringForPage(pdf, n);
    checkerror(env);
    return jbytearray_of_string(env, result);
}

/* __AUTO compositionJSON int->pdf->data */

/* CHAPTER 12. File Attachments */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XattachFile
  (JNIEnv * env, jobject jobj, jbyteArray data, jobject opdf)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_attachFile(str, pdf);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XattachFileToPage
  (JNIEnv * env, jobject jobj, jbyteArray data, jobject opdf, jint pagenumber)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_attachFileToPage(str, pdf, pagenumber);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XattachFileFromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    cpdf_attachFileFromMemory(memory, length, str2, pdf);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XattachFileToPageFromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jbyteArray data2, jobject opdf, jint pagenumber)
{
    int pdf = getPDF(env, jobj, opdf);
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);
    cpdf_attachFileToPageFromMemory(memory, length, str2, pdf, pagenumber);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}

/* __AUTO removeAttachedFiles pdf->unit */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetAttachments
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_startGetAttachments(pdf);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_numberGetAttachments
  (JNIEnv * env, jobject jobj)
{
    int result = cpdf_numberGetAttachments();
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getAttachmentName
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getAttachmentName(serial));
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getAttachmentPage
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getAttachmentPage(serial);
    checkerror(env);
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_getAttachmentData
  (JNIEnv * env, jobject obj, jint serial)
{
    int len = 0;
    void* memory = cpdf_getAttachmentData(serial, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetAttachments
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetAttachments();
    checkerror(env);
}

/* CHAPTER 13. Images */

/* __AUTO startGetImageResolution pdf->float->unit */
/* __AUTO getImageResolutionPageNumber int->int */
/* __AUTO getImageResolutionImageName int->string */
/* __AUTO getImageResolutionXPixels int->int */
/* __AUTO getImageResolutionYPixels int->int */
/* __AUTO getImageResolutionXRes int->int */
/* __AUTO getImageResolutionYRes int->int */
/* __AUTO getImageResolutionObjNum int->int */
/* __AUTO endGetImageResolution unit->unit */
/* __AUTO imagesJSON pdf->data */
/* __AUTO imageResolutionJSON pdf->float->data */

/* CHAPTER 14. Fonts */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetFontInfo
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_startGetFontInfo(pdf);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_numberFonts
  (JNIEnv * env, jobject jobj)
{
    int result = cpdf_numberFonts();
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getFontName
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getFontName(serial));
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getFontPage
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getFontPage(serial);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getFontType
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getFontType(serial));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getFontEncoding
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getFontEncoding(serial));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetFontInfo
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetFontInfo();
    checkerror(env);
}

/* __AUTO fontsJSON pdf->data */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeFonts
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeFonts(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_copyFont
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2, jobject orange, jint pagenumber, jstring fontname)
{
    int from_pdf = getPDF(env, jobj, opdf);
    int to_pdf = getPDF(env, jobj, opdf2);
    int range = getRange(env, jobj, orange);
    const char *str_fontname = (*env)->GetStringUTFChars(env, fontname, 0);
    cpdf_copyFont(from_pdf, to_pdf, range, pagenumber, str_fontname);
    (*env)->ReleaseStringUTFChars(env, fontname, str_fontname);
    checkerror(env);
}

/* CHAPTER 15. PDF and JSON */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_XoutputJSON
  (JNIEnv * env, jobject jobj, jbyteArray data, jboolean parse_content, jboolean no_stream_data, jboolean decompress_streams, jobject opdf)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_outputJSON(str, parse_content, no_stream_data, decompress_streams, pdf);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_XfromJSON
  (JNIEnv * env, jobject jobj, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int result = cpdf_fromJSON(str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_outputJSONMemory
  (JNIEnv * env, jobject jobj, jobject opdf, jboolean parse_content, jboolean no_stream_data, jboolean decompress_streams)
{
  int pdf = getPDF(env, jobj, opdf);
  int len = 0;
  void* memory = cpdf_outputJSONMemory(pdf, parse_content, no_stream_data, decompress_streams, &len);
  jbyteArray b = (*env)->NewByteArray(env, len);
  (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
  free(memory);
  return b;
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_fromJSONMemory
  (JNIEnv * env, jobject jobj, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    int result = cpdf_fromJSONMemory(memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

/* CHAPTER 16. Optional Content Groups */

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startGetOCGList
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_startGetOCGList(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_OCGListEntry
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_OCGListEntry(serial));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetOCGList
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetOCGList();
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_OCGCoalesce
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_OCGCoalesce(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_OCGRename
  (JNIEnv * env, jobject jobj, jobject opdf, jstring f, jstring t)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_f = (*env)->GetStringUTFChars(env, f, 0);
    const char *str_t = (*env)->GetStringUTFChars(env, t, 0);
    cpdf_OCGRename(pdf, str_f, str_t);
    (*env)->ReleaseStringUTFChars(env, f, str_f);
    (*env)->ReleaseStringUTFChars(env, t, str_t);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_OCGOrderAll
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_OCGOrderAll(pdf);
    checkerror(env);
}

/* CHAPTER 17. Creating New PDFs */

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_blankDocument
  (JNIEnv * env, jobject jobj, jdouble w, jdouble h, jint pages)
{
    int pdf = cpdf_blankDocument(w, h, pages);
    checkerror(env);
    return makePDF(env, jobj, pdf);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_blankDocumentPaper
  (JNIEnv * env, jobject jobj, jint papersize, jint pages)
{
    int pdf = cpdf_blankDocumentPaper(papersize, pages);
    checkerror(env);
    return makePDF(env, jobj, pdf);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_XtextToPDF
  (JNIEnv * env, jobject jobj, jdouble w, jdouble h, jbyteArray data2, jdouble fontsize, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);    
    int result = cpdf_textToPDF(w, h, str2, fontsize, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_XtextToPDFPaper
  (JNIEnv * env, jobject jobj, jint papersize, jbyteArray data2, jdouble fontsize, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    jbyte* memory = (*env)->GetByteArrayElements(env, data, 0);
    char* str = cstring_of_jbytes(memory, length);
    int length2 = (*env)->GetArrayLength(env, data2);
    jbyte* memory2 = (*env)->GetByteArrayElements(env, data2, 0);
    char* str2 = cstring_of_jbytes(memory2, length2);    
    int result = cpdf_textToPDFPaper(papersize, str2, fontsize, str);
    free(str);
    (*env)->ReleaseByteArrayElements(env, data, (jbyte *) memory, 0);
    free(str2);
    (*env)->ReleaseByteArrayElements(env, data2, (jbyte *) memory2, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

/* CHAPTER 18. Miscellaneous */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_draft
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange, jboolean boxes)
{
    int pdf = getPDF(env, jobj, opdf);
    int range = getRange(env, jobj, orange);
    cpdf_draft(pdf, range, boxes);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeAllText
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeAllText(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackText
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_blackText(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackLines
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_blackLines(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackFills
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_blackFills(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_thinLines
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange, jdouble minwidth)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_thinLines(pdf, range, minwidth);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_copyId
  (JNIEnv * env, jobject jobj, jobject opdf, jobject opdf2)
{
    int pdf = getPDF(env, jobj, opdf);
    int pdf2 = getPDF(env, jobj, opdf2);
    cpdf_copyId(pdf, pdf2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeId
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeId(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setVersion
  (JNIEnv * env, jobject jobj, jobject opdf, jint version)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_setVersion(pdf, version);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setFullVersion
  (JNIEnv * env, jobject jobj, jobject opdf, jint major, jint minor)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_setFullVersion(pdf, major, minor);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeDictEntry
  (JNIEnv * env, jobject jobj, jobject opdf, jstring str)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_removeDictEntry(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeDictEntrySearch
  (JNIEnv * env, jobject jobj, jobject opdf, jstring str, jstring str2)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    const char *str_str2 = (*env)->GetStringUTFChars(env, str2, 0);
    cpdf_removeDictEntrySearch(pdf, str_str, str_str2);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseStringUTFChars(env, str2, str_str2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_replaceDictEntry
  (JNIEnv * env, jobject jobj, jobject opdf, jstring str, jstring str2)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    const char *str_str2 = (*env)->GetStringUTFChars(env, str2, 0);
    cpdf_replaceDictEntry(pdf, str_str, str_str2);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseStringUTFChars(env, str2, str_str2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_replaceDictEntrySearch
  (JNIEnv * env, jobject jobj, jobject opdf, jstring str, jstring str2, jstring str3)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    const char *str_str2 = (*env)->GetStringUTFChars(env, str2, 0);
    const char *str_str3 = (*env)->GetStringUTFChars(env, str3, 0);
    cpdf_replaceDictEntrySearch(pdf, str_str, str_str2, str_str3);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseStringUTFChars(env, str2, str_str2);
    (*env)->ReleaseStringUTFChars(env, str3, str_str3);
    checkerror(env);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_getDictEntries
  (JNIEnv * env, jobject jobj, jobject opdf, jstring str)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    int len = 0;
    void* memory = cpdf_getDictEntries(pdf, str_str, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeClipping
  (JNIEnv * env, jobject jobj, jobject opdf, jobject orange)
{
    int range = getRange(env, jobj, orange);
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeClipping(pdf, range);
    checkerror(env);
}
