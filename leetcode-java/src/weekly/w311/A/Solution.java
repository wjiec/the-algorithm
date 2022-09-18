package weekly.w311.A;

/**
 * 6180. Smallest Even Multiple
 *
 * https://leetcode.cn/contest/weekly-contest-311/problems/smallest-even-multiple/
 *
 * Given a positive integer n, return the smallest positive integer that is a multiple of both 2 and n.
 */

public class Solution {

    public int smallestEvenMultiple(int n) {
        return n % 2 == 0 ? n : 2 * n;
    }

    public static void main(String[] args) {
    }

}
