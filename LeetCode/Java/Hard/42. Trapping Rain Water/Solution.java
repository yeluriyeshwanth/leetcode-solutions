class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int totalwater = 0;
        for(int i=1;i<n-1;i++)
        {
            int leftmax = 0;
            int rightmax = 0;
            for(int j=0;j<=i;j++)
            {
                leftmax = Math.max(leftmax,height[j]);
            }
            for(int j=i;j<n;j++)
            {
                rightmax = Math.max(rightmax,height[j]);
            }
            int waterlevel = Math.min(leftmax,rightmax);
            int water = waterlevel-height[i];
            totalwater+=water;
        }
        return totalwater;
    }
}