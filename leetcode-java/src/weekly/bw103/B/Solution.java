package weekly.bw103.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 2657. Find the Prefix Common Array of Two Arrays
 *
 * https://leetcode.cn/contest/biweekly-contest-103/problems/find-the-prefix-common-array-of-two-arrays/
 *
 * You are given two 0-indexed integer permutations A and B of length n.
 *
 * A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers
 * that are present at or before the index i in both A and B.
 *
 * Return the prefix common array of A and B.
 *
 * A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.
 */

public class Solution {

    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int[] ans = new int[A.length];
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            cnt.merge(A[i], 1, Integer::sum);
            cnt.merge(B[i], -1, Integer::sum);
            for (var v : cnt.values()) if (v == 0) ans[i]++;
        }
        return ans;
    }

}
