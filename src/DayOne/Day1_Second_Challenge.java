package DayOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day1_Second_Challenge {
    public static void main(String[] args) {
        System.out.println(calculateSum("E:\\ETF\\ostalo\\AdventOfCode2023\\src\\DayOne\\Day1_input"));
    }

    public static int calculateSum(String filePath){
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            int sum = 0;
            while ((line = reader.readLine()) != null) {
                sum+=extractNumbers(line);
            }
            return sum;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static int extractNumbers(String input) {
        Map<String, Integer> wordToNumber = createWordToNumberMapping();

        int firstNumber = -1;
        int lastNumber = -1;

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (Character.isDigit(currentChar)) {
                int numericValue = Character.getNumericValue(currentChar);
                if (firstNumber == -1) {
                    firstNumber = numericValue;
                    break;
                }
            } else if (Character.isLetter(currentChar)) {
                boolean found= false;
                StringBuilder wordBuilder = new StringBuilder();
                while (i < input.length() && (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i)))) {
                    if (Character.isLetter(input.charAt(i))) {
                        wordBuilder.append(input.charAt(i));
                    } else if (wordBuilder.length() > 0) {
                        firstNumber = Character.getNumericValue(input.charAt(i));
                        found = true;
                        break;
                    }
                    i++;
                    for (Map.Entry<String, Integer> entry : wordToNumber.entrySet()) {
                        String word = entry.getKey();
                        //System.out.println(wordBuilder.toString());
                        if ( wordBuilder.toString().contains(word) || wordBuilder.toString().equals(word) ||
                                (wordBuilder.toString().startsWith(word) &&
                                        wordBuilder.length() > word.length() &&
                                        Character.isDigit(wordBuilder.charAt(word.length())))) {
                            firstNumber = entry.getValue();
                            found = true;
                            break;
                        }
                    }
                    if(found) break;
                }
                if (found) break;
            }
        }

        for (int i = input.length()-1; i >0; i--) {
            char currentChar = input.charAt(i);

            if (Character.isDigit(currentChar)) {
                int numericValue = Character.getNumericValue(currentChar);
                if (lastNumber == -1) {
                    lastNumber = numericValue;
                    break;
                }
            } else if (Character.isLetter(currentChar)) {
                boolean found= false;
                StringBuilder wordBuilder = new StringBuilder();
                while (i >= 0 && (Character.isLetter(input.charAt(i)) || Character.isDigit(input.charAt(i)))) {
                    if (Character.isLetter(input.charAt(i))) {
                        wordBuilder.insert(0, input.charAt(i));
                    } else if (wordBuilder.length() > 0) {
                        lastNumber = Character.getNumericValue(input.charAt(i));
                        found = true;
                        break;
                    }
                    i--;
                    for (Map.Entry<String, Integer> entry : wordToNumber.entrySet()) {
                        String word = entry.getKey();
                        if ( wordBuilder.toString().contains(word) || wordBuilder.toString().equals(word) ||
                                (wordBuilder.toString().startsWith(word) &&
                                        wordBuilder.length() > word.length() &&
                                        Character.isDigit(wordBuilder.charAt(word.length())))) {
                            lastNumber = entry.getValue();
                            found = true;
                            break;
                        }
                    }
                    if(found) break;
                }
                if (found) break;
            }
        }

        if (firstNumber != -1 && lastNumber != -1) {
            return (firstNumber * 10) + lastNumber;
        } else if(lastNumber == -1){
            return (firstNumber * 10) + firstNumber;
        } else if (firstNumber == -1) {
            return (lastNumber * 10) + lastNumber;
        } else return -1;
    }

    private static Map<String, Integer> createWordToNumberMapping() {
        Map<String, Integer> mapping = new HashMap<>();
        mapping.put("one", 1);
        mapping.put("two", 2);
        mapping.put("three", 3);
        mapping.put("four", 4);
        mapping.put("five", 5);
        mapping.put("six", 6);
        mapping.put("seven", 7);
        mapping.put("eight", 8);
        mapping.put("nine", 9);
        return mapping;
    }
}
