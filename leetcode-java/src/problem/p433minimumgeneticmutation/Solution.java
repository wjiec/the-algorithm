package problem.p433minimumgeneticmutation;

import java.util.*;

/**
 * 433. Minimum Genetic Mutation
 *
 * https://leetcode-cn.com/problems/minimum-genetic-mutation/
 *
 * A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.
 *
 * Suppose we need to investigate a mutation from a gene string start to a gene string end
 * where one mutation is defined as one single character changed in the gene string.
 *
 * For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
 * There is also a gene bank bank that records all the valid gene mutations.
 * A gene must be in bank to make it a valid gene string.
 *
 * Given the two gene strings start and end and the gene bank bank,
 * return the minimum number of mutations needed to mutate from start to end.
 *
 * If there is no such a mutation, return -1.
 *
 * Note that the starting point is assumed to be valid, so it might not be included in the bank.
 */

public class Solution {

    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<>(Arrays.asList(bank));
        if (!set.contains(end)) return -1;

        Map<String, Integer> map = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        for (queue.add(start); !queue.isEmpty(); ) {
            String curr = queue.remove();
            int steps = map.getOrDefault(curr, 0);
            for (var item : bank) {
                if (!map.containsKey(item) && distance(curr, item) == 1) {
                    map.put(item, steps + 1);
                    queue.add(item);
                }
            }
        }
        return map.getOrDefault(end, -1);
    }

    private int distance(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().minMutation("AACCGGTT", "AACCGGTA", new String[]{
            "AACCGGTA"}) == 1;
        assert new Solution().minMutation("AACCGGTT", "AAACGGTA", new String[]{
            "AACCGGTA", "AACCGCTA", "AAACGGTA"}) == 2;
        assert new Solution().minMutation("AAAAACCC", "AACCCCCC", new String[]{
            "AAAACCCC", "AAACCCCC", "AACCCCCC"}) == 3;

        assert new Solution().minMutation("AAAAACCC", "TTTTTTTT", new String[]{
            "AAAACCCC", "AAACCCCC", "AACCCCCC"}) == -1;
    }

}
