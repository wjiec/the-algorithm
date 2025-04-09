package weekly.bw153.D;

import common.Tag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 3501. Maximize Active Section with Trade II
 *
 * https://leetcode.cn/contest/biweekly-contest-153/problems/maximize-active-section-with-trade-ii/
 *
 * You are given a binary string s of length n, where:
 *
 * '1' represents an active section.
 * '0' represents an inactive section.
 * You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
 *
 * Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
 * Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
 * Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].
 *
 * For each query, determine the maximum possible number of active sections in s after
 * making the optimal trade on the substring s[li...ri].
 *
 * Return an array answer, where answer[i] is the result for queries[i].
 *
 * Note
 *
 * For each query, treat s[li...ri] as if it is augmented with a '1' at both ends,
 * forming t = '1' + s[li...ri] + '1'. The augmented '1's do not contribute to the final count.
 *
 * The queries are independent of each other.
 */

public class Solution {

    private static class SparseTable {
        private final int[][] table;
        public SparseTable(List<int[]> ranges) {
            int n = ranges.size() - 1, sz = 32 - Integer.numberOfLeadingZeros(n);

            table = new int[n][sz];
            for (int i = 0; i < n; i++) {
                table[i][0] = (ranges.get(i)[1] - ranges.get(i)[0]) + (ranges.get(i + 1)[1] - ranges.get(i + 1)[0]);
            }

            for (int j = 1; j < sz; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    table[i][j] = Math.max(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
                }
            }
        }
        public int query(int l, int r) {
            if (l >= r) return 0;
            int k = 32 - Integer.numberOfLeadingZeros(r - l) - 1;
            return Math.max(table[l][k], table[r - (1 << k)][k]);
        }
    }

    @Tag({"ST表", "区间最值问题"})
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        // 根据 weekly.bw153.B 可以更进一步的抽象为找到 所有相邻的 0 组的最大值(在 [l, r] 范围内)
        int total = 0, n = s.length();
        List<int[]> groups = new ArrayList<>(); groups.add(new int[]{-1, -1});
        for (int i = 1, p = 0; i <= n; i++) {
            if (i == n || s.charAt(i) != s.charAt(p)) {
                if (s.charAt(p) == '1') total += i - p;
                else groups.add(new int[]{p, i}); // 左闭右开

                p = i;
            }
        }
        groups.add(new int[]{s.length() + 1, s.length() + 1});

        SparseTable st = new SparseTable(groups);
        List<Integer> ans = new ArrayList<>();
        for (var query : queries) {
            int ql = query[0], qr = query[1] + 1; // 左闭右开

            // 找到第一个区间的左端点 >= ql
            int l = Collections.binarySearch(groups, new int[]{ql, n}, Comparator.comparingInt(v -> v[0]));
            if (l < 0) l = -l - 1;

            // 找到最后一个区间的右端点 <= qr
            int r = Collections.binarySearch(groups, new int[]{0, qr + 1}, Comparator.comparingInt(v -> v[1]));
            if (r < 0) r = -r - 1;
            r--; // 找到第一个区间的右端点 > qr 的, 然后前一个区间就是最后一个区间的右端点 <= qr 的

            int curr = 0;
            if (l <= r) { // 在 [ql, qr) 内有完整区间的
                int full = st.query(l, r); // 相邻完整区间的长度之和的最大值
                int sl = add(groups.get(l - 1)[1] - ql, groups.get(l)[1] - groups.get(l)[0]); // 左边残缺的区间和
                int sr = add(groups.get(r)[1] - groups.get(r)[0], qr - groups.get(r + 1)[0]); // 右边残缺的区间和
                curr = Math.max(full, Math.max(sl, sr));
            } else if (l == r + 1) { // 在 [ql, qr) 中都是残缺的区间
                curr = add(groups.get(l - 1)[1] - ql, qr - groups.get(r + 1)[0]);
            }
            ans.add(total + curr);
        }

        return ans;
    }

    private int add(int a, int b) { return a > 0 && b > 0 ? (a + b) : 0; }

    public static void main(String[] args) {
        assert new Solution().maxActiveSectionsAfterTrade("1", new int[][]{{0,0}}).equals(List.of(1));

        assert new Solution().maxActiveSectionsAfterTrade("01", new int[][]{{0,1}}).equals(List.of(1));
        assert new Solution().maxActiveSectionsAfterTrade("0100", new int[][]{{0,3},{0,2},{1,3},{2,3}}).equals(List.of(4,3,1,1));
        assert new Solution().maxActiveSectionsAfterTrade("1000100", new int[][]{{1,5},{0,6},{0,4}}).equals(List.of(6,7,2));
        assert new Solution().maxActiveSectionsAfterTrade("01010", new int[][]{{0,3},{1,4},{1,3}}).equals(List.of(4,4,2));
    }

}
