package weekly.bw163.B;

import java.util.Arrays;

/**
 * Q2. Number of Perfect Pairs
 *
 * https://leetcode.cn/contest/biweekly-contest-163/problems/number-of-perfect-pairs/
 *
 * You are given an integer array nums.
 *
 * A pair of indices (i, j) is called perfect if the following conditions are satisfied:
 *
 * i < j
 * Let a = nums[i], b = nums[j]. Then:
 *  min(|a - b|, |a + b|) <= min(|a|, |b|)
 *  max(|a - b|, |a + b|) >= max(|a|, |b|)
 *
 * Return the number of distinct perfect pairs.
 *
 * Note: The absolute value |x| refers to the non-negative value of x.
 */

public class Solution {

    // 满足以下条件的数对数量
    //  - i < j
    //  - min(|a - b|, |a + b|) <= min(|a|, |b|)
    //  - max(|a - b|, |a + b|) >= max(|a|, |b|)
    public long perfectPairs(int[] nums) {
        int n = nums.length;
        // 对于所有满足 i < j 的数对 a = nums[i], b = nums[j]
        //  - 如果 a < b, 则有
        //      - min(|a - b|, |a + b|) <= |a|
        //          - 如果 a b 符号相同, 则有 min(|a - b|, |a + b|) = |a - b|
        //              - b - a <= |a|
        //                  = b <= |a| + a
        //          - 如果 a b 符号不同且 a < b, 则有 min(|a - b|, |a + b|) = |a + b|
        //              - |a + b| <= |a|
        //                  = b - |a| <= |a|
        //                  = b <= 2 * |a|
        //      - max(|a - b|, |a + b|) >= |b|
        //          - 如果 a b 符号相同, 则有 max(|a - b|, |a + b|) = |a + b|
        //              - |a| + |b| >= |b|      [总是满足的]
        //          - 如果 a b 符号不同且 a < b, 则有 max(|a - b|, |a + b|) = |a - b|
        //              - |a - b| >= |b|
        //                  = |a| + |b| >= |b|  [总是满足的]
        //
        //  - 如果 a > b, 则有
        //      - min(|a - b|, |a + b|) <= |b|
        //          - 如果 a b 符号相同, 则有 min(|a - b|, |a + b|) = |a - b|
        //              - a - b <= |b|
        //                  = a <= |b| + b
        //          - 如果 a b 符号不同且 a > b, 则有 min(|a - b|, |a + b|) = |a + b|
        //              - |a + b| <= |b|
        //              - a - |b| <= |b|
        //                  = a <= 2 * |b|
        //      - max(|a - b|, |a + b|) >= |a|
        //          - 如果 a b 符号相同, 则有 max(|a - b|, |a + b|) = |a + b|
        //              - |a + b| >= |a|
        //              - |a| + |b| >= |a|      [总是满足的]
        //          - 如果 a b 符号不同且 a > b, 则有 max(|a - b|, |a + b|) = |a - b|
        //              - |a - b| >= |a|
        //                  = |a| + |b| >= |a|      [总是满足的]
        //
        // 综上, 我们有以下结论:
        //  - 如果 a b 符号相同:
        //      - 如果 a < b, 则需要满足 b <= |a| + a
        //      - 如果 a > b, 则需要满足 a <= |b| + b
        //  - 如果 a b 符号不同:
        //      - 如果 a < b, 则需要满足 b <= 2 * |a|
        //      - 如果 a > b, 则需要满足 a <= 2 * |b|
        for (int i = 0; i < n; i++) nums[i] = Math.abs(nums[i]);
        Arrays.sort(nums);

        long ans = 0;
        for (int l = 0, r = 0; r < n; r++) {
            while (nums[r] > nums[l] * 2) l++;
            ans += r - l;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
