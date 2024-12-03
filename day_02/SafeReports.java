import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SafeReports {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java SafeReports <input_file>");
            return;
        }

        day_02(args);
    }

	private static void day_02(String[] args) {
		String inputFilePath = args[0];
        ReportAnalyzer analyzer = new ReportAnalyzer();

        int safeCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String report;
            while ((report = reader.readLine()) != null) {
                if (analyzer.isSafe(report) || analyzer.canBeMadeSafe(report)) {
                    safeCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Number of safe reports: " + safeCount);
	}
}