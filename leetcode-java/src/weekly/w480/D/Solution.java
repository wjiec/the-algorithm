package weekly.w480.D;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * Q4. Minimum Deletions to Make Alternating Substring
 *
 * https://leetcode.cn/contest/weekly-contest-480/problems/minimum-deletions-to-make-alternating-substring/
 *
 * You are given a string s of length n consisting only of the characters 'A' and 'B'.
 *
 * You are also given a 2D integer array queries of length q, where each queries[i] is one of the following:
 *
 * [1, j]: Flip the character at index j of s i.e. 'A' changes to 'B' (and vice versa).
 * This operation mutates s and affects subsequent queries.
 *
 * [2, l, r]: Compute the minimum number of character deletions required to make the substring s[l..r] alternating.
 * This operation does not modify s; the length of s remains n.
 *
 * A substring is alternating if no two adjacent characters are equal. A substring of length 1 is always alternating.
 *
 * Return an integer array answer, where answer[i] is the result of the ith query of type [2, l, r].
 */

public class Solution {

    private void update(int[] tree, int i, int v) {
        for (; i < tree.length; i += i & -i) tree[i] += v;
    }
    private int query(int[] tree, int i) {
        int ans = 0;
        for (; i > 0; i -= i & -i) ans += tree[i];
        return ans;
    }
    private int query(int[] tree, int l, int r) {
        return query(tree, r + 1) - query(tree, l);
    }
    // 找到 < target 的最后一个的位置
    private int search(int[] tree, int target) {
        int l = -1, r = tree.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (query(tree, mid) < target) l = mid;
            else r = mid;
        }
        return l;
    }

    public int[] minDeletions(String s, int[][] queries) {
        // 求最小删除数, 其实就是求子字符串中的 A, B 分组的数量
        //  - 对于 AAA, BBB, AA, BB 我们只能将每组的长度变为 1
        //
        // 所以问题变成, 如何动态计算每一个区间的字符组数, 并且支持删除组以及新增组
        //  - 使用树状数组?
        //
        // 树状数组
        //  - 在每一组的开头位置 +1, 中间位置都是 0
        //  - 对于 AAA, BB, A, BBB, AAA 则对应 100, 10, 1, 100, 100
        //      - 计算区间前缀和可以得知有多少个分组
        //  - 如果产生修改, 有以下情况
        //      - 单个 A 或者 B 的位置 i 发生修改: 会导致前后区间合并
        //          - 也就是 update(i, -1) && update(i + 1, -1)
        //      - 在一段的 AA... 或者 BB... 的开头发生修改: 会导致前一个区间变长
        //          - 也就是 update(i, -1) && update(i + 1, 1)
        //      - 在一段的 AA... 或者 BB... 的结尾发生修改: 会导致后一个区间变长
        //          - 也就是 update(i, 1) && update(i + 1, -1)
        //      - 在一段的 AA... 或者 BB... 的中间发生修改: 会知道前后区间分裂
        //          - 也就是 update(i, 1) && update(i + 1, 1)
        int[] tree = new int[s.length() + 1];
        for (int l = 0, r = 0; r <= s.length(); r++) {
            if (r == s.length() || s.charAt(r) != s.charAt(l)) {
                update(tree, l + 1, 1); l = r;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (var q : queries) {
            if (q[0] == 2) {
                int l = q[1], r = q[2], ql = query(tree, l, l);
                // 如果查询的左端点不是位于开头的话, 那么还需要加上不完整的一组
                ans.add(r - l + 1 - (query(tree, l, r) + (ql == 0 ? 1 : 0)));
                continue;
            }

            // 需要更新位置 j 的字符
            int j = q[1], qj = query(tree, j + 1);

            // 我们需要找到 j 所在的字符区间 [l, r)
            //  - l: (< qj) + 1
            //  - r: < (qj + 1)
            int l = search(tree, qj) + 1, r = search(tree, qj + 1);
            // 只有一个元素, 会导致前后区间合并
            if (l == r) { update(tree, r, -1); update(tree, r + 1, -1); }
            // 在区间的开头
            else if (j + 1 == l) { update(tree, l, -1); update(tree, l + 1, 1); }
            // 在区间的结尾
            else if (j + 1 == r) { update(tree, r, 1); update(tree, r + 1, -1); }
            // 在区间的中间
            else { update(tree, j + 1, 1); update(tree, j + 2, 1); }
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minDeletions("ABA", new int[][]{{2,1,2},{1,1},{2,0,2}}), new int[]{0,2});
        assert Checker.check(new Solution().minDeletions("ABB", new int[][]{{2,0,2},{1,2},{2,0,2}}), new int[]{1,0});
        assert Checker.check(new Solution().minDeletions("BABA", new int[][]{{2,0,3},{1,1},{2,1,3}}), new int[]{0,1});
    }

}
