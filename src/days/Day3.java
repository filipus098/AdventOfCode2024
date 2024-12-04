package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day3 {
  public static void main(String[] args) {

    try {
      List<String> input = Files.readAllLines(Paths.get("src/resources/day3.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);


    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {
    long sum = 0;

    for (String line : inputList) {
      String[] firstParts = line.split("mul\\(");
      for (String part : firstParts) {

        String[] toProcess = part.split(",");

        if (toProcess.length < 2) {
          continue;
        }

        try {
          long firstNum = Long.parseLong(toProcess[0]);
          long secondNum = Long.parseLong(toProcess[1].split("\\)")[0]);

          if (!part.contains(secondNum + ")")) {//very bad workaround for the case of mul(1,212,1)
            continue;
          }

          sum += firstNum * secondNum;
        } catch (NumberFormatException e) {

          continue;
        }
      }
    }
    System.out.println("Part 1: " + sum);
  }

  public static void part2(List<String> inputList) {
    long sum = 0;
    boolean isEnabled = true;
    for (String line : inputList) {
      String[] firstParts = line.split("mul\\(");
      for (String part : firstParts) {
        try {
          if (isEnabled) {
            String[] toProcess = part.split(",");

            if (toProcess.length < 2) {
              continue;
            }

            long firstNum = Long.parseLong(toProcess[0]);
            long secondNum = Long.parseLong(toProcess[1].split("\\)")[0]);

            if (!part.contains(secondNum + ")")) {//very bad workaround for the case of mul(1,212,1)
              continue;
            }

            sum += firstNum * secondNum;
          }
        } catch (NumberFormatException e) {
          continue;
        } finally {
          boolean hasDo = part.contains("do()");
          boolean hasDont = part.contains("don't()");

          if (hasDo && hasDont) {
            isEnabled = part.indexOf("do()") < part.indexOf("don't()");
          } else if (hasDo) {
            isEnabled = true;
          } else if (hasDont) {
            isEnabled = false;
          }
        }
      }
    }
    System.out.println("Part 2: " + sum);
  }


}
