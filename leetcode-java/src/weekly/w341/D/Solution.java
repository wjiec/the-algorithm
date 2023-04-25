package weekly.w341.D;

import java.util.HashSet;
import java.util.Set;

/**
 * 2646. Minimize the Total Price of the Trips
 *
 * https://leetcode.cn/contest/weekly-contest-341/problems/minimize-the-total-price-of-the-trips/
 *
 * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1.
 * You are given the integer n and a 2D integer array edges of length n - 1, where
 * edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * Each node has an associated price. You are given an integer array price, where
 * price[i] is the price of the ith node.
 *
 * The price sum of a given path is the sum of the prices of all nodes lying on that path.
 *
 * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates
 * that you start the ith trip from the node starti and travel to the node endi by any path you like.
 *
 * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
 *
 * Return the minimum total price sum to perform all the given trips.
 */

@SuppressWarnings({"DuplicatedCode", "unchecked"})
public class Solution {

    private int[] price;
    private final int[] freq = new int[51];
    private final Set<Integer>[] map = new Set[51];

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        for (int i = 0; i < n; i++) map[i] = new HashSet<>();
        for (var edge : edges) {
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }
        for (var trip : trips) travel(trip[0], trip[1], -1);

        this.price = price;
        int[] ans = dfs(0, -1);
        return Math.min(ans[0], ans[1]);
    }

    private int[] dfs(int curr, int parent) {
        int val = price[curr] * freq[curr], half = val / 2;
        for (var next : map[curr]) {
            if (next != parent) {
                int[] find = dfs(next, curr);
                // 当前不变的情况下, 后续的可以变也可以不变
                val += Math.min(find[0], find[1]);
                half += find[0]; // 当前如果变的话, 只能取不变的
            }
        }
        return new int[]{val, half};
    }

    private boolean travel(int curr, int end, int parent) {
        if (curr == end) {
            freq[curr]++;
            return true;
        }

        for (var next : map[curr]) {
            if (next != parent && travel(next, end, curr)) {
                freq[curr]++;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().minimumTotalPrice(4,  new int[][]{{0,1},{1,2},{1,3}}, new int[]{2,2,10,6}, new int[][]{{0,3},{2,1},{2,3}}) == 23;
        assert new Solution().minimumTotalPrice(4,  new int[][]{{0,1}}, new int[]{2,2}, new int[][]{{0,0}}) == 1;
    }

}
