package weekly.w447.D;

import common.Checker;
import common.PrettyPrinter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 3534. Path Existence Queries in a Graph II
 *
 * https://leetcode.cn/contest/weekly-contest-447/problems/path-existence-queries-in-a-graph-ii/
 *
 * You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.
 *
 * You are also given an integer array nums of length n and an integer maxDiff.
 *
 * An undirected edge exists between nodes i and j if the absolute difference
 * between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).
 *
 * You are also given a 2D integer array queries. For each queries[i] = [ui, vi], find
 * the minimum distance between nodes ui and vi. If no path exists between the two
 * nodes, return -1 for that query.
 *
 * Return an array answer, where answer[i] is the result of the ith query.
 *
 * Note: The edges between the nodes are unweighted.
 */

public class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // 在处理之前, 需要先把 nums 中的数字映射到一个一纬数组上, 方便后续进行处理
        Integer[] sorted = new Integer[n];
        Arrays.setAll(sorted, i -> i);
        // sorted[i] 表示 nums 中从小到大排第 i 的索引
        Arrays.sort(sorted, Comparator.comparingInt(i -> nums[i]));

        // 然后再将 i -> sorted[i] 建立映射, 也就是记录 i 位置的是第几小
        int[] ranking = new int[n];
        for (int i = 0; i < n; i++) ranking[sorted[i]] = i;
        PrettyPrinter.println(nums);
        PrettyPrinter.println(sorted);
        PrettyPrinter.println(ranking);

        // 先使用双指针找到每一个位置可以到达的最远距离
        int bits = 32 - Integer.numberOfLeadingZeros(n);
        int[][] jump = new int[n][bits];
        for (int l = 0, r = 0; r < n; r++) {
            while (nums[sorted[r]] - nums[sorted[l]] > maxDiff) l++;
            jump[r][0] = l;
        }

        // 再使用倍增的思想查找从 u 到 v 的最短路径
        for (int j = 1; j < bits; j++) {
            for (int i = 0; i < n; i++) {
                // 找到从 i 开始, 跳跃 1 << (j - 1) 次能到的位置
                int half = jump[i][j - 1];
                // 接下来再从 p 位置再跳跃 1 << (j - 1) 次能到的位置, 也就是
                // 从 i 开始跳跃 1 << j 次可以到的地方
                jump[i][j] = jump[half][j - 1];
            }
        }

        // 枚举查询
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (l == r) continue; // 不需要跳

            int rl = ranking[l], rr = ranking[r];
            if (rl > rr) { int t = rl; rl = rr; rr = t; }

            // 从 rr 开始跳到 rl
            int curr = 0;
            for (int k = bits - 1; k >= 0; k--) {
                if (jump[rr][k] > rl) {
                    curr |= 1 << k;
                    rr = jump[rr][k];
                }
            }
            ans[i] = jump[rr][0] > rl ? -1 : (curr + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pathExistenceQueries(3, new int[]{15,19,3}, 14, new int[][]{{2,1},{2,2}}), new int[]{2,0});
        assert Checker.check(new Solution().pathExistenceQueries(5, new int[]{5,3,1,9,10}, 2, new int[][]{{0,1},{0,2},{2,3},{4,3}}), new int[]{1,2,-1,1});
    }

}
