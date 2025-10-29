package _Day1;

import java.io.IOException;

/**
 * Windows Example.
 */
public class StartAffinityExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Run a Java program on core 1 (hexadecimal mask for core 1 is '2')
        String hexMask = "2";

        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", "/affinity", hexMask, "java", "-jar", "YourProgram.jar");
        System.out.println("Executing command: " + String.join(" ", pb.command()));

        Process process = pb.start();
        int exitCode = process.waitFor();

        System.out.println("Process exited with code: " + exitCode);
    }
}
