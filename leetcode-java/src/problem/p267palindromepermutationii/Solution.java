package problem.p267palindromepermutationii;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 267. Palindrome Permutation II
 *
 * https://leetcode-cn.com/problems/palindrome-permutation-ii/
 *
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 *
 * You may return the answer in any order. If s has no palindromic permutation,
 * return an empty list.
 */

public class Solution {

    public List<String> generatePalindromes(String s) {
        if (s.length() == 1) return List.of(s);

        int[] chars = new int[128];
        for (var c : s.toCharArray()) chars[c]++;

        int odd = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            if (chars[c] % 2 != 0) odd++;
        }
        if (odd > 1) return List.of();

        List<String> ans = new ArrayList<>();
        dfs(chars, 0, s.length(), ans, new StringBuilder());
        return ans;
    }

    private void dfs(int[] chars, int i, int n, List<String> ans, StringBuilder sb) {
        if (i == (n + 1) / 2) {
            StringBuilder curr = new StringBuilder(sb);
            int idx = sb.length() - 1 - (n % 2);
            for (; idx >= 0; idx--) curr.append(sb.charAt(idx));

            ans.add(curr.toString());
            return;
        }

        if (i == n / 2) {
            for (int c = 'a'; c <= 'z'; c++) {
                if (chars[c] % 2 == 1) {
                    chars[c] -= 1; sb.append((char) c);
                    dfs(chars, i + 1, n, ans, sb);
                    chars[c] += 1; sb.deleteCharAt(sb.length() - 1);
                }
            }
        } else {
            for (int c = 'a'; c <= 'z'; c++) {
                if (chars[c] != 0 && chars[c] >= 2) {
                    chars[c] -= 2; sb.append((char) c);
                    dfs(chars, i + 1, n, ans, sb);
                    chars[c] += 2; sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().generatePalindromes("aaa"), List.of("aaa"));

        assert Checker.anyOrder(new Solution().generatePalindromes("aabb"), List.of("abba","baab"));
        assert Checker.anyOrder(new Solution().generatePalindromes("abc"), List.of());
        assert Checker.anyOrder(new Solution().generatePalindromes("abb"), List.of("bab"));
        assert Checker.anyOrder(new Solution().generatePalindromes("abbb"), List.of());
    }

}
