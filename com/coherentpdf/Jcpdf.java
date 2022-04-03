package com.coherentpdf;
import java.nio.charset.Charset;

/** The Coherent PDF Library for Java */
public class Jcpdf {

    /** Create a new instance of the Jcpdf library. You must use <code>{@link
     #startup() startup()}</code> too, before calling any other function.*/
    public Jcpdf()
    {
      System.loadLibrary("cpdf");
      System.loadLibrary("jcpdf");
    }
    
    /** Any function in this library may raise the CPDFError exception, which carries a string describing the nature of the problem. */
    public static class CpdfError extends Exception
    {
      public CpdfError(String errorMessage)
      {
        super(errorMessage);
      }
    }

    /** PDF document
     *
     * <p>Use the <code>try</code> keyword, or call <code>close()</code>
     * to make sure PDFs are deallocated. */
    public class Pdf implements AutoCloseable
    {
      int pdf = -1;
      private Pdf(int pdf)
      {
        this.pdf = pdf;
      }
      public void close()
      {
        deletePdf(pdf);
      }
    }

    /** Page range
     *
     * <p>Use the <code>try</code> keyword, or call <code>close()</code> to
     * make sure ranges are deallocated. */
    public class Range implements AutoCloseable
    {
      int range = -1;
      private Range(int range)
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
    public static int noEdit = 0;
    /** Permission: cannot print the document */
    public static int noPrint = 1;
    /** Permission: cannot copy the document */
    public static int noCopy = 2;
    /** Permission: cannot annotate the document */
    public static int noAnnot = 3;
    /** Permission: cannot edit forms in the document */
    public static int noForms = 4;
    /** Permission: cannot extract information */
    public static int noExtract = 5;
    /** Permission: cannot assemble into a bigger document */
    public static int noAssemble = 6;
    /** Permission: cannot print high quality */
    public static int noHqPrint = 7;

    /** Encryption method: 40 bit RC4 encryption */
    public static int pdf40bit = 0;
    /** Encryption method: 128 bit RC4 encryption */
    public static int pdf128bit = 1;
    /** Encryption method: 128 bit AES encryption, do not encrypt metadata */
    public static int aes128bitfalse = 2;
    /** Encryption method: 128 bit AES encryption, encrypt metadata */
    public static int aes128bittrue = 3;
    /** Encryption method: Deprecated. Do not use for new files */
    public static int aes256bitfalse = 4;
    /** Encryption method: Deprecated. Do not use for new files */
    public static int aes256bittrue = 5;
    /** Encryption method: 256 bit AES encryption, do not encrypt metadata */
    public static int aes256bitisofalse = 6;
    /** Encryption method: 256 bit AES encryption, encrypt metadata */
    public static int aes256bitiso = 7;

    /** Page label style: 1, 2, 3... */
    public static int decimalArabic = 0;
    /** Page label style: I, II, III... */
    public static int uppercaseRoman = 1;
    /** Page label style: i, ii, iii... */
    public static int lowercaseRoman = 2;
    /** Page label style: A, B, C... */
    public static int uppercaseLetters = 3;
    /** Page label style: a, b, c...*/
    public static int lowercaseLetters = 4;

    /** Layout: single page */
    public static int singlePage = 0;
    /** Layout: one column */
    public static int oneColumn = 1;
    /** Layout: two column left */
    public static int twoColumnLeft = 2;
    /** Layout: two column right */
    public static int twoColumnRight = 3;
    /** Layout: two page left */
    public static int twoPageLeft = 4;
    /** Layout: two page right */
    public static int twoPageRight = 5;

    /** Page mode: use none */
    public static int useNone = 0;
    /** Page mode: use outlines */
    public static int useOutlines = 1;
    /** Page mode: use thumbs */
    public static int useThumbs = 2;
    /** Page mode: use OC */
    public static int useOC = 3;
    /** Page mode: use Attachments */
    public static int useAttachments = 4;

    /** Paper size: A0 Portrait */
    public static int a0portrait = 0;
    /** Paper size: A1 Portrait */
    public static int a1portrait = 1;
    /** Paper size: A2 Portrait */
    public static int a2portrait = 2;
    /** Paper size: A3 Portrait */
    public static int a3portrait = 3;
    /** Paper size: A4 Portrait */
    public static int a4portrait = 4;
    /** Paper size: A5 Portrait */
    public static int a5portrait = 5;
    /** Paper size: A0 Landscape */
    public static int a0landscape = 6;
    /** Paper size: A1 Landscape */
    public static int a1landscape = 7;
    /** Paper size: A2 Landscape */
    public static int a2landscape = 8;
    /** Paper size: A3 Landscape */
    public static int a3landscape = 9;
    /** Paper size: A4 Landscape */
    public static int a4landscape = 10;
    /** Paper size: A5 Landscape */
    public static int a5landscape = 11;
    /** Paper size: US Letter Portrait */
    public static int usletterportrait = 12;
    /** Paper size: US Letter Landscape */
    public static int usletterlandscape = 13;
    /** Paper size: US Legal Portrait */
    public static int uslegalportrait = 14;
    /** Paper size: US Legal Landscape */
    public static int uslegallandscape = 15;

    /** Standard font: Times Roman */
    public static int timesRoman = 0;
    /** Standard font: Times Bold */
    public static int timesBold = 1;
    /** Standard font: Times Italic */
    public static int timesItalic = 2;
    /** Standard font: Times Bold Italic */
    public static int timesBoldItalic = 3;
    /** Standard font: Helvetica */
    public static int helvetica = 4;
    /** Standard font: Helvetica Bold */
    public static int helveticaBold = 5;
    /** Standard font: Helvetica Oblique */
    public static int helveticaOblique = 6;
    /** Standard font: Helvetica Bold Oblique */
    public static int helveticaBoldOblique = 7;
    /** Standard font: Courier */
    public static int courier = 8;
    /** Standard font: Courier Bold */
    public static int courierBold = 9;
    /** Standard font: Courier Oblique */
    public static int courierOblique = 10;
    /** Standard font: Courier Bold Oblique */
    public static int courierBoldOblique = 11;

