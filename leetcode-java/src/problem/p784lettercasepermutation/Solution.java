package problem.p784lettercasepermutation;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 *
 * https://leetcode.cn/problems/letter-case-permutation/
 *
 * Given a string s, you can transform every letter individually to be
 * lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. Return the output in any order.
 */

public class Solution {

    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), 0, new char[s.length()], ans);
        return ans;
    }

    private void dfs(char[] chars, int i, char[] curr, List<String> ans) {
        if (i == chars.length) {
            ans.add(new String(curr));
            return;
        }

        curr[i] = chars[i];
        dfs(chars, i + 1, curr, ans);
        if ('a' <= chars[i] && chars[i] <= 'z') {
            curr[i] = (char) (chars[i] - 32);
            dfs(chars, i + 1, curr, ans);
        } else if ('A' <= chars[i] && chars[i] <= 'Z') {
            curr[i] = (char) (chars[i] + 32);
            dfs(chars, i + 1, curr, ans);
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().letterCasePermutation("a1b2"), List.of(
            "a1b2", "a1B2", "A1b2", "A1B2"
        ));

        assert Checker.anyOrder(new Solution().letterCasePermutation("3z4"), List.of(
            "3z4","3Z4"
        ));
    }

}
