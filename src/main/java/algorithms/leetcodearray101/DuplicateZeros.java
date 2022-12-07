package algorithms.leetcodearray101;

public class DuplicateZeros {

    public void duplicateZeros(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==0) {
                if(i+1 ==arr.length) {
                    break;
                }
                for(int j=arr.length-2; j>i;j--) {
                    arr[j+1] = arr[j];
                }
                arr[i+1] =0;
                i++;
            }
        }

    }

    public void duplicateZeros1(int[] arr) {
        // intuition is to count zeros. Imaginary array size equal to current size + count of zeros to accommodate duplicate zeros
        int zeroCount = 0;
        for(int i=0; i< arr.length;i++) {
            if(arr[i] ==0) {
                zeroCount++;
            }
        }
        int sizeWithAllDuplicateZeros = arr.length + zeroCount;

        for( int origIndex = arr.length -1, dupZeroIndex =sizeWithAllDuplicateZeros -1 ; origIndex >=0; origIndex--, dupZeroIndex--) {
            if(arr[origIndex] !=0) {
                if(dupZeroIndex < arr.length) {
                    arr[dupZeroIndex] = arr[origIndex]; // always set value based on imaginary array's current index,i.e, if that index is less than original array's length
                }
            } else {
                if(dupZeroIndex < arr.length) {  // take the example of array {0, 1}, dupZeroIndex is at 1 when origIndex is at 0
                    arr[dupZeroIndex] =0;
                }
                dupZeroIndex--;
                if(dupZeroIndex <arr.length) {
                    arr[dupZeroIndex] =0;
                }
            }
        }
    }
}
