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
    }
}
