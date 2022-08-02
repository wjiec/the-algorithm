package problem.p1371findthelongestsubstringcontainingvowelsinevencounts;

import common.TODO;

import java.util.Arrays;

/**
 * 1371. Find the Longest Substring Containing Vowels in Even Counts
 *
 * https://leetcode.cn/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 *
 * Given the string s, return the size of the longest substring containing each vowel an even number of times.
 * That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 */

public class Solution {

    @TODO
    public int findTheLongestSubstring(String s) {
        int[] index = new int[1 << 5];
        Arrays.fill(index, -1);
        index[0] = 0;

        int ans = 0, curr = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            switch (chars[i]) {
                case 'a' -> curr ^= 1;
                case 'e' -> curr ^= 1 << 1;
                case 'i' -> curr ^= 1 << 2;
                case 'o' -> curr ^= 1 << 3;
                case 'u' -> curr ^= 1 << 4;
            }

            if (index[curr] < 0) index[curr] = i + 1;
            else ans = Math.max(ans, i + 1 - index[curr]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findTheLongestSubstring("eleetminicoworoep") == 13;
        assert new Solution().findTheLongestSubstring("leetcodeisgreat") == 5;
        assert new Solution().findTheLongestSubstring("bcbcbc") == 6;
    }

}
