package com.coherentpdf;
import java.nio.charset.Charset;

/** The Coherent PDF Library for Java */
public class Jcpdf {
    /** Create a new instance of the Jcpdf library. You must use <code>startup()</code> too,
     before calling any other function.*/
    public Jcpdf()
    {
      System.loadLibrary("cpdf");
      System.loadLibrary("jcpdf");
    }
    
    /** Any function in this library may raise the CPDFError exception. */
    public static class CpdfError extends Exception
    {
      public CpdfError(String errorMessage)
      {
        super(errorMessage);
      }
    }

    /** PDF document. Use the 'try' keyword, or call close() to make sure PDFs are deallocated. */
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

    /** Page range. Use the 'try' keyword, or call close() to make sure ranges are deallocated. */
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

    // Enumerations

    /** Permission: cannot edit the document */
    public int noEdit = 0;
    /** Permission: cannot print the document */
    public int noPrint = 1;
    /** Permission: cannot copy the document */
    public int noCopy = 2;
    /** Permission: cannot annotate the document */
    public int noAnnot = 3;
    /** Permission: cannot edit forms in the document */
    public int noForms = 4;
    /** Permission: cannot extract information */
    public int noExtract = 5;
    /** Permission: cannot assemble into a bigger document */
    public int noAssemble = 6;
    /** Permission: cannot print high quality */
    public int noHqPrint = 7;

    /** Encryption method: 40 bit RC4 encryption */
    public int pdf40bit = 0;
    /** Encryption method: 128 bit RC4 encryption */
    public int pdf128bit = 1;
    /** Encryption method: 128 bit AES encryption, do not encrypt metadata */
    public int aes128bitfalse = 2;
    /** Encryption method: 128 bit AES encryption, encrypt metadata */
    public int aes128bittrue = 3;
    /** Encryption method: Deprecated. Do not use for new files */
    public int aes256bitfalse = 4;
    /** Encryption method: Deprecated. Do not use for new files */
    public int aes256bittrue = 5;
    /** Encryption method: 256 bit AES encryption, do not encrypt metadata */
    public int aes256bitisofalse = 6;
    /** Encryption method: 256 bit AES encryption, encrypt metadata */
    public int aes256bitiso = 7;

    /** Page label style: 1, 2, 3... */
    public int decimalArabic = 0;
    /** Page label style: I, II, III... */
    public int uppercaseRoman = 1;
    /** Page label style: i, ii, iii... */
    public int lowercaseRoman = 2;
    /** Page label style: A, B, C... */
    public int uppercaseLetters = 3;
    /** Page label style: a, b, c...*/
    public int lowercaseLetters = 4;

    /** Layout: single page */
    public int singlePage = 0;
    /** Layout: one column */
    public int oneColumn = 1;
    /** Layout: two column left */
    public int twoColumnLeft = 2;
    /** Layout: two column right */
    public int twoColumnRight = 3;
    /** Layout: two page left */
    public int twoPageLeft = 4;
    /** Layout: two page right */
    public int twoPageRight = 5;

    /** Page mode: use none */
    public int useNone = 0;
    /** Page mode: use outlines */
    public int useOutlines = 1;
    /** Page mode: use thumbs */
    public int useThumbs = 2;
    /** Page mode: use OC */
    public int useOC = 3;
    /** Page mode: use Attachments */
    public int useAttachments = 4;

    /** Paper size: A0 Portrait */
    public int a0portrait = 0;
    /** Paper size: A1 Portrait */
    public int a1portrait = 1;
    /** Paper size: A2 Portrait */
    public int a2portrait = 2;
    /** Paper size: A3 Portrait */
    public int a3portrait = 3;
    /** Paper size: A4 Portrait */
    public int a4portrait = 4;
    /** Paper size: A5 Portrait */
    public int a5portrait = 5;
    /** Paper size: A0 Landscape */
    public int a0landscape = 6;
    /** Paper size: A1 Landscape */
    public int a1landscape = 7;
    /** Paper size: A2 Landscape */
    public int a2landscape = 8;
    /** Paper size: A3 Landscape */
    public int a3landscape = 9;
    /** Paper size: A4 Landscape */
    public int a4landscape = 10;
    /** Paper size: A5 Landscape */
    public int a5landscape = 11;
    /** Paper size: US Letter Portrait */
    public int usletterportrait = 12;
    /** Paper size: US Letter Landscape */
    public int usletterlandscape = 13;
    /** Paper size: US Legal Portrait */
    public int uslegalportrait = 14;
    /** Paper size: US Legal Landscape */
    public int uslegallandscape = 15;

    /** Standard font: Times Roman */
    public int timesRoman = 0;
    /** Standard font: Times Bold */
    public int timesBold = 1;
    /** Standard font: Times Italic */
    public int timesItalic = 2;
    /** Standard font: Times Bold Italic */
    public int timesBoldItalic = 3;
    /** Standard font: Helvetica */
    public int helvetica = 4;
    /** Standard font: Helvetica Bold */
    public int helveticaBold = 5;
    /** Standard font: Helvetica Oblique */
    public int helveticaOblique = 6;
    /** Standard font: Helvetica Bold Oblique */
    public int helveticaBoldOblique = 7;
    /** Standard font: Courier */
    public int courier = 8;
    /** Standard font: Courier Bold */
    public int courierBold = 9;
    /** Standard font: Courier Oblique */
    public int courierOblique = 10;
    /** Standard font: Courier Bold Oblique */
    public int courierBoldOblique = 11;

    /** Position anchor: absolute centre */
    public int posCentre = 0;
    /** Position anchor: absolute left */
    public int posLeft = 1;
    /** Position anchor: absolute right */
    public int posRight = 2;
    /** Position anchor: the top centre of the page */
    public int top = 3;
    /** Position anchor: the top left of the page */
    public int topLeft = 4;
    /** Position anchor: the top right of the page */
    public int topRight = 5;
    /** Position anchor: the left hand side of the page, halfway down */
    public int left = 6;
    /** Position anchor: the bottom left of the page */
    public int bottomLeft = 7;
    /** Position anchor: the bottom middle of the page */
    public int bottom = 8;
    /** Position anchor: the bottomm right of the page */
    public int bottomRight = 9;
    /** Position anchor: the right hand side of the page, halfway down */
    public int right = 10;
    /** Position anchor: diagonal, bottom left to top right */
    public int diagonal = 11;
    /** Position anchor: diagonal, top left to bottom right */
    public int reverseDiagonal = 12;

