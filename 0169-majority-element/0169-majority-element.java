class Solution {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int value = 0;
        for(int num:nums)
        {
            if(value==0)
            {
                candidate=num;
            }
            if(num==candidate)
            {
                value++;
            }
            else{
                value--;
            }
        }
        return candidate;
    }
}