package problem.p1417reformatthestring;

/**
 * 1417. Reformat The String
 *
 * https://leetcode-cn.com/problems/reformat-the-string/
 *
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 *
 * You have to find a permutation of the string where no letter is followed by another letter
 * and no digit is followed by another digit. That is, no two adjacent characters have the same type.
 *
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 */

public class Solution {

    public String reformat(String s) {
        int letter = 0, digit = 0;
        for (var c : s.toCharArray()) {
            if (c >= '0' && c <= '9') digit++;
            else letter++;
        }

        if (Math.abs(letter - digit) > 1) return "";

        char[] ans = new char[s.length()];
        int l = letter >= digit ? 0 : 1, r = Math.abs(l - 1);
        for (var c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                ans[r] = c;
                r += 2;
            } else {
                ans[l] = c;
                l += 2;
            }
        }
        return new String(ans);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reformat("a0b1c2"));
        System.out.println(new Solution().reformat("leetcode"));
        System.out.println(new Solution().reformat("1229857369"));
        System.out.println(new Solution().reformat("covid2019"));
        System.out.println(new Solution().reformat("ab123"));
    }

}
