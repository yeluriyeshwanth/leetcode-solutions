import java.util.*;

class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                // Save the string before '('
                stack.push(current);

                // Start a new substring
                current = new StringBuilder();

            } 
            else if (ch == ')') {

                // Reverse the substring inside parentheses
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Add reversed substring to it
                previous.append(current);

                // Continue with the combined string
                current = previous;

            } 
            else {

                // Normal character
                current.append(ch);
            }
        }

        return current.toString();
    }
}