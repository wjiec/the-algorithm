package weekly.bw174.A;

/**
 * Q1. Best Reachable Tower
 *
 * https://leetcode.cn/contest/biweekly-contest-174/problems/best-reachable-tower/
 *
 * You are given a 2D integer array towers, where towers[i] = [xi, yi, qi] represents
 * the coordinates (xi, yi) and quality factor qi of the ith tower.
 *
 * You are also given an integer array center = [cx, cy] representing your location, and an integer radius.
 *
 * A tower is reachable if its Manhattan distance from center is less than or equal to radius.
 *
 * Among all reachable towers:
 *
 * Return the coordinates of the tower with the maximum quality factor.
 * If there is a tie, return the tower with the lexicographically smallest coordinate.
 * If no tower is reachable, return [-1, -1].
 *
 * The Manhattan Distance between two cells (xi, yi) and (xj, yj) is |xi - xj| + |yi - yj|.
 * A coordinate [xi, yi] is lexicographically smaller than [xj, yj] if xi < xj, or xi == xj and yi < yj.
 *
 * |x| denotes the absolute value of x.
 */

public class Solution {

    public int[] bestTower(int[][] towers, int[] center, int radius) {
        int x = -1, y = -1, mass = -1;
        for (var tower : towers) {
            int d = Math.abs(tower[0] - center[0]) + Math.abs(tower[1] - center[1]);
            if (d <= radius) {
                if (tower[2] > mass || (tower[2] == mass && (tower[0] < x || (tower[0] == x && tower[1] < y)))) {
                    mass = tower[2]; x = tower[0]; y = tower[1];
                }
            }
        }
        return new int[]{x, y};
    }

    public static void main(String[] args) {
    }

}
