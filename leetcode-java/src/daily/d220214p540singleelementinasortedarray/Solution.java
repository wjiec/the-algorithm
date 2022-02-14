package daily.d220214p540singleelementinasortedarray;

/**
 * 540. Single Element in a Sorted Array
 *
 * https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
 *
 * You are given a sorted array consisting of only integers where every element appears exactly twice,
 * except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 */

public class Solution {

    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] == nums[mid ^ 1]) l = mid + 1;
            else r = mid;
        }
        return nums[l];
    }

    public static void main(String[] args) {
        assert new Solution().singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}) == 2;
        assert new Solution().singleNonDuplicate(new int[]{3,3,7,7,10,11,11}) == 10;
        assert new Solution().singleNonDuplicate(new int[]{1}) == 1;
    }

}
