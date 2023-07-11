package weekly.w353.B;

import java.util.Arrays;

public class Solution {

    // -target <= nums[j] - nums[i] <= target
    // -target + nums[i] <= nums[j] <= target + nums[i]
    public int maximumJumps(int[] nums, int target) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && dp[i] == 0) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (-target <= nums[j] - nums[i] && nums[j] - nums[i] <= target) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
        }
        return dp[nums.length - 1] == 0 ? -1 : dp[nums.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().maximumJumps(new int[]{758043978,79060681,785252849,287889790,-983845055,224430896,-477101480}, 1769097904) == 6;
    }

}
