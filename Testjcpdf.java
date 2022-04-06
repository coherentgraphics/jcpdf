import com.coherentpdf.Jcpdf;

public class Testjcpdf
{
    static void chapter0(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
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
    }

    static void chapter1(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 1. Basics */
        System.out.println("***** CHAPTER 1. Basics");
        System.out.println("---cpdf_fromFile()");
        Jcpdf.Pdf pdf = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_fromFileLazy()");
        Jcpdf.Pdf pdf2 = jcpdf.fromFileLazy("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_toMemory()");
        byte[] mempdf = jcpdf.toMemory(pdf, false, false);
        System.out.println("---cpdf_fromMemory()");
        Jcpdf.Pdf frommem = jcpdf.fromMemory(mempdf, "");
        jcpdf.toFile(frommem, "testoutputs/01fromMemory.pdf", false, false);
        System.out.println("---cpdf_fromMemoryLazy()");
        Jcpdf.Pdf frommemlazy = jcpdf.fromMemoryLazy(mempdf, "");
        jcpdf.toFile(frommemlazy, "testoutputs/01fromMemoryLazy.pdf", false, false);
        jcpdf.fromMemoryLazyRelease(mempdf);
        Jcpdf.Pdf pdf3 = jcpdf.blankDocument(153.5, 234.2, 50);
        Jcpdf.Pdf pdf4 = jcpdf.blankDocumentPaper(jcpdf.a4landscape, 50);
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
        Jcpdf.Range range = jcpdf.range(1, 10);
        System.out.println("---cpdf_all()");
        Jcpdf.Range all = jcpdf.all(pdf3);
        System.out.println("---cpdf_even()");
        Jcpdf.Range even = jcpdf.even(all);
        System.out.println("---cpdf_odd()");
        Jcpdf.Range odd = jcpdf.odd(all);
        System.out.println("---cpdf_rangeUnion()");
        Jcpdf.Range union = jcpdf.rangeUnion(even, odd);
        System.out.println("---cpdf_difference()");
        Jcpdf.Range diff = jcpdf.difference(even, odd);
        System.out.println("---cpdf_removeDuplicates()");
        Jcpdf.Range revdup = jcpdf.removeDuplicates(even);
        System.out.println("---cpdf_rangeLength()");
        int length = jcpdf.rangeLength(even);
        System.out.println("---cpdf_rangeGet()");
        int rangeget = jcpdf.rangeGet(even, 1);
        System.out.println("---cpdf_rangeAdd()");
        Jcpdf.Range rangeadd = jcpdf.rangeAdd(even, 20);
        System.out.println("---cpdf_isInRange()");
        boolean isin = jcpdf.isInRange(even, 2);
        System.out.println("---cpdf_parsePagespec()");
        Jcpdf.Range r = jcpdf.parsePagespec(pdf3, "1-5");
        System.out.println("---cpdf_validatePagespec()");
        boolean valid = jcpdf.validatePagespec("1-4,5,6");
        System.out.format("Validating pagespec gives %d\n", valid ? 1 : 0);
        System.out.println("---cpdf_stringOfPagespec()");
        String ps = jcpdf.stringOfPagespec(pdf3, r);
        System.out.format("String of pagespec is %s\n", ps);
        System.out.println("---cpdf_blankRange()");
        Jcpdf.Range b = jcpdf.blankRange();
        Jcpdf.Pdf pdf10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
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
        try
           (Jcpdf.Pdf pdf400 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
            Jcpdf.Pdf pdf401 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            int[] permissions = new int[] {jcpdf.noEdit};
            System.out.println("---cpdf_toFileEncrypted()");
            jcpdf.toFileEncrypted(pdf400, jcpdf.pdf40bit, permissions, "owner", "user", false, false, "testoutputs/01encrypted.pdf");
            System.out.println("---cpdf_toFileEncryptedExt()");
            jcpdf.toFileEncryptedExt(pdf401, jcpdf.pdf40bit, permissions, "owner", "user", false, false, true, true, true, "testoutputs/01encryptedext.pdf");
            System.out.println("---cpdf_hasPermission()");
        }
        try (Jcpdf.Pdf pdfenc = jcpdf.fromFile("testoutputs/01encrypted.pdf", "user"))
        {
            boolean hasnoedit = jcpdf.hasPermission(pdfenc, jcpdf.noEdit);
            boolean hasnocopy = jcpdf.hasPermission(pdfenc, jcpdf.noCopy);
            System.out.format("Haspermission %d, %d\n", hasnoedit ? 1 : 0, hasnocopy ? 1 : 0);
            System.out.println("---cpdf_encryptionKind()");
            int enckind = jcpdf.encryptionKind(pdfenc);
            System.out.format("encryption kind is %d\n", enckind);
        }
        System.out.println("---cpdf_decryptPdf()");
        jcpdf.decryptPdf(pdf10, "");
        System.out.println("---cpdf_decryptPdfOwner()");
        jcpdf.decryptPdfOwner(pdf10, "");
        pdf.close();
        pdf2.close();
        pdf3.close();
        pdf4.close();
        pdf10.close();
        frommem.close();
        frommemlazy.close();
        range.close();
        all.close();
        even.close();
        odd.close();
        union.close();
        diff.close();
        revdup.close();
        r.close();
        b.close();
        rangeadd.close();
    }
   
