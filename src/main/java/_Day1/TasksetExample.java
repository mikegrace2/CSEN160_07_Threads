package _Day1;

import java.io.IOException;

/**
 * Linux Example.
 */
public class TasksetExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Run a simple Java program on core 1
        int coreId = 1;

        ProcessBuilder pb = new ProcessBuilder("taskset", "-c", String.valueOf(coreId), "java", "-jar", "YourProgram.jar");
        System.out.println("Executing command: " + String.join(" ", pb.command()));

        Process process = pb.start();
        int exitCode = process.waitFor();

        System.out.println("Process exited with code: " + exitCode);
    }
}
