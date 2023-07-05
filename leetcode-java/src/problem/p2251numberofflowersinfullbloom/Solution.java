package problem.p2251numberofflowersinfullbloom;

import java.util.TreeMap;

/**
 * 2251. Number of Flowers in Full Bloom
 *
 * https://leetcode.cn/problems/number-of-flowers-in-full-bloom/
 *
 * You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means
 * the ith flower will be in full bloom from starti to endi (inclusive).
 *
 * You are also given a 0-indexed integer array people of size n, where
 * poeple[i] is the time that the ith person will arrive to see the flowers.
 *
 * Return an integer array answer of size n, where answer[i] is the number of
 * flowers that are in full bloom when the ith person arrives.
 */

public class Solution {

    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        TreeMap<Integer, Integer> map = new TreeMap<>(); map.put(0, 0);
        for (var flower : flowers) {
            map.merge(flower[0], 1, Integer::sum);
            map.merge(flower[1] + 1, -1, Integer::sum);
        }

        int curr = 0;
        for (var k : map.keySet()) {
            map.put(k, curr += map.get(k));
        }

        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            ans[i] = map.floorEntry(people[i]).getValue();
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
