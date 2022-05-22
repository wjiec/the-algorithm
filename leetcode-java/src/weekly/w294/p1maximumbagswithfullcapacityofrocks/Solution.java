package weekly.w294.p1maximumbagswithfullcapacityofrocks;

import java.util.PriorityQueue;

/**
 * 6075. Maximum Bags With Full Capacity of Rocks
 *
 * https://leetcode.cn/contest/weekly-contest-294/problems/maximum-bags-with-full-capacity-of-rocks/
 *
 * You have n bags numbered from 0 to n - 1. You are given two 0-indexed integer arrays capacity and rocks.
 * The ith bag can hold a maximum of capacity[i] rocks and currently contains rocks[i] rocks.
 * You are also given an integer additionalRocks, the number of additional rocks you can place in any of the bags.
 *
 * Return the maximum number of bags that could have full capacity after placing the additional rocks in some bags.
 */

public class Solution {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for (int i = 0; i < capacity.length; i++) {
            bags.add(capacity[i] - rocks[i]);
        }

        int ans = 0;
        while (!bags.isEmpty() && additionalRocks != 0) {
            int curr = bags.remove();
            if (additionalRocks >= curr) {
                ans++;
                additionalRocks -= curr;
            } else break;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
