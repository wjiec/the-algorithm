package weekly.w405.A;

/**
 * 3210. Find the Encrypted String
 *
 * https://leetcode.cn/contest/weekly-contest-405/problems/find-the-encrypted-string/
 *
 * You are given a string s and an integer k. Encrypt the string using the following algorithm:
 *
 * For each character c in s, replace c with the kth character after c in the string (in a cyclic manner).
 *
 * Return the encrypted string.
 */

public class Solution {

    public String getEncryptedString(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt((i + k) % s.length()));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
