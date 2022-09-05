package problem.p1760minimumlimitofballsinabag;

import java.util.Arrays;

/**
 * 1760. Minimum Limit of Balls in a Bag
 *
 * https://leetcode.cn/problems/minimum-limit-of-balls-in-a-bag/
 *
 * You are given an integer array nums where the ith bag contains nums[i] balls.
 * You are also given an integer maxOperations.
 *
 * You can perform the following operation at most maxOperations times:
 *
 * Take any bag of balls and divide it into two new bags with a positive number of balls.
 * For example, a bag of 5 balls can become two new bags of 1 and 4 balls, or two new bags of 2 and 3 balls.
 * Your penalty is the maximum number of balls in a bag. You want to minimize your penalty after the operations.
 *
 * Return the minimum possible penalty after performing the operations.
 */

public class Solution {

    public int minimumSize(int[] nums, int maxOperations) {
        Arrays.sort(nums);
        int l = 1, r = nums[nums.length - 1];
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (countOperations(nums, mid) <= maxOperations) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int countOperations(int[] nums, int max) {
        int count = 0, n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] > max) {
                count += (nums[i] - 1) / max;
            } else break;
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().minimumSize(new int[]{9}, 2) == 3;
        assert new Solution().minimumSize(new int[]{2,4,8,2}, 4) == 2;
        assert new Solution().minimumSize(new int[]{7,17}, 2) == 7;
    }

}
