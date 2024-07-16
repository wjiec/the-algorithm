package problem.p2747countzerorequestservers;

import common.Checker;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 2747. Count Zero Request Servers
 *
 * https://leetcode.cn/problems/count-zero-request-servers/
 *
 * You are given an integer n denoting the total number of servers and a 2D 0-indexed
 * integer array logs, where logs[i] = [server_id, time] denotes that the server with
 * id server_id received a request at time time.
 *
 * You are also given an integer x and a 0-indexed integer array queries.
 *
 * Return a 0-indexed integer array arr of length queries.length where arr[i] represents
 * the number of servers that did not receive any requests during the time interval
 * [queries[i] - x, queries[i]].
 *
 * Note that the time intervals are inclusive.
 */

public class Solution {

    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparingInt(l -> l[1]));

        Integer[] sorted = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) sorted[i] = i;
        Arrays.sort(sorted, Comparator.comparingInt(i -> queries[i]));

        int ll = 0, lr = 0;
        int[] ans = new int[queries.length];
        Map<Integer, Integer> reqs = new HashMap<>();
        for (var idx : sorted) {
            int ql = queries[idx] - x, qr = queries[idx];
            while (lr < logs.length && logs[lr][1] <= qr) reqs.merge(logs[lr++][0], 1, Integer::sum);
            while (ll < lr && logs[ll][1] < ql) reqs.merge(logs[ll++][0], -1, Solution::sum);
            ans[idx] = n - reqs.size();
        }

        return ans;
    }

    private static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : (a + b); }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countServers(4, new int[][]{
            {4,3},{2,16},{1,21},{3,22},{1,13},{3,10},{2,1},{1,12},{4,13},{2,18}
        }, 8, new int[]{14,28,29}), new int[]{1,2,2});
    }

}
