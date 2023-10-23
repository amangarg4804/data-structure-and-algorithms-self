package algorithms.leetcodedp;

import java.util.*;

//Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
//
//Note that the same word in the dictionary may be reused multiple times in the segmentation.
//
//Example 1:
//
//Input: s = "leetcode", wordDict = ["leet","code"]
//Output: true
//Explanation: Return true because "leetcode" can be segmented as "leet code".
//Example 2:
//
//Input: s = "applepenapple", wordDict = ["apple","pen"]
//Output: true
//Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//Note that you are allowed to reuse a dictionary word.
//Example 3:
//
//Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
//Output: false
//
//Constraints:
//
//1 <= s.length <= 300
//1 <= wordDict.length <= 1000
//1 <= wordDict[i].length <= 20
//s and wordDict[i] consist of only lowercase English letters.
//All the strings of wordDict are unique.
public class WordBreak {
    //Recall back in the first chapter, we said that a good way to check if a problem should be solved with DP or greedy is to first assume that it can be solved greedily, then try to think of a counterexample.
    //Let's say that we had s= "abcdef" and
    //wordDict = [ "abcde", "ef", "abc", "a", "d"]
    //A greedy algorithm (picking the longest substring available) will not be able to determine that picking "abcde" here is the wrong decision.
    // Likewise, a greedy algorithm (picking the shortest substring available) will not be able to determine that picking "a" first is the wrong decision.
    public boolean wordBreak(String s, List<String> wordDict) {
        // TLE 35 / 46 tests passed
        // After using seen array, ALL TESTS PASS
        // We can apply BFS, initially we push the index 0 into queue
        // when polling from queue, we iterate from current index + 1 to end index to match a word in wordDic (convert list to hashset to check this in amortized O(1))
        // if we find a match, push the next index into q
        Set<String> dict = new HashSet<>(wordDict); // create hashset so that contains is O(1)
        Queue<Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[s.length() + 1];
        q.offer(0);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int wordStartIndex = q.poll();
                if (wordStartIndex == s.length()) { // if we reach beyond the available indexes, it means we are able to match all words.
                    return true;
                }
                for (int endIndex = wordStartIndex; endIndex < s.length(); endIndex++) {// why not start from wordStartIndex +1? because a word can be one character long "a". So the char at wordStartIndex is itself a word
                    String word = s.substring(wordStartIndex, endIndex+1); // +1 because substring second parameter is exclusive
                    if (wordDict.contains(word)) {
                        if(seen[endIndex+1]) {
                            continue;
                        }
                        q.offer(endIndex + 1); // if we found a matching word, then push the next index to q to check for remaining substring of s.
                        seen[endIndex+1] = true;
                    }
                }
            }
        }

        return false;
        //Time complexity: O(n^3+m⋅k)
        //There are O(n) nodes. Because of seen, we never visit a node more than once. At each node, we iterate over the nodes in front of the current node, of which there are O(n)O(n)O(n). For each node end, we create a substring, which also costs O(n)O(n)O(n).
        //
        //Therefore, handling a node costs O(n^2), so the BFS could cost up to O(n^3)
        // Finally, we also spent )O(m⋅k) to create the set words.
        //
        //Space complexity: O(n+m⋅k)
        //
        //We use O(n) space for queue and seen. We use O(m⋅k) space for the set words.

    }
    //1. BFS
    //2. Top down dp
    //3. bottom up dp

    public boolean wordBreak1(String s, List<String> wordDict) {
        // top down
        // the idea is that if we are at index i, we try to find a word that ends at index i.
        // leetcode {leet, code}
        // the start index is initialized with -1, so the word ending at index i is s.substring(start +1, i+1)
        // if the word exists in wordDict,we update start to end and move do i = i+1
        //  leetcode {le, leet, code}
        // if we start from le, then there is no answer possible, but if we start from leet, then there is an answer possible.
        // What is the recurrence relation
        // we will start from the last index
        // if we are able to form a word ending at index i, then the next word has to start from i-word.length
        // l e e t c o d e
        // 0 1 2 3 4 5 6 7
        // for the word code, index 7 - 4 = 3
       // return recurse(s, wordDict, s.length() -1);
        int[] dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return recurseMemoize(s, wordDict, s.length() -1, dp);

    }
    public boolean recurseMemoize(String s, List<String> wordDict, int index, int[] dp) {
        if(index< 0) {
            // we are done with string,
            return true;
        }

        // we need to know whether the value was calculated before and if it was calculated, what is the value/
        // two values are not enough, We can use -1 to say value was not set
        // 1 - value was set AND it is possible to break string as required
        // 0- value was set but not possible to break string as required
        if(dp[index] != -1) {
            // value was set before, return the computed value
            return dp[index] ==1;
        }
        for(String word : wordDict) {
            // Condition 1, the word should match
            // how to substring here?
            //      // if we are able to form a word ending at index i, then the next word has to start from i-word.length
            //        // l e e t c o d e
            //        // 0 1 2 3 4 5 6 7
            //        // for the word code, index 7 - 4 = 3
            // For the word code, end index should be 8 (exclusive) (index +1), word length is 4, start index = 4,

            // Condition 2:  it is not enough for the substring to match any word, remaining substring should also match words
            // where does next word end? - at index 3, index -word.length

            if(index +1-word.length() < 0) {
                continue;
            }
            if(s.substring(index +1 -word.length(), index +1).equals(word) && recurseMemoize (s, wordDict, index-word.length(), dp)) { // substring gets out of bound if the word is long
                dp[index] = 1;
                return true;
            }
        }
        dp[index] =0;
        return false;
        //Complexity Analysis
        //Given n as the length of s, m as the length of wordDict, and kkk as the average length of the words in wordDict,
        //Time complexity: O(n⋅m⋅k)
        //
        //There are n states of dp(i). Because of memoization, we only calculate each state once. To calculate a state, we iterate over m words,
        // and for each word perform some substring operations which costs O(k). Therefore, calculating a state costs O(m⋅k), and we need to calculate O(n) states.
        //Space complexity: O(n)
        //The data structure we use for memoization and the recursion call stack can use up to O(n) space.
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        // bottom up.
        // if we look at top down solution, at the deepest level of recursion tree, the 0th index will be calulation first
        // our base case is when index is -1, in that case, result is true;
        boolean[]dp = new boolean[s.length()];
        // Condition1: the word starting at index i should match.
        // condition2: The previous word should also match or the previous index should be 0.
        // We can set the word end indexes to true;
        for(int i=0; i< s.length(); i++) {
            for(String word : wordDict) {
                // start index is i
                //   l e e t c o d e
                //   0 1 2 3 4 5 6 7
                //   index of t = 0+4
                // end index will be i+ word.length -1 . For substring we use i+word.length
                if(i+word.length() > s.length()) {
                    continue;
                }
                // condition1: the word starting at index i should match the word
                // condition2: the word that ended at index i-1 should be true. For the first word, we can check that i-1 is -1
                if(s.substring(i, i+word.length()).equals(word) && (i-1 ==-1 || dp[i-1])) {
                    dp[i+word.length() -1] = true;
                }
            }
        }
        return dp[s.length()-1];
    }


    public boolean recurse(String s, List<String> wordDict, int index) {
       // 37 / 46 testcases passed TLE
        if(index< 0) {
            // we are done with string,
            return true;
        }
        for(String word : wordDict) {
            // Condition 1, the word should match
            // how to substring here?
            //      // if we are able to form a word ending at index i, then the next word has to start from i-word.length
            //        // l e e t c o d e
            //        // 0 1 2 3 4 5 6 7
            //        // for the word code, index 7 - 4 = 3
            // For the word code, end index should be 8 (exclusive) (index +1), word length is 4, start index = 4,

            // Condition 2:  it is not enough for the substring to match any word, remaining substring should also match words
            // where does next word end? - at index 3, index -word.length

            if(index +1-word.length() < 0) {
                continue;
            }
            if(s.substring(index +1 -word.length(), index +1).equals(word) && recurse(s, wordDict, index-word.length())) { // substring gets out of bound if the word is long
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        String s = "leetcode";
        List<String> list = List.of("leet", "code");
        System.out.println(s.indexOf("leet"));
        System.out.println(s.indexOf("code"));
        System.out.println("ab".substring(0, 2));
    }






}
