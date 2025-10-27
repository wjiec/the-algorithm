package weekly.w466.D;

import java.util.Arrays;

/**
 * Q4. Count Binary Palindromic Numbers
 *
 * https://leetcode.cn/contest/weekly-contest-466/problems/count-binary-palindromic-numbers/
 *
 * You are given a non-negative integer n.
 *
 * A non-negative integer is called binary-palindromic if its binary
 * representation (written without leading zeros) reads the same forward and backward.
 *
 * Return the number of integers k such that 0 <= k <= n and the binary representation of k is a palindrome.
 *
 * Note: The number 0 is considered binary-palindromic, and its representation is "0".
 */

public class Solution {

    public int countBinaryPalindromes(long n) {
        int b = 64 - Long.numberOfLeadingZeros(n);

        int[] bits = new int[b];
        for (int i = 0; i < b; i++) bits[i] = (n & (1L << i)) != 0 ? 1 : 0;

        // 0 也是符合条件的答案, 需要算上
        return dfs(bits, b - 1, true, new int[b], -1) + 1;
    }

    private final static int[][] memo = new int[64][64];
    static { for (var r : memo) Arrays.fill(r, -1); }

    // 当前填的是第 i 位, limited 表示当前位是否受到限制, j 表示第一个有效位是哪个
    private int dfs(int[] bits, int i, boolean limited, int[] curr, int j) {
        if (i < 0) return j == 0 ? 1 : 0;
        // 有效位从 [0, j) 一共 j 个位, 我们只需要填一半 也就是 j / 2 个位
        //  - 那当我们填到 i = j / 2 时, 就说明已经填完了, 但是需要注意不能超过 bits
        if (j >= 0 && i < (j + 1) / 2) {
            // 如果是受限的, 那么对称过来我们必须保证不超过限定的大小
            if (limited) {
                for (int l = i, r = j - i; l >= 0; l--, r++) {
                    if (curr[r] > bits[l]) return 0;
                    else if (curr[r] < bits[l]) return 1;
                }
            }
            // 否则都是满足条件的
            return 1;
        }
        if (!limited && j >= 0 && memo[i][j] != -1) return memo[i][j];

        int ans = 0;
        // 如果当前是无效的, 那么可以直接跳过这一位, 这样就没有限制了
        if (j == -1) ans += dfs(bits, i - 1, false, curr, j);
        // 否则可以考虑填任意的 0 或者 1
        for (int lower = j == -1 ? 1 : 0, upper = limited ? bits[i] : 1; lower <= upper; lower++) {
            curr[i] = lower;
            ans += dfs(bits, i - 1, limited && lower == bits[i], curr, Math.max(j, i));
        }

        if (!limited && j >= 0) memo[i][j] = ans;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countBinaryPalindromes(10) == 6;

        assert new Solution().countBinaryPalindromes(9) == 6;
        assert new Solution().countBinaryPalindromes(0) == 1;
    }

}
