package algorithms.salesforceinterview;

//Alloha veggies is a new grocery store. As part of stock clearance guidlines of the store, given many piles of fresh product,
// follow the rules given below to stack the products in orderly manner.
//
//There are total of n piles of products.
//The number of product in each pile is represented by the array numProducts.
//Select any subarray from the array numProducts and pickup product from the subarray
// such that number of product you pick from the ith pile is strictly less than
// the number of products you pick from the (i+1)th pile for all the indices i of the subarray.
//Find Maximum number of products that can be picked.
//
//Example - numProducts = [7,4,5,2,6,5]
//Ans : 12
//Subarray choosen (0,2). i.e [7,4,5]
//From the 0th index pile, we choose only 3 products. So, 3 from 0th pile, 4 from 1st pile, and 5 from 2nd pile. Total 12 products.
//https://leetcode.com/problems/maximum-number-of-books-you-can-take/
public class AlohaVeggies {
    public  long maxBooks(int[] books) {
        long maxTotalBooks = 0 ;

        for(int i=books.length -1; i>=0; i-- ) {
            int maxBound = books[i];
            long totalBooksEndingHere = books[i];
            for(int j=i-1; j>=0; j--) {
                if(books[j] < maxBound) {
                    totalBooksEndingHere+= books[j];
                    maxBound = books[j];
                } else {
                    totalBooksEndingHere += Math.max(0, maxBound-1);
                    maxBound = Math.max(0, maxBound-1); //0 <= books[i] <= 10^5
                }
            }
            maxTotalBooks = Math.max(maxTotalBooks,totalBooksEndingHere );
        }

        return maxTotalBooks;
        // time complexity: nested for loops: O(n^2)
        //space complexity : O(1)
    }


}
