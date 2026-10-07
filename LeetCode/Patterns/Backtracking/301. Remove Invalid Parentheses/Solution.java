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
            0,
            leftRem,
            rightRem,
            new StringBuilder(),
            result
        );

        return result;
    }

    private void backtrack(
            String s,
            int index,
            int balance,
            int leftRem,
            int rightRem,
            StringBuilder current,
            List<String> result) {

        // Entire string processed
        if (index == s.length()) {

            if (leftRem == 0 &&
                rightRem == 0 &&
                balance == 0) {

                result.add(current.toString());
            }

            return;
        }

        char ch = s.charAt(index);

        // ============================================
        // CASE 1: '('
        // ============================================

        if (ch == '(') {

            // OPTION 1: REMOVE '('
            if (leftRem > 0) {

                // Skip duplicate removal choices
                if (index == 0 ||
                    s.charAt(index - 1) != '(') {

                    backtrack(
                        s,
                        index + 1,
                        balance,
                        leftRem - 1,
                        rightRem,
                        current,
                        result
                    );
                }
            }

            // OPTION 2: KEEP '('

            current.append('(');

            backtrack(
                s,
                index + 1,
                balance + 1,
                leftRem,
                rightRem,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }

        // ============================================
        // CASE 2: ')'
        // ============================================

        else if (ch == ')') {

            // OPTION 1: REMOVE ')'
            if (rightRem > 0) {

                // Skip duplicate removal choices
                if (index == 0 ||
                    s.charAt(index - 1) != ')') {

                    backtrack(
                        s,
                        index + 1,
                        balance,
                        leftRem,
                        rightRem - 1,
                        current,
                        result
                    );
                }
            }

            // OPTION 2: KEEP ')'
            if (balance > 0) {

                current.append(')');

                backtrack(
                    s,
                    index + 1,
                    balance - 1,
                    leftRem,
                    rightRem,
                    current,
                    result
                );

                current.deleteCharAt(current.length() - 1);
            }
        }

        // ============================================
        // CASE 3: Letter
        // ============================================

        else {

            current.append(ch);

            backtrack(
                s,
                index + 1,
                balance,
                leftRem,
                rightRem,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }
    }
}