    /** Position anchor: absolute centre. Takes two numbers, x and y. */
    public static int posCentre = 0;
    /** Position anchor: absolute left. Takes two numbers, x and y. */
    public static int posLeft = 1;
    /** Position anchor: absolute right. Takes two numbers, x and y. */
    public static int posRight = 2;
    /** Position anchor: the top centre of the page. Takes one number - distance from top. Second number ignored. */
    public static int top = 3;
    /** Position anchor: the top left of the page. Takes one numbers - distance from top left. Second number ignored. */
    public static int topLeft = 4;
    /** Position anchor: the top right of the page. Takes one number - distance from top right. Second number ignored. */
    public static int topRight = 5;
    /** Position anchor: the left hand side of the page, halfway down. Takes one number - distance from left middle. Second number ignored. */
    public static int left = 6;
    /** Position anchor: the bottom left of the page. Takes one number - distance from bottom left. Second number ignored. */
    public static int bottomLeft = 7;
    /** Position anchor: the bottom middle of the page. Takes one number - distance from bottom middle. Second number ignored. */
    public static int bottom = 8;
    /** Position anchor: the bottomm right of the page. Takes one number - distance from bottom right. Second number ignored. */
    public static int bottomRight = 9;
    /** Position anchor: the right hand side of the page, halfway down. Takes one number - distance from right middle. Second number ignored. */
    public static int right = 10;
    /** Position anchor: diagonal, bottom left to top right. Takes no numbers. Both numbers ignored. */
    public static int diagonal = 11;
    /** Position anchor: diagonal, top left to bottom right. Takes no numbers. Both numbers ignored. */
    public static int reverseDiagonal = 12;

    /** Justification: left */
    public static int leftJustify = 0;
    /** Justification: centre */
    public static int centreJustify = 1;
    /** Justification: right */
    public static int rightJusitfy = 2;

    /* CHAPTER 0. Preliminaries */
    
    native void deletePdf(int pdf);
    native void deleteRange(int range);

    /** Initialises the library. Must be called before any other function. */
    public native void startup() throws CpdfError;
    
    /** Returns a string giving the version number of the Jcpdf library.
    @return version number of the Jcpdf library */
    public native String version() throws CpdfError;
    
    /** Sets fast mode. Some operations have a fast mode. The default is 'slow' mode, which works
    even on old-fashioned files. For more details, see section 1.13 of the
    CPDF manual. This functions sets the mode to fast globally. */
    public native void setFast() throws CpdfError;

    /** Sets slow mode. Some operations have a fast mode. The default is 'slow' mode, which works
    even on old-fashioned files. For more details, see section 1.13 of the
    CPDF manual. This functions sets the mode to slow globally. */
    public native void setSlow() throws CpdfError;
    
    /** Prints some information about
    resource usage. This can be used to detect if PDFs or ranges are being
    deallocated properly. Contrary to its name, it may be run at any time. */
    public native void onExit();

    /* CHAPTER 1. Basics */
    native Pdf XfromFile(byte[] filename, byte[] userpw) throws CpdfError;
    /** Loads a PDF document from a file. Supply
    a user password (possibly blank) in case the file is encrypted. It won't be
    decrypted, but sometimes the password is needed just to load the file.
    @param filename file name
    @param userpw user password
    @return PDF document*/
    public Pdf fromFile(String filename, String userpw) throws CpdfError
    { 
      return XfromFile(encodeUTF8(filename), encodeUTF8(userpw));
    }

    native Pdf XfromFileLazy(byte[] filename, byte[] userpw) throws CpdfError;
    
    /** Loads a PDF document from a file, doing only minimal
    parsing. The objects will be read and parsed when they are actually
    needed. Use this when the whole file won't be required. Also supply a user
    password (possibly blank) in case the file is encrypted. It won't be
    decrypted, but sometimes the password is needed just to load the file.

    @param filename file name
    @param userpw user password
    @return PDF document */
    public Pdf fromFileLazy(String filename, String userpw) throws CpdfError
    {
      return XfromFileLazy(encodeUTF8(filename), encodeUTF8(userpw));
    }


    native Pdf XfromMemory(byte[] data, byte[] userpw) throws CpdfError;

    /** Loads a PDF document from memory. Supply
    a user password (possibly blank) in case the file is encrypted. It won't be
    decrypted, but sometimes the password is needed just to load the file.

    @param data byte array containing the PDF file
    @param userpw user password
    @return PDF document */
    public Pdf fromMemory(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemory(data, encodeUTF8(userpw));
    }

    native Pdf XfromMemoryLazy(byte[] data, byte[] userpw) throws CpdfError;
    
    /** Loads a file from memory and the user
    password, but lazily like {@link #fromFileLazy(String, String) fromFileLazy}. The caller must use
    {@link #fromMemoryLazyRelease(byte[]) fromMemoryLazyRelease} to free the memory. It must not free the memory
    until the PDF is also gone. */
    public Pdf fromMemoryLazy(byte[] data, String userpw) throws CpdfError
    {
        return XfromMemoryLazy(data, encodeUTF8(userpw));
    }
    
    /** Releases memory returned from <code>{@link #fromMemoryLazy(byte[], String) fromMemoryLazy}</code>
    @param data byte array previously passed to {@link #fromMemoryLazy(byte[], String) fromMemoryLazy} */
    public native void fromMemoryLazyRelease(byte[] data) throws CpdfError;

    /** Begins enumerating currently allocated PDFs.
    
    <p>To enumerate the list of currently allocated PDFs, call
    {@link #startEnumeratePDFs() startEnumeratePDFs} which gives the number, <code>n</code>, of PDFs allocated, then
    {@link #enumeratePDFsInfo(int) enumeratePDFsInfo} and {@link #enumeratePDFsKey(int) enumeratePDFsKey} with index numbers from
    <code>0...(n - 1)</code>. Call {@link #endEnumeratePDFs() endEnumeratePDFs} to clean up. */
    public native int startEnumeratePDFs() throws CpdfError;
    
    /** Returns the key for a given PDF number. 
    
    <p>To enumerate the list of currently allocated PDFs, call
    {@link #startEnumeratePDFs() startEnumeratePDFs} which gives the number, <code>n</code>, of PDFs allocated, then
    {@link #enumeratePDFsInfo(int) enumeratePDFsInfo} and {@link #enumeratePDFsKey(int) enumeratePDFsKey} with index numbers from
    <code>0...(n - 1)</code>. Call {@link #endEnumeratePDFs() endEnumeratePDFs} to clean up. */
    public native int enumeratePDFsKey(int n) throws CpdfError;
    
