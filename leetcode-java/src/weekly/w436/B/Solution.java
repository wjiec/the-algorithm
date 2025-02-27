package weekly.w436.B;

import ability.Benchmark;

import java.util.*;

/**
 * 3447. Assign Elements to Groups with Constraints
 *
 * https://leetcode.cn/contest/weekly-contest-436/problems/assign-elements-to-groups-with-constraints/
 *
 * You are given an integer array groups, where groups[i] represents the size of the ith group.
 * You are also given an integer array elements.
 *
 * Your task is to assign one element to each group based on the following rules:
 *
 * An element at index j can be assigned to a group i if groups[i] is divisible by elements[j].
 * If there are multiple elements that can be assigned, assign the element with the smallest index j.
 * If no element satisfies the condition for a group, assign -1 to that group.
 *
 * Return an integer array assigned, where assigned[i] is the index of the element
 * chosen for group i, or -1 if no suitable element exists.
 *
 * Note: An element may be assigned to more than one group.
 */

public class Solution {

    public int[] assignElements(int[] groups, int[] elements) {
        int maxValue = 0;
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < groups.length; i++) {
            maxValue = Math.max(maxValue, groups[i]);
            g.computeIfAbsent(groups[i], k -> new HashSet<>()).add(i);
        }

        Set<Integer> seen = new HashSet<>();
        int[] ans = new int[groups.length]; Arrays.fill(ans, -1);
        for (int i = 0; i < elements.length; i++) {
            if (seen.add(elements[i])) {
                for (int v = elements[i]; v <= maxValue; v += elements[i]) {
                    if (g.containsKey(v)) {
                        for (var idx : g.get(v)) ans[idx] = i;
                        g.remove(v);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] groups = new int[100000];
        for (int i = 0; i < groups.length; i++) groups[i] = i + 1;

        int[] elements = new int[100000];
        Arrays.fill(elements, 1);

        Benchmark.benchmark("", () -> {
            var ignored = new Solution().assignElements(groups, elements);
        });
    }

}
