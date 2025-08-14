package weekly.w458.A;

/**
 * Q1. Process String with Special Operations I
 *
 * https://leetcode.cn/contest/weekly-contest-458/problems/process-string-with-special-operations-i/
 *
 * You are given a string s consisting of lowercase English letters and the special characters: *, #, and %.
 *
 * Build a new string result by processing s according to the following rules from left to right:
 *
 * If the letter is a lowercase English letter append it to result.
 * A '*' removes the last character from result, if it exists.
 * A '#' duplicates the current result and appends it to itself.
 * A '%' reverses the current result.
 *
 * Return the final string result after processing all characters in s.
 */

public class Solution {

    public String processStr(String s) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            switch (c) {
                case '*' -> {
                    if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
                }
                case '#' -> sb.append(sb);
                case '%' -> sb.reverse();
                default -> sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {

    }

}
