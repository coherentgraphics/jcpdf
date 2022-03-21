/* Jcpdf.java */

public class Jcpdf {
    native void startup();
    native String version();
    native void setFast();
    native void setSlow();
    native int fromFile(String filename, String userpw);
    native int fromFileLazy(String filename, String userpw);
    native byte[] toMemory(int pdf, boolean linearize, boolean make_id);
    native int fromMemory(byte[] data, String userpw);
    native int startEnumeratePDFs();
    native int enumeratePDFsKey(int n);
    native String enumeratePDFsInfo(int n);
    native void endEnumeratePDFs();
    native double ptOfCm(double f);
    native double ptOfMm(double f);
    native double ptOfIn(double f);
    native double cmOfPt(double f);
    native double mmOfPt(double f);
    native double inOfPt(double f);
    native int range(int from, int to);
    native int all(int pdf);
    native int odd(int r);
    native int even(int r);
    native int rangeUnion(int r, int s);
    native int difference(int r, int s);
    native int removeDuplicates(int r);
    native int rangeLength(int r);
    native int rangeGet(int r, int n);
    native int rangeAdd(int r, int n);
    native boolean isInRange(int r, int n);
    native int parsePagespec(int pdf, String pagespec);
    native boolean validatePagespec(String pagespec);
    native String stringOfPagespec(int pdf, int r);
    native int blankRange();
    native int pages(int pdf);
    native int pagesFast(String userpw, String filename);
    native void toFile(int pdf, String filename, boolean linearize, boolean make_id);
    native void toFileExt(int pdf, String filename, boolean linearize, boolean make_id, boolean preserve_objstm, boolean create_objstm, boolean compress_objstm);
    native boolean isEncrypted(int pdf);
    native boolean isLinearized(String filename);
    native int toFileEncrypted(int pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, String filename);
    native int toFileEncryptedExt(int pdf, int encryption_method, int[] permissions, String owner_password, String user_password, boolean linearize, boolean makeid, boolean preserve_objstm, boolean generate_objstm, boolean compress_objstm, String filename);
    native boolean hasPermission(int pdf, int permission);
    native int encryptionKind(int pdf);
    native void decryptPdf(int pdf, String userpw);
    native void decryptPdfOwner(int pdf, String ownerpw);

