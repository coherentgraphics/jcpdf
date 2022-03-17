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

JNIEXPORT jint JNICALL Java_Jcpdf_pages
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jint result = cpdf_pages(pdf);
    return result;
}

JNIEXPORT jint JNICALL Java_Jcpdf_pagesFast
  (JNIEnv * env, jobject jobj, jstring userpw, jstring filename)
{
    const char *str_userpw = (*env)->GetStringUTFChars(env, userpw, 0);
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    jint result = cpdf_pagesFast(str_userpw, str_filename);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_toFile
  (JNIEnv * env, jobject jobj, jint pdf, jstring filename, jboolean linearize, jboolean make_id)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_toFile(pdf, str_filename, linearize, make_id);
}

JNIEXPORT void JNICALL Java_Jcpdf_toFileExt
  (JNIEnv * env, jobject jobj, jint pdf, jstring filename, jboolean linearize, jboolean make_id, jboolean preserve_objstm, jboolean create_objstm, jboolean compress_objstm)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_toFileExt(pdf, str_filename, linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
}

JNIEXPORT jboolean JNICALL Java_Jcpdf_isEncrypted
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jint result = cpdf_isEncrypted(pdf);
    return result;
}

JNIEXPORT jboolean JNICALL Java_Jcpdf_isLinearized
  (JNIEnv * env, jobject jobj, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    jboolean result = cpdf_isLinearized(str_filename);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_scalePages
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble sx, jdouble sy)
{
    cpdf_scalePages(pdf, range, sx, sy);
}

JNIEXPORT void JNICALL Java_Jcpdf_scaleToFit
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble w, jdouble h, jdouble scale)
{
    cpdf_scaleToFit(pdf, range, w, h, scale);
}

JNIEXPORT void JNICALL Java_Jcpdf_scaleToFitPaper
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jint papersize, jdouble scale)
{
    cpdf_scaleToFitPaper(pdf, range, papersize, scale);
}

JNIEXPORT void JNICALL Java_Jcpdf_shiftContents
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble dx, jdouble dy)
{
    cpdf_shiftContents(pdf, range, dx, dy);
}

JNIEXPORT void JNICALL Java_Jcpdf_rotate
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jint angle)
{
    cpdf_rotate(pdf, range, angle);
}

JNIEXPORT void JNICALL Java_Jcpdf_rotateBy
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jint angle)
{
    cpdf_rotateBy(pdf, range, angle);
}

JNIEXPORT void JNICALL Java_Jcpdf_rotateContents
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble angle)
{
    cpdf_rotateContents(pdf, range, angle);
}

JNIEXPORT void JNICALL Java_Jcpdf_upright
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_upright(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_hFlip
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_hFlip(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_vFlip
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_vFlip(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_crop
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble x, jdouble y, jdouble w, jdouble h)
{
    cpdf_crop(pdf, range, x, y, w, h);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeCrop
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeCrop(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeTrim
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeTrim(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeArt
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeArt(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeBleed
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeBleed(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_trimMarks
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_trimMarks(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_showBoxes
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_showBoxes(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_hardBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jstring box)
{
    const char *str_box = (*env)->GetStringUTFChars(env, box, 0);
    cpdf_hardBox(pdf, range, str_box);
}

JNIEXPORT void JNICALL Java_Jcpdf_compress
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_compress(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_decompress
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_decompress(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_squeezeInMemory
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_squeezeInMemory(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_startGetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_startGetBookmarkInfo(pdf);
}

JNIEXPORT int JNICALL Java_Jcpdf_numberBookmarks
  (JNIEnv * env, jobject jobj)
{
    int result = cpdf_numberBookmarks();
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getBookmarkLevel
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getBookmarkLevel(serial);
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getBookmarkPage
  (JNIEnv * env, jobject jobj, jint pdf, jint serial)
{
    int result = cpdf_getBookmarkPage(pdf, serial);
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getBookmarkText
  (JNIEnv * env, jobject jobj, jint serial)
{
    return (*env)->NewStringUTF(env, cpdf_getBookmarkText(serial));
}

JNIEXPORT jboolean JNICALL Java_Jcpdf_getBookmarkOpenStatus
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getBookmarkOpenStatus(serial);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_endGetBookmarkInfo
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetBookmarkInfo();
}

JNIEXPORT void JNICALL Java_Jcpdf_startSetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint n)
{
    cpdf_startSetBookmarkInfo(n);
}

JNIEXPORT void JNICALL Java_Jcpdf_setBookmarkLevel
  (JNIEnv * env, jobject jobj, jint serial, jint level)
{
    cpdf_setBookmarkLevel(serial, level);
}

JNIEXPORT void JNICALL Java_Jcpdf_setBookmarkPage
  (JNIEnv * env, jobject jobj, jint pdf, jint serial, jint pagenum)
{
    cpdf_setBookmarkPage(pdf, serial, pagenum);
}

JNIEXPORT void JNICALL Java_Jcpdf_setBookmarkOpenStatus
  (JNIEnv * env, jobject jobj, jint serial, jboolean open)
{
    cpdf_setBookmarkOpenStatus(serial, open);
}

JNIEXPORT void JNICALL Java_Jcpdf_setBookmarkText
  (JNIEnv * env, jobject jobj, jint serial, jstring text)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    cpdf_setBookmarkText(serial, str_text);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
}

JNIEXPORT void JNICALL Java_Jcpdf_endSetBookmarkInfo
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_endSetBookmarkInfo(pdf);
}
