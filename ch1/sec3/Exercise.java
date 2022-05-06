package ch1.sec3;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise {

    /**
     * 1.3.4
     */
    @Test
    public void testParenthesesCheck() {
        String s = "[()]{}{[()()]()}";
        System.out.println(isValidParentheses(s));
    }

    private boolean isValidParentheses(String s) {
        LinkedListStack<Character> stack = new LinkedListStack<>();
        Map<Character, Character> m = new HashMap<>();
        m.put(")".charAt(0), "(".charAt(0));
        m.put("]".charAt(0), "[".charAt(0));
        m.put("}".charAt(0), "{".charAt(0));

        for (char c : s.toCharArray()) {
            if (m.containsValue(c)) {
                stack.push(c);
            } else if (m.containsKey(c) && stack.peek() == m.get(c)) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 1.3.9
     */
    @Test
    public void testFixLeftParentheses() {
        String brokenStr = "1+2)*3-4)*5-6)))";
        // stack for +,-,*,/
        LinkedListStack<Character> operators = new LinkedListStack<>();
        // stack for numbers
        LinkedListStack<String> operands = new LinkedListStack<>();

        for (int i = 0; i < brokenStr.length(); i++) {
            char c = brokenStr.charAt(i);

            if (c == '+' || c == '-' || c == '*' || c == '/') {
                operators.push(c);
            } else if (c >= '0' && c <= '9') {
                operands.push("" + c);
            } else if (c == ')') {    // reconstruct the most recent arithmetic expression
                String num2 = operands.pop();
                String num1 = operands.pop();
                char op = operators.pop();
                operands.push("(" + num1 + op + num2 + ")");
            }
        }
        System.out.println(operands.pop());
    }

    /**
     * 1.3.10
     */
    @Test
    public void testInfixToPostfix() {
        String infixStr = "A+B*C+D";

        // queue for output
        LinkedListQueue<Character> queue = new LinkedListQueue<>();
        // stack for operators
        LinkedListStack<Character> stack = new LinkedListStack<>();
        // set precedence for operators
        Map<Character, Integer> precedence = new HashMap<>();
        precedence.put("*".charAt(0), 3);
        precedence.put("/".charAt(0), 3);
        precedence.put("+".charAt(0), 2);
        precedence.put("-".charAt(0), 2);

        for (int i = 0; i < infixStr.length(); i++) {
            char c = infixStr.charAt(i);
            // if it's character then push in queue
            if (c >= 'A' && c <= 'Z') {
                queue.enqueue(c);
            }
            // if it's operator
            else if (precedence.containsKey(c)) {
                // pop all operators in stack (has higher precedence than current one) into the queue
                while (!stack.isEmpty() && (precedence.get(stack.peek()) >= precedence.get(c))) {
                    queue.enqueue(stack.pop());
                }
                // push into stack if the current one has higher precedence
                stack.push(c);
            }
        }
        // flush all in stack
        while (!stack.isEmpty()) {
            queue.enqueue(stack.pop());
        }
        System.out.println(queue.toString());
    }

    /**
     * 1.3.11
     */
    @Test
    public void testEvaluatePostfix() {
        String postfix = "123*+4+";
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        List<Character> validOperators = Arrays.asList('+', '-', '*', '/');

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (c >= '0' && c <= '9') {
                stack.push(Integer.parseInt(Character.toString(c)));
            } else if (validOperators.contains(c)) {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (c) {
                    case '+':
                        stack.push(num1 + num2);
                        break;
                    case '-':
                        stack.push(num1 - num2);
                        break;
                    case '*':
                        stack.push(num1 * num2);
                        break;
                    case '/':
                        stack.push(num1 / num2);
                        break;
                }
            }
        }
        System.out.println(stack.pop());
    }

    /**
     * See LinkedListQueue class for more exercises
     */
}
