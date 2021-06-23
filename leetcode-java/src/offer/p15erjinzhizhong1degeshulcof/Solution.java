package offer.p15erjinzhizhong1degeshulcof;

/**
 * 剑指 Offer 15. 二进制中1的个数
 *
 * https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/
 *
 * 请实现一个函数，输入一个整数（以二进制串形式），输出该数二进制表示中 1 的个数。
 * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 */

public class Solution {

    public int hammingWeight(int n) {
        int rs = n < 0 ? 1 : 0;
        for (int i = 0; i < 31; i++) {
            if (((n >> i) & 0x1) == 1) {
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().hammingWeight(Integer.MIN_VALUE) == 2;
        assert new Solution().hammingWeight(9) == 2;
        assert new Solution().hammingWeight(1) == 1;
        assert new Solution().hammingWeight(2) == 1;
        assert new Solution().hammingWeight(3) == 2;
    }

}