    /** Justification: left */
    public int leftJustify = 0;
    /** Justification: centre */
    public int centreJustify = 1;
    /** Justification: right */
    public int rightJusitfy = 2;

    /* CHAPTER 0. Preliminaries */
    
    native void deletePdf(int pdf);
    native void deleteRange(int range);

    /** Initialises the library. Must be called before any other function. */
    public native void startup() throws CpdfError;
    
    /** Returns a string giving the version number of the CPDF library. */
    public native String version() throws CpdfError;
    
    /** Some operations have a fast mode. The default is 'slow' mode, which works
    even on old-fashioned files. For more details, see section 1.13 of the
    CPDF manual. This functions sets the mode to slow globally. */
    public native void setFast() throws CpdfError;

    /** Some operations have a fast mode. The default is 'slow' mode, which works
    even on old-fashioned files. For more details, see section 1.13 of the
    CPDF manual. This functions sets the mode to slow globally. */
    public native void setSlow() throws CpdfError;
    
    /** A debug function which prints some information about
    resource usage. This can be used to detect if PDFs or ranges are being
    deallocated properly. Contrary to its name, it may be run at any time. */
    public native void onExit();

    /* CHAPTER 1. Basics */
    native Pdf XfromFile(byte[] filename, byte[] userpw) throws CpdfError;
    /** Loads a PDF file from a given file. Supply
    a user password (possibly blank) in case the file is encrypted. It won't be
    decrypted, but sometimes the password is needed just to load the file. */
    public Pdf fromFile(String filename, String userpw) throws CpdfError
    { 
      return XfromFile(encodeUTF8(filename), encodeUTF8(userpw));
    }

    native Pdf XfromFileLazy(byte[] filename, byte[] userpw) throws CpdfError;
    
    /** Loads a PDF from a file, doing only minimal
    parsing. The objects will be read and parsed when they are actually
    needed. Use this when the whole file won't be required. Also supply a user
    password (possibly blank) in case the file is encrypted. It won't be
    decrypted, but sometimes the password is needed just to load the file. */
    public Pdf fromFileLazy(String filename, String userpw) throws CpdfError
    {
      return XfromFileLazy(encodeUTF8(filename), encodeUTF8(userpw));
    }


    native Pdf XfromMemory(byte[] data, byte[] userpw) throws CpdfError;

    /** Loads a file from memory given any user password. */
    public Pdf fromMemory(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemory(data, encodeUTF8(userpw));
    }

    /** Release memory returned from fromMemory */
    public native void fromMemoryLazyRelease(byte[] data) throws CpdfError;

    native Pdf XfromMemoryLazy(byte[] data, byte[] userpw) throws CpdfError;
    
    /** Loads a file from memory, given a pointer and a length, and the user
    password, but lazily like fromFileLazy. The caller must use
    fromMemoryLazyRelease to free the memory. It must not free the memory
    until the PDF is also gone. */
    public Pdf fromMemoryLazy(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemoryLazy(data, encodeUTF8(userpw));
    }

    /** To enumerate the list of currently allocated PDFs, call
    startEnumeratePDFs which gives the number, n, of PDFs allocated, then
    enumeratePDFsInfo and enumeratePDFsKey with index numbers from
    0...(n - 1). Call endEnumeratePDFs to clean up. */
    public native int startEnumeratePDFs() throws CpdfError;
    
    /** To enumerate the list of currently allocated PDFs, call
    startEnumeratePDFs which gives the number, n, of PDFs allocated, then
    enumeratePDFsInfo and enumeratePDFsKey with index numbers from
    0...(n - 1). Call endEnumeratePDFs to clean up. */
    public native int enumeratePDFsKey(int n) throws CpdfError;
    
    /** To enumerate the list of currently allocated PDFs, call
    startEnumeratePDFs which gives the number, n, of PDFs allocated, then
    enumeratePDFsInfo and enumeratePDFsKey with index numbers from
    0...(n - 1). Call endEnumeratePDFs to clean up. */
    public native String enumeratePDFsInfo(int n) throws CpdfError;
    
    /** To enumerate the list of currently allocated PDFs, call
    startEnumeratePDFs which gives the number, n, of PDFs allocated, then
    enumeratePDFsInfo and enumeratePDFsKey with index numbers from
    0...(n - 1). Call endEnumeratePDFs to clean up. */
    public native void endEnumeratePDFs() throws CpdfError;


    /** Converts a figure in centimetres to points (72 points to 1 inch) */
    public native double ptOfCm(double f) throws CpdfError;
    
    /** Converts a figure in millimetres to points (72 points to 1 inch) */
    public native double ptOfMm(double f) throws CpdfError;
    
    /** Converts a figure in inches to points (72 points to 1 inch) */
    public native double ptOfIn(double f) throws CpdfError;
    
    /** Converts a figure in points to centimetres (72 points to 1 inch) */
    public native double cmOfPt(double f) throws CpdfError;
    
    /** Converts a figure in points to millimetres (72 points to 1 inch) */
    public native double mmOfPt(double f) throws CpdfError;

    /** Converts a figure in points to millimetres (72 points to 1 inch) */
    public native double inOfPt(double f) throws CpdfError;

    /** Parses a page specification with reference
    to a given PDF (the PDF is supplied so that page ranges which reference
    pages which do not exist are rejected). */
    public native Range parsePagespec(Pdf pdf, String pagespec) throws CpdfError;
    
    /** Validates a page specification so far as is
    possible in the absence of the actual document. Result is true if valid. */
    public native boolean validatePagespec(String pagespec) throws CpdfError;

    native byte[] XstringOfPagespec(Pdf pdf, Range r) throws CpdfError;
    
    /** Builds a page specification from a page
    range. For example, the range containing 1,2,3,6,7,8 in a document of 8
    pages might yield "1-3,6-end" */
    public String stringOfPagespec(Pdf pdf, Range r) throws CpdfError
    {
        return decodeUTF8(XstringOfPagespec(pdf, r));    
    }

    /** The range containing no pages. */
    public native Range blankRange() throws CpdfError;

