package weekly.bw175.A;

import java.util.ArrayList;
import java.util.List;

/**
 * Q1. Reverse Letters Then Special Characters in a String
 *
 * https://leetcode.cn/contest/biweekly-contest-175/problems/reverse-letters-then-special-characters-in-a-string/
 *
 * You are given a string s consisting of lowercase English letters and special characters.
 *
 * Your task is to perform these in order:
 *
 * Reverse the lowercase letters and place them back into the positions originally occupied by letters.
 * Reverse the special characters and place them back into the positions originally occupied by special characters.
 *
 * Return the resulting string after performing the reversals.
 */

public class Solution {

    public String reverseByType(String s) {
        List<Character> letters = new ArrayList<>();
        List<Character> symbols = new ArrayList<>();
        for (var c : s.toCharArray()) {
            if ('a' <= c && c <= 'z') letters.add(c);
            else symbols.add(c);
        }

        char[] ans = new char[s.length()];
        for (int i = 0, j = letters.size() - 1, k = symbols.size() - 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('a' <= c && c <= 'z') ans[i] = letters.get(j--);
            else ans[i] = symbols.get(k--);
        }
        return new String(ans);
    }

    public static void main(String[] args) {
    }

}
