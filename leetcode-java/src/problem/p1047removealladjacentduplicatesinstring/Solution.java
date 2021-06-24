package problem.p1047removealladjacentduplicatesinstring;

import java.util.Stack;

/**
 * 1047. Remove All Adjacent Duplicates In String
 *
 * https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/
 *
 * You are given a string s consisting of lowercase English letters.
 * A duplicate removal consists of choosing two adjacent and equal letters and removing them.
 *
 * We repeatedly make duplicate removals on s until we no longer can.
 *
 * Return the final string after all such duplicate removals have been made.
 * It can be proven that the answer is unique.
 */

public class Solution {

    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0, l = s.length(); i < l; i++) {
            if (!stack.empty() && stack.peek() == s.charAt(i)) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeDuplicates("abbaca").equals("ca");
        assert new Solution().removeDuplicates("aabbbbaac").equals("c");
        assert new Solution().removeDuplicates("aabbbaac").equals("c");
    }

}
