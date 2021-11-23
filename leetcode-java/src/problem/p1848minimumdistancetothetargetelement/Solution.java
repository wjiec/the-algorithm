package problem.p1848minimumdistancetothetargetelement;

/**
 * 1848. Minimum Distance to the Target Element
 *
 * https://leetcode-cn.com/problems/minimum-distance-to-the-target-element/
 *
 * Given an integer array nums (0-indexed) and two integers target and start,
 * find an index i such that nums[i] == target and abs(i - start) is minimized.
 *
 * Note that abs(x) is the absolute value of x.
 *
 * Return abs(i - start).
 *
 * It is guaranteed that target exists in nums.
 */

public class Solution {

    public int getMinDistance(int[] nums, int target, int start) {
        for (int i = 0; i < nums.length; i++) {
            int left = start - i, right = start + i;
            if (left >= 0 && nums[left] == target) return i;
            if (right < nums.length && nums[right] == target) return i;
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
        assert new Solution().getMinDistance(new int[]{1,2,3,4,5}, 5, 3) == 1;
        assert new Solution().getMinDistance(new int[]{1}, 1, 0) == 0;
        assert new Solution().getMinDistance(new int[]{1,1,1,1,1,1,1,1,1,1}, 1, 0) == 0;
    }

}
