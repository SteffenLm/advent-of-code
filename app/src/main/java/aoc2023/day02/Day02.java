package aoc2023.day02;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

/**
 * My solution for day 02 of Advent of Code 2023. The puzzle can be found at the <a
 * href="https://adventofcode.com/2023/day/02">AoC page</a>.
 */
public class Day02 {

  enum Color {
    RED,
    GREEN,
    BLUE,
    UNKNOWN;

    public static Color from(String color) {
      switch (color) {
        case "red":
          return Color.RED;
        case "blue":
          return Color.BLUE;
        case "green":
          return Color.GREEN;
        default:
          return Color.UNKNOWN;
      }
    }
  }

  record Cube(int amount, Color color) {
    public static Cube from(String cubeString) {
      return from(cubeString.trim().split(" "));
    }

    private static Cube from(String... cubeValues) {
      return new Cube(Integer.valueOf(cubeValues[0]), Color.from(cubeValues[1]));
    }
  }

  record CubeSet(int red, int green, int blue) {
    public static List<CubeSet> aggregatedCubeSet(LineParts lineParts) {
      return Stream.of(lineParts.sets().split(";"))
          .map(
              (set) ->
                  Arrays.stream(set.split(","))
                      .map(cubeString -> Cube.from(cubeString))
                      .map(cube -> CubeSet.from(cube))
                      .reduce(new CubeSet(0, 0, 0), CubeSet.accumulate))
          .toList();
    }

    public static CubeSet aggregateMinimalCubeSet(LineParts lineParts) {
      return Stream.of(lineParts.sets().split(";"))
          .map(
              (set) ->
                  Arrays.stream(set.split(","))
                      .map(cubeString -> Cube.from(cubeString))
                      .map(cube -> CubeSet.from(cube))
                      .reduce(new CubeSet(0, 0, 0), CubeSet.accumulateMinimalValues))
          .reduce(new CubeSet(0, 0, 0), CubeSet.accumulateMinimalValues);
    }

    public static BinaryOperator<CubeSet> accumulate =
        (acc, cur) ->
            new CubeSet(acc.red() + cur.red(), acc.green() + cur.green(), acc.blue() + cur.blue());

    public static BinaryOperator<CubeSet> accumulateMinimalValues =
        (acc, cur) ->
            new CubeSet(
                acc.red() >= cur.red() ? acc.red() : cur.red(),
                acc.green() >= cur.green() ? acc.green() : cur.green(),
                acc.blue() >= cur.blue() ? acc.blue() : cur.blue());

    public boolean validate(Predicate<CubeSet> validator) {
      return validator.test(this);
    }

    public static CubeSet from(Cube cube) {
      int red = 0, green = 0, blue = 0;
      switch (cube.color()) {
        case RED:
          red = cube.amount();
          break;
        case GREEN:
          green = cube.amount();
          break;
        case BLUE:
          blue = cube.amount();
          break;
        default:
          break;
      }
      return new CubeSet(red, green, blue);
    }
  }

  record Game(int id, List<CubeSet> sets) {
    public boolean allSetsMatch(Predicate<CubeSet> validator) {
      return this.sets.stream().filter(set -> !set.validate(validator)).findAny().isEmpty();
    }

    public static Game from(LineParts lineParts) {
      return new Game(getGameId(lineParts), CubeSet.aggregatedCubeSet(lineParts));
    }

    public static Game from(LineParts lineParts, Function<LineParts, CubeSet> aggregator) {
      return new Game(getGameId(lineParts), List.of(aggregator.apply(lineParts)));
    }

    private static int getGameId(LineParts lineParts) {
      String gameId = lineParts.game.split("\s")[1];
      return Integer.valueOf(gameId);
    }
  }

  record LineParts(String game, String sets) {
    public static LineParts from(String line) {
      return from(line.split(":"));
    }

    private static LineParts from(String... line) {
      return new LineParts(line[0].trim(), line[1].trim());
    }
  }

  public static int part1(List<String> input) {
    return input.stream()
        .map(line -> Game.from(LineParts.from(line)))
        .filter(game -> game.allSetsMatch(part1Validator))
        .mapToInt(game -> game.id())
        .sum();
  }

  public static int part2(List<String> input) {
    return input.stream()
        .map(
            line ->
                Game.from(
                    LineParts.from(line), lineParts -> CubeSet.aggregateMinimalCubeSet(lineParts)))
        .map(
            game ->
                game.sets().stream()
                    .map(cube -> cube.red() * cube.green() * cube.blue())
                    .mapToInt(cubeSum -> cubeSum)
                    .sum())
        .mapToInt(gameSum -> gameSum)
        .sum();
  }

  private static Predicate<CubeSet> part1Validator =
      cubeset -> cubeset.red() <= 12 && cubeset.green() <= 13 && cubeset.blue() <= 14;
}
