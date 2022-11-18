package offer2.p85;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 085. 生成匹配的括号
 *
 * https://leetcode.cn/problems/IDBivT/
 *
 * 正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 */

public class Solution {

    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, n, 0, 0, new char[n * 2]);
        return ans;
    }

    private void dfs(int open, int close, int state, int idx, char[] chars) {
        if (open == 0 && close == 0) { ans.add(new String(chars)); return; }

        if (open > 0) {
            chars[idx] = '(';
            dfs(open - 1, close, state + 1, idx + 1, chars);
        }
        if (state > 0) {
            chars[idx] = ')';
            dfs(open, close - 1, state - 1, idx + 1, chars);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
        System.out.println(new Solution().generateParenthesis(1));
    }

}
