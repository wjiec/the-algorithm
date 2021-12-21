package offer2.p18XltzEq;

/**
 * 剑指 Offer II 018. 有效的回文
 *
 * https://leetcode-cn.com/problems/XltzEq/
 *
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 本题中，将空字符串定义为有效的 回文串 。
 */

public class Solution {

    public boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            char a = normalize(s.charAt(l)), b = normalize(s.charAt(r));
            if (a == ' ') { l++; continue; }
            if (b == ' ') { r--; continue; }
            if (a != b) return false;
            l++; r--;
        }
        return true;
    }

    private char normalize(char c) {
        if ('0' <= c && c <= '9') return c;
        if ('a' <= c && c <= 'z') return c;
        if ('A' <= c && c <= 'Z') return (char) (c + 32);
        return ' ';
    }

    public static void main(String[] args) {
        assert new Solution().isPalindrome("A man, a plan, a canal: Panama");
        assert !new Solution().isPalindrome("race a car");
        assert new Solution().isPalindrome("abA");
    }

}
