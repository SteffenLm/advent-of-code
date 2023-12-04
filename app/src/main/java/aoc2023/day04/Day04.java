package aoc2023.day04;

import java.util.List;
import java.util.HashMap;
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

    public record Card(String cardId, String[] allNumbers) {
        public CardNumber getWinningNumbers() {
            var winningNumbers = allNumbers[0].trim().split(" ");
            return new CardNumber(winningNumbers);
        }

        public CardNumber getDrawnNumbers() {
            var drawnNumbers = allNumbers[1].trim().split(" ");
            return new CardNumber(drawnNumbers);
        }
    }

    public record Line(String line) {
        public Card getCard() {
            var cardNumbers = line.split(":");
            var allNumbers = cardNumbers[1].split("\\|");
            var cardId = cardNumbers[0].split(" ")[1];
            return new Card(cardId, allNumbers);
        }
    }

    public static int part1(List<String> input) {
        return input.stream()
                .map(line -> new Line(line).getCard())
                .map(card -> getNumberOfMatches(card))
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
        return 0;
    }

    private static int getNumberOfMatches(Card card) {
        return (int) card.getWinningNumbers().asStream()
                .filter(winningNumber -> !winningNumber.equals(""))
                .filter(winningNumber -> card.getDrawnNumbers().asStream()
                        .anyMatch(drawnNumber -> drawnNumber.equals(winningNumber)))
                .count();
    }

}
