package problem.p791customsortstring;

import java.util.Arrays;

/**
 * 791. Custom Sort String
 *
 * https://leetcode.cn/problems/custom-sort-string/
 *
 * You are given two strings order and s. All the words of order are unique
 * and were sorted in some custom order previously.
 *
 * Permute the characters of s so that they match the order that order was sorted.
 * More specifically, if a character x occurs before a character y in order,
 * then x should occur before y in the permuted string.
 *
 * Return any permutation of s that satisfies this property.
 */

public class Solution {

    public String customSortString(String order, String s) {
        int[] orders = new int[128]; int priority = 100;
        for (var c : order.toCharArray()) orders[c] = priority--;
        for (int i = 'a'; i <= 'z'; i++) if (orders[i] == 0) orders[i] = priority--;

        Character[] chars = new Character[s.length()];
        for (int i = 0; i < chars.length; i++) chars[i] = s.charAt(i);
        Arrays.sort(chars, (a, b) -> orders[b] - orders[a]);

        StringBuilder sb = new StringBuilder();
        for (var c :chars) sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().customSortString("cba", "abcd"));
        System.out.println(new Solution().customSortString("cbafg", "abcd"));
    }

}
