package algorithms.leetcodedp;

import java.util.HashMap;
import java.util.Map;

//You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
//Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large,
// return it modulo 109 + 7.
//
//In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally
// adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.
//Example 1:
//Input: n = 3
//Output: 5
//Explanation: The five different ways are show above.
//Example 2:
//Input: n = 1
//Output: 1
//Constraints:
//1 <= n <= 1000
public class DominoTrominoTiling {

    private static final int MOD = 1000_000_007;
    public int numTilings(int n) {
        // We can determine the number of ways to fully or partially tile a board of width kkk by considering every possible way to arrive at f(k) or p(k) by placing a domino or a tromino. Let's find f(k) together and then you can pause to practice by finding p(k) on your own. All of the ways to arrive at a fully tiled board of width k are as follows:
        //
        //1. From f(k−1) we can add 1 vertical domino for each tiling in a fully covered board with a width of k−1, as shown in the second animation.
        //2. From f(k−2) we can add 2 horizontal dominos for each tiling in a fully covered board with a width of k−2, as shown in the third animation.
        //      ->Note that we don't need to add 2 vertical dominos to f(k−2), since f(k−1) will cover that case and it will cause duplicates if we count it again.
        //3. From p(k−1) we can add an L-shaped tromino for each tiling in a partially covered board with a width of k−1. We will multiply by p(k−1)by 2 because for any partially covered tiling, there will be a horizontally symmetrical tiling of it
         //Summing the ways to reach f(k) gives us the following equation:
        //f(k)=f(k−1)+f(k−2)+2∗p(k−1)
        //Now that we know where tilings on f(k) are coming from, how about p(k)? Can we apply the same logic and find that out?
        // Absolutely yes!
        //
        //Take a pen and start drawing scenarios that contribute to p(4) (this is a good technique to aid critical thinking during an interview).
        // Start by drawing p(4), remember p(4) is a board of width 4 with the first 3 columns fully covered and the last column half covered.
        // Now, try removing a domino or a tromino to find which scenarios contribute to p(4).
        // Notice that p(k) can come from the below scenarios:
        //1. Adding a tromino to a fully covered board of width k−2 (i.e. f(k−2)). Note that a tromino can be added in two ways but since we are doubling, we count only 1
        // 1 1 0 0    1 1 1 0
        // 1 1 0 0    1 1 1 1
        //2. Adding a horizontal domino to a partially covered board of width k−1 (i.e. p(k−1))
        // 1 1 1 0    1 1 1 0
        // 1 1 0 0    1 1 1 1
        // 3. If k-1 is fully filled, we can't make fill the board of length k partially
        // base cases:
        // if n=1, only one way to fully tile- vertically place domino. so f(1)=1
        // if n=2, two ways to fully tile- vertically place dominos or horizontally place dominoes. If we place tromino,
        // it will be partically filled.  so f(2)=2
        // if p=2, although there are two ways to partially cover a board with width 2, we count only 1 because in the formula we are doubling the result.
        // so p(2) =1
        return (int)full(n);

    }

    private Map<Integer, Long> fullMap = new HashMap<>();
    private Map<Integer, Long> partialMap = new HashMap<>();

    long full(int n) { // doesn't work if we store int in map
        if(n<=2) {
            return n;
        }
        if(fullMap.containsKey(n)) {
            return fullMap.get(n);
        }
        long result = full(n-1) + full(n-2) + (2 * partial(n-1));
        fullMap.put(n, result % MOD);
        return result % MOD;
    }

    private long partial(int n) {
        if(n==2) {
            return 1;
        }
        if(partialMap.containsKey(n)) {
            return partialMap.get(n);
        }
        long result = full(n-2) + partial(n-1);
        partialMap.put(n, result % MOD);
        return  result %MOD;
    }
}
