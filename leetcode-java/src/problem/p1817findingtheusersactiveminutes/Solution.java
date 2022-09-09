package problem.p1817findingtheusersactiveminutes;

import common.Checker;

import java.util.*;

/**
 * 1817. Finding the Users Active Minutes
 *
 * https://leetcode.cn/problems/finding-the-users-active-minutes/
 *
 * You are given the logs for users' actions on LeetCode, and an integer k.
 * The logs are represented by a 2D integer array logs where each logs[i] = [IDi, timei]
 * indicates that the user with IDi performed an action at the minute timei.
 *
 * Multiple users can perform actions simultaneously, and a single user can perform
 * multiple actions in the same minute.
 *
 * The user active minutes (UAM) for a given user is defined as the number of unique
 * minutes in which the user performed an action on LeetCode.
 * A minute can only be counted once, even if multiple actions occur during it.
 *
 * You are to calculate a 1-indexed array answer of size k such that, for
 * each j (1 <= j <= k), answer[j] is the number of users whose UAM equals j.
 *
 * Return the array answer as described above.
 */

public class Solution {

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> uam = new HashMap<>();
        for (var log : logs) uam.computeIfAbsent(log[0], v -> new HashSet<>()).add(log[1]);

        int[] ans = new int[k];
        for (var u : uam.values()) ans[u.size() - 1]++;
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().findingUsersActiveMinutes(new int[][]{
            {0,5},{1,2},{0,2},{0,5},{1,3}
        }, 5), new int[]{0,2,0,0,0});

        assert Checker.check(new Solution().findingUsersActiveMinutes(new int[][]{
            {1,1},{2,2},{2,3}
        }, 4), new int[]{1,1,0,0});
    }

}
