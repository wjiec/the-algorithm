package weekly.w444.A;

/**
 * 3507. Minimum Pair Removal to Sort Array I
 *
 * https://leetcode.cn/contest/weekly-contest-444/problems/minimum-pair-removal-to-sort-array-i/
 *
 * Given an array nums, you can perform the following operation any number of times:
 *
 * Select the adjacent pair with the minimum sum in nums. If multiple such pairs exist, choose the leftmost one.
 * Replace the pair with their sum.
 * Return the minimum number of operations needed to make the array non-decreasing.
 *
 * An array is said to be non-decreasing if each element is greater than or equal to its previous element (if it exists).
 */

public class Solution {

    public int minimumPairRemoval(int[] nums) {
        int ans = 0, n = nums.length;
        while (!check(nums, n)) {
            int minimum = Integer.MAX_VALUE, idx = 0;
            for (int i = 0; i < n - 1; i++) {
                if (nums[i] + nums[i + 1] < minimum) {
                    minimum = nums[i] + nums[i + 1]; idx = i;
                }
            }

            nums[idx] = minimum;
            for (int i = idx + 1; i < n - 1; i++) {
                nums[i] = nums[i + 1];
            }
            n--; ans++;
        }

        return ans;
    }

    private boolean check(int[] nums, int len) {
        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minimumPairRemoval(new int[]{5,2,3,1}) == 2;
        assert new Solution().minimumPairRemoval(new int[]{1,2,2}) == 0;
    }

}
