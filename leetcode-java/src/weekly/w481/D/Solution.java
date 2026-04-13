package weekly.w481.D;

import common.TODO;
import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Total Sum of Interaction Cost in Tree Groups
 *
 * https://leetcode.cn/contest/weekly-contest-481/problems/total-sum-of-interaction-cost-in-tree-groups/
 *
 * You are given an integer n and an undirected tree with n nodes numbered from 0 to n - 1.
 *
 * This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
 * indicates an undirected edge between nodes ui and vi.
 *
 * You are also given an integer array group of length n, where group[i] denotes
 * the group label assigned to node i.
 *
 * Two nodes u and v are considered part of the same group if group[u] == group[v].
 * The interaction cost between u and v is defined as the number of edges
 * on the unique path connecting them in the tree.
 *
 * Return an integer denoting the sum of interaction costs over all
 * unordered pairs (u, v) with u != v such that group[u] == group[v].
 */

@SuppressWarnings({"unchecked", "DuplicatedCode"})
public class Solution {

    private static final int MAX_N = 21;

    public long interactionCosts(int n, int[][] edges, int[] group) {
        // 找到所有 group[u] == group[v] 的节点对的边数之和
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        // 将统计节点对数改为统计每条边的贡献
        //  - 也就是计算这条边会有多少个节点对经过
        //  - 对于一条边, 如果左边有 a 个节点, 右边有 b 个节点, 那么经过这条边的节点对就有 a * b 次
        // 分别计算所有节点在不用组内的节点个数即可
        for (var v : group) members[v]++;
        return dfs(g, 0, -1, group, new int[n][MAX_N]);
    }

    private final long[] members = new long[MAX_N];

    private long dfs(List<Integer>[] g, int curr, int parent, int[] group, int[][] count) {
        count[curr][group[curr]]++;

        // 后序遍历, 我们需要知道所有儿子中的当前节点个数
        long ans = 0;
        for (var next : g[curr]) {
            if (next == parent) continue;
            ans += dfs(g, next, curr, group, count);

            // 累加当前节点的节点个数
            for (int i = 1; i < MAX_N; i++) count[curr][i] += count[next][i];
        }

        // 对于现在 parent -> curr 的这条边, 我们需要计算
        //  - 在 curr 下面的所有子节点的情况为 count[curr][i]
        //  - parent == -1 的情况下, 所有的 count[curr][i] == members[i]
        for (int i = 1; i < MAX_N; i++) {
            ans += count[curr][i] * (members[i] - count[curr][i]);
        }

        return ans;
    }

    @TODO
    @Tag("虚树")
    private static class VirtualTree {
    }

    public static void main(String[] args) {
        assert new Solution().interactionCosts(3, new int[][]{{0,1},{1,2}}, new int[]{1,1,1}) == 4L;
        assert new Solution().interactionCosts(3, new int[][]{{0,1},{1,2}}, new int[]{3,2,3}) == 2L;
        assert new Solution().interactionCosts(4, new int[][]{{0,1},{0,2},{0,3}}, new int[]{1,1,4,4}) == 3L;
        assert new Solution().interactionCosts(2, new int[][]{{0,1}}, new int[]{9,8}) == 0L;
    }

}
