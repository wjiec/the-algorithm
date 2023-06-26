package weekly.bw107.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 2744. Find Maximum Number of String Pairs
 *
 * https://leetcode.cn/contest/biweekly-contest-107/problems/find-maximum-number-of-string-pairs/
 *
 * You are given a 0-indexed array words consisting of distinct strings.
 *
 * The string words[i] can be paired with the string words[j] if:
 *
 * The string words[i] is equal to the reversed string of words[j].
 * 0 <= i < j < words.length.
 * Return the maximum number of pairs that can be formed from the array words.
 *
 * Note that each string can belong in at most one pair.
 */


public class Solution {

    public int maximumNumberOfStringPairs(String[] words) {
        int ans = 0;
        Set<String> ss = new HashSet<>();
        for (var s : words) {
            if (ss.remove(s)) ans++;
            else ss.add(new StringBuilder(s).reverse().toString());
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
