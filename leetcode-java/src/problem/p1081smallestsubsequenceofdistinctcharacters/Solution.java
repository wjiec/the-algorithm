package problem.p1081smallestsubsequenceofdistinctcharacters;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1081. Smallest Subsequence of Distinct Characters
 *
 * https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * Given a string s, return the lexicographically smallest subsequence of s that
 * contains all the distinct characters of s exactly once.
 */

public class Solution {

    public String smallestSubsequence(String s) {
        int[] freq = new int[128];
        for (var c : s.toCharArray()) freq[c]++;

        boolean[] visited = new boolean[128];
        Deque<Character> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) {
            if (!visited[c]) {
                while (!stack.isEmpty() && stack.peek() > c) {
                    if (freq[stack.peek()] > 0) {
                        visited[stack.pop()] = false;
                    } else break;
                }

                stack.push(c);
                visited[c] = true;
            }
            freq[c]--;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.removeLast());
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().smallestSubsequence("bcabc").equals("abc");
        assert new Solution().smallestSubsequence("cbacdcbc").equals("acdb");
    }

}
