package lcp.p46;

import common.Checker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LCP 46. 志愿者调配
 *
 * https://leetcode.cn/problems/05ZEDJ/
 *
 * 「力扣挑战赛」有 n 个比赛场馆（场馆编号从 0 开始），场馆之间的通道分布情况记录于二维数组 edges 中，edges[i]= [x, y]
 * 表示第 i 条通道连接场馆 x 和场馆 y(即两个场馆相邻)。初始每个场馆中都有一定人数的志愿者（不同场馆人数可能不同），
 * 后续 m 天每天均会根据赛事热度进行志愿者人数调配。调配方案分为如下三种：
 *
 * 1. 将编号为 idx 的场馆内的志愿者人数减半；
 * 2. 将编号为 idx 的场馆相邻的场馆的志愿者人数都加上编号为 idx 的场馆的志愿者人数；
 * 3. 将编号为 idx 的场馆相邻的场馆的志愿者人数都减去编号为 idx 的场馆的志愿者人数。
 *
 * 所有的调配信息记录于数组 plans 中，plans[i] = [num,idx] 表示第 i 天对编号 idx 的场馆执行了第 num 种调配方案。
 * 在比赛结束后对调配方案进行复盘时，不慎将第 0 个场馆的最终志愿者人数丢失，只保留了初始所有场馆的志愿者总人数 totalNum ，
 * 以及记录了第 1 ~ n-1 个场馆的最终志愿者人数的一维数组 finalCnt。请你根据现有的信息求出初始每个场馆的志愿者人数，
 * 并按场馆编号顺序返回志愿者人数列表。
 *
 * 注意：
 *
 * 测试数据保证当某场馆进行第一种调配时，该场馆的志愿者人数一定为偶数；
 * 测试数据保证当某场馆进行第三种调配时，该场馆的相邻场馆志愿者人数不为负数；
 * 测试数据保证比赛开始时每个场馆的志愿者人数都不超过 10^9；
 * 测试数据保证给定的场馆间的道路分布情况中不会出现自环、重边的情况。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int[] volunteerDeployment(int[] finalCnt, long totalNum, int[][] edges, int[][] plans) {
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        for (var edge : edges) {
            neighbors.computeIfAbsent(edge[0], v -> new HashSet<>()).add(edge[1]);
            neighbors.computeIfAbsent(edge[1], v -> new HashSet<>()).add(edge[0]);
        }

        // [factor, constant]
        long[][] states = new long[finalCnt.length + 1][2]; states[0][0] = 1;
        for (int i = 0; i < finalCnt.length; i++) states[i + 1][1] = finalCnt[i];

        for (int i = plans.length - 1; i >= 0; i--) {
            int idx = plans[i][1];
            switch (plans[i][0]) {
                case 1 -> {
                    states[idx][0] *= 2;
                    states[idx][1] *= 2;
                }
                case 2 -> {
                    if (neighbors.containsKey(idx)) {
                        long f = states[idx][0], c = states[idx][1];
                        for (var neighbor : neighbors.get(idx)) {
                            states[neighbor][0] -= f;
                            states[neighbor][1] -= c;
                        }
                    }
                }
                case 3 -> {
                    if (neighbors.containsKey(idx)) {
                        long f = states[idx][0], c = states[idx][1];
                        for (var neighbor : neighbors.get(idx)) {
                            states[neighbor][0] += f;
                            states[neighbor][1] += c;
                        }
                    }
                }
            }
        }

        long factor = 0, constant = 0;
        for (var state : states) {
            factor += state[0];
            constant += state[1];
        }

        // aX + b = totalNum
        long x = (totalNum - constant) / factor;
        int[] ans = new int[states.length];
        for (int i = 0; i < states.length; i++) {
            ans[i] = (int) (states[i][0] * x + states[i][1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().volunteerDeployment(
            new int[]{1,16}, 21, new int[][]{{0, 1}, {1, 2}},
            new int[][]{{2, 1}, {1, 0}, {3, 0}}
        ), new int[]{5, 7, 9});
    }

}
