package days;

import utils.AocArrayUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day2 {

  public static void main(String[] args) {

    try {
      List<String> input = Files.readAllLines(Paths.get("src/resources/day2.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);


    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {
    int sum = 0;
    for (String line : inputList) {
      String[] parts = line.split(" ");

      int direction = 0;
      boolean isValid = true;//0=not set, 1 = ascending, -1 = descending
      for (int i = 0; i < parts.length - 1; i++) {
        int distanceBetween = Integer.parseInt(parts[i]) - Integer.parseInt(parts[i + 1]);

        if (distanceOutsideRange(distanceBetween)) {
          isValid = false;
          break;
        }

        if (direction == 0) {
          if (distanceBetween > 0) {
            direction = 1;
          } else {
            direction = -1;
          }
        } else {
          if (distanceBetween > 0 && !(direction > 0)) {
            isValid = false;
            break;
          } else if (distanceBetween < 0 && !(direction < 0)) {
            isValid = false;
            break;
          }
        }
      }
      if (isValid) {
        sum++;
      }
    }
    System.out.println("Part 1: " + sum);
  }

  public static void part2(List<String> inputList) {
    int sum = 0;
    for (String line : inputList) {
      String[] parts = line.split(" ");

      int isValid = validateLevelPart2(parts); //wenn int =-1 = valid, wenn was anderes index vom falschen hawara
      if (isValid == -1) {
        sum++;
      }else if(validateLevelPart2(AocArrayUtils.copyArrayWithoutSpecificElement(parts,isValid))==-1){
        sum++;
      }else if (validateLevelPart2(AocArrayUtils.copyArrayWithoutSpecificElement(parts,isValid-1))==-1){ //first/second element error
        sum++;
      }else if (validateLevelPart2(AocArrayUtils.copyArrayWithoutSpecificElement(parts,isValid-2))==-1){  //direction error on the second/third element
        sum++;
      }
    }
    System.out.println("Part 2: " + sum);
  }


  private static int validateLevelPart2(String[] parts) {

    int direction = 0; //0=not set, 1 = ascending, -1 = descending

    for (int i = 0; i < parts.length - 1; i++) {
      int distanceBetween = Integer.parseInt(parts[i]) - Integer.parseInt(parts[i + 1]);

      if (distanceOutsideRange(distanceBetween)) {
        return i+1;
      }

      if (direction == 0) {
        if (distanceBetween > 0) {
          direction = 1;
        } else {
          direction = -1;
        }
      } else {
        if (distanceBetween > 0 && !(direction > 0)) {
          return i+1;
        } else if (distanceBetween < 0 && !(direction < 0)) {
          return i+1;
        }
      }
    }
    return -1;
  }

  private static boolean distanceOutsideRange(int distanceBetween) {
    return Math.abs(distanceBetween) > 3 || Math.abs(distanceBetween) < 1;
  }


}