    static void chapter2(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 2. Merging and Splitting */
        System.out.println("***** CHAPTER 2. Merging and Splitting");
        try (Jcpdf.Pdf pdf11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            Jcpdf.Range selectrange = jcpdf.range(1, 3);
            System.out.println("---cpdf_mergeSimple()");
            Jcpdf.Pdf[] arr = new Jcpdf.Pdf[] {pdf11, pdf11, pdf11};
            Jcpdf.Pdf merged = jcpdf.mergeSimple(arr);
            jcpdf.toFile(merged, "testoutputs/02merged.pdf", false, true);
            merged.close();
            System.out.println("---cpdf_merge()");
            Jcpdf.Pdf merged2 = jcpdf.merge(arr, false, false);
            jcpdf.toFile(merged2, "testoutputs/02merged2.pdf", false, true);
            merged2.close();
            System.out.println("---cpdf_mergeSame()");
            Jcpdf.Range all = jcpdf.all(pdf11);
            Jcpdf.Range[] ranges = new Jcpdf.Range[] {all, all, all};
            Jcpdf.Pdf merged3 = jcpdf.mergeSame(arr, false, false, ranges);
            jcpdf.toFile(merged3, "testoutputs/02merged3.pdf", false, false);
            merged3.close();
            System.out.println("---cpdf_selectPages()");
            Jcpdf.Pdf pdf12 = jcpdf.selectPages(pdf11, selectrange);
            jcpdf.toFile(pdf12, "testoutputs/02selected.pdf", false, false);
            pdf12.close();
            selectrange.close();
            all.close();
        }
    }
   
