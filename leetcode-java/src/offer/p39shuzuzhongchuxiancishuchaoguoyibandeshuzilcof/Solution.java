package offer.p39shuzuzhongchuxiancishuchaoguoyibandeshuzilcof;

/**
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 *
 * https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof/
 *
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
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
        return v;
    }

    public static void main(String[] args) {
        assert new Solution().majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}) == 2;
    }

}
