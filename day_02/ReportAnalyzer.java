package day_02;

import java.util.ArrayList;
import java.util.List;

class ReportAnalyzer {

    public boolean isSafe(String report) {
        List<Integer> levels = parseLevels(report);
        return checkSafety(levels);
    }

    public boolean canBeMadeSafe(String report) {
        List<Integer> levels = parseLevels(report);
        int length = levels.size();

        for (int i = 0; i < length; i++) {
            // Check if removing the i-th level makes the report safe
            if (checkSafetyWithoutOne(levels, i)) {
                return true;
            }
        }
        return false; // No single removal made the report safe
    }

    private List<Integer> parseLevels(String report) {
        String[] levelsStr = report.trim().split("\\s+");
        List<Integer> levels = new ArrayList<>(levelsStr.length);

        for (String level : levelsStr) {
            levels.add(Integer.parseInt(level));
        }
        return levels;
    }

    private boolean checkSafety(List<Integer> levels) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.size() - 1; i++) {
            int current = levels.get(i);
            int next = levels.get(i + 1);
            int difference = Math.abs(next - current);

            if (difference < 1 || difference > 3) {
                return false; // fails the adjacent level difference check
            }

            if (next > current) {
                isDecreasing = false; // found an increase
            } else if (next < current) {
                isIncreasing = false; // found a decrease
            }

            // Early exit if both conditions are false
            if (!isIncreasing && !isDecreasing) {
                return false;
            }
        }

        return isIncreasing || isDecreasing;
    }

    private boolean checkSafetyWithoutOne(List<Integer> levels, int indexToRemove) {
        boolean isIncreasing = true;
        boolean isDecreasing = true;

        for (int i = 0; i < levels.size() - 1; i++) {
            // Skip the index that is being removed
            if (i == indexToRemove) {
                // Check the next pair if we are at the index to remove
                if (i + 1 < levels.size() - 1) {
                    int next = levels.get(i + 2);
                    int difference = Math.abs(next - levels.get(i));
                    if (difference < 1 || difference > 3) return false;
                    isIncreasing = isIncreasing && (next > levels.get(i));
                    isDecreasing = isDecreasing && (next < levels.get(i));
                }
                continue; // skip the current index
            }

            int current = levels.get(i);
            int next = levels.get(i + 1);
            int difference = Math.abs(next - current);

            if (difference < 1 || difference > 3) {
                return false; // fails the adjacent level difference check
            }

            if (next > current) {
                isDecreasing = false; // found an increase
            } else if (next < current) {
                isIncreasing = false; // found a decrease
            }

            // Early exit if both conditions are false
            if (!isIncreasing && !isDecreasing) {
                return false;
            }
        }

        return isIncreasing || isDecreasing;
    }
}