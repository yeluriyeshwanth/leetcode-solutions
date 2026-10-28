class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length, V = 1024;
        boolean[][] dp = new boolean[n][V];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int g = grid[i][j];
                boolean[] next = new boolean[V];
                if (i == 0 && j == 0) {
                    next[g] = true;
                } else {
                    boolean[] up = dp[j];
                    boolean[] left = j > 0 ? dp[j - 1] : null;
                    for (int x = 0; x < V; x++) {
                        if (up[x] || (left != null && left[x])) {
                            next[x ^ g] = true;
                        }
                    }
                }
                dp[j] = next;
            }
        }
        for (int x = 0; x < V; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}