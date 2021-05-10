package problem.p169majorityelement;

/**
 * 169. Majority Element
 *
 * https://leetcode-cn.com/problems/majority-element/
 *
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 */

public class Solution {

    public int majorityElement(int[] nums) {
        int c = 1, r = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (c == 0) {
                c = 1;
                r = nums[i];
            } else if (r != nums[i]) {
                c--;
            } else {
                c++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        assert new Solution().majorityElement(new int[]{3,2,3}) == 3;
        assert new Solution().majorityElement(new int[]{6,6,6,7,7}) == 6;
        assert new Solution().majorityElement(new int[]{2,2,1,1,1,2,2}) == 2;
        assert new Solution().majorityElement(new int[]{0,0,0,0,1,1,1,1,1}) == 1;
    }

}
