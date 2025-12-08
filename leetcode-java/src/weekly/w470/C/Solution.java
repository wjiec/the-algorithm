package weekly.w470.C;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q3. Remove K-Balanced Substrings
 *
 * https://leetcode.cn/contest/weekly-contest-470/problems/remove-k-balanced-substrings/
 *
 * You are given a string s consisting of '(' and ')', and an integer k.
 *
 * A string is k-balanced if it is exactly k consecutive '(' followed by k consecutive ')', i.e., '(' * k + ')' * k.
 *
 * For example, if k = 3, k-balanced is "((()))".
 *
 * You must repeatedly remove all non-overlapping k-balanced substrings from s, and then join the remaining parts.
 * Continue this process until no k-balanced substring exists.
 *
 * Return the final string after all possible removals.
 */

public class Solution {

    public String removeSubstring(String s, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0, t = 0; i <= s.length(); i++) {
            switch (i == s.length() ? '(' : s.charAt(i)) {
                case '(' -> {
                    // 如果当前顶部一直是 '(' 则只能继续叠加计数
                    if (t >= 0) t++;
                    else {
                        // 否则当前是第一个 '(', 也就是说
                        //  - 现在我们有 -t 个 ), 检查是否有正好合适的 k 个 '(' 进行匹配
                        while (-t >= k && !dq.isEmpty() && dq.peek() >= k) {
                            // 移除掉栈顶的 k 个 '('
                            if (dq.peek() == k) {
                                dq.pop();
                                if (!dq.isEmpty() && dq.peek() < 0) t += dq.pop();
                            }
                            else dq.push(dq.pop() - k);

                            // 同样去掉配对的 k 个 ')'
                            t += k;
                        }
                        if (t != 0) {
                            if (!dq.isEmpty() && dq.peek() < 0) dq.push(dq.pop() + t);
                            else dq.push(t);
                        }
                        if (!dq.isEmpty() && dq.peek() > 0) t = dq.pop() + 1;
                        else t = 1;
                    }

                    // 如果是最后一个的话, 需要检查并及时入栈
                    if (i == s.length() && t > 1) dq.push(t - 1);
                }
                case ')' -> {
                    if (t > 0) { dq.push(t); t = 0; }
                    t--; // 叠加数量
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var v : dq) {
            if (v > 0) sb.append("(".repeat(v));
            else sb.append(")".repeat(-v));
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().removeSubstring("(()(()(()))((()", 2).equals("(()((()");
        assert new Solution().removeSubstring(")(", 1).equals(")(");
        assert new Solution().removeSubstring("(()", 1).equals("(");

        assert new Solution().removeSubstring("(())", 1).isEmpty();
        assert new Solution().removeSubstring("(()(", 1).equals("((");
        assert new Solution().removeSubstring("((()))()()()", 3).equals("()()()");
    }

}
