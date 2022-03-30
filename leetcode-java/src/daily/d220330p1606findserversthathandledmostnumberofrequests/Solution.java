package daily.d220330p1606findserversthathandledmostnumberofrequests;

import common.Checker;

import java.util.*;

/**
 * 1606. Find Servers That Handled Most Number of Requests
 *
 * https://leetcode-cn.com/problems/find-servers-that-handled-most-number-of-requests/
 *
 * You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously.
 * Each server has infinite computational capacity but cannot handle more than one request at a time.
 * The requests are assigned to servers according to a specific algorithm:
 *
 * The ith (0-indexed) request arrives.
 * If all servers are busy, the request is dropped (not handled at all).
 * If the (i % k)th server is available, assign the request to that server.
 * Otherwise, assign the request to the next available server (wrapping around the list of servers
 * and starting from 0 if necessary). For example, if the ith server is busy, try to assign the request
 * to the (i+1)th server, then the (i+2)th server, and so on.
 * You are given a strictly increasing array arrival of positive integers, where arrival[i] represents
 * the arrival time of the ith request, and another array load, where load[i] represents the load of the ith
 * request (the time it takes to complete). Your goal is to find the busiest server(s).
 * A server is considered busiest if it handled the most number of requests successfully among all the servers.
 *
 * Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.
 */

public class Solution {

    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> idle = new TreeSet<>();
        for (int i = 0; i < k; i++) idle.add(i);

        int[] requests = new int[k];
        PriorityQueue<int[]> busy = new PriorityQueue<>(Comparator.comparingInt(v -> v[1]));
        for (int i = 0; i < arrival.length; i++) {
            while (!busy.isEmpty() && busy.peek()[1] <= arrival[i]) {
                idle.add(busy.remove()[0]);
            }

            if (!idle.isEmpty()) {
                Integer idx = idle.ceiling(i % k);
                if (idx == null) {
                    idx = idle.first();
                }

                requests[idx]++;
                busy.add(new int[]{idx, arrival[i] + load[i]});
                idle.remove(idx);
            }
        }

        int max = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requests[i] >= max) {
                if (requests[i] > max) {
                    ans.clear();
                    max = requests[i];
                }
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().busiestServers(
            3, new int[]{1,2,3,4,5}, new int[]{5,2,3,3,3}
        ), List.of(1));

        assert Checker.anyOrder(new Solution().busiestServers(
            3, new int[]{1,2,3,4}, new int[]{1,2,1,2}
        ), List.of(0));

        assert Checker.anyOrder(new Solution().busiestServers(
            3, new int[]{1,2,3}, new int[]{10,12,11}
        ), List.of(0, 1, 2));

        assert Checker.anyOrder(new Solution().busiestServers(
            3, new int[]{1,2,3,4,8,9,10}, new int[]{5,2,10,3,1,2,2}
        ), List.of(1));

        assert Checker.anyOrder(new Solution().busiestServers(
            1, new int[]{1}, new int[]{1}
        ), List.of(0));
    }

}
