package problem.p340longestsubstringwithatmostkdistinctcharacters;

import ability.Ability;

import java.util.HashMap;
import java.util.Map;

/**
 * 340. Longest Substring with At Most K Distinct Characters
 *
 * https://leetcode.cn/problems/longest-substring-with-at-most-k-distinct-characters/
 *
 * Given a string s and an integer k, return the length of the longest
 * substring of s that contains at most k distinct characters.
 */

public class Solution {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int ans = 0;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int l = 0, r = 0; r < chars.length; r++) {
            map.merge(chars[r], 1, Integer::sum);
            while (map.size() > k) map.merge(chars[l++], 1, Ability::subtract);
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().lengthOfLongestSubstringKDistinct("eceba", 2) == 3;
        assert new Solution().lengthOfLongestSubstringKDistinct("aa", 1) == 2;
    }

}
