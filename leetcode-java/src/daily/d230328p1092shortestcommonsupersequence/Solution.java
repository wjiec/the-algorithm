package daily.d230328p1092shortestcommonsupersequence;

/**
 * 1092. Shortest Common Supersequence
 *
 * https://leetcode.cn/problems/shortest-common-supersequence/
 *
 * Given two strings str1 and str2, return the shortest string
 * that has both str1 and str2 as subsequences.
 *
 * If there are multiple valid strings, return any of them.
 *
 * A string s is a subsequence of string t if deleting some number of characters
 * from t (possibly 0) results in the string s.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public String shortestCommonSupersequence(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();

        int m = chars1.length, n = chars2.length;
        int[][] lcs = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars1[i - 1] == chars2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1;
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                }
            }
        }

        int i = m, j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (chars1[i - 1] == chars2[j - 1]) {
                sb.append(chars1[i - 1]);
                i--; j--;
            } else if (lcs[i][j] == lcs[i - 1][j]) {
                // 从 i - 1 转移过来
                sb.append(chars1[--i]);
            } else if (lcs[i][j] == lcs[i][j - 1]) {
                // 从 j - 1 转移过来
                sb.append(chars2[--j]);
            }
        }
        while (i > 0) sb.append(chars1[--i]);
        while (j > 0) sb.append(chars2[--j]);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().shortestCommonSupersequence("abac", "cab"));
        System.out.println(new Solution().shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa"));
    }

}
