package weekly.w456.A;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q1. Partition String
 *
 * https://leetcode.cn/contest/weekly-contest-456/problems/partition-string/
 *
 * Given a string s, partition it into unique segments according to the following procedure:
 *
 * Start building a segment beginning at index 0.
 * Continue extending the current segment character by character until the current segment has not been seen before.
 * Once the segment is unique, add it to your list of segments, mark it as seen, and begin a new segment from the next index.
 * Repeat until you reach the end of s.
 *
 * Return an array of strings segments, where segments[i] is the ith segment created.
 */

public class Solution {

    public List<String> partitionString(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> uniq = new HashSet<>();
        for (int l = 0, r = 1; r <= s.length(); r++) {
            String curr = s.substring(l, r);
            if (uniq.add(curr)) { ans.add(curr); l = r; }
        }
        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().partitionString("abbccccd"));
        PrettyPrinter.println(new Solution().partitionString("aaaa"));
    }

}
