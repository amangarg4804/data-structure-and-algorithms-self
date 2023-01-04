package algorithms.optuminterview;

//There are n balls placed on a 1-dimensional axis with each of them moving with the same non-zero speed. direction[i] represents the direction in which the ith ball is moving, with -1 meaning that it is moving to the left and 1 meaning it is moving to the right. The strength of the ith ball is described by strength[i]. If 2 balls collide, the one with the higher strength destroys the smaller one. If both have the same strength, both are destroyed. Return a list of the indices of the balls that remain after all the collisions have occurred, in ascending order.
//Note that the arrays direction and strength are 0-indexed.
//Example
//direction = [1, -1]
//strength = [2, 1]
//The number of balls n = 2. The balls are listed in order of occurrence from left to right, so ball 0 is somewhere to the left of ball 1. Ball 0 is moving to the right and ball 1 is moving to the left. The balls will collide at some point and the ball with the higher strength, ball 0, remains. Return [0] as the answer.
//Complete the function findRemainingBalls in the editor below.
//findRemainingBalls has the following parameter(s):
//    int direction[n]:  the directions of the particles in order of their starting relative positions
//    int strength[n]:  the strengths of the particles
//Returns
//    int[]: an integer array that contains the indices of the remaining balls in ascending order

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BallCollision {

    public static int[] asteroidCollision(int[] direction, int[] strength) {
        // using stack to store indices
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < direction.length; i++) {
            if (direction[i] > 0 || s.isEmpty())
                s.push(i);
            else {
                // in case of a negative number, keep popping indexes with lesser strength than current
                while (!s.isEmpty() && direction[s.peek()] > 0 && strength[i] > strength[s.peek()]) {
                    s.pop();
                }
                // decide whether to push i or pop
                if (s.isEmpty() || direction[s.peek()] < 0) {
                    s.push(i);
                }
                else if (strength[s.peek()] == strength[i]) {
                    s.pop();
                }
            }
        }
        int[] res = new int[s.size()];
        for (int i = res.length - 1; i >= 0; i--)
            res[i] = s.pop();
        return res;
    }

    public static List<Integer> asteroidCollision1(int[] direction, int[] strength) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < direction.length; i++) {
            if(result.isEmpty() || direction[i]==1) {
                result.add(i);
                continue;
            }
            if(direction[i] ==-1) {
                boolean toAdd = true;
                while (!result.isEmpty()) {
                    int lastIndex = result.get(result.size()-1);
                    if(direction[lastIndex] ==-1){
                        break;
                    }
                    if(strength[lastIndex] > strength[i]) {
                        toAdd =false;
                        break;
                    } else if(strength[lastIndex] <strength[i]) {
                        result.remove(lastIndex);
                    } else {
                        result.remove(lastIndex);
                        toAdd = false;
                    }
                }
                if(toAdd) {
                    result.add(i);
                }
            }
        }
        return result;
    }
}
