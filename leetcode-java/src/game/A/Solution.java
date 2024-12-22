package game.A;

import ability.Array;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    // 表示一个 n * n 的网格, rows[i][j] 表示第 i 行有 rows[i][j] 个相连的格子
    // cols[i][j] 表示第 i 列有 cols[i][j] 个相连的格子
    // 每一组相连的格子必须是边界或者是一个空格
    public boolean[][] play(int n, int[][] rows, int[][] cols) {
        int[][] colMap = new int[n][n + 1];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < cols[j].length; k++) {
                colMap[j][cols[j][k]]++;
            }
        }

        int[][] matrix = new int[n + 1][n];
        assert dfs(matrix, 1, n, rows, colMap);

        boolean[][] ans = new boolean[n][n];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i - 1][j] = matrix[i][j] != 0;
            }
        }

        return ans;
    }

    // 枚举每一行的所有可能排列方式, 同时检查每一列是否满足要求
    // matrix[i][j] 表示第到第 i 行时第 j 列此时的长度是多少
    private boolean dfs(int[][] matrix, int i, int n, int[][] rows, int[][] cols) {
        if (i == matrix.length) {
            for (int j = 0; j < n; j++) {
                for (int c = 1; c <= n; c++) {
                    if (cols[j][c] - (matrix[n][j] == c ? 1 : 0) != 0) return false;
                }
            }

            return true;
        }

        // 枚举第 i 行的所有可能排列
        for (var perm : permutation(n, rows[i - 1])) {
            // 按照排列填充上这一行之后计算当前行每一列的数量是否符合要求
            boolean ok = true;
            int[][] next = copy(cols);
            for (int j = 0; j < n; j++) {
                matrix[i][j] = ((perm & (1 << j)) != 0) ? (matrix[i - 1][j] + 1) : 0;

                // 不存在 >= matrix[i][j] 数量的数字
                if (matrix[i][j] != 0 && !ceiling(matrix[i][j], cols[j])) { ok = false; break; }

                // 我们关闭了一个区间, 需要检查是否可以关闭这个区间
                if (matrix[i][j] == 0) {
                    int closed = matrix[i - 1][j];
                    if (closed != 0 && next[j][closed] == 0) { ok = false; break; }
                    if (closed != 0 && next[j][closed] > 0) next[j][closed]--;
                }
            }

            if (ok && dfs(matrix, i + 1, n, rows, next)) return true;
        }

        return false;
    }

    private int[][] copy(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] ans = new int[m][n];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, ans[i], 0, n);
        }
        return ans;
    }

    private boolean ceiling(int n, int[] blocks) {
        while (n < blocks.length && blocks[n] == 0) n++;
        return n < blocks.length;
    }

    private Set<Integer> permutation(int n, int[] array) {
        Set<Integer> ans = new HashSet<>(combination(n, array));
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                Array.swap(array, i, j);
                ans.addAll(combination(n, array));
                Array.swap(array, i, j);
            }
        }

        return ans;
    }

    private List<Integer> combination(int n, int[] array) {
        int total = 0;
        for (var v : array) total += v;

        List<Integer> ans = new ArrayList<>();
        dfs(array, 0, total, 0, n, 0, ans);

        return ans;
    }

    // array 表示按顺序的所有区间的长度, i 表示当前在处理第几个区间, j 表示当前正在填充的位置, n 是位置总数
    private void dfs(int[] array, int i, int r, int j, int n, int mask, List<Integer> ans) {
        if (j + r + (array.length - i - 1) > n) return; // 无法填充
        if (i >= array.length) { ans.add(mask); return; }

        // 直接跳过当前位置
        dfs(array, i, r, j + 1, n, mask, ans);

        // 在当前位置填充
        int len = array[i];
        // 但是这里需要注意保留一个空格作为间隔
        dfs(array, i + 1, r - len, j + len + 1, n, mask | ((1 << len) - 1) << j, ans);
    }

    public static void main(String[] args) {
        int[][] cols = new int[][]{
            {7,7},{2,2,1,1,4},{2,4,2,3},{3,5,2},{1,2,3,2},{2,4,2},{2,1,1},{1,4,2},{3,4,2},{3,1,5,2},{2,2,2,3},{2,2,1,1,3},{7,5},{8,1,1},{9,3}
        };
        int[][] rows = new int[][]{
            {5,7},
            {4,10},
            {1,1,2,2,3},
            {1,1,1,3},
            {3,4,6},
            {3,2,1,4},
            {1,2,1,3,3},
            {1,3,3,1,2},
            {1,3,3,1,1},
            {5,5},
            {1,1,1,2},
            {2,2},
            {3,3,1},
            {6,5,1},
            {11,2}
        };

        var ans = new Solution().play(15, rows, cols);
        SwingUtilities.invokeLater(() -> createAndShowGUI(ans));
    }

    public static void createAndShowGUI(boolean[][] data) {
        JFrame frame = new JFrame("Grid Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GridDisplay(data));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class GridDisplay extends JPanel {
        private final boolean[][] data;

        public GridDisplay(boolean[][] data) {
            this.data = data;
            setPreferredSize(new Dimension(30 * data.length, 30 * data[0].length)); // 设置首选大小
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int rows = data.length;
            int cols = data[0].length;

            int cellWidth = getWidth() / cols;
            int cellHeight = getHeight() / rows;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (data[i][j]) g.setColor(Color.BLACK);
                    else g.setColor(Color.WHITE);

                    g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                }
            }
        }
    }

}
