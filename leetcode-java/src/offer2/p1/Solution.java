package offer2.p1;

/**
 * 剑指 Offer II 001. 整数除法
 *
 * https://leetcode-cn.com/problems/xoh6Oh/
 *
 * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
 *
 * 注意：
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
 */

public class Solution {

    public int divide(int a, int b) {
        int flag = 0;
        if (a > 0) {
            a = -a;
            flag += 1;
        }

        if (b > 0) {
            b = -b;
            flag += 1;
        }
        int ret = calc(a, b);
        if (flag != 1 && ret == Integer.MIN_VALUE) {
            ret++;
        }
        return flag == 1 ? ret : -ret;
    }

    private int calc(int a, int b) {
        int ret = 0;
        while (a <= b) {
            int cnt = 1;
            int val = b;
            while (val >= Integer.MIN_VALUE >> 1  && a <= val << 1) {
                cnt += cnt;
                val += val;
            }
            ret -= cnt;
            a -= val;
        }
        return ret;
    }

    public static void main(String[] args) {
        assert new Solution().divide(15, 2) == 7;
        assert new Solution().divide(7, -3) == -2;
        assert new Solution().divide(0, 1) == 0;
        assert new Solution().divide(1, 1) == 1;
    }

}
