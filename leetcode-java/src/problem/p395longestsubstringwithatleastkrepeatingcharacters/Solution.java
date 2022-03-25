package problem.p395longestsubstringwithatleastkrepeatingcharacters;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 395. Longest Substring with At Least K Repeating Characters
 *
 * https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/
 *
 * Given a string s and an integer k, return the length of the longest substring of s
 * such that the frequency of each character in this substring is greater than or equal to k.
 */

public class Solution {

    public int longestSubstring(String s, int k) {
        int ans = 0;
        char[] chars = s.toCharArray();
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{0, chars.length}); !queue.isEmpty(); ) {
            int[] range = queue.remove();
            int[] letters = new int[128];
            int l = range[0], r = range[1];
            for (int i = l; i < r; i++) letters[chars[i]]++;

            char split = 0;
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] > 0 && letters[i] < k) {
                    split = (char) i;
                    break;
                }
            }

            if (split == 0) {
                ans = Math.max(ans, range[1] - range[0]);
                continue;
            }

            for (int p = l, i = l; i <= r; i++) {
                if (i == r || chars[i] == split) {
                    if (i - p >= k) {
                        queue.add(new int[]{p, i});
                    }
                    p = i + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().longestSubstring("bbaaacbd", 3) == 3;

        assert new Solution().longestSubstring("aaabb", 3) == 3;
        assert new Solution().longestSubstring("ababbc", 2) == 5;
    }

}
