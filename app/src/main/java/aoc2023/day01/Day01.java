package aoc2023.day01;

import java.util.List;

/**
 * My solution for day 01 of Advent of Code 2023. The puzzle can be found at the <a
 * href="https://adventofcode.com/2023/day/01">AoC page</a>.
 */
public class Day01 {
  private static final int NUMERIC_ONE = (int) '1';
  private static final int NUMERIC_NINE = (int) '9';
  private static final String[] STRING_NUMBERS = {
    "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    "1", "2", "3", "4", "5", "6", "7", "8", "9"
  };

  public record Match(int index, int value) {}

  public static int part1(List<String> input) {
    int numericOne = (int) '1';
    int numericNine = (int) '9';
    return input.stream()
        .map(
            line -> {
              String numbers = "";
              for (char character : line.toCharArray()) {
                int numericValue = (int) character;
                if (numericValue >= numericOne && numericValue <= numericNine) {
                  numbers += String.valueOf(character);
                }
              }
              char firstNumber = numbers.charAt(0);
              char lastNumber = numbers.charAt(numbers.length() - 1);
              String stringifiedNumber = String.valueOf(firstNumber) + String.valueOf(lastNumber);
              return Integer.valueOf(stringifiedNumber);
            })
        .mapToInt(v -> v)
        .sum();
  }

  public static int part2(List<String> input) {
    return input.stream()
        .map(
            line -> {
              boolean firstCharIsNumber = isNumber(line.charAt(0));
              boolean lastCharIsNumber = isNumber(line.charAt(line.length() - 1));
              Match firstOccurenceMatch = null;
              Match lastOccurenceMatch = null;

              for (String stringNumber : STRING_NUMBERS) {
                if (!firstCharIsNumber) {
                  int firstOccurenceIndex = line.indexOf(stringNumber);
                  if (firstOccurenceIndex != -1
                      && (firstOccurenceMatch == null
                          || firstOccurenceIndex < firstOccurenceMatch.index())) {
                    firstOccurenceMatch =
                        new Match(firstOccurenceIndex, getNumericValue(stringNumber));
                  }
                }

                if (!lastCharIsNumber) {
                  int lastOccurenceIndex = line.lastIndexOf(stringNumber);
                  if (lastOccurenceIndex != -1
                      && (lastOccurenceMatch == null
                          || lastOccurenceIndex > lastOccurenceMatch.index())) {
                    lastOccurenceMatch =
                        new Match(lastOccurenceIndex, getNumericValue(stringNumber));
                  }
                }
              }
              String numberOne =
                  firstCharIsNumber
                      ? String.valueOf(line.charAt(0))
                      : firstOccurenceMatch != null
                          ? String.valueOf(firstOccurenceMatch.value())
                          : "";
              String numberTwo =
                  lastCharIsNumber
                      ? String.valueOf(line.charAt(line.length() - 1))
                      : lastOccurenceMatch != null
                          ? String.valueOf(lastOccurenceMatch.value())
                          : "";
              return numberOne + numberTwo;
            })
        .mapToInt(line -> Integer.valueOf(line))
        .sum();
  }

  private static int getNumericValue(String stringNumber) {
    switch (stringNumber) {
      case "one":
      case "1":
        return 1;
      case "two":
      case "2":
        return 2;
      case "three":
      case "3":
        return 3;
      case "four":
      case "4":
        return 4;
      case "five":
      case "5":
        return 5;
      case "six":
      case "6":
        return 6;
      case "seven":
      case "7":
        return 7;
      case "eight":
      case "8":
        return 8;
      case "nine":
      case "9":
        return 9;
      default:
        return 0;
    }
  }

  public static boolean isNumber(char number) {
    int numericValue = (int) number;
    return numericValue >= NUMERIC_ONE && numericValue <= NUMERIC_NINE;
  }
}
