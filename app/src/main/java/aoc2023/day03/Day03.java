package aoc2023.day03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * My solution for day 3 of Advent of Code 2023. The puzzle can be found at the <a
 * href="https://adventofcode.com/2023/day/3">AoC page</a>.
 */
public class Day03 {
  private static final int NUMERIC_ZERO = (int) '0';
  private static final int NUMERIC_NINE = (int) '9';
  private static final int NUMERIC_DOT = (int) '.';

  /** Point */
  public record Point(int x, int y) {}

  public static int part1(List<String> input) {
    Map<Point, Character> symbols = new HashMap<Point, Character>();
    for (int y = 0; y < input.size(); y++) {
      String line = input.get(y);
      for (int x = 0; x < line.length(); x++) {
        char character = line.charAt(x);
        if (isSymbol(character)) {
          symbols.put(new Point(x, y), character);
        }
      }
    }
    int sum = 0;
    for (int y = 0; y < input.size(); y++) {
      String line = input.get(y);
      String number = "";
      Point startNumber = null;
      Point endNumber = null;

      for (int x = 0; x < line.length(); x++) {
        char character = line.charAt(x);
        if (isNumber(character)) {
          number += String.valueOf(character);
          if (startNumber == null) {
            startNumber = new Point(x, y);
          }
          if (startNumber != null) {
            endNumber = new Point(x, y);
          }
          if (x == line.length() - 1) {
            sum += getAdjacentSymbolExists(symbols, number, startNumber, endNumber);
          }
        } else if (!isNumber(character)) {
          if (startNumber != null && endNumber != null) {
            sum += getAdjacentSymbolExists(symbols, number, startNumber, endNumber);
          }
          number = "";
          startNumber = null;
          endNumber = null;
        }
      }
    }
    return sum;
  }

  public static int part2(List<String> input) {
    Map<Point, Integer> gears = new HashMap<Point, Integer>();
    for (int y = 0; y < input.size(); y++) {
      String line = input.get(y);
      for (int x = 0; x < line.length(); x++) {
        char character = line.charAt(x);
        if (character == '*') {
          gears.put(new Point(x, y), 0);
        }
      }
    }
    Map<Point, Integer> numbers = new HashMap<>();
    for (int y = 0; y < input.size(); y++) {
      String line = input.get(y);
      String number = "";
      Point startNumber = null;
      Point endNumber = null;

      for (int x = 0; x < line.length(); x++) {
        char character = line.charAt(x);
        if (isNumber(character)) {
          number += String.valueOf(character);
          if (startNumber == null) {
            startNumber = new Point(x, y);
          }
          if (startNumber != null) {
            endNumber = new Point(x, y);
          }
          if (x == line.length() - 1) {
            for (int i = startNumber.x(); i < endNumber.x(); i++) {
              numbers.put(new Point(i, startNumber.y()), Integer.valueOf(number));
            }
          }
        } else if (!isNumber(character)) {
          if (startNumber != null && endNumber != null) {
            for (int i = startNumber.x(); i <= endNumber.x(); i++) {
              numbers.put(new Point(i, startNumber.y()), Integer.valueOf(number));
            }
          }
          number = "";
          startNumber = null;
          endNumber = null;
        }
      }
    }

    return gears.keySet().stream()
        .map(
            gear -> {
              var a =
                  getPointsArround(gear, gear).stream()
                      .filter(pointArround -> numbers.containsKey(pointArround))
                      .map(pointArround -> numbers.get(pointArround))
                      .distinct()
                      .toList();
              if (a.size() > 1) {
                return a.stream().reduce(1, (acc, curr) -> acc * curr);
              } else {
                return 0;
              }
            })
        .mapToInt(v -> v)
        .sum();
  }

  private static int getAdjacentSymbolExists(
      Map<Point, Character> symbols, String number, Point startNumber, Point endNumber) {
    boolean adjacentSymbolExists =
        getPointsArround(startNumber, endNumber).stream()
            .filter(pointArround -> symbols.containsKey(pointArround))
            .findAny()
            .isPresent();
    if (adjacentSymbolExists) {
      return Integer.valueOf(number);
    }
    return 0;
  }

  public static boolean isNumber(char character) {
    return character >= NUMERIC_ZERO && character <= NUMERIC_NINE;
  }

  public static List<Point> getPointsArround(Point start, Point end) {
    ArrayList<Point> points = new ArrayList<>();
    for (int x = start.x() - 1; x <= end.x() + 1; x++) {
      for (int y = start.y() - 1; y <= end.y() + 1; y++) {
        points.add(new Point(x, y));
      }
    }
    return points.stream().toList();
  }

  public static boolean isSymbol(char character) {
    if (character == NUMERIC_DOT) {
      return false;
    }
    if (isNumber(character)) {
      return false;
    }
    return true;
  }
}
