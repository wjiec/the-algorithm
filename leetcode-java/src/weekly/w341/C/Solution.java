package weekly.w341.C;

/**
 * 2645. Minimum Additions to Make Valid String
 *
 * https://leetcode.cn/contest/weekly-contest-341/problems/minimum-additions-to-make-valid-string/
 *
 * Given a string word to which you can insert letters "a", "b" or "c" anywhere and any
 * number of times, return the minimum number of letters that must be inserted
 * so that word becomes valid.
 *
 * A string is called valid if it can be formed by concatenating the string "abc" several times.
 */

public class Solution {

    public int addMinimum(String word) {
        return dfs(word.toCharArray(), 0);
    }

    private int dfs(char[] chars, int i) {
        if (i == chars.length) return 0;

        int n = chars.length;
        switch (chars[i]) {
            case 'a' -> {
                if (i + 1 < n) {
                    if (chars[i + 1] == 'b') {
                        if (i + 2 < n && chars[i + 2] == 'c') {
                            return dfs(chars, i + 3);
                        }
                        return 1 + dfs(chars, i + 2);
                    }
                    if (chars[i + 1] == 'c') {
                        return 1 + dfs(chars, i + 2);
                    }
                }
                return 2 + dfs(chars, i + 1);
            }
            case 'b' -> {
                if (i + 1 < n && chars[i + 1] == 'c') {
                    return 1 + dfs(chars, i + 2);
                }
                return 2 + dfs(chars, i + 1);
            }
            case 'c' -> {
                return 2 + dfs(chars, i + 1);
            }
            default -> {
                return 999;
            }
        }
    }

    public static void main(String[] args) {
        assert new Solution().addMinimum("aaaaac") == 9;
    }
    
}
