package weekly.bw144.D;

import ability.Benchmark;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    public int maxCollectedFruits(int[][] fruits) {
        n = fruits.length; this.fruits = fruits;
        // 从 (0, 0) 出发的小朋友只能按照 (i + 1, j + 1) 的方向前进才有可能
        // 在 n - 1 步到达 (n - 1, n - 1)
        int ans = 0;
        for (int i = 0; i < n; i++) { ans += fruits[i][i]; fruits[i][i] = 0; }

        // 另外两位小朋友最多只能走 n / 2 次斜对角(每个方向), 这意味着肯定走不到对方的区域
        // 所以直接枚举剩下的两个小朋友的所有可能路径即可
        return ans
            + dfs(0, n - 1,new int[]{1, -1}, new int[]{1, 1}, new int[]{1, 0}, true)
            + dfs(n - 1, 0, new int[]{-1, 1}, new int[]{1, 1}, new int[]{0, 1}, false);
    }

    private int n = 0;
    private int[][] fruits = null;
    private final Map<Integer, Integer> memo = new HashMap<>();

    private int dfs(int x, int y, int[] up, int[] down, int[] direct, boolean rightTop) {
        if (x == n - 1 && y == n - 1) return 0;
        if (x < 0 || x >= n || y < 0 || y >= n || (rightTop != x < y)) return Integer.MIN_VALUE;

        int key = (x << 16) | y;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = dfs(x + direct[0], y + direct[1], up, down, direct, rightTop);
        ans = Math.max(ans, dfs(x + up[0], y + up[1], up, down, direct, rightTop));
        ans = Math.max(ans, dfs(x + down[0], y + down[1], up, down, direct, rightTop));

        memo.put(key, ans + fruits[x][y]);
        return ans + fruits[x][y];
    }

    private static class Iteration {
        public int maxCollectedFruits(int[][] fruits) {
            int n = fruits.length, ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < j && i + j < n - 1) fruits[i][j] = 0; // Top
                    if (i > j && i + j < n - 1) fruits[i][j] = 0; // Left
                    if (i == j) { ans += fruits[i][j]; fruits[i][j] = 0; }

                    if (i < j && i + j >= n - 1 && i > 0) { // Right
                        // 右上角的小朋友需要进行转移
                        int value = fruits[i][j];
                        fruits[i][j] = value + Math.max(fruits[i - 1][j], fruits[i - 1][j - 1]);
                        if (j + 1 < n) fruits[i][j] = Math.max(fruits[i][j], value + fruits[i - 1][j + 1]);
                    }
                }
            }

            for (int j = 0; j < n; j++) {
                for (int i = 0; i < n; i++) {
                    if (i > j && i + j >= n - 1 && j > 0) { // Bottom
                        // 左下角的小朋友需要进行转移
                        int value = fruits[i][j];
                        fruits[i][j] = value + Math.max(fruits[i][j - 1], fruits[i - 1][j - 1]);
                        if (i + 1 < n) fruits[i][j] = Math.max(fruits[i][j], value + fruits[i + 1][j - 1]);
                    }
                }
            }

            return ans + fruits[n - 1][n - 2] + fruits[n - 2][n - 1];
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Iteration", () -> {
            assert new Iteration().maxCollectedFruits(new int[][]{{16,9,1,2,3},{5,14,12,1,13},{3,12,20,3,10},{12,1,16,5,13},{2,19,11,9,10}}) == 150;

            assert new Iteration().maxCollectedFruits(new int[][]{{1,2,3,4},{5,6,8,7},{9,10,11,12},{13,14,15,16}}) == 100;
            assert new Iteration().maxCollectedFruits(new int[][]{{1,1},{1,1}}) == 4;
        });

        Benchmark.benchmark("normal", () -> {
            assert new Solution().maxCollectedFruits(new int[][]{{1,2,3,4},{5,6,8,7},{9,10,11,12},{13,14,15,16}}) == 100;
            assert new Solution().maxCollectedFruits(new int[][]{{1,1},{1,1}}) == 4;
        });
    }

}
