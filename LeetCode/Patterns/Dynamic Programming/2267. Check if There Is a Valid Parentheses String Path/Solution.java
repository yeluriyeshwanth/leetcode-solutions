class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Total number of characters in the path
        int length = m + n - 1;

        // A valid parentheses string must have even length
        if (length % 2 != 0) {
            return false;
        }

        // Start must be '(' and end must be ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        /*
         * dp[i][j][balance] = true
         *
         * There exists a path from (0,0)
         * to (i,j) having the given balance.
         */
        boolean[][][] dp = new boolean[m][n][length + 1];

        // Starting cell is '('
        // Therefore balance = 1
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Skip starting cell
                if (i == 0 && j == 0) {
                    continue;
                }

                // '(' increases balance
                // ')' decreases balance
                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0; balance <= length; balance++) {

                    int previousBalance = balance - change;

                    // IMPORTANT:
                    // Previous balance must be a valid index
                    if (previousBalance < 0 || previousBalance > length) {
                        continue;
                    }

                    // Come from the top
                    if (i > 0 && dp[i - 1][j][previousBalance]) {
                        dp[i][j][balance] = true;
                    }

                    // Come from the left
                    if (j > 0 && dp[i][j - 1][previousBalance]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        // Valid parentheses string must end with balance 0
        return dp[m - 1][n - 1][0];
    }
}