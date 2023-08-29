package weekly.w360.A;

/**
 * 2833. Furthest Point From Origin
 *
 * https://leetcode.cn/contest/weekly-contest-360/problems/furthest-point-from-origin/
 *
 * You are given a string moves of length n consisting only of characters 'L', 'R', and '_'.
 * The string represents your movement on a number line starting from the origin 0.
 *
 * In the ith move, you can choose one of the following directions:
 *
 * move to the left if moves[i] = 'L' or moves[i] = '_'
 * move to the right if moves[i] = 'R' or moves[i] = '_'
 *
 * Return the distance from the origin of the furthest point you can get to after n moves.
 */

public class Solution {

    public int furthestDistanceFromOrigin(String moves) {
        int left = 0, right = 0;
        for (var c : moves.toCharArray()) {
            switch (c) {
                case 'L' -> { left--; right--; }
                case 'R' -> { left++; right++; }
                default -> { left--; right++; }
            }
        }
        return Math.max(Math.abs(left), Math.abs(right));
    }

    public static void main(String[] args) {
    }

}
