package weekly.w390.B;

/**
 * 3091. Apply Operations To Make Sum Of Array Greater Than Or Equal To K
 *
 * https://leetcode.cn/contest/weekly-contest-390/problems/apply-operations-to-make-sum-of-array-greater-than-or-equal-to-k/
 *
 * You are given a positive integer k. Initially, you have an array nums = [1].
 *
 * You can perform any of the following operations on the array any number of times (possibly zero):
 *
 * Choose any element in the array and increase its value by 1.
 * Duplicate any element in the array and add it to the end of the array.
 *
 * Return the minimum number of operations required to make the
 * sum of elements of the final array greater than or equal to k.
 */

public class Solution {

    public int minOperations(int k) {
        if (k == 1) return 0;

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, i + (k + i) / (i + 1) - 1);
        }
        return Math.max(ans, 1);
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(2) == 1;
    }

}
