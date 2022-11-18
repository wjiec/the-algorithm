package offer2.p86;

import java.util.*;

/**
 * 剑指 Offer II 086. 分割回文子字符串
 *
 * https://leetcode.cn/problems/M99OJA/
 *
 * 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 */

public class Solution {

    private final Map<Integer, List<List<String>>> memo = new HashMap<>();

    public String[][] partition(String s) {
        List<List<String>> ps = partition(s.toCharArray(), s.length());

        String[][] ans = new String[ps.size()][];
        for (int i = 0; i < ps.size(); i++) {
            ans[i] = ps.get(i).toArray(new String[0]);
        }
        return ans;
    }

    private List<List<String>> partition(char[] chars, int r) {
        if (r == 0) return List.of(List.of());
        if (!memo.containsKey(r)) {
            List<List<String>> curr = new ArrayList<>();
            for (int l = 0; l < r; l++) {
                if (isPalindrome(chars, l, r - 1)) {
                    for (var ss : partition(chars, l)) {
                        List<String> v = new ArrayList<>(ss);
                        v.add(new String(chars, l, r - l));
                        curr.add(v);
                    }
                }
            }
            memo.put(r, curr);
        }
        return memo.get(r);
    }

    private boolean isPalindrome(char[] chars, int l, int r) {
        while (l < r && chars[l] == chars[r]) { l++; r--; }
        return l >= r;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Solution().partition("google")));
        System.out.println(Arrays.deepToString(new Solution().partition("aab")));
        System.out.println(Arrays.deepToString(new Solution().partition("a")));
        System.out.println(Arrays.deepToString(new Solution().partition("aaaaa")));
    }

}
