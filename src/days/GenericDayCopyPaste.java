package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericDayCopyPaste {

  public static void main(String[] args) {

    try {
      List<String> input =  Files.readAllLines(Paths.get("src/resources/dayX.txt"));

      List<String> part1List = new ArrayList<>(input);
      List<String> part2List = new ArrayList<>(input);

      part1(part1List);
      part2(part2List);


    } catch (IOException e) {
      System.err.println("ERROR READING FILE! \n" + e.getMessage());
    }

  }

  public static void part1(List<String> inputList) {

  }

  public static void part2(List<String> inputList) {

  }

}
