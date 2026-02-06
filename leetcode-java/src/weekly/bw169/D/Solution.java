package weekly.bw169.D;

import common.Preposing;

/**
 * Q4. Count Subarrays With Majority Element II
 *
 * https://leetcode.cn/contest/biweekly-contest-169/problems/count-subarrays-with-majority-element-ii/
 *
 * You are given an integer array nums and an integer target.
 *
 * Return the number of subarrays of nums in which target is the majority element.
 *
 * The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
 */

public class Solution {

    @Preposing(weekly.bw169.B.Solution.class)
    public long countMajoritySubarrays(int[] nums, int target) {
        // 树状数组优化
        long ans = 0; int n = nums.length;
        long[] tree = new long[(n << 1) + 3]; update(tree, n + 1);
        for (int r = 0, s = n + 1; r < n; r++) {
            // sum[r + 1] = sum(nums[0..r])
            s += nums[r] == target ? 1 : -1;
            // 找到 sum[l] < sum[r + 1] 的数量
            ans += query(tree, s - 1);
            // 更新树状数组
            update(tree, s);
        }
        return ans;
    }

    private void update(long[] tree, int i) {
        for (; i < tree.length; i += i & -i) {
            tree[i]++;
        }
    }

    private long query(long[] tree, int i) {
        long ans = 0;
        for (; i > 0; i -= i & -i) ans += tree[i];
        return ans;
    }

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        assert new Solution().countMajoritySubarrays(new int[]{1,2,2,3}, 2) == 5;
        assert new Solution().countMajoritySubarrays(new int[]{1,1,1,1}, 1) == 10;
        assert new Solution().countMajoritySubarrays(new int[]{1,2,3}, 4) == 0;
    }

}
