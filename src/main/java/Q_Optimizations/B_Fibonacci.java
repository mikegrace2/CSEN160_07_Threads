package Q_Optimizations;

public class B_Fibonacci {
    public static long fib(int n) {
        if (n <= 2) {
            return 1;
        } else {
            return fib(n - 2) + fib(n - 1);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = fib(40);
        long end = System.currentTimeMillis();

        System.out.println("Fibonacci of 40: " + result);
        System.out.println("Time taken: " + (end - start) + " ms");
    }
}
