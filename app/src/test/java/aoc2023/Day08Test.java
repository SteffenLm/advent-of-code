package aoc2023;

import static org.junit.jupiter.api.Assertions.assertEquals;

import aoc2023.day08.Day08;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import utils.InputUtils;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class Day08Test {

  private List<String> input = InputUtils.readInput("aoc2023/day08.txt");
  private List<String> inputExample = InputUtils.readInput("aoc2023/day08_example.txt");
  private List<String> inputExample2 = InputUtils.readInput("aoc2023/day08_example2.txt");

  @Test
  public void testInputExamplePart1() {
    // when
    var result = Day08.part1(inputExample);

    // then
    assertEquals(2, result);
  }

  @Test
  public void testInputPart1() {
    // when
    var result = Day08.part1(input);

    // then
    assertEquals(23147, result);
  }

  @Test
  public void testInputExamplePart2() {
    // when
    var result = Day08.part2(inputExample2);

    // then
    assertEquals(6, result);
  }

  @Test
  public void testInputPart2() {
    // when
    var result = Day08.part2(input);

    // then
    assertEquals(-1, result);
  }
}
