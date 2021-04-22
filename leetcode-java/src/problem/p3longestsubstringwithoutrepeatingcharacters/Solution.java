package problem.p3longestsubstringwithoutrepeatingcharacters;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string s, find the length of the longest substring without repeating characters.
 */

public class Solution {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }

        int max = 0, curr = 0;
        int[] chars = new int[255];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (chars[c] != 0) {
                if (curr > max) {
                    max = curr;
                }

                curr = 0;
                i = chars[c] - 1;
                chars = new int[255];
            } else {
                curr += 1;
                chars[c] = i + 1; // remove 0
            }
        }

        return Math.max(curr, max);
    }

    // good
    public int slideWindow(String s) {
        boolean[] cs = new boolean[0xff];

        int max = 0, sz = s.length();
        for (int l = 0, r = 0; l < sz; l++) {
            if (l != 0) {
                cs[s.charAt(l - 1)] = false;
            }

            for (var rc = s.charAt(r); !cs[rc]; rc = s.charAt(r)) {
                cs[s.charAt(r)] = true;
                if (++r >= sz) {
                    break;
                }
            }

            max = Math.max(max, r - l);
            if (r >= sz - 1) {
                break;
            }
        }

        return max;
    }

    public int slideWindowV1(String s) {
        boolean[] cs = new boolean[0xff];

        int max = 0, sz = s.length();
        for (int l = 0, r = 0; l < sz; l++) {
            if (l != 0) {
                cs[s.charAt(l - 1)] = false;
            }

            while (r < sz && !cs[s.charAt(r)]) {
                cs[s.charAt(r)] = true;
                r++;
            }

            max = Math.max(max, r - l);
            if (r == sz - 1) {
                break;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstring("") == 0;
        assert new Solution().lengthOfLongestSubstring("a") == 1;
        assert new Solution().lengthOfLongestSubstring("au") == 2;
        assert new Solution().lengthOfLongestSubstring("aab") == 2;
        assert new Solution().lengthOfLongestSubstring("abcabcbb") == 3;
        assert new Solution().lengthOfLongestSubstring("bbbbb") == 1;
        assert new Solution().lengthOfLongestSubstring("bbbbb") == 1;
        assert new Solution().lengthOfLongestSubstring("pwwkew") == 3;

        assert new Solution().slideWindow("") == 0;
        assert new Solution().slideWindow("a") == 1;
        assert new Solution().slideWindow("au") == 2;
        assert new Solution().slideWindow("aab") == 2;
        assert new Solution().slideWindow("abcabcbb") == 3;
        assert new Solution().slideWindow("bbbbb") == 1;
        assert new Solution().slideWindow("bbbbb") == 1;
        assert new Solution().slideWindow("pwwkew") == 3;
    }

}
