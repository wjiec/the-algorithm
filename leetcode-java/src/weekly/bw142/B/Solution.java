package weekly.bw142.B;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 3331. Find Subtree Sizes After Changes
 *
 * https://leetcode.cn/contest/biweekly-contest-142/problems/find-subtree-sizes-after-changes/
 *
 * You are given a tree rooted at node 0 that consists of n nodes numbered from 0 to n - 1.
 * The tree is represented by an array parent of size n, where parent[i] is the parent of node i.
 * Since node 0 is the root, parent[0] == -1.
 *
 * You are also given a string s of length n, where s[i] is the character assigned to node i.
 *
 * We make the following changes on the tree one time simultaneously for all nodes x from 1 to n - 1:
 *
 * Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
 * If node y does not exist, do nothing.
 *
 * Otherwise, remove the edge between x and its current parent and make node y the new parent of x by
 * adding an edge between them.
 *
 * Return an array answer of size n where answer[i] is the size of the subtree rooted at node i in the final tree.
 *
 * A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
 */

/** @noinspection unchecked, DataFlowIssue */
public class Solution {

    private List<Integer>[] g = null;

    /** @noinspection DuplicatedCode */
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length; g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) if (parent[i] >= 0) g[parent[i]].add(i);

        char[] chars = s.toCharArray();
        dfs(0, chars, parent);

        Arrays.setAll(g, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) if (parent[i] >= 0) g[parent[i]].add(i);

        int[] ans = new int[n];
        dfs2(0, ans);
        return ans;
    }

    private int dfs2(int node, int[] ans) {
        int curr = 1;
        for (var next : g[node]) {
            curr += dfs2(next, ans);
        }

        return ans[node] = curr;
    }

    private final ArrayDeque<Integer>[] q = new ArrayDeque[128];
    { Arrays.setAll(q, i -> new ArrayDeque<>()); }

    // 找到离当前节点最近的相同父节点
    private void dfs(int node, char[] chars, int[] np) {
        char c = chars[node];
        if (!q[c].isEmpty()) np[node] = q[c].peek();

        q[c].push(node);
        for (var next : g[node]) dfs(next, chars, np);
        q[c].pop();
    }

    public static void main(String[] args) {
    }

}
