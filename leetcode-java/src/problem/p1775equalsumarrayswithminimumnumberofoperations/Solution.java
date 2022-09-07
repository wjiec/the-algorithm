package problem.p1775equalsumarrayswithminimumnumberofoperations;

/**
 * 1775. Equal Sum Arrays With Minimum Number of Operations
 *
 * https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/
 *
 * You are given two arrays of integers nums1 and nums2, possibly of different lengths.
 * The values in the arrays are between 1 and 6, inclusive.
 *
 * In one operation, you can change any integer's value in any of the arrays to any value
 * between 1 and 6, inclusive.
 *
 * Return the minimum number of operations required to make the sum of values in nums1
 * equal to the sum of values in nums2. Return -1 if it is not possible to make the
 * sum of the two arrays equal.
 */

public class Solution {

    public int minOperations(int[] nums1, int[] nums2) {
        int[] s1 = new int[7], s2 = new int[7];
        for (var n : nums1) { s1[0] += n; s1[n]++; }
        for (var n : nums2) { s2[0] += n; s2[n]++; }
        if (s1[0] == s2[0]) return 0;

        // 约定保证 s1[0] > s2[0]
        if (s2[0] > s1[0]) { int[] t = s1; s1 = s2; s2 = t; }

        int diff = s1[0] - s2[0], ans = 0;
        for (int i = 1; i < 6 && diff > 0; i++) {
            // s1 的从 {6, 5, 4, 3, 2} 变成 1
            int di = 6 - i; // 每次变化的贡献
            int c1 = Math.min(s1[di + 1], (diff + di - 1) / di);
            diff -= c1 * di; ans += c1;
            if (diff <= 0) break;

            // s2 的从 {1, 2, 3, 4, 5} 变成 6
            int c2 = Math.min(s2[i], (diff + di - 1) / di);
            diff -= c2 * di; ans += c2;
        }

        return diff > 0 ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{5,6,4,3,1,2}, new int[]{6,3,3,1,4,5,3,4,1,3,4}) == 4;

        assert new Solution().minOperations(new int[]{1,2,3,4,5,6}, new int[]{1,1,2,2,2,2}) == 3;
        assert new Solution().minOperations(new int[]{1,1,1,1,1,1,1}, new int[]{6}) == -1;
        assert new Solution().minOperations(new int[]{6,6}, new int[]{1}) == 3;
    }

}
