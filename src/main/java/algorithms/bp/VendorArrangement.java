package algorithms.bp;

import java.util.Arrays;

public class VendorArrangement {
//There are K vendors in a city who want to set their shops on one street in a city.
// There are N open spaces available to set shops. all N spaces are only at one side of road (i.e.on X axis).
//vendors are quite competitive and fight with each other continuously.
// To avoid their fight, we need to keep them away from each other as far as possible.
//Here we are given the distance d which should be there between each of the vendors.
// Check if it is possible for all vendors to set their shops in given available spaces.
//Return true if all vendors can be arranged, otherwise return false.
//
//Input :
//Spaces(positions) : 1, 4, 2, 8, 9
//K  : 3
//distance : 3
//Answer: true
//
//Spaces (positions) : 1, 4, 2, 8, 9
//K : 4
//dist : 3
//Ans : ? (false)

    public boolean canBeArranaged(int[] openSpaces, int minimumDistance, int vendorCount) {
        Arrays.sort(openSpaces); //1, 2, 4, 8, 9, minimumDistance=3, vendorCount=3
        int startSpace =0;
        int vendorsArranged =1;
        while(startSpace < openSpaces.length) {
            int endSpace = startSpace + 1;
            while(endSpace < openSpaces.length && openSpaces[endSpace] - openSpaces[startSpace] < minimumDistance) {
                endSpace++;
            }
            if(openSpaces[endSpace] - openSpaces[startSpace] >=minimumDistance) {
                vendorsArranged++;
            }
            startSpace = endSpace;
        }
        return vendorsArranged >= vendorCount;
    }

    public boolean canBeArranaged2(int[] openSpaces, int minimumDistance, int vendorCount) {
        // shorter code
        Arrays.sort(openSpaces); //1, 2, 4, 8, 9, minimumDistance=3, vendorCount=3
        int previous =openSpaces[0];
        int vendorsArranged =1;
        for(int i = 1; i < openSpaces.length; i++) {
            if(openSpaces[i] - previous >= minimumDistance) {
                vendorsArranged++;
                previous = openSpaces[i];
            }
        }
        return vendorsArranged >= vendorCount;
        // The time complexity of the provided solution is O(N log N), where N is the number of available spaces (positions). Here's a breakdown of the time complexity:
        //Sorting the array of space positions using Arrays.sort() takes O(N log N) time complexity, where N is the number of spaces.
        //After sorting, the solution iterates through the sorted array once. This linear iteration takes O(N) time complexity.
    }

    //Now we are not given distance d between the shops. We need to decide what minimum distance we can keep to keep shops as distant from each other as possible
    // Point to consider while calculating distance : vendors are quite competitive and fight with each other continuously.
    // To avoid their fight, we need to keep them away from each other as far as possible.
    //Return the distance d needed to place all shops.
    //
    //Input :
    //Spaces : 1, 4, 2, 8, 9
    //K : 3
    //Answer: 3
    //
    //Spaces : 1, 4, 2, 8, 9
    //K : 2
    //answer: 8
    public int minimumDistanceToAvoidFight(int[] openSpaces, int vendorCount) {
        //we need to keep them away from each other as far as possible.
        // 1, 2, 4, 8, 9, for, k=3 , maximum distance is 3 {1, 4, 9}
        // 1, 2, 4, 5, 8, 9, for, k=3 , maximum distance is 4 {1, 5, 9}, we want to maximize distance, so in this case we choose 5 over 4
        // 1, 2, 4, 5, 6, 7, 8 for, k=3 , maximum distance is 3 {1, 5, 8}
        int left = 0 ;
        int right = openSpaces[openSpaces.length -1] - openSpaces[0];
        int result = -1;
        //left is initialized to 0 because it represents the minimum possible distance. In other words, it's the lower bound for the search.
        //right is initialized to spaces[spaces.length - 1] - spaces[0] because it represents the maximum possible distance that could exist between any two shops.
        // This ensures that we are initially considering the largest possible search space.
        while (left <= right) {
            // if left and right are same, mid will also be equal to left and right.  It also means previously, this value was never tested for
            // In this case we still have to verify whether the distance can be equal to left/right/mid (all same)
            int mid = left + (right - left) / 2;
            if(canBeArranaged(openSpaces, mid, vendorCount)) {
                // If it's possible to place all vendors with distance'mid', increase'mid''
                result = mid;
                left = mid + 1;
            } else {
                // If it's not possible to place all vendors with distance 'mid', decrease 'mid'
                right = mid - 1;
            }
        }
        return result;
        //The time complexity of this solution is O(N log R), where N is the number of available spaces and R is the range of possible distances.
        // However, in practice, the number of iterations in the binary search is typically small, so the algorithm is efficient.
    }

}
