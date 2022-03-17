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
        System.out.format("Validating pagespec gives %b\n", valid);
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
        System.out.format("isencrypted: %b\n", isenc);
        System.out.println("---cpdf_isLinearized()");
        boolean lin = jcpdf.isLinearized("testinputs/cpdfmanual.pdf");
        System.out.format("islinearized: %b\n", lin);

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
            System.out.format("Bookmark at level %d points to page %d and has text \"%s\" and open %b\n", level, page, text, open);
        }
        jcpdf.endGetBookmarkInfo();
        System.out.println("---jcpdf: set bookmarks");
        jcpdf.startSetBookmarkInfo(1);
        jcpdf.setBookmarkLevel(0, 0);
        jcpdf.setBookmarkPage(pdf17, 0, 20);
        jcpdf.setBookmarkOpenStatus(0, true);
        jcpdf.setBookmarkText(0, "New bookmark!");
        jcpdf.endSetBookmarkInfo(pdf17);
        jcpdf.toFile(pdf17, "testoutputs/06newmarks.pdf", false, false);
        /*System.out.println("---jcpdf.getBookmarksJSON()");
        jcpdf.Pdf marksjson = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] marksdata = jcpdf.getBookmarksJSON(marksjson);
        System.out.println($"Contains {marksdata.Length} bytes of data");
        System.out.println("---jcpdf.setBookmarksJSON()");
        jcpdf.setBookmarksJSON(marksjson, marksdata);
        jcpdf.toFile(marksjson, "testoutputs/06jsonmarks.pdf", false, false);
        marksjson.Dispose();
        System.out.println("---jcpdf.tableOfContents()");
        jcpdf.Pdf tojcpdf.= jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        jcpdf.tableOfContents(tojcpdf. jcpdf.Font.TimesRoman, 12.0, "Table of Contents", false);
        jcpdf.toFile(tojcpdf. "testoutputs/06toc.pdf", false, false);
        tojcpdf.Dispose();*/
    }
}
