package algorithms.leetcodedp;

import java.util.List;

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


        return true;
    }
    //1. BFS
    //2. Top down dp
    //3. bottom up dp

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> list = List.of("leet", "code");
        System.out.println(s.indexOf("leet"));
        System.out.println(s.indexOf("code"));
    }
}
