import java.util.*;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening bracket
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            }

            // Closing bracket
            else {

                // No opening bracket available
                if (stack.isEmpty()) {
                    return false;
                }

                char ch1 = stack.pop();

                if (ch == ')' && ch1 != '(') {
                    return false;
                }

                if (ch == '}' && ch1 != '{') {
                    return false;
                }

                if (ch == ']' && ch1 != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}