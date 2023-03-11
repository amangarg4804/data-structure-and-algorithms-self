package algorithms.leetcodebinarysearch;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        // merge both the arrays and find the median based on middle index(es)
        int[] merged = mergeSortedArrays(nums1, nums2);
        if(merged.length%2==0) {
            int mid1 = merged[merged.length/2];
            int mid2 = merged[(merged.length/2) -1]; //NOTE: array of length 4 {1, 2, 3, 4} Middle indexes are length/2 and length/2-1
           return  (double)(mid1+mid2)/2;
        }
        return merged[merged.length/2];
        // time O(n1+n2)
        // Space O(n1+n2) n1 n2 being the length of nums1 and nums2 respectively
        // how can we solve it without using extra space?
    }

    private int[] mergeSortedArrays(int[] nums1, int[] nums2) {
        int[] merged = new int[nums1.length + nums2.length];
        int nums1Index = 0;
        int nums2Index = 0;
        int mergedIndex =0;
        while (nums1Index < nums1.length && nums2Index < nums2.length) {
            if(nums1[nums1Index] < nums2[nums2Index]) {
                merged[mergedIndex++] = nums1[nums1Index++];
            } else {
                merged[mergedIndex++] = nums2[nums2Index++];
            }
        }
        while (nums1Index < nums1.length) {
            merged[mergedIndex++] = nums1[nums1Index++];
        }
        while (nums2Index < nums2.length) {
            merged[mergedIndex++] = nums2[nums2Index++];
        }
        return merged;
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        // We don't actually need to merge the arrays, we can just figure out the median indexes and iterate till we reach them
        int totalLength = nums1.length + nums2.length;
        int nums1Index = 0;
        int nums2Index = 0;
        int mergedIndex =0;
        int medianIndex1=0;
        int medianIndex2 =0;
        if(totalLength%2==0) {

            medianIndex1=(totalLength/2)-1;
            medianIndex2 = totalLength/2;
        } else {
            medianIndex1= medianIndex2 =totalLength/2;
        }

        //now we know the median indexes, we have to find values on those indexes if the array was merged, we won't be actually merging the array
        // // we have refactored the mergedSortedArray method :
        // 1. we have merged all while conditions to have a single while condition. In this situation mergedIndex will be increment only once in a single iteration
        // 2. all the while conditions are now changed to if else conditions so they run only once in one iteration of outer while loop
        // 3. Since the mergedIndex has to be incremented in all if else conditions, we increment it only once at the end of the loop
        int medianNum1 = 0;
        int medianNum2 =0;
        while (mergedIndex <=medianIndex2) { // medianIndex2 is greater than or equal to medianIndex1
            int currentNum = 0;
            if(nums1Index < nums1.length && nums2Index < nums2.length) {
                if(nums1[nums1Index] < nums2[nums2Index]) {
                    currentNum = nums1[nums1Index++];
                } else {
                    currentNum = nums2[nums2Index++];
                }
            }else if (nums1Index < nums1.length) {
                currentNum = nums1[nums1Index++];
            }else if (nums2Index < nums2.length) {
                currentNum = nums2[nums2Index++];
            }
            if(mergedIndex ==medianIndex1) {
                medianNum1 = currentNum;
            }
            if(mergedIndex ==medianIndex2) {
                medianNum2 =currentNum;
            }
            mergedIndex++;// mergedIndex has to be incremented in all conditions
        }

        if(totalLength%2==0) {
            return (double)(medianNum1 +medianNum2)/2;
        }
        return medianNum2;
    }

    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        // We don't actually need to merge the arrays, we can just figure out the median indexes and iterate till we reach them
        // In this solution we eliminate the last 2 if conditions, we also remove variables medianIndex1, medianIndex2
        int totalLength = nums1.length + nums2.length;
        int nums1Index = 0;
        int nums2Index = 0;
        int mergedIndex =0;

        //now we know the median indexes, we have to find values on those indexes if the array was merged, we won't be actually merging the array
        // // we have refactored the mergedSortedArray method :
        // 1. we have merged all while conditions to have a single while condition. In this situation mergedIndex will be increment only once in a single iteration
        // 2. all the while conditions are now changed to if else conditions so they run only once in one iteration of outer while loop
        // 3. Since the mergedIndex has to be incremented in all if else conditions, we increment it only once at the end of the loop
        int medianNum1 = 0;
        int medianNum2 =0;
        while (mergedIndex <=totalLength/2) { // We want the loop to run for last median index so that medianNum2 can be set correctly. For array length 6 {0,1,2,3,4,5}, last median index is 3, for length 5 {0,1,2,3,4}, last median index is 2
            medianNum1 = medianNum2;
            if(nums1Index < nums1.length && nums2Index < nums2.length) {
                if(nums1[nums1Index] < nums2[nums2Index]) {
                    medianNum2 = nums1[nums1Index++];
                } else {
                    medianNum2 = nums2[nums2Index++];
                }
            }else if (nums1Index < nums1.length) {
                medianNum2 = nums1[nums1Index++];
            }else if (nums2Index < nums2.length) {
                medianNum2 = nums2[nums2Index++];
            }
            mergedIndex++;// mergedIndex has to be incremented in all conditions
        }

        if(totalLength%2==0) {
            return (double)(medianNum1 +medianNum2)/2;
        }
        return medianNum2;
    }
    //Intuition :
    //
    //We came up with a naive solution because of the hint that two arrays are sorted and we want elements from merged sorted arrays.
    // If we look into the word “sorted arrays”, we can think of a binary solution. Hence, we move to an efficient solution using binary search.
    // But how to apply binary search? Let’s look into the thought process.
    //
    //We know that we will get answers only from the final merged sorted arrays. We figured it out with the naive approach discussed above.
    // We will partition both the arrays in such a way that the left half of the partition will contain elements,
    // which will be there when we merge them, till the median element and rest in the other right half. This partitioning of both arrays will be done by binary search.
    //
    //Approach :
    //
    //We will do a binary search in one of the arrays which have a minimum size among the two.
    //
    //Firstly, calculate the median position with (n+1)/2. Point two-pointer, say low and high,
    // equal to 0 and size of the array on which we are applying binary search respectively. Find the partition of the array.
    // Check if the left half has a total number of elements equal to the median position. If not, get the remaining elements from the second array.
    // Now, check if partitioning is valid. This is only when l1<=r2 and l2<=r1. If valid, return max(l1,l2)(when odd number of elements present) else return (max(l1,l2)+min(r1,r2))/2.
    //
    //If partitioning is not valid, move ranges. When l1>r2, move left and perform the above operations again. When l2>r2,
    // move right and perform the above operations.
    public double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        // using binary search on smaller array
        // time complexity is reduced to O(Log(m+n))
        // We want to have half of the elements to the left and half to the right of cut
        // Cut the first array into two. all elements to the left of cut will be less than all elements to the right.
        // last number of left half = l1. first number of right half = r1
        // Similarly cut the second array into two. last number of left half =l2. First number of right half = r2.
        // Now for the both left halves to have smaller elements than right half:
        //  l1 should be less than r2
        //  l2 should be less than r1
        // If l1 is not less than r2. Meaning we need to find a smaller l1, so right--
        // if l2 is not less than r1.
        if(nums1.length > nums2.length) {
            return findMedianSortedArrays4(nums2, nums1);
        }
        int start =0;
        int end = nums1.length-1;
        // how many elements do we want in left half of merged array?
        // In case of 10(even), we have 5 to the left and 5 to the right. And we can do Max (l1,l2)+Min(r1,r2)/2
        // in case of 11(odd), we can have 5 to the left and 6 to the right. And we can do min(r1,r2)
        int median = (nums1.length + nums2.length)/2; // will return 5 for both 10 and 11
        while (start<=end+1) { // code will not work with condition start<=end because we might have a case where all elements in the first array are smaller than right and both arrays have same length
            // e.g. {1,2} and {3,4}. In this case, we will do start ++ twice and start will become 2. because r1 is Integer_Min and l2 is 3,4. l2 not less than r2.
            // Once start become 2 our loop will not run if loops is based on condition start <=end
            int cut1 = start+(end-start)/2;
            // if cut1 is 2 (0 based index) and median is 5, we have picked 2 elements from nums1 (excluding the index itself because an empty array will also return 0 as mid),
            // now we can pick 3 elements from num2
            int cut2 = median - cut1; // cut2 is 3
            int l1 = cut1 != 0? nums1[cut1-1]: Integer.MIN_VALUE;
            int r1 = cut1< nums1.length? nums1[cut1]: Integer.MAX_VALUE;
            int l2 = cut2 !=0 ? nums2[cut2-1]: Integer.MIN_VALUE; // to take 3 elements from nums2, index should be 3-1, cut2-1
            int r2 = cut2 < nums2.length? nums2[cut2]: Integer.MAX_VALUE;
            if(l1 <=r2 && l2 <= r1) {
                if((nums1.length+nums2.length) %2 ==0) {
                    return (double) (Math.max(l1,l2) + Math.min(r1,r2))/2;
                } else {
                    return (double) Math.min(r1, r2);
                }
            } else if(l1 >r2) {
                end--;
            } else {
                start++;
            }
        }
        return 0.0;

    }

    public static void main(String[] args) {
//        new MedianOfTwoSortedArrays().findMedianSortedArrays1(new int[]{1, 2}, new int[]{3, 4});
//        new MedianOfTwoSortedArrays().findMedianSortedArrays2(new int[]{1, 3}, new int[]{2});
//        new MedianOfTwoSortedArrays().findMedianSortedArrays2(new int[]{1, 2}, new int[]{3,4});
        new MedianOfTwoSortedArrays().findMedianSortedArrays4(new int[]{1, 2}, new int[]{3,4});
    }
}
