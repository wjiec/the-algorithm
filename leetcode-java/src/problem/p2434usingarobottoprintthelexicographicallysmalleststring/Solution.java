package problem.p2434usingarobottoprintthelexicographicallysmalleststring;

import common.TODO;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 2434. Using a Robot to Print the Lexicographically Smallest String
 *
 * https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string/
 *
 * You are given a string s and a robot that currently holds an empty string t.
 * Apply one of the following operations until s and t are both empty:
 *
 * Remove the first character of a string s and give it to the robot.
 * The robot will append this character to the string t.
 *
 * Remove the last character of a string t and give it to the robot.
 * The robot will write this character on paper.
 *
 * Return the lexicographically smallest string that can be written on the paper.
 */

public class Solution {

    @TODO
    public String robotWithString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        char[] min = new char[n + 1]; min[n] = 'z' + 1;
        for (int i = n - 1; i >= 0; i--) {
            min[i] = (char) Math.min(min[i + 1], chars[i]);
        }

        StringBuilder sb = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < chars.length; i++) {
            stack.push(chars[i]);
            while (!stack.isEmpty() && stack.peek() <= min[i + 1]) {
                sb.append(stack.pop());
            }
        }
        while (!stack.isEmpty()) sb.append(stack.pop());

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().robotWithString("zza").equals("azz");
        assert new Solution().robotWithString("bac").equals("abc");
        assert new Solution().robotWithString("bdda").equals("addb");
    }

}
