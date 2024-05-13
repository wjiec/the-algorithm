package weekly.bw130.B;

import java.util.*;

/**
 * 100302. Maximum Points Inside the Square
 *
 * https://leetcode.cn/contest/biweekly-contest-130/problems/maximum-points-inside-the-square/
 *
 * You are given a 2D array points and a string s where, points[i] represents
 * the coordinates of point i, and s[i] represents the tag of point i.
 *
 * A valid square is a square centered at the origin (0, 0), has edges
 * parallel to the axes, and does not contain two points with the same tag.
 *
 * Return the maximum number of points contained in a valid square.
 *
 * Note:
 *
 * A point is considered to be inside the square if it lies on or within the square's boundaries.
 * The side length of the square can be zero.
 */

public class Solution {

    public int maxPointsInsideSquare(int[][] points, String s) {
        TreeMap<Integer, Map<Character, Integer>> m = new TreeMap<>();
        for (int i = 0; i < points.length; i++) {
            char label = s.charAt(i);
            m.computeIfAbsent(Math.max(Math.abs(points[i][0]), Math.abs(points[i][1])), k -> new HashMap<>()).merge(label, 1, Integer::sum);
        }

        System.out.println(m);

        int ans = 0;
        Set<Character> seen = new HashSet<>();
        for (var v : m.values()) {
            boolean ok = true;
            for (var cn : v.entrySet()) {
                if (cn.getValue() > 1 || !seen.add(cn.getKey())) {
                    ok = false; break;
                }
            }
            if (!ok) break;
            ans += v.size();
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