    /** The page range containing all page numbers from one page number to another. */
    public native Range range(int from, int to) throws CpdfError;

    /** The page range contaning all pages in a given document. */
    public native Range all(Pdf pdf) throws CpdfError;

    /** The page range containing all odd-numbered pages from an existing range. */
    public native Range odd(Range r) throws CpdfError;

    /** The page range containing all even-numbered pages from an existing range. */
    public native Range even(Range r) throws CpdfError;

    /** The union of two ranges - all those pages in either. */
    public native Range rangeUnion(Range r, Range s) throws CpdfError;

    /** The range containing all pages in the first given range which are not in the second. */
    public native Range difference(Range r, Range s) throws CpdfError;
    
    /** Remove duplicates from a range, returning a new one. */
    public native Range removeDuplicates(Range r) throws CpdfError;

    /** The length of a range. */
    public native int rangeLength(Range r) throws CpdfError;

    /** Get a page number from a range at the given offset. */
    public native int rangeGet(Range r, int n) throws CpdfError;
    
    /** Add a page number to a range, returning a new one. */
    public native Range rangeAdd(Range r, int n) throws CpdfError;

    /** Test to see if a given number is in a page range. */
    public native boolean isInRange(Range r, int n) throws CpdfError;

    /** Returns the number of pages in a PDF. */
    public native int pages(Pdf pdf) throws CpdfError;

    native int XpagesFast(byte[] userpw, byte[] filename) throws CpdfError;

    /** Returns the number of pages in a given
    PDF, with given user password. It tries to do this as fast as
    possible, without loading the whole file. */
    public int pagesFast(String userpw, String filename) throws CpdfError
    {
        return XpagesFast(encodeUTF8(userpw), encodeUTF8(filename));
    }

    native void XtoFile(Pdf pdf, byte[] filename, boolean linearize, boolean make_id) throws CpdfError;
    
    /** Writes the file to a given
    filename. If linearize is true, it will be linearized if a linearizer is
    available. If make_id is true, it will be given a new ID. */
    public void toFile(Pdf pdf, String filename, boolean linearize, boolean make_id) throws CpdfError
    {
        XtoFile(pdf, encodeUTF8(filename), linearize, make_id);
    }
    
    native void XtoFileExt(Pdf pdf, byte[] filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError;

    /** Writes the file to a given filename. If
    make_id is true, it will be given a new ID.  If preserve_objstm is true,
    existing object streams will be preserved. If generate_objstm is true,
    object streams will be generated even if not originally present. If
    compress_objstm is true, object streams will be compressed (what we
    usually want). WARNING: the pdf argument will be invalid after this call,
    and should be not be used again. */
    public void toFileExt(Pdf pdf, String filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError
    {
        XtoFileExt(pdf, encodeUTF8(filename), linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
    }

    /** Writes a PDF file and returns as an array of bytes. */
    public native byte[] toMemory(Pdf pdf, boolean linearize, boolean make_id) throws CpdfError;

    /** Returns true if a documented is encrypted, false otherwise. */
    public native boolean isEncrypted(Pdf pdf) throws CpdfError;
    
    /** Attempts to decrypt a PDF using the given
    user password. An exception is raised if the decryption fails. */
    public void decryptPdf(Pdf pdf, String userpw) throws CpdfError
    {
        XdecryptPdf(pdf, encodeUTF8(userpw));
    }

    native void XdecryptPdfOwner(Pdf pdf, byte[] ownerpw) throws CpdfError;

    /** Attempts to decrypt a PDF using the given owner password. Raises an
    exception if the decryption fails. */
    public void decryptPdfOwner(Pdf pdf, String ownerpw) throws CpdfError
    {
        XdecryptPdfOwner(pdf, encodeUTF8(ownerpw));
    }

    native int XtoFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, byte[] filename) throws CpdfError;

    /** Writes a file as encrypted. The encryption method and permissions are drawn from Jcpdf's fields, documented above. */
    public int toFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, String filename) throws CpdfError
    {
        return XtoFileEncrypted(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, encodeUTF8(filename));
    }
    native int XtoFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, byte[] filename) throws CpdfError;

