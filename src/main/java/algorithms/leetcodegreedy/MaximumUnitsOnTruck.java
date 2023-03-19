package algorithms.leetcodegreedy;

import java.util.Arrays;

//Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
//Output: 8
//Explanation: There are:
//- 1 box of the first type that contains 3 units.
//- 2 boxes of the second type that contain 2 units each.
//- 3 boxes of the third type that contain 1 unit each.
//You can take all the boxes of the first and second types, and one box of the third type.
//The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
public class MaximumUnitsOnTruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) { // truck size is maximum no of boxes
        // to maximize no of units in the truck, we should place boxes with maximum units first
        // we can sort the array in a decreasing/descending order and place the units from first box in sorted array till the units of this particular type are exhausted

        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int totalUnits = 0;
        for (int[] boxType : boxTypes) {
            int noOfBoxes = boxType[0];
            int noOfUnitsPerBox = boxType[1];
            if (noOfBoxes == truckSize) {
                // all boxes can be placed and after placing them no boxes will be left to be placed
                totalUnits = totalUnits + (noOfBoxes * noOfUnitsPerBox);
                break;
            }
            if (noOfBoxes < truckSize) {
                // all boxes can be placed. truck can still accommodate more boxes after placing this batch
                totalUnits = totalUnits + (noOfBoxes * noOfUnitsPerBox);
                truckSize = truckSize - noOfBoxes;
            } else {
                // noOfBoxes > truckSize
                // we can't place all available boxes. We can only place truckSize no of boxes
                // loop should break as there won't be any more boxes to be placed
                totalUnits = totalUnits + (truckSize * noOfUnitsPerBox);
                break;
            }
        }
        return totalUnits;
    }

    public int maximumUnits2(int[][] boxTypes, int truckSize) { // truck size is maximum no of boxes
        // optimized
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int totalUnits = 0;
        for (int[] boxType : boxTypes) {
            int noOfBoxes = boxType[0];
            int noOfUnitsPerBox = boxType[1];
            if (noOfBoxes >= truckSize) {
                totalUnits = totalUnits + (truckSize * noOfUnitsPerBox);
                break;
            } else  {
                // all boxes can be placed. truck can still accommodate more boxes after placing this batch
                totalUnits = totalUnits + (noOfBoxes * noOfUnitsPerBox);
                truckSize = truckSize - noOfBoxes;
            }
        }
        return totalUnits;
    }

    public int maximumUnits3(int[][] boxTypes, int truckSize) { // truck size is maximum no of boxes
        // instead of using Arrays.sort, we can also use bucket sort because:
        //1 <= boxTypes.length <= 1000
        // 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
        // array index will represent no of units in a box, value will represent no of boxes
        int totalUnits = 0;
        int [] sortedBoxes = new int[1001];// to save doing -1 +1 as the no of boxes can be from 1 to 1000, 0th index is of no use
        for (int[] boxType : boxTypes) {
            sortedBoxes[boxType[1]] += boxType[0];
        }
        // iterate from the box containing most units
        for(int units =1000; units>0; units--) {
            int noOfBoxes = sortedBoxes[units];
            if (noOfBoxes >= truckSize) {
                totalUnits = totalUnits + (truckSize * units);
                break;
            } else  {
                // all boxes can be placed. truck can still accommodate more boxes after placing this batch
                totalUnits = totalUnits + (noOfBoxes * units);
                truckSize = truckSize - noOfBoxes;
            }
        }
        return totalUnits;
    }

}
