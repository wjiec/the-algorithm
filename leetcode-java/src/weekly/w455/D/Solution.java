package weekly.w455.D;

import ability.Benchmark;
import common.Checker;

import java.util.*;

/**
 * Q4. Minimum Time to Transport All Individuals
 *
 * https://leetcode.cn/contest/weekly-contest-455/problems/minimum-time-to-transport-all-individuals/
 *
 * You are given n individuals at a base camp who need to cross a river
 * to reach a destination using a single boat. The boat can carry at
 * most k people at a time. The trip is affected by environmental
 * conditions that vary cyclically over m stages.
 *
 * Each stage j has a speed multiplier mul[j]:
 *
 * If mul[j] > 1, the trip slows down.
 * If mul[j] < 1, the trip speeds up.
 * Each individual i has a rowing strength represented by time[i], the time (in minutes) it
 * takes them to cross alone in neutral conditions.
 *
 * Rules:
 *
 * A group g departing at stage j takes time equal to the maximum time[i] among its members,
 * multiplied by mul[j] minutes to reach the destination.
 *
 * After the group crosses the river in time d, the stage advances by floor(d) % m steps.
 *
 * If individuals are left behind, one person must return with the boat. Let r be the
 * index of the returning person, the return takes time[r] × mul[current_stage],
 * defined as return_time, and the stage advances by floor(return_time) % m.
 *
 * Return the minimum total time required to transport all individuals.
 * If it is not possible to transport all individuals to the destination, return -1.
 */

public class Solution {

    public double minTime(int n, int k, int m, int[] time, double[] mul) {
        // 如果一个船只能带一个人, 但是有超过 1 人需要过河是不可能的
        if (k == 1 && n > 1) return -1;
        // 如果只有一个人的话, 那就直接过去
        if (n == 1) return time[0] * mul[0];

        // 渡河规则:
        //  - 一次只能坐 k 个人过去, 每次过去所需的时间是 t1 = max(time[i]) * mul[j], j 会前进 t1 步
        //  - 如果需要返回, 则需要一个人带着船返回, 所需时间是 t2 = time[i] * mul[j], j 会前进 t2 步
        //  - 求所有人过河的最短时间

        int MAX_N = 1 << n, MASK = MAX_N - 1;
        // 预处理所有可能的选择方式的最大值
        int[] maxTime = new int[MAX_N];
        int[] bitCount = new int[MAX_N];
        Queue<Integer> q = new ArrayDeque<>(); q.add(0);
        while (!q.isEmpty()) {
            for (int i = 0, l = q.size(); i < l; i++) {
                int curr = q.remove();
                for (int j = 0; j < n; j++) {
                    int next = curr | (1 << j);
                    if (maxTime[next] == 0) {
                        bitCount[next] = Integer.bitCount(next);
                        maxTime[next] = Math.max(maxTime[curr], time[j]);
                        q.add(next);
                    }
                }
            }
        }

        double ans = Double.MAX_VALUE;
        record Step(int mask, int stage, double ans) {}
        Queue<Step> q1 = new ArrayDeque<>();
        q1.add(new Step(MASK, 0, 0));
        Map<Integer, Double> seen = new HashMap<>();
        while (!q1.isEmpty()) {
            var curr = q1.remove();
            if (curr.mask == 0) { ans = Math.min(ans, curr.ans); continue; }
            if (curr.ans > ans) continue;

            // 找到至多为 k 个人开始过河
            for (int i = 1; i < MAX_N; i++) {
                // 一个人过去再一个人回来可能可以花费更低的总时间
                if (bitCount[i] <= k && (curr.mask & i) == i) {
                    // 过河时间
                    double d = maxTime[i] * mul[curr.stage];
                    if (curr.ans + d > ans) break;
                    // 如果没人留在营地了, 那么就可以直接过了
                    if ((curr.mask ^ i) == 0) {
                        q1.add(new Step(0, 0, curr.ans + d));
                        break;
                    }

                    // 否则还需要找一个人回来继续接人, 已经过去了 ~curr.mask | i 个人, 从他们里找一个回去
                    int crosses = ((~curr.mask) | i) & MASK;
                    for (int j = 0; j < n; j++) {
                        if ((crosses & (1 << j)) != 0) {
                            // 计算返回的时间
                            int moveStage = (curr.stage + (int) (d)) % m;
                            double returnTime = time[j] * mul[moveStage];
                            // 现在还剩下的人是刚回来的 returnMember 和剩余的人
                            int newMask = (curr.mask ^ i) | (1 << j), newStage = (moveStage + (int) returnTime) % m;
                            int key = (newMask << 3) | newStage; double newAns = curr.ans + d + returnTime;
                            if (!seen.containsKey(key) || seen.get(key) > newAns) {
                                seen.put(key, newAns);
                                q1.add(new Step(newMask, newStage, newAns));
                            }
                        }
                    }
                }
            }
        }

        return ans;
    }

