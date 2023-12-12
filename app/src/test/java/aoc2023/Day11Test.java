package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc2023.day11.Day11;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day11Test {

  private List<String> input = InputUtils.readInput("aoc2023/day11.txt");
  private List<String> inputExample = InputUtils.readInput("aoc2023/day11_example.txt");

  @Test
  public void testInputExamplePart1() {
    // when
    var result = Day11.part1(inputExample);

    // then
    assertEquals(374, result);
  }

  @Test
  public void testInputPart1() {
    // when
    var result = Day11.part1(input);

    // then
    assertEquals(9563821, result);
  }

  @Test
  public void testInputExamplePart2() {
    // when
    var result = Day11.part2(inputExample);

    // then
    assertEquals(82000210, result);
  }

  @Test
  public void testInputPart2() {
    // when
    var result = Day11.part2(input);

    // then
    assertEquals(827009909817L, result);
  }
}
