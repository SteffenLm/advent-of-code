package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc2023.day02.Day02;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day02Test {

  private List<String> input = InputUtils.readInput("aoc2023/day02.txt");
  private List<String> inputExample = InputUtils.readInput("aoc2023/day02_example.txt");

  @Test
  public void testInputExamplePart1() {
    // when
    var result = Day02.part1(inputExample);

    // then
    assertEquals(8, result);
  }

  @Test
  public void testInputPart1() {
    // when
    var result = Day02.part1(input);

    // then
    assertEquals(2439, result);
  }

  @Test
  public void testInputExamplePart2() {
    // when
    var result = Day02.part2(inputExample);

    // then
    assertEquals(2286, result);
  }

  @Test
  public void testInputPart2() {
    // when
    var result = Day02.part2(input);

    // then
    assertEquals(63711, result);
  }
}
