package weekly.w396.C;

import java.util.Arrays;

/**
 * 3138. Minimum Length of Anagram Concatenation
 *
 * https://leetcode.cn/contest/weekly-contest-396/problems/minimum-length-of-anagram-concatenation/
 *
 * You are given a string s, which is known to be a concatenation of anagrams of some string t.
 *
 * Return the minimum possible length of the string t.
 *
 * An anagram is formed by rearranging the letters of a string.
 *
 * For example, "aab", "aba", and, "baa" are anagrams of "aab".
 */

public class Solution {

    public int minAnagramLength(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int[] freq = new int[128];
        for (var c : chars) freq[c]++;

        int maxFreq = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            maxFreq = Math.max(maxFreq, freq[c]);
        }

        int ans = n;
        for (int i = 1; i <= maxFreq; i++) {
            if (n % i == 0) {
                if (check(chars, i)) return i;
                if (i != 1 && n / i > i && check(chars, n / i)) {
                    ans = Math.min(ans, n / i);
                }
            }
        }
        return ans;
    }

    private boolean check(char[] chars, int gl) {
        int[] ref = new int[128];
        for (int i = 0; i < gl; i++) ref[chars[i]]++;

        for (int i = gl; i < chars.length; i += gl) {
            int[] curr = new int[128];
            for (int j = 0; j < gl; j++) curr[chars[i + j]]++;
            if (!Arrays.equals(curr, ref)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minAnagramLength("lbuorourlb") == 5;
        assert new Solution().minAnagramLength("abba") == 2;
    }

}
