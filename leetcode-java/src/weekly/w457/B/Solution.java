package weekly.w457.B;

import ability.Ability.UnionFind;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * Q2. Power Grid Maintenance
 *
 * https://leetcode.cn/contest/weekly-contest-457/problems/power-grid-maintenance/
 *
 * You are given an integer c representing c power stations, each with a unique
 * identifier id from 1 to c (1‑based indexing).
 *
 * These stations are interconnected via n bidirectional cables, represented by a 2D
 * array connections, where each element connections[i] = [ui, vi] indicates a connection
 * between station ui and station vi.
 *
 * Stations that are directly or indirectly connected form a power grid.
 *
 * Initially, all stations are online (operational).
 *
 * You are also given a 2D array queries, where each query is one of the following two types:
 *
 * [1, x]: A maintenance check is requested for station x. If station x is online, it resolves
 * the check by itself. If station x is offline, the check is resolved by the operational station
 * with the smallest id in the same power grid as x. If no operational station exists in that grid, return -1.
 *
 * [2, x]: Station x goes offline (i.e., it becomes non-operational).
 *
 * Return an array of integers representing the results of each query of type [1, x] in the order they appear.
 *
 * Note: The power grid preserves its structure; an offline (non‑operational) node remains
 * part of its grid and taking it offline does not alter connectivity.
 *
 * ©leetcode
 */

public class Solution {

    /** @noinspection unchecked*/
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        UnionFind uf = new UnionFind(c + 1);
        for (var conn : connections) uf.union(conn[0], conn[1]);

        TreeSet<Integer>[] g = new TreeSet[c + 1];
        for (int i = 1; i <= c; i++) {
            int p = uf.find(i);
            if (g[p] == null) g[p] = new TreeSet<>();
            g[p].add(i);
        }

        List<Integer> ans = new ArrayList<>();
        for (var query : queries) {
            int x = query[1], px = uf.find(x);
            switch (query[0]) {
                case 1 -> {
                    if (!g[px].contains(x)) {
                        Integer lower = g[px].isEmpty() ? null : g[px].first();
                        ans.add(lower == null ? -1 : lower);
                    } else ans.add(x);
                }
                case 2 -> g[px].remove(x);
            }
        }

        int[] copy = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) copy[i] = ans.get(i);
        return copy;
    }

    public static void main(String[] args) {
    }

}
