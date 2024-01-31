package problem.p2076processrestrictedfriendrequests;

import java.util.Arrays;

/**
 * 2076. Process Restricted Friend Requests
 *
 * https://leetcode.cn/problems/process-restricted-friend-requests/
 *
 * You are given an integer n indicating the number of people in a network.
 * Each person is labeled from 0 to n - 1.
 *
 * You are also given a 0-indexed 2D integer array restrictions, where
 * restrictions[i] = [xi, yi] means that person xi and person yi cannot
 * become friends, either directly or indirectly through other people.
 *
 * Initially, no one is friends with each other. You are given a list of friend
 * requests as a 0-indexed 2D integer array requests, where requests[j] = [uj, vj]
 * is a friend request between person uj and person vj.
 *
 * A friend request is successful if uj and vj can be friends. Each friend request
 * is processed in the given order (i.e., requests[j] occurs before requests[j + 1]),
 * and upon a successful request, uj and vj become direct friends for all future friend requests.
 *
 * Return a boolean array result, where each result[j] is true if the jth friend
 * request is successful or false if it is not.
 *
 * Note: If uj and vj are already direct friends, the request is still successful.
 */

public class Solution {

    private static class UnionFind {
        private final int[] parent;
        public UnionFind(int n) { parent = new int[n]; Arrays.setAll(parent, i -> i); }
        private int find(int v) { return v == parent[v] ? v : (parent[v] = find(parent[v])); }
        private void union(int x, int y) { parent[find(x)] = find(y); }
    }

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        boolean[] ans = new boolean[requests.length];
        Arrays.fill(ans, true);

        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < requests.length; i++) {
            int a = requests[i][0], b = requests[i][1];
            int pa = uf.find(a), pb = uf.find(b); // parent[pa] = pb;
            if (pa == pb) continue;

            for (var r : restrictions) {
                int p0 = uf.find(r[0]), p1 = uf.find(r[1]);
                if ((p0 == pa || p0 == pb) && (p1 == pa || p1 == pb)) {
                    ans[i] = false; break;
                }
            }

            if (ans[i]) uf.union(a, b);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
