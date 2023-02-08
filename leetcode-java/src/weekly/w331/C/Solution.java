package weekly.w331.C;

import java.util.Arrays;

public class Solution {

    public int minCapability(int[] nums, int k) {
        int[] dp1 = new int[nums.length + 1];
        int[] dp2 = new int[nums.length + 1];

        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);

        int l = 0, r = sorted.length, ans = 0;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, dp1, dp2, k, sorted[mid])) {
                ans = sorted[mid]; r = mid;
            } else l = mid + 1;
        }
        return ans;
    }

    private boolean check(int[] nums, int[] dp1, int[] dp2, int k, int max) {
        int n = nums.length, cap = 0;
        for (int i = 1; i <= n; i++) {
            if (nums[i - 1] <= max) {
                dp1[i] = dp2[i - 1] + 1; // 偷当前的
            } else dp1[i] = 0;

            dp2[i] = Math.max(dp1[i - 1], dp2[i - 1]); // 不偷当前的
            cap = Math.max(cap, Math.max(dp1[i], dp2[i]));
        }
        return cap >= k;
    }

    public static void main(String[] args) {
        assert new Solution().minCapability(new int[]{2,3,5,9}, 2) == 5;
        assert new Solution().minCapability(new int[]{2,7,9,3,1}, 2) == 2;
    }

}
