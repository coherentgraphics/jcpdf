package com.coherentpdf;
import java.nio.charset.Charset;

public class Jcpdf {
    //Initalize by loading external DLLs
    public Jcpdf()
    {
      System.loadLibrary("cpdf");
      System.loadLibrary("jcpdf");
    }
    
    //Exceptions
    public static class CpdfError extends Exception
    {
      public CpdfError(String errorMessage)
      {
        super(errorMessage);
      }
    }

    //PDFs backed by Cpdflib PDFs.
    public class Pdf implements AutoCloseable
    {
      int pdf = -1;
      public Pdf(int pdf)
      {
        this.pdf = pdf;
      }
      public void close()
      {
        deletePdf(pdf);
      }
    }

    //Ranges backed by Cpdflib ranges
    public class Range implements AutoCloseable
    {
      int range = -1;
      public Range(int range)
      {
        this.range = range;
      }
      public void close()
      {
        deleteRange(range);
      }
    }

    // Work around JNI's horrible "modified UTF-8" by passing and receiving
    // Strings, where needed, as byte arrays in UTF8.
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    String decodeUTF8(byte[] bytes) { return new String(bytes, UTF8_CHARSET); }
    byte[] encodeUTF8(String string) { return string.getBytes(UTF8_CHARSET); }

    //Enumerations
    public int noEdit = 0;
    public int noPrint = 1;
    public int noCopy = 2;
    public int noAnnot = 3;
    public int noForms = 4;
    public int noExtract = 5;
    public int noAssemble = 6;
    public int noHqPrint = 7;

    public int pdf40bit = 0;
    public int pdf128bit = 1;
    public int aes128bitfalse = 2;
    public int aes128bittrue = 3;
    public int aes256bitfalse = 4;
    public int aes256bittrue = 5;
    public int aes256bitisofalse = 6;
    public int aes256bitiso = 7;

    public int decimalArabic = 0;
    public int uppercaseRoman = 1;
    public int lowercaseRoman = 2;
    public int uppercaseLetters = 3;
    public int lowercaseLetters = 4;

    public int singlePage = 0;
    public int oneColumn = 1;
    public int twoColumnLeft = 2;
    public int twoColumnRight = 3;
    public int twoPageLeft = 4;
    public int twoPageRight = 5;

    public int useNone = 0;
    public int useOutlines = 1;
    public int useThumbs = 2;
    public int useOC = 3;
    public int useAttachments = 4;

    public int a0portrait = 0;
    public int a1portrait = 1;
    public int a2portrait = 2;
    public int a3portrait = 3;
    public int a4portrait = 4;
    public int a5portrait = 5;
    public int a0landscape = 6;
    public int a1landscape = 7;
    public int a2landscape = 8;
    public int a3landscape = 9;
    public int a4landscape = 10;
    public int a5landscape = 11;
    public int usletterportrait = 12;
    public int usletterlandscape = 13;
    public int uslegalportrait = 14;
    public int uslegallandscape = 15;

    public int timesRoman = 0;
    public int timesBold = 1;
    public int timesItalic = 2;
    public int timesBoldItalic = 3;
    public int helvetica = 4;
    public int helveticaBold = 5;
    public int helveticaOblique = 6;
    public int helveticaBoldOblique = 7;
    public int courier = 8;
    public int courierBold = 9;
    public int courierOblique = 10;
    public int courierBoldOblique = 11;

    public int posCentre = 0;
    public int posLeft = 1;
    public int posRight = 2;
    public int top = 3;
    public int topLeft = 4;
    public int topRight = 5;
    public int left = 6;
    public int bottomLeft = 7;
    public int bottom = 8;
    public int bottomRight = 9;
    public int right = 10;
    public int diagonal = 11;
    public int reverseDiagonal = 12;

    public int leftJustify = 0;
    public int centreJustify = 1;
    public int rightJusitfy = 2;

    /* CHAPTER 0. Preliminaries */
    public native void onExit();
    public native void deletePdf(int pdf);
    public native void deleteRange(int range);
    public native void startup() throws CpdfError;
    public native String version() throws CpdfError;
    public native void setFast() throws CpdfError;
    public native void setSlow() throws CpdfError;

