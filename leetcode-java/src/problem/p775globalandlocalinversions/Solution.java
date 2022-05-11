package problem.p775globalandlocalinversions;

/**
 * 775. Global and Local Inversions
 *
 * https://leetcode.cn/problems/global-and-local-inversions/
 *
 * You are given an integer array nums of length n which represents
 * a permutation of all the integers in the range [0, n - 1].
 *
 * The number of global inversions is the number of the different pairs (i, j) where:
 *
 * 0 <= i < j < n
 * nums[i] > nums[j]
 * The number of local inversions is the number of indices i where:
 *
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 * Return true if the number of global inversions is equal to the number of local inversions.
 */

public class Solution {

    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length, x = nums.length;
        for (int i = n - 1; i >= 2; i--) {
            if (nums[i] < x) x = nums[i];
            if (nums[i - 2] > x) return false;
        }
        return true;
    }

    private static class Liner {
        public boolean isIdealPermutation(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (Math.abs(nums[i] - i) > 1) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        assert new Solution().isIdealPermutation(new int[]{1,0,2});
        assert !new Solution().isIdealPermutation(new int[]{1,2,0});

        assert new Liner().isIdealPermutation(new int[]{1,0,2});
        assert !new Liner().isIdealPermutation(new int[]{1,2,0});
    }

}
