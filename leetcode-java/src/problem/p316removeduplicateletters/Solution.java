package problem.p316removeduplicateletters;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 316. Remove Duplicate Letters
 *
 * https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 */

public class Solution {

    public String removeDuplicateLetters(String s) {
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
        assert new Solution().removeDuplicateLetters("bcabc").equals("abc");
        assert new Solution().removeDuplicateLetters("cbacdcbc").equals("acdb");
        assert new Solution().removeDuplicateLetters("dcba").equals("dcba");
        assert new Solution().removeDuplicateLetters("dcabbc").equals("dabc");
        assert new Solution().removeDuplicateLetters("abgccidd").equals("abgcid");
        assert new Solution().removeDuplicateLetters("aahgee").equals("ahge");
        assert new Solution().removeDuplicateLetters("aaxggcc").equals("axgc");
    }

}
