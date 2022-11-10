package algorithms.leetcoderecursion2;

//Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
//
//        Integers in each row are sorted in ascending from left to right.
//        Integers in each column are sorted in ascending from top to bottom.
public class Search2dMatrixII {

    public boolean searchMatrix(int[][] matrix, int target) {
        // we can perform binary search on each row. n rows and each having m elements, Time complexity: nlog(m)
        for (int i = 0; i < matrix.length; i++) {
            boolean result = binarySearch(matrix[i], target);
            if (result) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return false;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        // we can start with top right corner and keep moving the pointer till we find the element or reach out of bounds. We either increase row index or decrease column index
        // time complexity O(m+n), in worst case, we will be traversing in L shape
        int row = 0;
        int col = matrix[0].length-1;
        while(row < matrix.length && col >=0) {
            if(matrix[row][col]==target) {
                return true;
            }
            if(matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }


    public boolean searchMatrixRecursive(int[][] matrix, int target) {
       return searchMatrixRecursive(matrix, target, 0, matrix[0].length-1);
    }

    private boolean searchMatrixRecursive(int[][] matrix, int target, int row, int column) {
        if(row>=matrix.length || column <0) {
            return false;
        }
        if(matrix[row][column] ==target) {
            return true;
        }
        return matrix[row][column] < target ?  searchMatrixRecursive(matrix, target, ++row, column) : searchMatrixRecursive(matrix, target, row, --column);
    }

}
