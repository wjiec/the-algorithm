package problem.p402removekdigits;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 402. Remove K Digits
 *
 * https://leetcode-cn.com/problems/remove-k-digits/
 *
 * Given string num representing a non-negative integer num, and an integer k,
 * return the smallest possible integer after removing k digits from num.
 */

public class Solution {

    public String removeKdigits(String num, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        for (int i = 0, n = num.length(); i < n; i++) {
            char digit = num.charAt(i);
            while (!deque.isEmpty() && k != 0 && deque.peekLast() > digit) {
                deque.removeLast();
                k--;
            }
            deque.addLast(digit);
        }
        for (int i = 0; i < k; i++) deque.removeLast();

        while (!deque.isEmpty() && deque.peekFirst() == '0') deque.removeFirst();

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) sb.append(deque.removeFirst());
        return sb.length() == 0 ? "0" : sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeKdigits("1432219", 3).equals("1219");
        assert new Solution().removeKdigits("10200", 1).equals("200");
        assert new Solution().removeKdigits("10", 2).equals("0");
    }

}
