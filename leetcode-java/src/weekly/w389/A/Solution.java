package weekly.w389.A;

/**
 * 3083. Existence of a Substring in a String and Its Reverse
 *
 * https://leetcode.cn/contest/weekly-contest-389/problems/existence-of-a-substring-in-a-string-and-its-reverse/
 *
 * Given a string s, find any substring of length 2 which is also present in the reverse of s.
 *
 * Return true if such a substring exists, and false otherwise.
 */

public class Solution {

    public boolean isSubstringPresent(String s) {
        String reversed = new StringBuilder(s).reverse().toString();
        for (int l = 0, r = 2; r <= s.length(); l++, r++) {
            if (reversed.contains(s.substring(l, r))) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
