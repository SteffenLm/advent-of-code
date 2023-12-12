package aoc2023.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * My solution for day 11 of Advent of Code 2023. The puzzle can be found at the <a
 * href="https://adventofcode.com/2023/day/11">AoC page</a>.
 */
public class Day11 {
  private record Point(long x, long y) {
    public long calculateDistance(Point other) {
      long horizontalDistance = Math.abs(x() - other.x());
      long verticalDistance = Math.abs(y() - other.y());
      return horizontalDistance + verticalDistance;
    }

    public Point add(Point otherPoint) {
      return new Point(x() + otherPoint.x(), y() + otherPoint.y());
    }

    public long manhattan(Point otherPoint) {
      return Math.abs(x() - otherPoint.x()) + Math.abs(y() - otherPoint.y());
    }
  }

  public static int part1(List<String> input) {
    var grid =
        new ArrayList<ArrayList<String>>(
            input.stream()
                .map(line -> new ArrayList<String>(Arrays.stream(line.split("")).toList()))
                .toList());
    for (int y = 0; y < grid.size(); y++) {
      var line = grid.get(y);
      if (line.stream().noneMatch(s -> s.equals("#"))) {
        grid.add(y, new ArrayList<>(line.stream().toList()));
        y++;
      }
    }
    for (int x = 0; x < grid.get(0).size(); x++) {
      ArrayList<String> ys = new ArrayList<>();
      for (int y = 0; y < grid.size(); y++) {
        ys.add(grid.get(y).get(x));
      }
      if (ys.stream().noneMatch(s -> s.equals("#"))) {
        for (int y = 0; y < ys.size(); y++) {
          grid.get(y).add(x, ys.get(y));
        }
        x++;
      }
    }
    var points = new ArrayList<Point>();
    for (int y = 0; y < grid.size(); y++) {
      for (int x = 0; x < grid.get(y).size(); x++) {
        if (grid.get(y).get(x).equals("#")) {
          points.add(new Point(x, y));
        }
      }
    }
    int sum = 0;
    for (int i = 0; i < points.size(); i++) {
      var currentPoint = points.get(i);
      for (int j = i + 1; j < points.size(); j++) {
        var comparingPoint = points.get(j);
        sum += currentPoint.calculateDistance(comparingPoint);
      }
    }
    return sum;
  }

  public static long part2(List<String> input) {
    var grid = input.stream().map(line -> Arrays.stream(line.split("")).toList()).toList();

    var multiplier = 1000000;
    var galaxies = getGalaxies(grid);

    var xGalaxies = galaxies.stream().map(g -> g.x).collect(Collectors.toSet());
    var emptyRows =
        LongStream.rangeClosed(0, grid.size() - 1)
            .filter(y -> !xGalaxies.contains(y))
            .boxed()
            .toList();

    var yGalaxies = galaxies.stream().map(g -> g.y).collect(Collectors.toSet());
    var emptyColumns =
        LongStream.rangeClosed(0, grid.size() - 1)
            .filter(y -> !yGalaxies.contains(y))
            .boxed()
            .toList();

    var correctGalaxies = getCorrectGalaxyPositons(multiplier, galaxies, emptyRows, emptyColumns);

    return getSum(correctGalaxies);
  }

  private static long getSum(List<Point> correctGalaxies) {
    var sum = 0L;
    for (var i = 0; i < correctGalaxies.size() - 1; i++) {
      for (var j = i; j < correctGalaxies.size(); j++) {
        sum += correctGalaxies.get(i).manhattan(correctGalaxies.get(j));
      }
    }
    return sum;
  }

  private static List<Point> getCorrectGalaxyPositons(
      int multiplier, List<Point> galaxies, List<Long> emptyRows, List<Long> emptyColumns) {
    return galaxies.stream()
        .map(
            galaxy -> {
              var additionalRows =
                  emptyRows.stream().filter(emptyColumn -> galaxy.x() > emptyColumn).count();
              var additionalColumns =
                  emptyColumns.stream().filter(emptyLine -> galaxy.y() > emptyLine).count();
              var newX = additionalRows * (multiplier - 1);
              var newY = additionalColumns * (multiplier - 1);
              return galaxy.add(new Point(newX, newY));
            })
        .toList();
  }

  private static List<Point> getGalaxies(List<List<String>> grid) {
    var galaxies = new ArrayList<Point>();
    for (var y = 0; y < grid.size(); y++) {
      for (var x = 0; x < grid.get(y).size(); x++) {
        if (grid.get(y).get(x).equals("#")) {
          galaxies.add(new Point(x, y));
        }
      }
    }
    return galaxies.stream().toList();
  }
}
