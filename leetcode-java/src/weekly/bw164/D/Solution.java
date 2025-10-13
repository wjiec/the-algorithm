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
        int n = s.length(), zero = 0;
        for (var c : s.toCharArray()) zero += (c & 1) ^ 1;

        // 特殊情况直接判断返回
        if (k == 1) return zero;
        if (k == n) return zero == k ? 1 : -1;
        if (zero % k == 0) return zero / k;

        // 一次选择 k 个不同的下标进行翻转
        //  - 如果有 2 个 0, 和 k - 1 个任意的字符集 s, 我们可以通过 2 次操作将其全部翻转为 1
        //      - 选择 k - 1 个字符集 s, 和其中一个 0, 将其翻转为 1 和 s'
        //      - 选择刚刚翻转的字符集 s', 和另外一个 0, 将其翻转为 1 和 s
        //  - 如果只有 1 个 0, 则需要 k 个 0 一起才能将其翻转为 1

        // 如果 0 的个数为 c, 则有
        //  - 如果 c 为偶数, 则可以通过至多 c 次操作将其翻转为全为 1 的字符串
        //      - 如果 k 为偶数, 则可以通过 (c / k) + (c % k) 次操作完成
        //      - 如果 k 为奇数, 则需要满足 c % (2 * k) == 0, 否则无法完成
        if (zero % 2 == 0) {
            if (k % 2 == 0) return (zero / k) + (zero % k);

            // k 为奇数的情况下, 需要使得 k' = 2 * k 能实现翻转
            return zero % (2 * k) == 0 ? (zero / (2 * k)) : -1;
        }

        // c 为奇数的情况下, 则需要 k 也要为奇数且 c > k, 否则无法翻转
        if (k % 2 == 1 && zero > k) {
            // 删除奇数个 k 集合, 剩下偶数个 c' 就可以用 c' 次完成翻转
            int g = zero / k; if (g % 2 == 0) g--;
            return g + (zero - g * k);
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("0101", 3) == 2;
    }

}
