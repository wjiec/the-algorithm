package daily.p210420.implementstrstr;

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