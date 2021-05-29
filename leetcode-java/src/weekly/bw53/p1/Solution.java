package weekly.bw53.p1;

import java.util.Arrays;

public class Solution {

    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, mid = n / 2, ans = 0;
        for (int i = 0; i < mid; i++) {
            ans = Math.max(ans, nums[i] + nums[n - i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minPairSum(new int[]{3,5,2,3}) == 7;
        assert new Solution().minPairSum(new int[]{3,5,4,2,4,6}) == 8;
    }

}
