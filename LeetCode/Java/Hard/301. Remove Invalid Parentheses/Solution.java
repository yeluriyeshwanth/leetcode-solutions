import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        int leftRem = 0;
        int rightRem = 0;

        // Find the minimum number of removals needed
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRem++;
            }

            else if (ch == ')') {

                if (leftRem > 0) {
                    leftRem--;
                }
                else {
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

        // Finished processing the string
        if (index == s.length()) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                balance == 0) {

                String answer = current.toString();

                // Avoid duplicate answers
                if (!result.contains(answer)) {
                    result.add(answer);
                }
            }

            return;
        }

        char ch = s.charAt(index);

        // =========================================
        // CASE 1: '('
        // =========================================

        if (ch == '(') {

            // OPTION 1: REMOVE '('
            if (leftRem > 0) {

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

            // OPTION 2: KEEP '('
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

            // Undo
            current.deleteCharAt(current.length() - 1);
        }

        // =========================================
        // CASE 2: ')'
        // =========================================

        else if (ch == ')') {

            // OPTION 1: REMOVE ')'
            if (rightRem > 0) {

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

            // OPTION 2: KEEP ')'
            //
            // We can only keep ')' if there is
            // an unmatched '(' available.
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

                // Undo
                current.deleteCharAt(current.length() - 1);
            }
        }

        // =========================================
        // CASE 3: LETTER
        // =========================================

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

            // Undo
            current.deleteCharAt(current.length() - 1);
        }
    }
}