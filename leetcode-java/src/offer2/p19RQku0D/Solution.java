package offer2.p19RQku0D;

/**
 * 剑指 Offer II 019. 最多删除一个字符得到回文
 *
 * https://leetcode-cn.com/problems/RQku0D/
 *
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 */

public class Solution {

    public boolean validPalindrome(String s) {
        return check(s.toCharArray(), 0, s.length() - 1, true);
    }

    private boolean check(char[] chars, int a, int b, boolean ok) {
        while (a < b) {
            if (chars[a] == chars[b]) {
                a++; b--;
                continue;
            }

            return ok && (check(chars, a + 1, b, false) || check(chars, a, b - 1, false));
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().validPalindrome("cbbcc");
        assert new Solution().validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga");

        assert new Solution().validPalindrome("aba");
        assert new Solution().validPalindrome("abca");
        assert !new Solution().validPalindrome("abc");
        assert !new Solution().validPalindrome("abbc");
        assert new Solution().validPalindrome("abba");
        assert new Solution().validPalindrome("abbba");
    }

}
