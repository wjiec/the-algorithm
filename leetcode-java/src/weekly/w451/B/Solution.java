package weekly.w451.B;

import java.util.ArrayDeque;

/**
 * Q2. Resulting String After Adjacent Removals
 *
 * https://leetcode.cn/contest/weekly-contest-451/problems/resulting-string-after-adjacent-removals/
 *
 * You are given a string s consisting of lowercase English letters.
 *
 * You must repeatedly perform the following operation while the
 * string s has at least two consecutive characters:
 *
 * Remove the leftmost pair of adjacent characters in the string that are consecutive
 * in the alphabet, in either order (e.g., 'a' and 'b', or 'b' and 'a').
 *
 * Shift the remaining characters to the left to fill the gap.
 *
 * Return the resulting string after no more operations can be performed.
 *
 * Note: Consider the alphabet as circular, thus 'a' and 'z' are consecutive.
 */

public class Solution {

    public String resultingString(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if (!stack.isEmpty() && consecutive(stack.peek(), c)) stack.pop();
            else stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        return sb.reverse().toString();
    }

    private boolean consecutive(char a, char b) {
        return Math.abs(a - b) == 1 || (a == 'a' && b == 'z') || (a == 'z' && b == 'a');
    }

    public static void main(String[] args) {
    }

}
