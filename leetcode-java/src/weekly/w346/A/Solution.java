package weekly.w346.A;

/**
 * 6439. Minimum String Length After Removing Substrings
 *
 * https://leetcode.cn/contest/weekly-contest-346/problems/minimum-string-length-after-removing-substrings/
 *
 * You are given a string s consisting only of uppercase English letters.
 *
 * You can apply some operations to this string where, in one operation, you can
 * remove any occurrence of one of the substrings "AB" or "CD" from s.
 *
 * Return the minimum possible length of the resulting string that you can obtain.
 *
 * Note that the string concatenates after removing the substring and could produce new "AB" or "CD" substrings.
 */

public class Solution {

    public int minLength(String s) {
        while (s.contains("AB") || s.contains("CD")) {
            s = s.replaceAll("AB", "");
            s = s.replaceAll("CD", "");
        }
        return s.length();
    }

    public static void main(String[] args) {
    }

}