    static void chapter3(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 3. Pages */
        System.out.println("***** CHAPTER 3. Pages");
        try (Jcpdf.Pdf pagespdf1 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r1 = jcpdf.all(pagespdf1);
             Jcpdf.Pdf pagespdf2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r2 = jcpdf.all(pagespdf2);
             Jcpdf.Pdf pagespdf3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r3 = jcpdf.all(pagespdf3);
             Jcpdf.Pdf pagespdf4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r4 = jcpdf.all(pagespdf4);
             Jcpdf.Pdf pagespdf5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r5 = jcpdf.all(pagespdf5);
             Jcpdf.Pdf pagespdf6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r6 = jcpdf.all(pagespdf6);
             Jcpdf.Pdf pagespdf7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r7 = jcpdf.all(pagespdf7);
             Jcpdf.Pdf pagespdf8 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r8 = jcpdf.all(pagespdf8);
             Jcpdf.Pdf pagespdf9 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r9 = jcpdf.all(pagespdf9);
             Jcpdf.Pdf pagespdf10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r10 = jcpdf.all(pagespdf10);
             Jcpdf.Pdf pagespdf11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r11 = jcpdf.all(pagespdf11);
             Jcpdf.Pdf pagespdf12 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r12 = jcpdf.all(pagespdf12);
             Jcpdf.Pdf pagespdf13 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r13 = jcpdf.all(pagespdf13);
             Jcpdf.Pdf pagespdf14 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r14 = jcpdf.all(pagespdf14);
             Jcpdf.Pdf pagespdf15 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r15 = jcpdf.all(pagespdf15);
             Jcpdf.Pdf pagespdf16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r16 = jcpdf.all(pagespdf16);
             Jcpdf.Pdf pagespdf17 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r17 = jcpdf.all(pagespdf17);
             Jcpdf.Pdf pagespdf18 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r18 = jcpdf.all(pagespdf18);
             Jcpdf.Pdf pagespdf19 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range r19 = jcpdf.all(pagespdf19))
        {
            System.out.println("---cpdf_scalePages()");
            jcpdf.scalePages(pagespdf1, r1, 1.5, 1.8);
            jcpdf.toFile(pagespdf1, "testoutputs/03scalepages.pdf", false, false);
            System.out.println("---cpdf_scaleToFit()");
            jcpdf.scaleToFit(pagespdf2, r2, 1.5, 1.8, 0.9);
            jcpdf.toFile(pagespdf2, "testoutputs/03scaletofit.pdf", false, false);
            System.out.println("---cpdf_scaleToFitPaper()");
            jcpdf.scaleToFitPaper(pagespdf3, r3, jcpdf.a4portrait, 0.8);
            jcpdf.toFile(pagespdf3, "testoutputs/03scaletofitpaper.pdf", false, false);
            System.out.println("---cpdf_scaleContents()");
            jcpdf.scaleContents(pagespdf4, r4, jcpdf.topLeft, 20.0, 20.0, 2.0);
            jcpdf.toFile(pagespdf4, "testoutputs/03scalecontents.pdf", false, false);
            System.out.println("---cpdf_shiftContents()");
            jcpdf.shiftContents(pagespdf5, r5, 1.5, 1.25);
            jcpdf.toFile(pagespdf5, "testoutputs/03shiftcontents.pdf", false, false);
            System.out.println("---cpdf_rotate()");
            jcpdf.rotate(pagespdf6, r6, 90);
            jcpdf.toFile(pagespdf6, "testoutputs/03rotate.pdf", false, false);
            System.out.println("---cpdf_rotateBy()");
            jcpdf.rotateBy(pagespdf7, r7, 90);
            jcpdf.toFile(pagespdf7, "testoutputs/03rotateby.pdf", false, false);
            System.out.println("---cpdf_rotateContents()");
            jcpdf.rotateContents(pagespdf8, r8, 35.0);
            jcpdf.toFile(pagespdf8, "testoutputs/03rotatecontents.pdf", false, false);
            System.out.println("---cpdf_upright()");
            jcpdf.upright(pagespdf9, r9);
            jcpdf.toFile(pagespdf9, "testoutputs/03upright.pdf", false, false);
            System.out.println("---cpdf_hFlip()");
            jcpdf.hFlip(pagespdf10, r10);
            jcpdf.toFile(pagespdf10, "testoutputs/03hflip.pdf", false, false);
            System.out.println("---cpdf_vFlip()");
            jcpdf.vFlip(pagespdf11, r11);
            jcpdf.toFile(pagespdf11, "testoutputs/03vflip.pdf", false, false);
            System.out.println("---cpdf_crop()");
            jcpdf.crop(pagespdf12, r12, 0.0, 0.0, 400.0, 500.0);
            jcpdf.toFile(pagespdf12, "testoutputs/03crop.pdf", false, false);
            System.out.println("---cpdf_trimMarks()");
            jcpdf.trimMarks(pagespdf13, r13);
            jcpdf.toFile(pagespdf13, "testoutputs/03trim_marks.pdf", false, false);
            System.out.println("---cpdf_showBoxes()");
            jcpdf.showBoxes(pagespdf14, r14);
            jcpdf.toFile(pagespdf14, "testoutputs/03show_boxes.pdf", false, false);
            System.out.println("---cpdf_hardBox()");
            jcpdf.hardBox(pagespdf15, r15, "/MediaBox");
            jcpdf.toFile(pagespdf15, "testoutputs/03hard_box.pdf", false, false);
            System.out.println("---cpdf_removeCrop()");
            jcpdf.removeCrop(pagespdf16, r16);
            jcpdf.toFile(pagespdf16, "testoutputs/03remove_crop.pdf", false, false);
            System.out.println("---cpdf_removeTrim()");
            jcpdf.removeTrim(pagespdf17, r17);
            jcpdf.toFile(pagespdf17, "testoutputs/03remove_trim.pdf", false, false);
            System.out.println("---cpdf_removeArt()");
            jcpdf.removeArt(pagespdf18, r18);
            jcpdf.toFile(pagespdf18, "testoutputs/03remove_art.pdf", false, false);
            System.out.println("---cpdf_removeBleed()");
            jcpdf.removeBleed(pagespdf19, r19);
            jcpdf.toFile(pagespdf19, "testoutputs/03remove_bleed.pdf", false, false);
        }
    }
   
