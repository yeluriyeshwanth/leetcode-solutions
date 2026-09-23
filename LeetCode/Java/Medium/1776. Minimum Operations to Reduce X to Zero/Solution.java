class Solution {

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        int target = total - x;
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return n;
        }
        int left = 0;
        int windowSum = 0;
        int maxLength = -1;
        for (int right = 0; right < n; right++) {

            windowSum += nums[right];
            while (windowSum > target && left <= right) {
                windowSum -= nums[left];
                left++;
            }
            if (windowSum == target) {
                int length = right - left + 1;
                maxLength = Math.max(maxLength, length);
            }
        }
        if (maxLength == -1) {
            return -1;
        }
        return n - maxLength;
    }
}