    /** Returns the info for a given PDF number.
    
    <p>To enumerate the list of currently allocated PDFs, call
    {@link #startEnumeratePDFs() startEnumeratePDFs} which gives the number, <code>n</code>, of PDFs allocated, then
    {@link #enumeratePDFsInfo(int) enumeratePDFsInfo} and {@link #enumeratePDFsKey(int) enumeratePDFsKey} with index numbers from
    <code>0...(n - 1)</code>. Call {@link #endEnumeratePDFs() endEnumeratePDFs} to clean up. */
    public native String enumeratePDFsInfo(int n) throws CpdfError;
    
    /** Ends enumeration of currently allocated PDFs.
    
    <p>To enumerate the list of currently allocated PDFs, call
    {@link #startEnumeratePDFs() startEnumeratePDFs} which gives the number, <code>n</code>, of PDFs allocated, then
    {@link #enumeratePDFsInfo(int) enumeratePDFsInfo} and {@link #enumeratePDFsKey(int) enumeratePDFsKey} with index numbers from
    <code>0...(n - 1)</code>. Call {@link #endEnumeratePDFs() endEnumeratePDFs} to clean up. */
    public native void endEnumeratePDFs() throws CpdfError;


    /** Converts a figure in centimetres to points. (72 points to 1 inch) */
    public native double ptOfCm(double f) throws CpdfError;
    
    /** Converts a figure in millimetres to points. (72 points to 1 inch) */
    public native double ptOfMm(double f) throws CpdfError;
    
    /** Converts a figure in inches to points (72. points to 1 inch) */
    public native double ptOfIn(double f) throws CpdfError;
    
    /** Converts a figure in points to centimetres. (72 points to 1 inch) */
    public native double cmOfPt(double f) throws CpdfError;
    
    /** Converts a figure in points to millimetres. (72 points to 1 inch) */
    public native double mmOfPt(double f) throws CpdfError;

    /** Converts a figure in points to millimetres. (72 points to 1 inch) */
    public native double inOfPt(double f) throws CpdfError;

    /** Parses a page specification with reference
    to a given PDF. (The PDF is supplied so that page ranges which reference
    pages which do not exist are rejected).

    @param pdf PDF document
    @param pagespec page specification
    @return page range */
    public native Range parsePagespec(Pdf pdf, String pagespec) throws CpdfError;
    
    /** Validates a page specification so far as is
    possible in the absence of the actual document. Result is <code>true</code> if valid. */
    public native boolean validatePagespec(String pagespec) throws CpdfError;

    native byte[] XstringOfPagespec(Pdf pdf, Range r) throws CpdfError;
    
    /** Builds a page specification from a page
    range. For example, the range containing 1,2,3,6,7,8 in a document of 8
    pages might yield <code>"1-3,6-end"</code>

    @param pdf PDF document
    @param r page range
    @return page range in string form
    */
    public String stringOfPagespec(Pdf pdf, Range r) throws CpdfError
    {
        return decodeUTF8(XstringOfPagespec(pdf, r));
    }

    /** The range containing no pages. */
    public native Range blankRange() throws CpdfError;

    /** The page range containing all page numbers from one page number to another.
    @param from page number to begin at (inclusive)
    @param to page number to end at (inclusive) */
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

    /** Gets a page number from a range at the given offset. */
    public native int rangeGet(Range r, int n) throws CpdfError;
    
    /** Adds a page number to a range, returning a new one. */
    public native Range rangeAdd(Range r, int n) throws CpdfError;

    /** Tests to see if a given number is in a page range. */
    public native boolean isInRange(Range r, int n) throws CpdfError;

    /** Returns the number of pages in a PDF. */
    public native int pages(Pdf pdf) throws CpdfError;

    native int XpagesFast(byte[] userpw, byte[] filename) throws CpdfError;

    /** Returns the number of pages in a given
    PDF, with given user password. It tries to do this as fast as
    possible, without loading the whole file.
    @param userpw user password
    @param filename file name */
    public int pagesFast(String userpw, String filename) throws CpdfError
    {
        return XpagesFast(encodeUTF8(userpw), encodeUTF8(filename));
    }

    native void XtoFile(Pdf pdf, byte[] filename, boolean linearize, boolean make_id) throws CpdfError;
    
    /** Writes the PDF document to a given
    filename. If <code>linearize</code> is <code>true</code>, it will be linearized if a linearizer is
    available. If <code>make_id</code> is <code>true</code>, it will be given a new ID.
    @param pdf PDF document
    @param filename file name
    @param linearize linearize
    @param make_id make new ID
    */
    public void toFile(Pdf pdf, String filename, boolean linearize, boolean make_id) throws CpdfError
    {
        XtoFile(pdf, encodeUTF8(filename), linearize, make_id);
    }
    
    native void XtoFileExt(Pdf pdf, byte[] filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError;

    /** Writes the PDF document to a given filename, with extra parameters. If
    <code>make_id</code> is true, it will be given a new ID.  If <code>preserve_objstm</code> is true,
    existing object streams will be preserved. If <code>generate_objstm</code> is true,
    object streams will be generated even if not originally present. If
    <code>compress_objstm</code> is true, object streams will be compressed (what we
    usually want). WARNING: the pdf argument will be invalid after this call,
    and should be not be used again.
    @param pdf PDF document
    @param filename file name
    @param linearize linearize
    @param make_id make new ID
    @param preserve_objstm preserve object streams
    @param create_objstm create new object streams
    @param compress_objstm compress object streams
    */
    public void toFileExt(Pdf pdf, String filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm) throws CpdfError
    {
        XtoFileExt(pdf, encodeUTF8(filename), linearize, make_id, preserve_objstm, create_objstm, compress_objstm);
    }

    /** Writes a PDF document and returns it as an array of bytes.
    @param pdf PDF document
    @param linearize linearize
    @param make_id make new ID
    @return array of bytes containing PDF file
    */
    public native byte[] toMemory(Pdf pdf, boolean linearize, boolean make_id) throws CpdfError;

    /** Returns <code>true</code> if a document is encrypted, <code>false</code> otherwise. */
    public native boolean isEncrypted(Pdf pdf) throws CpdfError;
    
    native void XdecryptPdf(Pdf pdf, byte[] userpw) throws CpdfError;
    /** Attempts to decrypt a PDF using the given
    user password. An exception is raised if the decryption fails.
    @param pdf PDF document
    @param userpw user password */
    public void decryptPdf(Pdf pdf, String userpw) throws CpdfError
    {
        XdecryptPdf(pdf, encodeUTF8(userpw));
    }

    native void XdecryptPdfOwner(Pdf pdf, byte[] ownerpw) throws CpdfError;

    /** Attempts to decrypt a PDF using the given owner password. Raises an
    exception if the decryption fails.
    @param pdf PDF document
    @param ownerpw owner password */
    public void decryptPdfOwner(Pdf pdf, String ownerpw) throws CpdfError
    {
        XdecryptPdfOwner(pdf, encodeUTF8(ownerpw));
    }

    native void XtoFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, byte[] filename) throws CpdfError;

