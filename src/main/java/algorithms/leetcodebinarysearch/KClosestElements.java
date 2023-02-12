package algorithms.leetcodebinarysearch;

import java.util.*;
import java.util.stream.Collectors;

//An integer a is closer to x than an integer b if:
//
//|a - x| < |b - x|, or
//|a - x| == |b - x| and a < b

//Input: arr = [1,2,3,4,5], k = 4, x = -1
//Output: [1,2,3,4]
//https://leetcode.com/explore/learn/card/binary-search/135/template-iii/945/discuss/2636647/Java-oror-Explained-in-Detailoror-Binary-Search-oror-Two-Pointers-oror-Priority-Queue
public class KClosestElements {

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            int bDiff = Math.abs(b-x);
            int aDiff = Math.abs(a-x);
            if(aDiff == bDiff) {
                return b-a;//
            }
            return bDiff -aDiff;
        });  // we require max heap. The integer with max difference should be on top of heap, so we can compare it with next integer.
        // We should remove top of heap if :
        // 1. difference of top of heap and x is more than difference between current integer and x
        // 2. If difference is same but integer itself is smaller than top of heap

        for(int i=0; i< arr.length; i++) {
            pq.offer(arr[i]);
            if(pq.size() > k) {
                pq.poll();
            }
        }// loop nLogk
        List<Integer> result = new ArrayList<>(pq);
        Collections.sort(result); //kLogk
        return result;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        // optimization on above solution - instead of iterating from 0 to last index. We can find the closest index in array and iterate from closestIndex-k index to closestIndex + k
        // This will reduce loop complexity from nlogk to 2klogk
        // So first we find the closest index using binary search
        int closestIndex = binarySearch(arr, x);
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            int bDiff = Math.abs(b-x);
            int aDiff = Math.abs(a-x);
            if(aDiff == bDiff) {
                return b-a;//
            }
            return bDiff -aDiff;
        });  // we require max heap. The integer with max difference should be on top of heap, so we can compare it with next integer.
        // We should remove top of heap if :
        // 1. difference of top of heap and x is more than difference between current integer and x
        // 2. If difference is same but integer itself is smaller than top of heap
        int start = Math.max(0, closestIndex-k);
        int end = Math.min(closestIndex+k,arr.length-1);
        for(int i=start; i<=end; i++) {
            pq.offer(arr[i]);
            if(pq.size() > k) {
                pq.poll();
            }
        }// loop 2kLogk
        List<Integer> result = new ArrayList<>(pq);
        Collections.sort(result); //kLogk
        return result;
    }

    private int binarySearch(int[] arr, int x) {
        if(x < arr[0]) {
            return 0;
        }
        if(x > arr[arr.length-1]) {
            return arr.length-1;
        }
        int start = 0;
        int end = arr.length-1;
        while (start < end) {
            int mid = start + (end-start)/2;

            if(arr[mid] ==x) {
                return mid;
            }
            if(arr[mid] < x) {
               start = mid+1;
            } else {
                end = mid -1;
            }
        }

        if(Math.abs(arr[start]-x) <= Math.abs(arr[end]-x)) {
            return start;
        }
        return end;
    }

    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        // linear solution. Use two pointers. Increment start or decrement end based on difference
        // Without priority queue
        int start = 0;
        int end = arr.length -1;
        while (end -start >= k){ // for array of size 3 with indices 0,1,2.k =3-> start =0, end =2. Loop will not run
            if(Math.abs(arr[end]-x) < Math.abs(arr[start]-x)) {
                start = start +1;
            } else { //note that we can't change order of if else, because in case difference is same, we should decrement end and not increment start
                end = end -1;
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=start ; i<=end; i++) {
            result.add(arr[i]);
        }
        return result;
    }

    public List<Integer> findClosestElements4(int[] arr, int k, int x) {
        // binary search with sliding window.
        // Without priority queue
        int start = 0;
        int end = arr.length -k;  // array is {0,1,2}, k =3  so start =0, end = 3-3 =0. loop won't run
        // We try to find correct value for start
        // start can not be more than arr.length - k because we require a window of k elements
        while (start < end){ // start will reach end and loop will terminate
            int mid = start + (end-start)/2;
            // With midpoint on the left, we use x - arr[midpoint], while arr[midpoint + k] - x because it is on the right.
            // This is important!
            // Rather than using Math.abs(), we need the direction to keep the x within the sliding window.
            // If the window is too far left, we shift the window to the right
            if(x-arr[mid] > arr[mid+k] -x) { // consider an example k =2 , x =0, arr[mid] = -5, arr[mid +k] =5, we set end = mid in this case
                start = start +1;
            } else {
                end = mid; //If the window is too far right, we shift the window to the left
            }
        }
        List<Integer> result = new ArrayList<>();
        for(int i=start ; i<start+k; i++) { // start = 0, k =3, i should be less than 0+3
            result.add(arr[i]);
        }
        return result;
    }
}
