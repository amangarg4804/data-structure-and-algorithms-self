package basic;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class CountStreamTwice {

  public static void main(String[] args) {
    List<String> strings = Arrays.asList("abc", "cde");
    Stream<String> stream = strings.stream();
    stream.count();
    stream.count(); // IllegalStateException
  }
}
