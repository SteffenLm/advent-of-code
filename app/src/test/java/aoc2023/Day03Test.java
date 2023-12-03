package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import aoc2023.day03.Day03;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day03Test {

    private List<String> input = InputUtils.readInput("aoc2023/day03.txt");
    private List<String> inputExample = InputUtils.readInput("aoc2023/day03_example.txt");

    @Test
    public void isSymbolTest() {
        assertEquals(false, Day03.isSymbol('.'));
        assertEquals(false, Day03.isSymbol('1'));
        assertEquals(false, Day03.isSymbol('2'));
        assertEquals(false, Day03.isSymbol('3'));
        assertEquals(false, Day03.isSymbol('4'));
        assertEquals(false, Day03.isSymbol('5'));
        assertEquals(false, Day03.isSymbol('6'));
        assertEquals(false, Day03.isSymbol('7'));
        assertEquals(false, Day03.isSymbol('8'));
        assertEquals(false, Day03.isSymbol('9'));
        assertEquals(true, Day03.isSymbol('&'));
    }

    @Test
    public void testInputExamplePart1() {
        // when
        var result = Day03.part1(inputExample);

        // then
        assertEquals(4361, result);
    }

    @Test
    public void testInputPart1() {
        // when
        var result = Day03.part1(input);

        // then
        assertEquals(521601, result);
    }

    @Test
    public void testInputExamplePart2() {
        // when
        var result = Day03.part2(inputExample);

        // then
        assertEquals(467835, result);
    }

    @Test
    public void testInputPart2() {
        // when
        var result = Day03.part2(input);

        // then
        assertEquals(80694070, result);
    }
}