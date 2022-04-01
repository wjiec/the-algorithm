package problem.p457circulararrayloop;

/**
 * 457. Circular Array Loop
 *
 * https://leetcode-cn.com/problems/circular-array-loop/
 *
 * You are playing a game involving a circular array of non-zero integers nums. Each nums[i] denotes
 * the number of indices forward/backward you must move if you are located at index i:
 *
 * If nums[i] is positive, move nums[i] steps forward, and
 * If nums[i] is negative, move nums[i] steps backward.
 * Since the array is circular, you may assume that moving forward from the last element puts you
 * on the first element, and moving backwards from the first element puts you on the last element.
 *
 * A cycle in the array consists of a sequence of indices seq of length k where:
 *
 * Following the movement rules above results in the repeating index sequence
 * seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ...
 *
 * Every nums[seq[j]] is either all positive or all negative.
 *
 * k > 1
 *
 * Return true if there is a cycle in nums, or false otherwise.
 */

public class Solution {

    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;

            int slow = i, fast = next(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[next(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != next(nums, slow)) {
                        return true;
                    }
                    break;
                }

                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }

            for (int j = i; nums[j] * nums[next(nums, j)] > 0; ) {
                int n = next(nums, j);
                nums[j] = 0;
                j = n;
            }
        }
        return false;
    }

    private int next(int[] nums, int i) {
        return (((i + nums[i]) % nums.length) + nums.length) % nums.length;
    }

    public static void main(String[] args) {
        assert new Solution().circularArrayLoop(new int[]{2,-1,1,2,2});
        assert !new Solution().circularArrayLoop(new int[]{-1,2});
        assert !new Solution().circularArrayLoop(new int[]{-2,1,-1,-2,-2});
    }

}
