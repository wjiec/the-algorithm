package weekly.w397.A;

/**
 * 3146. Permutation Difference between Two Strings
 *
 * https://leetcode.cn/contest/weekly-contest-397/problems/permutation-difference-between-two-strings/
 *
 * You are given two strings s and t such that every character occurs
 * at most once in s and t is a permutation of s.
 *
 * The permutation difference between s and t is defined as the sum of
 * the absolute difference between the index of the occurrence of each
 * character in s and the index of the occurrence of the same character in t.
 *
 * Return the permutation difference between s and t.
 */

public class Solution {

    public int findPermutationDifference(String s, String t) {
        int[] index = new int[128];
        for (int i = 0; i < s.length(); i++) index[s.charAt(i)] = i;

        int ans = 0;
        for (int i = 0; i < t.length(); i++) {
            ans += Math.abs(index[t.charAt(i)] - i);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
