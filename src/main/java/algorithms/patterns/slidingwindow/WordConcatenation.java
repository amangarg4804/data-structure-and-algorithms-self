package algorithms.patterns.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordConcatenation {

  public static void main(String[] args) {
    System.out.println(findWordConcatenations("catfoxcat", new String[]{"cat", "fox"}));
    System.out.println(findWordConcatenations("catcatfoxfox", new String[]{"cat", "fox"}));
  }

  public static List<Integer> findWordConcatenations(String str, String[] words) {
    List<Integer> resultIndices = new ArrayList<>();
    Map<String, Integer> wordFrequencyMap = new HashMap<>();
    for (String word : words) {
      wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
    }
    int wordLength = words[0].length();
    int noOfWords = words.length;
    int totalLengthOfWords = noOfWords * wordLength;
    for (int i = 0; i <= str.length() - totalLengthOfWords; i++) {
      Map<String, Integer> wordsSeen = new HashMap<>();
      for (int j = 0; j < noOfWords; j++) {
        int currentWordStartIndex = i + j * wordLength;
        String currentWord = str
            .substring(currentWordStartIndex, currentWordStartIndex + wordLength);
        if (!wordFrequencyMap.containsKey(currentWord)) {
          break;
        }
        wordsSeen
            .put(currentWord, wordsSeen.getOrDefault(currentWord, 0) + 1);

        if(wordsSeen.get(currentWord) > wordFrequencyMap.get(currentWord)) {
          break;
        }
        if (noOfWords == j + 1) {
          resultIndices.add(i);
        }
      }
    }
    return resultIndices;
  }
}
