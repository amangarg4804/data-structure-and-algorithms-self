package algorithms.interviews.salesforceinterview;


import java.util.Arrays;

// Salesforce first onsite round
//Given a string S and an array roll where roll[i] represents rolling first roll[i] characters in the string, the task is to apply every roll[i] on the string and output the final string. Rolling means increasing the ASCII value of character, like rolling z would result in a, rolling b would result in c, etc.
//
//
//
//Example 1:
//
//Input: s = "bca"
//roll[] = {1, 2, 3}
//Output: eeb
//Explanation: arr[0] = 1 means roll
//first character of string -> cca
//arr[1] = 2 means roll
//first two characters of string -> dda
//arr[2] = 3 means roll
//first three characters of string -> eeb
//So final ans is "eeb".
public class RollTheString {
    public static String rollString(String s, int[] roll) {
        StringBuilder result = new StringBuilder(s);
        for( int i=0; i< roll.length ; i++) {
            for(int j = 0; j< roll[i]; j++) {
                char rolledChar = (char) (result.charAt(j) + 1);
                if(rolledChar > 'z') {
                    rolledChar = 'a';
                }
                    result.setCharAt(j, rolledChar);// During interview, I was creating a new String every time in loop. setCharAt is a better approach
            }
        }
        return result.toString();
        //Time: O(M*N): M is length of roll array and N is length of string, in the worst case, every value in roll array is equal to length of String, i.e., all charaters have to be rolled
    }

    public static String rollString2(String s, long[] roll) {
        // one observation is that if we sort the roll array, and perform roll[0] operation,let's say roll[0] =X,
        // then the total number of operations on characters from 0 to X index are 1 * length of roll array;
        // And after these operations are performed on characters from 0 to X, there are no more operations to be performed on characters 0 to X.
        // For roll[1], the total number of operations on characters with indices alreadyRolledIndex to X index are 1 * length of remaining roll array;
        Arrays.sort(roll);
        int alreadyRolledIndex=0;
        StringBuilder result = new StringBuilder(s);
        for(int i = 0; i< roll.length; i++) {
            for(; alreadyRolledIndex < roll[i]; alreadyRolledIndex++) {
                long rolls = roll.length-i;
                char rolledChar = (char)('a' + (s.charAt(alreadyRolledIndex)- 'a' + rolls) % 26);
                result.setCharAt(alreadyRolledIndex, rolledChar);
            }
        }
        // time complexity:
        // if roll array contains same value repeatedly {5, ,5, 5,5,5 ,5 }: in this case,
        // the inner for loop will run only once ,ie.for the first 5 and alreadyRolledIndex will reach 5.
        // time complexity for inner for loop running only once from 0 to 5 is O(5)-> O(maximum value in roll array))->
        // in worst case, maximum value will be equal to lenghth of string- >O(M)
        // For sorting the roll, array=> NLogN
        // for outer for loop : N
        //Total : O(NLogN ) + O(N) + O(M)
        //if roll array contains different values {1,2,3,4,5 }: in this case, the inner loop will run once for every value of outer loop,
        // inner loop complexity is again O(M)
        // Total : O(NLogN ) + O(N) + O(M)
        return result.toString();
    }


    public static void main(String[] args) {
        char ch = 'z'; // Initialize character 'z'
        int roll = 5; // Number of rolls
        // Calculate the rolled character
        ch = (char) ('a' + (ch - 'a' + roll) % 26);
         char ch1='z';
         ch1 = (char) (97 + (ch1 - 'a' + roll) % 26);
        char ch2='z';
        ch2 = (char) ((ch2 + roll) % 26);
        System.out.println(ch); // This will also print 'e'
        System.out.println(ch1);
        System.out.println(ch2);

    }
}
