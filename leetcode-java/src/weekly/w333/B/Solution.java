package weekly.w333.B;

/**
 * 2571. Minimum Operations to Reduce an Integer to 0
 *
 * https://leetcode.cn/problems/minimum-operations-to-reduce-an-integer-to-0/
 *
 * You are given a positive integer n, you can do the following operation any number of times:
 *
 * Add or subtract a power of 2 from n.
 * Return the minimum number of operations to make n equal to 0.
 *
 * A number x is power of 2 if x == 2i where i >= 0.
 */

public class Solution {

    public int minOperations(int n) {
        int ans = 1;
        while ((n & (n - 1)) != 0) { // 不是 2 的幂次
            int lsb = n & -n;
            // 加上一个 lsb 等于增加了一个基数
            // (n >> #lsb) + 1
            if ((n & (lsb << 1)) != 0) n += lsb; // 在之后还有多个1
            else n -= lsb; // 独立的 1
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(0b1111_0000) == 4;
        assert new Solution().minOperations(27) == 3;

        assert new Solution().minOperations(39) == 3;
        assert new Solution().minOperations(54) == 3;
    }

}
