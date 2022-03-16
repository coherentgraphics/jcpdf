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
            string info = jcpdf.enumeratePDFsInfo(x);
        }
        jcpdf.endEnumeratePDFs();
        /*
        Console.WriteLine("---cpdf_ptOfIn()");
        Console.WriteLine($"One inch is {Cpdf.ptOfIn(1.0):0.000000} points");
        Console.WriteLine("---cpdf_ptOfCm()");
        Console.WriteLine($"One centimetre is {Cpdf.ptOfCm(1.0):0.000000} points");
        Console.WriteLine("---cpdf_ptOfMm()");
        Console.WriteLine($"One millimetre is {Cpdf.ptOfMm(1.0):0.000000} points");
        Console.WriteLine("---cpdf_inOfPt()");
        Console.WriteLine($"One point is {Cpdf.inOfPt(1.0):0.000000} inches");
        Console.WriteLine("---cpdf_cmOfPt()");
        Console.WriteLine($"One point is {Cpdf.cmOfPt(1.0):0.000000} centimetres");
        Console.WriteLine("---cpdf_mmOfPt()");
        Console.WriteLine($"One point is {Cpdf.mmOfPt(1.0):0.000000} millimetres");
        Console.WriteLine("---cpdf_range()");
        List<int> _range = Cpdf.range(1, 10);
        Console.WriteLine("---cpdf_all()");
        List<int> _all = Cpdf.all(pdf3);
        Console.WriteLine("---cpdf_even()");
        List<int> _even = Cpdf.even(_all);
        Console.WriteLine("---cpdf_odd()");
        List<int> _odd = Cpdf.odd(_all);
        Console.WriteLine("---cpdf_rangeUnion()");
        List<int> union = Cpdf.rangeUnion(_even, _odd);
        Console.WriteLine("---cpdf_difference()");
        List<int> diff = Cpdf.difference(_even, _odd);
        Console.WriteLine("---cpdf_removeDuplicates()");
        List<int> revdup = Cpdf.removeDuplicates(_even);
        Console.WriteLine("---cpdf_rangeLength()");
        int length = Cpdf.rangeLength(_even);
        Console.WriteLine("---cpdf_rangeGet()");
        int rangeget = Cpdf.rangeGet(_even, 1);
        Console.WriteLine("---cpdf_rangeAdd()");
        List<int> rangeadd = Cpdf.rangeAdd(_even, 20);
        Console.WriteLine("---cpdf_isInRange()");
        bool isin = Cpdf.isInRange(_even, 2);
        Console.WriteLine("---cpdf_parsePagespec()");
        List<int> r = Cpdf.parsePagespec(pdf3, "1-5");
        Console.WriteLine("---cpdf_validatePagespec()");
        bool valid = Cpdf.validatePagespec("1-4,5,6");
        Console.WriteLine($"Validating pagespec gives {(valid ? 1 : 0)}");
        Console.WriteLine("---cpdf_stringOfPagespec()");
        string ps = Cpdf.stringOfPagespec(pdf3, r);
        Console.WriteLine($"String of pagespec is {ps}");
        Console.WriteLine("---cpdf_blankRange()");
        List<int> b = Cpdf.blankRange();
        Cpdf.Pdf pdf10 = Cpdf.fromFile("testinputs/cpdflibmanual.pdf", "");
        Console.WriteLine("---cpdf_pages()");
        int _pages = Cpdf.pages(pdf10);
        Console.WriteLine($"Pages = {_pages}");
        Console.WriteLine("---cpdf_pagesFast()");
        int pagesfast = Cpdf.pagesFast("", "testinputs/cpdflibmanual.pdf");
        Console.WriteLine($"Pages = {_pages}");
        Console.WriteLine("---cpdf_toFile()");
        Cpdf.toFile(pdf10, "testoutputs/01tofile.pdf", false, false);
        Console.WriteLine("---cpdf_toFileExt()");
        Cpdf.toFileExt(pdf10, "testoutputs/01tofileext.pdf", false, true, true, true, true);
        Console.WriteLine("---cpdf_isEncrypted()");
        bool isenc = Cpdf.isEncrypted(pdf10);
        Console.WriteLine($"isencrypted:{(isenc ? 1 : 0)}");
        Console.WriteLine("---cpdf_isLinearized()");
        bool lin = Cpdf.isLinearized("testinputs/cpdfmanual.pdf");
        Console.WriteLine($"islinearized:{(lin ? 1 : 0)}");
        using (Cpdf.Pdf pdf400 = Cpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        using (Cpdf.Pdf pdf401 = Cpdf.fromFile("testinputs/cpdflibmanual.pdf", ""))
        {
            List<Cpdf.Permission> permissions = new List<Cpdf.Permission> {Cpdf.Permission.NoEdit};
            Console.WriteLine("---cpdf_toFileEncrypted()");
            Cpdf.toFileEncrypted(pdf400, Cpdf.EncryptionMethod.Pdf40bit, permissions, "owner", "user", false, false, "testoutputs/01encrypted.pdf");
            Console.WriteLine("---cpdf_toFileEncryptedExt()");
            Cpdf.toFileEncryptedExt(pdf401, Cpdf.EncryptionMethod.Pdf40bit, permissions, "owner", "user", false, false, true, true, true, "testoutputs/01encryptedext.pdf");
            Console.WriteLine("---cpdf_hasPermission()");
        }
        using (Cpdf.Pdf pdfenc = Cpdf.fromFile("testoutputs/01encrypted.pdf", "user"))
        {
            bool hasnoedit = Cpdf.hasPermission(pdfenc, Cpdf.Permission.NoEdit);
            bool hasnocopy = Cpdf.hasPermission(pdfenc, Cpdf.Permission.NoCopy);
            Console.WriteLine($"Haspermission {(hasnoedit ? 1 : 0)}, {(hasnocopy ? 1 : 0)}");
            Console.WriteLine("---cpdf_encryptionKind()");
            int enckind = Cpdf.encryptionKind(pdfenc);
            Console.WriteLine($"encryption kind is {enckind}");
        }
        Console.WriteLine("---cpdf_decryptPdf()");
        Cpdf.decryptPdf(pdf10, "");
        Console.WriteLine("---cpdf_decryptPdfOwner()");
        Cpdf.decryptPdfOwner(pdf10, "");
        frommem.Dispose();
        frommemlazy.Dispose();
        pdf.Dispose();
        pdf2.Dispose();
        pdf3.Dispose();
        pdf4.Dispose();
        pdf10.Dispose();*/
    }
}
