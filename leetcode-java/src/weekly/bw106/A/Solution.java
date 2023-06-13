package weekly.bw106.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 2729. Check if The Number is Fascinating
 *
 * https://leetcode.cn/contest/biweekly-contest-106/problems/check-if-the-number-is-fascinating/
 *
 * You are given an integer n that consists of exactly 3 digits.
 *
 * We call the number n fascinating if, after the following modification, the resulting number
 * contains all the digits from 1 to 9 exactly once and does not contain any 0's:
 *
 * Concatenate n with the numbers 2 * n and 3 * n.
 * Return true if n is fascinating, or false otherwise.
 *
 * Concatenating two numbers means joining them together. For example, the concatenation of 121 and 371 is 121371.
 */

public class Solution {

    public boolean isFascinating(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 3; i++) sb.append(n * i);

        Set<Character> chars = new HashSet<>();
        for (var c : sb.toString().toCharArray()) chars.add(c);
        return chars.size() == 9 && !chars.contains('0') && sb.length() == 9;
    }

    public static void main(String[] args) {
    }

}
