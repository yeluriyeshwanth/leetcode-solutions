class Solution {
    public int minMoves(int[] nums) {
        int max=0,ans=0;
        for(int val:nums){
            if(val>max)max=val;
        }
        for(int val:nums)ans+=max-val;
        return ans;
    }
}