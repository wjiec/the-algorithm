package problem.p1203sortitemsbygroupsrespectingdependencies;

import ability.Graph.TopologicalSort;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.List;

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
        TopologicalSort sort = new TopologicalSort(n);
        for (int i = 0; i < beforeItems.size(); i++) {
            for (var before : beforeItems.get(i)) {
                sort.addTask(i, before);
            }
        }

        int[] order = sort.sort();
        if (order.length == 0) return new int[0];

        List<Integer>[] groups = new List[m];
        for (int i = 0; i < m; i++) groups[i] = new ArrayList<>();

        List<Integer> ungroups = new ArrayList<>();
        for (var idx : order) {
            if (group[idx] == -1) ungroups.add(idx);
            else groups[group[idx]].add(idx);
        }

        int i = 0;
        int[] ans = new int[n];
        boolean[] removed = new boolean[n];
        boolean[] visited = new boolean[m];
        for (var idx : order) {
            int g = group[idx];
            if (g == -1) {
                ans[i++] = idx;
                removed[idx] = true;
            } else if (!visited[g]) {
                visited[g] = true;
                for (var x : groups[g]) {
                    ans[i++] = x;
                }
            }
        }
        for (var v : ungroups) {
            if (!removed[v]) {
                ans[i++] = v;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().sortItems(8, 2, new int[]{-1,-1,1,0,0,1,0,-1},
            List.of(List.of(), List.of(6), List.of(5), List.of(6), List.of(3,6), List.of(), List.of(), List.of())));
    }

}
