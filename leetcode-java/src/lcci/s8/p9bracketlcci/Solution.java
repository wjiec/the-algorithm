package lcci.s8.p9bracketlcci;

import java.util.ArrayList;
import java.util.List;

/**
 * 面试题 08.09. 括号
 *
 * https://leetcode.cn/problems/bracket-lcci/
 *
 * 括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
 *
 * 说明：解集不能包含重复的子集。
 */

public class Solution {

    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(new char[n + n], 0, n, n);
        return ans;
    }

    private void dfs(char[] chars, int i, int open, int close) {
        if (i == chars.length) {
            ans.add(new String(chars));
            return;
        }

        if (open > 0) {
            chars[i] = '(';
            dfs(chars, i + 1, open - 1, close);
        }
        if (close > open) {
            chars[i] = ')';
            dfs(chars, i + 1, open, close - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(3));
    }

}
