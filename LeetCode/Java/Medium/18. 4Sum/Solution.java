class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;
        for(int i=0;i<n-3;i++)
        {
            for(int j=i+1;j<n-2;j++)
            {
                for(int m =j+1;m<n-1;m++)
                {
                    for(int k=m+1;m<n;m++)
                    {
                        long sum = (long)nums[i]+nums[j]+nums[m]+nums[k];
                        if(sum==target)
                        {
                            List<Integer> ans = Arrays.asList(
                                nums[i],nums[j],nums[m],nums[k]
                            );
                            Collections.sort(ans);
                            result.add(ans);

                        }
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }
}