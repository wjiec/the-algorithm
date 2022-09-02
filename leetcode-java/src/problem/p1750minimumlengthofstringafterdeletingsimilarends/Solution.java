package problem.p1750minimumlengthofstringafterdeletingsimilarends;

import common.Tag;

/**
 * 1750. Minimum Length of String After Deleting Similar Ends
 *
 * https://leetcode.cn/problems/minimum-length-of-string-after-deleting-similar-ends/
 *
 * Given a string s consisting only of characters 'a', 'b', and 'c'. You are asked to apply the
 * following algorithm on the string any number of times:
 *
 * Pick a non-empty prefix from the string s where all the characters in the prefix are equal.
 * Pick a non-empty suffix from the string s where all the characters in this suffix are equal.
 * The prefix and the suffix should not intersect at any index.
 * The characters from the prefix and suffix must be the same.
 * Delete both the prefix and the suffix.
 * Return the minimum length of s after performing the above operation any number of times (possibly zero times).
 */

public class Solution {

    @Tag({"字符串", "前后移除相同字符"})
    public int minimumLength(String s) {
        int l = 0, r = s.length() - 1;
        char[] chars = s.toCharArray();
        while (l < r) {
            if (chars[l] == chars[r]) {
                char c = chars[l];
                while (l <= r && chars[l] == c) l++;
                while (l <= r && chars[r] == c) r--;
            } else break;
        }
        return r - l + 1;
    }

    public static void main(String[] args) {
        assert new Solution().minimumLength("ca") == 2;
        assert new Solution().minimumLength("cabaabac") == 0;
        assert new Solution().minimumLength("aabccabba") == 3;
    }

}
