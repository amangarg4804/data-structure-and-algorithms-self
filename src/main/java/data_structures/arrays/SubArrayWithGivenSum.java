package data_structures.arrays;

public class SubArrayWithGivenSum {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 7, 5};
        int sum = 12;
        System.out.println(givenSumSubArray(arr, sum));
    }

    private static String givenSumSubArray(int[] arr, int sum) {

        for (int i = 0; i < arr.length; i++) {
            int sumStartingHere = arr[i];
            if (sumStartingHere == sum) {
                return (i + 1) + " " + (i + 1);
            }
            for (int j = i + 1; j < arr.length; j++) {
                sumStartingHere = sumStartingHere + arr[j];
                if (sumStartingHere == sum) {
                    return (i + 1) + " " + (j + 1);
                }
            }
        }
        return "-1";
    }

    private static String givenSumSubArrayOptmized(int[] arr, int sum) {

    // If the array is of postive integers,
        // then we can remove the first element from subarray if the subarray exceeds the sum
// 1 2 3 7 5
// sum = 12
// currentsum 12
// startIndex = 1 ,
// i = 4
        // 117 119 96 48 127 172 139 70 113 68 100 36 95 104 12 123 134 (last position check)
        int startIndex = 0;
        int currentSum = arr[0];
        for (int i = 1; i<= arr.length; i++) {
            while(currentSum > sum && startIndex < i-1) {
                currentSum = currentSum - arr[startIndex];
                startIndex++;
            }
            if(currentSum == sum) {
                return startIndex+1 + " " + i;
            }
            if(i< arr.length) {
                currentSum = currentSum + arr[i];
            }
        }
        return "-1";
    }

}
