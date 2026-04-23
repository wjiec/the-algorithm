package weekly.bw174.B;

import java.util.HashSet;
import java.util.Set;

/**
 * Q2. Minimum Operations to Reach Target Array
 *
 * https://leetcode.cn/contest/biweekly-contest-174/problems/minimum-operations-to-reach-target-array/
 *
 * You are given two integer arrays nums and target, each of length n, where nums[i] is the
 * current value at index i and target[i] is the desired value at index i.
 *
 * You may perform the following operation any number of times (including zero):
 *
 * Choose an integer value x
 * Find all maximal contiguous segments where nums[i] == x (a segment is maximal if
 * it cannot be extended to the left or right while keeping all values equal to x)
 *
 * For each such segment [l, r], update simultaneously:
 * nums[l] = target[l], nums[l + 1] = target[l + 1], ..., nums[r] = target[r]
 *
 * Return the minimum number of operations required to make nums equal to target.
 */

public class Solution {

    public int minOperations(int[] nums, int[] target) {
        // 找到不同的位置上有多少种整数
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target[i]) ans.add(nums[i]);
        }
        return ans.size();
    }

    public static void main(String[] args) {
    }

}
