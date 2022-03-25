#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "cpdflibwrapper.h"

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

void checkerror(JNIEnv *env)
{
  if (cpdf_lastError != 0)
  {
      cpdf_clearError();
      jclass cls = (*env)->FindClass(env, "com/coherentpdf/Jcpdf$CpdfError");
      if (cls != NULL) (*env)->ThrowNew(env, cls, cpdf_lastErrorString);
      (*env)->DeleteLocalRef(env, cls);
  }
}

/* CHAPTER 0. Preliminaries */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startup
  (JNIEnv * env, jobject jobj)
{
    char *argv[] = { "jcpdf", NULL };
    cpdf_startup(argv);
    checkerror(env);
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_version
  (JNIEnv * env, jobject jobj)
{
    jstring str = (*env)->NewStringUTF(env, cpdf_version());
    checkerror(env);
    return str;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setFast
  (JNIEnv * env, jobject jobj)
{
    cpdf_setFast();
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setSlow
  (JNIEnv * env, jobject jobj)
{
    cpdf_setSlow();
    checkerror(env);
}

/* CHAPTER 1. Basics */

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_fromFile
  (JNIEnv * env, jobject jobj, jstring filename, jstring userpw)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int pdf = cpdf_fromFile(str_filename, str_userpw);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    checkerror(env);
    return makePDF(env, jobj, pdf);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_fromFileLazy
  (JNIEnv * env, jobject jobj, jstring filename, jstring userpw)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int pdf = cpdf_fromFileLazy(str_filename, str_userpw);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    checkerror(env);
    return makePDF(env, jobj, pdf);
}

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

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_fromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jstring userpw)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int result = cpdf_fromMemory(memory, length, str_userpw);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    checkerror(env);
    return makePDF(env, jobj, result);
}

