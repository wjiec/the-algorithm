package weekly.w396.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 3137. Minimum Number of Operations to Make Word K-Periodic
 *
 * https://leetcode.cn/contest/weekly-contest-396/problems/minimum-number-of-operations-to-make-word-k-periodic/
 *
 * You are given a string word of size n, and an integer k such that k divides n.
 *
 * In one operation, you can pick any two indices i and j, that are divisible by k, then replace
 * the substring of length k starting at i with the substring of length k starting at j.
 * That is, replace the substring word[i..i + k - 1] with the substring word[j..j + k - 1].
 *
 * Return the minimum number of operations required to make word k-periodic.
 *
 * We say that word is k-periodic if there is some string s of length k such that word
 * can be obtained by concatenating s an arbitrary number of times.
 *
 * For example, if word == “ababab”, then word is 2-periodic for s = "ab".
 */

public class Solution {

    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int max = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i += k) {
            max = Math.max(max, map.merge(word.substring(i, i + k), 1, Integer::sum));
        }
        return word.length() / k - max;
    }

    public static void main(String[] args) {
    }

}
