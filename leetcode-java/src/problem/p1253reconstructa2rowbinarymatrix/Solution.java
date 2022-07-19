package problem.p1253reconstructa2rowbinarymatrix;

import common.PrettyPrinter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 1253. Reconstruct a 2-Row Binary Matrix
 *
 * https://leetcode.cn/problems/reconstruct-a-2-row-binary-matrix/
 *
 * Given the following details of a matrix with n columns and 2 rows :
 *
 * The matrix is a binary matrix, which means each element in the matrix can be 0 or 1.
 * The sum of elements of the 0-th(upper) row is given as upper.
 * The sum of elements of the 1-st(lower) row is given as lower.
 * The sum of elements in the i-th column(0-indexed) is colsum[i], where
 * colsum is given as an integer array with length n.
 * Your task is to reconstruct the matrix with upper, lower and colsum.
 *
 * Return it as a 2-D integer array.
 *
 * If there are more than one valid solution, any of them will be accepted.
 *
 * If no valid solution exists, return an empty 2-D array.
 */

public class Solution {

    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        List<Integer> a = new ArrayList<>(colsum.length);
        List<Integer> b = new ArrayList<>(colsum.length);

        for (int i = 0; i < colsum.length; i++) {
            if (colsum[i] == 0) { a.set(i, 0); b.set(i, 0); }
            if (colsum[i] == 2) { a.set(i, 1); b.set(i, 1); upper--; lower--; }
        }
        if (upper < 0 || lower < 0) return Collections.emptyList();

        for (int i = 0; i < colsum.length && upper > 0; i++) {
            if (a.get(i) == null) { a.set(i, 1); upper--; }
        }

        for (int i = 0; i < colsum.length && lower > 0; i++) {
            if (b.get(i) == null && a.get(i) != 1) {
                b.set(i, 1); lower--;
            }
        }

        if (upper != 0 || lower != 0) return Collections.emptyList();
        return List.of(a, b);
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().reconstructMatrix(2, 1, new int[]{1, 1, 1}));
        PrettyPrinter.println(new Solution().reconstructMatrix(2, 3, new int[]{2, 2, 1, 1}));
        PrettyPrinter.println(new Solution().reconstructMatrix(5, 5, new int[]{2,1,2,0,1,0,1,2,0,1}));
    }

}
