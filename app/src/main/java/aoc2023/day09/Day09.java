package aoc2023.day09;

import java.util.Arrays;
import java.util.List;

/**
 * My solution for day 9 of Advent of Code 2023. The puzzle can be found at the
 * <a href="https://adventofcode.com/2023/day/9">AoC page</a>.
 */
public class Day09 {
  private record History(Long[] entries) {
    public long future() {
      return entries()[entries().length - 1] + futureCalc(entries());
    }

    public long past() {
      return entries()[0] - pastCalc(entries());
    }

    private long pastCalc(Long[] sequences) {
      Long[] nextSequence = new Long[sequences.length - 1];
      for (int i = 0; i < sequences.length - 1; i++) {
        nextSequence[i] = sequences[i + 1] - sequences[i];
      }
      if (Arrays.stream(nextSequence).allMatch(entry -> entry == 0)) {
        return 0;
      } else {
        return nextSequence[0] - pastCalc(nextSequence);
      }
    }

    private long futureCalc(Long[] sequences) {
      Long[] nextSequence = new Long[sequences.length - 1];
      for (int i = 0; i < sequences.length - 1; i++) {
        nextSequence[i] = sequences[i + 1] - sequences[i];
      }
      if (Arrays.stream(nextSequence).allMatch(entry -> entry == 0)) {
        return 0;
      } else {
        return nextSequence[nextSequence.length - 1] + futureCalc(nextSequence);
      }
    }

    public static History from(String line) {
      var entries = Arrays.stream(line.split(" ")).map(Long::valueOf).toArray(Long[]::new);
      return new History(entries);
    }
  }

  public static long part1(List<String> input) {
    return input.stream().mapToLong(line -> History.from(line).future()).sum();
  }

  public static long part2(List<String> input) {
    return input.stream().mapToLong(line -> History.from(line).past()).sum();
  }
}
