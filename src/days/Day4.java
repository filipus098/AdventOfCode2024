package days;

import utils.Coordinate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
  public static void main(String[] args) {

    try {
      List<String> input = Files.readAllLines(Paths.get("src/resources/day4.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);


    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {
    Map<Coordinate, Character> coordMap = getAllCoordinates(inputList);

    int sum = 0;
    for (Map.Entry<Coordinate, Character> entry : coordMap.entrySet()) {
      if (entry.getValue() == 'X') {
        sum += findFullAdjacentCords(entry, coordMap);
      }
    }
    System.out.println("Part 1: " + sum);

  }

  private static int findFullAdjacentCords(Map.Entry<Coordinate, Character> entry, Map<Coordinate, Character> coordMap) {
    //i read the thing wrong and did this really stupidly at first
    //i wanted to have it recursively in all possible directons so for example:
    // . X .
    // M M .
    // A . .
    // . S .
    //gives 2
    //so time to rewrite it in a completely dumb way!
    int sum = 0;
    for (int xOffset = -1; xOffset < 2; xOffset++) {
      for (int yOffset = -1; yOffset < 2; yOffset++) {
        if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset, entry.getKey().y() + yOffset), 'Ü') == 'M') {
          if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset * 2, entry.getKey().y() + yOffset * 2), 'Ü') == 'A') {
            if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset * 3, entry.getKey().y() + yOffset * 3), 'Ü') == 'S') {
              sum++;
            }

          }
        }
      }
    }

    return sum;
  }

  public static void part2(List<String> inputList) {
    //oh my god this will be terrible
    //im commiting a war crime
    //:thumbsup:
    Map<Coordinate, Character> coordMap = getAllCoordinates(inputList);

    int sum = 0;
    for (Map.Entry<Coordinate, Character> entry : coordMap.entrySet()) {
      if (entry.getValue() == 'M') {
        sum += findAdjacentCords2(entry, coordMap);
      }
    }
    System.out.println("Part 2: " + sum);
  }

  private static int findAdjacentCords2(Map.Entry<Coordinate, Character> entry, Map<Coordinate, Character> coordMap) {
    int sum = 0;
    int i = 0;
    int xOffset = -1;
    int yOffset = 1;
    while (i < 2) {
      if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset, entry.getKey().y() + yOffset), 'Ü') == 'A') {
        if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset * 2, entry.getKey().y() + yOffset * 2), 'Ü') == 'S') {
          if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset * 2, entry.getKey().y()), 'Ü') == 'S') {
            if (coordMap.getOrDefault(new Coordinate(entry.getKey().x(), entry.getKey().y() + yOffset * 2), 'Ü') == 'M') {
              sum++;
            }
          }else{
            if (coordMap.getOrDefault(new Coordinate(entry.getKey().x() + xOffset * 2, entry.getKey().y()), 'Ü') == 'M') {
              if (coordMap.getOrDefault(new Coordinate(entry.getKey().x(), entry.getKey().y() + yOffset * 2), 'Ü') == 'S') {
                sum++;
              }
            }
        }
      }
    }
    xOffset = 1;
    yOffset = -1;
    i++;
  }
    return sum;
}

public static Map<Coordinate, Character> getAllCoordinates(List<String> inputList) {
  Map<Coordinate, Character> coordMap = new HashMap<>();
  int x = 0;
  int y = 0;
  for (String line : inputList) {
    for (String c : line.split("")) {
      if (!c.equals(".")) {
        coordMap.put(new Coordinate(x, y), c.charAt(0));
      }
      x++;
    }
    x = 0;
    y++;
  }
  return coordMap;
}

}
