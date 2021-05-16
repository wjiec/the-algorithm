package problem.p242validanagram;

/**
 * 242. Valid Anagram
 *
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 */

public class Solution {

    public boolean isAnagram(String s, String t) {
        int sz = s.length(), tz = t.length();
        if (sz != tz) {
            return false;
        }

        int[] letters = new int[26];
        for (int i = 0; i < sz; i++) {
            letters[s.charAt(i) - 'a']++;
            letters[t.charAt(i) - 'a']--;
        }

        for (int letter : letters) {
            if (letter != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isAnagram("anagram", "nagaram");
        assert !new Solution().isAnagram("rat", "car");
        assert !new Solution().isAnagram("a", "ab");
        assert !new Solution().isAnagram("aa", "bb");
        assert !new Solution().isAnagram("ac", "bb");
    }

}
