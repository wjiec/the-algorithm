package offer.p44shuzixuliezhongmouyiweideshuzilcof;

/**
 * 剑指 Offer 44. 数字序列中某一位的数字
 *
 * https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/
 *
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 *
 * 请写一个函数，求任意第n位对应的数字。
 */

public class Solution {

    // 0
    // 1 2 3 4 5 6 7 8 9                        1 * 1 * 9
    // 11 12 13 14 15 16 17 18 19               2 * 10 * 9
    //                                          3 * 100 * 9
    public int findNthDigit(int n) {
        long base = 1, bits = 1, count = 9;
        while (count < n) {
            n -= count;

            bits += 1;
            base *= 10;
            count = bits * base * 9;
        }

        return Long.toString(base + (n - 1) / bits).charAt((int) ((n - 1) % bits)) - '0';
    }

    public static void main(String[] args) {
        assert new Solution().findNthDigit(3) == 3;
        assert new Solution().findNthDigit(11) == 0;
    }

}
