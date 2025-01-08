package weekly.bw147.A;

import java.util.TreeSet;

/**
 * 3407. Substring Matching Pattern
 *
 * https://leetcode.cn/contest/biweekly-contest-147/problems/substring-matching-pattern/
 *
 * You are given a string s and a pattern string p, where p contains exactly one '*' character.
 *
 * The '*' in p can be replaced with any sequence of zero or more characters.
 *
 * Return true if p can be made a substring of s, and false otherwise.
 */

public class Solution {

    public boolean hasMatch(String s, String p) {
        var parts = p.split("\\*");
        if (parts.length == 0) return true;
        if (parts.length == 1) return s.contains(parts[0]);

        TreeSet<Integer> suf = indexOf(s, parts[1]);
        for (var pre : indexOf(s, parts[0])) {
            if (suf.ceiling(pre + parts[0].length()) != null) return true;
        }

        return false;
    }

    private TreeSet<Integer> indexOf(String s, String pattern) {
        TreeSet<Integer> ans = new TreeSet<>();
        for (int i = 0; i < s.length(); i++) {
            var found = s.indexOf(pattern, i);
            if (found >= 0) ans.add(found);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().hasMatch("kvb", "k*v");
        assert new Solution().hasMatch("l", "*");
        assert new Solution().hasMatch("leetcode", "ee*e");
        assert !new Solution().hasMatch("car", "c*v");
        assert new Solution().hasMatch("luck", "u*");
    }

}