    native int mergeSimple(int[] pdfs);
    native int merge(int[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts);
    native int mergeSame(int[] pdfs, boolean retain_numbering, boolean remove_duplicate_fonts, int[] ranges);
    native int selectPages(int pdf, int range);

    native void scalePages(int pdf, int range, double sx, double sy);
    native void scaleToFit(int pdf, int range, double w, double h, double scale);
    native void scaleToFitPaper(int pdf, int range, int papersize, double scale);
    native void shiftContents(int pdf, int range, double dx, double dy);
    native void rotate(int pdf, int range, int angle);
    native void rotateBy(int pdf, int range, int angle);
    native void rotateContents(int pdf, int range, double angle);
    native void upright(int pdf, int range);
    native void hFlip(int pdf, int range);
    native void vFlip(int pdf, int range);
    native void crop(int pdf, int range, double x, double y, double w, double h);
    native void removeCrop(int pdf, int range);
    native void removeTrim(int pdf, int range);
    native void removeArt(int pdf, int range);
    native void removeBleed(int pdf, int range);
    native void trimMarks(int pdf, int range);
    native void showBoxes(int pdf, int range);
    native void hardBox(int pdf, int range, String box);
    native void compress(int pdf);
    native void decompress(int pdf);
    native void squeezeInMemory(int pdf);
    native void startGetBookmarkInfo(int pdf);
    native int numberBookmarks();
    native int getBookmarkLevel(int serial);
    native int getBookmarkPage(int pdf, int serial);
    native String getBookmarkText(int serial);
    native boolean getBookmarkOpenStatus(int serial);
    native void endGetBookmarkInfo();
    native void startSetBookmarkInfo(int n);
    native void setBookmarkLevel(int serial, int level);
    native void setBookmarkPage(int pdf, int serial, int pagenum);
    native void setBookmarkOpenStatus(int serial, boolean open);
    native void setBookmarkText(int serial, String text);
    native void endSetBookmarkInfo(int pdf);
    native byte[] getBookmarksJSON(int pdf);
    native void setBookmarksJSON(int pdf, byte[] data);
    native void tableOfContents(int pdf, int font, double fontsize, String title, boolean bookmark);
    native void removeText(int pdf, int range);
    native int textWidth(int font, String text);
    native void stampOn(int pdf, int pdf2, int range);
    native void stampUnder(int pdf, int pdf2, int range);
    native int combinePages(int pdf, int pdf2);
    native String stampAsXObject(int pdf, int range, int stamp_pdf);
    native void addContent(String s, boolean before, int pdf, int range); 
    native void impose(int pdf, double x, double y, boolean fit, boolean columns, boolean rtl, boolean btt, boolean center, double margin, double spacing, double linewidth);
    native void twoUp(int pdf);
    native void twoUpStack(int pdf);
    native void padBefore(int pdf, int range);
    native void padAfter(int pdf, int range);
    native void padEvery(int pdf, int n);
    native void padMultiple(int pdf, int n);
    native void padMultipleBefore(int pdf, int n);
    native byte[] annotationsJSON(int pdf);
    native int getVersion(int pdf);
    native int getMajorVersion(int pdf);
    native String getTitle(int pdf);
    native String getAuthor(int pdf);
    native String getSubject(int pdf);
    native String getKeywords(int pdf);
    native String getCreator(int pdf);
    native String getProducer(int pdf);
    native String getCreationDate(int pdf);
    native String getModificationDate(int pdf);
    native String getTitleXMP(int pdf);
    native String getAuthorXMP(int pdf);
    native String getSubjectXMP(int pdf);
    native String getKeywordsXMP(int pdf);
    native String getCreatorXMP(int pdf);
    native String getProducerXMP(int pdf);
    native String getCreationDateXMP(int pdf);
    native String getModificationDateXMP(int pdf);
    native void setTitle(int pdf, String str);
    native void setAuthor(int pdf, String str);
    native void setSubject(int pdf, String str);
    native void setKeywords(int pdf, String str);
    native void setCreator(int pdf, String str);
    native void setProducer(int pdf, String str);
    native void setCreationDate(int pdf, String str);
    native void setModificationDate(int pdf, String str);
    native void setTitleXMP(int pdf, String str);
    native void setAuthorXMP(int pdf, String str);
    native void setSubjectXMP(int pdf, String str);
    native void setKeywordsXMP(int pdf, String str);
    native void setCreatorXMP(int pdf, String str);
    native void setProducerXMP(int pdf, String str);
    native void setCreationDateXMP(int pdf, String str);
    native void setModificationDateXMP(int pdf, String str);
    native int getPageRotation(int pdf, int pagenumber);
    native boolean hasBox(int pdf, int pagenumber, String boxname);
    native void setMediabox(int pdf, int range, double minx, double maxx, double miny, double maxy);
    native void setCropBox(int pdf, int range, double minx, double maxx, double miny, double maxy);
    native void setTrimBox(int pdf, int range, double minx, double maxx, double miny, double maxy);
    native void setArtBox(int pdf, int range, double minx, double maxx, double miny, double maxy);
    native void setBleedBox(int pdf, int range, double minx, double maxx, double miny, double maxy);
    native void markTrapped(int pdf);
    native void markUntrapped(int pdf);
    native void markTrappedXMP(int pdf);
    native void markUntrappedXMP(int pdf);
    native void setPageLayout(int pdf, int layout);
    native void setPageMode(int pdf, int mode);
    native void hideToolbar(int pdf, boolean flag);
    native void hideMenubar(int pdf, boolean flag);
    native void hideWindowUi(int pdf, boolean flag);
    native void fitWindow(int pdf, boolean flag);
    native void centerWindow(int pdf, boolean flag);
    native void displayDocTitle(int pdf, boolean flag);
    native void openAtPage(int pdf, boolean fit, int pagenumber);
    native void setMetadataFromFile(int pdf, String filename);
    native void setMetadataFromByteArray(int pdf, byte[] data);
    native byte[] getMetadata(int pdf);

    native void removeMetadata(int pdf);
    native void createMetadata(int pdf);
    native void setMetadataDate(int pdf, String date);
    native void addPageLabels(int pdf, int style, String prefix, int offset, int range, boolean progress);
    native void removePageLabels(int pdf);
    native int startGetPageLabels(int pdf);
    native void endGetPageLabels();
    native int getPageLabelOffset(int n);
    native int getPageLabelStyle(int n);
    native int getPageLabelRange(int n);
    native String getPageLabelPrefix(int n);
    native String getPageLabelStringForPage(int pdf, int n);

    native void attachFile(String filename, int pdf);
    native void attachFileToPage(String filename, int pdf, int pagenumber);
    native void removeAttachedFiles(int pdf);
    native void startGetAttachments(int pdf);
    native int numberGetAttachments();
    native String getAttachmentName(int serial);
    native int getAttachmentPage(int serial);
    native void endGetAttachments(); 

    native int startGetImageResolution(int pdf, double res);
    native int getImageResolutionPageNumber(int serial);
    native String getImageResolutionImageName(int serial);
    native int getImageResolutionXPixels(int serial);
    native int getImageResolutionYPixels(int serial);
    native double getImageResolutionXRes(int serial);
    native double getImageResolutionYRes(int serial);
    native void endGetImageResolution();

    native void startGetFontInfo(int pdf);
    native int numberFonts();
    native String getFontName(int serial);
    native int getFontPage(int serial);
    native String getFontType(int setial);
    native String getFontEncoding(int serial);
    native void endGetFontInfo();
    native void removeFonts(int pdf);
    native void copyFont(int from_pdf, int to_pdf, int range, int pagenumber, String fontname);

    native void outputJSON(String filename, boolean parse_content, boolean no_stream_data, boolean decompress_streams, int pdf);
    native int fromJSON(String filename);
    native byte[] outputJSONMemory(int pdf, boolean parse_content, boolean no_stream_data, boolean decompress_streams);
    native int fromJSONMemory(byte[] data);

    native int startGetOCGList(int pdf);
    native String OCGListEntry(int serial);
    native void endGetOCGList();
    native void OCGCoalesce(int pdf);
    native void OCGRename(int pdf, String f, String t);
    native void OCGOrderAll(int pdf);

    native int blankDocument(double w, double h, int pages);
    native int blankDocumentPaper(int papersize, int pages);
    native int textToPDF(double w, double h, int font, double fontsize, String filename);
    native int textToPDFPaper(int papersize, int font, double fontsize, String filename);

    native void draft(int pdf, int range, boolean boxes);
    native void removeAllText(int pdf, int range);
    native void blackText(int pdf, int range);
    native void blackLines(int pdf, int range);
    native void blackFills(int pdf, int range);
    native void thinLines(int pdf, int range, double minwidth);
    native void copyId(int pdf, int pdf2);
    native void removeId(int pdf);
    native void setVersion(int pdf, int version);
    native void setFullVersion(int pdf, int major, int minor);
    native void removeDictEntry(int pdf, String str);
    native void removeDictEntrySearch(int pdf, String str, String searchterm);
    native void replaceDictEntry(int pdf, String key, String newvalue);
    native void replaceDictEntrySearch(int pdf, String key, String newvalue, String searchterm);
    native byte[] getDictEntries(int pdf, String key);
    native void removeClipping(int pdf, int range);

    int noEdit = 0;
    int noPrint = 1;
    int noCopy = 2;
    int noAnnot = 3;
    int noForms = 4;
    int noExtract = 5;
    int noAssemble = 6;
    int noHqPrint = 7;

    int pdf40bit = 0;
    int pdf128bit = 1;
    int aes128bitfalse = 2;
    int aes128bittrue = 3;
    int aes256bitfalse = 4;
    int aes256bittrue = 5;
    int aes256bitisofalse = 6;
    int aes256bitiso = 7;

    int decimalArabic = 0;
    int uppercaseRoman = 1;
    int lowercaseRoman = 2;
    int uppercaseLetters = 3;
    int lowercaseLetters = 4;

    int singlePage = 0;
    int oneColumn = 1;
    int twoColumnLeft = 2;
    int twoColumnRight = 3;
    int twoPageLeft = 4;
    int twoPageRight = 5;

    int useNone = 0;
    int useOutlines = 1;
    int useThumbs = 2;
    int useOC = 3;
    int useAttachments = 4;

    int a0portrait = 0;
    int a1portrait = 1;
    int a2portrait = 2;
    int a3portrait = 3;
    int a4portrait = 4;
    int a5portrait = 5;
    int a0landscape = 6;
    int a1landscape = 7;
    int a2landscape = 8;
    int a3landscape = 9;
    int a4landscape = 10;
    int a5landscape = 11;
    int usletterportrait = 12;
    int usletterlandscape = 13;
    int uslegalportrait = 14;
    int uslegallandscape = 15;

    int timesRoman = 0;
    int timesBold = 1;
    int timesItalic = 2;
    int timesBoldItalic = 3;
    int helvetica = 4;
    int helveticaBold = 5;
    int helveticaOblique = 6;
    int helveticaBoldOblique = 7;
    int courier = 8;
    int courierBold = 9;
    int courierOblique = 10;
    int courierBoldOblique = 11;

    static public void main(String argv[]) {
        System.loadLibrary("cpdf");
        System.loadLibrary("jcpdf");
        Jcpdf jcpdf = new Jcpdf();
        /* CHAPTER 0. Preliminaries */
        System.out.println("***** CHAPTER 0. Preliminaries");
        System.out.println("---cpdf_startup()");
        jcpdf.startup();
        System.out.println("---cpdf_version()");
        System.out.format("version = %s\n", jcpdf.version());
        System.out.println("---cpdf_setFast()");
        jcpdf.setFast();
        System.out.println("---cpdf_setSlow()");
        jcpdf.setSlow();
        System.out.println("---cpdf_clearError()");
        System.out.println("***** CHAPTER 1. Basics");
        System.out.println("---cpdf_fromFile()");
        int pdf = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_fromFileLazy()");
        int pdf2 = jcpdf.fromFileLazy("testinputs/cpdflibmanual.pdf", "");

        System.out.println("---cpdf_toMemory()");
        byte[] mempdf = jcpdf.toMemory(pdf, false, false);
        System.out.println("---cpdf_fromMemory()");
        int frommem = jcpdf.fromMemory(mempdf, "");
        jcpdf.toFile(frommem, "testoutputs/01fromMemory.pdf", false, false);

        /*Console.WriteLine("---cpdf_fromMemoryLazy()");
        IntPtr ptr = Marshal.AllocHGlobal(mempdf.Length);
        Marshal.Copy(mempdf, 0, ptr, mempdf.Length);
        Cpdf.Pdf frommemlazy = Cpdf.fromMemoryLazy(ptr, mempdf.Length, "");
        Cpdf.toFile(frommemlazy, "testoutputs/01fromMemoryLazy.pdf", false, false);*/
        int pdf3 = jcpdf.blankDocument(153.5, 234.2, 50);
        int pdf4 = jcpdf.blankDocumentPaper(jcpdf.a4landscape, 50);
        System.out.println("---cpdf: enumerate PDFs");
        int n = jcpdf.startEnumeratePDFs();
        for (int x = 0; x < n; x++)
        {
            int key = jcpdf.enumeratePDFsKey(x);
            String info = jcpdf.enumeratePDFsInfo(x);
        }
        jcpdf.endEnumeratePDFs();
        System.out.println("---cpdf_ptOfIn()");
        System.out.format("One inch is %f points\n", jcpdf.ptOfIn(1.0));
        System.out.println("---cpdf_ptOfCm()");
        System.out.format("One centimetre is %f points\n", jcpdf.ptOfCm(1.0));
        System.out.println("---cpdf_ptOfMm()");
        System.out.format("One millimetre is %f points\n", jcpdf.ptOfMm(1.0));
        System.out.println("---cpdf_inOfPt()");
        System.out.format("One point is %f inches\n", jcpdf.inOfPt(1.0));
        System.out.println("---cpdf_cmOfPt()");
        System.out.format("One point is %f centimetres\n", jcpdf.cmOfPt(1.0));
        System.out.println("---cpdf_mmOfPt()");
        System.out.format("One point is %f millimetres\n", jcpdf.mmOfPt(1.0));
        System.out.println("---cpdf_range()");
        int range = jcpdf.range(1, 10);
        System.out.println("---cpdf_all()");
        int all = jcpdf.all(pdf3);
        System.out.println("---cpdf_even()");
        int even = jcpdf.even(all);
        System.out.println("---cpdf_odd()");
        int odd = jcpdf.odd(all);
        System.out.println("---cpdf_rangeUnion()");
        int union = jcpdf.rangeUnion(even, odd);
        System.out.println("---cpdf_difference()");
        int diff = jcpdf.difference(even, odd);
        System.out.println("---cpdf_removeDuplicates()");
        int revdup = jcpdf.removeDuplicates(even);
        System.out.println("---cpdf_rangeLength()");
        int length = jcpdf.rangeLength(even);
        System.out.println("---cpdf_rangeGet()");
        int rangeget = jcpdf.rangeGet(even, 1);
        System.out.println("---cpdf_rangeAdd()");
        int rangeadd = jcpdf.rangeAdd(even, 20);
        System.out.println("---cpdf_isInRange()");
        boolean isin = jcpdf.isInRange(even, 2);
        System.out.println("---cpdf_parsePagespec()");
        int r = jcpdf.parsePagespec(pdf3, "1-5");
        System.out.println("---cpdf_validatePagespec()");
        boolean valid = jcpdf.validatePagespec("1-4,5,6");
        System.out.format("Validating pagespec gives %d\n", valid ? 1 : 0);
        System.out.println("---cpdf_stringOfPagespec()");
        String ps = jcpdf.stringOfPagespec(pdf3, r);
        System.out.format("String of pagespec is %s\n", ps);
        System.out.println("---cpdf_blankRange()");
        int b = jcpdf.blankRange();
        int pdf10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_pages()");
        int pages = jcpdf.pages(pdf10);
        System.out.format("Pages = %d\n", pages);
        System.out.println("---cpdf_pagesFast()");
        int pagesfast = jcpdf.pagesFast("", "testinputs/cpdflibmanual.pdf");
        System.out.format("Pages = %d\n", pagesfast);
        System.out.println("---cpdf_toFile()");
        jcpdf.toFile(pdf10, "testoutputs/01tofile.pdf", false, false);

        System.out.println("---cpdf_toFileExt()");
        jcpdf.toFileExt(pdf10, "testoutputs/01tofileext.pdf", false, true, true, true, true);
        System.out.println("---cpdf_isEncrypted()");
        boolean isenc = jcpdf.isEncrypted(pdf10);
        System.out.format("isencrypted:%d\n", isenc ? 1 : 0);
        System.out.println("---cpdf_isLinearized()");
        boolean lin = jcpdf.isLinearized("testinputs/cpdfmanual.pdf");
        System.out.format("islinearized:%d\n", lin ? 1 : 0);

        int pdf400 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pdf401 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");

        int[] permissions = new int[] {jcpdf.noEdit};
        System.out.println("---cpdf_toFileEncrypted()");
        jcpdf.toFileEncrypted(pdf400, jcpdf.pdf40bit, permissions, "owner", "user", false, false, "testoutputs/01encrypted.pdf");
        System.out.println("---cpdf_toFileEncryptedExt()");
        jcpdf.toFileEncryptedExt(pdf401, jcpdf.pdf40bit, permissions, "owner", "user", false, false, true, true, true, "testoutputs/01encryptedext.pdf");
        System.out.println("---cpdf_hasPermission()");
        int pdfenc = jcpdf.fromFile("testoutputs/01encrypted.pdf", "user");
        boolean hasnoedit = jcpdf.hasPermission(pdfenc, jcpdf.noEdit);
        boolean hasnocopy = jcpdf.hasPermission(pdfenc, jcpdf.noCopy);
        System.out.format("Haspermission %d, %d\n", hasnoedit ? 1 : 0, hasnocopy ? 1 : 0);
        System.out.println("---cpdf_encryptionKind()");
        int enckind = jcpdf.encryptionKind(pdfenc);
        System.out.format("encryption kind is %d\n", enckind);
        System.out.println("---cpdf_decryptPdf()");
        jcpdf.decryptPdf(pdf10, "");
        System.out.println("---cpdf_decryptPdfOwner()");
        jcpdf.decryptPdfOwner(pdf10, "");
        
        /* CHAPTER 2. Merging and Splitting */
        System.out.println("***** CHAPTER 2. Merging and Splitting");
        int pdf11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int selectrange = jcpdf.range(1, 3);
        System.out.println("---cpdf_mergeSimple()");
        int[] arr = new int[] {pdf11, pdf11, pdf11};
        int merged = jcpdf.mergeSimple(arr);
        jcpdf.toFile(merged, "testoutputs/02merged.pdf", false, true);
        System.out.println("---cpdf_merge()");
        int merged2 = jcpdf.merge(arr, false, false);
        jcpdf.toFile(merged2, "testoutputs/02merged2.pdf", false, true);
        System.out.println("---cpdf_mergeSame()");
        int[] ranges = new int[] {jcpdf.all(pdf11), jcpdf.all(pdf11), jcpdf.all(pdf11)};
        int merged3 = jcpdf.mergeSame(arr, false, false, ranges);
        jcpdf.toFile(merged3, "testoutputs/02merged3.pdf", false, false);
        System.out.println("---cpdf_selectPages()");
        int pdf12 = jcpdf.selectPages(pdf11, selectrange);
        jcpdf.toFile(pdf12, "testoutputs/02selected.pdf", false, false);

        /* CHAPTER 3. Pages */
        System.out.println("***** CHAPTER 3. Pages");
        int pagespdf1 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf8 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf9 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf12 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf13 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf14 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf15 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf17 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf18 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int pagespdf19 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_scalePages()");
        jcpdf.scalePages(pagespdf1, jcpdf.all(pagespdf1), 1.5, 1.8);
        jcpdf.toFile(pagespdf1, "testoutputs/03scalepages.pdf", false, false);
        System.out.println("---cpdf_scaleToFit()");
        jcpdf.scaleToFit(pagespdf2, jcpdf.all(pagespdf2), 1.5, 1.8, 0.9);
        jcpdf.toFile(pagespdf2, "testoutputs/03scaletofit.pdf", false, false);
        System.out.println("---cpdf_scaleToFitPaper()");
        jcpdf.scaleToFitPaper(pagespdf3, jcpdf.all(pagespdf3), jcpdf.a4portrait, 0.8);
        jcpdf.toFile(pagespdf3, "testoutputs/03scaletofitpaper.pdf", false, false);
        System.out.println("---cpdf_scaleContents()");

        //FIXME Need to implement positions
        /*Cpdf.Position position = new Cpdf.Position (Cpdf.Anchor.TopLeft, 20.0, 20.0);
        Cpdf.scaleContents(pagespdf4, Cpdf.all(pagespdf4), position, 2.0);*/

        jcpdf.toFile(pagespdf4, "testoutputs/03scalecontents.pdf", false, false);
        System.out.println("---cpdf_shiftContents()");
        jcpdf.shiftContents(pagespdf5, jcpdf.all(pagespdf5), 1.5, 1.25);
        jcpdf.toFile(pagespdf5, "testoutputs/03shiftcontents.pdf", false, false);
        System.out.println("---cpdf_rotate()");
        jcpdf.rotate(pagespdf6, jcpdf.all(pagespdf6), 90);
        jcpdf.toFile(pagespdf6, "testoutputs/03rotate.pdf", false, false);
        System.out.println("---cpdf_rotateBy()");
        jcpdf.rotateBy(pagespdf7, jcpdf.all(pagespdf7), 90);
        jcpdf.toFile(pagespdf7, "testoutputs/03rotateby.pdf", false, false);
        System.out.println("---cpdf_rotateContents()");
        jcpdf.rotateContents(pagespdf8, jcpdf.all(pagespdf8), 35.0);
        jcpdf.toFile(pagespdf8, "testoutputs/03rotatecontents.pdf", false, false);
        System.out.println("---cpdf_upright()");
        jcpdf.upright(pagespdf9, jcpdf.all(pagespdf9));
        jcpdf.toFile(pagespdf9, "testoutputs/03upright.pdf", false, false);
        System.out.println("---cpdf_hFlip()");
        jcpdf.hFlip(pagespdf10, jcpdf.all(pagespdf10));
        jcpdf.toFile(pagespdf10, "testoutputs/03hflip.pdf", false, false);
        System.out.println("---cpdf_vFlip()");
        jcpdf.vFlip(pagespdf11, jcpdf.all(pagespdf11));
        jcpdf.toFile(pagespdf11, "testoutputs/03vflip.pdf", false, false);
        System.out.println("---cpdf_crop()");
        jcpdf.crop(pagespdf12, jcpdf.all(pagespdf12), 0.0, 0.0, 400.0, 500.0);
        jcpdf.toFile(pagespdf12, "testoutputs/03crop.pdf", false, false);
        System.out.println("---cpdf_trimMarks()");
        jcpdf.trimMarks(pagespdf13, jcpdf.all(pagespdf13));
        jcpdf.toFile(pagespdf13, "testoutputs/03trim_marks.pdf", false, false);
        System.out.println("---cpdf_showBoxes()");
        jcpdf.showBoxes(pagespdf14, jcpdf.all(pagespdf14));
        jcpdf.toFile(pagespdf14, "testoutputs/03show_boxes.pdf", false, false);
        System.out.println("---cpdf_hardBox()");
        jcpdf.hardBox(pagespdf15, jcpdf.all(pagespdf15), "/MediaBox");
        jcpdf.toFile(pagespdf15, "testoutputs/03hard_box.pdf", false, false);
        System.out.println("---cpdf_removeCrop()");
        jcpdf.removeCrop(pagespdf16, jcpdf.all(pagespdf16));
        jcpdf.toFile(pagespdf16, "testoutputs/03remove_crop.pdf", false, false);
        System.out.println("---cpdf_removeTrim()");
        jcpdf.removeTrim(pagespdf17, jcpdf.all(pagespdf17));
        jcpdf.toFile(pagespdf17, "testoutputs/03remove_trim.pdf", false, false);
        System.out.println("---cpdf_removeArt()");
        jcpdf.removeArt(pagespdf18, jcpdf.all(pagespdf18));
        jcpdf.toFile(pagespdf18, "testoutputs/03remove_art.pdf", false, false);
        System.out.println("---cpdf_removeBleed()");
        jcpdf.removeBleed(pagespdf19, jcpdf.all(pagespdf19));
        jcpdf.toFile(pagespdf19, "testoutputs/03remove_bleed.pdf", false, false);
        
        /* CHAPTER 4. Encryption */
        /* Encryption covered under Chapter 1 in cpdflib. */
        
        /* CHAPTER 5. Compression */
        System.out.println("***** CHAPTER 5. Compression");
        int pdf16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_compress()");
        jcpdf.compress(pdf16);
        jcpdf.toFile(pdf16, "testoutputs/05compressed.pdf", false, false);
        System.out.println("---cpdf_decompress()");
        jcpdf.decompress(pdf16);
        jcpdf.toFile(pdf16, "testoutputs/05decompressed.pdf", false, false);
        System.out.println("---cpdf_squeezeInMemory()");
        jcpdf.squeezeInMemory(pdf16);
        jcpdf.toFile(pdf16, "testoutputs/05squeezedinmemory.pdf", false, false);
        
        /* CHAPTER 6. Bookmarks */
        System.out.println("***** CHAPTER 6. Bookmarks");
        int pdf17 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf: get bookmarks");
        jcpdf.startGetBookmarkInfo(pdf17);
        int nb = jcpdf.numberBookmarks();
        System.out.format("There are %d bookmarks\n", nb);
        for (int b2 = 0; b2 < nb; b2++)
        {
            int level = jcpdf.getBookmarkLevel(b2);
            int page = jcpdf.getBookmarkPage(pdf17, b2);
            String text = jcpdf.getBookmarkText(b2);
            boolean open = jcpdf.getBookmarkOpenStatus(b2);
            System.out.format("Bookmark at level %d points to page %d and has text \"%s\" and open %d\n", level, page, text, open ? 1 : 0);
        }
        jcpdf.endGetBookmarkInfo();
        System.out.println("---cpdf: set bookmarks");
        jcpdf.startSetBookmarkInfo(1);
        jcpdf.setBookmarkLevel(0, 0);
        jcpdf.setBookmarkPage(pdf17, 0, 20);
        jcpdf.setBookmarkOpenStatus(0, true);
        jcpdf.setBookmarkText(0, "New bookmark!");
        jcpdf.endSetBookmarkInfo(pdf17);
        jcpdf.toFile(pdf17, "testoutputs/06newmarks.pdf", false, false);

        System.out.println("---cpdf_getBookmarksJSON()");
        int marksjson = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] marksdata = jcpdf.getBookmarksJSON(marksjson);
        System.out.format("Contains %d bytes of data\n", marksdata.length);
        System.out.println("---cpdf_setBookmarksJSON()");
        jcpdf.setBookmarksJSON(marksjson, marksdata);
        jcpdf.toFile(marksjson, "testoutputs/06jsonmarks.pdf", false, false);

        System.out.println("---cpdf_tableOfContents()");
        int toc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        jcpdf.tableOfContents(toc, jcpdf.timesRoman, 12.0, "Table of Contents", false);
        jcpdf.toFile(toc, "testoutputs/06toc.pdf", false, false);
        
        /* CHAPTER 7. Presentations */
        /* Not included in the library version. */

        /* CHAPTER 8. Logos, Watermarks and Stamps */
        System.out.println("***** CHAPTER 8. Logos, Watermarks and Stamps");
        int textfile = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        //FIXME pos
        /*Console.WriteLine("---cpdf_addText()");
        Cpdf.Position pos = new Cpdf.Position (Cpdf.Anchor.TopLeft, 20.0, 20.0);
        Cpdf.addText(false,
                     textfile,
                     Cpdf.all(textfile),
                     "Some Text~~~~~~~~~~!",
                     pos,
                     1.0,
                     1,
                     Cpdf.Font.TimesRoman,
                     20.0,
                     0.5,
                     0.5,
                     0.5,
                     false,
                     false,
                     true,
                     0.5,
                     Cpdf.Justification.LeftJustify,
                     false,
                     false,
                     "",
                     1.0,
                     false);
        Console.WriteLine("---cpdf_addTextSimple()");
        Cpdf.addTextSimple(textfile, Cpdf.all(textfile), "The text!", pos, Cpdf.Font.TimesRoman, 50.0);
        Cpdf.toFile(textfile, "testoutputs/08added_text.pdf", false, false);
        */

        System.out.println("---cpdf_removeText()");
        jcpdf.removeText(textfile, jcpdf.all(textfile));
        jcpdf.toFile(textfile, "testoutputs/08removed_text.pdf", false, false);
        System.out.println("---cpdf_textWidth()");
        int w = jcpdf.textWidth(jcpdf.timesRoman, "What is the width of this?");
        int stamp = jcpdf.fromFile("testinputs/logo.pdf", "");
        int stampee = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int stamp_range = jcpdf.all(stamp);
        System.out.println("---cpdf_stampOn()");
        jcpdf.stampOn(stamp, stampee, stamp_range);
        System.out.println("---cpdf_stampUnder()");
        jcpdf.stampUnder(stamp, stampee, stamp_range);

        //FIXME pos
        /*
        Cpdf.Position spos = new Cpdf.Position (Cpdf.Anchor.TopLeft, 20.0, 20.0);
        Console.WriteLine("---cpdf_stampExtended()");
        Cpdf.stampExtended(stamp, stampee, stamp_range, true, true, spos, true);
        Cpdf.toFile(stamp, "testoutputs/08stamp_after.pdf", false, false);
        Cpdf.toFile(stampee, "testoutputs/08stampee_after.pdf", false, false);*/

        int c1 = jcpdf.fromFile("testinputs/logo.pdf", "");
        int c2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_combinePages()");
        int c3 = jcpdf.combinePages(c1, c2);
        jcpdf.toFile(c3, "testoutputs/08c3after.pdf", false, false);
        System.out.println("---cpdf_stampAsXObject()");
        int undoc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int ulogo = jcpdf.fromFile("testinputs/logo.pdf", "");

        String name = jcpdf.stampAsXObject(undoc, jcpdf.all(undoc), ulogo);
        String content = String.format("q 1 0 0 1 100 100 cm %s Do Q q 1 0 0 1 300 300 cm %s Do Q q 1 0 0 1 500 500 cm %s Do Q", name, name, name);
        System.out.println("---cpdf_addContent()");
        jcpdf.addContent(content, true, undoc, jcpdf.all(undoc));
        jcpdf.toFile(undoc, "testoutputs/08demo.pdf", false, false);
        
        /* CHAPTER 9. Multipage facilities */
        System.out.println("***** CHAPTER 9. Multipage facilities");
        int mp = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp25 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp26 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int mp7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_twoUp()");
        jcpdf.twoUp(mp);
        jcpdf.toFile(mp, "testoutputs/09mp.pdf", false, false);
        System.out.println("---cpdf_twoUpStack()");
        jcpdf.twoUpStack(mp2);
        jcpdf.toFile(mp2, "testoutputs/09mp2.pdf", false, false);
        System.out.println("---cpdf_impose()");
        jcpdf.impose(mp25, 5.0, 4.0, false, false, false, false, false, 40.0, 20.0, 2.0);
        jcpdf.toFile(mp25, "testoutputs/09mp25.pdf", false, false);
        jcpdf.impose(mp26, 2000.0, 1000.0, true, false, false, false, false, 40.0, 20.0, 2.0);
        jcpdf.toFile(mp26, "testoutputs/09mp26.pdf", false, false);
        System.out.println("---cpdf_padBefore()");
        jcpdf.padBefore(mp3, jcpdf.range(1, 10));
        jcpdf.toFile(mp3, "testoutputs/09mp3.pdf", false, false);
        System.out.println("---cpdf_padAfter()");
        jcpdf.padAfter(mp4, jcpdf.range(1, 10));
        jcpdf.toFile(mp4, "testoutputs/09mp4.pdf", false, false);
        System.out.println("---cpdf_padEvery()");
        jcpdf.padEvery(mp5, 5);
        jcpdf.toFile(mp5, "testoutputs/09mp5.pdf", false, false);
        System.out.println("---cpdf_padMultiple()");
        jcpdf.padMultiple(mp6, 10);
        jcpdf.toFile(mp6, "testoutputs/09mp6.pdf", false, false);
        System.out.println("---cpdf_padMultipleBefore()");
        jcpdf.padMultipleBefore(mp7, 23);
        jcpdf.toFile(mp7, "testoutputs/09mp7.pdf", false, false);
        
        /* CHAPTER 10. Annotations */
        System.out.println("***** CHAPTER 10. Annotations");
        System.out.println("---cpdf_annotationsJSON()");
        int annot = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] annotjson = jcpdf.annotationsJSON(annot);
        System.out.format("Contains %d bytes of data\n", annotjson.length);
        
