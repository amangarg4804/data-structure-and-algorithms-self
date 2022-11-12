package algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BrickWall {

  public static void main(String[] args) {
    List<List<Integer>> list = new ArrayList<>();

    List<Integer> row1 = new ArrayList<>();
    row1.add(1);
    row1.add(2);
    row1.add(2);
    row1.add(1);

    List<Integer> row2 = new ArrayList<>();
    row2.add(3);
    row2.add(1);
    row2.add(2);

    List<Integer> row3 = new ArrayList<>();
    row3.add(1);
    row3.add(3);
    row3.add(2);

    List<Integer> row4 = new ArrayList<>();
    row4.add(2);
    row4.add(4);

    List<Integer> row5 = new ArrayList<>();
    row5.add(3);
    row5.add(1);
    row5.add(2);

    List<Integer> row6 = new ArrayList<>();
    row6.add(1);
    row6.add(3);
    row6.add(1);
    row6.add(1);
    list.add(row1);
    list.add(row2);
    list.add(row3);
    list.add(row4);
    list.add(row5);
    list.add(row6);

    System.out.println(leastBricksSolution(list));

  }

  public int leastBricksIntuition(List<List<Integer>> wall) {


    // Intuition is to  calculate width of adjacent bricks in a row and then compare if any of those widths match other rows
    int leastBricks = 0;

    List<Set<Integer>> allRowSums = new ArrayList<>();

    for (int row = 0; row < wall.size(); row++) {
      Set<Integer> thisRowSums = new HashSet<>();
      int currentSum = 0;
      for (int brick = 0; brick < wall.get(row).size(); brick++) {
        currentSum = wall.get(row).get(brick);
        thisRowSums.add(currentSum);
      }
      allRowSums.add(thisRowSums);
    }

    int noOfCrosses = 0;


    // this would be a naive approach..
    for (int row = 0; row < allRowSums.size(); row++) {

      for (int otherRows = 0; otherRows < allRowSums.size(); otherRows++) {

      }


    }

    return leastBricks;
  }

  public static int leastBricksSolution(List<List<Integer>> wall) {

    int leastBricks = 0;

    List<Set<Integer>> allRowSums = new ArrayList<>();

    Map<Integer, Integer> sumToCount = new HashMap<>();
    for (int row = 0; row < wall.size(); row++) {
      int currentSum = 0;
      for (int brick = 0; brick < wall.get(row).size()-1; brick++) {
        currentSum = currentSum + wall.get(row).get(brick);

        if (sumToCount.containsKey(currentSum)) {
          Integer existingCount = sumToCount.get(currentSum);
          sumToCount.put(currentSum, existingCount + 1);
        } else {
          sumToCount.put(currentSum, 1);
        }
      }
    }

    System.out.println(sumToCount);

    int maxSum = 0;
    for(Entry<Integer, Integer> entry : sumToCount.entrySet()) {
      if(entry.getValue()>maxSum) {
        maxSum = entry.getValue();
      }
    }

    return wall.size()-maxSum;
  }



}
