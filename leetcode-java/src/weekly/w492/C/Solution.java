package weekly.w492.C;

/**
 * Q3. Minimum Operations to Sort a String
 *
 * https://leetcode.cn/contest/weekly-contest-492/problems/minimum-operations-to-sort-a-string/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * In one operation, you can select any substring of s that is not the entire
 * string and sort it in non-descending alphabetical order.
 *
 * Return the minimum number of operations required to make s sorted in non-descending order.
 *
 * If it is not possible, return -1.
 */

public class Solution {

    public int minOperations(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        // 如果已经按照降序排列了, 那么不需要操作, 返回 0
        char mn = chars[0], mx = chars[0]; boolean sorted = true;
        for (int i = 1; i < chars.length; i++) {
            mn = (char) Math.min(mn, chars[i]);
            mx = (char) Math.max(mx, chars[i]);
            if (chars[i] < chars[i - 1]) sorted = false;
        }
        if (sorted) return 0;
        // 如果首字母是最小的字符, 那么只需要选择 [1, n) 操作 1 次
        // 如果尾字母是最大的字符, 那么只需要选择 [0, n - 1) 操作 1 次
        if (chars[0] == mn || chars[n - 1] == mx) return 1;
        // 如果长度为 2 且是倒序的, 那么我们无法完成排序
        if (n == 2) return -1;
        // 否则对于长度 >= 3 的所有字符串, 我们可以
        //  - 先排序 [0, n - 1), 如果首位已经是最小的了, 那么只需要再执行一次 [1, n - 1] 即可
        //  - 先排序 [1, n), 如果尾位已经是最大的, 那么也只需要再执行一次 [0, n - 1) 即可
        // 实际上就是检查 [0, n - 1) 是否有 mn; 或者是 [1, n) 是否有 mx
        for (int i = 0; i < chars.length; i++) {
            if (i < n - 1 && chars[i] == mn) return 2;
            if (i >= 1 && chars[i] == mx) return 2;
        }
        // 否则都需要 3 次
        return 3;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("oool") == 2;

        assert new Solution().minOperations("jgg") == 2;
        assert new Solution().minOperations("edc") == 3;
    }

}
