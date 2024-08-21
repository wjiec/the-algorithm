package problem.p2772applyoperationstomakeallarrayelementsequaltozero;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 2772. Apply Operations to Make All Array Elements Equal to Zero
 *
 * https://leetcode.cn/problems/apply-operations-to-make-all-array-elements-equal-to-zero/
 *
 * You are given a 0-indexed integer array nums and a positive integer k.
 *
 * You can apply the following operation on the array any number of times:
 *
 * Choose any subarray of size k from the array and decrease all its elements by 1.
 * Return true if you can make all the array elements equal to 0, or false otherwise.
 *
 * A subarray is a contiguous non-empty part of an array.
 */

public class Solution {

    public boolean checkArray(int[] nums, int k) {
        // [index, diff]
        int x = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!q.isEmpty() && q.peek()[0] <= i) {
                x -= q.remove()[1];
            }

            if ((nums[i] -= x) < 0) return false;
            q.add(new int[]{i + k, nums[i]});
            x += nums[i];
        }

        while (!q.isEmpty() && q.peek()[0] == nums.length) {
            x -= q.remove()[1];
        }

        return x == 0;
    }

    public static void main(String[] args) {
        assert new Solution().checkArray(new int[]{60,72,87,89,63,52,64,62,31,37,57,83,98,94,92,77,94,91,87,100,91,91,50,26}, 4);
        assert !new Solution().checkArray(new int[]{0,45,82,98,99}, 4);

        assert new Solution().checkArray(new int[]{2,2,3,1,1,0}, 3);
        assert !new Solution().checkArray(new int[]{1,3,1,1}, 2);
    }

}
