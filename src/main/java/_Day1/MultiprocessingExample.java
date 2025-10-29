package _Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class MultiprocessingExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Available processors: "+Runtime.getRuntime().availableProcessors());
    	ProcessBuilder processBuilder = new ProcessBuilder(
                "java",
                "-cp",
                "target/classes",
                "_Day1.MultiprocessingLongExecution");
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();

        try (BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = r.readLine()) != null) {
                System.out.println("Child output: " + line);
            }
        }

        // wait and get exit code
        if (!process.waitFor(60, TimeUnit.SECONDS)) {
            process.destroyForcibly();
            System.out.println("Child timed out and was killed");
        } else {
            System.out.println("Java process exited with code: " + process.exitValue());
        }
    }
}