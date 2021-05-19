package problem.p387firstuniquecharacterinastring;

/**
 * 387. First Unique Character in a String
 *
 * Given a string s, return the first non-repeating character in it and return its index.
 * If it does not exist, return -1.
 */

public class Solution {

    public int firstUniqChar(String s) {
        int[] chars = new int[26];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (chars[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().firstUniqChar("leetcode") == 0;
        assert new Solution().firstUniqChar("loveleetcode") == 2;
    }

}
