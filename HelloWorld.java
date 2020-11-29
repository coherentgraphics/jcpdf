/* HelloWorld.java */

public class HelloWorld {
    native void helloFromC(); /* (1) */
    static public void main(String argv[]) {
        System.loadLibrary("ctest"); /* (2) */
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.helloFromC(); /* (3) */
    }
}
