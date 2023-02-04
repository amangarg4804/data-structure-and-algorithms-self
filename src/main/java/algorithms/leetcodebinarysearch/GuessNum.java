package algorithms.leetcodebinarysearch;

//We are playing the Guess Game. The game is as follows:
//
//I pick a number from 1 to n. You have to guess which number I picked.
//
//Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.
//
//You call a pre-defined API int guess(int num), which returns three possible results:
//
//-1: Your guess is higher than the number I picked (i.e. num > pick).
//1: Your guess is lower than the number I picked (i.e. num < pick).
//0: your guess is equal to the number I picked (i.e. num == pick).
public class GuessNum extends GuessGame {
    public int guessNumber(int n) {
        int left =1;
        int right = n;
        while (left <=right) {
            int mid = left + (right-left)/2;
            int result = guess(mid);
            if(result==0) {
                return mid;
            }
            if(result==-1) {
                right = mid -1;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }
}

abstract class GuessGame {
    int guess(int num) {
        return -1;
    }
}