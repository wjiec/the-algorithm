package problem.p1941checkifallcharactershaveequalnumberofoccurrences;

/**
 * 1941. Check if All Characters Have Equal Number of Occurrences
 *
 * https://leetcode-cn.com/problems/check-if-all-characters-have-equal-number-of-occurrences/
 *
 * Given a string s, return true if s is a good string, or false otherwise.
 *
 * A string s is good if all the characters that appear in s have
 * the same number of occurrences (i.e., the same frequency).
 */

public class Solution {

    public boolean areOccurrencesEqual(String s) {
        if (s.length() == 0) return true;

        int[] map = new int[128];
        for (var c : s.toCharArray()) map[c]++;

        int idx = 0;
        while (map[idx] == 0) idx++;
        for (int value : map) if (value != 0 && value != map[idx]) return false;
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().areOccurrencesEqual("");
        assert new Solution().areOccurrencesEqual("aazz");
        assert new Solution().areOccurrencesEqual("abacbc");
        assert !new Solution().areOccurrencesEqual("aaabb");
    }

}