    /** Writes a PDF document as encrypted. The encryption method and permissions are drawn from Jcpdf's fields, documented above.
    @param pdf PDF document
    @param encryption_method encryption method
    @param permissions array of permissions
    @param owner_password owner password
    @param user_password user password
    @param linearize linearize
    @param makeid make new ID
    @param filename file name */
    public void toFileEncrypted(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, String filename) throws CpdfError
    {
        XtoFileEncrypted(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, encodeUTF8(filename));
    }

    native void XtoFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, byte[] owner_password, byte[] user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, byte[] filename) throws CpdfError;

    /** Writes a file as encrypted with extra parameters. WARNING: the pdf
     argument will be invalid after this call, and should not be used again.

    @param pdf PDF document
    @param encryption_method encryption method
    @param permissions array of permissions
    @param owner_password owner password
    @param user_password user password
    @param linearize linearize
    @param makeid make new ID
    @param preserve_objstm preserve existing object streams
    @param generate_objstm generate new object streams
    @param compress_objstm compress object streams
    @param filename file name */
    public void toFileEncryptedExt(Pdf pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, String filename) throws CpdfError
    {
        XtoFileEncryptedExt(pdf, encryption_method, permissions, encodeUTF8(owner_password), encodeUTF8(user_password), linearize, makeid, preserve_objstm, generate_objstm, compress_objstm, encodeUTF8(filename));
    }

    /** Returns <code>true</code> if the given permission (restriction) is present. */
    public native boolean hasPermission(Pdf pdf, int permission) throws CpdfError;
    
    /** Returns the encryption method currently in use on a document. */
    public native int encryptionKind(Pdf pdf) throws CpdfError;

    /* CHAPTER 2. Merging and Splitting */
    
    /** Given an array of PDFs, merges the documents into a new one, which is returned. */
    public native Pdf mergeSimple(Pdf[] pdfs) throws CpdfError;
    
    /** Merges the PDFs. If <code>retain_numbering</code> is true page labels are not rewritten. If
    <code>remove_duplicate_fonts</code> is true, duplicate fonts are merged. This is useful
    when the source documents for merging originate from the same source.
    @param pdfs array of PDF documents
    @param retain_numbering retain page numbering in output
    @param remove_duplicate_fonts remove duplicate font data by merging */
    public native Pdf merge(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts) throws CpdfError;
    
