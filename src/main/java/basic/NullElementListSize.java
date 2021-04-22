package basic;

import java.util.ArrayList;
import java.util.List;

public class NullElementListSize {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add(null);

    System.out.println(list.size());

    System.out.println(list.get(0)==null);
  }

}
