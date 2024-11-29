package weekly.w425.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 3366. Minimum Array Sum
 *
 * https://leetcode.cn/contest/weekly-contest-425/problems/minimum-array-sum/
 *
 * You are given an integer array nums and three integers k, op1, and op2.
 *
 * You can perform the following operations on nums:
 *
 * Operation 1: Choose an index i and divide nums[i] by 2, rounding up to the nearest whole number.
 * You can perform this operation at most op1 times, and not more than once per index.
 *
 * Operation 2: Choose an index i and subtract k from nums[i], but only if nums[i] is greater than
 * or equal to k. You can perform this operation at most op2 times, and not more than once per index.
 *
 * Note: Both operations can be applied to the same index, but at most once each.
 *
 * Return the minimum possible sum of all elements in nums after performing any number of operations.
 */

public class Solution {

    public int minArraySum(int[] nums, int k, int op1, int op2) {
        return dfs(nums, 0, k, op1, op2);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int dfs(int[] nums, int i, int k, int op1, int op2) {
        if (i == nums.length) return 0;

        int key = (i << 20) | (op1 << 10) | op2;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = nums[i] + dfs(nums, i + 1, k, op1, op2);
        // 当前位置可以执行 op1
        if (op1 > 0) {
            int after = (nums[i] + 1) / 2;
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1 - 1, op2));
        }

        // 当前位置可以执行 op2
        if (op2 > 0 && nums[i] >= k) {
            int after = nums[i] - k;
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1, op2 - 1));
        }

        // 当前位置可以执行 op1 和 op2
        if (op1 > 0 && op2 > 0 && nums[i] >= k) {
            int after = (nums[i] - k + 1) / 2;
            // 检查是否可以先执行 op1 再执行 op2
            if ((nums[i] + 1) / 2 >= k) after = Math.min(after, (nums[i] + 1) / 2 - k);
            ans = Math.min(ans, after + dfs(nums, i + 1, k, op1 - 1, op2 - 1));
        }

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minArraySum(new int[]{2,8,3,19,3}, 3, 1, 1) == 23;
        assert new Solution().minArraySum(new int[]{2,4,3}, 3, 2, 1) == 3;
        assert new Solution().minArraySum(new int[]{6,8,3}, 8, 1, 3) == 6;
    }

}
