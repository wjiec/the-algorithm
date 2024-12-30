package weekly.w429.C;

import java.util.PriorityQueue;

/**
 * 3398. Smallest Substring With Identical Characters I
 *
 * https://leetcode.cn/contest/weekly-contest-429/problems/smallest-substring-with-identical-characters-i/
 *
 * You are given a binary string s of length n and an integer numOps.
 *
 * You are allowed to perform the following operation on s at most numOps times:
 *
 * Select any index i (where 0 <= i < n) and flip s[i]. If s[i] == '1', change s[i] to '0' and vice versa.
 * You need to minimize the length of the longest substring of s such that all the characters in the substring are identical.
 *
 * Return the minimum length after the operations.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int minLength(String s, int numOps) {
        char prev = ' '; int n = s.length();
        char[] chars = s.toCharArray();
        // [bit, l, r] where [l, r)
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[2] - b[1]) - (a[2] - a[1]));
        for (int i = 0, l = -1; i <= n; i++) {
            if (i == n || chars[i] != prev) {
                if (l >= 0) pq.add(new int[]{prev - '0', l, i});
                if (i < n) prev = chars[i];
                l = i;
            }
        }

        // 让连续的 0 或者连续的 1 的长度最小, 最理想情况是 0 1 0 1 ... 这样相互间隔
        for (; !pq.isEmpty() && numOps > 0; numOps--) {
            // 如果这一段的长度只有 1 的话, 就没必要分了
            //  - 101 或者 010 的情况, 我们最好就是不进行翻转
            // 如果这一段的长度为 2 的话, 只有在边缘的情况才有分的必要(我们在后续进行处理)
            //  - 0010 或者 1101 可以从第一位分开, 变成 1010, 0101
            int len = pq.peek()[2] - pq.peek()[1];
            if (len <= 2) break;

            // 使用一个不同的数字将这一段分割开, 从中间分割, 分割的长度分别为 l1 (较小)和 l2 (较大)
            var curr = pq.remove();
            int l1 = (len - 1) / 2, l2 = (len - 1) - l1;
            // 如果左边可以顶到开头, 那么就倾向于左边保留长的, 方便后续再对左边划分
            if (curr[1] == 0) {
                pq.add(new int[]{curr[0], curr[1], curr[1] + l2}); // 左边
                pq.add(new int[]{curr[0] ^ 1, curr[1] + l2, curr[1] + l2 + 1}); // 中间
                pq.add(new int[]{curr[0], curr[1] + l2 + 1, curr[2]}); // 右边
                chars[curr[1] + l2] = (char) ('0' + (curr[0] ^ 1));
            } else { // 否则右边的处理方式同样
                pq.add(new int[]{curr[0], curr[1], curr[1] + l1}); // 左边
                pq.add(new int[]{curr[0] ^ 1, curr[1] + l1, curr[1] + l1 + 1}); // 中间
                pq.add(new int[]{curr[0], curr[1] + l1 + 1, curr[2]}); // 右边

                chars[curr[1] + l1] = (char) ('0' + (curr[0] ^ 1));
            }
        }

        assert !pq.isEmpty();
        int mxLen = pq.peek()[2] - pq.peek()[1];
        if (numOps == 0 || mxLen == 1) return mxLen;

        // 接下来需要检查如果长度为 2, 我们是否可以通过 numOps 将其移动到边上, 使得答案为 1
        //  - 首先计算从后往前需要多少次操作才能将 11 或者 00 移动到末尾
        int[] suf = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1];
            if (chars[i] == chars[i + 1]) {
                suf[i] = n - i - 1;
            }
        }
        if (suf[0] <= numOps) return 1;

        //  - 然后从前往后算是否能够在 numOps 的限制下使得所有的 2 都变成 1
        for (int i = 1; i < n; i++) {
            if (chars[i] == chars[i - 1]) {
                if (i + suf[i] <= numOps) return 1;
            }
        }

        // 否则没有办法使得所有的 2 都变为 1
        return 2;
    }

    public static void main(String[] args) {
        assert new Solution().minLength("00000", 2) == 1;
        assert new Solution().minLength("101010010010", 5) == 2;
        assert new Solution().minLength("101010010010", 6) == 1;
        assert new Solution().minLength("0110", 2) == 1;
        assert new Solution().minLength("11", 1) == 1;
        assert new Solution().minLength("00", 1) == 1;
        assert new Solution().minLength("00", 0) == 2;

        assert new Solution().minLength("000001", 1) == 2;
        assert new Solution().minLength("0000", 2) == 1;
        assert new Solution().minLength("0101", 0) == 1;
    }

}
