package weekly.w404.A;

/**
 * 3200. Maximum Height of a Triangle
 *
 * https://leetcode.cn/contest/weekly-contest-404/problems/maximum-height-of-a-triangle/
 *
 * You are given two integers red and blue representing the count of red and blue colored balls.
 * You have to arrange these balls to form a triangle such that the 1st row will have 1 ball,
 * the 2nd row will have 2 balls, the 3rd row will have 3 balls, and so on.
 *
 * All the balls in a particular row should be the same color, and adjacent rows should have different colors.
 *
 * Return the maximum height of the triangle that can be achieved.
 */

public class Solution {

    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(maxHeight(red, blue), maxHeight(blue, red));
    }

    private int maxHeight(int a, int b) {
        int ans = 0;
        while ((ans % 2 == 0) ? (a >= (ans + 1)) : (b >= (ans + 1))) {
            if (ans % 2 == 0) a -= ++ans;
            else b -= ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
