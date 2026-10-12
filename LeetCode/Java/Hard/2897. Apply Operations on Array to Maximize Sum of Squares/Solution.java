import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int MOD = 1_000_000_007;
        long ans = 0;

        int[] count = new int[32];

        // Count how many numbers contain each bit
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    count[bit]++;
                }
            }
        }

        // Greedily construct the largest possible numbers
        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int bit = 30; bit >= 0; bit--) {
                if (count[bit] > 0) {
                    num |= (1L << bit);
                    count[bit]--;
                }
            }

            ans = (ans + num * num) % MOD;
        }

        return (int) ans;
    }
}