class Solution {
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;

        int maxWater = 0;

        while (left < right) {

            // Calculate width
            int width = right - left;

            // Find the smaller height
            int minHeight = Math.min(
                    height[left],
                    height[right]
            );

            // Calculate current area
            int area = width * minHeight;

            // Update maximum area
            maxWater = Math.max(maxWater, area);

            // Move the pointer with smaller height
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxWater;
    }
}