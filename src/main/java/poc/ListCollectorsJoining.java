package poc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListCollectorsJoining {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add("7283");
    list.add("232");
    list.add("abd");
   list.add("");
    list.add("");

    System.out.println(list.stream().collect(Collectors.joining("|")));
  }

}
