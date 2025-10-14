package weekly.bw164.D;

/**
 * Q4. Minimum Operations to Equalize Binary String
 *
 * https://leetcode.cn/contest/biweekly-contest-164/problems/minimum-operations-to-equalize-binary-string/
 *
 * You are given a binary string s, and an integer k.
 *
 * In one operation, you must choose exactly k different
 * indices and flip each '0' to '1' and each '1' to '0'.
 *
 * Return the minimum number of operations required to make all characters
 * in the string equal to '1'. If it is not possible, return -1.
 */

public class Solution {

    public int minOperations(String s, int k) {
        // 字符串 s 的长度是 n, 其中 '0' 的数量为 z, k 是每次操作需要翻转的下标个数
        //  - 我们假设需要操作的次数是 ans
        //
        // 由于我们可以任何选择所要翻转的下标, 所以是与 s 的字符顺序无关的
        //
        // 我们的目的是使得字符串 s 中的所有 '0' 都变成 '1'
        //  - 也就是所有的 '0' 都需要翻转奇数次, 而所有的 '1' 都需要翻转偶数次(保持不变)
        //
        // 由于我们一共操作 ans 次, 每次操作需要翻转 k 个下标, 所以一共需要翻转 ans * k 个下标
        //  - ans * k - z 必须是偶数: 减去 '0' 的翻转次数后就是所有 '1' 的翻转次数
        //  - ans * k >= z: 翻转的下标个数肯定要至少超过 '0' 的数量
        //      - 也就是 ans >= ceil(z / k)
        //
        // 如果 ans 是一个偶数
        //  - 由于 '0' 必须翻转奇数次, 所以 '0' 的翻转次数至多为 ans - 1
        //      - '0' 的翻转次数为: z * (ans - 1), '1' 的翻转次数为: (n - z) * ans
        //          - z * (ans - 1) + (n - z) * ans >= ans * k
        //          - 化简得: ans >= ceil(z / (n - k))
        //  - 组合得 ans >= max(ceil(z / k), ceil(z / (n - k)))
        //
        // 如果 ans 是一个奇数
        //  - 由于 '1' 必须翻转偶数次, 所以 '1' 的翻转次数至多为 ans - 1
        //      - '1' 的翻转次数为: (n - z) * (ans - 1), '0' 的翻转次数为: z * ans
        //          - z * ans + (n - z) * (ans - 1) >= ans * k
        //          - 化简得: ans >= ceil((n - z) / (n - k))
        //  - 组合得 ans >= max(ceil(z / k), ceil((n - z) / (n - k)))
        int n = s.length(), z = 0, ans = Integer.MAX_VALUE;
        for (var c : s.toCharArray()) z += (c & 1) ^ 1;

        // 特殊情况直接判断返回
        if (z == 0) return 0;
        if (n == k) return z == n ? 1 : -1;

        // 如果操作次数 ans 是个偶数
        if (z % 2 == 0) {
            int curr = Math.max(((z + k - 1) / k), ((z + (n - k) - 1) / (n - k)));
            ans = curr + (curr & 1); // 将 ans 调整为一个偶数
        }

        // 如果操作系数 ans 是个奇数
        if (z % 2 == k % 2) {
            int curr = Math.max(((z + k - 1) / k), (((n - z) + (n - k) - 1) / (n - k)));
            ans = Math.min(ans, curr | 1);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("0101", 3) == 2;
    }

}
