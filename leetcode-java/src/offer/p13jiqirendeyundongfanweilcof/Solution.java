package offer.p13jiqirendeyundongfanweilcof;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer 13. 机器人的运动范围
 *
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
 *
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */

public class Solution {

    private final int[] dx = new int[]{0, 1, 0, -1};
    private final int[] dy = new int[]{1, 0, -1, 0};

    public int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            int rowSum = digitSum(i);
            for (int j = 0; j < n; j++) {
                if (rowSum + digitSum(j) <= k) visited[i][j] = true;
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0});

        int count = 0;
        while (!queue.isEmpty()) {
            int[] xy = queue.remove();
            if (visited[xy[0]][xy[1]]) {
                count++;

                visited[xy[0]][xy[1]] = false;
                for (int i = 0; i < 4; i++) {
                    int x = xy[0] + dx[i], y = xy[1] + dy[i];
                    if (x >= 0 && y >= 0 && x < m && y < n) {
                        if (visited[x][y]) queue.add(new int[]{x, y});
                    }
                }
            }
        }
        return count;
    }

    private int digitSum(int a) {
        if (a < 10) return a;
        if (a < 100) return (a / 10) + (a % 10);
        return 1;
    }

    public static void main(String[] args) {
        assert new Solution().movingCount(36, 11, 15) == 362;
        assert new Solution().movingCount(14, 14, 5) == 21;
        assert new Solution().movingCount(38, 15, 9) == 135;
        assert new Solution().movingCount(16, 8, 4) == 15;
        assert new Solution().movingCount(2, 3, 1) == 3;
        assert new Solution().movingCount(3, 1, 0) == 1;
    }

}
