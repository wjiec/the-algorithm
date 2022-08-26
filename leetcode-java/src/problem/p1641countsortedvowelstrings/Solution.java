package problem.p1641countsortedvowelstrings;

/**
 * 1641. Count Sorted Vowel Strings
 *
 * https://leetcode.cn/problems/count-sorted-vowel-strings/
 *
 * Given an integer n, return the number of strings of length n that consist
 * only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i] is the same
 * as or comes before s[i+1] in the alphabet.
 */

public class Solution {

    public int countVowelStrings(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 2; j <= n; j++) {
            u = a + e + i + o + u;
            o = a + e + i + o;
            i = a + e + i;
            e = a + e;
        }
        return a + e + i + o + u;
    }

    public static void main(String[] args) {
        assert new Solution().countVowelStrings(1) == 5;
        assert new Solution().countVowelStrings(2) == 15;
        assert new Solution().countVowelStrings(33) == 66045;
    }

}
