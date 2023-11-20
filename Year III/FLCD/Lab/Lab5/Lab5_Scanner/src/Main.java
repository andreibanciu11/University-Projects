public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Program 1");
        MyScanner scannerP1 = new MyScanner("src/P1.txt",
                "src/PIF1.txt",
                "src/ST1.txt");
        scannerP1.scan();

//        System.out.println("------------------------");
//        System.out.println("Program 2");
//        MyScanner scannerP2 = new MyScanner("src/P2.txt",
//                "src/PIF2.txt",
//                "src/ST2.txt");
//        scannerP2.scan();


//        System.out.println("------------------------");
//        System.out.println("Program 3");
//        MyScanner scannerP3 = new MyScanner("src/P3.txt",
//                "src/PIF3.txt",
//                "src/ST3.txt");
//        scannerP3.scan();

        System.out.println("------------------------");
        System.out.println("Program with lexical errors");
        MyScanner scannerP1err = new MyScanner("src/P1err.txt",
                "src/PIFerr.txt",
                "src/STerr.txt");
        scannerP1err.scan();
    }
}