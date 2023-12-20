package weekly.w376.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 2965. Find Missing and Repeated Values
 *
 * https://leetcode.cn/contest/weekly-contest-376/problems/find-missing-and-repeated-values/
 *
 * You are given a 0-indexed 2D integer matrix grid of size n * n with values in the range [1, n2].
 *
 * Each integer appears exactly once except a which appears twice and b which is missing.
 *
 * The task is to find the repeating and missing numbers a and b.
 *
 * Return a 0-indexed integer array ans of size 2 where ans[0] equals to a and ans[1] equals to b.
 */

public class Solution {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int a = 0;
        Set<Integer> set = new HashSet<>();
        for (var row : grid) {
            for (var v : row) {
                if (!set.add(v)) {
                    a = v;
                }
            }
        }

        int b = 1;
        while (set.contains(b)) b++;
        return new int[]{a, b};
    }

    public static void main(String[] args) {
    }

}