    /* CHAPTER 1. Basics */
    native Pdf XfromFile(byte[] filename, byte[] userpw) throws CpdfError;
    public Pdf fromFile(String filename, String userpw) throws CpdfError
    { 
      return XfromFile(encodeUTF8(filename), encodeUTF8(userpw));
    }
    native Pdf XfromFileLazy(byte[] filename, byte[] userpw) throws CpdfError;
    public Pdf fromFileLazy(String filename, String userpw) throws CpdfError
    {
      return XfromFileLazy(encodeUTF8(filename), encodeUTF8(userpw));
    }
    public native byte[] toMemory(Pdf pdf, boolean linearize, boolean make_id) throws CpdfError;
    native Pdf XfromMemory(byte[] data, byte[] userpw) throws CpdfError;
    public Pdf fromMemory(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemory(data, encodeUTF8(userpw));
    }
    public native void fromMemoryLazyRelease(byte[] data) throws CpdfError;
    native Pdf XfromMemoryLazy(byte[] data, byte[] userpw) throws CpdfError;
    public Pdf fromMemoryLazy(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemoryLazy(data, encodeUTF8(userpw));
    }
    public native int startEnumeratePDFs() throws CpdfError;
    public native int enumeratePDFsKey(int n) throws CpdfError;
    public native String enumeratePDFsInfo(int n) throws CpdfError;
    public native void endEnumeratePDFs() throws CpdfError;
    public native double ptOfCm(double f) throws CpdfError;
    public native double ptOfMm(double f) throws CpdfError;
    public native double ptOfIn(double f) throws CpdfError;
    public native double cmOfPt(double f) throws CpdfError;
    public native double mmOfPt(double f) throws CpdfError;
    public native double inOfPt(double f) throws CpdfError;
    public native Range range(int from, int to) throws CpdfError;
    public native Range all(Pdf pdf) throws CpdfError;
    public native Range odd(Range r) throws CpdfError;
    public native Range even(Range r) throws CpdfError;
    public native Range rangeUnion(Range r, Range s) throws CpdfError;
    public native Range difference(Range r, Range s) throws CpdfError;
    public native Range removeDuplicates(Range r) throws CpdfError;
    public native int rangeLength(Range r) throws CpdfError;
    public native int rangeGet(Range r, int n) throws CpdfError;
    public native Range rangeAdd(Range r, int n) throws CpdfError;
    public native boolean isInRange(Range r, int n) throws CpdfError;
    public native Range parsePagespec(Pdf pdf, String pagespec) throws CpdfError;
    public native boolean validatePagespec(String pagespec) throws CpdfError;
    native byte[] XstringOfPagespec(Pdf pdf, Range r) throws CpdfError;
    public String stringOfPagespec(Pdf pdf, Range r) throws CpdfError
    {
        return decodeUTF8(XstringOfPagespec(pdf, r));    
    }
    public native Range blankRange() throws CpdfError;
    public native int pages(Pdf pdf) throws CpdfError;
    native int XpagesFast(byte[] userpw, byte[] filename) throws CpdfError;
    public int pagesFast(String userpw, String filename) throws CpdfError
    {
        return XpagesFast(encodeUTF8(userpw), encodeUTF8(filename));
    }
    native void XtoFile(Pdf pdf, byte[] filename, boolean linearize, boolean make_id) throws CpdfError;
    public void toFile(Pdf pdf, String filename, boolean linearize, boolean make_id) throws CpdfError
    {
        XtoFile(pdf, encodeUTF8(filename), linearize, make_id);
    }
    native void XtoFileExt(Pdf pdf, byte[] filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError;
    public void toFileExt(Pdf pdf, String filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError
    {
        XtoFileExt(pdf, encodeUTF8(filename), linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
    }
    public native boolean isEncrypted(Pdf pdf) throws CpdfError;
    native boolean XisLinearized(byte[] filename) throws CpdfError;
    public boolean isLinearized(String filename) throws CpdfError
    {
        return XisLinearized(encodeUTF8(filename));
    }
    native int XtoFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, byte[] filename) throws CpdfError;
    public int toFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, String filename) throws CpdfError
    {
        return XtoFileEncrypted(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, encodeUTF8(filename));
    }
    native int XtoFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, byte[] filename) throws CpdfError;
    public int toFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, String filename) throws CpdfError
    {
        return XtoFileEncryptedExt(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, preserve_objstm, generate_objstm, compress_objstm, encodeUTF8(filename));
    }
    public native boolean hasPermission(Pdf pdf, int permission) throws CpdfError;
    public native int encryptionKind(Pdf pdf) throws CpdfError;
    native void XdecryptPdf(Pdf pdf, byte[] userpw) throws CpdfError;
    public void decryptPdf(Pdf pdf, String userpw) throws CpdfError
    {
        XdecryptPdf(pdf, encodeUTF8(userpw));
    }
    native void XdecryptPdfOwner(Pdf pdf, byte[] ownerpw) throws CpdfError;
    public void decryptPdfOwner(Pdf pdf, String ownerpw) throws CpdfError
    {
        XdecryptPdfOwner(pdf, encodeUTF8(ownerpw));
    }

    /* CHAPTER 2. Merging and Splitting */
    public native Pdf mergeSimple(Pdf[] pdfs) throws CpdfError;
    public native Pdf merge(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts) throws CpdfError;
    public native Pdf mergeSame(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts, Range[] ranges) throws CpdfError;
    public native Pdf selectPages(Pdf pdf, Range range) throws CpdfError;

    /* CHAPTER 3. Pages */
    public native void scalePages(Pdf pdf, Range range, double sx, double sy) throws CpdfError;
    public native void scaleToFit(Pdf pdf, Range range, double w, double h, double scale) throws CpdfError;
    public native void scaleToFitPaper(Pdf pdf, Range range, int papersize, double scale) throws CpdfError;
    public native void scaleContents(Pdf pdf, Range range, int anchor, double p1, double p2, double scale) throws CpdfError;
    public native void shiftContents(Pdf pdf, Range range, double dx, double dy) throws CpdfError;
    public native void rotate(Pdf pdf, Range range, int angle) throws CpdfError;
    public native void rotateBy(Pdf pdf, Range range, int angle) throws CpdfError;
    public native void rotateContents(Pdf pdf, Range range, double angle) throws CpdfError;
    public native void upright(Pdf pdf, Range range) throws CpdfError;
    public native void hFlip(Pdf pdf, Range range) throws CpdfError;
    public native void vFlip(Pdf pdf, Range range) throws CpdfError;
    public native void crop(Pdf pdf, Range range, double x, double y, double w, double h) throws CpdfError;
    public native void removeCrop(Pdf pdf, Range range) throws CpdfError;
    public native void removeTrim(Pdf pdf, Range range) throws CpdfError;
    public native void removeArt(Pdf pdf, Range range) throws CpdfError;
    public native void removeBleed(Pdf pdf, Range range) throws CpdfError;
    public native void trimMarks(Pdf pdf, Range range) throws CpdfError;
    public native void showBoxes(Pdf pdf, Range range) throws CpdfError;
    public native void hardBox(Pdf pdf, Range range, String box) throws CpdfError;

    /* CHAPTER 4. Encryption */
    /* Encryption covered under Chapter 1 in cpdflib. */

    /* CHAPTER 5. Compression */
    public native void compress(Pdf pdf) throws CpdfError;
    public native void decompress(Pdf pdf) throws CpdfError;
    public native void squeezeInMemory(Pdf pdf) throws CpdfError;
    
    /* CHAPTER 6. Bookmarks */
    public native void startGetBookmarkInfo(Pdf pdf) throws CpdfError;
    public native int numberBookmarks() throws CpdfError;
    public native int getBookmarkLevel(int serial) throws CpdfError;
    public native int getBookmarkPage(Pdf pdf, int serial) throws CpdfError;
    native byte[] XgetBookmarkText(int serial) throws CpdfError;
    public String getBookmarkText(int serial) throws CpdfError
    {
        return decodeUTF8(XgetBookmarkText(serial));
    }
    public native boolean getBookmarkOpenStatus(int serial) throws CpdfError;
    public native void endGetBookmarkInfo() throws CpdfError;
    public native void startSetBookmarkInfo(int n) throws CpdfError;
    public native void setBookmarkLevel(int serial, int level) throws CpdfError;
    public native void setBookmarkPage(Pdf pdf, int serial, int pagenum) throws CpdfError;
    public native void setBookmarkOpenStatus(int serial, boolean open) throws CpdfError;
    native void XsetBookmarkText(int serial, byte[] text) throws CpdfError;
    public void setBookmarkText(int serial, String text) throws CpdfError
    {
        XsetBookmarkText(serial, encodeUTF8(text));
    }
    public native void endSetBookmarkInfo(Pdf pdf) throws CpdfError;
    public native byte[] getBookmarksJSON(Pdf pdf) throws CpdfError;
    public native void setBookmarksJSON(Pdf pdf, byte[] data) throws CpdfError;
    native void XtableOfContents(Pdf pdf, int font, double fontsize, byte[] title, boolean bookmark) throws CpdfError;
    public void tableOfContents(Pdf pdf, int font, double fontsize, String title, boolean bookmark) throws CpdfError
    {
        XtableOfContents(pdf, font, fontsize, encodeUTF8(title), bookmark);
    }

    /* CHAPTER 7. Presentations */
    /* Not included in the library version. */

    /* CHAPTER 8. Logos, Watermarks and Stamps */
    native void XaddText(boolean metrics, Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, byte[] filename, double linewidth, boolean embed_fonts) throws CpdfError;
    public void addText(boolean metrics, Pdf pdf, Range range, String text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, String filename, double linewidth, boolean embed_fonts) throws CpdfError
    {
        XaddText(metrics, pdf, range, encodeUTF8(text), anchor, p1, p2, linespacing, bates, font, fontsize,
                r, g, b, underneath, cropbox, outline, opacity, justification, midline, topline, encodeUTF8(filename), linewidth, embed_fonts);
    }
    native void XaddTextSimple(Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError;
    public void addTextSimple(Pdf pdf, Range range, String text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError
    {
        XaddTextSimple(pdf, range, encodeUTF8(text), anchor, p1, p2, font, fontsize);
    }
    public native void removeText(Pdf pdf, Range range) throws CpdfError;
    native int XtextWidth(int font, byte[] text) throws CpdfError;
    public int textWidth(int font, String test) throws CpdfError
    {
        return XtextWidth(font, encodeUTF8(test));
    }
    public native void stampOn(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;
    public native void stampUnder(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;
    public native void stampExtended(Pdf pdf, Pdf pdf2, Range range, boolean isover, boolean scale_stamp_to_fit, int anchor, double p1, double p2, boolean relative_to_cropbox) throws CpdfError;
    public native Pdf combinePages(Pdf pdf, Pdf pdf2) throws CpdfError;
    public native String stampAsXObject(Pdf pdf, Range range, Pdf stamp_pdf) throws CpdfError;
    native void XaddContent(byte[] s, boolean before, Pdf pdf, Range range) throws CpdfError;
    public void addContent(String s, boolean before, Pdf pdf, Range range) throws CpdfError
    {
        XaddContent(encodeUTF8(s), before, pdf, range);
    }
    
    /* CHAPTER 9. Multipage facilities */
    public native void impose(Pdf pdf, double x, double y, boolean fit, boolean columns, boolean rtl, boolean btt, boolean center, double margin, double spacing, double linewidth) throws CpdfError;
    public native void twoUp(Pdf pdf) throws CpdfError;
    public native void twoUpStack(Pdf pdf) throws CpdfError;
    public native void padBefore(Pdf pdf, Range range) throws CpdfError;
    public native void padAfter(Pdf pdf, Range range) throws CpdfError;
    public native void padEvery(Pdf pdf, int n) throws CpdfError;
    public native void padMultiple(Pdf pdf, int n) throws CpdfError;
    public native void padMultipleBefore(Pdf pdf, int n) throws CpdfError;

    /* CHAPTER 10. Annotations */
    public native byte[] annotationsJSON(Pdf pdf) throws CpdfError;
    
    /* CHAPTER 11. Document Information and Metadata */
    public native int getVersion(Pdf pdf) throws CpdfError;
    public native int getMajorVersion(Pdf pdf) throws CpdfError;
    native byte[] XgetTitle(Pdf pdf) throws CpdfError;
    native byte[] XgetAuthor(Pdf pdf) throws CpdfError;
    native byte[] XgetSubject(Pdf pdf) throws CpdfError;
    native byte[] XgetKeywords(Pdf pdf) throws CpdfError;
    native byte[] XgetCreator(Pdf pdf) throws CpdfError;
    native byte[] XgetProducer(Pdf pdf) throws CpdfError;
    native byte[] XgetCreationDate(Pdf pdf) throws CpdfError;
    native byte[] XgetModificationDate(Pdf pdf) throws CpdfError;
    native byte[] XgetTitleXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetAuthorXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetSubjectXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetKeywordsXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetCreatorXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetProducerXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetCreationDateXMP(Pdf pdf) throws CpdfError;
    native byte[] XgetModificationDateXMP(Pdf pdf) throws CpdfError;
    public String getTitle(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetTitle(pdf)); 
    }
    public String getAuthor(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetAuthor(pdf)); 
    }
    public String getSubject(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetSubject(pdf)); 
    }
    public String getKeywords(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetKeywords(pdf)); 
    }
    public String getCreator(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreator(pdf)); 
    }
    public String getProducer(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetProducer(pdf)); 
    }
    public String getCreationDate(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreationDate(pdf)); 
    }
    public String getModificationDate(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetModificationDate(pdf)); 
    }
    public String getTitleXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetTitleXMP(pdf)); 
    }
    public String getAuthorXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetAuthorXMP(pdf)); 
    }
    public String getSubjectXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetSubjectXMP(pdf)); 
    }
    public String getKeywordsXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetKeywordsXMP(pdf)); 
    }
    public String getCreatorXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreatorXMP(pdf)); 
    }
    public String getProducerXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetProducerXMP(pdf)); 
    }
    public String getCreationDateXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreationDateXMP(pdf)); 
    }
    public String getModificationDateXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetModificationDateXMP(pdf)); 
    }
    native void XsetTitle(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetAuthor(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetSubject(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetKeywords(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetCreator(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetProducer(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetCreationDate(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetModificationDate(Pdf pdf, byte[] str) throws CpdfError;
    public void setTitle(Pdf pdf, String str) throws CpdfError
    {
        XsetTitle(pdf, encodeUTF8(str));
    }
    public void setAuthor(Pdf pdf, String str) throws CpdfError
    {
        XsetAuthor(pdf, encodeUTF8(str));
    }
    public void setSubject(Pdf pdf, String str) throws CpdfError
    {
        XsetSubject(pdf, encodeUTF8(str));
    }
    public void setKeywords(Pdf pdf, String str) throws CpdfError
    {
        XsetKeywords(pdf, encodeUTF8(str));
    }
    public void setCreator(Pdf pdf, String str) throws CpdfError
    {
        XsetCreator(pdf, encodeUTF8(str));
    }
    public void setProducer(Pdf pdf, String str) throws CpdfError
    {
        XsetProducer(pdf, encodeUTF8(str));
    }
    public void setCreationDate(Pdf pdf, String str) throws CpdfError
    {
        XsetCreationDate(pdf, encodeUTF8(str));
    }
    public void setModificationDate(Pdf pdf, String str) throws CpdfError
    {
        XsetModificationDate(pdf, encodeUTF8(str));
    }
    native void XsetTitleXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetAuthorXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetSubjectXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetKeywordsXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetCreatorXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetProducerXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetCreationDateXMP(Pdf pdf, byte[] str) throws CpdfError;
    native void XsetModificationDateXMP(Pdf pdf, byte[] str) throws CpdfError;
    public void setTitleXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetTitleXMP(pdf, encodeUTF8(str));
    }
    public void setAuthorXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetAuthorXMP(pdf, encodeUTF8(str));
    }
    public void setSubjectXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetSubjectXMP(pdf, encodeUTF8(str));
    }
    public void setKeywordsXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetKeywordsXMP(pdf, encodeUTF8(str));
    }
    public void setCreatorXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetCreatorXMP(pdf, encodeUTF8(str));
    }
    public void setProducerXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetProducerXMP(pdf, encodeUTF8(str));
    }
    public void setCreationDateXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetCreationDateXMP(pdf, encodeUTF8(str));
    }
    public void setModificationDateXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetModificationDateXMP(pdf, encodeUTF8(str));
    }
    public native void getDateComponents(String datestring, int[] r) throws CpdfError;
    public native String dateStringOfComponents(int year, int month, int day, int hour, int minute, int second, int hour_offset, int minute_offset) throws CpdfError;
    public native void getMediaBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    public native void getCropBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    public native void getBleedBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    public native void getArtBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    public native void getTrimBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    public native int getPageRotation(Pdf pdf, int pagenumber) throws CpdfError;
    public native boolean hasBox(Pdf pdf, int pagenumber, String boxname) throws CpdfError;
    public native void setMediabox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    public native void setCropBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    public native void setTrimBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    public native void setArtBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    public native void setBleedBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    public native void markTrapped(Pdf pdf) throws CpdfError;
    public native void markUntrapped(Pdf pdf) throws CpdfError;
    public native void markTrappedXMP(Pdf pdf) throws CpdfError;
    public native void markUntrappedXMP(Pdf pdf) throws CpdfError;
    public native void setPageLayout(Pdf pdf, int layout) throws CpdfError;
    public native void setPageMode(Pdf pdf, int mode) throws CpdfError;
    public native void hideToolbar(Pdf pdf, boolean flag) throws CpdfError;
    public native void hideMenubar(Pdf pdf, boolean flag) throws CpdfError;
    public native void hideWindowUi(Pdf pdf, boolean flag) throws CpdfError;
    public native void fitWindow(Pdf pdf, boolean flag) throws CpdfError;
    public native void centerWindow(Pdf pdf, boolean flag) throws CpdfError;
    public native void displayDocTitle(Pdf pdf, boolean flag) throws CpdfError;
    public native void openAtPage(Pdf pdf, boolean fit, int pagenumber) throws CpdfError;
    native void XsetMetadataFromFile(Pdf pdf, byte[] filename) throws CpdfError;
    public void setMetadataFromFile(Pdf pdf, String filename) throws CpdfError
    {
        XsetMetadataFromFile(pdf, encodeUTF8(filename));
    }
    public native void setMetadataFromByteArray(Pdf pdf, byte[] data) throws CpdfError;
    public native byte[] getMetadata(Pdf pdf) throws CpdfError;
    public native void removeMetadata(Pdf pdf) throws CpdfError;
    public native void createMetadata(Pdf pdf) throws CpdfError;
    public native void setMetadataDate(Pdf pdf, String date) throws CpdfError;
    native void XaddPageLabels(Pdf pdf, int style, byte[] prefix, int offset, Range range, boolean progress) throws CpdfError;
    public void addPageLabels(Pdf pdf, int style, String prefix, int offset, Range range, boolean progress) throws CpdfError
    {
        XaddPageLabels(pdf, style, encodeUTF8(prefix), offset, range, progress);
    }
    public native void removePageLabels(Pdf pdf) throws CpdfError;
    public native int startGetPageLabels(Pdf pdf) throws CpdfError;
    public native void endGetPageLabels() throws CpdfError;
    public native int getPageLabelOffset(int n) throws CpdfError;
    public native int getPageLabelStyle(int n) throws CpdfError;
    public native int getPageLabelRange(int n) throws CpdfError;
    native byte[] XgetPageLabelPrefix(int n) throws CpdfError;
    public String getPageLabelPrefix(int n) throws CpdfError
    {
        return decodeUTF8(XgetPageLabelPrefix(n));
    }
    native byte[] XgetPageLabelStringForPage(Pdf pdf, int n) throws CpdfError;
    public String getPageLabelStringForPage(Pdf pdf, int n) throws CpdfError
    {
        return decodeUTF8(XgetPageLabelStringForPage(pdf, n));
    }
    
    /* CHAPTER 12. File Attachments */
    native void XattachFile(byte[] filename, Pdf pdf) throws CpdfError;
    native void XattachFileToPage(byte[] filename, Pdf pdf, int pagenumber) throws CpdfError;
    native void XattachFileFromMemory(byte[] data, byte[] filename, Pdf pdf) throws CpdfError;
    native void XattachFileToPageFromMemory(byte[] data, byte[] filename, Pdf pdf, int pagenumber) throws CpdfError;
    public void attachFile(String filename, Pdf pdf) throws CpdfError
    {
        XattachFile(encodeUTF8(filename), pdf);
    }
    public void attachFileToPage(String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPage(encodeUTF8(filename), pdf, pagenumber);
    }
    public void attachFileFromMemory(byte[] data, String filename, Pdf pdf) throws CpdfError
    {
        XattachFileFromMemory(data, encodeUTF8(filename), pdf);
    }
    public void attachFileToPageFromMemory(byte[] data, String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPageFromMemory(data, encodeUTF8(filename), pdf, pagenumber);
    }
    public native void removeAttachedFiles(Pdf pdf) throws CpdfError;
    public native void startGetAttachments(Pdf pdf) throws CpdfError;
    public native int numberGetAttachments() throws CpdfError;
    public native String getAttachmentName(int serial) throws CpdfError;
    public native int getAttachmentPage(int serial) throws CpdfError;
    public native byte[] getAttachmentData(int serial) throws CpdfError;
    public native void endGetAttachments() throws CpdfError; 

    /* CHAPTER 13. Images. */
    public native int startGetImageResolution(Pdf pdf, double res) throws CpdfError;
    public native int getImageResolutionPageNumber(int serial) throws CpdfError;
    public native String getImageResolutionImageName(int serial) throws CpdfError;
    public native int getImageResolutionXPixels(int serial) throws CpdfError;
    public native int getImageResolutionYPixels(int serial) throws CpdfError;
    public native double getImageResolutionXRes(int serial) throws CpdfError;
    public native double getImageResolutionYRes(int serial) throws CpdfError;
    public native void endGetImageResolution() throws CpdfError;

    /* CHAPTER 14. Fonts. */
    public native void startGetFontInfo(Pdf pdf) throws CpdfError;
    public native int numberFonts() throws CpdfError;
    public native String getFontName(int serial) throws CpdfError;
    public native int getFontPage(int serial) throws CpdfError;
    public native String getFontType(int setial) throws CpdfError;
    public native String getFontEncoding(int serial) throws CpdfError;
    public native void endGetFontInfo() throws CpdfError;
    public native void removeFonts(Pdf pdf) throws CpdfError;
    public native void copyFont(Pdf from_pdf, Pdf to_pdf, Range range, int pagenumber, String fontname) throws CpdfError;

    /* CHAPTER 15. PDF and JSON */
    native void XoutputJSON(byte[] filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError;
    public void outputJSON(String filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError
    {
        XoutputJSON(encodeUTF8(filename), parse_content, no_stream_data, decompress_streams, pdf);
    }
    
    native Pdf XfromJSON(byte[] filename) throws CpdfError;
    public Pdf fromJSON(String filename) throws CpdfError
    {
        return XfromJSON(encodeUTF8(filename));
    }

    public native byte[] outputJSONMemory(Pdf pdf, boolean parse_content, boolean no_stream_data, boolean decompress_streams) throws CpdfError;
    public native Pdf fromJSONMemory(byte[] data) throws CpdfError;
    
    /* CHAPTER 16. Optional Content Groups */
    public native int startGetOCGList(Pdf pdf) throws CpdfError;
    public native String OCGListEntry(int serial) throws CpdfError;
    public native void endGetOCGList() throws CpdfError;
    public native void OCGCoalesce(Pdf pdf) throws CpdfError;
    public native void OCGRename(Pdf pdf, String f, String t) throws CpdfError;
    public native void OCGOrderAll(Pdf pdf) throws CpdfError;
    public native Pdf blankDocument(double w, double h, int pages) throws CpdfError;
    public native Pdf blankDocumentPaper(int papersize, int pages) throws CpdfError;
    native Pdf XtextToPDF(double w, double h, int font, double fontsize, byte[] filename) throws CpdfError;
    native Pdf XtextToPDFPaper(int papersize, int font, double fontsize, byte[] filename) throws CpdfError;
    public Pdf textToPDF(double w, double h, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDF(w, h, font, fontsize, encodeUTF8(filename));
    }
    public Pdf textToPDFPaper(int papersize, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDFPaper(papersize, font, fontsize, encodeUTF8(filename));
    }

    /* CHAPTER 18. Miscellaneous */
    public native void draft(Pdf pdf, Range range, boolean boxes) throws CpdfError;
    public native void removeAllText(Pdf pdf, Range range) throws CpdfError;
    public native void blackText(Pdf pdf, Range range) throws CpdfError;
    public native void blackLines(Pdf pdf, Range range) throws CpdfError;
    public native void blackFills(Pdf pdf, Range range) throws CpdfError;
    public native void thinLines(Pdf pdf, Range range, double minwidth) throws CpdfError;
    public native void copyId(Pdf pdf, Pdf pdf2) throws CpdfError;
    public native void removeId(Pdf pdf) throws CpdfError;
    public native void setVersion(Pdf pdf, int version) throws CpdfError;
    public native void setFullVersion(Pdf pdf, int major, int minor) throws CpdfError;
    public native void removeDictEntry(Pdf pdf, String str) throws CpdfError;
    public native void removeDictEntrySearch(Pdf pdf, String str, String searchterm) throws CpdfError;
    public native void replaceDictEntry(Pdf pdf, String key, String newvalue) throws CpdfError;
    public native void replaceDictEntrySearch(Pdf pdf, String key, String newvalue, String searchterm) throws CpdfError;
    public native byte[] getDictEntries(Pdf pdf, String key) throws CpdfError;
    public native void removeClipping(Pdf pdf, Range range) throws CpdfError;
}
