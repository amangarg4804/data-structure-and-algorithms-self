package algorithms.leetcodestackandqueues;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class EvaluateReversePolishAnnotation {

    public int evalRPN(String[] tokens) {
        Deque<String> stack = new LinkedList<>();
        Set<String> operators = Set.of("+", "-", "*", "/");
        for (int i = 0; i < tokens.length; i++) {
            if (operators.contains(tokens[i])) {
                int secondOperand = Integer.parseInt(stack.pop());
                int firstOperand = Integer.parseInt(stack.pop());
                int answer = 0;
                switch (tokens[i]) {
                    case "+":
                        answer = firstOperand + secondOperand;
                        break;
                    case "-":
                        answer = firstOperand - secondOperand;
                        break;
                    case "*":
                        answer = firstOperand * secondOperand;
                        break;
                    case "/":
                        answer = firstOperand / secondOperand;
                        break;

                }
                stack.push(String.valueOf(answer));
            } else {
                stack.push(tokens[i]);
            }

        }
        return Integer.parseInt(stack.pop());
    }
}
