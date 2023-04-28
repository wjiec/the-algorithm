package weekly.w342.D;

/**
 * 2654. Minimum Number of Operations to Make All Array Elements Equal to 1
 *
 * https://leetcode.cn/contest/weekly-contest-342/problems/minimum-number-of-operations-to-make-all-array-elements-equal-to-1/
 *
 * You are given a 0-indexed array nums consisiting of positive integers.
 * You can do the following operation on the array any number of times:
 *
 * Select an index i such that 0 <= i < n - 1 and replace either of nums[i] or nums[i+1] with their gcd value.
 * Return the minimum number of operations to make all elements of nums equal to 1. If it is impossible, return -1.
 *
 * The gcd of two integers is the greatest common divisor of the two integers.
 */

public class Solution {

    public int minOperations(int[] nums) {
        int n = nums.length, g = 0, c1 = 0;
        for (var num : nums) {
            g = gcd(g, num);
            if (num == 1) c1++;
        }
        if (g > 1) return -1;
        if (c1 > 0) return n - c1;

        int minSize = n;
        for (int i = 0; i < n; i++) {
            g = nums[i];
            for (int j = i + 1; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minSize = Math.min(minSize, j - i);
                    break;
                }
            }
        }
        return minSize + n - 1;
    }

    private int gcd(int a, int b) { return a % b == 0 ? b : gcd(b, a % b); }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{6,10,15}) == 4;

        assert new Solution().minOperations(new int[]{2,6,3,4}) == 4;
        assert new Solution().minOperations(new int[]{2,10,6,14}) == -1;
    }

}
