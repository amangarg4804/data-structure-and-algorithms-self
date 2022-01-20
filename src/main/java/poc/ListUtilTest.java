package poc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ListUtilTest {

  private static final Pattern UNDERSCORE_PATTERN = Pattern.compile("_");
  private static final Pattern COMMA_PATTERN = Pattern.compile(",");



  public static void main(String[] args) {
//    String string1 = "1817_1,1816_1,2532_1,2534_1";
//    String string2 = "2532_1_2534_1_2530_1";
//        String string1 = "1817_1,1816_1,2532_1";
//    String string2 = "2532_1_2530_1_2534_1";
     String string1 = null;
    String string2 = "2530_1_2532_1_2534_1";



    List<String> moduleNames = new ArrayList<>(Arrays.asList(UNDERSCORE_PATTERN.split(string2)));
    List<String> allContextsIncludedInFilteredCollectionName = new ArrayList<>();
    for(int i =0; i< moduleNames.size(); i= i+2) {
      allContextsIncludedInFilteredCollectionName.add(moduleNames.get(i) + "_" +moduleNames.get(i+1));
    }
    if(string1 != null) {
      List<String> executedContexts = new ArrayList<>(Arrays.asList(COMMA_PATTERN.split(string1)));
      removeExecutedContexts(allContextsIncludedInFilteredCollectionName, executedContexts);
    }
    System.out.println(allContextsIncludedInFilteredCollectionName.get(0));
  }

  private static List<String> removeExecutedContexts(List<String> allContextsIncludedInFilteredCollectionName, List<String> executedContexts) {
      for(String executedContext : executedContexts) {
        allContextsIncludedInFilteredCollectionName.remove(executedContext);
      }
      return allContextsIncludedInFilteredCollectionName;
  }
}
