/* Jcpdf.java */

public class Jcpdf {
    native void startup();
    native String version();
    native void setFast();
    native void setSlow();
    native int fromFile(String filename, String userpw);
    native int fromFileLazy(String filename, String userpw);
    native int blankDocument(double w, double h, int pages);
    native int blankDocumentPaper(int papersize, int pages);
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
    native void tableOfContents(int pdf, int font, double fontsize, String title, boolean bookmark);
    native void removeText(int pdf, int range);
    native int textWidth(int font, String text);
    native void stampOn(int pdf, int pdf2, int range);
    native void stampUnder(int pdf, int pdf2, int range);
    native int combinePages(int pdf, int pdf2);
    native void impose(int pdf, double x, double y, boolean fit, boolean columns, boolean rtl, boolean btt, boolean center, double margin, double spacing, double linewidth);
    native void twoUp(int pdf);
    native void twoUpStack(int pdf);
    native void padBefore(int pdf, int range);
    native void padAfter(int pdf, int range);
    native void padEvery(int pdf, int n);
    native void padMultiple(int pdf, int n);
    native void padMultipleBefore(int pdf, int n);
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

        //FIXME: Implement in/out to memory
        
        /*Console.WriteLine("---cpdf_toMemory()");
        byte[] mempdf = Cpdf.toMemory(pdf, false, false);
        Console.WriteLine("---cpdf_fromMemory()");
        Cpdf.Pdf frommem = Cpdf.fromMemory(mempdf, "");
        Cpdf.toFile(frommem, "testoutputs/01fromMemory.pdf", false, false);
        Console.WriteLine("---cpdf_fromMemoryLazy()");
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

        //FIXME: Implement arrays/lists
        
        /*List<Cpdf.Permission> permissions = new List<Cpdf.Permission> {Cpdf.Permission.NoEdit};
        System.out.println("---cpdf_toFileEncrypted()");
        Cpdf.toFileEncrypted(pdf400, Cpdf.EncryptionMethod.Pdf40bit, permissions, "owner", "user", false, false, "testoutputs/01encrypted.pdf");
        System.out.println("---cpdf_toFileEncryptedExt()");
        Cpdf.toFileEncryptedExt(pdf401, Cpdf.EncryptionMethod.Pdf40bit, permissions, "owner", "user", false, false, true, true, true, "testoutputs/01encryptedext.pdf");*/
        /*System.out.println("---cpdf_hasPermission()");
        int pdfenc = Cpdf.fromFile("testoutputs/01encrypted.pdf", "user")
        bool hasnoedit = Cpdf.hasPermission(pdfenc, Cpdf.Permission.NoEdit);
        bool hasnocopy = Cpdf.hasPermission(pdfenc, Cpdf.Permission.NoCopy);
        System.out.println($"Haspermission {(hasnoedit ? 1 : 0)}, {(hasnocopy ? 1 : 0)}");
        System.out.println("---cpdf_encryptionKind()");
        int enckind = Cpdf.encryptionKind(pdfenc);
        System.out.println($"encryption kind is {enckind}");
        System.out.println("---cpdf_decryptPdf()");
        Cpdf.decryptPdf(pdf10, "");
        System.out.println("---cpdf_decryptPdfOwner()");
        Cpdf.decryptPdfOwner(pdf10, "");*/
        
        /* CHAPTER 2. Merging and Splitting */
        /*Console.WriteLine("***** CHAPTER 2. Merging and Splitting");
        using (Cpdf.Pdf pdf11 = Cpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            List<int> selectrange = Cpdf.range(1, 3);
            Console.WriteLine("---cpdf_mergeSimple()");
            Cpdf.Pdf[] arr = new [] {pdf11, pdf11, pdf11};
            List<Cpdf.Pdf> arr_list = new List<Cpdf.Pdf> {};
            arr_list.AddRange(arr);
            Cpdf.Pdf merged = Cpdf.mergeSimple(arr_list);
            Cpdf.toFile(merged, "testoutputs/02merged.pdf", false, true);
            merged.Dispose();
            Console.WriteLine("---cpdf_merge()");
            Cpdf.Pdf merged2 = Cpdf.merge(arr_list, false, false);
            Cpdf.toFile(merged2, "testoutputs/02merged2.pdf", false, true);
            merged2.Dispose();
            Console.WriteLine("---cpdf_mergeSame()");
            List<List<int>> ranges = new List<List<int>> {Cpdf.all(pdf11), Cpdf.all(pdf11), Cpdf.all(pdf11)};
            Cpdf.Pdf merged3 = Cpdf.mergeSame(arr_list, false, false, ranges);
            Cpdf.toFile(merged3, "testoutputs/02merged3.pdf", false, false);
            merged3.Dispose();
            Console.WriteLine("---cpdf_selectPages()");
            Cpdf.Pdf pdf12 = Cpdf.selectPages(pdf11, selectrange);
            Cpdf.toFile(pdf12, "testoutputs/02selected.pdf", false, false);
            pdf12.Dispose();
        }*/
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
        //FIXME data in and out
        /*System.out.println("---jcpdf.getBookmarksJSON()");
        jcpdf.Pdf marksjson = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] marksdata = jcpdf.getBookmarksJSON(marksjson);
        System.out.println($"Contains {marksdata.Length} bytes of data");
        System.out.println("---jcpdf.setBookmarksJSON()");
        jcpdf.setBookmarksJSON(marksjson, marksdata);
        jcpdf.toFile(marksjson, "testoutputs/06jsonmarks.pdf", false, false);
        */
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

