class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];

        for (int i = 0; i < m; i++) {
            char[] row = new char[n];

            for (int j = 0; j < n; j++) {
                row[j] = '#';
            }

            grid[i] = new String(row);
        }

        // Create one valid path:
        // Move right across the first row,
        // then down the last column.
        char[] firstRow = grid[0].toCharArray();

        for (int j = 0; j < n; j++) {
            firstRow[j] = '.';
        }

        grid[0] = new String(firstRow);

        for (int i = 1; i < m; i++) {
            char[] row = grid[i].toCharArray();
            row[n - 1] = '.';
            grid[i] = new String(row);
        }

        return grid;
    }
}