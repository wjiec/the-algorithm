package problem.p131palindromepartitioning;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 *
 * https://leetcode-cn.com/problems/palindrome-partitioning/
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 */

public class Solution {

    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(s.toCharArray(), ans, new ArrayList<>(), 0);
        return ans;
    }

    private void dfs(char[] chars, List<List<String>> ans, List<String> curr, int i) {
        if (i == chars.length) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        curr.add(String.valueOf(chars[i]));
        dfs(chars, ans, curr, i + 1);
        curr.remove(curr.size() - 1);

        for (int j = i + 1; j < chars.length; j++) {
            if (isPalindrome(chars, i, j)) {
                curr.add(new String(chars, i, j - i + 1));
                dfs(chars, ans, curr, j + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(char[] chars, int l, int r) {
        for (; l < r; l++, r--) {
            if (chars[l] != chars[r]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().partition("aab"));
        System.out.println(new Solution().partition("aabbaa"));
        System.out.println(new Solution().partition("a"));
    }

}