        // FIXME data in/out
        /*
        string name = Cpdf.stampAsXObject(undoc, Cpdf.all(undoc), ulogo);
        string content = $"q 1 0 0 1 100 100 cm {name} Do Q q 1 0 0 1 300 300 cm {name} Do Q q 1 0 0 1 500 500 cm {name} Do Q";
        Console.WriteLine("---cpdf_addContent()");
        Cpdf.addContent(content, true, undoc, Cpdf.all(undoc));
        Cpdf.toFile(undoc, "testoutputs/08demo.pdf", false, false);
        */
        
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
        /*
        using (Cpdf.Pdf annot = Cpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            byte[] annotjson = Cpdf.annotationsJSON(annot);
            Console.WriteLine($"Contains {annotjson.Length} bytes of data");
        }*/
        
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
        
        //FIXME DO NOW
        /*System.out.println("---cpdf_getPageRotation()");
        int rot = jcpdf.getPageRotation(pdf30, 1);
        System.out.format("/Rotate on page 1 = {rot}");
        System.out.println("---cpdf_hasBox()");
        boolean hasbox = jcpdf.hasBox(pdf30, 1, "/CropBox");
        System.out.format("hasbox: {(hasbox ? 1 : 0)}");*/

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

        //FIXME DO NOW
        /*
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
        jcpdf.setPageLayout(pdf30, jcpdf.Layout.TwoColumnLeft);
        System.out.println("---cpdf_setPageMode()");
        jcpdf.setPageMode(pdf30, jcpdf.PageMode.UseOutlines);
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
        jcpdf.toFile(pdf30, "testoutputs/11metadata1.pdf", false, false);*/

        //FIXME bytes in/out
        /*System.out.println("---cpdf_setMetadataFromByteArray()");
        byte[] md = Encoding.ASCII.GetBytes("BYTEARRAY");
        jcpdf.setMetadataFromByteArray(pdf30, md);
        jcpdf.toFile(pdf30, "testoutputs/11metadata2.pdf", false, false);
        System.out.println("---cpdf_getMetadata()");
        byte[] metadata = jcpdf.getMetadata(pdf30);*/

        //FIXME DO NOW
        /*System.out.println("---cpdf_removeMetadata()");
        jcpdf.removeMetadata(pdf30);
        System.out.println("---cpdf_createMetadata()");
        jcpdf.createMetadata(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11metadata3.pdf", false, false);
        System.out.println("---cpdf_setMetadataDate()");
        jcpdf.setMetadataDate(pdf30, "now");
        jcpdf.toFile(pdf30, "testoutputs/11metadata4.pdf", false, false);
        System.out.println("---cpdf_addPageLabels()");
        jcpdf.addPageLabels(pdf30, jcpdf.PageLabelStyle.UppercaseRoman, "PREFIX-", 1, jcpdf.all(pdf30), false);
        System.out.println("---cpdf: get page labels");
        int pls = jcpdf.startGetPageLabels(pdf30);
        System.out.format("There are {pls} labels");
        for (int plsc = 0; plsc < pls; plsc++)
        {
            int style = jcpdf.getPageLabelStyle(plsc);
            string prefix = jcpdf.getPageLabelPrefix(plsc);
            int offset = jcpdf.getPageLabelOffset(plsc);
            int lab_range = jcpdf.getPageLabelRange(plsc);
            System.out.format("Page label: {style}, {prefix}, {offset}, {lab_range}");
        }
        jcpdf.endGetPageLabels();
        System.out.println("---cpdf_removePageLabels()");
        jcpdf.removePageLabels(pdf30);
        jcpdf.toFile(pdf30, "testoutputs/11pagelabels.pdf", false, false);
        System.out.println("---cpdf_getPageLabelStringForPage()");
        String pl = jcpdf.getPageLabelStringForPage(pdf30, 1);
        System.out.format("Label string is {pl}");*/
    }
}