    /** Writes a file as encrypted with extra parameters. WARNING: the pdf
     argument will be invalid after this call, and should not be used again.
     */
    public int toFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, String filename) throws CpdfError
    {
        return XtoFileEncryptedExt(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, preserve_objstm, generate_objstm, compress_objstm, encodeUTF8(filename));
    }

    /** Returns true if the given permission (restriction) is present. */
    public native boolean hasPermission(Pdf pdf, int permission) throws CpdfError;
    
    /** Returns the encryption method currently in use on a document. */
    public native int encryptionKind(Pdf pdf) throws CpdfError;
    native void XdecryptPdf(Pdf pdf, byte[] userpw) throws CpdfError;

    /* CHAPTER 2. Merging and Splitting */
    
    /** Given an array of PDFs, merges the documnets into a new one, which is returned. */
    public native Pdf mergeSimple(Pdf[] pdfs) throws CpdfError;
    
    /** Merges the PDFs. If retain_numbering is true page labels are not rewritten. If
    remove_duplicate_fonts is true, duplicate fonts are merged. This is useful
    when the source documents for merging originate from the same source. */
    public native Pdf merge(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts) throws CpdfError;
    
    /** The same as merge, except that it has an additional
    argument - a list of page ranges. This is used to select the pages to
    pick from each PDF. This avoids duplication of information when multiple
    discrete parts of a source PDF are included. */
    public native Pdf mergeSame(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts, Range[] ranges) throws CpdfError;
    
    /** Returns a new document which just those pages in the page range. */
    public native Pdf selectPages(Pdf pdf, Range range) throws CpdfError;

    /* CHAPTER 3. Pages */
    
    /** Scales the page dimensions
    and content by the given scale, about (0, 0). Other boxes (crop etc. are
    altered as appropriate). */
    public native void scalePages(Pdf pdf, Range range, double sx, double sy) throws CpdfError;

    /** Scales the content to fit
    new page dimensions (width x height) multiplied by scale (typically 1.0).
    Other boxes (crop etc. are altered as appropriate). */
    public native void scaleToFit(Pdf pdf, Range range, double w, double h, double scale) throws CpdfError;

    /** Scales the page content to fit the given page size, possibly multiplied by scale (typically 1.0). */
    public native void scaleToFitPaper(Pdf pdf, Range range, int papersize, double scale) throws CpdfError;

    /** Scales the contents of the
    pages in the range about the point given by the position, by the scale given. */
    public native void scaleContents(Pdf pdf, Range range, int anchor, double p1, double p2, double scale) throws CpdfError;

    /** Shifts the content of the pages in the range. */
    public native void shiftContents(Pdf pdf, Range range, double dx, double dy) throws CpdfError;

    /** Changes the viewing rotation to an absolute value. Appropriate rotations are 0, 90, 180, 270. */
    public native void rotate(Pdf pdf, Range range, int angle) throws CpdfError;

    /** Rotates the content about the centre of the page by the given number of degrees, in a clockwise
    direction. */
    public native void rotateBy(Pdf pdf, Range range, int angle) throws CpdfError;

    /** Rotates the content about the centre of the page by the given number of degrees, in a clockwise
    direction. */
    public native void rotateContents(Pdf pdf, Range range, double angle) throws CpdfError;

    /** Changes the viewing rotation of the pages in the
    range, counter-rotating the dimensions and content such that there is no
    visual change. */
    public native void upright(Pdf pdf, Range range) throws CpdfError;

    /** Flips horizontally the pages in the range. */
    public native void hFlip(Pdf pdf, Range range) throws CpdfError;

    /** Flips vertically the pages in the range. */
    public native void vFlip(Pdf pdf, Range range) throws CpdfError;

    /** Crops a page, replacing any existing crop box. The dimensions are in points. */
    public native void crop(Pdf pdf, Range range, double x, double y, double w, double h) throws CpdfError;

    /** Removes any crop box from pages in the range. */
    public native void removeCrop(Pdf pdf, Range range) throws CpdfError;

    /** Removes any trim box from pages in the range. */
    public native void removeTrim(Pdf pdf, Range range) throws CpdfError;

    /** Removes any art box from pages in the range. */
    public native void removeArt(Pdf pdf, Range range) throws CpdfError;

    /** Removes any bleed box from pages in the range. */
    public native void removeBleed(Pdf pdf, Range range) throws CpdfError;

    /** Adds trim marks to the given pages, if the trimbox exists. */
    public native void trimMarks(Pdf pdf, Range range) throws CpdfError;

    /** Shows the boxes on the given pages, for debug. */
    public native void showBoxes(Pdf pdf, Range range) throws CpdfError;

    /** Makes a given box a 'hard box' i.e clips it explicitly. */
    public native void hardBox(Pdf pdf, Range range, String box) throws CpdfError;

    /* CHAPTER 4. Encryption */
    /* Encryption covered under Chapter 1 in cpdflib. */

    /* CHAPTER 5. Compression */
    
    /** Compresses any uncompressed streams in the given PDF using the Flate algorithm. */
    public native void compress(Pdf pdf) throws CpdfError;
    
    /** Decompresses any streams in the given PDF, so long as the compression method is supported. */
    public native void decompress(Pdf pdf) throws CpdfError;

    /** Squeezes a pdf in memory. */
    public native void squeezeInMemory(Pdf pdf) throws CpdfError;
    
    /* CHAPTER 6. Bookmarks */
    
    /** Starts the bookmark retrieval process for a given PDF. */
    public native void startGetBookmarkInfo(Pdf pdf) throws CpdfError;

    /** Gets the number of bookmarks for the PDF given to startGetBookmarkInfo. */
    public native int numberBookmarks() throws CpdfError;
    
    /** Gets the bookmark level for the given bookmark (0...(n - 1)). */
    public native int getBookmarkLevel(int serial) throws CpdfError;
    
    /** Gets the bookmark target page for the given PDF
    (which must be the same as the PDF passed to startSetBookmarkInfo)
    and bookmark (0...(n - 1)). */
    public native int getBookmarkPage(Pdf pdf, int serial) throws CpdfError;

    native byte[] XgetBookmarkText(int serial) throws CpdfError;
    
    /** Returns the text of bookmark (0...(n - 1)). */
    public String getBookmarkText(int serial) throws CpdfError
    {
        return decodeUTF8(XgetBookmarkText(serial));
    }

    /** True if the bookmark is open. */
    public native boolean getBookmarkOpenStatus(int serial) throws CpdfError;

    /** Ends the bookmark retrieval process, cleaning up. */
    public native void endGetBookmarkInfo() throws CpdfError;
    
    /** Starts the bookmark setting process for n bookmarks. */
    public native void startSetBookmarkInfo(int n) throws CpdfError;

    /** Set bookmark level for the given bookmark (0...(n - 1)). */
    public native void setBookmarkLevel(int serial, int level) throws CpdfError;

    /** Sets the bookmark target
    page for the given PDF (which must be the same as the PDF to be passed to
    endSetBookmarkInfo) and bookmark (0...(n - 1)). */
    public native void setBookmarkPage(Pdf pdf, int serial, int pagenum) throws CpdfError;
    
    /** Sets the open status of bookmark (0...(n - 1)). */
    public native void setBookmarkOpenStatus(int serial, boolean open) throws CpdfError;

    native void XsetBookmarkText(int serial, byte[] text) throws CpdfError;
    /** Sets the text of bookmark (0...(n - 1)). */
    public void setBookmarkText(int serial, String text) throws CpdfError
    {
        XsetBookmarkText(serial, encodeUTF8(text));
    }
    
    /** Ends the bookmark setting process, writing the bookmarks to the given PDF. */
    public native void endSetBookmarkInfo(Pdf pdf) throws CpdfError;

    /** Returns the bookmark data in JSON format. */
    public native byte[] getBookmarksJSON(Pdf pdf) throws CpdfError;
    
    /** Sets the bookmarks from JSON bookmark data. */
    public native void setBookmarksJSON(Pdf pdf, byte[] data) throws CpdfError;

    native void XtableOfContents(Pdf pdf, int font, double fontsize, byte[] title, boolean bookmark) throws CpdfError;
    
    /** Typesets a table
    of contents from existing bookmarks and prepends it to the document. If
    bookmark is set, the table of contents gets its own bookmark. */
    public void tableOfContents(Pdf pdf, int font, double fontsize, String title, boolean bookmark) throws CpdfError
    {
        XtableOfContents(pdf, font, fontsize, encodeUTF8(title), bookmark);
    }

    /* CHAPTER 7. Presentations */
    /* Not included in the library version. */

    /* CHAPTER 8. Logos, Watermarks and Stamps */
   
    /** Stamps stamp_pdf on top of all the
    pages in the document which are in the range. The stamp is placed with its
    origin at the origin of the target document. */
    public native void stampOn(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;

    /** Stamps stamp_pdf under all the
    pages in the document which are in the range. The stamp is placed with its
    origin at the origin of the target document. */
    public native void stampUnder(Pdf pdf, Pdf pdf2, Range range) throws CpdfError;

    /** A stamping function with extra features. - isover
    true, pdf goes over pdf2, isover false, pdf goes under pdf2 -
    scale_stamp_to_fit scales the stamp to fit the page - pos gives the
    position to put the stamp - relative_to_cropbox: if true, pos is relative
    to cropbox not mediabox. */
    public native void stampExtended(Pdf pdf, Pdf pdf2, Range range, boolean isover, boolean scale_stamp_to_fit, int anchor, double p1, double p2, boolean relative_to_cropbox) throws CpdfError;
    
    /** Combines the PDFs page-by-page, putting each page of 'over' over each page of 'under'. */
    public native Pdf combinePages(Pdf pdf, Pdf pdf2) throws CpdfError;
    
    native void XaddText(boolean metrics, Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, byte[] filename, double linewidth, boolean embed_fonts) throws CpdfError;

    /** Adds text to the pages in the given range. */
    public void addText(boolean metrics, Pdf pdf, Range range, String text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, String filename, double linewidth, boolean embed_fonts) throws CpdfError
    {
        XaddText(metrics, pdf, range, encodeUTF8(text), anchor, p1, p2, linespacing, bates, font, fontsize,
                r, g, b, underneath, cropbox, outline, opacity, justification, midline, topline, encodeUTF8(filename), linewidth, embed_fonts);
    }
    native void XaddTextSimple(Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError;
    
    /** Adds text with most parameters default. */
    public void addTextSimple(Pdf pdf, Range range, String text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError
    {
        XaddTextSimple(pdf, range, encodeUTF8(text), anchor, p1, p2, font, fontsize);
    }
    
    /** Removes any text added by cpdf from the given pages. */
    public native void removeText(Pdf pdf, Range range) throws CpdfError;

    native int XtextWidth(int font, byte[] text) throws CpdfError;
    
    /** Returns the width of a given string in the given font in thousandths of a point. */
    public int textWidth(int font, String test) throws CpdfError
    {
        return XtextWidth(font, encodeUTF8(test));
    }

    /** Adds page content before (if
    true) or after (if false) the existing content to pages in the given range
    in the given PDF. */
    public void addContent(String s, boolean before, Pdf pdf, Range range) throws CpdfError
    {
        XaddContent(encodeUTF8(s), before, pdf, range);
    }

    /** Stamps stamp_pdf onto the pages
    in the given range in pdf as a shared Form XObject. The name of the
    newly-created XObject is returned. */
    public native String stampAsXObject(Pdf pdf, Range range, Pdf stamp_pdf) throws CpdfError;

    native void XaddContent(byte[] s, boolean before, Pdf pdf, Range range) throws CpdfError;
    
    /* CHAPTER 9. Multipage facilities */
    
    /** Imposes a PDF. There are two modes: imposing x * y, or imposing
    to fit a page of size x * y. This is controlled by fit. Columns imposes by
    columns rather than rows. rtl is right-to-left, btt bottom-to-top. Center is
    unused for now. Margin is the margin around the output, spacing the spacing
    between imposed inputs. */
    public native void impose(Pdf pdf, double x, double y, boolean fit, boolean columns, boolean rtl, boolean btt, boolean center, double margin, double spacing, double linewidth) throws CpdfError;

    /** Imposes a document two up. twoUpStack does so by shrinking the page size, to fit two pages on one. */
    public native void twoUp(Pdf pdf) throws CpdfError;
    
    /** Impose a document two up. twoUpStack does so by doubling the page size, to fit two pages on one. */
    public native void twoUpStack(Pdf pdf) throws CpdfError;

    /** Adds a blank page before each page in the given range. */
    public native void padBefore(Pdf pdf, Range range) throws CpdfError;

    /** Adds a blank page after each page in the given range. */
    public native void padAfter(Pdf pdf, Range range) throws CpdfError;

    /** Adds a blank page after every n pages. */
    public native void padEvery(Pdf pdf, int n) throws CpdfError;

    /** Adds pages at the end to pad the file to a multiple of n pages in length. */
    public native void padMultiple(Pdf pdf, int n) throws CpdfError;

    /** Adds pages at the beginning to pad the file to a multiple of n pages in length. */
    public native void padMultipleBefore(Pdf pdf, int n) throws CpdfError;

    /* CHAPTER 10. Annotations */
    
    /** Returns the annotations from a PDF in JSON format. */
    public native byte[] annotationsJSON(Pdf pdf) throws CpdfError;
    
    /* CHAPTER 11. Document Information and Metadata */
    
    native boolean XisLinearized(byte[] filename) throws CpdfError;
    
    /** Finds out if a document is linearized as quickly as possible without loading it. */
    public boolean isLinearized(String filename) throws CpdfError
    {
        return XisLinearized(encodeUTF8(filename));
    }

    /** Returns the minor version number of a document. */
    public native int getVersion(Pdf pdf) throws CpdfError;
    
    /** Returns the major version number of a document. */
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

    /** Returns the title of a document. */
    public String getTitle(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetTitle(pdf)); 
    }

    /** Returns the author of a document. */
    public String getAuthor(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetAuthor(pdf)); 
    }

    /** Returns the subject of a document. */
    public String getSubject(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetSubject(pdf)); 
    }

    /** Returns the keywords of a document. */
    public String getKeywords(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetKeywords(pdf)); 
    }

    /** Returns the creator of a document. */
    public String getCreator(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreator(pdf)); 
    }

    /** Returns the producer of a document. */
    public String getProducer(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetProducer(pdf)); 
    }

    /** Returns the creation date of a document. */
    public String getCreationDate(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreationDate(pdf)); 
    }

    /** Returns the modification date of a document. */
    public String getModificationDate(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetModificationDate(pdf)); 
    }

    /** Returns the XMP title of a document. */
    public String getTitleXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetTitleXMP(pdf)); 
    }

    /** Returns the XMP author of a document. */
    public String getAuthorXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetAuthorXMP(pdf)); 
    }

    /** Returns the XMP subject of a document. */
    public String getSubjectXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetSubjectXMP(pdf)); 
    }

    /** Returns the XMP keywords of a document. */
    public String getKeywordsXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetKeywordsXMP(pdf)); 
    }

    /** Returns the XMP creator of a document. */
    public String getCreatorXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreatorXMP(pdf)); 
    }

    /** Returns the XMP producer of a document. */
    public String getProducerXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetProducerXMP(pdf)); 
    }

    /** Returns the XMP creation date of a document. */
    public String getCreationDateXMP(Pdf pdf) throws CpdfError
    {
        return decodeUTF8(XgetCreationDateXMP(pdf)); 
    }

    /** Returns the XMP modification date of a document. */
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
    
    /** Sets the title of a document. */
    public void setTitle(Pdf pdf, String str) throws CpdfError
    {
        XsetTitle(pdf, encodeUTF8(str));
    }


    /** Sets the author of a document. */
    public void setAuthor(Pdf pdf, String str) throws CpdfError
    {
        XsetAuthor(pdf, encodeUTF8(str));
    }

    /** Sets the subject of a document. */
    public void setSubject(Pdf pdf, String str) throws CpdfError
    {
        XsetSubject(pdf, encodeUTF8(str));
    }

    /** Sets the keywords of a document. */
    public void setKeywords(Pdf pdf, String str) throws CpdfError
    {
        XsetKeywords(pdf, encodeUTF8(str));
    }

    /** Sets the creator of a document. */
    public void setCreator(Pdf pdf, String str) throws CpdfError
    {
        XsetCreator(pdf, encodeUTF8(str));
    }

    /** Sets the producer of a document. */
    public void setProducer(Pdf pdf, String str) throws CpdfError
    {
        XsetProducer(pdf, encodeUTF8(str));
    }
    
    /** Sets the creation date of a document. */
    public void setCreationDate(Pdf pdf, String str) throws CpdfError
    {
        XsetCreationDate(pdf, encodeUTF8(str));
    }
    
    /** Sets the modification date of a document. */
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
    
    /** Sets the XMP title of a document. */
    public void setTitleXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetTitleXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP author of a document. */
    public void setAuthorXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetAuthorXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP subject of a document. */
    public void setSubjectXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetSubjectXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP keywords of a document. */
    public void setKeywordsXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetKeywordsXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP creator of a document. */
    public void setCreatorXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetCreatorXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP producer of a document. */
    public void setProducerXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetProducerXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP creation date of a document. */
    public void setCreationDateXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetCreationDateXMP(pdf, encodeUTF8(str));
    }
    
    /** Sets the XMP modification date of a document. */
    public void setModificationDateXMP(Pdf pdf, String str) throws CpdfError
    {
        XsetModificationDateXMP(pdf, encodeUTF8(str));
    }
    
    /** Returns the components from a PDF date string in an array of length 8. */
    public native void getDateComponents(String datestring, int[] r) throws CpdfError;

    /** Builds a PDF date string from individual components. */
    public native String dateStringOfComponents(int year, int month, int day, int hour, int minute, int second, int hour_offset, int minute_offset) throws CpdfError;

    /** Gets the viewing rotation for a given page. */
    public native int getPageRotation(Pdf pdf, int pagenumber) throws CpdfError;

    /** Returns true, if that page has the given box. E.g "/CropBox". */
    public native boolean hasBox(Pdf pdf, int pagenumber, String boxname) throws CpdfError;

    /** These functions get a box given the document, page number, min x, max x,
    min y, max y in points. Only succeeds if such a box exists, as checked by
    hasBox. */
    public native void getMediaBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document, page number, min x, max x,
    min y, max y in points. Only succeeds if such a box exists, as checked by
    hasBox. */
    public native void getCropBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document, page number, min x, max x,
    min y, max y in points. Only succeeds if such a box exists, as checked by
    hasBox. */
    public native void getBleedBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document, page number, min x, max x,
    min y, max y in points. Only succeeds if such a box exists, as checked by
    hasBox. */
    public native void getArtBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document, page number, min x, max x,
    min y, max y in points. Only succeeds if such a box exists, as checked by
    hasBox. */
    public native void getTrimBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;

    /** These functions set a box given the document, page range, min x, max x, min y, max y in points. */
    public native void setMediabox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    
    /** These functions set a box given the document, page range, min x, max x, min y, max y in points. */
    public native void setCropBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    
    /** These functions set a box given the document, page range, min x, max x, min y, max y in points. */
    public native void setTrimBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    
    /** These functions set a box given the document, page range, min x, max x, min y, max y in points. */
    public native void setArtBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;
    
    /** These functions set a box given the document, page range, min x, max x, min y, max y in points. */
    public native void setBleedBox(Pdf pdf, Range range, double minx, double maxx, double miny, double maxy) throws CpdfError;


    /** Marks a document as trapped. */
    public native void markTrapped(Pdf pdf) throws CpdfError;
    
    /** Marks a document as untrapped. */
    public native void markUntrapped(Pdf pdf) throws CpdfError;
    
    /** Marks a document as trapped in XMP metadata. */
    public native void markTrappedXMP(Pdf pdf) throws CpdfError;

    /** Marks a document as untrapped in XMP metadata. */
    public native void markUntrappedXMP(Pdf pdf) throws CpdfError;

    /** Sets the page layout for a document. */
    public native void setPageLayout(Pdf pdf, int layout) throws CpdfError;
    
    /** Sets the page mode for a document. */
    public native void setPageMode(Pdf pdf, int mode) throws CpdfError;
    
    /** Sets the hide toolbar flag. */
    public native void hideToolbar(Pdf pdf, boolean flag) throws CpdfError;
    
    /** Sets the hide menubar flag. */
    public native void hideMenubar(Pdf pdf, boolean flag) throws CpdfError;

    /** Sets the hide window UI flag. */
    public native void hideWindowUi(Pdf pdf, boolean flag) throws CpdfError;
    
    /** Sets the fit window flag. */
    public native void fitWindow(Pdf pdf, boolean flag) throws CpdfError;

    /** Sets the center window flag. */
    public native void centerWindow(Pdf pdf, boolean flag) throws CpdfError;
    
    /** Sets the display doc title flag. */
    public native void displayDocTitle(Pdf pdf, boolean flag) throws CpdfError;

    /** Sets the PDF to open, possibly with zoom-to-fit, at the given page number. */
    public native void openAtPage(Pdf pdf, boolean fit, int pagenumber) throws CpdfError;

    native void XsetMetadataFromFile(Pdf pdf, byte[] filename) throws CpdfError;

    /** Sets the XMP metadata of a document, given a file name. */
    public void setMetadataFromFile(Pdf pdf, String filename) throws CpdfError
    {
        XsetMetadataFromFile(pdf, encodeUTF8(filename));
    }

    /** Sets the XMP metadata from an array of bytes. */
    public native void setMetadataFromByteArray(Pdf pdf, byte[] data) throws CpdfError;
    
    /** Removes the XMP metadata from a document. */
    public native void removeMetadata(Pdf pdf) throws CpdfError;
    
    /** Returns the XMP metadata from a document. */
    public native byte[] getMetadata(Pdf pdf) throws CpdfError;

    /** Builds fresh XMP metadata as best it can from
    existing metadata in the document. */
    public native void createMetadata(Pdf pdf) throws CpdfError;

    /** Sets the metadata date for a PDF. The date
    is given in PDF date format -- cpdf will convert it to XMP format. The
    date "now" means now. */
    public native void setMetadataDate(Pdf pdf, String date) throws CpdfError;

    native void XaddPageLabels(Pdf pdf, int style, byte[] prefix, int offset, Range range, boolean progress) throws CpdfError;
    
    /** Adds page labels. The prefix is prefix text for each label. The range is the page range the
    labels apply to. Offset can be used to shift the numbering up or down. */
    public void addPageLabels(Pdf pdf, int style, String prefix, int offset, Range range, boolean progress) throws CpdfError
    {
        XaddPageLabels(pdf, style, encodeUTF8(prefix), offset, range, progress);
    }

    /** Removes the page labels from the document. */
    public native void removePageLabels(Pdf pdf) throws CpdfError;
    
    native byte[] XgetPageLabelStringForPage(Pdf pdf, int n) throws CpdfError;
    
    /** Calculates the full label string for a given page, and returns it. */
    public String getPageLabelStringForPage(Pdf pdf, int n) throws CpdfError
    {
        return decodeUTF8(XgetPageLabelStringForPage(pdf, n));
    }
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int startGetPageLabels(Pdf pdf) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native void endGetPageLabels() throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int getPageLabelOffset(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int getPageLabelStyle(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    
    public native int getPageLabelRange(int n) throws CpdfError;
    
    native byte[] XgetPageLabelPrefix(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public String getPageLabelPrefix(int n) throws CpdfError
    {
        return decodeUTF8(XgetPageLabelPrefix(n));
    }


    /* CHAPTER 12. File Attachments */
    native void XattachFile(byte[] filename, Pdf pdf) throws CpdfError;
    native void XattachFileToPage(byte[] filename, Pdf pdf, int pagenumber) throws CpdfError;
    native void XattachFileFromMemory(byte[] data, byte[] filename, Pdf pdf) throws CpdfError;
    native void XattachFileToPageFromMemory(byte[] data, byte[] filename, Pdf pdf, int pagenumber) throws CpdfError;
    
    /** Attaches a file to the pdf. It is attached at document level. */
    public void attachFile(String filename, Pdf pdf) throws CpdfError
    {
        XattachFile(encodeUTF8(filename), pdf);
    }

    /** Attaches a file, given its file name, pdf, and the page number to which it should be attached. */
    public void attachFileToPage(String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPage(encodeUTF8(filename), pdf, pagenumber);
    }

    /** Attaches data from memory, just like attachFile. */
    public void attachFileFromMemory(byte[] data, String filename, Pdf pdf) throws CpdfError
    {
        XattachFileFromMemory(data, encodeUTF8(filename), pdf);
    }

    /** Attaches to a page from memory, just like attachFileToPage. */
    public void attachFileToPageFromMemory(byte[] data, String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPageFromMemory(data, encodeUTF8(filename), pdf, pagenumber);
    }

    /** Removes all page- and document-level attachments from a document. */
    public native void removeAttachedFiles(Pdf pdf) throws CpdfError;

    /** Lists information about attachments. Call startGetAttachments(pdf)
    first, then numberGetAttachments to find out how many there are. Then
    getAttachmentName etc. to return each one 0...(n - 1). Finally, call
    endGetAttachments to clean up. */
    public native void startGetAttachments(Pdf pdf) throws CpdfError;
    
    /** Lists information about attachments. Call startGetAttachments(pdf)
    first, then numberGetAttachments to find out how many there are. Then
    getAttachmentName etc. to return each one 0...(n - 1). Finally, call
    endGetAttachments to clean up. */
    public native int numberGetAttachments() throws CpdfError;
    
    /** Gets the name of an attachment. */
    public native String getAttachmentName(int serial) throws CpdfError;
    
    /** Gets the page number. 0 = document level. */
    public native int getAttachmentPage(int serial) throws CpdfError;
    
    /** Gets the attachment data itself. */
    public native byte[] getAttachmentData(int serial) throws CpdfError;
    
    /** Lists information about attachments. Call startGetAttachments(pdf)
    first, then numberGetAttachments to find out how many there are. Then
    getAttachmentName etc. to return each one 0...(n - 1). Finally, call
    endGetAttachments to clean up. */
    public native void endGetAttachments() throws CpdfError; 

    /* CHAPTER 13. Images. */
    
    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native int startGetImageResolution(Pdf pdf, double res) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native int getImageResolutionPageNumber(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native String getImageResolutionImageName(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native int getImageResolutionXPixels(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native int getImageResolutionYPixels(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native double getImageResolutionXRes(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native double getImageResolutionYRes(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    startGetImageResolution(pdf, min_required_resolution) will begin the
    process of obtaining data on all image uses below min_required_resolution,
    returning the total number. So, to return all image uses, specify a very
    high min_required_resolution. Then, call the other functions giving a
    serial number 0..n - 1, to retrieve the data. Finally, call
    endGetImageResolution to clean up. */
    public native void endGetImageResolution() throws CpdfError;

    /* CHAPTER 14. Fonts. */
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native void startGetFontInfo(Pdf pdf) throws CpdfError;

    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native int numberFonts() throws CpdfError;
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native String getFontName(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native int getFontPage(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native String getFontType(int setial) throws CpdfError;
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native String getFontEncoding(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call startGetFontInfo(pdf). Now
    call numberFonts to return the number of fonts. For each font, call
    one or more of getFontPage, getFontName, getFontType, and
    getFontEncoding giving a serial number 0..n - 1 to
    return information. Finally, call endGetFontInfo to clean up. */
    public native void endGetFontInfo() throws CpdfError;
    
    /** Removes all font data from a file. */
    public native void removeFonts(Pdf pdf) throws CpdfError;
    
    /** Copies the given font
    from the given page in the 'from' PDF to every page in the 'to' PDF. The
    new font is stored under its font name. */
    public native void copyFont(Pdf from_pdf, Pdf to_pdf, Range range, int pagenumber, String fontname) throws CpdfError;

    /* CHAPTER 15. PDF and JSON */
    native void XoutputJSON(byte[] filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError;
    
    /** Outputs a PDF
    in JSON format to the given filename. If parse_content is true, page content
    is parsed. If no_stream_data is true, all stream data is suppressed entirely. */
    public void outputJSON(String filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError
    {
        XoutputJSON(encodeUTF8(filename), parse_content, no_stream_data, decompress_streams, pdf);
    }

    /** Like outputJSON, but it writes to a byte array in memory. */
    public native byte[] outputJSONMemory(Pdf pdf, boolean parse_content, boolean no_stream_data, boolean decompress_streams) throws CpdfError;


    native Pdf XfromJSON(byte[] filename) throws CpdfError;
    
    /** Loads a PDF from a JSON file given its filename. */
    public Pdf fromJSON(String filename) throws CpdfError
    {
        return XfromJSON(encodeUTF8(filename));
    }

    /** Loads a PDF from a JSON file in memory */
    public native Pdf fromJSONMemory(byte[] data) throws CpdfError;
    
    /* CHAPTER 16. Optional Content Groups */
    
    /** Begins retrieving optional content group names. The serial number 0..n - 1 is returned. */
    public native int startGetOCGList(Pdf pdf) throws CpdfError;
    public native String OCGListEntry(int serial) throws CpdfError;
    public native void endGetOCGList() throws CpdfError;

    /** Renames an optional content group. */
    public native void OCGRename(Pdf pdf, String f, String t) throws CpdfError;
    
    /** Ensures that every optional content group appears in the OCG order list. */
    public native void OCGOrderAll(Pdf pdf) throws CpdfError;

    /** Coalesces optional content groups. For example, if we merge or stamp two
    files both with an OCG called "Layer 1", we will have two different optional
    content groups. This function will merge the two into a single optional
    content group. */
    public native void OCGCoalesce(Pdf pdf) throws CpdfError;

    /* CHAPTER 17. Creating New PDFs */
    
    /** Creates a blank document with
    pages of the given width (in points), height (in points), and number of
    pages. */
    public native Pdf blankDocument(double w, double h, int pages) throws CpdfError;

    /** Makes a blank document given a page size and number of pages. */
    public native Pdf blankDocumentPaper(int papersize, int pages) throws CpdfError;
    
    native Pdf XtextToPDF(double w, double h, int font, double fontsize, byte[] filename) throws CpdfError;
    native Pdf XtextToPDFPaper(int papersize, int font, double fontsize, byte[] filename) throws CpdfError;
    
    /** Typesets a UTF8 text file
    ragged right on a page of size w * h in points in the given font and font
    size. */
    public Pdf textToPDF(double w, double h, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDF(w, h, font, fontsize, encodeUTF8(filename));
    }
    
    /** Typesets a UTF8 text file ragged right on a page of the given size in the given font and font size. */
    public Pdf textToPDFPaper(int papersize, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDFPaper(papersize, font, fontsize, encodeUTF8(filename));
    }

    /* CHAPTER 18. Miscellaneous */
    
    /** Removes images on the given pages, replacing them with crossed boxes if 'boxes' is true. */
    public native void draft(Pdf pdf, Range range, boolean boxes) throws CpdfError;
    
    /** Removes all text from the given pages in a given document. */
    public native void removeAllText(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all text on the given pages. */
    public native void blackText(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all lines on the given pages. */
    public native void blackLines(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all fills on the given pages. */
    public native void blackFills(Pdf pdf, Range range) throws CpdfError;
    
    /** Thickens every line less than min_thickness to min_thickness. Thickness given in points. */
    public native void thinLines(Pdf pdf, Range range, double minwidth) throws CpdfError;
    
    /** Copies the /ID from one document to another. */
    public native void copyId(Pdf pdf, Pdf pdf2) throws CpdfError;
    
    /** Removes a document's /ID. */
    public native void removeId(Pdf pdf) throws CpdfError;
    
    /** Sets the minor version number of a document. */
    public native void setVersion(Pdf pdf, int version) throws CpdfError;
    
    /** Sets the full version number of a document. */
    public native void setFullVersion(Pdf pdf, int major, int minor) throws CpdfError;
    
    /** Removes any dictionary entry with the given key anywhere in the document. */
    public native void removeDictEntry(Pdf pdf, String str) throws CpdfError;
    
    /** Removes any dictionary entry with the given key whose value matches the given search term. */
    public native void removeDictEntrySearch(Pdf pdf, String str, String searchterm) throws CpdfError;
    
    /** Replaces the value associated with the given key. */
    public native void replaceDictEntry(Pdf pdf, String key, String newvalue) throws CpdfError;
    
    /** Replaces the value associated with the given key if the existing value matches the search term. */
    public native void replaceDictEntrySearch(Pdf pdf, String key, String newvalue, String searchterm) throws CpdfError;
    
    /** Removes all clipping from pages in the given range. */
    public native void removeClipping(Pdf pdf, Range range) throws CpdfError;
    
    /** Returns a JSON array containing any and all values associated with the given key, and fills in its length. */
    public native byte[] getDictEntries(Pdf pdf, String key) throws CpdfError;
}
