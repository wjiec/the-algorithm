package weekly.w486.D;

/**
 * Q4. Find Nth Smallest Integer With K One Bits
 *
 * https://leetcode.cn/contest/weekly-contest-486/problems/find-nth-smallest-integer-with-k-one-bits/
 *
 * You are given two positive integers n and k.
 *
 * Return an integer denoting the nth smallest positive integer that has
 * exactly k ones in its binary representation. It is guaranteed that
 * the answer is strictly less than 2^50.
 */

public class Solution {

    public long nthSmallest(long n, int k) {
        // 在二进制位里有 k 个 1, 要求找到第 n 小的整数
        //  - 如果在第 a 位是 1 的话, 一共有多少种可能?
        //      - 剩下 a' = a - 1 位, 需要填入 k' = k - 1 个 1
        //      - 也就是 c = (a' - k')! / k'!
        //  - 如果 n > c 则这一位必须填 1
        long ans = 0;
        // 枚举第 i 位(还剩下 i 个位置)是否可以填 1
        for (int i = 50; i >= 0 && k > 0; i--) {
            // 如果后面所有的位都填上 k 个 1 以及足够了, 那么这一位就不需要填 1 了
            long c = combination(i, k);
            if (n <= c) continue; // 只能填 0

            // 否则这一位可以填入 1, 数量要减少 c
            ans |= (1L << i); n -= c; k--;
        }

        return ans;
    }

    private long combination(int a, int k) {
        long ans = 1;
        for (int i = 0; i < k; i++) {
            ans = ans * (a - i) / (i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().nthSmallest(1231231231L, 33) == 8233179020765L;

        assert new Solution().nthSmallest(1, 2) == 3;
        assert new Solution().nthSmallest(4, 2) == 9;
        assert new Solution().nthSmallest(3, 1) == 4;
    }

}
