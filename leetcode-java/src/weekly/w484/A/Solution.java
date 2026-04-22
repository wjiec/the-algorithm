package weekly.w484.A;

import java.util.HashSet;
import java.util.Set;

/**
 * Q1. Count Residue Prefixes
 *
 * https://leetcode.cn/contest/weekly-contest-484/problems/count-residue-prefixes/
 *
 * You are given a string s consisting only of lowercase English letters.
 *
 * A prefix of s is called a residue if the number of distinct characters in the prefix is equal to len(prefix) % 3.
 *
 * Return the count of residue prefixes in s.
 *
 * A prefix of a string is a non-empty substring that starts from the
 * beginning of the string and extends to any point within it.
 */

public class Solution {

    public int residuePrefixes(String s) {
        int ans = 0;
        Set<Character> uniq = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            uniq.add(s.charAt(i));
            if (uniq.size() == (i + 1) % 3) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
