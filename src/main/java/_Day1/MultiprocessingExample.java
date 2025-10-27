package _Day1;

import java.io.IOException;

public class MultiprocessingExample {
    public static void main(String[] args) throws IOException, InterruptedException {
    	ProcessBuilder processBuilder = new ProcessBuilder("java", "-cp", "classpath", "MultiprocessingLongExecution");

        Process process = processBuilder.start();
        int exitCode = process.waitFor();

        System.out.println("Java process exited with code: " + exitCode);
    }
}