package DayTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_First_Challenge {
    public static void main(String[] args) {
        System.out.println("Sum: " + readFile("Day2_input.txt"));
    }

    public static int readFile(String filePath){
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int sumOfIDs = 0;
            while ((line = reader.readLine()) != null) {
                int gameNumber = calculateSumID(line);
                sumOfIDs += (gameNumber == -1) ? 0 : gameNumber;
            }
            return sumOfIDs;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int calculateSumID(String line){
        int red = 12, green = 13, blue = 14;
        Pattern pattern = Pattern.compile("Game (\\d+):");
        Matcher matcher = pattern.matcher(line);
        int ID = 0;
        int blueCount = 0;
        int redCount = 0;
        int greenCount = 0;
        if (matcher.find()) {
            String gameNumberStr = matcher.group(1);
            ID = Integer.parseInt(gameNumberStr);
        }
        Pattern bluePattern = Pattern.compile("(\\d+) blue");
        Pattern redPattern = Pattern.compile("(\\d+) red");
        Pattern greenPattern = Pattern.compile("(\\d+) green");

        Matcher blueMatcher = bluePattern.matcher(line);
        Matcher redMatcher = redPattern.matcher(line);
        Matcher greenMatcher = greenPattern.matcher(line);

        while (blueMatcher.find()) {
            blueCount = Integer.parseInt(blueMatcher.group(1));
            if(blueCount > blue) return -1;
        }
        while (redMatcher.find()) {
            redCount = Integer.parseInt(redMatcher.group(1));
            if(redCount > red) return -1;
        }
        while (greenMatcher.find()) {
            greenCount = Integer.parseInt(greenMatcher.group(1));
            if(greenCount > green) return -1;
        }
        return ID;
    }
}
