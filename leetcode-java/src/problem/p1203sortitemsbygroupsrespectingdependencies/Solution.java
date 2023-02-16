package problem.p1203sortitemsbygroupsrespectingdependencies;

import common.PrettyPrinter;

import java.util.*;

/**
 * 1203. Sort Items by Groups Respecting Dependencies
 *
 * https://leetcode.cn/problems/sort-items-by-groups-respecting-dependencies/
 *
 * There are n items each belonging to zero or one of m groups where group[i] is the
 * group that the i-th item belongs to and it's equal to -1 if the i-th item
 * belongs to no group.
 *
 * The items and the groups are zero indexed. A group can have no item belonging to it.
 *
 * Return a sorted list of the items such that:
 *
 * The items that belong to the same group are next to each other in the sorted list.
 * There are some relations between these items where beforeItems[i] is a list containing
 * all the items that should come before the i-th item in the sorted array
 * (to the left of the i-th item).
 *
 * Return any solution if there is more than one solution and
 * return an empty list if there is no solution.
 */

public class Solution {

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int[] degree = new int[n];
        Map<Integer, Set<Integer>> edges = new HashMap<>();
        for (int dst = 0; dst < beforeItems.size(); dst++) {
            degree[dst] += beforeItems.get(dst).size();
            for (var src : beforeItems.get(dst)) {
                edges.computeIfAbsent(src, v -> new HashSet<>()).add(dst);
            }
        }

        List<Integer>[] groups = new List[m];
        for (int i = 0; i < m; i++) groups[i] = new ArrayList<>();

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 0) queue.add(i);
        }

        List<Integer> sorts = new ArrayList<>(n);
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            int groupId = group[curr];

            sorts.add(curr);
            if (groupId != -1) groups[groupId].add(curr);

            if (edges.containsKey(curr)) {
                for (var next : edges.get(curr)) {
                    if (--degree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
        }
        System.out.println(sorts);
        if (sorts.size() != n) return new int[0];

        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            int curr = sorts.get(i);
            int groupId = group[curr];
            if (groupId == -1) ans[j++] = curr;
            else if (groups[groupId] != null) {
                for (var v : groups[groupId]) ans[j++] = v;
                groups[groupId] = null;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // [3,2,0,1,4]
        // g0 0 1 4
        // g1 3
        // g2 2
        PrettyPrinter.println(new Solution().sortItems(5, 3, new int[]{0,0,2,1,0},
            List.of(List.of(3), List.of(), List.of(), List.of(), List.of(1,3,2))));

        // [6,3,4,1,5,2,0,7]
        PrettyPrinter.println(new Solution().sortItems(8, 2, new int[]{-1,-1,1,0,0,1,0,-1},
            List.of(List.of(), List.of(6), List.of(5), List.of(6), List.of(3,6), List.of(), List.of(), List.of())));
    }

}
