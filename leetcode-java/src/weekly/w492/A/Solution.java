package weekly.w492.A;

/**
 * Q1. Minimum Capacity Box
 *
 * https://leetcode.cn/contest/weekly-contest-492/problems/minimum-capacity-box/
 *
 * You are given an integer array capacity, where capacity[i] represents the
 * capacity of the ith box, and an integer itemSize representing the size of an item.
 *
 * The ith box can store the item if capacity[i] >= itemSize.
 *
 * Return an integer denoting the index of the box with the minimum capacity that can store the item.
 * If multiple such boxes exist, return the smallest index.
 *
 * If no box can store the item, return -1.
 */

public class Solution {

    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.length; i++) {
            if (capacity[i] >= itemSize && (ans == -1 || capacity[i] < capacity[ans])) ans = i;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
