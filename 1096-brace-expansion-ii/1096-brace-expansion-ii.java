import java.util.*;

class Solution {

    private String expression;
    private int index;

    public List<String> braceExpansionII(String expression) {

        this.expression = expression;
        this.index = 0;

        Set<String> result = parseExpression();

        List<String> answer = new ArrayList<>(result);

        Collections.sort(answer);

        return answer;
    }

    // Handles union using commas
    private Set<String> parseExpression() {

        Set<String> result = parseConcat();

        while (index < expression.length()
                && expression.charAt(index) == ',') {

            index++; // Skip comma

            Set<String> next = parseConcat();

            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation of multiple factors
    private Set<String> parseConcat() {

        Set<String> result = new HashSet<>();

        result.add("");

        while (index < expression.length()
                && expression.charAt(index) != ','
                && expression.charAt(index) != '}') {

            Set<String> next = parseFactor();

            Set<String> combined = new HashSet<>();

            for (String first : result) {

                for (String second : next) {

                    combined.add(first + second);
                }
            }

            result = combined;
        }

        return result;
    }

    // Parses a single letter or a brace group
    private Set<String> parseFactor() {

        Set<String> result = new HashSet<>();

        char current = expression.charAt(index);

        if (current == '{') {

            index++; // Skip opening brace

            result = parseExpression();

            index++; // Skip closing brace

        } else {

            result.add(String.valueOf(current));

            index++; // Move to the next character
        }

        return result;
    }
}