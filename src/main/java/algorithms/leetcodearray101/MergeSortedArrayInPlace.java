package algorithms.leetcodearray101;

public class MergeSortedArrayInPlace {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // nums1 is of length m+n, elements after m are null, result is stored in nums1
        int arr1Index = m-1;
        int arr2Index = n-1;
        int resultIndex = m+n-1;

        while (arr1Index>=0 && arr2Index >=0) {
            if(nums1[arr1Index] > nums2[arr2Index]) { // NOTE: We start filling maximum element first
                 nums1[resultIndex--] = nums1[arr1Index--];
            } else {
                nums1[resultIndex--] = nums2[arr2Index--];
            }
        }

        while (arr1Index >=0) {
            nums1[resultIndex--] = nums1[arr1Index--];  // This loop is not required, if arr2Index has reached 0 and arr1Index is still greater than 0, while running this loop,
            // we will be simply assigning same values to array elements
        }
        while (arr2Index >=0) {
            nums1[resultIndex--] = nums2[arr2Index--];
        }

    }
}
