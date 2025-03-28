package weekly.bw152.D;

import common.Checker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 3425. Longest Special Path
 *
 * https://leetcode.cn/contest/biweekly-contest-148/problems/longest-special-path/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1,
 * represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi, lengthi]
 * indicates an edge between nodes ui and vi with length lengthi.
 *
 * You are also given an integer array nums, where nums[i] represents the value at node i.
 *
 * A special path is defined as a downward path from an ancestor node to a descendant node
 * such that all the values of the nodes in that path are unique.
 *
 * Note that a path may start and end at the same node.
 *
 * Return an array result of size 2, where result[0] is the length of the longest
 * special path, and result[1] is the minimum number of nodes in all possible
 * longest special paths.
 */

public class Solution {

    private final Map<Integer, Map<Integer, Integer>> g = new HashMap<>();

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        for (var edge : edges) g.computeIfAbsent(edge[0], k -> new HashMap<>()).put(edge[1], edge[2]);

        dfs(nums, 0, 0, 0, 0);
        return new int[]{maxLength, minimumNode};
    }

    private int maxLength = 0, minimumNode = 1;
    private final int[] path = new int[50_001];

    // 当前已经到 curr 节点, 累计的长度是 sum, l 是窗口左侧所处的, 深度是 depth
    private void dfs(int[] nums, int curr, int sum, int l, int depth) {
        path[depth] = curr;
        // 检查是否需要移动窗口左边的节点
        while (!isOk(nums, l, depth)) { l++; sum -= g.get(path[depth]).get(path[depth + 1]); }

        // 检查是否需要更新答案
        if (sum > maxLength) { maxLength = sum; minimumNode = depth - l + 1; }
        if (sum == maxLength) minimumNode = Math.min(minimumNode, depth - l + 1);

        for (var next : g.getOrDefault(curr, Collections.emptyMap()).entrySet()) {
            // 在添加一个元素之后, 左边需要移动
            dfs(nums, next.getKey(), sum + next.getValue(), l, depth + 1);
        }
    }

    private boolean isOk(int[] nums, int l, int r) {
        int overhead = 0; int[] count = new int[50_001];
        while (l <= r) if (++count[nums[path[l++]]] > 2) if (++overhead > 1) return false;
        return true;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,1,1},{1,2,3},{1,3,1},{2,4,6},{4,7,2},{3,5,2},{3,6,5},{6,8,3}}, new int[]{1,1,0,3,1,2,1,1,0}), new int[]{9,3});
        assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,3},{0,2,4},{0,3,5}}, new int[]{1,1,0,2}), new int[]{5,2});
    }

}
