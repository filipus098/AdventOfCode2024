package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day1 {
  public static void main(String[] args) {

    try {
      List<String> input = Files.readAllLines(Paths.get("src/resources/day1.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);

    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();

    for (String line : inputList) {

      String[] parts = line.split("\\s+");
      left.add(Integer.parseInt(parts[0]));
      right.add(Integer.parseInt(parts[1]));
    }

    Collections.sort(left);
    Collections.sort(right);

    int sum = 0;

    for (int i = 0; i < left.size(); i++) {
      sum += Math.abs(left.get(i) - right.get(i));
    }
    System.out.println("Part 1 result: " + sum);
  }

  public static void part2(List<String> inputList) {
    Map<Integer, Integer> left = new HashMap<>();
    Map<Integer, Integer> right = new HashMap<>();

    for (String line : inputList) {
      String[] parts = line.split("\\s+");
      left.merge(Integer.parseInt(parts[0]), 1, Integer::sum);
      right.merge(Integer.parseInt(parts[1]), 1, Integer::sum);
    }

    int sum = 0;
    for (Map.Entry<Integer, Integer> entryLeft : left.entrySet()) {
      Integer valueRight = right.get(entryLeft.getKey());
      if (valueRight != null) {
        sum += valueRight * entryLeft.getValue() * entryLeft.getKey();
      }
    }
    System.out.println("Part 2 result: " + sum);
  }
}