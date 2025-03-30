package weekly.bw152.D;

import common.Checker;

import java.util.*;

/**
 * 3486. Longest Special Path II
 *
 * https://leetcode.cn/contest/biweekly-contest-152/problems/longest-special-path-ii/
 *
 * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1.
 * This is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi, lengthi]
 * indicates an edge between nodes ui and vi with length lengthi. You are also given an integer
 * array nums, where nums[i] represents the value at node i.
 *
 * A special path is defined as a downward path from an ancestor node to a descendant node in
 * which all node values are distinct, except for at most one value that may appear twice.
 *
 * Return an array result of size 2, where result[0] is the length of the longest special
 * path, and result[1] is the minimum number of nodes in all possible longest special paths.
 */

public class Solution {

    private List<int[]>[] g = null;

    @SuppressWarnings({"unchecked", "unused", "DuplicatedCode"})
    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        g = new List[nums.length]; Arrays.setAll(g, i -> new ArrayList<>());
        for (var edge : edges) {
            g[edge[0]].add(new int[]{edge[1], edge[2]});
            g[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        dfs(nums, 0, -1, 0, 1, 0);
        return new int[]{maxLength, minimumNode};
    }

    private int maxLength = 0, minimumNode = 1;
    private final int[] sum = new int[50_001];
    private final Map<Integer, Integer> last = new HashMap<>(); // 颜色最后一次出现的深度 + 1

    // 当前位置在 curr, 是从 parent 来的, 窗口是 [l, r], 出现 2 次的元素的最后一次位置是 twice
    private void dfs(int[] nums, int curr, int parent, int l, int r, int twice) {
        // 当前节点的颜色是 color, 上一次出现的深度是 currLast, 从 [currLast, r] 出现了 2 次当前颜色
        int color = nums[curr], currLast = last.getOrDefault(color, 0);
        // 窗口左端点 l 必须大于等于 min(twice, currLast) + 1, 这样才能使得只有一个颜色出现 2 次
        l = Math.max(l, Math.min(twice, currLast));

        int currLen = sum[r] - sum[l + 1];
        if (currLen > maxLength || (currLen == maxLength && r - l < minimumNode)) {
            maxLength = currLen; minimumNode = r - l;
        }

        last.put(color, r); // 替换当前颜色出现的最后一次位置
        for (var next : g[curr]) {
            if (next[0] == parent) continue;

            sum[r + 1] = sum[r] + next[1];
            dfs(nums, next[0], curr, l, r + 1, Math.max(twice, currLast));
        }
        last.put(color, currLast); // 回滚当前颜色的最后一次出现位置
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,2,4},{1,2,10},{3,1,5}}, new int[]{4,5,4,5}), new int[]{15,3});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{5,0,3},{1,3,4},{4,2,2},{2,5,6},{2,1,5},{6,1,1}}, new int[]{5,2,2,2,5,3,1}), new int[]{15,5});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{4,1,9},{2,0,2},{0,4,10},{0,3,9},{3,5,5}}, new int[]{1,2,3,2,1,1}), new int[]{19,3});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,10}}, new int[]{2,2}), new int[]{10,2});

        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,1,1},{1,2,3},{1,3,1},{2,4,6},{4,7,2},{3,5,2},{3,6,5},{6,8,3}}, new int[]{1,1,0,3,1,2,1,1,0}), new int[]{9,3});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,3},{0,2,4},{0,3,5}}, new int[]{1,1,0,2}), new int[]{5,2});
    }

}
