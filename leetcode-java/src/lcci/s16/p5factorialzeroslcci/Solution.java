package lcci.s16.p5factorialzeroslcci;

/**
 * 面试题 16.05. 阶乘尾数
 *
 * https://leetcode-cn.com/problems/factorial-zeros-lcci/
 *
 * 设计一个算法，算出 n 阶乘有多少个尾随零。
 */

public class Solution {

    public int trailingZeroes(int n) {
        int ans = 0;
        for (; n >= 5; n /= 5) ans += n / 5;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().trailingZeroes(3) == 0;
        assert new Solution().trailingZeroes(5) == 1;
    }

}
