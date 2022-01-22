package problem.p22generateparentheses;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 */

public class Solution {

    private final List<String> ans = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        dfs(n, 0, new StringBuilder());
        return ans;
    }

    private void dfs(int n, int opens, StringBuilder sb) {
        if (sb.length() == n * 2) {
            ans.add(sb.toString());
            return;
        }

        if (opens < n) {
            sb.append('(');
            dfs(n, opens + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (sb.length() - opens < opens) {
            sb.append(')');
            dfs(n, opens, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateParenthesis(1));
        System.out.println(new Solution().generateParenthesis(2));
        System.out.println(new Solution().generateParenthesis(3));
        System.out.println(new Solution().generateParenthesis(4));
        System.out.println(new Solution().generateParenthesis(5));
        System.out.println(new Solution().generateParenthesis(6));
        System.out.println(new Solution().generateParenthesis(7));
        System.out.println(new Solution().generateParenthesis(8));
    }

}
