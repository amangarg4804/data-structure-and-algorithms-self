package algorithms.leetcodebinarysearch;

//You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
//
//Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
//
//You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.
public class FirstBadVersion extends VersionControl {
    public int firstBadVersion(int n) {
        // we are not finding a target number here. We have to find the first number that returns false
        int start =1;
        int end =n;
        int ans =Integer.MAX_VALUE;
        while (start <=end) {
            int mid = start + (end -start)/2;
            if(isBadVersion(mid)) {
                ans = mid;
                end =mid-1;
            } else {
                start = mid +1;
            }
        }
        return ans;
    }

    public int firstBadVersion2(int n) {
        // we are not finding a target number here. We have to find the first number that returns false
        // without extra variable
        int start =1;
        int end =n;
        while (start <end) {
            int mid = start + (end -start)/2;
            if(isBadVersion(mid)) {
                end =mid; // if mid is bad version, it is possible that mid -1 is not bad version
            } else {
                start = mid +1;// if this mid is not bad version, we have to increase start anyway because we are trying to find bad version
            }
        }
        // loop ends, meaning start ==end
        return start;// we don't have to check whether start is bad version, there will exist a bad version in array
    }
}
class VersionControl {
    boolean isBadVersion(int version) {
        return false;
    }
}
