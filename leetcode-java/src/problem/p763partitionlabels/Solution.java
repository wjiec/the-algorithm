package problem.p763partitionlabels;

import java.util.ArrayList;
import java.util.List;

/**
 * 763. Partition Labels
 *
 * https://leetcode.cn/problems/partition-labels/
 *
 * You are given a string s. We want to partition the string into as many parts as possible
 * so that each letter appears in at most one part.
 *
 * Note that the partition is done so that after concatenating all the parts in order,
 * the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 */

public class Solution {

    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] map = new int[128];
        for (int i = 0; i < n; i++) map[s.charAt(i)] = i;

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int next = i;
            for (int j = i; j <= next && next < n; j++) {
                next = Math.max(next, map[s.charAt(j)]);
            }
            ans.add(next - i + 1);
            i = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().partitionLabels("ababcbacadefegdehijhklij").equals(List.of(9,7,8));
        assert new Solution().partitionLabels("ababcbacadefegdehijhklia").equals(List.of(25));
        assert new Solution().partitionLabels("abcdefghijklmnopqrstuvwxyz").equals(List.of(
            1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1
        ));
    }

}
