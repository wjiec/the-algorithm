package weekly.bw153.D;

import java.util.ArrayList;
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

    /** @noinspection DuplicatedCode*/
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        s = "1" + s + "1";
        List<Integer> groups = new ArrayList<>();
        for (int i = 0, p = 9, c = 0; i <= s.length(); i++) {
            int curr = i == s.length() ? 1 : (s.charAt(i) - '0');
            if (i == s.length() || curr != p) {
                if (c != 0) groups.add(c * (p == 1 ? 1 : -1));
                p = curr; c = 1;
            } else c++;
        }

        int nc = 0, total = 0;
        for (var v : groups) {
            if (v > 0) total += v;
            else nc++;
        }

        List<Integer> ans = new ArrayList<>();
        if (nc < 2) {
            for (int i = 0; i < queries.length; i++) ans.add(total - 2);
            return ans;
        }

        int[] acc = new int[groups.size() + 1];
        for (int i = 1; i <= groups.size(); i++) acc[i] = acc[i - 1] + groups.get(i - 1);

        for (var query : queries) {
            ans.add(trade(groups, acc, query[0], query[1], total));
        }
        return ans;
    }

    private int trade(List<Integer> groups, int[] acc, int l, int r, int tot) {
        // 找到在 [l, r] 范围内的 groups, 然后找到相邻的最大负数之和
        return 1;
    }

    public static void main(String[] args) {
    }

}
