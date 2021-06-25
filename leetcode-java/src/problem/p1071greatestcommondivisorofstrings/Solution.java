package problem.p1071greatestcommondivisorofstrings;

/**
 * 1071. Greatest Common Divisor of Strings
 *
 * https://leetcode-cn.com/problems/greatest-common-divisor-of-strings/
 *
 * For two strings s and t, we say "t divides s" if and only if s = t + ... + t
 * (t concatenated with itself 1 or more times)
 *
 * Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.
 */

public class Solution {

    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        if (a % b != 0) return gcd(b, a % b);
        return b;
    }

    public static void main(String[] args) {
        assert new Solution().gcdOfStrings("ABCABC", "ABC").equals("ABC");
        assert new Solution().gcdOfStrings("ABABAB", "ABAB").equals("AB");
        assert new Solution().gcdOfStrings("LEET", "CODE").equals("");
    }

}