    private static class Optimization {
        public double minTime(int n, int k, int m, int[] time, double[] mul) {
            final int MAX_N = 1 << n;
            // 我们将 {stage, mask} 作为图中的一个节点, 从这个节点可以到达 {stage', mask'}
            //  - 此时这就构成了一个有向图, 此时可以通过 dijkstra 算法进行求解

            // 预处理每个子集的 time 最大值
            int[] maxTime = new int[MAX_N];
            for (int i = 0; i < n; i++) {
                int bitset = 1 << i, val = time[i];
                // 其实就是枚举在之前的所有情况下加上当前这一个人的情况
                //  - 1 [a, b, c, d] (a, b, c, d 表示由之前人组成的所有可能性)
                //    ^ curr
                for (int mask = 0; mask < bitset; mask++) {
                    maxTime[bitset | mask] = Math.max(maxTime[mask], val);
                    // 如果人数超过 k 了, 那么就是不允许的
                    if (Integer.bitCount(bitset | mask) > k) {
                        maxTime[bitset | mask] = Integer.MAX_VALUE;
                    }
                }
            }

            double[][] dis = new double[m][MAX_N];
            for (var row : dis) Arrays.fill(row, Double.MAX_VALUE);

            record Step(int stage, int mask, double dis) {}
            PriorityQueue<Step> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> v.dis));
            pq.add(new Step(0, MAX_N - 1, 0));

            while (!pq.isEmpty()) {
                var curr = pq.remove();
                if (curr.mask == 0) return curr.dis;
                if (curr.dis > dis[curr.stage][curr.mask]) continue;

                // 枚举接下来哪些人坐这个船过去(枚举 curr.mask 的子集)
                for (int subset = curr.mask; subset > 0; subset = (subset - 1) & curr.mask) {
                    if (maxTime[subset] == Integer.MAX_VALUE) continue;

                    // 这批人过河的代价以及过河之后新的阶段
                    double cost1 = maxTime[subset] * mul[curr.stage];
                    int stage1 = (curr.stage + (int) cost1) % m;

                    // 如果所有人都过河了, 那就不需要再找一个人回来了
                    if (subset == curr.mask) {
                        if (curr.dis + cost1 < dis[stage1][0]) {
                            dis[stage1][0] = curr.dis + cost1;
                            pq.add(new Step(stage1, 0, curr.dis + cost1));
                        }
                        continue;
                    }

                    // 所有人是 (MAX_N - 1), 当前人是 curr.mask, 过去的人是 subset
                    //  - 已经过去的人就是 ((MAX_N - 1) ^ curr.mask) | subset
                    for (int s = (MAX_N - 1) ^ curr.mask ^ subset, lb = 0; s > 0; s ^= lb) {
                        lb = s & -s; // 取到最小位置的 1

                        double cost2 = maxTime[lb] * mul[stage1];
                        int stage2 = (stage1 + (int) cost2) % m;
                        if (curr.dis + cost1 + cost2 < dis[stage2][curr.mask ^ subset ^ lb]) {
                            dis[stage2][curr.mask ^ subset ^ lb] = curr.dis + cost1 + cost2;
                            pq.add(new Step(stage2, curr.mask ^ subset ^ lb, curr.dis + cost1 + cost2));
                        }
                    }
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Optimization", () -> {
            assert Checker.check(new Optimization().minTime(10, 2, 3, new int[]{86,30,83,17,34,87,22,47,14,88}, new double[]{1.72,0.94,1.55}), 509.97000);
            assert Checker.check(new Optimization().minTime(3, 5, 5, new int[]{83,2,53}, new double[]{1.98,0.75,1.28,0.57,1.88}), 161.14);
            assert Checker.check(new Optimization().minTime(3, 2, 4, new int[]{57,80,46}, new double[]{1.37,1.81,0.52,1.66}), 240.53);
            assert new Optimization().minTime(1, 3, 4, new int[]{63}, new double[]{1.88,1.78,1.54,1.77}) == 118.44;
            assert new Optimization().minTime(1, 1, 2, new int[]{5}, new double[]{1.0,1.3}) == 5.0;
            assert new Optimization().minTime(3, 2, 3, new int[]{2,5,8}, new double[]{1.0,1.5,0.75}) == 14.5;
            assert new Optimization().minTime(2, 1, 2, new int[]{10,10}, new double[]{2.0,2.0}) == -1.0;
        });

        Benchmark.benchmark("", () -> {
            assert Checker.check(new Solution().minTime(10, 2, 3, new int[]{86,30,83,17,34,87,22,47,14,88}, new double[]{1.72,0.94,1.55}), 509.97000);
        });

        assert Checker.check(new Solution().minTime(3, 5, 5, new int[]{83,2,53}, new double[]{1.98,0.75,1.28,0.57,1.88}), 161.14);
        assert Checker.check(new Solution().minTime(3, 2, 4, new int[]{57,80,46}, new double[]{1.37,1.81,0.52,1.66}), 240.53);
        assert new Solution().minTime(1, 3, 4, new int[]{63}, new double[]{1.88,1.78,1.54,1.77}) == 118.44;
        assert new Solution().minTime(1, 1, 2, new int[]{5}, new double[]{1.0,1.3}) == 5.0;
        assert new Solution().minTime(3, 2, 3, new int[]{2,5,8}, new double[]{1.0,1.5,0.75}) == 14.5;
        assert new Solution().minTime(2, 1, 2, new int[]{10,10}, new double[]{2.0,2.0}) == -1.0;
    }

}
