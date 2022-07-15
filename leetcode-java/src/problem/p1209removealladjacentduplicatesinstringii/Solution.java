package problem.p1209removealladjacentduplicatesinstringii;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1209. Remove All Adjacent Duplicates in String II
 *
 * https://leetcode.cn/problems/remove-all-adjacent-duplicates-in-string-ii/
 *
 * You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent
 * and equal letters from s and removing them, causing the left and the right side of the
 * deleted substring to concatenate together.
 *
 * We repeatedly make k duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 */

public class Solution {

    private static class RepeatedChar {
        private int count;
        private final char c;
        public RepeatedChar(char c) { this.c = c; this.count = 1; }
        @Override public String toString() { return "<" + c + "*" + count + ">"; }
    }

    public String removeDuplicates(String s, int k) {
        Deque<RepeatedChar> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if (stack.isEmpty() || stack.peek().c != c) {
                stack.push(new RepeatedChar(c));
            } else if (stack.peek().c == c && ++stack.peek().count == k) {
                stack.pop();
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            RepeatedChar curr = stack.removeLast();
            sb.append(String.valueOf(curr.c).repeat(curr.count));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeDuplicates("abcd", 2).equals("abcd");
        assert new Solution().removeDuplicates("deeedbbcccbdaa", 3).equals("aa");
        assert new Solution().removeDuplicates("pbbcggttciiippooaais", 2).equals("ps");
    }

}
