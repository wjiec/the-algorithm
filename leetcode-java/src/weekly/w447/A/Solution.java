package weekly.w447.A;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 3531. Count Covered Buildings
 *
 * https://leetcode.cn/contest/weekly-contest-447/problems/count-covered-buildings/
 *
 * You are given a positive integer n, representing an n x n city. You are also given a 2D grid
 * buildings, where buildings[i] = [x, y] denotes a unique building located at coordinates [x, y].
 *
 * A building is covered if there is at least one building in all four directions: left, right, above, and below.
 *
 * Return the number of covered buildings.
 */

public class Solution {

    public int countCoveredBuildings(int n, int[][] buildings) {
        TreeMap<Integer, TreeSet<Integer>> x = new TreeMap<>();
        TreeMap<Integer, TreeSet<Integer>> y = new TreeMap<>();
        for (var building : buildings) {
            x.computeIfAbsent(building[0], k -> new TreeSet<>()).add(building[1]);
            y.computeIfAbsent(building[1], k -> new TreeSet<>()).add(building[0]);
        }

        int ans = 0;
        for (var building : buildings) {
            int xi = building[0], yi = building[1];
            if (x.get(xi).higher(yi) != null && x.get(xi).lower(yi) != null && y.get(yi).higher(xi) != null && y.get(yi).lower(xi) != null) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
