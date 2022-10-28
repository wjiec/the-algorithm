package offer.p56shuzuzhongshuzichuxiandecishulcof;

import common.Checker;

/**
 * 剑指 Offer 56 - I. 数组中数字出现的次数
 *
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 *
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 */

public class Solution {

    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        // 数组中出现两次的数字因为异或都被排除了
        for (var v : nums) xor ^= v;

        // 由于异或的特性，只有两个二进制位不同才为1，所以我们
        // 找到异或结果中的第一个1的位置
        int lsb = xor & -xor;

        int a = 0, b = 0;
        for (var v : nums) {
            if ((v & lsb) == 0) a ^= v;
            else b ^= v;
        }
        return new int[]{a, b};
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().singleNumbers(new int[]{4,1,4,6}), new int[]{1, 6});
        assert Checker.anyOrder(new Solution().singleNumbers(new int[]{1,2,10,4,1,4,3,3}), new int[]{2, 10});
    }

}
