package weekly.w484.C;

import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Count Caesar Cipher Pairs
 *
 * https://leetcode.cn/contest/weekly-contest-484/problems/count-caesar-cipher-pairs/
 *
 * You are given an array words of n strings.
 * Each string has length m and contains only lowercase English letters.
 *
 * Two strings s and t are similar if we can apply the following operation
 * any number of times (possibly zero times) so that s and t become equal.
 *
 * Choose either s or t.
 * Replace every letter in the chosen string with the next letter in the alphabet cyclically.
 * The next letter after 'z' is 'a'.
 * Count the number of pairs of indices (i, j) such that:
 *
 * i < j
 * words[i] and words[j] are similar.
 *
 * Return an integer denoting the number of such pairs.
 */

public class Solution {

    public long countPairs(String[] words) {
        // 将其全部对齐到 a 开头的字符串上再统计即可
        long ans = 0;
        Map<String, Integer> c = new HashMap<>();
        for (var word : words) {
            char[] chars = word.toCharArray();
            while (chars[0] != 'a') {
                for (int i = 0; i < chars.length; i++) {
                    chars[i] = (char) ('a' + ((chars[i] - 'a' + 1) % 26));
                }
            }

            ans += c.merge(new String(chars), 1, Integer::sum) - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
