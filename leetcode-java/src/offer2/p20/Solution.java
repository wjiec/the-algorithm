package offer2.p20;

/**
 * 剑指 Offer II 020. 回文子字符串的个数
 *
 * https://leetcode.cn/problems/a7VOhD/
 *
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 *
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 */

public class Solution {

    public int countSubstrings(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; i++) {
            ans += count(chars, i, i, n);
            ans += count(chars, i - 1, i, n);
        }
        return ans;
    }

    private int count(char[] chars, int l, int r, int n) {
        int ans = 0;
        while (l >= 0 && r < n && chars[l] == chars[r]) {
            ans++; l--; r++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countSubstrings("abc") == 3;
        assert new Solution().countSubstrings("aaa") == 6;
    }

}
