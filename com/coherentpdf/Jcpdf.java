package com.coherentpdf;
import java.nio.charset.Charset;

public class Jcpdf {
    public static class CpdfError extends Exception
    {
      public CpdfError(String errorMessage)
      {
        super(errorMessage);
      }
    }
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
    public Jcpdf()
    {
      System.loadLibrary("cpdf");
      System.loadLibrary("jcpdf");
    }
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    String decodeUTF8(byte[] bytes)
    {
        return new String(bytes, UTF8_CHARSET);
    }
    byte[] encodeUTF8(String string)
    {
        return string.getBytes(UTF8_CHARSET);
    }
    public native void onExit();
    public native void deletePdf(int pdf);
    public native void deleteRange(int range);
    public native void startup() throws CpdfError;
    public native String version() throws CpdfError;
    public native void setFast() throws CpdfError;
    public native void setSlow() throws CpdfError;
    native Pdf XfromFile(byte[] filename, byte[] userpw) throws CpdfError;
    public Pdf fromFile(String filename, String userpw) throws CpdfError
    { 
      return XfromFile(encodeUTF8(filename), encodeUTF8(userpw));
    }
    public native Pdf fromFileLazy(String filename, String userpw) throws CpdfError;
    public native byte[] toMemory(Pdf pdf, boolean linearize, boolean make_id) throws CpdfError;
    public native Pdf fromMemory(byte[] data, String userpw) throws CpdfError;
    public native void fromMemoryLazyRelease(byte[] data) throws CpdfError;
    public native Pdf fromMemoryLazy(byte[] data, String userpw) throws CpdfError;
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
    public native int pagesFast(String userpw, String filename) throws CpdfError;
    public native void toFile(Pdf pdf, String filename, boolean linearize, boolean make_id) throws CpdfError;
    public native void toFileExt(Pdf pdf, String filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError;
    public native boolean isEncrypted(Pdf pdf) throws CpdfError;
    public native boolean isLinearized(String filename) throws CpdfError;
    public native int toFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, String filename) throws CpdfError;
    public native int toFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, String filename) throws CpdfError;
    public native boolean hasPermission(Pdf pdf, int permission) throws CpdfError;
    public native int encryptionKind(Pdf pdf) throws CpdfError;
    public native void decryptPdf(Pdf pdf, String userpw) throws CpdfError;
    public native void decryptPdfOwner(Pdf pdf, String ownerpw) throws CpdfError;
    public native Pdf mergeSimple(Pdf[] pdfs) throws CpdfError;
    public native Pdf merge(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts) throws CpdfError;
    public native Pdf mergeSame(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts, Range[] ranges) throws CpdfError;
    public native Pdf selectPages(Pdf pdf, Range range) throws CpdfError;
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
    public native void compress(Pdf pdf) throws CpdfError;
    public native void decompress(Pdf pdf) throws CpdfError;
    public native void squeezeInMemory(Pdf pdf) throws CpdfError;
    
    /* CHAPTER 6. Bookmarks */
    public native void startGetBookmarkInfo(Pdf pdf) throws CpdfError;
    public native int numberBookmarks() throws CpdfError;
    public native int getBookmarkLevel(int serial) throws CpdfError;
    public native int getBookmarkPage(Pdf pdf, int serial) throws CpdfError;
    public native String getBookmarkText(int serial) throws CpdfError;
    public native boolean getBookmarkOpenStatus(int serial) throws CpdfError;
    public native void endGetBookmarkInfo() throws CpdfError;
    public native void startSetBookmarkInfo(int n) throws CpdfError;
    public native void setBookmarkLevel(int serial, int level) throws CpdfError;
    public native void setBookmarkPage(Pdf pdf, int serial, int pagenum) throws CpdfError;
    public native void setBookmarkOpenStatus(int serial, boolean open) throws CpdfError;
    public native void setBookmarkText(int serial, String text) throws CpdfError;
    public native void endSetBookmarkInfo(Pdf pdf) throws CpdfError;
    public native byte[] getBookmarksJSON(Pdf pdf) throws CpdfError;
    public native void setBookmarksJSON(Pdf pdf, byte[] data) throws CpdfError;
    public native void tableOfContents(Pdf pdf, int font, double fontsize, String title, boolean bookmark) throws CpdfError;


    /* CHAPTER 8. Logos, Watermarks and Stamps */
    public native void addText(boolean metrics, Pdf pdf, Range range, String text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, String filename, double linewidth, boolean embed_fonts) throws CpdfError;
    public native void addTextSimple(Pdf pdf, Range range, String text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError;
    public native void removeText(Pdf pdf, Range range) throws CpdfError;
    public native int textWidth(int font, String text) throws CpdfError;
    public native void stampOn(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;
    public native void stampUnder(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;
    public native void stampExtended(Pdf pdf, Pdf pdf2, Range range, boolean isover, boolean scale_stamp_to_fit, int anchor, double p1, double p2, boolean relative_to_cropbox) throws CpdfError;
    public native Pdf combinePages(Pdf pdf, Pdf pdf2) throws CpdfError;
    public native String stampAsXObject(Pdf pdf, Range range, Pdf stamp_pdf) throws CpdfError;
    public native void addContent(String s, boolean before, Pdf pdf, Range range) throws CpdfError; 
    
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
    public native String getTitle(Pdf pdf) throws CpdfError;
    public native String getAuthor(Pdf pdf) throws CpdfError;
    public native String getSubject(Pdf pdf) throws CpdfError;
    public native String getKeywords(Pdf pdf) throws CpdfError;
    public native String getCreator(Pdf pdf) throws CpdfError;
    public native String getProducer(Pdf pdf) throws CpdfError;
    public native String getCreationDate(Pdf pdf) throws CpdfError;
    public native String getModificationDate(Pdf pdf) throws CpdfError;
    public native String getTitleXMP(Pdf pdf) throws CpdfError;
    public native String getAuthorXMP(Pdf pdf) throws CpdfError;
    public native String getSubjectXMP(Pdf pdf) throws CpdfError;
    public native String getKeywordsXMP(Pdf pdf) throws CpdfError;
    public native String getCreatorXMP(Pdf pdf) throws CpdfError;
    public native String getProducerXMP(Pdf pdf) throws CpdfError;
    public native String getCreationDateXMP(Pdf pdf) throws CpdfError;
    public native String getModificationDateXMP(Pdf pdf) throws CpdfError;
    public native void setTitle(Pdf pdf, String str) throws CpdfError;
    public native void setAuthor(Pdf pdf, String str) throws CpdfError;
    public native void setSubject(Pdf pdf, String str) throws CpdfError;
    public native void setKeywords(Pdf pdf, String str) throws CpdfError;
    public native void setCreator(Pdf pdf, String str) throws CpdfError;
    public native void setProducer(Pdf pdf, String str) throws CpdfError;
    public native void setCreationDate(Pdf pdf, String str) throws CpdfError;
    public native void setModificationDate(Pdf pdf, String str) throws CpdfError;
    public native void setTitleXMP(Pdf pdf, String str) throws CpdfError;
    public native void setAuthorXMP(Pdf pdf, String str) throws CpdfError;
    public native void setSubjectXMP(Pdf pdf, String str) throws CpdfError;
    public native void setKeywordsXMP(Pdf pdf, String str) throws CpdfError;
    public native void setCreatorXMP(Pdf pdf, String str) throws CpdfError;
    public native void setProducerXMP(Pdf pdf, String str) throws CpdfError;
    public native void setCreationDateXMP(Pdf pdf, String str) throws CpdfError;
    public native void setModificationDateXMP(Pdf pdf, String str) throws CpdfError;
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
    public native void setMetadataFromFile(Pdf pdf, String filename) throws CpdfError;
    public native void setMetadataFromByteArray(Pdf pdf, byte[] data) throws CpdfError;
    public native byte[] getMetadata(Pdf pdf) throws CpdfError;
    public native void removeMetadata(Pdf pdf) throws CpdfError;
    public native void createMetadata(Pdf pdf) throws CpdfError;
    public native void setMetadataDate(Pdf pdf, String date) throws CpdfError;
    public native void addPageLabels(Pdf pdf, int style, String prefix, int offset, Range range, boolean progress) throws CpdfError;
    public native void removePageLabels(Pdf pdf) throws CpdfError;
    public native int startGetPageLabels(Pdf pdf) throws CpdfError;
    public native void endGetPageLabels() throws CpdfError;
    public native int getPageLabelOffset(int n) throws CpdfError;
    public native int getPageLabelStyle(int n) throws CpdfError;
    public native int getPageLabelRange(int n) throws CpdfError;
    public native String getPageLabelPrefix(int n) throws CpdfError;
    public native String getPageLabelStringForPage(Pdf pdf, int n) throws CpdfError;
    
    /* CHAPTER 12. File Attachments */
    public native void attachFile(String filename, Pdf pdf) throws CpdfError;
    public native void attachFileToPage(String filename, Pdf pdf, int pagenumber) throws CpdfError;
    public native void attachFileFromMemory(byte[] data, String filename, Pdf pdf) throws CpdfError;
    public native void attachFileToPageFromMemory(byte[] data, String filename, Pdf pdf, int pagenumber) throws CpdfError;
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
    public native void outputJSON(String filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError;
    public native Pdf fromJSON(String filename) throws CpdfError;
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
    public native Pdf textToPDF(double w, double h, int font, double fontsize, String filename) throws CpdfError;
    public native Pdf textToPDFPaper(int papersize, int font, double fontsize, String filename) throws CpdfError;

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
}