    static void chapter4(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 4. Encryption */
        /* Encryption covered under Chapter 1 in cpdflib. */
    }
   
    static void chapter5(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 5. Compression */
        System.out.println("***** CHAPTER 5. Compression");
        try (Jcpdf.Pdf pdf16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            System.out.println("---cpdf_compress()");
            jcpdf.compress(pdf16);
            jcpdf.toFile(pdf16, "testoutputs/05compressed.pdf", false, false);
            System.out.println("---cpdf_decompress()");
            jcpdf.decompress(pdf16);
            jcpdf.toFile(pdf16, "testoutputs/05decompressed.pdf", false, false);
            System.out.println("---cpdf_squeezeInMemory()");
            jcpdf.squeezeInMemory(pdf16);
            jcpdf.toFile(pdf16, "testoutputs/05squeezedinmemory.pdf", false, false);
        }
    }
   
    static void chapter6(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 6. Bookmarks */
        System.out.println("***** CHAPTER 6. Bookmarks");
        Jcpdf.Pdf pdf17 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
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
        Jcpdf.Pdf marksjson = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] marksdata = jcpdf.getBookmarksJSON(marksjson);
        System.out.format("Contains %d bytes of data\n", marksdata.length);
        System.out.println("---cpdf_setBookmarksJSON()");
        jcpdf.setBookmarksJSON(marksjson, marksdata);
        jcpdf.toFile(marksjson, "testoutputs/06jsonmarks.pdf", false, false);
        System.out.println("---cpdf_tableOfContents()");
        Jcpdf.Pdf toc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        jcpdf.tableOfContents(toc, jcpdf.timesRoman, 12.0, "Table of Contents", false);
        jcpdf.toFile(toc, "testoutputs/06toc.pdf", false, false);
        pdf17.close();
        marksjson.close();
        toc.close();
    }
   
    static void chapter7(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 7. Presentations */
        /* Not included in the library version. */
    }
   
    static void chapter8(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 8. Logos, Watermarks and Stamps */
        System.out.println("***** CHAPTER 8. Logos, Watermarks and Stamps");
        Jcpdf.Pdf textfile = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_addText()");
        Jcpdf.Range all = jcpdf.all(textfile);
        jcpdf.addText(false,
                     textfile,
                     all,
                     "Some Text~~~~~~~~~~!",
                     jcpdf.topLeft, 20.0, 20.0,
                     1.0,
                     1,
                     jcpdf.timesRoman,
                     20.0,
                     0.5,
                     0.5,
                     0.5,
                     false,
                     false,
                     true,
                     0.5,
                     jcpdf.leftJustify,
                     false,
                     false,
                     "",
                     1.0,
                     false);
        System.out.println("---cpdf_addTextSimple()");
        jcpdf.addTextSimple(textfile, all, "The text!", jcpdf.topLeft, 20.0, 20.0, jcpdf.timesRoman, 50.0);
        jcpdf.toFile(textfile, "testoutputs/08added_text.pdf", false, false);
        System.out.println("---cpdf_removeText()");
        jcpdf.removeText(textfile, all);
        jcpdf.toFile(textfile, "testoutputs/08removed_text.pdf", false, false);
        System.out.println("---cpdf_textWidth()");
        int w = jcpdf.textWidth(jcpdf.timesRoman, "What is the width of this?");
        Jcpdf.Pdf stamp = jcpdf.fromFile("testinputs/logo.pdf", "");
        Jcpdf.Pdf stampee = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        Jcpdf.Range stamp_range = jcpdf.all(stamp);
        System.out.println("---cpdf_stampOn()");
        jcpdf.stampOn(stamp, stampee, stamp_range);
        System.out.println("---cpdf_stampUnder()");
        jcpdf.stampUnder(stamp, stampee, stamp_range);
        System.out.println("---cpdf_stampExtended()");
        jcpdf.stampExtended(stamp, stampee, stamp_range, true, true, jcpdf.topLeft, 20.0, 20.0, true);
        jcpdf.toFile(stamp, "testoutputs/08stamp_after.pdf", false, false);
        jcpdf.toFile(stampee, "testoutputs/08stampee_after.pdf", false, false);
        Jcpdf.Pdf c1 = jcpdf.fromFile("testinputs/logo.pdf", "");
        Jcpdf.Pdf c2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_combinePages()");
        Jcpdf.Pdf c3 = jcpdf.combinePages(c1, c2);
        jcpdf.toFile(c3, "testoutputs/08c3after.pdf", false, false);
        System.out.println("---cpdf_stampAsXObject()");
        Jcpdf.Pdf undoc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        Jcpdf.Pdf ulogo = jcpdf.fromFile("testinputs/logo.pdf", "");
        Jcpdf.Range undoc_all = jcpdf.all(undoc);
        String name = jcpdf.stampAsXObject(undoc, undoc_all, ulogo);
        String content = String.format("q 1 0 0 1 100 100 cm %s Do Q q 1 0 0 1 300 300 cm %s Do Q q 1 0 0 1 500 500 cm %s Do Q", name, name, name);
        System.out.println("---cpdf_addContent()");
        jcpdf.addContent(content, true, undoc, undoc_all);
        jcpdf.toFile(undoc, "testoutputs/08demo.pdf", false, false);
        textfile.close();
        stamp.close();
        stampee.close();
        c1.close();
        c2.close();
        c3.close();
        undoc.close();
        ulogo.close();
        all.close();
        stamp_range.close();
        undoc_all.close();
    }
   
    static void chapter9(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 9. Multipage facilities */
        System.out.println("***** CHAPTER 9. Multipage facilities");
        try
            (Jcpdf.Pdf mp = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp25 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp26 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf mp7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
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
            Jcpdf.Range r = jcpdf.range(1, 10);
            jcpdf.padBefore(mp3, r);
            jcpdf.toFile(mp3, "testoutputs/09mp3.pdf", false, false);
            System.out.println("---cpdf_padAfter()");
            jcpdf.padAfter(mp4, r);
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
            r.close();
        }
    }
   
    static void chapter10(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 10. Annotations */
        System.out.println("***** CHAPTER 10. Annotations");
        System.out.println("---cpdf_annotationsJSON()");
        Jcpdf.Pdf annot = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        byte[] annotjson = jcpdf.annotationsJSON(annot);
        System.out.format("Contains %d bytes of data\n", annotjson.length);
        annot.close();
    }
   
    static void chapter11(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 11. Document Information and Metadata */
        System.out.println("***** CHAPTER 11. Document Information and Metadata");
        Jcpdf.Pdf pdf30 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        Jcpdf.Range pdf30all = jcpdf.all(pdf30);
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
        int[] t = {0, 0, 0, 0, 0, 0, 0, 0};
        System.out.println("---cpdf_getDateComponents()");
        jcpdf.getDateComponents("D:20061108125017Z", t);
        System.out.format("D:20061108125017Z = %d, %d, %d, %d, %d, %d, %d, %d\n", t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7]);
        System.out.println("---cpdf_dateStringOfComponents()");
        String datestr = jcpdf.dateStringOfComponents(t[0], t[1], t[2], t[3], t[4], t[5], t[6], t[7]);
        System.out.println(datestr);
        System.out.println("---cpdf_getPageRotation()");
        int rot = jcpdf.getPageRotation(pdf30, 1);
        System.out.format("/Rotate on page 1 = %d\n", rot);
        System.out.println("---cpdf_hasBox()");
        boolean hasbox = jcpdf.hasBox(pdf30, 1, "/CropBox");
        System.out.format("hasbox: %d\n", hasbox ? 1 : 0);
        System.out.println("---cpdf_getMediaBox()");
        double[] b4 = {0.0, 0.0, 0.0, 0.0};
        jcpdf.getMediaBox(pdf30, 1, b4);
        System.out.format("Media: %f %f %f %f\n", b4[0], b4[1], b4[2], b4[3]);
        System.out.println("---cpdf_getCropBox()");
        jcpdf.getCropBox(pdf30, 1, b4);
        System.out.format("Crop: %f %f %f %f\n", b4[0], b4[1], b4[2], b4[3]);
        System.out.println("---cpdf_getBleedBox()");
        jcpdf.getBleedBox(pdf30, 1, b4);
        System.out.format("Bleed: %f %f %f %f\n", b4[0], b4[1], b4[2], b4[3]);
        System.out.println("---cpdf_getArtBox()");
        jcpdf.getArtBox(pdf30, 1, b4);
        System.out.format("Art: %f %f %f %f\n", b4[0], b4[1], b4[2], b4[3]);
        System.out.println("---cpdf_getTrimBox()");
        jcpdf.getTrimBox(pdf30, 1, b4);
        System.out.format("Trim: %f %f %f %f\n", b4[0], b4[1], b4[2], b4[3]);
        System.out.println("---cpdf_setMediaBox()");
        jcpdf.setMediabox(pdf30, pdf30all, 100, 500, 150, 550);
        System.out.println("---cpdf_setCropBox()");
        jcpdf.setCropBox(pdf30, pdf30all, 100, 500, 150, 550);
        System.out.println("---cpdf_setTrimBox()");
        jcpdf.setTrimBox(pdf30, pdf30all, 100, 500, 150, 550);
        System.out.println("---cpdf_setArtBox()");
        jcpdf.setArtBox(pdf30, pdf30all, 100, 500, 150, 550);
        System.out.println("---cpdf_setBleedBox()");
        jcpdf.setBleedBox(pdf30, pdf30all, 100, 500, 150, 550);
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
        jcpdf.addPageLabels(pdf30, jcpdf.uppercaseRoman, "PREFIX-", 1, pdf30all, false);
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
        pdf30.close();
        pdf30all.close();
    }
   
    static void chapter12(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 12. File Attachments */
        System.out.println("***** CHAPTER 12. File Attachments");
        Jcpdf.Pdf attachments = jcpdf.fromFile("testinputs/has_attachments.pdf", "");
        System.out.println("---cpdf_attachFile()");
        jcpdf.attachFile("testinputs/image.pdf", attachments);
        System.out.println("---cpdf_attachFileToPage()");
        jcpdf.attachFileToPage("testinputs/image.pdf", attachments, 1);
        System.out.println("---cpdf_attachFileFromMemory()");
        byte[] empty = {};
        jcpdf.attachFileFromMemory(empty, "metadata.txt", attachments);
        System.out.println("---cpdf_attachFileToPageFromMemory()");
        jcpdf.attachFileToPageFromMemory(empty, "metadata.txt", attachments, 1);
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
            byte[] a_data = jcpdf.getAttachmentData(aa);
            System.out.format("Contains %d bytes of data\n", a_data.length);
        }
        jcpdf.endGetAttachments();
        System.out.println("---cpdf_removeAttachedFiles()");
        jcpdf.removeAttachedFiles(attachments);
        jcpdf.toFile(attachments, "testoutputs/12removed_attachments.pdf", false, false);
        attachments.close();
    }
   
    static void chapter13(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 13. Images. */
        System.out.println("***** CHAPTER 13. Images");
        System.out.println("---cpdf: get image resolution");
        Jcpdf.Pdf image_pdf = jcpdf.fromFile("testinputs/image.pdf", "");
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
        image_pdf.close();
    }
   
    static void chapter14(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 14. Fonts. */
        System.out.println("***** CHAPTER 14. Fonts");
        System.out.println("---cpdf: Get Fonts");
        Jcpdf.Pdf fonts = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        Jcpdf.Pdf fonts2 = jcpdf.fromFile("testinputs/frontmatter.pdf", "");
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
        Jcpdf.Range all = jcpdf.all(fonts);
        jcpdf.copyFont(fonts, fonts2, all, 1, "/Font");
        fonts.close();
        fonts2.close();
        all.close();
    }
   
    static void chapter15(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 15. PDF and JSON */
        System.out.println("***** CHAPTER 15. PDF and JSON");
        Jcpdf.Pdf jsonpdf = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        System.out.println("---cpdf_outputJSON()");
        jcpdf.outputJSON("testoutputs/15json.json", false, false, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsonnostream.json", false, true, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsonparsed.json", true, false, false, jsonpdf);
        jcpdf.outputJSON("testoutputs/15jsondecomp.json", false, false, true, jsonpdf);
        System.out.println("---cpdf_fromJSON()");
        Jcpdf.Pdf fromjsonpdf = jcpdf.fromJSON("testoutputs/15jsonparsed.json");
        jcpdf.toFile(fromjsonpdf, "testoutputs/15fromjson.pdf", false, false);
        System.out.println("---cpdf_outputJSONMemory()");
        byte[] jbuf = jcpdf.outputJSONMemory(fromjsonpdf, false, false, false);
        System.out.println("---cpdf_fromJSONMemory()");
        Jcpdf.Pdf jfrommem = jcpdf.fromJSONMemory(jbuf);
        jcpdf.toFile(jfrommem, "testoutputs/15fromJSONMemory.pdf", false, false);
        jsonpdf.close();
        fromjsonpdf.close();
        jfrommem.close();
    }
   
    static void chapter16(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 16. Optional Content Groups */
        System.out.println("***** CHAPTER 16. Optional Content Groups");
        Jcpdf.Pdf ocg = jcpdf.fromFile("testinputs/has_ocgs.pdf", "");
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
        ocg.close();
    }
    
    static void chapter17(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 17. Creating New PDFs */
        System.out.println("***** CHAPTER 17. Creating New PDFs");
        System.out.println("---cpdf_blankDocument()");
        System.out.println("---cpdf_blankDocumentPaper()");
        try
            (Jcpdf.Pdf new1 = jcpdf.blankDocument(100.0, 200.0, 20);
             Jcpdf.Pdf new2 = jcpdf.blankDocumentPaper(jcpdf.a4portrait, 10))
        {
            jcpdf.toFile(new1, "testoutputs/01blank.pdf", false, false);
            jcpdf.toFile(new2, "testoutputs/01blanka4.pdf", false, false);
            System.out.println("---cpdf_textToPDF()");
            System.out.println("---cpdf_textToPDFPaper()");
        }
        try
            (Jcpdf.Pdf ttpdf = jcpdf.textToPDF(500.0, 600.0, jcpdf.timesItalic, 8.0, "../cpdflib-source/cpdflibtest.c");
             Jcpdf.Pdf ttpdfpaper = jcpdf.textToPDFPaper(jcpdf.a4portrait, jcpdf.timesBoldItalic, 10.0, "../cpdflib-source/cpdflibtest.c"))
        {
            jcpdf.toFile(ttpdf, "testoutputs/01ttpdf.pdf", false, false);
            jcpdf.toFile(ttpdfpaper, "testoutputs/01ttpdfpaper.pdf", false, false);
        }
    }

    static void chapter18(Jcpdf jcpdf) throws Jcpdf.CpdfError
    {
        /* CHAPTER 18. Miscellaneous */
        System.out.println("***** CHAPTER 18. Miscellaneous");
        try
            (Jcpdf.Pdf misc = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc2 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc3 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc4 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc5 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc6 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc7 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc8 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc9 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc10 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc11 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc12 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc13 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc14 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc15 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Pdf misc16 = jcpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
             Jcpdf.Range all = jcpdf.all(misc);
             Jcpdf.Pdf misclogo = jcpdf.fromFile("testinputs/logo.pdf", ""))
        {
            System.out.println("---cpdf_draft()");
            jcpdf.draft(misc, all, true);
            jcpdf.toFile(misc, "testoutputs/17draft.pdf", false, false);
            System.out.println("---cpdf_removeAllText()");
            jcpdf.removeAllText(misc2, all);
            jcpdf.toFile(misc2, "testoutputs/17removealltext.pdf", false, false);
            System.out.println("---cpdf_blackText()");
            jcpdf.blackText(misc3, all);
            jcpdf.toFile(misc3, "testoutputs/17blacktext.pdf", false, false);
            System.out.println("---cpdf_blackLines()");
            jcpdf.blackLines(misc4, all);
            jcpdf.toFile(misc4, "testoutputs/17blacklines.pdf", false, false);
            System.out.println("---cpdf_blackFills()");
            jcpdf.blackFills(misc5, all);
            jcpdf.toFile(misc5, "testoutputs/17blackfills.pdf", false, false);
            System.out.println("---cpdf_thinLines()");
            jcpdf.thinLines(misc6, all, 2.0);
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
            jcpdf.removeClipping(misc12, all);
            jcpdf.toFile(misc12, "testoutputs/17removeclipping.pdf", false, false);
        }
    }

    //Merge example
    static void example()
    {
       // Initialise cpdf
       Jcpdf jcpdf = new Jcpdf();
       try
       {
         jcpdf.startup();
       }
       catch (Jcpdf.CpdfError e)
       {
         System.out.println("Error during cpdf startup");
       }

       // We will take the input hello.pdf and repeat it three times
       try (Jcpdf.Pdf mergepdf = jcpdf.fromFile("hello.pdf", ""))
       {
         // The array of PDFs to merge
         Jcpdf.Pdf[] pdfs = {mergepdf, mergepdf, mergepdf};

         // Merge them
         Jcpdf.Pdf merged = jcpdf.mergeSimple(pdfs);

         // Write output
         jcpdf.toFile(merged, "merged.pdf", false, false);

         // Dispose of merged PDF
         merged.close();
       }
       catch (Jcpdf.CpdfError e)
       {
         System.out.println("Error during cpdf operation");
       }
    }

    static public void main(String argv[]) {
        Jcpdf jcpdf = new Jcpdf();
        try
        {
            chapter0(jcpdf);
            chapter1(jcpdf);
            chapter2(jcpdf);
            chapter3(jcpdf);
            chapter4(jcpdf);
            chapter5(jcpdf);
            chapter6(jcpdf);
            chapter7(jcpdf);
            chapter8(jcpdf);
            chapter9(jcpdf);
            chapter10(jcpdf);
            chapter11(jcpdf);
            chapter12(jcpdf);
            chapter13(jcpdf);
            chapter14(jcpdf);
            chapter15(jcpdf);
            chapter16(jcpdf);
            chapter17(jcpdf);
            chapter18(jcpdf);
            //example();
        }
        catch (Jcpdf.CpdfError e)
        {
        }
    }
}
