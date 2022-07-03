package weekly.w300.B;

import common.ListNode;
import common.PrettyPrinter;

import java.util.Arrays;

/**
 * 6111. Spiral Matrix IV
 *
 * https://leetcode.cn/contest/weekly-contest-300/problems/spiral-matrix-iv/
 *
 * You are given two integers m and n, which represent the dimensions of a matrix.
 *
 * You are also given the head of a linked list of integers.
 *
 * Generate an m x n matrix that contains the integers in the linked list presented in spiral
 * order (clockwise), starting from the top-left of the matrix.
 *
 * If there are remaining empty spaces, fill them with -1.
 *
 * Return the generated matrix.
 */

public class Solution {

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(ans[i], -1);
        if (m == 1 && n == 1) {
            ans[0][0] = head.val;
            return ans;
        }

        int a = 0, b = 0, c = m - 1, d = n - 1;
        while (head != null) {
            // top
            for (int i = b; i < d && head != null; i++) {
                ans[a][i] = head.val;
                head = head.next;
            }
            // right
            for (int i = a; i < c && head != null; i++) {
                ans[i][d] = head.val;
                head = head.next;
            }
            // bottom
            for (int i = d; i > b && head != null; i--) {
                ans[c][i] = head.val;
                head = head.next;
            }
            // left
            for (int i = c; i > a && head != null; i--) {
                ans[i][b] = head.val;
                head = head.next;
            }

            a++; b++; c--; d--;
        }

        return ans;
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().spiralMatrix(1, 1, ListNode.build(1, 2)));
    }

}
