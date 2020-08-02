package data_structures.arrays;

//TODO:
public class LongestWordInSentence {

    public static void main(String[] args) {
        String sentence = "Behind this mask is an idea and ideas are bulletproof";

        System.out.println(longestWord(sentence));
    }

    private static String longestWord(String sentence) {
        String[] stringArr = sentence.split(" ");

        String longestWord = stringArr[0];
        for(int i = 1; i< stringArr.length; i++) {
            if(stringArr[i].length() > longestWord.length()) {
                longestWord = stringArr[i];
            }
        }
        return longestWord;
    }

    private static String longestWordWithoutSplit(String sentence) {

        String longestWord = "";
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i< sentence.length(); i++) {
            if(sentence.charAt(i) == ' ' ) {
                stringBuilder = new StringBuilder();
                continue;
            }
            stringBuilder.append(sentence.charAt(i));
            if(stringBuilder.length() > longestWord.length()) {
                longestWord = stringBuilder.toString();
            }
        }
        return longestWord;
    }

}
