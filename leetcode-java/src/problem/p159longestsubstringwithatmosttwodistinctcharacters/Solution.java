package problem.p159longestsubstringwithatmosttwodistinctcharacters;

import java.util.HashMap;
import java.util.Map;

/**
 * 159. Longest Substring with At Most Two Distinct Characters
 *
 * https://leetcode-cn.com/problems/longest-substring-with-at-most-two-distinct-characters/
 *
 * Given a string s, return the length of the longest substring that contains at most two distinct characters.
 */

public class Solution {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> chars = new HashMap<>();
        for (int l = 0, r = 0; r < n; r++) {
            chars.merge(s.charAt(r), 1, Integer::sum);
            for (; chars.size() > 2; l++) {
                char c = s.charAt(l);
                if (chars.get(c) == 1) chars.remove(c);
                else chars.put(c, chars.get(c) - 1);
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstringTwoDistinct("eceba") == 3;
        assert new Solution().lengthOfLongestSubstringTwoDistinct("ccaabbb") == 5;
    }

}
