package weekly.w310.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 6177. Optimal Partition of String
 *
 * https://leetcode.cn/contest/weekly-contest-310/problems/optimal-partition-of-string/
 *
 * Given a string s, partition the string into one or more substrings such that the characters
 * in each substring are unique. That is, no letter appears in a single substring more than once.
 *
 * Return the minimum number of substrings in such a partition.
 *
 * Note that each character should belong to exactly one substring in a partition.
 */

public class Solution {

    public int partitionString(String s) {
        int ans = 0, n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < n; ) {
            int j = i;
            Set<Character> uniq = new HashSet<>();
            while (j < n && !uniq.contains(chars[j])) uniq.add(chars[j++]);
            ans++; i = j;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
