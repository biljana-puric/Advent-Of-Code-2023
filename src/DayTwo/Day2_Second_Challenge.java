package DayTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_Second_Challenge {
    public static void main(String[] args) {
        System.out.println("Sum: " + readFile("Day2_input.txt"));
    }

    public static int readFile(String filePath){
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int sumOfPowers = 0;
            while ((line = reader.readLine()) != null) {
                int power = calculateSumPower(line);
                sumOfPowers += power;
            }
            return sumOfPowers;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int calculateSumPower(String line){
        int blueCount = 0;
        int redCount = 0;
        int greenCount = 0;
        Pattern bluePattern = Pattern.compile("(\\d+) blue");
        Pattern redPattern = Pattern.compile("(\\d+) red");
        Pattern greenPattern = Pattern.compile("(\\d+) green");

        Matcher blueMatcher = bluePattern.matcher(line);
        Matcher redMatcher = redPattern.matcher(line);
        Matcher greenMatcher = greenPattern.matcher(line);

        while (blueMatcher.find()) {
            int blueC = Integer.parseInt(blueMatcher.group(1));
            if(blueC > blueCount){
                blueCount = blueC;
            }
        }
        while (redMatcher.find()) {
            int redC = Integer.parseInt(redMatcher.group(1));
            if(redC > redCount){
                redCount = redC;
            }
        }
        while (greenMatcher.find()) {
            int greenC = Integer.parseInt(greenMatcher.group(1));
            if(greenC > greenCount){
                greenCount = greenC;
            }
        }

        return blueCount*greenCount*redCount;
    }
}
