package offer.p48zuichangbuhanzhongfuzifudezizifuchuanlcof;

/**
 * 剑指 Offer 48. 最长不含重复字符的子字符串
 *
 * https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/
 *
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int n = s.length(), ans = 0;

        boolean[] chars = new boolean[255];
        for (int l = 0, r = 0; l < n; l++) {
            if (l != 0) chars[s.charAt(l - 1)] = false;
            while (r < n && !chars[s.charAt(r)]) {
                chars[s.charAt(r)] = true;
                r++;
            }

            ans = Math.max(ans, r - l);
            if (r == n) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstring("") == 0;
        assert new Solution().lengthOfLongestSubstring("a") == 1;
        assert new Solution().lengthOfLongestSubstring("au") == 2;
        assert new Solution().lengthOfLongestSubstring("aab") == 2;
        assert new Solution().lengthOfLongestSubstring("abcabcbb") == 3;
        assert new Solution().lengthOfLongestSubstring("bbbbb") == 1;
        assert new Solution().lengthOfLongestSubstring("pwwkew") == 3;
    }

}
