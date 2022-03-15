/* Jcpdf.java */

public class Jcpdf {
    native void startup();
    native String version();
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
        //Console.WriteLine("---cpdf_setFast()");
        //Cpdf.setFast();
        //Console.WriteLine("---cpdf_setSlow()");
        //Cpdf.setSlow();
        //Console.WriteLine("---cpdf_clearError()");
    }
}
