package weekly.bw155.C;

import java.util.*;
import java.util.function.Function;

/**
 * 3529. Count Cells in Overlapping Horizontal and Vertical Substrings
 *
 * https://leetcode.cn/contest/biweekly-contest-155/problems/count-cells-in-overlapping-horizontal-and-vertical-substrings/
 *
 * You are given an m x n matrix grid consisting of characters and a string pattern.
 *
 * A horizontal substring is a contiguous sequence of characters read from left to right.
 * If the end of a row is reached before the substring is complete, it wraps to the first
 * column of the next row and continues as needed. You do not wrap from the bottom row back to the top.
 *
 * A vertical substring is a contiguous sequence of characters read from top to bottom.
 * If the bottom of a column is reached before the substring is complete, it wraps to the
 * first row of the next column and continues as needed. You do not wrap from the last column back to the first.
 *
 * Count the number of cells in the matrix that satisfy the following condition:
 *
 * The cell must be part of at least one horizontal substring and at least one
 * vertical substring, where both substrings are equal to the given pattern.
 *
 * Return the count of these cells.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public int countCells(char[][] grid, String pattern) {
        int m = grid.length, n = grid[0].length;
        // 找到满足 pattern 的即属于水平字符串又属于垂直字符串的单元格数量
        //  - 找到所有满足 pattern 的水平字符串和垂直字符串的起始位置

        // 水平模式
        char[] horizontal = new char[m * n];
        for (int i = 0, x = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                horizontal[x++] = grid[i][j];
            }
        }
        var seen = match(horizontal, pattern.toCharArray(), (idx) -> ((idx / n) << 16) | (idx % n));

        // 垂直模式
        char[] vertical = new char[m * n];
        for (int j = 0, x = 0; j < n; j++) {
            for (char[] row : grid) {
                vertical[x++] = row[j];
            }
        }

        int ans = 0;
        for (var curr : match(vertical, pattern.toCharArray(), (idx) -> ((idx % m) << 16) | (idx / m))) {
            if (seen.contains(curr)) ans++;
        }

        return ans;
    }

    private Set<Integer> match(char[] target, char[] pattern, Function<Integer, Integer> transform) {
        int last = -1;
        Set<Integer> ans = new HashSet<>();
        for (var idx : kmp(target, pattern)) { // 所有匹配 pattern 的起始位置
            for (int i = Math.max(idx, last); i < idx + pattern.length; i++) {
                ans.add(transform.apply(i));
            }
            last = idx + pattern.length;
        }

        return ans;
    }

    /** @noinspection DuplicatedCode*/
    private List<Integer> kmp(char[] target, char[] pattern) {
        if (pattern.length == 0) return Collections.emptyList();
        if (pattern.length > target.length) return Collections.emptyList();

        char[] combine = new char[target.length + pattern.length + 1];
        System.arraycopy(pattern, 0, combine, 0, pattern.length);
        combine[pattern.length] = '#';
        System.arraycopy(target, 0, combine, pattern.length + 1, target.length);

        int[] next = lps(combine);
        List<Integer> ans = new ArrayList<>();
        for (int i = pattern.length + 1; i <= target.length + pattern.length; i++) {
            if (next[i] == pattern.length) ans.add(i - 2 * pattern.length);
        }

        return ans;
    }

    /** @noinspection DuplicatedCode*/
    private int[] lps(char[] array) {
        int[] next = new int[array.length];
        for (int i = 1, j = 0; i < array.length; ) {
            if (array[i] == array[j]) {
                next[i++] = ++j;
            } else {
                if (j != 0) j = next[j - 1];
                else next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        assert new Solution().countCells(new char[][]{
            {'a','a','c','c'},
            {'b','b','b','c'},
            {'a','a','b','a'},
            {'c','a','a','c'},
            {'a','a','b','a'}
        }, "abaca") == 1;
        assert new Solution().countCells(new char[][]{
            {'c','a','a','a'},
            {'a','a','b','a'},
            {'b','b','a','a'},
            {'a','a','b','a'}
        }, "aba") == 4;
        assert new Solution().countCells(new char[][]{{'a'}}, "a") == 1;
    }

}
