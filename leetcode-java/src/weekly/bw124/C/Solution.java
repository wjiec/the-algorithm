package weekly.bw124.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 3040. Maximum Number of Operations With the Same Score II
 *
 * https://leetcode.cn/contest/biweekly-contest-124/problems/maximum-number-of-operations-with-the-same-score-ii/
 *
 * Given an array of integers called nums, you can perform any of the following
 * operation while nums contains at least 2 elements:
 *
 * Choose the first two elements of nums and delete them.
 * Choose the last two elements of nums and delete them.
 * Choose the first and the last elements of nums and delete them.
 *
 * The score of the operation is the sum of the deleted elements.
 *
 * Your task is to find the maximum number of operations that can be
 * performed, such that all operations have the same score.
 *
 * Return the maximum number of operations possible that satisfy the condition mentioned above.
 */

public class Solution {

    public int maxOperations(int[] nums) {
        int n = nums.length;
        int f2 = dfs(nums, 2, n, nums[0] + nums[1]);
        int l2 = dfs(nums, 0, n - 2, nums[n - 1] + nums[n - 2]);
        int fl = dfs(nums, 1, n - 1, nums[0] + nums[n - 1]);
        return Math.max(f2, Math.max(l2, fl)) + 1;
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int dfs(int[] nums, int l, int r, int target) {
        if (r - l < 2) return 0;

        long key = ((((long) l << 16) | r) << 32) | target;
        if (!memo.containsKey(key)) {
            int ans = 0;
            if (nums[l] + nums[l + 1] == target) {
                ans = Math.max(ans, 1 + dfs(nums, l + 2, r, target));
            }
            if (r - l > 2 && nums[r - 1] + nums[r - 2] == target) {
                ans = Math.max(ans, 1 + dfs(nums, l, r - 2, target));
            }
            if (r - l > 2 && nums[l] + nums[r - 1] == target) {
                ans = Math.max(ans, 1 + dfs(nums, l + 1, r - 1, target));
            }
            memo.put(key, ans);
        }
        return memo.get(key);
    }

    public static void main(String[] args) {
        assert new Solution().maxOperations(new int[]{3,2,1,4,1}) == 2;
    }

}
