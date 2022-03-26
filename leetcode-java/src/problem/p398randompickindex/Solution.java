package problem.p398randompickindex;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 398. Random Pick Index
 *
 * https://leetcode-cn.com/problems/random-pick-index/
 *
 * Given an integer array nums with possible duplicates, randomly output the index of a given target number.
 * You can assume that the given target number must exist in the array.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the array nums.
 *
 * int pick(int target) Picks a random index i from nums where nums[i] == target.
 * If there are multiple valid i's, then each index should have an equal probability of returning.
 */

public class Solution {

    private final int[] nums;

    public Solution(int[] nums) { this.nums = nums; }

    public int pick(int target) {
        int ans = 0, count = 0;
        for (int i = 0, n = nums.length; i < n; i++) {
            if (nums[i] == target) {
                count++;
                if (ThreadLocalRandom.current().nextInt(0, count) == 0) {
                    ans = i;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution(new int[]{1, 2, 3, 3, 3});
        System.out.println(solution.pick(3));;
        System.out.println(solution.pick(1));;
        System.out.println(solution.pick(3));;
    }

}
