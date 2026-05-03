package weekly.bw176.D;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Palindromic Path Queries in a Tree
 *
 * https://leetcode.cn/contest/biweekly-contest-176/problems/palindromic-path-queries-in-a-tree/
 *
 * You are given an undirected tree with n nodes labeled 0 to n - 1. This is represented
 * by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected
 * edge between nodes ui and vi.
 *
 * You are also given a string s of length n consisting of lowercase English letters,
 * where s[i] represents the character assigned to node i.
 *
 * You are also given a string array queries, where each queries[i] is either:
 *
 * "update ui c": Change the character at node ui to c. Formally, update s[ui] = c.
 *
 * "query ui vi": Determine whether the string formed by the characters on the unique path
 * from ui to vi (inclusive) can be rearranged into a palindrome.
 *
 * Return a boolean array answer, where answer[j] is true if the jth query of type "query ui vi"
 * can be rearranged into a palindrome, and false otherwise.
 */

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Solution {

    private static class TreeOp {
        private int ts = 0;
        private final int[][] table;
        private final int[] enter;
        private final int[] leave;
        private final int[] depth;
        private final int[] tree, lazy;
        public TreeOp(int n, int[][] edges, char[] chars) {
            int logN = 32 - Integer.numberOfLeadingZeros(n);

            enter = new int[n];
            leave = new int[n];
            depth = new int[n];
            table = new int[n][logN];
            tree = new int[2 << (32 - Integer.numberOfLeadingZeros(2 * n))];
            lazy = new int[2 << (32 - Integer.numberOfLeadingZeros(2 * n))];

            List<Integer>[] g = new List[n]; Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                g[edge[0]].add(edge[1]);
                g[edge[1]].add(edge[0]);
            }
            dfs(g, 0, -1);
            for (int j = 1; j < logN; j++) {
                for (int i = 0; i < n; i++) {
                    int p = table[i][j - 1];
                    table[i][j] = p < 0 ? p : table[p][j - 1];
                }
            }

            for (int i = 0; i < n; i++) {
                update(enter[i], leave[i] - 1, 1 << (chars[i] - 'a'));
            }
        }

        public int query(int node) { return query(enter[node], enter[node]); }
        private int query(int l, int r) { return query(1, 0, ts - 1, l, r); }
        private int query(int p, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[p];

            spread(p, l, r);
            int mid = l + (r - l) / 2, ans = 0;
            if (ql <= mid) ans ^= query(p * 2, l, mid, ql, qr);
            if (mid < qr) ans ^= query(p * 2 + 1, mid + 1, r, ql, qr);
            return ans;
        }

        public void update(int node, int v) { update(enter[node], leave[node] - 1, v); }
        private void update(int l, int r, int v) { update(1, 0, ts - 1, l, r, v); }
        private void update(int p, int l, int r, int ql, int qr, int v) {
            // 当前区间是所需要修改区间的子集
            if (ql <= l && r <= qr) { lazyUpdate(p, l, r, v); return; }

            spread(p, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) update(p * 2, l, mid, ql, qr, v);
            if (mid < qr) update(p * 2 + 1, mid + 1, r, ql, qr, v);

            tree[p] = tree[p * 2] ^ tree[p * 2 + 1];
        }

        private void spread(int p, int l, int r) {
            if (lazy[p] != 0) {
                int mid = l + (r - l) / 2;
                lazyUpdate(p * 2, l, mid, lazy[p]);
                lazyUpdate(p * 2 + 1, mid + 1, r, lazy[p]);
                lazy[p] = 0;
            }
        }
        private void lazyUpdate(int p, int l, int r, int v) {
            if ((r - l + 1) % 2 == 1) tree[p] ^= v;
            lazy[p] ^= v;
        }

        private void dfs(List<Integer>[] g, int curr, int parent) {
            enter[curr] = ts++;
            table[curr][0] = parent;
            for (var next : g[curr]) {
                if (next == parent) continue;

                depth[next] = depth[curr] + 1;
                dfs(g, next, curr);
            }
            leave[curr] = ts++;
        }

        public int distance(int u, int v) {
            return depth[u] + depth[v] - 2 * depth[lca(u, v)];
        }
        public int lca(int u, int v) {
            if (depth[u] > depth[v]) { int t = u; u = v; v = t; }

            v = kthAncestor(v, depth[v] - depth[u]);
            if (u == v) return u;

            for (int j = table[u].length - 1; j >= 0; j--) {
                int px = table[u][j], py = table[v][j];
                if (px != py) { u = px; v = py; }
            }

            return table[u][0];
        }
        public int kthAncestor(int node, int k) {
            for (; k != 0; k &= k - 1) {
                node = table[node][Integer.numberOfTrailingZeros(k)];
            }
            return node;
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // 查询从 u -> v 是否能重新排列为一个回文序列
        //  - 如果节点数为偶数, 则每个字母的数量必须都是偶数
        //  - 如果节点数为奇数, 则必须有一个字母的数量是奇数, 其他的为偶数
        //
        // 现在的问题是如何在树上维护字母数量
        //  - 位运算压缩, 从上往下 children = parent ^ (1 << c)
        //  - 也就是节点的值等于从根节点到当前节点的字母数量
        //  - 然后我们需要计算从 u -> v 的字母数量
        //      - 找到 lca, 最终答案也就是 [root -> u] ^ [root -> v] 的过程中 [root -> lca] 会走两遍, 也就是抵消了
        //      - lca 会被抵消掉, 需要补上
        //  - 我们需要计算 u -> v 走过的节点数
        //
        // 更新操作会影响当前节点以及子树的区间
        //
        // 树上时间戳 + xor线段树 + LCA
        char[] chars = s.toCharArray();
        TreeOp t = new TreeOp(n, edges, chars);
        List<Boolean> ans = new ArrayList<>();
        for (var query : queries) {
            var ops = query.split(" ");
            if (ops[0].equals("update")) {
                int i = Integer.parseInt(ops[1]); char c = ops[2].charAt(0);
                if (chars[i] == c) continue;
                t.update(i, 1 << (c - 'a') | (1 << (chars[i] - 'a')));
                chars[i] = c;
            } else {
                int u = Integer.parseInt(ops[1]), v = Integer.parseInt(ops[2]);

                // 计算字符数量
                int lca = t.lca(u, v), qu = t.query(u), qv = t.query(v);
                int c = qu ^ qv ^ (1 << (chars[lca] - 'a')), d = t.distance(u, v) + 1;
                if (d % 2 == 0) ans.add(c == 0); else ans.add((c & (c - 1)) == 0);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().palindromePath(19, new int[][]{
            {0,7},{1,2},{8,11},{0,1},{8,12},{9,17},{7,10},{1,3},{1,9},{0,6},
            {5,16},{1,5},{6,8},{0,14},{11,15},{1,13},{9,18},{1,4}
        }, "deedcbeddcbcdaceeda", new String[]{
            "update 10 e","update 6 e","query 16 5","update 7 b",
            "query 17 18","query 2 0","query 12 11","query 1 6","query 0 1"
        }), List.of(false,false,true,true,true,false));

        assert Checker.check(new Solution().palindromePath(3, new int[][]{{0, 1}, {1, 2}}, "aac", new String[]{
            "query 0 2",
            "update 1 b",
            "query 0 2",
        }), List.of(true, false));
    }

}
