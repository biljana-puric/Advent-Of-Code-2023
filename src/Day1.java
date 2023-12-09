import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day1 {

    public static int calculateSum(String filePath) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int sum = 0;
            while ((line = reader.readLine()) != null) {
                int i = 0;
                int first = 0;
                while (i < line.length() && !Character.isDigit(line.charAt(i))) {
                    i++;
                }
                if (i < line.length()) {
                    first = Character.getNumericValue(line.charAt(i));
                }

                int j = line.length() - 1;
                int second = 0;
                while (j >= 0 && !Character.isDigit(line.charAt(j))) {
                    j--;
                }
                if (j >= 0) {
                    second = Character.getNumericValue(line.charAt(j));
                }

                int lineNumber = first * 10 + second;
                sum += lineNumber;
            }
            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static void main(String[] args) {
        System.out.println(calculateSum("Day1_input.txt"));
    }
}
