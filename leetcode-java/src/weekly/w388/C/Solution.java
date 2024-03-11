package weekly.w388.C;

import common.Checker;

/**
 * 100251. Shortest Uncommon Substring in an Array
 *
 * https://leetcode.cn/contest/weekly-contest-388/problems/shortest-uncommon-substring-in-an-array/
 *
 * You are given an array arr of size n consisting of non-empty strings.
 *
 * Find a string array answer of size n such that:
 *
 * answer[i] is the shortest substring of arr[i] that does not occur as a substring
 * in any other string in arr. If multiple such substrings exist, answer[i] should be
 * the lexicographically smallest. And if no such substring exists, answer[i] should be an empty string.
 *
 * Return the array answer.
 */

public class Solution {

    public String[] shortestSubstrings(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String curr = "";
            for (int len = 1; len <= arr[i].length(); len++) {
                for (var ss : allSubstring(arr[i], len)) {
                    if (check(arr, i, ss)) {
                        if (curr.length() == 0 || curr.compareTo(ss) > 0) curr = ss;
                    }
                }
                if (curr.length() != 0) break;
            }
            ans[i] = curr;
        }
        return ans;
    }

    private boolean check(String[] arr, int i, String ss) {
        for (int j = 0; j < arr.length; j++) {
            if (i != j && arr[j].contains(ss)) return false;
        }
        return true;
    }

    private String[] allSubstring(String s, int len) {
        int n = s.length();
        String[] ans = new String[n - len + 1];
        for (int l = 0, r = len; r <= n; l++, r++) {
            ans[l] = s.substring(l, r);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().shortestSubstrings(new String[]{"cab","ad","bad","c"}), new String[]{"ab","","ba",""});
        assert Checker.check(new Solution().shortestSubstrings(new String[]{"abc","bcd","abcd"}), new String[]{"","","abcd"});
    }

}
