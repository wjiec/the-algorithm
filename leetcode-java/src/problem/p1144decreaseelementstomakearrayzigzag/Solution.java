package problem.p1144decreaseelementstomakearrayzigzag;

/**
 * 1144. Decrease Elements To Make Array Zigzag
 *
 * https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/
 *
 * Given an array nums of integers, a move consists of choosing any element and decreasing it by 1.
 *
 * An array A is a zigzag array if either:
 *
 * Every even-indexed element is greater than adjacent elements, ie. A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * OR, every odd-indexed element is greater than adjacent elements, ie. A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * Return the minimum number of moves to transform the given array nums into a zigzag array.
 */

public class Solution {

    public int movesToMakeZigzag(int[] nums) {
        int mx0 = 0, mx1 = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                int l = i > 0 && nums[i] >= nums[i - 1] ? (nums[i] - nums[i - 1] + 1) : 0;
                int r = i + 1 < n && nums[i] >= nums[i + 1] ? (nums[i] - nums[i + 1] + 1) : 0;
                mx0 += Math.max(l, r);
            } else {
                int l = nums[i] >= nums[i - 1] ? (nums[i] - nums[i - 1] + 1) : 0;
                int r = i + 1 < n && nums[i] >= nums[i + 1] ? (nums[i] - nums[i + 1] + 1) : 0;
                mx1 += Math.max(l, r);
            }
        }
        return Math.min(mx0, mx1);
    }

    public static void main(String[] args) {
        assert new Solution().movesToMakeZigzag(new int[]{7,4,8,9,7,7,5}) == 6;
        assert new Solution().movesToMakeZigzag(new int[]{3,10,7,9,9,3,6,9,4}) == 11;
        assert new Solution().movesToMakeZigzag(new int[]{2,7,10,9,8,9}) == 4;

        assert new Solution().movesToMakeZigzag(new int[]{1,2,3}) == 2;
        assert new Solution().movesToMakeZigzag(new int[]{9,6,1,6,2}) == 4;
    }

}
