package weekly.w431.B;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 3412. Find Mirror Score of a String
 *
 * https://leetcode.cn/contest/weekly-contest-431/problems/find-mirror-score-of-a-string/
 *
 * You are given a string s.
 *
 * We define the mirror of a letter in the English alphabet as its corresponding letter
 * when the alphabet is reversed. For example, the mirror of 'a' is 'z', and the mirror of 'y' is 'b'.
 *
 * Initially, all characters in the string s are unmarked.
 *
 * You start with a score of 0, and you perform the following process on the string s:
 *
 * Iterate through the string from left to right.
 * At each index i, find the closest unmarked index j such that j < i and s[j] is the mirror of s[i].
 * Then, mark both indices i and j, and add the value i - j to the total score.
 *
 * If no such index j exists for the index i, move on to the next index without making any changes.
 *
 * Return the total score at the end of the process.
 */

public class Solution {

    public long calculateScore(String s) {
        Deque<Integer>[] sts = new Deque[26];
        Arrays.setAll(sts, i -> new ArrayDeque<>());

        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i), rev = (char) ('z' - (curr - 'a'));
            if (!sts[rev - 'a'].isEmpty()) {
                ans += i - sts[rev - 'a'].pop();
            } else sts[curr - 'a'].push(i);
        }

        return ans;
    }

    public static void main(String[] args) {
    }

}
