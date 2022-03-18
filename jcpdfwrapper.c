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

JNIEXPORT void JNICALL Java_Jcpdf_tableOfContents
  (JNIEnv * env, jobject jobj, jint pdf, jint font, jdouble fontsize, jstring title, jboolean bookmark)
{
    const char *str_title = (*env)->GetStringUTFChars(env, title, 0);
    cpdf_tableOfContents(pdf, font, fontsize, str_title, bookmark);
    (*env)->ReleaseStringUTFChars(env, title, str_title);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeText
  (JNIEnv * env, jobject jobj, jint pdf, jint range)
{
    cpdf_removeText(pdf, range);
}

JNIEXPORT int JNICALL Java_Jcpdf_textWidth
  (JNIEnv * env, jobject jobj, jint font, jstring text)
{
    const char *str_text = (*env)->GetStringUTFChars(env, text, 0);
    int w = cpdf_textWidth(font, str_text);
    (*env)->ReleaseStringUTFChars(env, text, str_text);
    return w;
}

JNIEXPORT void JNICALL Java_Jcpdf_stampOn
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2, jint range)
{
    cpdf_stampOn(pdf, pdf2, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_stampUnder
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2, jint range)
{
    cpdf_stampUnder(pdf, pdf2, range);
}

JNIEXPORT int JNICALL Java_Jcpdf_combinePages
  (JNIEnv * env, jobject jobj, jint pdf, jint pdf2)
{
    int result = cpdf_combinePages(pdf, pdf2);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_impose
  (JNIEnv * env, jobject jobj, jint pdf, jdouble x, jdouble y, jboolean fit, jboolean columns, jboolean rtl, jboolean btt, jboolean center, jdouble margin, jdouble spacing, jdouble linewidth)
{
    cpdf_impose(pdf, x, y, fit, columns, rtl, btt, center, margin, spacing, linewidth);
}

JNIEXPORT void JNICALL Java_Jcpdf_twoUp
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_twoUp(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_twoUpStack
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_twoUpStack(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_padBefore
  (JNIEnv * env, jobject jobj, jint pdf, int range)
{
    cpdf_padBefore(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_padAfter
  (JNIEnv * env, jobject jobj, jint pdf, int range)
{
    cpdf_padAfter(pdf, range);
}

JNIEXPORT void JNICALL Java_Jcpdf_padEvery
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padEvery(pdf, n);
}

JNIEXPORT void JNICALL Java_Jcpdf_padMultiple
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padMultiple(pdf, n);
}

JNIEXPORT void JNICALL Java_Jcpdf_padMultipleBefore
  (JNIEnv * env, jobject jobj, jint pdf, int n)
{
    cpdf_padMultipleBefore(pdf, n);
}

/* CHAPTER 11. Document Information and Metadata */

JNIEXPORT int JNICALL Java_Jcpdf_getVersion
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_getVersion(pdf);
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getMajorVersion
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_getMajorVersion(pdf);
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getTitle
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getTitle(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getAuthor
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getAuthor(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getSubject
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getSubject(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getKeywords
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getKeywords(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getCreator
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreator(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getProducer
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getProducer(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getCreationDate
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreationDate(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getModificationDate
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getModificationDate(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getTitleXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getTitleXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getAuthorXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getAuthorXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getSubjectXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getSubjectXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getKeywordsXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getKeywords(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getCreatorXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreatorXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getProducerXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getProducerXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getCreationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getCreationDateXMP(pdf));
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getModificationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    jstring result = (*env)->NewStringUTF(env, cpdf_getModificationDateXMP(pdf));
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_setTitle
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setTitle(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setAuthor
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setAuthor(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setSubject
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setSubject(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setKeywords
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setKeywords(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setCreator
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreator(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setProducer
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setProducer(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setCreationDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreationDate(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setModificationDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setModificationDate(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setTitleXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setTitleXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setAuthorXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setAuthorXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setSubjectXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setSubjectXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setKeywordsXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setKeywordsXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setCreatorXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreatorXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setProducerXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setProducerXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setCreationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setCreationDateXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT void JNICALL Java_Jcpdf_setModificationDateXMP
  (JNIEnv * env, jobject jobj, jint pdf, jstring str)
{
    const char *str_str = (*env)->GetStringUTFChars(env, str, 0);
    cpdf_setModificationDateXMP(pdf, str_str);
    (*env)->ReleaseStringUTFChars(env, str, str_str);
}

JNIEXPORT int JNICALL Java_Jcpdf_getPageRotation
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber)
{
    int result = cpdf_getPageRotation(pdf, pagenumber);
    return result;
}

JNIEXPORT jboolean JNICALL Java_Jcpdf_hasBox
  (JNIEnv * env, jobject jobj, jint pdf, jint pagenumber, jstring boxname)
{
    const char *str_boxname = (*env)->GetStringUTFChars(env, boxname, 0);
    int result = cpdf_hasBox(pdf, pagenumber, str_boxname);
    (*env)->ReleaseStringUTFChars(env, boxname, str_boxname);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_setMediabox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setMediabox(pdf, range, minx, maxx, miny, maxy);
}

JNIEXPORT void JNICALL Java_Jcpdf_setCropBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setCropBox(pdf, range, minx, maxx, miny, maxy);
}

JNIEXPORT void JNICALL Java_Jcpdf_setTrimBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setTrimBox(pdf, range, minx, maxx, miny, maxy);
}

JNIEXPORT void JNICALL Java_Jcpdf_setArtBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setArtBox(pdf, range, minx, maxx, miny, maxy);
}

JNIEXPORT void JNICALL Java_Jcpdf_setBleedBox
  (JNIEnv * env, jobject jobj, jint pdf, jint range, jdouble minx, jdouble maxx, jdouble miny, jdouble maxy)
{
    cpdf_setBleedBox(pdf, range, minx, maxx, miny, maxy);
}

JNIEXPORT void JNICALL Java_Jcpdf_markTrapped
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markTrapped(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_markUntrapped
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markUntrapped(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_markTrappedXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markTrappedXMP(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_markUntrappedXMP
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_markUntrappedXMP(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_setPageLayout
  (JNIEnv * env, jobject jobj, jint pdf, jint layout)
{
    cpdf_setPageLayout(pdf, layout);
}

JNIEXPORT void JNICALL Java_Jcpdf_setPageMode
  (JNIEnv * env, jobject jobj, jint pdf, jint mode)
{
    cpdf_setPageMode(pdf, mode);
}

JNIEXPORT void JNICALL Java_Jcpdf_hideToolbar
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideToolbar(pdf, flag);
}

JNIEXPORT void JNICALL Java_Jcpdf_hideMenubar
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideMenubar(pdf, flag);
}

JNIEXPORT void JNICALL Java_Jcpdf_hideWindowUi
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_hideWindowUi(pdf, flag);
}
JNIEXPORT void JNICALL Java_Jcpdf_fitWindow
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_fitWindow(pdf, flag);
}

JNIEXPORT void JNICALL Java_Jcpdf_centerWindow
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_centerWindow(pdf, flag);
}

JNIEXPORT void JNICALL Java_Jcpdf_displayDocTitle
  (JNIEnv * env, jobject jobj, jint pdf, jboolean flag)
{
    cpdf_displayDocTitle(pdf, flag);
}

JNIEXPORT void JNICALL Java_Jcpdf_openAtPage
  (JNIEnv * env, jobject jobj, jint pdf, jboolean fit, jint pagenumber)
{
    cpdf_openAtPage(pdf, fit, pagenumber);
}

JNIEXPORT void JNICALL Java_Jcpdf_setMetadataFromFile
  (JNIEnv * env, jobject jobj, jint pdf, jstring filename)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_setMetadataFromFile(pdf, str_filename);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeMetadata
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeMetadata(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_createMetadata
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_createMetadata(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_setMetadataDate
  (JNIEnv * env, jobject jobj, jint pdf, jstring date)
{
    const char *str_date = (*env)->GetStringUTFChars(env, date, 0);
    cpdf_setMetadataDate(pdf, str_date);
    (*env)->ReleaseStringUTFChars(env, date, str_date);
}

JNIEXPORT void JNICALL Java_Jcpdf_addPageLabels
  (JNIEnv * env, jobject jobj, jint pdf, jint style, jstring prefix, jint offset, jint range, jboolean progress)
{
    const char *str_prefix = (*env)->GetStringUTFChars(env, prefix, 0);
    cpdf_addPageLabels(pdf, style, str_prefix, offset, range, progress);
    (*env)->ReleaseStringUTFChars(env, prefix, str_prefix);
}

JNIEXPORT void JNICALL Java_Jcpdf_removePageLabels
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removePageLabels(pdf);
}

JNIEXPORT int JNICALL Java_Jcpdf_startGetPageLabels
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_startGetPageLabels(pdf);
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getPageLabelOffset
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelOffset(n);
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getPageLabelStyle
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelStyle(n);
    return result;
}

JNIEXPORT int JNICALL Java_Jcpdf_getPageLabelRange
  (JNIEnv * env, jobject jobj, jint n)
{
    int result = cpdf_getPageLabelRange(n);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_endGetPageLabels
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetPageLabels();
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getPageLabelPrefix
  (JNIEnv * env, jobject jobj, jint n)
{
    return (*env)->NewStringUTF(env, cpdf_getPageLabelPrefix(n));
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getPageLabelStringForPage
  (JNIEnv * env, jobject jobj, jint pdf, jint n)
{
    return (*env)->NewStringUTF(env, cpdf_getPageLabelStringForPage(pdf, n));
}

JNIEXPORT void JNICALL Java_Jcpdf_attachFile
  (JNIEnv * env, jobject jobj, jstring filename, jint pdf)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFile(str_filename, pdf);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
}

JNIEXPORT void JNICALL Java_Jcpdf_attachFileToPage
  (JNIEnv * env, jobject jobj, jstring filename, jint pdf, jint pagenumber)
{
    const char *str_filename = (*env)->GetStringUTFChars(env, filename, 0);
    cpdf_attachFileToPage(str_filename, pdf, pagenumber);
    (*env)->ReleaseStringUTFChars(env, filename, str_filename);
}

JNIEXPORT void JNICALL Java_Jcpdf_removeAttachedFiles
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_removeAttachedFiles(pdf);
}

JNIEXPORT void JNICALL Java_Jcpdf_startGetAttachments
  (JNIEnv * env, jobject jobj, jint pdf)
{
    cpdf_startGetAttachments(pdf);
}

JNIEXPORT int JNICALL Java_Jcpdf_numberGetAttachments
  (JNIEnv * env, jobject jobj, jint pdf)
{
    int result = cpdf_numberGetAttachments();
    return result;
}

JNIEXPORT jstring JNICALL Java_Jcpdf_getAttachmentName
  (JNIEnv * env, jobject jobj, jint serial)
{
    return (*env)->NewStringUTF(env, cpdf_getAttachmentName(serial));
}

JNIEXPORT int JNICALL Java_Jcpdf_getAttachmentPage
  (JNIEnv * env, jobject jobj, jint serial)
{
    int result = cpdf_getAttachmentPage(serial);
    return result;
}

JNIEXPORT void JNICALL Java_Jcpdf_endGetAttachments
  (JNIEnv * env, jobject jobj)
{
    cpdf_endGetAttachments();
}

