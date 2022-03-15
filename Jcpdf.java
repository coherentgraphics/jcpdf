/* Jcpdf.java */

public class Jcpdf {
    native void helloFromC(); /* (1) */
    static public void main(String argv[]) {
        System.loadLibrary("cpdf");
        System.loadLibrary("jcpdf"); /* (2) */
        Jcpdf jcpdf = new Jcpdf();
        jcpdf.helloFromC(); /* (3) */
    }
}
