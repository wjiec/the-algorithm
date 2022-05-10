package problem.p756pyramidtransitionmatrix;

import java.util.*;

/**
 * 756. Pyramid Transition Matrix
 *
 * https://leetcode.cn/problems/pyramid-transition-matrix/
 *
 * You are stacking blocks to form a pyramid.
 * Each block has a color, which is represented by a single letter.
 * Each row of blocks contains one less block than the row beneath it and is centered on top.
 *
 * To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed.
 * A triangular pattern consists of a single block stacked on top of two blocks.
 * The patterns are given as a list of three-letter strings allowed,
 * where the first two characters of a pattern represent the left and right bottom blocks respectively,
 * and the third character is the top block.
 *
 * For example, "ABC" represents a triangular pattern
 * with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block.
 * Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
 * You start with a bottom row of blocks bottom, given as a single string,
 * that you must use as the base of the pyramid.
 *
 * Given bottom and allowed, return true if you can build the pyramid all
 * the way to the top such that every triangular pattern in the pyramid is in allowed,
 * or false otherwise.
 */

public class Solution {

    private final Map<Character, Map<Character, Set<Character>>> map = new HashMap<>();

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        if (bottom.length() == 1) return true;
        for (var allow : allowed)
            map.computeIfAbsent(allow.charAt(0), v -> new HashMap<>())
                .computeIfAbsent(allow.charAt(1), v -> new HashSet<>())
                .add(allow.charAt(2));

        char[][] chars = new char[bottom.length()][bottom.length()];
        System.arraycopy(bottom.toCharArray(), 0, chars[0], 0, bottom.length());

        return bfs(chars, 0, 0, bottom.length());
    }

    private boolean bfs(char[][] grid, int row, int col, int n) {
        if (n == 1) return true;
        if (col == n - 1) return bfs(grid, row + 1, 0, n - 1);

        Map<Character, Set<Character>> right = map.get(grid[row][col]);
        if (right == null) return false;

        Set<Character> chars = right.get(grid[row][col + 1]);
        if (chars == null) return false;

        for (var c : chars) {
            grid[row + 1][col] = c;
            if (bfs(grid, row, col + 1, n)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().pyramidTransition("BCD", List.of("BCC","CDE","CEA","FFF"));
        assert !new Solution().pyramidTransition("AAAA", List.of("AAB","AAC","BCD","BBE","DEF"));
    }

}
