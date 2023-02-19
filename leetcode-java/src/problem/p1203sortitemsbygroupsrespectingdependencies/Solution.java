package problem.p1203sortitemsbygroupsrespectingdependencies;

import ability.Graph;
import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        // 所有 -1 的组并不是同一个组
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        Graph.TopologicalSort items = new Graph.TopologicalSort(n);
        Graph.TopologicalSort groups = new Graph.TopologicalSort(m);
        for (int i = 0; i < beforeItems.size(); i++) {
            int currGroup = group[i];
            for (var before : beforeItems.get(i)) {
                items.addTask(i, before);
                int beforeGroup = group[before];
                if (beforeGroup != currGroup) {
                    groups.addTask(currGroup, beforeGroup);
                }
            }
        }

        int[] sortedItem = items.sort();
        if (sortedItem.length == 0) return new int[0];

        int[] sortedGroup = groups.sort();
        if (sortedGroup.length == 0) return new int[0];

        Map<Integer, List<Integer>> groupToItem = new HashMap<>();
        for (var item : sortedItem) {
            groupToItem.computeIfAbsent(group[item], v -> new ArrayList<>()).add(item);
        }

        int idx = 0;
        int[] ans = new int[n];
        for (var groupId : sortedGroup) {
            if (groupToItem.containsKey(groupId)) {
                for (var item : groupToItem.get(groupId)) {
                    ans[idx++] = item;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [3,2,0,1,4]
        // g0 0 1 4
        // g1 3
        // g2 2
        // 3 <- 0
        // 1 3 2 <- 4
        PrettyPrinter.println(new Solution().sortItems(5, 3, new int[]{0,0,2,1,0},
            List.of(List.of(3), List.of(), List.of(), List.of(), List.of(1,3,2))));

        // [6,3,4,1,5,2,0,7]
        PrettyPrinter.println(new Solution().sortItems(8, 2, new int[]{-1,-1,1,0,0,1,0,-1},
            List.of(List.of(), List.of(6), List.of(5), List.of(6), List.of(3,6), List.of(), List.of(), List.of())));
    }

}
