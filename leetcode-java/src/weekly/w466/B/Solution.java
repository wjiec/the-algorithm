package weekly.w466.B;

/**
 * Q2. Minimum Operations to Transform String
 *
 * https://leetcode.cn/contest/weekly-contest-466/problems/minimum-operations-to-transform-string/
 *
 * You are given a string s consisting only of lowercase English letters.
 *
 * You can perform the following operation any number of times (including zero):
 *
 * Choose any character c in the string and replace every occurrence of c
 * with the next lowercase letter in the English alphabet.
 *
 * Return the minimum number of operations required to transform s
 * into a string consisting of only 'a' characters.
 *
 * Note: Consider the alphabet as circular, thus 'a' comes after 'z'.
 */

public class Solution {

    public int minOperations(String s) {
        int[] chars = new int[27];
        for (var c : s.toCharArray()) chars[c - 'a']++;
        if (chars[0] == s.length()) return 0;

        int ans = 0;
        for (int i = 1; i < 26; i++) {
            if (chars[i] != 0) {
                ans++;
                chars[i + 1] += chars[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }
    
}
