package weekly.bw113.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2857. Count Pairs of Points With Distance k
 *
 * https://leetcode.cn/contest/biweekly-contest-113/problems/count-pairs-of-points-with-distance-k/
 *
 * You are given a 2D integer array coordinates and an integer k, where
 * coordinates[i] = [xi, yi] are the coordinates of the ith point in a 2D plane.
 *
 * We define the distance between two points (x1, y1) and (x2, y2) as (x1 XOR x2) + (y1 XOR y2)
 * where XOR is the bitwise XOR operation.
 *
 * Return the number of pairs (i, j) such that i < j and the distance between points i and j is equal to k.
 */

public class Solution {

    public int countPairs(List<List<Integer>> coordinates, int k) {
        int ans = 0;
        Map<Long, Integer> cnt = new HashMap<>();
        for (var coordinate : coordinates) {
            long x = coordinate.get(0), y = coordinate.get(1);
            for (int i = 0; i <= k; i++) {
                ans += cnt.getOrDefault(((x ^ i) << 32) | (y ^ (k - i)), 0);
            }
            cnt.merge((x << 32) | y, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
