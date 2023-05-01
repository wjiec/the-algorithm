package problem.p444sequencereconstruction;

import java.util.*;

/**
 * 444. Sequence Reconstruction
 *
 * https://leetcode.cn/problems/sequence-reconstruction/
 *
 * You are given an integer array nums of length n where nums is a permutation of the integers in the range [1, n].
 * You are also given a 2D integer array sequences where sequences[i] is a subsequence of nums.
 *
 * Check if nums is the shortest possible and the only supersequence. The shortest supersequence is a sequence
 * with the shortest length and has all sequences[i] as subsequences.
 * There could be multiple valid supersequences for the given array sequences.
 *
 * For example, for sequences = [[1,2],[1,3]], there are two shortest supersequences, [1,2,3] and [1,3,2].
 * While for sequences = [[1,2],[1,3],[1,2,3]], the only shortest supersequence possible is [1,2,3].
 * [1,2,3,4] is a possible supersequence but not the shortest.
 *
 * Return true if nums is the only shortest supersequence for sequences, or false otherwise.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements
 * without changing the order of the remaining elements.
 */

public class Solution {

    public boolean sequenceReconstruction(int[] nums, List<List<Integer>> sequences) {
        int[] inDegree = new int[nums.length + 1];
        Set<Integer>[] edges = new Set[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) edges[i] = new HashSet<>();
        for (var sequence : sequences) {
            for (int i = 1; i < sequence.size(); i++) {
                edges[sequence.get(i - 1)].add(sequence.get(i));
                inDegree[sequence.get(i)]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= nums.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        if (queue.size() != 1) return false;

        int ref = 0;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            if (nums[ref++] != curr) return false;
            for (var next : edges[curr]) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
            if (queue.size() > 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().sequenceReconstruction(new int[]{4,1,5,2,6,3}, List.of(
            List.of(5,2,6,3), List.of(4,1,5,2)
        ));
    }

}
