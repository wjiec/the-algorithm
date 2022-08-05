package problem.p1404numberofstepstoreduceanumberinbinaryrepresentationtoone;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 1404. Number of Steps to Reduce a Number in Binary Representation to One
 *
 * https://leetcode.cn/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/
 *
 * Given the binary representation of an integer as a string s, return the number of steps to
 * reduce it to 1 under the following rules:
 *
 * If the current number is even, you have to divide it by 2.
 *
 * If the current number is odd, you have to add 1 to it.
 *
 * It is guaranteed that you can always reach one for all test cases.
 */

public class Solution {

    public int numSteps(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (var c : s.toCharArray()) stack.push(c - '0');

        int ans = 0;
        while (stack.size() > 1) {
            if (stack.peek() == 0) {
                stack.pop(); ans++;
            } else {
                ans++;
                while (!stack.isEmpty() && stack.peek() == 1) {
                    stack.pop(); ans++;
                }
                if (!stack.isEmpty()) stack.pop(); // remove 0
                stack.push(1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numSteps("1101") == 6;
        assert new Solution().numSteps("10") == 1;
        assert new Solution().numSteps("1") == 0;
    }

}
