package problem.p1376timeneededtoinformallemployees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1376. Time Needed to Inform All Employees
 *
 * https://leetcode.cn/problems/time-needed-to-inform-all-employees/
 *
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company is the one with headID.
 *
 * Each employee has one direct manager given in the manager array where manager[i] is
 * the direct manager of the i-th employee, manager[headID] = -1. Also, it is guaranteed
 * that the subordination relationships have a tree structure.
 *
 * The head of the company wants to inform all the company employees of an urgent piece of news.
 * He will inform his direct subordinates, and they will inform their subordinates, and so on
 * until all employees know about the urgent news.
 *
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 *
 * Return the number of minutes needed to inform all the employees about the urgent news.
 */

public class Solution {

    private int ans = 0;
    // [manager => employees]
    private final Map<Integer, Set<Integer>> map = new HashMap<>();

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        for (int i = 0; i < manager.length; i++) {
            map.computeIfAbsent(manager[i], v -> new HashSet<>())
                .add(i);
        }
        dfs(headID, informTime, 0);
        return ans;
    }

    private void dfs(int curr, int[] inform, int time) {
        if (time > ans) ans = time;
        if (map.containsKey(curr)) {
            for (var employee : map.get(curr)) {
                dfs(employee, inform, time + inform[curr]);
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().numOfMinutes(1, 0, new int[]{-1}, new int[]{0}) == 0;
        assert new Solution().numOfMinutes(6, 2, new int[]{2,2,-1,2,2,2}, new int[]{0,0,1,0,0,0}) == 1;
    }

}
