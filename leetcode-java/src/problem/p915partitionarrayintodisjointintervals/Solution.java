package problem.p915partitionarrayintodisjointintervals;

/**
 * 915. Partition Array into Disjoint Intervals
 *
 * https://leetcode.cn/problems/partition-array-into-disjoint-intervals/
 *
 * Given an integer array nums, partition it into two (contiguous) subarrays left and right so that:
 *
 * Every element in left is less than or equal to every element in right.
 * left and right are non-empty.
 * left has the smallest possible size.
 * Return the length of left after such a partitioning.
 *
 * Test cases are generated such that partitioning exists.
 */

public class Solution {

    public int partitionDisjoint(int[] nums) {
        int[] maxLeft = new int[nums.length];
        for (int max = nums[0], i = 0; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
            maxLeft[i] = max;
        }

        int[] minRight = new int[nums.length];
        for (int min = nums[nums.length - 1], i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < min) min = nums[i];
            minRight[i] = min;
        }

        for (int i = 1; i < nums.length; i++) {
            if (maxLeft[i - 1] <= minRight[i]) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().partitionDisjoint(new int[]{5,0,3,8,6}) == 3;
        assert new Solution().partitionDisjoint(new int[]{1,1,1,0,6,12}) == 4;
    }

}
