package offer2.p16;

/**
 * 剑指 Offer II 016. 不含重复字符的最长子字符串
 *
 * https://leetcode.cn/problems/wtcaE1/
 *
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 */

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        boolean[] unique = new boolean[128];
        for (int l = 0, r = 0; r < n; r++) {
            while (unique[chars[r]]) unique[chars[l++]] = false;
            unique[chars[r]] = true;
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstring("abcabcbb") == 3;
        assert new Solution().lengthOfLongestSubstring("bbbbb") == 1;
        assert new Solution().lengthOfLongestSubstring("pwwkew") == 3;
        assert new Solution().lengthOfLongestSubstring("") == 0;
    }

}
