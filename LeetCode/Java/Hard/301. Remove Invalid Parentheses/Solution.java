import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        int leftRem = 0;
        int rightRem = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {

                leftRem++;

            } else if (ch == ')') {

                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        backtrack(
            s,
            0,
            leftRem,
            rightRem,
            0,
            new StringBuilder(),
            result
        );

        return result;
    }

    private void backtrack(
            String s,
            int index,
            int leftRem,
            int rightRem,
            int balance,
            StringBuilder current,
            List<String> result) {

        // We processed the entire string
        if (index == s.length()) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                balance == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // ------------------------------------------------
        // CASE 1: Current character is '('
        // ------------------------------------------------

        if (ch == '(') {

            // Option 1: Remove '('
            if (leftRem > 0) {

                // Avoid duplicate removals
                if (index == 0 ||
                    s.charAt(index - 1) != '(') {

                    backtrack(
                        s,
                        index + 1,
                        leftRem - 1,
                        rightRem,
                        balance,
                        current,
                        result
                    );
                }
            }

            // Option 2: Keep '('
            current.append('(');

            backtrack(
                s,
                index + 1,
                leftRem,
                rightRem,
                balance + 1,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }

        // ------------------------------------------------
        // CASE 2: Current character is ')'
        // ------------------------------------------------

        else if (ch == ')') {

            // Option 1: Remove ')'
            if (rightRem > 0) {

                // Avoid duplicate removals
                if (index == 0 ||
                    s.charAt(index - 1) != ')') {

                    backtrack(
                        s,
                        index + 1,
                        leftRem,
                        rightRem - 1,
                        balance,
                        current,
                        result
                    );
                }
            }

            // Option 2: Keep ')'
            if (balance > 0) {

                current.append(')');

                backtrack(
                    s,
                    index + 1,
                    leftRem,
                    rightRem,
                    balance - 1,
                    current,
                    result
                );

                current.deleteCharAt(current.length() - 1);
            }
        }

        // ------------------------------------------------
        // CASE 3: Current character is a letter
        // ------------------------------------------------

        else {

            current.append(ch);

            backtrack(
                s,
                index + 1,
                leftRem,
                rightRem,
                balance,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }
    }
}