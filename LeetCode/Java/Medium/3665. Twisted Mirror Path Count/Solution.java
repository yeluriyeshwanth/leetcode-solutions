class Solution {

    int[][][] memo;
    int modulo = 1000000007;

    private enum Direction {
        DOWN,
        RIGHT,
        INITIAL
    }

    private int recursion(int row, int col, Direction direction, int[][] grid) {

        int directionAsInt;

        if (direction == Direction.INITIAL)
            directionAsInt = 0;
        else if (direction == Direction.RIGHT)
            directionAsInt = 1;
        else
            directionAsInt = 2;

        // Out of bounds
        if (row < 0 || col < 0 ||
            row >= grid.length || col >= grid[0].length)
            return 0;

        // Destination reached
        if (row == grid.length - 1 &&
            col == grid[0].length - 1)
            return 1;

        // Already calculated
        if (memo[row][col][directionAsInt] != -1)
            return memo[row][col][directionAsInt];

        int right = 0, down = 0;

        // Mirror
        if (grid[row][col] == 1) {

            if (direction == Direction.DOWN) {
                right = recursion(row, col + 1, Direction.RIGHT, grid);
            }
            else if (direction == Direction.RIGHT) {
                down = recursion(row + 1, col, Direction.DOWN, grid);
            }

        }
        else {

            // Empty cell: explore both directions
            right = recursion(row, col + 1, Direction.RIGHT, grid);
            down = recursion(row + 1, col, Direction.DOWN, grid);

        }

        // Store and return the result
        memo[row][col][directionAsInt] = (right + down) % modulo;

        return memo[row][col][directionAsInt];
    }

    public int uniquePaths(int[][] grid) {

        memo = new int[grid.length][grid[0].length][3];

        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {

                memo[i][j][0] = -1; // INITIAL
                memo[i][j][1] = -1; // RIGHT
                memo[i][j][2] = -1; // DOWN

            }
        }

        return recursion(0, 0, Direction.INITIAL, grid);
    }
}