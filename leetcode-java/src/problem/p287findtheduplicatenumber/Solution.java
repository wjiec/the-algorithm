package problem.p287findtheduplicatenumber;

/**
 * 287. Find the Duplicate Number
 *
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 *
 * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
 *
 * There is only one repeated number in nums, return this repeated number.
 *
 * You must solve the problem without modifying the array nums and uses only constant extra space.
 */

public class Solution {

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        assert new Solution().findDuplicate(new int[]{2,2,2,2,2}) == 2;

        assert new Solution().findDuplicate(new int[]{1,3,4,2,2}) == 2;
        assert new Solution().findDuplicate(new int[]{3,1,3,4,2}) == 3;
    }

}
