package problem.p1625lexicographicallysmalleststringafterapplyingoperations;

/**
 * 1625. Lexicographically Smallest String After Applying Operations
 *
 * https://leetcode.cn/problems/lexicographically-smallest-string-after-applying-operations/
 *
 * You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.
 *
 * You can apply either of the following two operations any number of times and in any order on s:
 *
 * Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0.
 * For example, if s = "3456" and a = 5, s becomes "3951".
 * Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
 * Return the lexicographically smallest string you can obtain by applying the above
 * operations any number of times on s.
 *
 * A string a is lexicographically smaller than a string b (of the same length) if in the
 * first position where a and b differ, string a has a letter that appears earlier in the
 * alphabet than the corresponding letter in b. For example, "0158" is lexicographically
 * smaller than "0190" because the first position they differ is at the third
 * letter, and '5' comes before '9'.
 */

public class Solution {

    public String findLexSmallestString(String s, int a, int b) {
        char[] cs = s.toCharArray(); String ans = s;
        for (int i = 0; i <= cs.length; i++) {
            // 轮转 cs.length 次就会回到原来位置
            cs = rotate(cs, b);
            // 修改10次奇数元素也能修改回原本位置
            for (int j = 0; j < 10; j++) {
                for (int k = 1; k < cs.length; k += 2) {
                    cs[k] = (char) (((cs[k] - '0' + a) % 10) + '0');
                }

                if (b % 2 == 0) {
                    String curr = new String(cs);
                    if (curr.compareTo(ans) < 0) {
                        ans = curr;
                    }
                    continue;
                }

                // 奇数轮转的话也有可能修改到偶数位置
                for (int c = 0; c < 10; c++) {
                    for (int k = 0; k < cs.length; k += 2) {
                        cs[k] = (char) (((cs[k] - '0' + a) % 10) + '0');
                    }

                    String curr = new String(cs);
                    if (curr.compareTo(ans) < 0) {
                        ans = curr;
                    }
                }
            }
        }
        return ans;
    }

    private char[] rotate(char[] chars, int b) {
        int n = chars.length;
        char[] ans = new char[n];
        for (int l = 0, r = n - b; l < n; l++, r++) {
            ans[l] = chars[r % n];
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findLexSmallestString("5525", 9, 2).equals("2050");
        assert new Solution().findLexSmallestString("0011", 4, 2).equals("0011");
        assert new Solution().findLexSmallestString("43987654", 7, 3).equals("00553311");
    }

}
