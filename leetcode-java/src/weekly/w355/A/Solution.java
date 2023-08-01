package weekly.w355.A;

import java.util.ArrayList;
import java.util.List;

/**
 * 2788. Split Strings by Separator
 *
 * https://leetcode.cn/contest/weekly-contest-355/problems/split-strings-by-separator/
 *
 * Given an array of strings words and a character separator, split each string in words by separator.
 *
 * Return an array of strings containing the new strings formed after the splits, excluding empty strings.
 *
 * Notes
 *
 * separator is used to determine where the split should occur, but it is not
 * included as part of the resulting strings.
 *
 * A split may result in more than two strings.
 *
 * The resulting strings must maintain the same order as they were initially given.
 */

public class Solution {

    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> ans = new ArrayList<>();
        for (var word : words) {
            char[] chars = word.toCharArray();
            for (int p = -1, i = 0; i <= chars.length; i++) {
                if ((i == chars.length || chars[i] == separator) && p + 1 < i) {
                    ans.add(word.substring(p + 1, i));
                }
                if (i < chars.length && chars[i] == separator) p = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().splitWordsBySeparator(List.of("one.two.three","four.five","six"), '.'));
        System.out.println(new Solution().splitWordsBySeparator(List.of("$easy$","$problem$"), '$'));
        System.out.println(new Solution().splitWordsBySeparator(List.of("|||"), '|'));
    }

}
