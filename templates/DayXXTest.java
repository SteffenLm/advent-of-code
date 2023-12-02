package aocYYYY;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import aocYYYY.dayXX.DayXX;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class DayXXTest {

    private List<String> input = InputUtils.readInput("aocYYYY/dayXX.txt");
    private List<String> inputExample = InputUtils.readInput("aocYYYY/dayXX_example.txt");

    @Test
    public void testInputExamplePart1() {
        // when
        var result = DayXX.part1(inputExample);

        // then
        assertEquals(-1, result);
    }

    @Test
    public void testInputPart1() {
        // when
        var result = DayXX.part1(input);

        // then
        assertEquals(-1, result);
    }

    @Test
    public void testInputExamplePart2() {
        // when
        var result = DayXX.part2(inputExample);

        // then
        assertEquals(-1, result);
    }

    @Test
    public void testInputPart2() {
        // when
        var result = DayXX.part2(input);

        // then
        assertEquals(-1, result);
    }
}