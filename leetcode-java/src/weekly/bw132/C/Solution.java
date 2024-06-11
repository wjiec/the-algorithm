package weekly.bw132.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 3176. Find the Maximum Length of a Good Subsequence I
 *
 * https://leetcode.cn/contest/biweekly-contest-132/problems/find-the-maximum-length-of-a-good-subsequence-i/
 *
 * You are given an integer array nums and a non-negative integer k.
 *
 * A sequence of integers seq is called good if there are at most k indices i
 * in the range [0, seq.length - 2] such that seq[i] != seq[i + 1].
 *
 * Return the maximum possible length of a good subsequence of nums.
 */

public class Solution {

    public int maximumLength(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length - ans; i++) {
            ans = Math.max(ans, 1 + dfs(nums, k, i, nums.length));
        }
        return ans;
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int dfs(int[] nums, long k, int l, int r) {
        long key = (k << 32) | ((long) l << 16) | r;
        if (!memo.containsKey(key)) {
            int ans = 0;
            for (int i = l + 1; i < r; i++) {
                int x = nums[i] != nums[l] ? 1 : 0;
                if (k == 0 && x != 0) continue;
                ans = Math.max(ans, 1 + dfs(nums, k - x, i, r));
            }
            memo.put(key, ans);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
        assert new Solution().maximumLength(new int[]{2}, 0) == 1;
        assert new Solution().maximumLength(new int[]{1,17}, 0) == 1;
        assert new Solution().maximumLength(new int[]{2,2}, 0) == 2;

        assert new Solution().maximumLength(new int[]{1,2,1,1,3}, 2) == 4;
        assert new Solution().maximumLength(new int[]{1,2,3,4,5,1}, 0) == 2;
    }

}
