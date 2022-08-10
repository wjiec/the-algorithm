package problem.p1456maximumnumberofvowelsinasubstringofgivenlength;

/**
 * 1456. Maximum Number of Vowels in a Substring of Given Length
 *
 * https://leetcode.cn/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 *
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 */

public class Solution {

    public int maxVowels(String s, int k) {
        int ans = 0, n = s.length(), curr = 0;
        for (int l = 0, r = 0; r < n; r++) {
            curr += isVowel(s.charAt(r)) ? 1 : 0;
            if (r - l + 1 > k) {
                curr -= isVowel(s.charAt(l++)) ? 1 : 0;
            }

            if (curr > ans) ans = curr;
            if (ans == k) break;
        }
        return ans;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        assert new Solution().maxVowels("abciiidef", 3) == 3;
        assert new Solution().maxVowels("aeiou", 2) == 2;
        assert new Solution().maxVowels("leetcode", 3) == 2;
        assert new Solution().maxVowels("rhythms", 4) == 0;
        assert new Solution().maxVowels("tryhard", 4) == 1;
    }

}
