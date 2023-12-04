package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc2023.day01.Day01;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day01Test {

  private List<String> input = InputUtils.readInput("aoc2023/day01.txt");
  private List<String> inputExample = InputUtils.readInput("aoc2023/day01_example.txt");
  private List<String> inputExample2 = InputUtils.readInput("aoc2023/day01_example2.txt");

  @Test
  public void testInputExamplePart1() {
    // when
    var result = Day01.part1(inputExample);

    // then
    assertEquals(142, result);
  }

  @Test
  public void testInputPart1() {
    // when
    var result = Day01.part1(input);

    // then
    assertEquals(54561, result);
  }

  @Test
  public void testInputExamplePart2() {
    // when
    var result = Day01.part2(inputExample2);

    // then
    assertEquals(281, result);
  }

  @Test
  public void testInputPart2() {
    // when
    var result = Day01.part2(input);

    // then
    assertEquals(54076, result);
  }
}
