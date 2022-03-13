package problem.p241differentwaystoaddparentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * 241. Different Ways to Add Parentheses
 *
 * https://leetcode-cn.com/problems/different-ways-to-add-parentheses/
 *
 * Given a string expression of numbers and operators, return all possible results from
 * computing all the different possible ways to group numbers and operators.
 *
 * You may return the answer in any order.
 */

public class Solution {

    public List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression.toCharArray(), 0, expression.length());
    }

    private List<Integer> dfs(char[] expression, int l, int r) {
        int val = 0;
        boolean hasOperator = false;
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i < r; i++) {
            if (!Character.isDigit(expression[i])) {
                hasOperator = true;

                List<Integer> left = dfs(expression, l, i);
                List<Integer> right = dfs(expression, i + 1, r);

                for (var a : left) {
                    for (var b : right) {
                        switch (expression[i]) {
                            case '+' -> ans.add(a + b);
                            case '-' -> ans.add(a - b);
                            case '*' -> ans.add(a * b);
                        }
                    }
                }
            } else val = val * 10 + (expression[i] - '0');
        }

        if (!hasOperator) ans.add(val);
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().diffWaysToCompute("2-1-1"));
        System.out.println(new Solution().diffWaysToCompute("2*3-4*5"));
    }

}
