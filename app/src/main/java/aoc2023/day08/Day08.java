package aoc2023.day08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day08 {
  private record Node(String from, String left, String right) {
    public static Node from(String line) {
      String from = line.split(" = ")[0];
      String directionsString = line.split(" = ")[1];
      String[] directions = directionsString.substring(1, directionsString.length() - 1).split(", ");
      return new Node(from, directions[0], directions[1]);
    }
  }

  public static long part1(List<String> input) {
    char[] instructions = input.get(0).toCharArray();
    Map<String, Node> nodes = input.stream()
        .skip(2)
        .map(line -> Node.from(line))
        .collect(Collectors.toMap(node -> node.from, node -> node));
    long steps = 0;
    int instruction = 0;
    Node node = nodes.get("AAA");
    while (!node.from.equals("ZZZ")) {
      steps++;
      node = instructions[instruction] == 'R' ? nodes.get(node.right()) : nodes.get(node.left());
      instruction = instruction == instructions.length - 1 ? 0 : instruction + 1;
    }
    return steps;
  }

  public static long part2(List<String> input) {
    char[] instructions = input.get(0).toCharArray();
    Map<String, Node> nodes = input.stream()
        .skip(2)
        .map(line -> Node.from(line))
        .collect(Collectors.toMap(node -> node.from, node -> node));
    int instruction = 0;
    List<Node> startingNodes = new ArrayList<>(
        nodes.values().stream().filter((node) -> node.from().endsWith("A")).toList());
    HashMap<Integer, Integer> nodeSteps = new HashMap<>();
    while (!startingNodes.stream().allMatch(node -> node.from().endsWith("Z"))) {
      for (int i = 0; i < startingNodes.size(); i++) {
        Node currentNode = startingNodes.get(i);
        if (!currentNode.from().endsWith("Z")) {
          Node nextNode = instructions[instruction] == 'R' ? nodes.get(currentNode.right())
              : nodes.get(currentNode.left());
          startingNodes.set(i, nextNode);
          nodeSteps.put(i, nodeSteps.get(i) != null ? nodeSteps.get(i) + 1 : 1);
        }
      }
      instruction = instruction == instructions.length - 1 ? 0 : instruction + 1;
    }
    return nodeSteps.values().stream().reduce(1, (acc, curr) -> acc * curr);
  }
}
