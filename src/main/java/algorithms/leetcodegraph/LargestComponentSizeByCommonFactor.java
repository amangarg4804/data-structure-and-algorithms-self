package algorithms.leetcodegraph;

//You are given an integer array of unique positive integers nums. Consider the following graph:
//
//There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
//There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
//Return the size of the largest connected component in the graph.

//Input: nums = [4,6,15,35]
//Output: 4
// 4-6-15-35

// 20-50 9-63
// Input: nums = [20,50,9,63]
//Output: 2

//Input: nums = [2,3,6,7,4,12,21,39]
//Output: 8

//Constraints:
//1 <= nums.length <= 2 * 104
//1 <= nums[i] <= 105
//All the values of nums are unique.

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LargestComponentSizeByCommonFactor {
    public int largestComponentSize(int[] nums) {
        // this is a graph partitioning problem
        // what we can do is find all factors of a number and connect them in disjoint set
        // For example: if nums array contains a number 6. The factors are 2 and 3 (we have to check factors greater than 1 as per problem description)
        // So we can do a union of 6 with 2 and union of 6 with 3
        // if another number 9 comes in, the factors are 3 and 3
        // so we do union(9,3) and (9,3) (Note that if we call union function twice for same numbers,
        // there is no change in disjoint set because the union function first checks whether the two nodes are already connected)
        // As 3 was already present in previous component, so we still have total 1 component only, 6-2-3-9
        // for number 7, it will be its own component
        // Now once we have created the Disjoint set, We can iterate over each number and find its root. We can create a map with root as keuy and componet size as value
        // Keep incrementing the size against key.
        // keep track of max size and return

        // how do we know the size of Disjoint set?-  it should be the max number in nums array
        int max = Integer.MIN_VALUE;
        for(int i: nums) {
            max = Math.max(i, max);
        }
        OptimizedDisjointSet ds = new OptimizedDisjointSet(max +1); //+1 to avoid arrayIndexOutOfBound. Disjoint set should be able to contain max
        for(int i:nums) {
            // to find factors of a number, we start from 2 to square root of n, If the remainder is 0, then it is a factor
            int sqrt = (int) Math.sqrt(i);
            for(int j=2; j<= sqrt; j++) { //= too,  for 9, 3 is a factor
                if(i % j==0) {
                    ds.union(i,j);
                    ds.union(i, i/j);
                }
            }
        }
        int maxSize =0;
        Map<Integer, Integer> rootToSize = new HashMap<>();
        for(int i: nums) {
            int root = ds.find(i);
            int componentSize =rootToSize.getOrDefault(root, 0) +1;
            rootToSize.put(root, componentSize);
            maxSize = Math.max(maxSize, componentSize);
        }
        return maxSize;
        // time complexity:
        // 1. for finding max element in array : O(n)
        // 2. for for loop to find factors of each number : O(n)
        // 2.1 nested for loop iterating from 2 to sqrtM = O(sqrt(m))
        // 2.2 for each union function,since we have used Optimized union set with rank and path compression,
        // we consider the time complexity as O(1) because of ackerman function
        // so total for 2: O(n *sqrt(m) )
        // 3. For next for loop to find max size: put and get are O(1), find() in  ds is also O(1) because of path compression
        // so O(n)
        // O(n) + O(n *sqrt(m) ) + O(n)= O(n*sqrt(m))


    }
    //https://drive.google.com/file/d/1hYqYk0QGpmP8HlogtL7n5KXaYQSnqnzD/view?usp=drive_link

    public int largestComponentSize2(int[] nums) {
        // factors by sieve method we used in school
        // Each number can be represented by its prime factors
        //
        //For instance, for the number 12,factors as [2, 3, 4, 6].
        // it can be represented as 2*2*3
        //The intuition is that the prime factors of a number can represent all of its factors,
        // i.e. the integer can be characterized by a series of prime factors.

        // this is a graph partitioning problem
        // what we can do is find all factors of a number and connect them in disjoint set
        // For example: if nums array contains a number 6. The factors are 2 and 3 (we have to check factors greater than 1 as per problem description)
        // So we can do a union of 6 with 2 and union of 6 with 3
        // if another number 9 comes in, the factors are 3 and 3
        // so we do union(9,3) and (9,3) (Note that if we call union function twice for same numbers,
        // there is no change in disjoint set because the union function first checks whether the two nodes are already connected)
        // As 3 was already present in previous component, so we still have total 1 component only, 6-2-3-9
        // for number 7, it will be its own component
        // Now once we have created the Disjoint set, We can iterate over each number and find its root. We can create a map with root as keuy and componet size as value
        // Keep incrementing the size against key.
        // keep track of max size and return

        // how do we know the size of Disjoint set?-  it should be the max number in nums array
        int max = Integer.MIN_VALUE;
        for(int i: nums) {
            max = Math.max(i, max);
        }
        OptimizedDisjointSet ds = new OptimizedDisjointSet(max +1); //+1 to avoid arrayIndexOutOfBound. Disjoint set should be able to contain max
        for(int i:nums) {
            // instead of finding all factor, use sieve method and find only prime factors
            Set<Integer> primefactors = getPrimeFactors(i);
            for(int factor: primefactors) {
                    ds.union(i, factor);
            }
        }
        int maxSize =0;
        Map<Integer, Integer> rootToSize = new HashMap<>();
        for(int i: nums) {
            int root = ds.find(i);
            int componentSize =rootToSize.getOrDefault(root, 0) +1;
            rootToSize.put(root, componentSize);
            maxSize = Math.max(maxSize, componentSize);
        }
        return maxSize;
        // time complexity:
        // 1. for finding max element in array : O(n)
        // 2. for for loop to find factors of each number : O(n)
        // 2.1 nested for loop iterating from 2 to sqrtM = O(sqrt(m))
        // 2.2 for each union function,since we have used Optimized union set with rank and path compression,
        // we consider the time complexity as O(1) because of ackerman function
        // so total for 2: O(n *sqrt(m) )
        // 3. For next for loop to find max size: put and get are O(1), find() in  ds is also O(1) because of path compression
        // so O(n)
        // O(n) + O(n *sqrt(m) ) + O(n)= O(n*sqrt(m))


    }

    private Set<Integer> getPrimeFactors(int i) {
        Set<Integer> set = new HashSet<>();
        int factor =2;
        while (i >= factor*factor) {// take examples 4, 6, 12
            if(i%factor ==0) {
                set.add(factor);
                i=i/factor;
            } else {
                factor++;
            }
        }
        // add remaining value of i
        set.add(i);
        return set;
    }
}
