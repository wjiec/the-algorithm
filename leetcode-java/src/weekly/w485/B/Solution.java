package weekly.w485.B;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Q2. Maximum Capacity Within Budget
 *
 * https://leetcode.cn/contest/weekly-contest-485/problems/maximum-capacity-within-budget/
 *
 * You are given two integer arrays costs and capacity, both of length n,
 * where costs[i] represents the purchase cost of the ith machine and
 * capacity[i] represents its performance capacity.
 *
 * You are also given an integer budget.
 *
 * You may select at most two distinct machines such that the total cost of the
 * selected machines is strictly less than budget.
 *
 * Return the maximum achievable total capacity of the selected machines.
 */

public class Solution {

    public int maxCapacity(int[] costs, int[] capacity, int budget) {
        Integer[] sorted = IntStream.range(0, costs.length).boxed().toArray(Integer[]::new);
        Arrays.sort(sorted, Comparator.comparing(i -> costs[i]));

        int ans = 0;
        for (int i = 0, j = 1; j < sorted.length; i++, j++) {
            if (costs[sorted[i]] + costs[sorted[j]] < budget) {
                ans = Math.max(ans, capacity[sorted[i]] + capacity[sorted[j]]);
            }
        }

        TreeMap<Integer, Integer> m = new TreeMap<>(); m.put(0, 0);
        for (var i : sorted) {
            int cost = costs[i], cap = capacity[i];
            if (cost < budget) ans = Math.max(ans, cap);

            var found = m.lowerEntry(budget - cost);
            if (found != null) ans = Math.max(ans, cap + found.getValue());
            m.merge(cost, Math.max(cap, m.lastEntry().getValue()), Integer::max);
        }
        return ans;
    }

    private static class Optimization {
        public int maxCapacity(int[] costs, int[] capacity, int budget) {
            record Machine(int cost, int cap) {}
            Machine[] machines = new Machine[costs.length];
            for (int i = 0; i < costs.length; i++) {
                machines[i] = new Machine(costs[i], capacity[i]);
            }
            Arrays.sort(machines, Comparator.comparing(m -> m.cost));

            int ans = 0; Deque<Machine> mono = new ArrayDeque<>(); mono.push(new Machine(0, 0));
            for (var machine : machines) {
                // 如果一台机器都买不起了, 那么就直接跳出
                if (machine.cost >= budget) break;
                // 固定买的机器越来越贵之后, 我们可以选择的机器就越来越少
                while (mono.peek().cost + machine.cost >= budget) mono.pop();

                ans = Math.max(ans, mono.peek().cap + machine.cap);
                if (machine.cap > mono.peek().cap) mono.push(machine);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
    }

}
