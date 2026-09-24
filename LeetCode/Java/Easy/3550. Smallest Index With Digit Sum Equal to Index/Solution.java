class Solution {
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for(int i=0;i<n;i++)
        {
            int num = nums[i];
            int sum = 0;
            while(num>0)
            {
                int last_digit = num%10;
                sum+=last_digit;
                num=num/10;
            }
            if(sum==i)
            {
                return i;
            }
        }
        return -1;
    }
}