        /* CHAPTER 11. Document Information and Metadata */
        System.out.println("***** CHAPTER 11. Document Information and Metadata");
        int pdf30 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_getVersion()");
        int v = jcpdf.getVersion(pdf30);
        System.out.format("minor version:%d\n", v);
        System.out.println("---cpdf_getMajorVersion()");
        int v2 = jcpdf.getMajorVersion(pdf30);
        System.out.format("major version:%d\n", v2);
        System.out.println("---cpdf_getTitle()");
        String title = jcpdf.getTitle(pdf30);
        System.out.format("title: %s\n", title);
        System.out.println("---cpdf_getAuthor()");
        String author = jcpdf.getAuthor(pdf30);
        System.out.format("author: %s\n", author);
        System.out.println("---cpdf_getSubject()");
        String subject = jcpdf.getSubject(pdf30);
        System.out.format("subject: %s\n", subject);
        System.out.println("---cpdf_getKeywords()");
        String keywords = jcpdf.getKeywords(pdf30);
        System.out.format("keywords: %s\n", keywords);
        System.out.println("---cpdf_getCreator()");
        String creator = jcpdf.getCreator(pdf30);
        System.out.format("creator: %s\n", creator);
        System.out.println("---cpdf_getProducer()");
        String producer = jcpdf.getProducer(pdf30);
        System.out.format("producer: %s\n", producer);
        System.out.println("---cpdf_getCreationDate()");
        String creationdate = jcpdf.getCreationDate(pdf30);
        System.out.format("creationdate: %s\n", creationdate);
        System.out.println("---cpdf_getModificationDate()");
        String modificationdate = jcpdf.getModificationDate(pdf30);
        System.out.format("modificationdate: %s\n", modificationdate);
        System.out.println("---cpdf_getTitleXMP()");
        String titlexmp = jcpdf.getTitleXMP(pdf30);
        System.out.format("titleXMP: %s\n", titlexmp);
        System.out.println("---cpdf_getAuthorXMP()");
        String authorxmp = jcpdf.getAuthorXMP(pdf30);
        System.out.format("authorXMP: %s\n", authorxmp);
        System.out.println("---cpdf_getSubjectXMP()");
        String subjectxmp = jcpdf.getSubjectXMP(pdf30);
        System.out.format("subjectXMP: %s\n", subjectxmp);
        System.out.println("---cpdf_getKeywordsXMP()");
        String keywordsxmp = jcpdf.getKeywordsXMP(pdf30);
        System.out.format("keywordsXMP: %s\n", keywordsxmp);
        System.out.println("---cpdf_getCreatorXMP()");
        String creatorxmp = jcpdf.getCreatorXMP(pdf30);
        System.out.format("creatorXMP: %s\n", creatorxmp);
        System.out.println("---cpdf_getProducerXMP()");
        String producerxmp = jcpdf.getProducerXMP(pdf30);
        System.out.format("producerXMP: %s\n", producerxmp);
        System.out.println("---cpdf_getCreationDateXMP()");
        String creationdatexmp = jcpdf.getCreationDateXMP(pdf30);
        System.out.format("creationdateXMP: %s\n", creationdatexmp);
        System.out.println("---cpdf_getModificationDateXMP()");
        String modificationdatexmp = jcpdf.getModificationDateXMP(pdf30);
        System.out.format("modificationdateXMP: %s\n", modificationdatexmp);
        System.out.println("---cpdf_setTitle()");
        jcpdf.setTitle(pdf30, "title");
        System.out.println("---cpdf_setAuthor()");
        jcpdf.setAuthor(pdf30, "author");
        System.out.println("---cpdf_setSubject()");
        jcpdf.setSubject(pdf30, "subject");
        System.out.println("---cpdf_setKeywords()");
        jcpdf.setKeywords(pdf30, "keywords");
        System.out.println("---cpdf_setCreator()");
        jcpdf.setCreator(pdf30, "creator");
        System.out.println("---cpdf_setProducer()");
        jcpdf.setProducer(pdf30, "producer");
        System.out.println("---cpdf_setCreationDate()");
        jcpdf.setCreationDate(pdf30, "now");
        System.out.println("---cpdf_setModificationDate()");
        jcpdf.setModificationDate(pdf30, "now");
        System.out.println("---cpdf_setTitleXMP()");
        jcpdf.setTitleXMP(pdf30, "title");
        System.out.println("---cpdf_setAuthorXMP()");
        jcpdf.setAuthorXMP(pdf30, "author");
        System.out.println("---cpdf_setSubjectXMP()");
        jcpdf.setSubjectXMP(pdf30, "subject");
        System.out.println("---cpdf_setKeywordsXMP()");
        jcpdf.setKeywordsXMP(pdf30, "keywords");
        System.out.println("---cpdf_setCreatorXMP()");
        jcpdf.setCreatorXMP(pdf30, "creator");
        System.out.println("---cpdf_setProducerXMP()");
        jcpdf.setProducerXMP(pdf30, "producer");
        System.out.println("---cpdf_setCreationDateXMP()");
        jcpdf.setCreationDateXMP(pdf30, "now");
        System.out.println("---cpdf_setModificationDateXMP()");
        jcpdf.setModificationDateXMP(pdf30, "now");
        jcpdf.toFile(pdf30, "testoutputs/11setinfo.pdf", false, false);
        //FIXME: ref
        /*int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        int hour_offset = 0;
        int minute_offset = 0;
        System.out.println("---cpdf_getDateComponents()");
        jcpdf.getDateComponents("D:20061108125017Z", ref year, ref month, ref day, ref hour, ref minute, ref second, ref hour_offset, ref minute_offset);
        System.out.format("D:20061108125017Z = {year}, {month}, {day}, {hour}, {minute}, {second}, {hour_offset}, {minute_offset}");
        System.out.println("---cpdf_dateStringOfComponents()");
        String datestr = jcpdf.dateStringOfComponents(year, month, day, hour, minute, second, hour_offset, minute_offset);
        System.out.println(datestr);*/
        
