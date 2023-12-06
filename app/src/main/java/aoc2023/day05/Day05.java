package aoc2023.day05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * My solution for day 5 of Advent of Code 2023. The puzzle can be found at the <a
 * href="https://adventofcode.com/2023/day/5">AoC page</a>.
 */
public class Day05 {

  private record SeedLine(List<Long> seeds) {
    public static SeedLine from(List<String> input) {
      var seeds =
          input.stream()
              .limit(1)
              .flatMap(
                  firstLine -> Arrays.stream(firstLine.split(":")).skip(1).map(line -> line.trim()))
              .flatMap(
                  seedsLine -> {
                    var dbgRes = Arrays.stream(seedsLine.split("\\s+"));
                    return dbgRes;
                  })
              .map(
                  seed -> {
                    var dbgRes = Long.valueOf(seed);
                    return dbgRes;
                  });
      return new SeedLine(seeds.toList());
    }
  }

  private record CategoryRange(long destinationStart, long sourceStart, long rangeLength) {
    public Optional<Long> getDestination(long source) {
      if (source >= sourceStart() && source <= sourceStart() + rangeLength()) {
        return Optional.of(source - sourceStart() + destinationStart());
      } else {
        return Optional.empty();
      }
    }

    public static CategoryRange from(String line) {
      var rangeValues = Arrays.stream(line.split("\\s+")).map(Long::valueOf).toArray(Long[]::new);
      return from(rangeValues);
    }

    public static CategoryRange from(Long... rangeValues) {
      return new CategoryRange(rangeValues[0], rangeValues[1], rangeValues[2]);
    }
  }

  private record Category(String source, String destination, ArrayList<CategoryRange> ranges) {

    public long getMappedValue(long source) {
      for (CategoryRange categoryRange : ranges) {
        var mappedValue = categoryRange.getDestination(source);
        if (mappedValue.isPresent()) {
          return mappedValue.get();
        }
      }
      return source;
    }

    public static Category from(String line) {
      return from(line.split(" ")[0].split("-"));
    }

    public static Category from(String... values) {
      return new Category(values[0], values[2], new ArrayList<CategoryRange>());
    }

    public static boolean isBeginOfCategoryDefinition(String line) {
      return line.contains(" map:");
    }

    public static boolean isEndOfCategoryDefinition(String line) {
      return line.isBlank();
    }
  }

  private record CategoryLines(HashMap<String, Category> categories) {
    public static CategoryLines from(List<String> input) {
      var categories = new HashMap<String, Category>();
      Category currentCategory = null;
      for (String line : input.stream().skip(2).toList()) {
        if (Category.isBeginOfCategoryDefinition(line)) {
          currentCategory = Category.from(line);
        } else if (Category.isEndOfCategoryDefinition(line) && currentCategory != null) {
          categories.put(currentCategory.source, currentCategory);
        } else if (currentCategory != null) {
          currentCategory.ranges.add(CategoryRange.from(line));
        }
      }
      return new CategoryLines(categories);
    }

    public long getLocation(long seed) {
      var map = categories().get("seed");
      boolean finished = false;
      long nextValue = seed;
      while (!finished) {
        nextValue = map.getMappedValue(nextValue);
        if (categories().containsKey(map.destination())) {
          map = categories().get(map.destination());
        } else {
          finished = true;
        }
      }
      return nextValue;
    }
  }

  public static long part1(List<String> input) {
    var seedLine = SeedLine.from(input);
    var categoryLine = CategoryLines.from(input);
    var locations =
        seedLine.seeds().stream()
            .map(seed -> categoryLine.getLocation(seed))
            .mapToLong(value -> value)
            .min()
            .getAsLong();
    return locations;
  }

  public static long part2(List<String> input) {
    var categoryLine = CategoryLines.from(input);
    var seedRanges = new ArrayList<Range>();
    var seedLine = SeedLine.from(input);
    var seedLocationMap = new HashMap<Long, Long>();
    for (int i = 0; i < seedLine.seeds().size(); i++) {
      if (i % 2 == 0) {
        seedRanges.add(new Range(seedLine.seeds().get(i), seedLine.seeds().get(i + 1)));
      }
    }
    var a =
        seedRanges.stream()
            .map(
                range -> {
                  long location;
                  if (seedLocationMap.containsKey(range.start())) {
                    location = seedLocationMap.get(range.start());
                  } else {
                    location = categoryLine.getLocation(range.start());
                    seedLocationMap.put(range.start(), location);
                  }
                  for (long seed = range.start() + 1; seed < range.start() + range.end(); seed++) {
                    long locationOfCurrentSeed;
                    if (seedLocationMap.containsKey(seed)) {
                      locationOfCurrentSeed = seedLocationMap.get(seed);
                    } else {
                      locationOfCurrentSeed = categoryLine.getLocation(seed);
                      seedLocationMap.put(seed, location);
                    }
                    if (locationOfCurrentSeed < location) {
                      location = locationOfCurrentSeed;
                    }
                  }
                  return location;
                })
            .mapToLong(value -> value)
            .min()
            .getAsLong();
    return a;
  }

  private record Range(long start, long end) {}
}
