package weekly.w478.B;

/**
 * Q2. Maximum Substrings With Distinct Start
 *
 * https://leetcode.cn/contest/weekly-contest-478/problems/maximum-substrings-with-distinct-start/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * Return an integer denoting the maximum number of substrings you can split s
 * into such that each substring starts with a distinct character (i.e., no two
 * substrings start with the same character).
 */

public class Solution {

    public int maxDistinct(String s) {
        // 也就是找到不同字母的数量
        boolean[] uniq = new boolean[128]; int ans = 0;
        for (var c : s.toCharArray()) {
            if (!uniq[c]) { ans++; uniq[c] = true; }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
