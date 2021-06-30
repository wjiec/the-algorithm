package offer.p14jianshengziiilcof;

/**
 * 剑指 Offer 14- II. 剪绳子 II
 *
 * https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof/
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。
 * 请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 *
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */

public class Solution {

    public int cuttingRope(int n) {
        if (n <= 3) return n - 1;

        long ans = 1, MOD = 1000000007;
        for (int i = 1, l = n / 3; i < l; i++) {
            ans  = (3 * ans) % MOD;
        }

        if (n % 3 == 0) return (int) ((ans * 3) % MOD);
        if (n % 3 == 1) return (int) ((ans * 4) % MOD); // 2*2 > 1*3
        return (int) ((ans * 3 * 2) % MOD);
    }

    public static void main(String[] args) {
        assert new Solution().cuttingRope(2) == 1;
        assert new Solution().cuttingRope(10) == 36;
    }

}
