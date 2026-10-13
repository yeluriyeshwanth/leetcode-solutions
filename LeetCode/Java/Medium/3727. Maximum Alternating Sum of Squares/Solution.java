import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];

        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long score = 0;

        for (int i = 0; i < n; i++) {
            if (i < (n + 1) / 2) {
                score += squares[n - 1 - i];
            } else {
                score -= squares[n - 1 - i];
            }
        }

        return score;
    }
}