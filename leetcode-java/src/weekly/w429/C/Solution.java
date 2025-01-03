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
        int n = s.length();
        char[] chars = s.toCharArray();
        // 让连续的 0 或者连续的 1 的长度最小, 最理想情况是 0 1 0 1 ... 这样相互间隔
        int xor = 0;
        for (int i = 0; i < n; i++) {
            if ((((chars[i] - '0') ^ i) & 1) == 1) xor++;
        }
        if (numOps >= Math.min(xor, n - xor)) return 1;

        // 首先对字符串进行分组
        // [section_len, section_count, len]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 1, l = 0; i <= n; i++) {
            if (i == n || chars[i] != chars[l]) {
                pq.add(new int[]{i - l, 1, i - l});
                l = i;
            }
        }

        // 接下来只需要对长度大于等于 2 的所有分段进行切分
        //  - 分组之后对最大长度进行切分再加入最大堆是不正确的, 考虑长度为 10 切两次的情况
        //      - 10 -> 4 |1| 5 -> 4 |1| (2 |1| 3)
        //      - 10 -> 3 |1| 3 |1| 2
        for (; !pq.isEmpty() && numOps > 0; numOps--) {
            var curr = pq.remove();
            // 如果最小长度已经是 2 了且我们已经检查过无法使其变为 1, 所以我们直接返回 2
            if (curr[0] == 2) return 2;

            // 对取出来的分组再切一刀, 要求尽可能均分的切
            curr[0] = curr[2] / ++curr[1];
            pq.add(curr);
        }

        return pq.remove()[0];
    }

    public static void main(String[] args) {
        assert new Solution().minLength("0000", 1) == 2;
        assert new Solution().minLength("00000", 2) == 1;
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
