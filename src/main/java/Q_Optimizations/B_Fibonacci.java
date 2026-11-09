package Q_Optimizations;

public class B_Fibonacci {
    public static long fib(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }

    public static long fibIterative(int n) {
        if (n <= 2) {
            return 1;
        }

        long previous = 1;
        long current = 1;
        for (int i = 3; i <= n; i++) {
            long next = previous + current;
            previous = current;
            current = next;
        }
        return current;
    }

    private static void verifyImplementations(int maxN) {
        for (int n = 0; n <= maxN; n++) {
            long recursiveResult = fib(n);
            long iterativeResult = fibIterative(n);
            if (recursiveResult != iterativeResult) {
                throw new IllegalStateException("Implementations differ for n=" + n
                        + ": recursive=" + recursiveResult + ", iterative=" + iterativeResult);
            }
        }
    }

    public static void main(String[] args) {
        int verificationLimit = 46;
        long verificationStart = System.currentTimeMillis();
        verifyImplementations(verificationLimit);
        long verificationEnd = System.currentTimeMillis();
        System.out.println("Recursive and iterative implementations match for n=0.."
                + verificationLimit + ".");
        System.out.println("Verification time: " + (verificationEnd - verificationStart) + " ms");

        int n = 50;
        long start = System.currentTimeMillis();
        long result = fibIterative(n);
        long end = System.currentTimeMillis();

        System.out.println("Fibonacci of " + n + ": " + result);
        System.out.println("Iterative time: " + (end - start) + " ms");
    }
}
