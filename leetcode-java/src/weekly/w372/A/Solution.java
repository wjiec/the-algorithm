package weekly.w372.A;

/**
 * 2937. Make Three Strings Equal
 *
 * https://leetcode.cn/contest/weekly-contest-372/problems/make-three-strings-equal/
 *
 * You are given three strings s1, s2, and s3. You have to perform the
 * following operation on these three strings as many times as you want.
 *
 * In one operation you can choose one of these three strings such that its
 * length is at least 2 and delete the rightmost character of it.
 *
 * Return the minimum number of operations you need to perform to make the three
 * strings equal if there is a way to make them equal, otherwise, return -1.
 */

public class Solution {

    public int findMinimumOperations(String s1, String s2, String s3) {
        int n1 = s1.length(), n2 = s2.length(), n3 = s3.length();
        if (s1.charAt(0) != s2.charAt(0) || s2.charAt(0) != s3.charAt(0)) return -1;

        for (int i = 0; i < n1 && i < n2 && i < n3; i++) {
            char c1 = s1.charAt(i), c2 = s2.charAt(i), c3 = s3.charAt(i);
            if (c1 != c2 || c2 != c3) {
                return n1 + n2 + n3 - 3 * i;
            }
        }
        return n1 + n2 + n3 - 3 * Math.min(n1, Math.min(n2, n3));
    }

    public static void main(String[] args) {
    }

}
