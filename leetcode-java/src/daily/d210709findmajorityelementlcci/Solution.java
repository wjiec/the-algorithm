package daily.d210709findmajorityelementlcci;

/**
 * 面试题 17.10. 主要元素
 *
 * https://leetcode-cn.com/problems/find-majority-element-lcci/
 *
 * 数组中占比超过一半的元素称之为主要元素。给你一个 整数 数组，找出其中的主要元素。若没有，返回 -1 。
 *
 * 请设计时间复杂度为 O(N) 、空间复杂度为 O(1) 的解决方案。
 */

public class Solution {

    public int majorityElement(int[] nums) {
        int v = 0, c = 0;
        for (var n : nums) {
            if (n == v) c++;
            else if (--c <= 0) {
                c = 1; v = n;
            }
        }

        c = 0;
        for (var n : nums) {
            if (n == v) c++;
        }

        return c * 2 > nums.length ? v : -1;
    }

    public static void main(String[] args) {
        assert new Solution().majorityElement(new int[]{1,2,5,9,5,9,5,5,5}) == 5;
        assert new Solution().majorityElement(new int[]{3,2}) == -1;
        assert new Solution().majorityElement(new int[]{2,2,1,1,1,2,2}) == 2;
    }

}
