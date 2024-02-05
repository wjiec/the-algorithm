package weekly.bw123.A;

/**
 * 3024. Type of Triangle II
 *
 * https://leetcode.cn/contest/biweekly-contest-123/problems/type-of-triangle-ii/
 *
 * You are given a 0-indexed integer array nums of size 3 which can form the sides of a triangle.
 *
 * A triangle is called equilateral if it has all sides of equal length.
 * A triangle is called isosceles if it has exactly two sides of equal length.
 * A triangle is called scalene if all its sides are of different lengths.
 *
 * Return a string representing the type of triangle that can be formed or "none" if it cannot form a triangle.
 */

public class Solution {

    public String triangleType(int[] nums) {
        int a = nums[0], b = nums[1], c = nums[2];
        int mi = Math.min(a, Math.min(b, c));
        int mx = Math.max(a, Math.max(b, c));
        int md = (a + b + c) - mi - mx;
        if (mi + md <= mx) return "none";

        if (a == b && b == c) return "equilateral";
        if (a == b || b == c || c == a) return "isosceles";
        return "scalene";
    }

    public static void main(String[] args) {
    }

}
