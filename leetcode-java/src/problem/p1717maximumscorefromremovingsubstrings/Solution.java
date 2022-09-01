package problem.p1717maximumscorefromremovingsubstrings;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 1717. Maximum Score From Removing Substrings
 *
 * https://leetcode.cn/problems/maximum-score-from-removing-substrings/
 *
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 *
 * Remove substring "ab" and gain x points.
 * For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * Remove substring "ba" and gain y points.
 * For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 */

public class Solution {

    public int maximumGain(String s, int x, int y) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        Queue<Character> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (chars[i] == 'a' || chars[i] == 'b') queue.add(chars[i]);
            if ((chars[i] != 'a' && chars[i] != 'b') || i == n - 1) {
                if (queue.size() > 1) {
                    if (x > y) {
                        ans += x * countSeq(queue, 'a', 'b');
                        ans += y * countSeq(queue, 'b', 'a');
                    } else {
                        ans += y * countSeq(queue, 'b', 'a');
                        ans += x * countSeq(queue, 'a', 'b');
                    }
                }
                queue.clear();
            }

        }
        return ans;
    }

    private int countSeq(Queue<Character> src, char prev, char next) {
        int count = 0;
        Deque<Character> out = new ArrayDeque<>();
        while (!src.isEmpty()) {
            if (!out.isEmpty() && out.peek() == prev && src.peek() == next) {
                count++; out.pop(); src.remove();
            } else out.push(src.remove());
        }

        while (!out.isEmpty()) src.add(out.removeLast());
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().maximumGain("cdbcbbaaabab", 4, 5) == 19;
        assert new Solution().maximumGain("aabbaaxybbaabb", 5, 4) == 20;
    }

}
