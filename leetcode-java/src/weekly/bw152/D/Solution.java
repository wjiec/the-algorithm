package weekly.bw152.D;

import ability.Benchmark;
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

    /** @noinspection DuplicatedCode, DuplicatedCode, unchecked */
    private static class Optimization { // based weekly.bw148.C
        private List<int[]>[] g = null;

        public int[] longestSpecialPath(int[][] edges, int[] nums) {
            g = new List[nums.length];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (var edge : edges) {
                g[edge[0]].add(new int[]{edge[1], edge[2]});
                g[edge[1]].add(new int[]{edge[0], edge[2]});
            }

            int maxColors = 0;
            for (var v : nums) maxColors = Math.max(maxColors, v);

            // 在树上使用滑动窗口, 我们枚举滑动窗口的右边, 然后根据当前节点的颜色找到上一次出现
            // 对应颜色的深度, 然后找到窗口的左端点应该在什么位置
            preSum = new int[nums.length + 1];
            lastDepth = new int[maxColors + 1];
            dfs(nums, 0, -1, 0, 1, 0);
            return new int[]{maxLength, minimumNodes};
        }

        private int maxLength = 0, minimumNodes = 1;
        private int[] lastDepth = null, preSum = null;

        // 当前节点位于 node, 从 parent 过来的, 深度为 r, 同时有效的窗口左侧位置是 l
        //  - 第一个节点的深度是从 1 开始的, 我们使用 preSum 记录每个深度对应的前缀和
        //  - diff bw148.C: 我们需要额外记录下在窗口内颜色出现 2 次的最左侧位置 twice
        private void dfs(int[] colors, int node, int parent, int l, int r, int twice) {
            // 找到当前节点的颜色以及当前颜色的上一次出现位置 prevDepth
            int currColor = colors[node], prevDepth = lastDepth[currColor];
            // 在加入当前节点之前, 有效的窗口是 [l, r), 加入当前颜色的节点之后, 窗口的左端点需要根据情况进行移动
            //  - 此时区间内可能有 2 个颜色出现 2 次:
            //      - 当前颜色: prevDepth, r
            //      - 区间内之前合法的颜色: twice, ? (在窗口内的任意位置)
            //  - 我们需要保证区间内只有一个颜色出现 2 次, 同时考虑长度尽可能长, 所以考虑
            //      - 如果 prevDepth < twice, 那我们保留 twice 对应的颜色, 需要从 prevDepth + 1 开始
            //      - 如果 twice < prevDepth, 那我们保留当前颜色, 从 twice + 1 开始
            //  - 同时我们需要保证以上所取的位置在 [l, r] 之间才是合理的
            // 即新窗口为 [max(l, min(prevDepth, twice) + 1), r]
            l = Math.max(l, Math.min(prevDepth, twice) + 1);

            // 计算当前窗口的有效长度和节点个数
            //  - sum[i] 表示从根节点到 i 节点的长度之和
            //  - 如果需要计算 [l, r] 的长度之和, 等于从根节点到 r 的长度之和 - 从根节点到 l 的长度之和
            int currLen = preSum[r] - preSum[l], nodeCount = r - l + 1;
            if (currLen > maxLength || (currLen == maxLength && nodeCount < minimumNodes)) {
                maxLength = currLen; minimumNodes = nodeCount;
            }

            // 覆盖当前颜色出现的最后深度
            lastDepth[currColor] = r;

            // 枚举后续的窗口
            for (var next : g[node]) {
                if (next[0] == parent) continue;

                preSum[r + 1] = preSum[r] + next[1];
                // 根据上述推论, 当前我们保留的颜色是深度较大的那个, 也就是 max(twice, prevDepth)
                dfs(colors, next[0], node, l, r + 1, Math.max(twice, prevDepth));
            }

            // 恢复当前颜色出现的最后位置
            lastDepth[currColor] = prevDepth;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{0,2,4},{1,2,10},{3,1,5}}, new int[]{4,5,4,5}), new int[]{15,3});
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{5,0,3},{1,3,4},{4,2,2},{2,5,6},{2,1,5},{6,1,1}}, new int[]{5,2,2,2,5,3,1}), new int[]{15,5});
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{4,1,9},{2,0,2},{0,4,10},{0,3,9},{3,5,5}}, new int[]{1,2,3,2,1,1}), new int[]{19,3});
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{1,0,10}}, new int[]{2,2}), new int[]{10,2});

            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{0,1,1},{1,2,3},{1,3,1},{2,4,6},{4,7,2},{3,5,2},{3,6,5},{6,8,3}}, new int[]{1,1,0,3,1,2,1,1,0}), new int[]{9,3});
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{1,0,3},{0,2,4},{0,3,5}}, new int[]{1,1,0,2}), new int[]{5,2});
        });

        Benchmark.benchmark("", () -> {
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,2,4},{1,2,10},{3,1,5}}, new int[]{4,5,4,5}), new int[]{15,3});
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{5,0,3},{1,3,4},{4,2,2},{2,5,6},{2,1,5},{6,1,1}}, new int[]{5,2,2,2,5,3,1}), new int[]{15,5});
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{4,1,9},{2,0,2},{0,4,10},{0,3,9},{3,5,5}}, new int[]{1,2,3,2,1,1}), new int[]{19,3});
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,10}}, new int[]{2,2}), new int[]{10,2});

            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,1,1},{1,2,3},{1,3,1},{2,4,6},{4,7,2},{3,5,2},{3,6,5},{6,8,3}}, new int[]{1,1,0,3,1,2,1,1,0}), new int[]{9,3});
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,3},{0,2,4},{0,3,5}}, new int[]{1,1,0,2}), new int[]{5,2});
        });
    }

}
