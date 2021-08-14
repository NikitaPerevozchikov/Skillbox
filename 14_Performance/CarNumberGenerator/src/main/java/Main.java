public class Main {
    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        Loader.startGeneration();
        System.out.println("Однопоточная генерация составляет: " + (System.currentTimeMillis() - start) + " ms");

        long startFileChanel = System.currentTimeMillis();
        LoaderFileChanel.startGeneration();
        System.out.println("Однопоточная генерация c FileChanel составляет: " + (System.currentTimeMillis() - startFileChanel) + " ms");

        long startThread = System.currentTimeMillis();
        LoaderThread.startGeneration();
        System.out.println("Многопоточная генерация составляет: " + (System.currentTimeMillis() - startThread) + " ms");

    }
}

