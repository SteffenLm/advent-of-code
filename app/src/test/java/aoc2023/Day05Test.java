package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc2023.day05.Day05;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day05Test {

  private List<String> input = InputUtils.readInput("aoc2023/day05.txt");
  private List<String> inputExample = InputUtils.readInput("aoc2023/day05_example.txt");

  @Test
  public void testInputExamplePart1() {
    // when
    var result = Day05.part1(inputExample);

    // then
    assertEquals(35, result);
  }

  @Test
  public void testInputPart1() {
    // when
    var result = Day05.part1(input);

    // then
    assertEquals(324724204, result);
  }

  @Test
  public void testInputExamplePart2() {
    // when
    var result = Day05.part2(inputExample);

    // then
    assertEquals(46, result);
  }

  @Test
  public void testInputPart2() {
    // when
    var result = Day05.part2(input);

    // then
    assertEquals(-1, result);
  }
}
