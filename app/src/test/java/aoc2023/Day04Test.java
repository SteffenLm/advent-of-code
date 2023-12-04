package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import aoc2023.day04.Day04;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day04Test {

    private List<String> input = InputUtils.readInput("aoc2023/day04.txt");
    private List<String> inputExample = InputUtils.readInput("aoc2023/day04_example.txt");

    @Test
    public void testInputExamplePart1() {
        // when
        var result = Day04.part1(inputExample);

        // then
        assertEquals(13, result);
    }

    @Test
    public void testInputPart1() {
        // when
        var result = Day04.part1(input);

        // then
        assertEquals(21213, result);
    }

    @Test
    public void testInputExamplePart2() {
        // when
        var result = Day04.part2(inputExample);

        // then
        assertEquals(30, result);
    }

    @Test
    public void testInputPart2() {
        // when
        var result = Day04.part2(input);

        // then
        assertEquals(8549735, result);
    }
}