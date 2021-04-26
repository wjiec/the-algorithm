package problem.p27removeelement;

import java.util.Arrays;

/**
 * 27. Remove Element
 *
 * https://leetcode-cn.com/problems/remove-element/
 *
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */

public class Solution {

    public int removeElement(int[] nums, int val) {
        int rs = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[rs++] = nums[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        var a0 = new int[]{1,1,2};
        assert new Solution().removeElement(a0, 1) == 1;
        System.out.println(Arrays.toString(a0));

        var a1 = new int[]{0,0,1,1,1,2,2,3,3,4};
        assert new Solution().removeElement(a1, 2) == 8;
        System.out.println(Arrays.toString(a1));

        var a3 = new int[]{3,2,2,3};
        assert new Solution().removeElement(a3, 3) == 2;
        System.out.println(Arrays.toString(a3));

        var a4 = new int[]{0,1,2,2,3,0,4,2};
        assert new Solution().removeElement(a4, 2) == 5;
        System.out.println(Arrays.toString(a4));
    }

}
