package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day5 {

  public static void main(String[] args) {

    try {
      List<String> input = Files.readAllLines(Paths.get("src/resources/day5.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);


    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {

    Map<Integer, List<Integer>> rulesMap = new HashMap<>();
    List<String> secondInputList = new ArrayList<>(inputList);
    for (String lineFirstSection : inputList) {
      secondInputList.remove(lineFirstSection);
      if (lineFirstSection.isEmpty()) {
        break;
      }
      String[] split = lineFirstSection.split("\\|");
      Integer rule = Integer.parseInt(split[0]);
      List<Integer> list;
      if ((list = rulesMap.get(rule)) == null) {
        List<Integer> ruleList = new ArrayList<>();
        rulesMap.put(rule, ruleList);
        ruleList.add(Integer.parseInt(split[1]));
      } else {
        list.add(Integer.parseInt(split[1]));
      }

    }

    int sum = 0;
    for (String lineSecondSection : secondInputList) {
      String[] split = lineSecondSection.split(",");
      Map<Integer, Integer> mapNumbers = new HashMap<>();
      for (int i = 0; i < split.length; i++) {
        Integer currentNum = Integer.parseInt(split[i]);

        mapNumbers.put(currentNum, i);
      }
      boolean broke = false;
      for (Map.Entry<Integer, Integer> entry : mapNumbers.entrySet()) {
        List<Integer> ruleList = rulesMap.get(entry.getKey());
        if(ruleList == null) {
          continue;
        }
        for (Integer rule : ruleList) {
          Integer indexRule = mapNumbers.get(rule);
          if(indexRule == null) {
            continue;
          }
          if (indexRule < entry.getValue()) {
            broke = true;
          }
        }
        if (broke) {
          break;
        }
      }
      if(!broke){
        sum+=Integer.parseInt(split[split.length/2]);
      }
    }

    System.out.println("Part1: " + sum);
  }

  public static void part2(List<String> inputList) {

  }

}
