package problem.p1961checkifstringisaprefixofarray;

/**
 * 1961. Check If String Is a Prefix of Array
 *
 * https://leetcode-cn.com/problems/check-if-string-is-a-prefix-of-array/
 *
 * Given a string s and an array of strings words, determine whether s is a prefix string of words.
 *
 * A string s is a prefix string of words if s can be made by concatenating the first k strings
 * in words for some positive k no larger than words.length.
 *
 * Return true if s is a prefix string of words, or false otherwise.
 */

public class Solution {

    public boolean isPrefixString(String s, String[] words) {
        int x = 0, l = s.length(), y = 0;
        for (var word : words) {
            for (y = 0; x < l && y < word.length(); x++, y++) {
                if (s.charAt(x) != word.charAt(y)) return false;
            }
            if (y != 0 && y != word.length()) return false;
        }
        return x == l;
    }

    public static void main(String[] args) {
        assert !new Solution().isPrefixString("ccccccccc", new String[]{"c","cc"});
        assert !new Solution().isPrefixString("a", new String[]{"aa","aaaa","banana"});

        assert new Solution().isPrefixString("iloveleetcode", new String[]{"i","love","leetcode","apples"});
        assert !new Solution().isPrefixString("iloveleetcode", new String[]{"apples","i","love","leetcode"});
    }

}
