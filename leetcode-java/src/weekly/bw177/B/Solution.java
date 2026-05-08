package weekly.bw177.B;

import java.util.Arrays;

/**
 * Q2. Merge Close Characters
 *
 * https://leetcode.cn/contest/biweekly-contest-177/problems/merge-close-characters/
 *
 * You are given a string s consisting of lowercase English letters and an integer k.
 *
 * Two equal characters in the current string s are considered close
 * if the distance between their indices is at most k.
 *
 * When two characters are close, the right one merges into the left. Merges happen one
 * at a time, and after each merge, the string updates until no more merges are possible.
 *
 * Return the resulting string after performing all possible merges.
 *
 * Note: If multiple merges are possible, always merge the pair with the smallest left index.
 * If multiple pairs share the smallest left index, choose the pair with the smallest right index.
 */

public class Solution {

    public String mergeCharacters(String s, int k) {
        char[] chars = s.toCharArray(); int n = 0;
        int[] last = new int[128]; Arrays.fill(last, -1);
        for (int r = 0, d = 0; r < chars.length; r++) {
            char c = chars[r];
            // 可以合并到之前的位置
            if (last[c] != -1 && r - last[c] - d <= k) {
                d++; continue;
            }
            last[c] = r - d; chars[n++] = c;
        }
        return new String(chars, 0, n);
    }

    public static void main(String[] args) {
        assert new Solution().mergeCharacters("abca", 3).equals("abc");
        assert new Solution().mergeCharacters("aabca", 2).equals("abca");
        assert new Solution().mergeCharacters("yybyzybz", 2).equals("ybzybz");
    }

}
