package weekly.bw132.A;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 3174. Clear Digits
 *
 * https://leetcode.cn/contest/biweekly-contest-132/problems/clear-digits/
 *
 * You are given a string s.
 *
 * Your task is to remove all digits by doing this operation repeatedly:
 *
 * Delete the first digit and the closest non-digit character to its left.
 *
 * Return the resulting string after removing all digits.
 */

public class Solution {

    public String clearDigits(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if ('0' <= c && c <= '9') stack.removeLast();
            else stack.addLast(c);
        }

        StringBuilder sb = new StringBuilder();
        for (var c : stack) sb.append(c);
        return sb.toString();
    }

    private static class Optimization {
        public String clearDigits(String s) {
            char[] chars = s.toCharArray();

            int l = 0;
            for (int i = 0; i < chars.length; i++) {
                if ('0' <= chars[i] && chars[i] <= '9') l--;
                else chars[l++] = chars[i];
            }
            return new String(chars, 0, l);
        }
    }

    public static void main(String[] args) {
    }

}
