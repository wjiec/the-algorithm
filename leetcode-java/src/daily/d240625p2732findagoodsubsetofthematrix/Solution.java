package daily.d240625p2732findagoodsubsetofthematrix;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2732. Find a Good Subset of the Matrix
 *
 * https://leetcode.cn/problems/find-a-good-subset-of-the-matrix
 *
 * You are given a 0-indexed m x n binary matrix grid.
 *
 * Let us call a non-empty subset of rows good if the sum of each column of the subset is at
 * most half of the length of the subset.
 *
 * More formally, if the length of the chosen subset of rows is k, then the sum of each column
 * should be at most floor(k / 2).
 *
 * Return an integer array that contains row indices of a good subset sorted in ascending order.
 *
 * If there are multiple good subsets, you can return any of them.
 * If there are no good subsets, return an empty array.
 *
 * A subset of rows of the matrix grid is any matrix that can be obtained by
 * deleting some (possibly none or all) rows from grid.
 */

public class Solution {

    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int[] numbers = new int[grid.length];
        for (int i = 0; i < grid.length; i++) {
            for (var v : grid[i]) numbers[i] = (numbers[i] << 1) | v;
            if (numbers[i] == 0) return List.of(i);
        }

        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if ((numbers[i] & numbers[j]) == 0) {
                    return List.of(i, j);
                }
            }
        }

        return Collections.emptyList();
    }

    private static class Classify {
        public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
            int[] masks = new int[1 << grid[0].length];
            Arrays.fill(masks, -1);

            for (int i = 0; i < grid.length; i++) {
                int curr = 0;
                for (var v : grid[i]) curr = (curr << 1) | v;
                if (curr == 0) return List.of(i);
                if (masks[curr] == -1) masks[curr] = i;
            }

            for (int i = 1; i < masks.length; i++) {
                if (masks[i] == -1) continue;
                for (int j = i + 1; j < masks.length; j++) {
                    if (masks[j] == -1) continue;
                    if ((i & j) == 0) {
                        return List.of(Math.min(masks[i], masks[j]), Math.max(masks[i], masks[j]));
                    }
                }
            }
            return Collections.emptyList();
        }
    }

    public static void main(String[] args) {
    }

}
