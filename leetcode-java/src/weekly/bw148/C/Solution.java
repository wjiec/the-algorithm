package weekly.bw148.C;

import ability.Benchmark;
import common.Checker;

import java.util.*;

/**
 * 3425. Longest Special Path
 *
 * https://leetcode.cn/contest/biweekly-contest-148/problems/longest-special-path/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1, represented
 * by a 2D array edges of length n - 1, where edges[i] = [ui, vi, lengthi] indicates an edge between
 * nodes ui and vi with length lengthi. You are also given an integer array nums, where nums[i] represents
 * the value at node i.
 *
 * A special path is defined as a downward path from an ancestor node to a descendant node such that all
 * the values of the nodes in that path are unique.
 *
 * Note that a path may start and end at the same node.
 *
 * Return an array result of size 2, where result[0] is the length of the longest
 * special path, and result[1] is the minimum number of nodes in all possible
 * longest special paths.
 */

/** @noinspection unchecked*/
public class Solution {

    private Map<Integer, Integer>[] g;
    private int[] next = null, sum = null, depth = null, values = null;

    public int[] longestSpecialPath(int[][] edges, int[] nums) {
        values = nums;
        next = new int[nums.length];
        depth = new int[nums.length];
        sum = new int[nums.length];

        g = new Map[nums.length];
        Arrays.setAll(g, i -> new HashMap<>());
        for (var edge : edges) {
            g[edge[0]].put(edge[1], edge[2]);
            g[edge[1]].put(edge[0], edge[2]);
        }

        dfs(0, -1, 0);
        return new int[]{maxLength, minimumNodes};
    }

    private int maxLength = 0, minimumNodes = 1;
    private final Map<Integer, Deque<Integer>> seen = new HashMap<>();

    // 路径中不能存在相同的两个值, 所以遍历树中的每个节点之后需要找到前一个相同值所在的位置
    // 我们需要从与当前节点最近的非重复节点开始计算特殊路径的长度以及节点个数
    private void dfs(int curr, int parent, int head) {
        // 根据节点的值获取一个堆栈用于找到最近的相同值节点
        var dq = seen.computeIfAbsent(values[curr], k -> new ArrayDeque<>());
        // 找到与当前节点的值相同的节点的所在位置, 这个节点需要比之前找到的最近节点更近
        if (!dq.isEmpty() && depth[next[dq.peek()]] > depth[head]) head = next[dq.peek()];

        // 现在我们找到了一段符合条件的特殊路径, 检查是否需要更新答案
        if (sum[curr] - sum[head] > maxLength) {
            maxLength = sum[curr] - sum[head];
            minimumNodes = depth[curr] - depth[head] + 1;
        } else if (sum[curr] - sum[head] == maxLength) {
            minimumNodes = Math.min(minimumNodes, depth[curr] - depth[head] + 1);
        }

        dq.push(curr);
        for (var e : g[curr].entrySet()) {
            if (e.getKey() == parent) continue;

            next[curr] = e.getKey();
            depth[e.getKey()] = depth[curr] + 1;
            sum[e.getKey()] = sum[curr] + e.getValue();

            dfs(e.getKey(), curr, head);
        }

        // 恢复现场
        dq.pop();
    }

    /** @noinspection DuplicatedCode*/
    private static class Optimization {

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
            dfs(nums, 0, -1, 0, 1);
            return new int[]{maxLength, minimumNodes};
        }

        private int maxLength = 0, minimumNodes = 1;
        private int[] lastDepth = null, preSum = null;

        // 当前节点位于 node, 从 parent 过来的, 深度为 r, 同时有效的窗口左侧位置是 l
        //  - 第一个节点的深度是从 1 开始的, 我们使用 preSum 记录每个深度对应的前缀和
        private void dfs(int[] colors, int node, int parent, int l, int r) {
            // 找到当前节点的颜色以及当前颜色的上一次出现位置
            int currColor = colors[node], prevDepth = lastDepth[currColor];
            // 在加入当前节点之前, 有效的窗口是 [l, r), 加入当前颜色的节点之后, 窗口的左端点需要根据情况进行移动
            //  - 如果 prevDepth < l, 则 l 应当保持不变
            //  - 如果 prevDepth > l, 则应该使用 prevDepth + 1 作为新窗口的左侧端点
            // 即新窗口为 [max(l, prevDepth + 1), r]
            l = Math.max(l, prevDepth + 1);

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
                dfs(colors, next[0], node, l, r + 1);
            }

            // 恢复当前颜色出现的最后位置
            lastDepth[currColor] = prevDepth;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{4,0,2},{3,1,8},{3,2,9},{3,4,8}}, new int[]{5,5,1,3,3}), new int[]{9, 2});

            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}}, new int[]{2,1,2,1,3,1}), new int[]{6, 2});
            assert Checker.check(new Optimization().longestSpecialPath(new int[][]{{1,0,8}}, new int[]{2,2}), new int[]{0, 1});
        });

        Benchmark.benchmark("", () -> {
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{4,0,2},{3,1,8},{3,2,9},{3,4,8}}, new int[]{5,5,1,3,3}), new int[]{9, 2});

            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{0,1,2},{1,2,3},{1,3,5},{1,4,4},{2,5,6}}, new int[]{2,1,2,1,3,1}), new int[]{6, 2});
            assert Checker.check(new Solution().longestSpecialPath(new int[][]{{1,0,8}}, new int[]{2,2}), new int[]{0, 1});
        });
    }

}
