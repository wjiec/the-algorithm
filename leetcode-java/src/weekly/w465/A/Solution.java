package weekly.w465.A;

/**
 * Q1. Restore Finishing Order
 *
 * https://leetcode.cn/contest/weekly-contest-465/problems/restore-finishing-order/
 *
 * You are given an integer array order of length n and an integer array friends.
 *
 * order contains every integer from 1 to n exactly once, representing the IDs of the
 * participants of a race in their finishing order.
 *
 * friends contains the IDs of your friends in the race sorted in strictly increasing order.
 * Each ID in friends is guaranteed to appear in the order array.
 *
 * Return an array containing your friends' IDs in their finishing order.
 */

public class Solution {

    public int[] recoverOrder(int[] order, int[] friends) {
        boolean[] seen = new boolean[order.length + 1];
        for (var v : friends) seen[v] = true;

        int[] ans = new int[friends.length]; int i = 0;
        for (var v : order) {
            if (seen[v]) ans[i++] = v;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
