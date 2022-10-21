package algorithms.leetcoderecursion;


// We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row,
// we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
public class KthGrammar {

    public int kthGrammar(int n, int k) {
        if(n==1 && k==1) {
            return 0;
        }
        int km1th = kthGrammar(n-1, k%2==0? k/2 : ((k/2)+1));
        String result;
        if(km1th ==0) {
            result = "01";

        } else {
            result ="10";
        }

        return k%2==0? result.charAt(1)-'0': result.charAt(0) -'0';
    }
}
