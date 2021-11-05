package problem.p1684countthenumberofconsistentstrings;

import java.util.Arrays;

/**
 * 1684. Count the Number of Consistent Strings
 *
 * https://leetcode-cn.com/problems/count-the-number-of-consistent-strings/
 *
 * You are given a string allowed consisting of distinct characters and an array of strings words.
 *
 * A string is consistent if all characters in the string appear in the string allowed.
 *
 * Return the number of consistent strings in the array words.
 */

public class Solution {

    public int countConsistentStrings(String allowed, String[] words) {
        boolean[] chars = new boolean[256];
        for (var c : allowed.toCharArray()) chars[c] = true;

        return (int) Arrays.stream(words).filter(s -> {
            for (var c : s.toCharArray()) {
                if (!chars[c]) return false;
            }
            return true;
        }).count();
    }

    public static void main(String[] args) {
        assert new Solution().countConsistentStrings("ab", new String[]{"ad","bd","aaab","baa","badab"}) == 2;
        assert new Solution().countConsistentStrings("abc", new String[]{"a","b","c","ab","ac","bc","abc"}) == 7;
        assert new Solution().countConsistentStrings("cad", new String[]{"cc","acd","b","ba","bac","bad","ac","d"}) == 4;
    }

}