        System.out.println("---cpdf_getPageRotation()");
        int rot = jcpdf.getPageRotation(pdf30, 1);
        System.out.format("/Rotate on page 1 = %d\n", rot);
        System.out.println("---cpdf_hasBox()");
        boolean hasbox = jcpdf.hasBox(pdf30, 1, "/CropBox");
        System.out.format("hasbox: %d\n", hasbox ? 1 : 0);

        //FIXME: refs
        /*double mb_minx = 0.0;
        double mb_maxx = 0.0;
        double mb_miny = 0.0;
        double mb_maxy = 0.0;
        double cb_minx = 0.0;
        double cb_maxx = 0.0;
        double cb_miny = 0.0;
        double cb_maxy = 0.0;
        double tb_minx = 0.0;
        double tb_maxx = 0.0;
        double tb_miny = 0.0;
        double tb_maxy = 0.0;
        double ab_minx = 0.0;
        double ab_maxx = 0.0;
        double ab_miny = 0.0;
        double ab_maxy = 0.0;
        double bb_minx = 0.0;
        double bb_maxx = 0.0;
        double bb_miny = 0.0;
        double bb_maxy = 0.0;
        System.out.println("---cpdf_getMediaBox()");
        jcpdf.getMediaBox(pdf30, 1, ref mb_minx, ref mb_maxx, ref mb_miny, ref mb_maxy);
        System.out.format("Media: {mb_minx:0.000000} {mb_maxx:0.000000} {mb_miny:0.000000} {mb_maxy:0.000000}");
        System.out.println("---cpdf_getCropBox()");
        jcpdf.getCropBox(pdf30, 1, ref cb_minx, ref cb_maxx, ref cb_miny, ref cb_maxy);
        System.out.format("Crop: {cb_minx:0.000000} {cb_maxx:0.000000} {cb_miny:0.000000} {cb_maxy:0.000000}");
        System.out.println("---cpdf_getBleedBox()");
        jcpdf.getBleedBox(pdf30, 1, ref bb_minx, ref bb_maxx, ref bb_miny, ref bb_maxy);
        System.out.format("Bleed: {bb_minx:0.000000} {bb_maxx:0.000000} {bb_miny:0.000000} {bb_maxy:0.000000}");
        System.out.println("---cpdf_getArtBox()");
        jcpdf.getArtBox(pdf30, 1, ref ab_minx, ref ab_maxx, ref ab_miny, ref ab_maxy);
        System.out.format("Art: {ab_minx:0.000000} {ab_maxx:0.000000} {ab_miny:0.000000} {ab_maxy:0.000000}");
        System.out.println("---cpdf_getTrimBox()");
        jcpdf.getTrimBox(pdf30, 1, ref tb_minx, ref tb_maxx, ref tb_miny, ref tb_maxy);
        System.out.format("Trim: {tb_minx:0.000000} {tb_maxx:0.000000} {tb_miny:0.000000} {tb_maxy:0.000000}");*/

