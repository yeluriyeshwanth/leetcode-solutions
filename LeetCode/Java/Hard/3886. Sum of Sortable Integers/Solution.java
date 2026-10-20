
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        // Prefix maximum
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // Suffix minimum
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }

        // Count adjacent descents
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1];
            if (nums[i - 1] > nums[i]) {
                prefDrop[i]++;
            }
        }

        int answer = 0;

        // Check all divisors of n
        for (int d = 1; d <= n / d; d++) {
            if (n % d != 0) {
                continue;
            }

            if (isSortable(nums, d, prefMax, suffMin, prefDrop)) {
                answer += d;
            }

            int other = n / d;

            if (other != d &&
                isSortable(nums, other, prefMax, suffMin, prefDrop)) {
                answer += other;
            }
        }

        return answer;
    }

    private boolean isSortable(
        int[] nums, int k,
        int[] prefMax, int[] suffMin, int[] prefDrop
    ) {
        int n = nums.length;

        for (int start = 0; start < n; start += k) {
            int end = start + k - 1;

            // Count descents inside the block
            int drops = prefDrop[end] - prefDrop[start];

            // Include the circular edge: end -> start
            if (nums[end] > nums[start]) {
                drops++;
            }

            // Block must be sortable by rotation,
            // and blocks must combine in sorted order.
            if (drops > 1 || prefMax[start] > suffMin[start]) {
                return false;
            }
        }

        return true;
    }
}
