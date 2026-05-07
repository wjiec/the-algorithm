package weekly.w490.C;

/**
 * Q3. Maximum Bitwise XOR After Rearrangement
 *
 * https://leetcode.cn/contest/weekly-contest-490/problems/maximum-bitwise-xor-after-rearrangement/
 *
 * You are given two binary strings s and t, each of length n.
 *
 * You may rearrange the characters of t in any order, but s must remain unchanged.
 *
 * Return a binary string of length n representing the maximum integer value
 * obtainable by taking the bitwise XOR of s and rearranged t.
 */

public class Solution {

    public String maximumXor(String s, String t) {
        // 越大的位置越应该放相反的 0 或者 1, 没了的话就随意
        int ones = 0, zeros = t.length();
        for (var c : t.toCharArray()) ones += c & 1;
        zeros -= ones;

        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) {
            switch (c) {
                case '0' -> {
                    if (ones > 0) { sb.append(1); ones--; }
                    else { sb.append(0); zeros--; }
                }
                case '1' -> {
                    if (zeros > 0) { sb.append(1); zeros--; }
                    else { sb.append(0); ones--; }
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }

}
