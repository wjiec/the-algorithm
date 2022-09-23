package problem.p1899mergetripletstoformtargettriplet;

/**
 * 1899. Merge Triplets to Form Target Triplet
 *
 * https://leetcode.cn/problems/merge-triplets-to-form-target-triplet/
 *
 * A triplet is an array of three integers. You are given a 2D integer array triplets, where
 * triplets[i] = [ai, bi, ci] describes the ith triplet. You are also given an integer array
 * target = [x, y, z] that describes the triplet you want to obtain.
 *
 * To obtain target, you may apply the following operation on triplets any number of times (possibly zero):
 *
 * Choose two indices (0-indexed) i and j (i != j) and update triplets[j] to
 * become [max(ai, aj), max(bi, bj), max(ci, cj)].
 * For example, if triplets[i] = [2, 5, 3] and triplets[j] = [1, 7, 5], triplets[j] will be
 * updated to [max(2, 1), max(5, 7), max(3, 5)] = [2, 7, 5].
 * Return true if it is possible to obtain the target triplet [x, y, z] as an element of
 * triplets, or false otherwise.
 */

public class Solution {

    public boolean mergeTriplets(int[][] triplets, int[] target) {
        int a = 0, b = 0, c = 0;
        int x = target[0], y = target[1], z = target[2];
        for (var triple : triplets) {
            if (triple[0] <= x && triple[1] <= y && triple[2] <= z) {
                a = Math.max(a, triple[0]);
                b = Math.max(b, triple[1]);
                c = Math.max(c, triple[2]);
            }
        }
        return a == x && b == y && c == z;
    }

    public static void main(String[] args) {
        assert !new Solution().mergeTriplets(new int[][]{{3,5,1},{10,5,7}}, new int[]{3,5,7});

        assert new Solution().mergeTriplets(new int[][]{{2,5,3},{1,8,4},{1,7,5}}, new int[]{2,7,5});
        assert new Solution().mergeTriplets(new int[][]{{1,3,4},{2,5,8}}, new int[]{2,5,8});
        assert new Solution().mergeTriplets(new int[][]{{2,5,3},{2,3,4},{1,2,5},{5,2,3}}, new int[]{5,5,5});
        assert !new Solution().mergeTriplets(new int[][]{{3,4,5},{4,5,6}}, new int[]{3,2,5});
    }

}
