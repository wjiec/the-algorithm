package weekly.w284.p2maximizethetopmostelementafterkmoves;

/**
 * 5227. Maximize the Topmost Element After K Moves
 *
 * https://leetcode-cn.com/contest/weekly-contest-284/problems/maximize-the-topmost-element-after-k-moves/
 *
 * You are given a 0-indexed integer array nums representing the contents of a pile,
 * where nums[0] is the topmost element of the pile.
 *
 * In one move, you can perform either of the following:
 *
 * If the pile is not empty, remove the topmost element of the pile.
 * If there are one or more removed elements, add any one of them back onto the pile.
 * This element becomes the new topmost element.
 * You are also given an integer k, which denotes the total number of moves to be made.
 *
 * Return the maximum value of the topmost element of the pile possible after exactly k moves.
 * In case it is not possible to obtain a non-empty pile after k moves, return -1.
 */

public class Solution {

    public int maximumTop(int[] nums, int k) {
        if (k == 0) return nums[0];
        if (k == 1) return nums.length == 1 ? -1 : nums[1];
        if (nums.length == 1) return k % 2 == 0 ? nums[0] : -1;

        if (k > nums.length) {
            int max = -1;
            for (var n : nums) max = Math.max(max, n);
            return max;
        }

        int next = k >= nums.length ? -1 : nums[k], max = -1;
        for (int i = 0; i < k - 1; i++) max = Math.max(max, nums[i]);
        return Math.max(next, max);
    }

    public static void main(String[] args) {
        assert new Solution().maximumTop(new int[]{18}, 3) == -1;

        assert new Solution().maximumTop(new int[]{
            91,98,17,79,15,55,47,86,4,5,17,79,68,60,60,31,72,85,25,77,
            8,78,40,96,76,69,95,2,42,87,48,72,45,25,40,60,21,91,32,79,
            2,87,80,97,82,94,69,43,18,19,21,36,44,81,99
        }, 2) == 91;

        assert new Solution().maximumTop(new int[]{5,2,2,4,0,6}, 1) == 2;
        assert new Solution().maximumTop(new int[]{5,2,2,4,0,6}, 2) == 5;
        assert new Solution().maximumTop(new int[]{5,2,2,4,0,6}, 3) == 5;
        assert new Solution().maximumTop(new int[]{3,2,2,4,0,6}, 4) == 3;
        assert new Solution().maximumTop(new int[]{2}, 1) == -1;
    }

}
