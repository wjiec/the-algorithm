package offer.p16shuzhidezhengshucifanglcof;

/**
 * 剑指 Offer 16. 数值的整数次方
 *
 * https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
 *
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 */

public class Solution {

    // @TODO
    public double myPow(double x, int n) {
        long b = n;

        if (x == 0) return 0;
        if (b == 0 || x == 1) return 1;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }

        double ans = 1.0;
        while (b > 0) {
            if ((b & 1) == 1) ans *= x;
            x *= x;
            b >>= 1;
        }
        return ans;
    }

    public double myPowTimeout(double x, int n) {
        if (x == 0) return 0;
        if (n == 0 || x == 1) return 1;
        if (n < 0) return 1.0 / myPow(x, -n);

        double ans = x;
        for (int i = 1; i < n; i++) {
            ans *= x;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().myPow(1.00000, -2147483648) == 1;
        assert new Solution().myPow(2, 2) == 4;
        assert new Solution().myPow(2, 10) == 1024;
        assert new Solution().myPow(2.1, 3) == 9.261;
        assert new Solution().myPow(2.1, -2) == 0.25;
    }

}
