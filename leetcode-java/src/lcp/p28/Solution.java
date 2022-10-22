package lcp.p28;

import java.util.Arrays;

/**
 * LCP 28. 采购方案
 *
 * https://leetcode-cn.com/problems/4xy4Wx/
 *
 * 小力将 N 个零件的报价存于数组 nums。小力预算为 target，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。
 *
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 */

public class Solution {

    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);

        int ans = 0, l = 0, r = nums.length - 1, MOD = 1000000007;
        while (l < r) {
            if (nums[l] + nums[r] > target) r--;
            else {
                ans += r - l;
                l++;
            }
            ans %= MOD;
        }
        return ans % MOD;
    }

    public static void main(String[] args) {
        assert new Solution().purchasePlans(new int[]{2,5,3,5}, 6) == 1;
        assert new Solution().purchasePlans(new int[]{2,2,1,9}, 10) == 4;
    }

}
