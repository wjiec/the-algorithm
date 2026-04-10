package weekly.bw172.A;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Q1. Minimum Number of Operations to Have Distinct Elements
 *
 * https://leetcode.cn/contest/biweekly-contest-172/problems/minimum-number-of-operations-to-have-distinct-elements/
 *
 * You are given an integer array nums.
 *
 * In one operation, you remove the first three elements of the current array.
 * If there are fewer than three elements remaining, all remaining elements are removed.
 *
 * Repeat this operation until the array is empty or contains no duplicate values.
 *
 * Return an integer denoting the number of operations required.
 */

public class Solution {

    public int minOperations(int[] nums) {
        Map<Integer, Integer> c = new HashMap<>();
        for (var v : nums) c.merge(v, 1, Integer::sum);

        int n = nums.length;
        if (c.size() == n) return 0;

        int ans = 0;
        for (int i = 0; i < n; i += 3) {
            ans++;
            c.merge(nums[i], -1, (a, b) -> a + b == 0 ? null : (a + b));
            if (i + 1 < n) c.merge(nums[i + 1], -1, (a, b) -> a + b == 0 ? null : (a + b));
            if (i + 2 < n) c.merge(nums[i + 2], -1, (a, b) -> a + b == 0 ? null : (a + b));
            if (c.size() == n - ans * 3) break;
        }
        return ans;
    }

    private static class optimization {
        public int minOperations(int[] nums) {
            Set<Integer> seen = new HashSet<>();
            for (int i = nums.length - 1; i >= 0; i--) {
                if (!seen.add(nums[i])) {
                    // 不重复的区间是 (i, n), 所以需要删除的区间就是 [0, i]
                    //  - 一共 i + 1 个元素需要删除, 操作次数就是 ceil((i + 1) / 3)
                    //  - 也就是 ((i + 1) + 2) / 3 = i / 3 + 1
                    return i / 3 + 1;
                }
            }
            // 否则就是不用删除的
            return 0;
        }
    }

    public static void main(String[] args) {
    }

}
