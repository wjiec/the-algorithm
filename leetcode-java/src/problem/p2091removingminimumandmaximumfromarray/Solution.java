package problem.p2091removingminimumandmaximumfromarray;

/**
 * 2091. Removing Minimum and Maximum From Array
 *
 * https://leetcode.cn/problems/removing-minimum-and-maximum-from-array/
 *
 * You are given a 0-indexed array of distinct integers nums.
 *
 * There is an element in nums that has the lowest value and an element that has the highest value.
 * We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 *
 * A deletion is defined as either removing an element from the front of the array
 * or removing an element from the back of the array.
 *
 * Return the minimum number of deletions it would take to remove both the minimum
 * and maximum element from the array.
 */

public class Solution {

    public int minimumDeletions(int[] nums) {
        int min = 0, max = 0, n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < nums[min]) min = i;
            if (nums[i] > nums[max]) max = i;
        }

        int l = Math.min(min, max), r = Math.max(min, max);
        if (l + 1 > n - r) {
            return n - r + Math.min(l + 1, r - l);
        }
        return l + 1 + Math.min(r - l, n - r);
    }

    public static void main(String[] args) {
        assert new Solution().minimumDeletions(new int[]{-14,61,29,-18,59,13,-67,-16,55,-57,7,74}) == 6;

        assert new Solution().minimumDeletions(new int[]{2,10,7,5,4,1,8,6}) == 5;
        assert new Solution().minimumDeletions(new int[]{0,-4,19,1,8,-2,-3,5}) == 3;
        assert new Solution().minimumDeletions(new int[]{101}) == 1;
    }

}
