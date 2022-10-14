package problem.p2131longestpalindromebyconcatenatingtwoletterwords;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2131. Longest Palindrome by Concatenating Two Letter Words
 *
 * https://leetcode.cn/problems/longest-palindrome-by-concatenating-two-letter-words/
 *
 * You are given an array of strings words. Each element of words
 * consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating
 * them in any order. Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to
 * create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 */

public class Solution {

    public int longestPalindrome(String[] words) {
        Map<String, Integer> count = new HashMap<>();
        for (var word : words) count.merge(word, 1, Integer::sum);

        int ans = 0; boolean single = false;
        Set<String> keySet = new HashSet<>(count.keySet());
        for (var word : keySet) {
            int cnt = count.get(word);
            if (cnt == 0) continue;

            char c0 = word.charAt(0), c1 = word.charAt(1);
            if (c0 == c1) {
                ans += (cnt / 2) * 4;
                if (cnt % 2 == 1) single = true;
            } else {
                int cpc = count.getOrDefault(c1 + "" + c0, 0);
                int pairs = Math.min(cpc, cnt);
                ans += pairs * 4;
                count.merge(word, -pairs, Integer::sum);
                count.merge(c1 + "" + c0, -pairs, Integer::sum);
            }
        }

        return ans + (single ? 2 : 0);
    }

    public static void main(String[] args) {
        assert new Solution().longestPalindrome(new String[]{"dd","aa","bb","dd","aa","dd","bb","dd","aa","cc","bb","cc","dd","cc"}) == 22;

        assert new Solution().longestPalindrome(new String[]{"lc","cl","gg"}) == 6;
        assert new Solution().longestPalindrome(new String[]{"ab","ty","yt","lc","cl","ab"}) == 8;
        assert new Solution().longestPalindrome(new String[]{"cc","ll","xx"}) == 2;
    }

}