        System.out.println("---cpdf_setMediaBox()");
        jcpdf.setMediabox(pdf30, jcpdf.all(pdf30), 100, 500, 150, 550);
        System.out.println("---cpdf_setCropBox()");
        jcpdf.setCropBox(pdf30, jcpdf.all(pdf30), 100, 500, 150, 550);
        System.out.println("---cpdf_setTrimBox()");
        jcpdf.setTrimBox(pdf30, jcpdf.all(pdf30), 100, 500, 150, 550);
        System.out.println("---cpdf_setArtBox()");
        jcpdf.setArtBox(pdf30, jcpdf.all(pdf30), 100, 500, 150, 550);
        System.out.println("---cpdf_setBleedBox()");
        jcpdf.setBleedBox(pdf30, jcpdf.all(pdf30), 100, 500, 150, 550);
        jcpdf.toFile(pdf30, "testoutputs/11setboxes.pdf", false, false);
        System.out.println("---cpdf_markTrapped()");
        jcpdf.markTrapped(pdf30);
        System.out.println("---cpdf_markTrappedXMP()");
        jcpdf.markTrappedXMP(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11trapped.pdf", false, false);
        System.out.println("---cpdf_markUntrapped()");
        jcpdf.markUntrapped(pdf30);
        System.out.println("---cpdf_markUntrappedXMP()");
        jcpdf.markUntrappedXMP(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11untrapped.pdf", false, false);
        System.out.println("---cpdf_setPageLayout()");
        jcpdf.setPageLayout(pdf30, jcpdf.twoColumnLeft);
        System.out.println("---cpdf_setPageMode()");
        jcpdf.setPageMode(pdf30, jcpdf.useOutlines);
        System.out.println("---cpdf_hideToolbar()");
        jcpdf.hideToolbar(pdf30, true);
        System.out.println("---cpdf_hideMenubar()");
        jcpdf.hideMenubar(pdf30, true);
        System.out.println("---cpdf_hideWindowUi()");
        jcpdf.hideWindowUi(pdf30, true);
        System.out.println("---cpdf_fitWindow()");
        jcpdf.fitWindow(pdf30, true);
        System.out.println("---cpdf_centerWindow()");
        jcpdf.centerWindow(pdf30, true);
        System.out.println("---cpdf_displayDocTitle()");
        jcpdf.displayDocTitle(pdf30, true);
        System.out.println("---cpdf_openAtPage()");
        jcpdf.openAtPage(pdf30, true, 4);
        jcpdf.toFile(pdf30, "testoutputs/11open.pdf", false, false);
        System.out.println("---cpdf_setMetadataFromFile()");
        jcpdf.setMetadataFromFile(pdf30, "testinputs/cpdflibmanual.pdf");
        jcpdf.toFile(pdf30, "testoutputs/11metadata1.pdf", false, false);

        System.out.println("---cpdf_setMetadataFromByteArray()");
        byte[] md = "BYTEARRAY".getBytes();
        jcpdf.setMetadataFromByteArray(pdf30, md);
        jcpdf.toFile(pdf30, "testoutputs/11metadata2.pdf", false, false);
        System.out.println("---cpdf_getMetadata()");
        byte[] metadata = jcpdf.getMetadata(pdf30);

        System.out.println("---cpdf_removeMetadata()");
        jcpdf.removeMetadata(pdf30);
        System.out.println("---cpdf_createMetadata()");
        jcpdf.createMetadata(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11metadata3.pdf", false, false);
        System.out.println("---cpdf_setMetadataDate()");
        jcpdf.setMetadataDate(pdf30, "now");
        jcpdf.toFile(pdf30, "testoutputs/11metadata4.pdf", false, false);
        System.out.println("---cpdf_addPageLabels()");
        jcpdf.addPageLabels(pdf30, jcpdf.uppercaseRoman, "PREFIX-", 1, jcpdf.all(pdf30), false);
        System.out.println("---cpdf: get page labels");
        int pls = jcpdf.startGetPageLabels(pdf30);
        System.out.format("There are %d labels\n", pls);
        for (int plsc = 0; plsc < pls; plsc++)
        {
            int style = jcpdf.getPageLabelStyle(plsc);
            String prefix = jcpdf.getPageLabelPrefix(plsc);
            int offset = jcpdf.getPageLabelOffset(plsc);
            int lab_range = jcpdf.getPageLabelRange(plsc);
            System.out.format("Page label: %d, %s, %d, %d\n", style, prefix, offset, lab_range);
        }
        jcpdf.endGetPageLabels();
        System.out.println("---cpdf_removePageLabels()");
        jcpdf.removePageLabels(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11pagelabels.pdf", false, false);
        System.out.println("---cpdf_getPageLabelStringForPage()");
        String pl = jcpdf.getPageLabelStringForPage(pdf30, 1);
        System.out.format("Label string is %s\n", pl);
        
        /* CHAPTER 12. File Attachments */
        System.out.println("***** CHAPTER 12. File Attachments");
        int attachments = jcpdf.fromFile("testinputs/has_attachments.pdf", "");
        System.out.println("---cpdf_attachFile()");
        jcpdf.attachFile("testinputs/image.pdf", attachments);
        System.out.println("---cpdf_attachFileToPage()");
        jcpdf.attachFileToPage("testinputs/image.pdf", attachments, 1);
        System.out.println("---cpdf_attachFileFromMemory()");
        /*byte[] empty = {};
        jcpdf.attachFileFromMemory(empty, "metadata.txt", attachments);
        System.out.println("---cpdf_attachFileToPageFromMemory()");
        jcpdf.attachFileToPageFromMemory(empty, "metadata.txt", attachments, 1);*/
        jcpdf.toFile(attachments, "testoutputs/12with_attachments.pdf", false, false);
        System.out.println("---cpdf: get attachments");
        jcpdf.startGetAttachments(attachments);
        int n_a = jcpdf.numberGetAttachments();
        System.out.format("There are %d attachments to get\n", n_a);
        for (int aa = 0; aa < n_a; aa++)
        {
            String a_n = jcpdf.getAttachmentName(aa);
            System.out.format("Attachment %d is named %s\n", aa, a_n);
            int a_page = jcpdf.getAttachmentPage(aa);
            System.out.format("It is on page %d\n", a_page);
            /*byte[] a_data = jcpdf.getAttachmentData(aa);
            System.out.println($"Contains {a_data.Length} bytes of data");*/
        }
        jcpdf.endGetAttachments();
        System.out.println("---cpdf_removeAttachedFiles()");
        jcpdf.removeAttachedFiles(attachments);
        jcpdf.toFile(attachments, "testoutputs/12removed_attachments.pdf", false, false);
        
        /* CHAPTER 13. Images. */
        System.out.println("***** CHAPTER 13. Images");
        System.out.println("---cpdf: get image resolution");
        int image_pdf = jcpdf.fromFile("testinputs/image.pdf", "");
        int im_n = jcpdf.startGetImageResolution(image_pdf, 2.0);
        for (int im = 0; im < im_n; im++)
        {
            int im_p = jcpdf.getImageResolutionPageNumber(im);
            String im_name = jcpdf.getImageResolutionImageName(im);
            int im_xp = jcpdf.getImageResolutionXPixels(im);
            int im_yp = jcpdf.getImageResolutionYPixels(im);
            double im_xres = jcpdf.getImageResolutionXRes(im);
            double im_yres = jcpdf.getImageResolutionYRes(im);
            System.out.format("IMAGE: %d, %s, %d, %d, %f, %f\n", im_p, im_name, im_xp, im_yp, im_xres, im_yres);
        }
        jcpdf.endGetImageResolution();

        /* CHAPTER 14. Fonts. */
        System.out.println("***** CHAPTER 14. Fonts");
        System.out.println("---cpdf: Get Fonts");
        int fonts = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int fonts2 = jcpdf.fromFile("testinputs/frontmatter.pdf", "");
        jcpdf.startGetFontInfo(fonts);
        int n_fonts = jcpdf.numberFonts();
        for (int ff = 0; ff < n_fonts; ff++)
        {
            int page = jcpdf.getFontPage(ff);
            String f_name = jcpdf.getFontName(ff);
            String type = jcpdf.getFontType(ff);
            String encoding = jcpdf.getFontEncoding(ff);
            System.out.format("Page %d, font %s has type %s and encoding %s\n", page, f_name, type, encoding);
        }
        jcpdf.endGetFontInfo();
        System.out.println("---cpdf_removeFonts()");
        jcpdf.removeFonts(fonts);
        jcpdf.toFile(fonts, "testoutputs/14remove_fonts.pdf", false, false);
        System.out.println("---cpdf_copyFont()");
        jcpdf.copyFont(fonts, fonts2, jcpdf.all(fonts), 1, "/Font");
        /* CHAPTER 15. PDF and JSON */
        System.out.println("***** CHAPTER 15. PDF and JSON");
        int jsonpdf = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_outputJSON()");
        jcpdf.outputJSON("testoutputs/15json.json", false, false, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsonnostream.json", false, true, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsonparsed.json", true, false, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsondecomp.json", false, false, true, jsonpdf);
        System.out.println("---cpdf_fromJSON()");
        int fromjsonpdf = jcpdf.fromJSON("testoutputs/15jsonparsed.json");
        jcpdf.toFile(fromjsonpdf, "testoutputs/15fromjson.pdf", false, false);
        System.out.println("---cpdf_outputJSONMemory()");
        
        byte[] jbuf = jcpdf.outputJSONMemory(fromjsonpdf, false, false, false);
        System.out.println("---cpdf_fromJSONMemory()");
        int jfrommem = jcpdf.fromJSONMemory(jbuf);
        jcpdf.toFile(jfrommem, "testoutputs/15fromJSONMemory.pdf", false, false);

        /* CHAPTER 16. Optional Content Groups */
        System.out.println("***** CHAPTER 16. Optional Content Groups");
        int ocg = jcpdf.fromFile("testinputs/has_ocgs.pdf", "");
        System.out.println("---cpdf: Get OCG List");
        int n2 = jcpdf.startGetOCGList(ocg);
        for(int x = 0; x < n2; x++)
        {
            System.out.println(jcpdf.OCGListEntry(x));
        }
        jcpdf.endGetOCGList();
        System.out.println("---cpdf_OCGCoalesce()");
        jcpdf.OCGCoalesce(ocg);
        System.out.println("---cpdf_OCGRename()");
        jcpdf.OCGRename(ocg, "From", "To");
        System.out.println("---cpdf_OCGOrderAll()");
        jcpdf.OCGOrderAll(ocg);

        /* CHAPTER 17. Creating New PDFs */
        System.out.println("***** CHAPTER 17. Creating New PDFs");
        System.out.println("---cpdf_blankDocument()");
        System.out.println("---cpdf_blankDocumentPaper()");
        int new1 = jcpdf.blankDocument(100.0, 200.0, 20);
        int new2 = jcpdf.blankDocumentPaper(jcpdf.a4portrait, 10);
        jcpdf.toFile(new1, "testoutputs/01blank.pdf", false, false);
        jcpdf.toFile(new2, "testoutputs/01blanka4.pdf", false, false);
        System.out.println("---cpdf_textToPDF()");
        System.out.println("---cpdf_textToPDFPaper()");
        int ttpdf = jcpdf.textToPDF(500.0, 600.0, jcpdf.timesItalic, 8.0, "../cpdflib-source/cpdflibtest.c");
        int ttpdfpaper = jcpdf.textToPDFPaper(jcpdf.a4portrait, jcpdf.timesBoldItalic, 10.0, "../cpdflib-source/cpdflibtest.c");
        jcpdf.toFile(ttpdf, "testoutputs/01ttpdf.pdf", false, false);
        jcpdf.toFile(ttpdfpaper, "testoutputs/01ttpdfpaper.pdf", false, false);

        /* CHAPTER 18. Miscellaneous */
        System.out.println("***** CHAPTER 18. Miscellaneous");
        int misc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc8 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc9 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc12 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc13 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc14 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc15 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misc16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        int misclogo = jcpdf.fromFile("testinputs/logo.pdf", "");
        System.out.println("---cpdf_draft()");
        jcpdf.draft(misc, jcpdf.all(misc), true);
        jcpdf.toFile(misc, "testoutputs/17draft.pdf", false, false);
        System.out.println("---cpdf_removeAllText()");
        jcpdf.removeAllText(misc2, jcpdf.all(misc2));
        jcpdf.toFile(misc2, "testoutputs/17removealltext.pdf", false, false);
        System.out.println("---cpdf_blackText()");
        jcpdf.blackText(misc3, jcpdf.all(misc3));
        jcpdf.toFile(misc3, "testoutputs/17blacktext.pdf", false, false);
        System.out.println("---cpdf_blackLines()");
        jcpdf.blackLines(misc4, jcpdf.all(misc4));
        jcpdf.toFile(misc4, "testoutputs/17blacklines.pdf", false, false);
        System.out.println("---cpdf_blackFills()");
        jcpdf.blackFills(misc5, jcpdf.all(misc5));
        jcpdf.toFile(misc5, "testoutputs/17blackfills.pdf", false, false);
        System.out.println("---cpdf_thinLines()");
        jcpdf.thinLines(misc6, jcpdf.all(misc6), 2.0);
        jcpdf.toFile(misc6, "testoutputs/17thinlines.pdf", false, false);
        System.out.println("---cpdf_copyId()");
        jcpdf.copyId(misclogo, misc7);
        jcpdf.toFile(misc7, "testoutputs/17copyid.pdf", false, false);
        System.out.println("---cpdf_removeId()");
        jcpdf.removeId(misc8);
        jcpdf.toFile(misc8, "testoutputs/17removeid.pdf", false, false);
        System.out.println("---cpdf_setVersion()");
        jcpdf.setVersion(misc9, 1);
        jcpdf.toFile(misc9, "testoutputs/17setversion.pdf", false, false);
        System.out.println("---cpdf_setFullVersion()");
        jcpdf.setFullVersion(misc10, 2, 0);
        jcpdf.toFile(misc10, "testoutputs/17setfullversion.pdf", false, false);
        System.out.println("---cpdf_removeDictEntry()");
        jcpdf.removeDictEntry(misc11, "/Producer");
        jcpdf.toFile(misc11, "testoutputs/17removedictentry.pdf", false, false);
        System.out.println("---cpdf_removeDictEntrySearch()");
        jcpdf.removeDictEntrySearch(misc13, "/Producer", "1");
        jcpdf.toFile(misc13, "testoutputs/17removedictentrysearch.pdf", false, false);
        System.out.println("---cpdf_replaceDictEntry()");
        jcpdf.replaceDictEntry(misc14, "/Producer", "{\"I\" : 1}");
        jcpdf.toFile(misc14, "testoutputs/17replacedictentry.pdf", false, false);
        System.out.println("---cpdf_replaceDictEntrySearch()");
        jcpdf.replaceDictEntrySearch(misc15, "/Producer", "1", "2");
        jcpdf.toFile(misc15, "testoutputs/17replacedictentrysearch.pdf", false, false);
        System.out.println("---cpdf_getDictEntries()");
        byte[] entries = jcpdf.getDictEntries(misc16, "/Producer");
        System.out.format("length of entries data = %d\n", entries.length);
        System.out.println("---cpdf_removeClipping()");
        jcpdf.removeClipping(misc12, jcpdf.all(misc12));
        jcpdf.toFile(misc12, "testoutputs/17removeclipping.pdf", false, false);
    }
}