void* memory;

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_fromMemoryLazyRelease
  (JNIEnv *env, jobject jobj, jbyteArray data)
{
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_fromMemoryLazy
  (JNIEnv * env, jobject jobj, jbyteArray data, jstring userpw)
{
    int length = (*env)->GetArrayLength(env, data);
    memory = (*env)->GetByteArrayElements(env, data, 0); 
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    int result = cpdf_fromMemory(memory, length, str_userpw);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_deletePdf
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_deletePdf(pdf);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startEnumeratePDFs
  (JNIEnv * env, jobject jobj)
{
    int n = cpdf_startEnumeratePDFs();
    checkerror(env);
    return n;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_enumeratePDFsKey
  (JNIEnv * env, jobject jobj, jint n)
{
    int i = cpdf_enumeratePDFsKey(n);
    checkerror(env);
    return i;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_enumeratePDFsInfo
  (JNIEnv * env, jobject jobj, jint n)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_enumeratePDFsInfo(n));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endEnumeratePDFs
  (JNIEnv * env, jobject jobj, jint n)
{
    cpdf_endEnumeratePDFs();
    checkerror(env);
    return;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_ptOfCm
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfCm(f);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_ptOfMm
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfMm(f);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_ptOfIn
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_ptOfIn(f);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_inOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_inOfPt(f);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_cmOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_cmOfPt(f);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_mmOfPt
  (JNIEnv * env, jobject jobj, jdouble f)
{
    jdouble result = cpdf_mmOfPt(f);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_range
  (JNIEnv * env, jobject jobj, jint f, jint t)
{
    jint result = cpdf_range(f, t);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_all
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    jint result = cpdf_all(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_even
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_even(r);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_odd
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_odd(r);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeUnion
  (JNIEnv * env, jobject jobj, jint r, jint s)
{
    jint result = cpdf_rangeUnion(r, s);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_difference
  (JNIEnv * env, jobject jobj, jint r, jint s)
{
    jint result = cpdf_difference(r, s);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_removeDuplicates
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_removeDuplicates(r);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeLength
  (JNIEnv * env, jobject jobj, jint r)
{
    jint result = cpdf_rangeLength(r);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeGet
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jint result = cpdf_rangeGet(r, n);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_rangeAdd
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jint result = cpdf_rangeAdd(r, n);
    checkerror(env);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_isInRange
  (JNIEnv * env, jobject jobj, jint r, jint n)
{
    jboolean result = cpdf_isInRange(r, n);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_parsePagespec
  (JNIEnv * env, jobject jobj, jobject opdf, jstring pagespec)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jint result = cpdf_parsePagespec(pdf, str_pagespec);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_validatePagespec
  (JNIEnv * env, jobject jobj, jstring pagespec)
{
    const char *str_pagespec = (*env)->GetStringUTFChars(env, pagespec, 0);
    jboolean result = cpdf_validatePagespec(str_pagespec);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_stringOfPagespec
  (JNIEnv * env, jobject jobj, jobject opdf, jint r)
{
    int pdf = getPDF(env, jobj, opdf);
    jstring result = (*env)->NewStringUTF(env, cpdf_stringOfPagespec(pdf, r));
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_blankRange
  (JNIEnv * env, jobject jobj)
{
    jint result = cpdf_blankRange();
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_deleteRange
  (JNIEnv * env, jobject jobj, jint range)
{
    cpdf_deleteRange(range);
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_pages
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    jint result = cpdf_pages(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT jint JNICALL Java_com_coherentpdf_Jcpdf_pagesFast
  (JNIEnv * env, jobject jobj, jstring userpw, jstring filename)
{
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    jint result = cpdf_pagesFast(str_userpw, str_filename);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_toFile
  (JNIEnv * env, jobject jobj, jobject opdf, jstring filename, jboolean linearize, jboolean make_id)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_toFile(pdf, str_filename, linearize, make_id);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_toFileExt
  (JNIEnv * env, jobject jobj, jobject opdf, jstring filename, jboolean linearize, jboolean make_id, jboolean preserve_objstm, jboolean create_objstm, jboolean compress_objstm)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_toFileExt(pdf, str_filename, linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_toFileEncrypted
  (JNIEnv * env, jobject jobj, jobject opdf, jint encryption_method, jintArray data, jstring owner_password, jstring user_password, jboolean linearize, jboolean makeid, jstring filename)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_user_password = (*env)->GetStringUTFChars(env, user_password, 0);
    const char *str_owner_password = (*env)->GetStringUTFChars(env, owner_password, 0);
    int len = (*env)->GetArrayLength(env, data);
    int* perms = (*env)->GetIntArrayElements(env, data, 0); 
    cpdf_toFileEncrypted(pdf, encryption_method, perms, len, str_owner_password, str_user_password, linearize, makeid, str_filename);
    (*env)->ReleaseIntArrayElements(env, data, perms, 0);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, user_password, str_user_password);
    (*env)->ReleaseStringUTFChars(env, owner_password, str_owner_password);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_toFileEncryptedExt
  (JNIEnv * env, jobject jobj, jobject opdf, jint encryption_method, jintArray data, jstring owner_password, jstring user_password, jboolean linearize, jboolean makeid, jboolean preserve_objstm, jboolean generate_objstm, jboolean compress_objstm, jstring filename)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    const char *str_user_password = (*env)->GetStringUTFChars(env, user_password, 0);
    const char *str_owner_password = (*env)->GetStringUTFChars(env, owner_password, 0);
    int len = (*env)->GetArrayLength(env, data);
    int* perms = (*env)->GetIntArrayElements(env, data, 0); 
    cpdf_toFileEncryptedExt(pdf, encryption_method, perms, len, str_owner_password, str_user_password, linearize, makeid, preserve_objstm, generate_objstm, compress_objstm, str_filename);
    (*env)->ReleaseIntArrayElements(env, data, perms, 0);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    (*env)->ReleaseStringUTFChars(env, user_password, str_user_password);
    (*env)->ReleaseStringUTFChars(env, owner_password, str_owner_password);
    checkerror(env);
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_hasPermission
  (JNIEnv * env, jobject jobj, jobject opdf, jint permission)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_hasPermission(pdf, permission);
    checkerror(env);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_encryptionKind
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_encryptionKind(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_decryptPdf
  (JNIEnv * env, jobject jobj, jobject opdf, jstring userpw)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    cpdf_decryptPdf(pdf, str_userpw);
    (*env)->ReleaseStringUTFChars(env, userpw, str_userpw);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_decryptPdfOwner
  (JNIEnv * env, jobject jobj, jobject opdf, jstring ownerpw)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_ownerpw = (*env)->GetStringUTFChars(env, ownerpw, 0);
    cpdf_decryptPdf(pdf, str_ownerpw);
    (*env)->ReleaseStringUTFChars(env, ownerpw, str_ownerpw);
    checkerror(env);
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_isEncrypted
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    jint result = cpdf_isEncrypted(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_isLinearized
  (JNIEnv * env, jobject jobj, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    jboolean result = cpdf_isLinearized(str_filename);
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
  (JNIEnv * env, jobject jobj, jobjectArray data, jboolean retain_numbering, jboolean remove_duplicate_fonts, jintArray data2)
{
    int len = (*env)->GetArrayLength(env, data);
    int* perms = malloc(len * sizeof(int));
    for (int x = 0; x < len; x++)
    {
        perms[x] = getPDF(env, jobj, (*env)->GetObjectArrayElement(env, data, x));
    }
    int* ranges = (*env)->GetIntArrayElements(env, data2, 0);
    int result = cpdf_mergeSame(perms, len, retain_numbering, remove_duplicate_fonts, ranges);
    (*env)->ReleaseIntArrayElements(env, data2, ranges, 0);
    checkerror(env);
    return makePDF(env, jobj, result);
}

JNIEXPORT jobject JNICALL Java_com_coherentpdf_Jcpdf_selectPages
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    int result = cpdf_selectPages(pdf, range);
    checkerror(env);
    return makePDF(env, jobj, result);
}

/* CHAPTER 3. Pages */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scalePages
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jdouble sx, jdouble sy)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_scalePages(pdf, range, sx, sy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleToFit
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jdouble w, jdouble h, jdouble scale)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_scaleToFit(pdf, range, w, h, scale);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleToFitPaper
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jint papersize, jdouble scale)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_scaleToFitPaper(pdf, range, papersize, scale);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_shiftContents
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jdouble dx, jdouble dy)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_shiftContents(pdf, range, dx, dy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_scaleContents
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jint anchor, jdouble p1, jdouble p2, jdouble scale)
{
    int pdf = getPDF(env, jobj, opdf);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_scaleContents(pdf, range, p, scale);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_rotate
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jint angle)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_rotate(pdf, range, angle);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_rotateBy
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jint angle)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_rotateBy(pdf, range, angle);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_rotateContents
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jdouble angle)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_rotateContents(pdf, range, angle);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_upright
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_upright(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hFlip
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_hFlip(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_vFlip
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_vFlip(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_crop
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jdouble x, jdouble y, jdouble w, jdouble h)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_crop(pdf, range, x, y, w, h);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeCrop
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeCrop(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeTrim
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeTrim(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeArt
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeArt(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeBleed
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_removeBleed(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_trimMarks
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_trimMarks(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_showBoxes
  (JNIEnv * env, jobject jobj, jobject opdf, jint range)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_showBoxes(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hardBox
  (JNIEnv * env, jobject jobj, jobject opdf, jint range, jstring box)
{
    int pdf = getPDF(env, jobj, opdf);
    const char *str_box = (*env)->GetStringUTFChars(env, box, 0);
    cpdf_hardBox(pdf, range, str_box);
    checkerror(env);
}

/* CHAPTER 5. Compression */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_compress
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_compress(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_decompress
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_decompress(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_squeezeInMemory
  (JNIEnv * env, jobject jobj, jobject opdf)
{
    int pdf = getPDF(env, jobj, opdf);
    cpdf_squeezeInMemory(pdf);
    checkerror(env);
}

/* CHAPTER 6. Bookmarks */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint pdf)
{
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
  (JNIEnv * env, jobject jobj, jint pdf, jint serial)
{
    int result = cpdf_getBookmarkPage(pdf, serial);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getBookmarkText
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getBookmarkText(serial));
    checkerror(env);
    return result;
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
  (JNIEnv * env, jobject jobj, jint pdf, jint serial, jint pagenum)
{
    cpdf_setBookmarkPage(pdf, serial, pagenum);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarkOpenStatus
  (JNIEnv * env, jobject jobj, jint serial, jboolean open)
{
    cpdf_setBookmarkOpenStatus(serial, open);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarkText
  (JNIEnv * env, jobject jobj, jint serial, jstring text)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    cpdf_setBookmarkText(serial, str_text);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endSetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_endSetBookmarkInfo(pdf);
    checkerror(env);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_getBookmarksJSON
  (JNIEnv * env, jobject obj, jint pdf)
{
    int len = 0;
    void* memory = cpdf_getBookmarksJSON(pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBookmarksJSON
  (JNIEnv * env, jobject jobj, jint pdf, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    cpdf_setBookmarksJSON(pdf, memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_tableOfContents
  (JNIEnv * env, jobject jobj, jint pdf, jint font, jdouble fontsize, jstring title, jboolean bookmark)
{
    const char *str_title = (*env)->GetStringUTFChars(env, title, 0);
    cpdf_tableOfContents(pdf, font, fontsize, str_title, bookmark);
    (*env)->ReleaseStringUTFChars(env, title, str_title);
    checkerror(env);
}

/* CHAPTER 7. Presentations */

/* Not included in the library version */

/* CHAPTER 8. Logos, Watermarks and Stamps */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampOn
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2, jint range)
{
    cpdf_stampOn(pdf, pdf2, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampUnder
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2, jint range)
{
    cpdf_stampUnder(pdf, pdf2, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_stampExtended
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2, jint range, jboolean isover, jboolean scale_stamp_to_fit, jint anchor, jdouble p1, jdouble p2, jboolean relative_to_cropbox)
{
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_stampExtended(pdf, pdf2, range, isover, scale_stamp_to_fit, p, relative_to_cropbox);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_combinePages
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2)
{
    int result = cpdf_combinePages(pdf, pdf2);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_addText
  (JNIEnv * env, jobject jobj, jboolean metrics, jint pdf, jint range, jstring text, jint anchor, jdouble p1, jdouble p2, jdouble linespacing, jint bates, jint font, jdouble fontsize, jdouble r, jdouble g, jdouble b, jboolean underneath, jboolean cropbox, jboolean outline, jdouble opacity, jint justification, jboolean midline, jboolean topline, jstring filename, jdouble linewidth, jboolean embed_fonts)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_addText(metrics, pdf, range, str_text, p, linespacing, bates, font, fontsize, r, g, b, underneath, cropbox, outline, opacity, justification, midline, topline, str_filename, linewidth, embed_fonts);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

  JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_addTextSimple
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jstring text, jint anchor, jdouble p1, jdouble p2, jint font, jdouble fontsize)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    struct cpdf_position p = {.cpdf_anchor = anchor, .cpdf_coord1 = p1, .cpdf_coord2 = p2};
    cpdf_addTextSimple(pdf, range, str_text, p, font, fontsize);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeText
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeText(pdf, range);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_textWidth
  (JNIEnv * env, jobject jobj, jint font, jstring text)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    int w = cpdf_textWidth(font, str_text);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
    checkerror(env);
    return w;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_stampAsXObject
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jint stamp_pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_stampAsXObject(pdf, range, stamp_pdf));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_addContent
  (JNIEnv * env, jobject jobj, jstring str, jboolean before, jint pdf, jint range)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_addContent(str_str, before, pdf, range);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

/* CHAPTER 9. Multipage facilities */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_impose
  (JNIEnv * env, jobject jobj, jint pdf, jdouble x, jdouble y, jboolean fit, jboolean columns, jboolean rtl, jboolean btt, jboolean center, jdouble margin, jdouble spacing, jdouble linewidth)
{
    cpdf_impose(pdf, x, y, fit, columns, rtl, btt, center, margin, spacing, linewidth);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_twoUp
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_twoUp(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_twoUpStack
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_twoUpStack(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padBefore
  (JNIEnv * env, jobject jobj, jint pdf, int range)
{
    cpdf_padBefore(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padAfter
  (JNIEnv * env, jobject jobj, jint pdf, int range)
{
    cpdf_padAfter(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padEvery
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padEvery(pdf, n);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padMultiple
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padMultiple(pdf, n);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_padMultipleBefore
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padMultipleBefore(pdf, n);
    checkerror(env);
}

/* CHAPTER 10. Annotations */

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_annotationsJSON
  (JNIEnv * env, jobject obj, jobject opdf)
{
    int pdf = getPDF(env, obj, opdf);
    int len = 0;
    void* memory = cpdf_annotationsJSON(pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

/* CHAPTER 11. Document Information and Metadata */

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getVersion
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_getVersion(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getMajorVersion
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_getMajorVersion(pdf);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getTitle
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getTitle(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getAuthor
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getAuthor(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getSubject
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getSubject(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getKeywords
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getKeywords(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getCreator
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreator(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getProducer
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getProducer(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getCreationDate
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreationDate(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getModificationDate
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getModificationDate(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getTitleXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getTitleXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getAuthorXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getAuthorXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getSubjectXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getSubjectXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getKeywordsXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getKeywords(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getCreatorXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreatorXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getProducerXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getProducerXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getCreationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreationDateXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getModificationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getModificationDateXMP(pdf));
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setTitle
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setTitle(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setAuthor
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setAuthor(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setSubject
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setSubject(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setKeywords
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setKeywords(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setCreator
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreator(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setProducer
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setProducer(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setCreationDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreationDate(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setModificationDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setModificationDate(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setTitleXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setTitleXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setAuthorXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setAuthorXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setSubjectXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setSubjectXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setKeywordsXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setKeywordsXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setCreatorXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreatorXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setProducerXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setProducerXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setCreationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreationDateXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setModificationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setModificationDateXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

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
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber)
{
    int result = cpdf_getPageRotation(pdf, pagenumber);
    checkerror(env);
    return result;
}

JNIEXPORT jboolean JNICALL Java_com_coherentpdf_Jcpdf_hasBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jstring boxname)
{
    const char *str_boxname = (*env)->GetStringUTFChars(env, boxname, 0);
    int result = cpdf_hasBox(pdf, pagenumber, str_boxname);
    (*env)->ReleaseStringUTFChars(env, boxname, str_boxname);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getMediaBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jdoubleArray data)
{
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_getMediaBox(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getCropBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jdoubleArray data)
{
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_getCropBox(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getArtBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jdoubleArray data)
{
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_getArtBox(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getTrimBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jdoubleArray data)
{
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_getTrimBox(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_getBleedBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jdoubleArray data)
{
    double da, db, dc, dd;
    double* a = (*env)->GetDoubleArrayElements(env, data, 0); 
    cpdf_getBleedBox(pdf, pagenumber, &da, &db, &dc, &dd);
    a[0] = da;
    a[1] = db;
    a[2] = dc;
    a[3] = dd;
    (*env)->ReleaseDoubleArrayElements(env, data, a, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMediabox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setMediabox(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setCropBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setCropBox(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setTrimBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setTrimBox(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setArtBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setArtBox(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setBleedBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setBleedBox(pdf, range, minx, maxx, miny, maxy);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_markTrapped
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markTrapped(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_markUntrapped
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markUntrapped(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_markTrappedXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markTrappedXMP(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_markUntrappedXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markUntrappedXMP(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setPageLayout
  (JNIEnv * env, jobject jobj, jint pdf, jint layout)
{
    cpdf_setPageLayout(pdf, layout);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setPageMode
  (JNIEnv * env, jobject jobj, jint pdf, jint mode)
{
    cpdf_setPageMode(pdf, mode);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hideToolbar
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideToolbar(pdf, flag);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hideMenubar
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideMenubar(pdf, flag);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_hideWindowUi
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideWindowUi(pdf, flag);
    checkerror(env);
}
JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_fitWindow
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_fitWindow(pdf, flag);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_centerWindow
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_centerWindow(pdf, flag);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_displayDocTitle
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_displayDocTitle(pdf, flag);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_openAtPage
  (JNIEnv * env, jobject jobj, jint pdf, jboolean fit, jint pagenumber)
{
    cpdf_openAtPage(pdf, fit, pagenumber);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMetadataFromFile
  (JNIEnv * env, jobject jobj, jint pdf, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_setMetadataFromFile(pdf, str_filename);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_getMetadata
  (JNIEnv * env, jobject obj, jint pdf)
{
    int len = 0;
    void* memory = cpdf_getMetadata(pdf, &len);
    jbyteArray b = (*env)->NewByteArray(env, len);
    (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
    free(memory);
    checkerror(env);
    return b;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMetadataFromByteArray
  (JNIEnv * env, jobject jobj, jint pdf, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    signed char* memory = (*env)->GetByteArrayElements(env, data, 0); 
    cpdf_setMetadataFromByteArray(pdf, memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeMetadata
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeMetadata(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_createMetadata
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_createMetadata(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setMetadataDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring date)
{
    const char *str_date = (*env)->GetStringUTFChars(env, date, 0);
    cpdf_setMetadataDate(pdf, str_date);
    (*env)->ReleaseStringUTFChars(env, date, str_date);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_addPageLabels
  (JNIEnv * env, jobject jobj, jint pdf, jint style, jstring prefix, jint offset, jint range, jboolean progress)
{
    const char *str_prefix = (*env)->GetStringUTFChars(env, prefix, 0);
    cpdf_addPageLabels(pdf, style, str_prefix, offset, range, progress);
    (*env)->ReleaseStringUTFChars(env, prefix, str_prefix);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removePageLabels
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removePageLabels(pdf);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startGetPageLabels
  (JNIEnv * env, jobject jobj, jint pdf)
{
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

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getPageLabelPrefix
  (JNIEnv * env, jobject jobj, jint n)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getPageLabelPrefix(n));
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getPageLabelStringForPage
  (JNIEnv * env, jobject jobj, jint pdf, jint n)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getPageLabelStringForPage(pdf, n));
    checkerror(env);
    return result;
}

/* CHAPTER 12. File Attachments */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_attachFile
  (JNIEnv * env, jobject jobj, jstring filename, jint pdf)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFile(str_filename, pdf);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_attachFileToPage
  (JNIEnv * env, jobject jobj, jstring filename, jint pdf, jint pagenumber)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFileToPage(str_filename, pdf, pagenumber);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_attachFileFromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jstring filename, jint pdf)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFileFromMemory(memory, length, str_filename, pdf);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_attachFileToPageFromMemory
  (JNIEnv * env, jobject jobj, jbyteArray data, jstring filename, jint pdf, jint pagenumber)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFileToPageFromMemory(memory, length, str_filename, pdf, pagenumber);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeAttachedFiles
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeAttachedFiles(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetAttachments
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_startGetAttachments(pdf);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_numberGetAttachments
  (JNIEnv * env, jobject jobj, jint pdf)
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

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startGetImageResolution
  (JNIEnv * env, jobject jobj, jint pdf, jdouble res)
{
    int result = cpdf_startGetImageResolution(pdf, res);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionPageNumber
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getImageResolutionPageNumber(serial);
    checkerror(env);
    return result;
}

JNIEXPORT jstring JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionImageName
  (JNIEnv * env, jobject jobj, jint serial)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getImageResolutionImageName(serial));
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionXPixels
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getImageResolutionXPixels(serial);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionYPixels
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getImageResolutionYPixels(serial);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionXRes
  (JNIEnv * env, jobject jobj, jint serial)
{
    double result = cpdf_getImageResolutionXRes(serial);
    checkerror(env);
    return result;
}

JNIEXPORT jdouble JNICALL Java_com_coherentpdf_Jcpdf_getImageResolutionYRes
  (JNIEnv * env, jobject jobj, jint serial)
{
    double result = cpdf_getImageResolutionYRes(serial);
    checkerror(env);
    return result;
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_endGetImageResolution
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetImageResolution();
    checkerror(env);
}

/* CHAPTER 14. Fonts */


JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_startGetFontInfo
  (JNIEnv * env, jobject jobj, jint pdf)
{
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

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeFonts
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeFonts(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_copyFont
  (JNIEnv * env, jobject jobj, jint from_pdf, jint to_pdf, jint range, jint pagenumber, jstring fontname)
{
    const char *str_fontname = (*env)->GetStringUTFChars(env, fontname, 0);
    cpdf_copyFont(from_pdf, to_pdf, range, pagenumber, str_fontname);
    (*env)->ReleaseStringUTFChars(env, fontname, str_fontname);
    checkerror(env);
}

/* CHAPTER 15. PDF and JSON */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_outputJSON
  (JNIEnv * env, jobject jobj, jstring filename, jboolean parse_content, jboolean no_stream_data, jboolean decompress_streams, jint pdf)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_outputJSON(str_filename, parse_content, no_stream_data, decompress_streams, pdf);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_fromJSON
  (JNIEnv * env, jobject jobj, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    int result = cpdf_fromJSON(str_filename);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
    return result;
}

JNIEXPORT jbyteArray JNICALL Java_com_coherentpdf_Jcpdf_outputJSONMemory
  (JNIEnv * env, jobject obj, jint pdf, jboolean parse_content, jboolean no_stream_data, jboolean decompress_streams)
{
  int len = 0;
  void* memory = cpdf_outputJSONMemory(pdf, parse_content, no_stream_data, decompress_streams, &len);
  jbyteArray b = (*env)->NewByteArray(env, len);
  (*env)->SetByteArrayRegion(env, b, 0, len, memory); 
  free(memory);
  return b;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_fromJSONMemory
  (JNIEnv * env, jobject jobj, jbyteArray data)
{
    int length = (*env)->GetArrayLength(env, data);
    void* memory = (*env)->GetByteArrayElements(env, data, 0); 
    int result = cpdf_fromJSONMemory(memory, length);
    (*env)->ReleaseByteArrayElements(env, data, memory, 0);
    checkerror(env);
    return result;
}

/* CHAPTER 16. Optional Content Groups */

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_startGetOCGList
  (JNIEnv * env, jobject jobj, jint pdf)
{
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
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_OCGCoalesce(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_OCGRename
  (JNIEnv * env, jobject jobj, jint pdf, jstring f, jstring t)
{
    const char *str_f = (*env)->GetStringUTFChars(env, f, 0);
    const char *str_t = (*env)->GetStringUTFChars(env, t, 0);
    cpdf_OCGRename(pdf, str_f, str_t);
    (*env)->ReleaseStringUTFChars(env, f, str_f);
    (*env)->ReleaseStringUTFChars(env, t, str_t);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_OCGOrderAll
  (JNIEnv * env, jobject jobj, jint pdf)
{
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

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_textToPDF
  (JNIEnv * env, jobject jobj, jdouble w, jdouble h, jint font, jdouble fontsize, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    int result = cpdf_textToPDF(w, h, font, fontsize, str_filename);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
    return result;
}

JNIEXPORT int JNICALL Java_com_coherentpdf_Jcpdf_textToPDFPaper
  (JNIEnv * env, jobject jobj, jint papersize, jint font, jdouble fontsize, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    int result = cpdf_textToPDFPaper(papersize, font, fontsize, str_filename);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
    checkerror(env);
    return result;
}

/* CHAPTER 18. Miscellaneous */

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_draft
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jboolean boxes)
{
    cpdf_draft(pdf, range, boxes);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeAllText
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeAllText(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackText
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_blackText(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackLines
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_blackLines(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_blackFills
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_blackFills(pdf, range);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_thinLines
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minwidth)
{
    cpdf_thinLines(pdf, range, minwidth);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_copyId
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2)
{
    cpdf_copyId(pdf, pdf2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeId
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeId(pdf);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setVersion
  (JNIEnv * env, jobject jobj, jint pdf, jint version)
{
    cpdf_setVersion(pdf, version);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_setFullVersion
  (JNIEnv * env, jobject jobj, jint pdf, jint major, jint minor)
{
    cpdf_setFullVersion(pdf, major, minor);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeDictEntry
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_removeDictEntry(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_removeDictEntrySearch
  (JNIEnv * env, jobject jobj, jint pdf, jstring str, jstring str2)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    const char *str_str2 = (*env)->GetStringUTFChars(env, str2, 0);
    cpdf_removeDictEntrySearch(pdf, str_str, str_str2);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseStringUTFChars(env, str2, str_str2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_replaceDictEntry
  (JNIEnv * env, jobject jobj, jint pdf, jstring str, jstring str2)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    const char *str_str2 = (*env)->GetStringUTFChars(env, str2, 0);
    cpdf_replaceDictEntry(pdf, str_str, str_str2);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
    (*env)->ReleaseStringUTFChars(env, str2, str_str2);
    checkerror(env);
}

JNIEXPORT void JNICALL Java_com_coherentpdf_Jcpdf_replaceDictEntrySearch
  (JNIEnv * env, jobject jobj, jint pdf, jstring str, jstring str2, jstring str3)
{
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
  (JNIEnv * env, jobject obj, jint pdf, jstring str)
{
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
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeClipping(pdf, range);
    checkerror(env);
}
