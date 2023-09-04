package weekly.bw112.B;

/**
 * 2840. Check if Strings Can be Made Equal With Operations II
 *
 * https://leetcode.cn/contest/biweekly-contest-112/problems/check-if-strings-can-be-made-equal-with-operations-ii/
 *
 * You are given two strings s1 and s2, both of length n, consisting of lowercase English letters.
 *
 * You can apply the following operation on any of the two strings any number of times:
 *
 * Choose any two indices i and j such that i < j and the difference j - i is even, then
 * swap the two characters at those indices in the string.
 *
 * Return true if you can make the strings s1 and s2 equal, and false otherwise.
 */

public class Solution {

    public boolean checkStrings(String s1, String s2) {
        int[] even = new int[128], odd = new int[128];
        for (int i = 0; i < s1.length(); i++) {
            if (i % 2 == 0) {
                even[s1.charAt(i)]++;
                even[s2.charAt(i)]--;
            } else {
                odd[s1.charAt(i)]++;
                odd[s2.charAt(i)]--;
            }
        }

        for (var n : odd) if (n != 0) return false;
        for (var n : even) if (n != 0) return false;
        return true;
    }

    public static void main(String[] args) {
    }

}