    /** Merges PDFs when one or more are drawn from the same document. It has an additional
    argument - a list of page ranges. This is used to select the pages to
    pick from each PDF. This avoids duplication of information when multiple
    discrete parts of a source PDF are included.
    @param pdfs array of PDF documents
    @param retain_numbering retain page numbering in output
    @param remove_duplicate_fonts remove duplicate font data by merging
    @param ranges array of ranges, one for each PDF*/
    public native Pdf mergeSame(Pdf[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts, Range[] ranges) throws CpdfError;
    
    /** Returns a new document with just those pages in the page range.
     *  @param pdf PDF document
     *  @param range range*/
    public native Pdf selectPages(Pdf pdf, Range range) throws CpdfError;

    /* CHAPTER 3. Pages */
    
    /** Scales the page dimensions
    and content by the given scale, about (0, 0). Other boxes (crop etc. are
    altered as appropriate).
    @param pdf PDF document
    @param range page range
    @param sx X scale
    @param sy Y scale*/
    public native void scalePages(Pdf pdf, Range range, double sx, double sy) throws CpdfError;

    /** Scales the content to fit
    new page dimensions (width x height) multiplied by scale (typically 1.0).
    Other boxes (crop etc. are altered as appropriate).
    @param pdf PDF document
    @param range page range
    @param w width in points
    @param h height in points
    @param scale scale (typically 1.0)
    */
    public native void scaleToFit(Pdf pdf, Range range, double w, double h, double scale) throws CpdfError;

    /** Scales the page content to fit the given page size, possibly multiplied by scale (typically 1.0).
    @param pdf PDF document
    @param range page range
    @param papersize paper size
    @param scale scale (typically 1.0)
    */
    public native void scaleToFitPaper(Pdf pdf, Range range, int papersize, double scale) throws CpdfError;

    /** Scales the contents of the pages in the range about the point given by the <code>anchor</code>,
    <code>p1</code> and <code>p2</code> by the scale given. See the documentation for the chosen anchor.
    @param pdf PDF document
    @param range page range
    @param anchor position anchor
    @param p1 position parameter 1
    @param p2 position parameter 2
    @param scale scale
    */
    public native void scaleContents(Pdf pdf, Range range, int anchor, double p1, double p2, double scale) throws CpdfError;

    /** Shifts the content of the pages in the range.
    @param pdf PDF document
    @param range page range
    @param dx X shift
    @param dy Y shift
    */
    public native void shiftContents(Pdf pdf, Range range, double dx, double dy) throws CpdfError;

    /** Changes the viewing rotation to an absolute value. Appropriate rotations are 0, 90, 180, 270.
    @param pdf PDF document
    @param range page range
    @param angle viewing rotation
    */
    public native void rotate(Pdf pdf, Range range, int angle) throws CpdfError;

    /** Changes the viewing rotation by a relative value. Appropriate rotations are 0, 90, 180, 270.
    @param pdf PDF document
    @param range page range
    @param angle viewing rotation
    */
    public native void rotateBy(Pdf pdf, Range range, int angle) throws CpdfError;

    /** Rotates the content about the centre of the page by the given number of degrees, in a clockwise
    direction.
    @param pdf PDF document
    @param range page range
    @param angle angle in degrees
    */
    public native void rotateContents(Pdf pdf, Range range, double angle) throws CpdfError;

    /** Changes the viewing rotation of the pages in the
    range, counter-rotating the dimensions and content such that there is no
    visual change. */
    public native void upright(Pdf pdf, Range range) throws CpdfError;

    /** Flips horizontally the pages in the range. */
    public native void hFlip(Pdf pdf, Range range) throws CpdfError;

    /** Flips vertically the pages in the range. */
    public native void vFlip(Pdf pdf, Range range) throws CpdfError;

    /** Crops a page, replacing any existing crop box. The dimensions are in points.
    @param pdf PDF document
    @param range page range
    @param x minimum X
    @param y minimum Y
    @param w width
    @param h height */
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

    /** Makes a given box a 'hard box' i.e clips it explicitly.
    @param pdf PDF document
    @param range page range
    @param box box name e.g "/CropBox" */
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

    /** Gets the number of bookmarks for the PDF given to {@link #startGetBookmarkInfo(Pdf) startGetBookmarkInfo}. */
    public native int numberBookmarks() throws CpdfError;
    
    /** Gets the bookmark level for the given bookmark <code>0...(n - 1)</code>. */
    public native int getBookmarkLevel(int serial) throws CpdfError;
    
    /** Gets the bookmark target page for the given PDF
    (which must be the same as the PDF passed to  {@link #startGetBookmarkInfo(Pdf) startGetBookmarkInfo})
    and bookmark <code>0...(n - 1)</code>. */
    public native int getBookmarkPage(Pdf pdf, int serial) throws CpdfError;

    native byte[] XgetBookmarkText(int serial) throws CpdfError;
    
    /** Returns the text of bookmark <code>0...(n - 1))</code> */
    public String getBookmarkText(int serial) throws CpdfError
    {
        return decodeUTF8(XgetBookmarkText(serial));
    }

    /** Returns <code>true</code> if the bookmark is open. */
    public native boolean getBookmarkOpenStatus(int serial) throws CpdfError;

    /** Ends the bookmark retrieval process, cleaning up. */
    public native void endGetBookmarkInfo() throws CpdfError;
    
    /** Starts the bookmark setting process for n bookmarks. */
    public native void startSetBookmarkInfo(int n) throws CpdfError;

    /** Set bookmark level for the given bookmark <code>0...(n - 1)</code>. */
    public native void setBookmarkLevel(int serial, int level) throws CpdfError;

    /** Sets the bookmark target
    page for the given PDF (which must be the same as the PDF to be passed to
    {@link #endSetBookmarkInfo(Pdf) endSetBookmarkInfo}) and bookmark <code>0...(n - 1)</code>. */
    public native void setBookmarkPage(Pdf pdf, int serial, int pagenum) throws CpdfError;
    
    /** Sets the open status of bookmark <code>0...(n - 1)</code>. */
    public native void setBookmarkOpenStatus(int serial, boolean open) throws CpdfError;

    native void XsetBookmarkText(int serial, byte[] text) throws CpdfError;
    /** Sets the text of bookmark <code>0...(n - 1)</code>. */
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
    of contents from existing bookmarks and prepends it to the document.
    @param pdf PDF document
    @param font font
    @param fontsize font size
    @param title table of contents title
    @param bookmark if <code>true</code>, the table of contents gets its own bookmark. */
    public void tableOfContents(Pdf pdf, int font, double fontsize, String title, boolean bookmark) throws CpdfError
    {
        XtableOfContents(pdf, font, fontsize, encodeUTF8(title), bookmark);
    }

    /* CHAPTER 7. Presentations */
    /* Not included in the library version. */

    /* CHAPTER 8. Logos, Watermarks and Stamps */
   
    /** Stamps another PDF on top of all the
    pages in the document which are in the range. The stamp is placed with its
    origin at the origin of the target document.
    @param stamp_pdf stamp PDF document
    @param pdf PDF document
    @param range page range */
    public native void stampOn(Pdf stamp_pdf, Pdf pdf, Range range) throws CpdfError;

    /** Stamps another PDF under all the
    pages in the document which are in the range. The stamp is placed with its
    origin at the origin of the target document.
    @param stamp_pdf stamp PDF document
    @param pdf PDF document
    @param range page range */
    public native void stampUnder(Pdf stamp_pdf, Pdf pdf, Range range) throws CpdfError;

    /** A stamping function with extra features.
     * @param pdf first PDF document
     * @param pdf2 second PDF document
     * @param range page range
     * @param isover if <code>true</code>, <code>pdf</code> goes over <code>pdf2</code> otherwise under
     * @param scale_stamp_to_fit if <code>true</code> scales the stamp to fit the page.
     * @param anchor position anchor
     * @param p1 position parameter one
     * @param p2 position parameter two
     * @param relative_to_cropbox if <code>true</code>, the position is relative to the crop box rather than the media box. */
    public native void stampExtended(Pdf pdf, Pdf pdf2, Range range, boolean isover, boolean scale_stamp_to_fit, int anchor, double p1, double p2, boolean relative_to_cropbox) throws CpdfError;
    
    /** Combines the two PDFs page-by-page, putting each page of 'over' over each page of 'under'. */
    public native Pdf combinePages(Pdf under, Pdf over) throws CpdfError;
    
    native void XaddText(boolean metrics, Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, byte[] filename, double linewidth, boolean embed_fonts) throws CpdfError;

    /** Adds text to the pages in the given range. See <a href="https://www.coherentpdf.com/jcpdfmanual.pdf">the PDF manual</a> for details.
     * @param metrics if <code>true</code>, only collect metrics
     * @param pdf PDF document
     * @param range page range
     * @param text the text to stamp, including any special codes
     * @param anchor position anchor
     * @param p1 position parameter one
     * @param p2 position parameter two
     * @param linespacing line spacing
     * @param bates starting bates number
     * @param font font 
     * @param fontsize font size
     * @param r red component of colour
     * @param g green component of colour
     * @param b blue component of colour
     * @param underneath if <code>true</code>, text goes under page
     * @param cropbox if <code>true</code>, relative to cropbox rather than media box
     * @param outline text is outline
     * @param opacity opacity
     * @param justification justification
     * @param midline position is relative to midline not baseline
     * @param topline position is relative to topline not baseline
     * @param filename file name, if requied by special code in text
     * @param linewidth line width
     * @param embed_fonts if true, embed fonts. Requires external program.
    */
    public void addText(boolean metrics, Pdf pdf, Range range, String text, int anchor, double p1, double p2, double linespacing, int bates, int font, double fontsize, double r, double g, double b, boolean underneath, boolean cropbox, boolean outline, double opacity, int justification, boolean midline, boolean topline, String filename, double linewidth, boolean embed_fonts) throws CpdfError
    {
        XaddText(metrics, pdf, range, encodeUTF8(text), anchor, p1, p2, linespacing, bates, font, fontsize,
                r, g, b, underneath, cropbox, outline, opacity, justification, midline, topline, encodeUTF8(filename), linewidth, embed_fonts);
    }
    native void XaddTextSimple(Pdf pdf, Range range, byte[] text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError;
    
    /** Adds text with most parameters default.
     * @param pdf PDF document
     * @param range page range
     * @param text the text to stamp, including any special codes
     * @param anchor position anchor
     * @param p1 position parameter one
     * @param p2 position parameter two
     * @param font font 
     * @param fontsize font size
     * */
    public void addTextSimple(Pdf pdf, Range range, String text, int anchor, double p1, double p2, int font, double fontsize) throws CpdfError
    {
        XaddTextSimple(pdf, range, encodeUTF8(text), anchor, p1, p2, font, fontsize);
    }
    
    /** Removes any text added by Jcpdf from the given pages. */
    public native void removeText(Pdf pdf, Range range) throws CpdfError;

    native int XtextWidth(int font, byte[] text) throws CpdfError;
    
    /** Returns the width of a given string in the given font in thousandths of a point.
     * @param font font
     * @param text text*/
    public int textWidth(int font, String text) throws CpdfError
    {
        return XtextWidth(font, encodeUTF8(text));
    }

    native void XaddContent(byte[] s, boolean before, Pdf pdf, Range range) throws CpdfError;
    
    /** Adds page content before or after the existing content to pages in the given range
    in the given PDF.
    @param s page content to add
    @param before if <code>true</code> new content goes before, else after
    @param pdf PDF document
    @param range page range */
    public void addContent(String s, boolean before, Pdf pdf, Range range) throws CpdfError
    {
        XaddContent(encodeUTF8(s), before, pdf, range);
    }

    /** Stamps a PDF onto the pages
    in the given range in pdf as a shared Form XObject. The name of the
    newly-created XObject is returned.
    @param pdf PDF document
    @param range page range
    @param stamp_pdf PDF document to stamp */
    public native String stampAsXObject(Pdf pdf, Range range, Pdf stamp_pdf) throws CpdfError;
    
    /* CHAPTER 9. Multipage facilities */
    
    /** Imposes a PDF.
     @param pdf PDF document
     @param x x parameter
     @param y y parameter
     @param fit true: impose to fit a page x * y; false: impose x * y 
     @param columns imposes by columns rather than rows
     @param rtl impose right-to-left
     @param btt impose bottom-to-top
     @param center unused for now
     @param margin margin around the output
     @param spacing spacing between imposed inputs */
    public native void impose(Pdf pdf, double x, double y, boolean fit, boolean columns, boolean rtl, boolean btt, boolean center, double margin, double spacing, double linewidth) throws CpdfError;

    /** Imposes a document two up. twoUp does so by shrinking the page size, to fit two pages on one. */
    public native void twoUp(Pdf pdf) throws CpdfError;
    
    /** Imposes a document two up. twoUpStack does so by doubling the page size, to fit two pages on one. */
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

    /** Returns <code>true</code> if the given page has the given box. E.g "/CropBox". */
    public native boolean hasBox(Pdf pdf, int pagenumber, String boxname) throws CpdfError;

    /** These functions get a box given the document. The values are returned
    in a given array of length 4: min x, max x, min y, max y in points. Only
    succeeds if such a box exists, as checked by hasBox. */
    public native void getMediaBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document. The values are returned
    in a given array of length 4: min x, max x, min y, max y in points. Only
    succeeds if such a box exists, as checked by hasBox. */
    public native void getCropBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document. The values are returned
    in a given array of length 4: min x, max x, min y, max y in points. Only
    succeeds if such a box exists, as checked by hasBox. */
    public native void getBleedBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document. The values are returned
    in a given array of length 4: min x, max x, min y, max y in points. Only
    succeeds if such a box exists, as checked by hasBox. */
    public native void getArtBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;
    
    /** These functions get a box given the document. The values are returned
    in a given array of length 4: min x, max x, min y, max y in points. Only
    succeeds if such a box exists, as checked by hasBox. */
    public native void getTrimBox(Pdf pdf, int pagenumber, double[] r) throws CpdfError;

    /** These functions set a box given the document page range, min x, max x, min y, max y in points. */
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
    is given in PDF date format - Jcpdf will convert it to XMP format. The
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
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int startGetPageLabels(Pdf pdf) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native void endGetPageLabels() throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int getPageLabelOffset(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    public native int getPageLabelStyle(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
    labelprefix = ""<br/>
    startpage = 6<br/>
    startvalue = 1<br> */
    
    public native int getPageLabelRange(int n) throws CpdfError;
    
    native byte[] XgetPageLabelPrefix(int n) throws CpdfError;
    
    /** Gets page label data. Call startGetPageLabels to find out how many
    there are, then use these serial numbers to get the style, prefix, offset
    and start value (note not a range). Call endGetPageLabels to clean up.
    
    <p>For example, a document might have five pages of introduction with roman
    numerals, followed by the rest of the pages in decimal arabic, numbered from
    one:
    
    <p>labelstyle = LowercaseRoman<br/>
    labelprefix = ""<br/>
    startpage = 1<br/>
    startvalue = 1<br/>
    
    <p>labelstyle = DecimalArabic<br/>
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
    
    /** Attaches a file to the PDF. It is attached at document level.
     * @param filename file name
     * @param pdf PDF document */
    public void attachFile(String filename, Pdf pdf) throws CpdfError
    {
        XattachFile(encodeUTF8(filename), pdf);
    }

    /** Attaches a file to a page of the PDF. Given its file name, pdf, and the page number to which it should be attached.
     * @param filename file name
     * @param pdf PDF document
     * @param pagenumber page number to attach to */
    public void attachFileToPage(String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPage(encodeUTF8(filename), pdf, pagenumber);
    }

    /** Attaches data from memory to a document.
     * @param data attachment itself
     * @param filename file name to use to describe attachment
     * @param pdf PDF document */
    public void attachFileFromMemory(byte[] data, String filename, Pdf pdf) throws CpdfError
    {
        XattachFileFromMemory(data, encodeUTF8(filename), pdf);
    }

    /** Attaches data to a page from memory.
     * @param data attachment itself
     * @param filename file name to use to describe attachment
     * @param pdf PDF document */
    public void attachFileToPageFromMemory(byte[] data, String filename, Pdf pdf, int pagenumber) throws CpdfError
    {
        XattachFileToPageFromMemory(data, encodeUTF8(filename), pdf, pagenumber);
    }

    /** Removes all page- and document-level attachments from a document. */
    public native void removeAttachedFiles(Pdf pdf) throws CpdfError;

    /** Lists information about attachments. Call
    {@link #startGetAttachments(pdf) startGetAttachments} first, then {@link
    #numberGetAttachments() numberGetAttachments} to find out how many there are.
    Then {@link #getAttachmentName(int) getAttachmentName}, {@link
    #getAttachmentPage(int) getAttachmentPage}, or {@link #getAttachmentData(int)
    getAttachmentData}. to return each one <code>0...(n - 1)</code>. Finally, call
    {@link #endGetAttachments() #endGetAttachments} to clean up. */
    public native void startGetAttachments(Pdf pdf) throws CpdfError;
    
    /** Lists information about attachments. Call
    {@link #startGetAttachments(pdf) startGetAttachments} first, then {@link
    #numberGetAttachments() numberGetAttachments} to find out how many there are.
    Then {@link #getAttachmentName(int) getAttachmentName}, {@link
    #getAttachmentPage(int) getAttachmentPage}, or {@link #getAttachmentData(int)
    getAttachmentData}. to return each one <code>0...(n - 1)</code>. Finally, call
    {@link #endGetAttachments() #endGetAttachments} to clean up. */
    public native int numberGetAttachments() throws CpdfError;
    
    /** Gets the name of an attachment, given a serial number. */
    public native String getAttachmentName(int serial) throws CpdfError;
    
    /** Gets the page number, given a serial number. 0 = document level. */
    public native int getAttachmentPage(int serial) throws CpdfError;
    
    /** Gets the attachment data itself, given a serial number. */
    public native byte[] getAttachmentData(int serial) throws CpdfError;
    
    /** Lists information about attachments. Call
    {@link #startGetAttachments(pdf) startGetAttachments} first, then {@link
    #numberGetAttachments() numberGetAttachments} to find out how many there are.
    Then {@link #getAttachmentName(int) getAttachmentName}, {@link
    #getAttachmentPage(int) getAttachmentPage}, or {@link #getAttachmentData(int)
    getAttachmentData}. to return each one <code>0...(n - 1)</code>. Finally, call
    {@link #endGetAttachments() #endGetAttachments} to clean up. */
    public native void endGetAttachments() throws CpdfError; 

    /* CHAPTER 13. Images. */
    
    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native int startGetImageResolution(Pdf pdf, double res) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native int getImageResolutionPageNumber(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native String getImageResolutionImageName(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native int getImageResolutionXPixels(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native int getImageResolutionYPixels(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native double getImageResolutionXRes(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native double getImageResolutionYRes(int serial) throws CpdfError;

    /** Gets image data, including resolution at all points of use. Call
    {@link #startGetImageResolution(pdf, double) startGetImageResolution(pdf, min_required_resolution)} to begin
    the process of obtaining data on all image uses below
    <code>min_required_resolution</code>, returning the total number. So, to
    return all image uses, specify a very high
    <code>min_required_resolution</code>. Then, call the other functions giving
    a serial number <code>0...n - 1</code>, to retrieve the data. Finally, call
    {@link #endGetImageResolution() endGetImageResolution} to clean up. */
    public native void endGetImageResolution() throws CpdfError;

    /* CHAPTER 14. Fonts. */
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native void startGetFontInfo(Pdf pdf) throws CpdfError;

    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native int numberFonts() throws CpdfError;
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native String getFontName(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native int getFontPage(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native String getFontType(int setial) throws CpdfError;
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native String getFontEncoding(int serial) throws CpdfError;
    
    /** Retrieves font information. First, call {@link #startGetFontInfo(Pdf) startGetFontInfo}.
    Now call {@link #numberFonts() numberFonts} to return the number of fonts.
    For each font, call one or more of {@link #getFontPage(int) getFontPage},
    {@link #getFontName(int) getFontName}, {@link #getFontType(int)
    getFontType}, and {@link #getFontEncoding(int) getFontEncoding} giving a
    serial number <code>0...n - 1</code> to return information. Finally, call
    {@link #endGetFontInfo() endGetFontInfo} to clean up. */
    public native void endGetFontInfo() throws CpdfError;
    
    /** Removes all font data from a file. */
    public native void removeFonts(Pdf pdf) throws CpdfError;
    
    /** Copies the given font
    from the given page in the 'from' PDF to every page in the 'to' PDF. The
    new font is stored under its font name.
    @param from_pdf PDF document to copy from
    @param to_pdf PDF document to copy to
    @param range page range
    @param pagenumber page number of the page to copy from
    @param fontname font name */
    public native void copyFont(Pdf from_pdf, Pdf to_pdf, Range range, int pagenumber, String fontname) throws CpdfError;

    /* CHAPTER 15. PDF and JSON */
    native void XoutputJSON(byte[] filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError;
    
    /** Outputs a PDF in JSON format to the given filename.
     * @param filename file name
     * @param parse_content parse page content
     * @param no_stream_data all stream data is suppressed entirely
     * @param decompress_streams streams are decompressed
     * @param pdf PDF document */
    public void outputJSON(String filename, boolean parse_content, boolean
        no_stream_data, boolean decompress_streams, Pdf pdf) throws CpdfError {
      XoutputJSON(encodeUTF8(filename), parse_content, no_stream_data,
          decompress_streams, pdf); }

    /** Like outputJSON, but it writes to a byte array in memory.
     * @param pdf PDF document
     * @param parse_content parse page content
     * @param no_stream_data all stream data is suppressed entirely
     * @param decompress_streams streams are decompressed */
    public native byte[] outputJSONMemory(Pdf pdf, boolean parse_content, boolean no_stream_data, boolean decompress_streams) throws CpdfError;

    native Pdf XfromJSON(byte[] filename) throws CpdfError;
    
    /** Loads a PDF from a JSON file given its filename. */
    public Pdf fromJSON(String filename) throws CpdfError
    {
        return XfromJSON(encodeUTF8(filename));
    }

    /** Loads a PDF from a JSON file in memory. */
    public native Pdf fromJSONMemory(byte[] data) throws CpdfError;
    
    /* CHAPTER 16. Optional Content Groups */
    
    /** Begins retrieving optional content group names. The serial number 0..n - 1 is returned. */
    public native int startGetOCGList(Pdf pdf) throws CpdfError;
    
    /** Retrieves an entry in the optional content group list, given the serial number 0..n - 1. */
    public native String OCGListEntry(int serial) throws CpdfError;
    
    /** Ends retrieval of optional content group names. */
    public native void endGetOCGList() throws CpdfError;

    /** Renames an optional content group.
     * @param pdf PDF document
     * @param f name to rename from
     * @param t name to rename to */
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
    pages.
    @param w width of page
    @param h height of page
    @param pages number of pages */
    public native Pdf blankDocument(double w, double h, int pages) throws CpdfError;

    /** Makes a blank document given a page size and number of pages.
    @param papersize paper size
    @param pages number of pages */
    public native Pdf blankDocumentPaper(int papersize, int pages) throws CpdfError;
    
    native Pdf XtextToPDF(double w, double h, int font, double fontsize, byte[] filename) throws CpdfError;
    native Pdf XtextToPDFPaper(int papersize, int font, double fontsize, byte[] filename) throws CpdfError;
    
    /** Typesets a UTF8 text file
    ragged right on a page of size w * h in points in the given font and font
    size.
    @param w width of page
    @param h height of page
    @param font font
    @param fontsize font size
    @param filename file name */
    public Pdf textToPDF(double w, double h, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDF(w, h, font, fontsize, encodeUTF8(filename));
    }
    
    /** Typesets a UTF8 text file ragged right on a page of the given size in the given font and font size.
    @param papersize paper size
    @param font font
    @param fontsize font size
    @param filename file name */
    public Pdf textToPDFPaper(int papersize, int font, double fontsize, String filename) throws CpdfError
    {
        return XtextToPDFPaper(papersize, font, fontsize, encodeUTF8(filename));
    }

    /* CHAPTER 18. Miscellaneous */
    
    /** Removes images on the given pages, replacing them with crossed boxes if <code>boxes</code> is <code>true</code>.
     * @param pdf PDF document
     * @param range page range
     * @param boxes add crossed boxes */
    public native void draft(Pdf pdf, Range range, boolean boxes) throws CpdfError;
    
    /** Removes all text from the given pages in a given document. */
    public native void removeAllText(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all text on the given pages. */
    public native void blackText(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all lines on the given pages. */
    public native void blackLines(Pdf pdf, Range range) throws CpdfError;
    
    /** Blackens all fills on the given pages. */
    public native void blackFills(Pdf pdf, Range range) throws CpdfError;
    
    /** Thickens every line less than <code>min_thickness</code> to <code>min_thickness</code>. Thickness given in points.
    @param pdf PDF document
    @param range page range
    @param min_thickness minimum thickness
    */
    public native void thinLines(Pdf pdf, Range range, double min_thickness) throws CpdfError;
    
    /** Copies the <code>/ID</code> from one document to another.
     * @param pdf PDF document to copy from
     * @param pdf2 PDF document to copy to */
    public native void copyId(Pdf pdf, Pdf pdf2) throws CpdfError;
    
    /** Removes a document's <code>/ID</code>. */
    public native void removeId(Pdf pdf) throws CpdfError;
    
    /** Sets the minor version number of a document.
     * @param pdf PDF document
     * @param version minor version number. */
    public native void setVersion(Pdf pdf, int version) throws CpdfError;
    
    /** Sets the full version number of a document.
     * @param pdf PDF document
     * @param major major version number
     * @param minor minor version nuber */
    public native void setFullVersion(Pdf pdf, int major, int minor) throws CpdfError;
    
    /** Removes any dictionary entry with the given key anywhere in the document.
     * @param pdf PDF document
     * @param key key to remove */
    public native void removeDictEntry(Pdf pdf, String key) throws CpdfError;
    
    /** Removes any dictionary entry with the given key whose value matches the given search term.
     * @param pdf PDF document
     * @param key key to remove
     * @param searchterm search term */
    public native void removeDictEntrySearch(Pdf pdf, String key, String searchterm) throws CpdfError;
    
    /** Replaces the value associated with the given key.
     * @param pdf PDF document
     * @param key key whose value to replace
     * @param newvalue value to replac with */
    public native void replaceDictEntry(Pdf pdf, String key, String newvalue) throws CpdfError;
    
    /** Replaces the value associated with the given key if the existing value matches the search term.
     * @param pdf PDF document
     * @param key key whose value to replace
     * @param newvalue value to replac with
     * @param searchterm search term */
    public native void replaceDictEntrySearch(Pdf pdf, String key, String newvalue, String searchterm) throws CpdfError;
    
    /** Removes all clipping from pages in the given range. */
    public native void removeClipping(Pdf pdf, Range range) throws CpdfError;
    
    /** Returns a JSON array containing any and all values associated with the given key, and fills in its length.
     * @param pdf PDF document
     * @param key key to search for */
    public native byte[] getDictEntries(Pdf pdf, String key) throws CpdfError;
}
