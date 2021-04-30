package daily.d210420p28implementstrstr;

/**
 * 28. Implement strStr()
 *
 * https://leetcode-cn.com/problems/implement-strstr/
 *
 * Implement strStr().
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * Clarification:
 *
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * For the purpose of this problem, we will return 0 when needle is an empty string.
 * This is consistent to C's strstr() and Java's indexOf().
 */

class Solution {

    public int strStr(String haystack, String needle) {
        if (needle.isBlank()) {
            return 0;
        }

        int offset = 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(offset)) {
                offset += 1;
                if (offset == needle.length()) {
                    return i - offset + 1;
                }
            } else if (offset != 0) {
                i -= offset;
                offset = 0;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().strStr("", "") == 0;
        assert new Solution().strStr("hello", "ll") == 2;
        assert new Solution().strStr("aaaaa", "bb") == -1;
        assert new Solution().strStr("qqaskjdh", "jd") == 5;
        assert new Solution().strStr("mississippi", "issip") == 4;
    }

}
