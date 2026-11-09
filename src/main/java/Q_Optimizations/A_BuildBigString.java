package Q_Optimizations;

public class A_BuildBigString {
    public final static int REPS = 80000;

    public static String makeString() {
        String str = "";

        for (int n = 0; n < REPS; n++) {
            str += "more";
        }

        return str;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String str = makeString();
        long end = System.currentTimeMillis();

        System.out.println("Length of string: " + str.length());
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}
