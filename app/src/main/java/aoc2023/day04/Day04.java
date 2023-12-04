package aoc2023.day04;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * My solution for day 4 of Advent of Code 2023.
 * The puzzle can be found at the
 * <a href="https://adventofcode.com/2023/day/4">AoC page</a>.
 */
public class Day04 {

    public record CardNumber(String[] numbers) {
        public Stream<String> asStream() {
            return Stream.of(numbers());
        }

        public IntStream asIntStream() {
            return asStream()
                    .mapToInt(num -> Integer.valueOf(num));
        }

    }

    public record Card(String id, String[] allNumbers) {
        public CardNumber getWinningNumbers() {
            var winningNumbers = allNumbers[0].trim().split(" ");
            return new CardNumber(winningNumbers);
        }

        public CardNumber getDrawnNumbers() {
            var drawnNumbers = allNumbers[1].trim().split(" ");
            return new CardNumber(drawnNumbers);
        }

        public int idAsInt() {
            return Integer.valueOf(id());
        }

        public int matches() {
            return (int) getWinningNumbers().asStream()
                    .filter(winningNumber -> !winningNumber.equals(""))
                    .filter(winningNumber -> getDrawnNumbers().asStream()
                            .anyMatch(drawnNumber -> drawnNumber.equals(winningNumber)))
                    .count();
        }
    }

    public record Line(String line) {
        public Card getCard() {
            var cardLine = line.split(":");
            var id = cardLine[0].split("\\s+")[1];
            var numbers = cardLine[1].split("\\|");
            return new Card(id, numbers);
        }
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(line -> new Line(line).getCard())
                .map(card -> card.matches())
                .map(numberOfMatches -> {
                    if (numberOfMatches > 0) {
                        return Math.pow(2, numberOfMatches - 1);
                    } else
                        return 0;
                })
                .mapToInt(score -> score.intValue())
                .sum();
    }

    public static int part2(List<String> input) {
        List<Card> cards = input.stream()
                .map(line -> new Line(line).getCard())
                .toList();
        Map<Integer, Integer> map = cards.stream()
                .collect(Collectors.toMap(card -> card.idAsInt(), card -> 1));

        cards.forEach(card -> {
            int matches = card.matches();
            for (int i = 0; i < matches; i++) {
                int targetCardId = card.idAsInt() + i + 1;
                map.put(targetCardId, map.get(targetCardId) + map.get(card.idAsInt()));
            }
        });
        return map.values().stream().mapToInt(v -> v).sum();
    }
}
