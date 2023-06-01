package weekly.w347.C;

/**
 * 2712. Minimum Cost to Make All Characters Equal
 *
 * https://leetcode.cn/contest/weekly-contest-347/problems/minimum-cost-to-make-all-characters-equal/
 *
 * You are given a 0-indexed binary string s of length n on which you can apply two types of operations:
 *
 * Choose an index i and invert all characters from index 0 to index i (both inclusive), with a cost of i + 1
 * Choose an index i and invert all characters from index i to index n - 1 (both inclusive), with a cost of n - i
 * Return the minimum cost to make all characters of the string equal.
 *
 * Invert a character means if its value is '0' it becomes '1' and vice-versa.
 */

public class Solution {

    public long minimumCost(String s) {
        long ans = 0, n = s.length();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(i, n - i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
