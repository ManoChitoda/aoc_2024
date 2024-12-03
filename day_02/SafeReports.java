package day_02;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SafeReports {

    public static void main(String[] args) {
        
        String inputFilePath = "day_02_input.txt";
        int safeCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String report;
            while ((report = reader.readLine()) != null) {
                if (isSafe(report) || canBeMadeSafe(report)) {
                    safeCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Number of safe reports: " + safeCount);
    }

    private static boolean isSafe(String report) {
        String[] levelsStr = report.trim().split("\\s+");
        int[] levels = new int[levelsStr.length];

        for (int i = 0; i < levelsStr.length; i++) {
            levels[i] = Integer.parseInt(levelsStr[i]);
        }

        return checkSafety(levels);
    }

    private static boolean canBeMadeSafe(String report) {
        String[] levelsStr = report.trim().split("\\s+");
        int[] levels = new int[levelsStr.length];

        for (int i = 0; i < levelsStr.length; i++) {
            levels[i] = Integer.parseInt(levelsStr[i]);
        }

        // Try removing each level and check if the remaining levels are safe
        for (int i = 0; i < levels.length; i++) {
            int[] newLevels = new int[levels.length - 1];
            for (int j = 0, k = 0; j < levels.length; j++) {
                if (j != i) {
                    newLevels[k++] = levels[j];
                }
            }
            if (checkSafety(newLevels)) {
                return true; // Found a configuration that is safe
            }
        }
        return false; // No single removal made the report safe
    }

    private static boolean checkSafety(int[] levels) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.length - 1; i++) {
            int difference = Math.abs(levels[i + 1] - levels[i]);

            if (difference < 1 || difference > 3) {
                return false; // fails the adjacent level difference check
            }

            if (levels[i + 1] > levels[i]) {
                isDecreasing = false; // found an increase
            } else if (levels[i + 1] < levels[i]) {
                isIncreasing = false; // found a decrease
            }
        }

        // Report is safe if it is either fully increasing or fully decreasing
        return isIncreasing || isDecreasing;
    }
}