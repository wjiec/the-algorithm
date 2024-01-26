package weekly.w381.B;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 3015. Count the Number of Houses at a Certain Distance I
 *
 * https://leetcode.cn/contest/weekly-contest-381/problems/count-the-number-of-houses-at-a-certain-distance-i/
 *
 * You are given three positive integers n, x, and y.
 *
 * In a city, there exist houses numbered 1 to n connected by n streets.
 * There is a street connecting the house numbered i with the house
 * numbered i + 1 for all 1 <= i <= n - 1 . An additional street connects
 * the house numbered x with the house numbered y.
 *
 * For each k, such that 1 <= k <= n, you need to find the number of pairs of
 * houses (house1, house2) such that the minimum number of streets that need
 * to be traveled to reach house2 from house1 is k.
 *
 * Return a 1-indexed array result of length n where result[k] represents the
 * total number of pairs of houses such that the minimum streets required to
 * reach one house from the other is k.
 *
 * Note that x and y can be equal.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final static int[] dirs = new int[]{1, 0, -1};

    public int[] countOfPairs(int n, int x, int y) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = Math.abs(i - j);
            }
        }

        // 开始走捷径
        if (x != y) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[]{x, y, 1});
            while (!queue.isEmpty()) {
                int[] curr = queue.remove();
                int cx = curr[0], cy = curr[1], cost = curr[2];
                dp[cx][cy] = dp[cy][cx] = Math.min(dp[cy][cx], Math.min(dp[cx][cy], cost));

                for (var dx : dirs) {
                    int nx = cx + dx;
                    for (var dy : dirs) {
                        int ny = cy + dy;
                        int nc = cost + Math.abs(dx) + Math.abs(dy);
                        if (nx > 0 && nx <= n && ny > 0 && ny <= n && nc < dp[nx][ny]) {
                            dp[nx][ny] = dp[ny][nx] = nc;
                            queue.add(new int[]{nx, ny, nc});
                        }
                    }
                }
            }
        }

        int[] ans = new int[n];
        for (int i = 1; i < dp.length; i++) {
            for (int j = i + 1; j < dp.length; j++) {
                ans[dp[i][j] - 1] += 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countOfPairs(3, 1, 3), new int[]{6, 0, 0});
        assert Checker.check(new Solution().countOfPairs(3, 1, 3), new int[]{6, 0, 0});
        assert Checker.check(new Solution().countOfPairs(5, 2, 4), new int[]{10,8,2,0,0});
        assert Checker.check(new Solution().countOfPairs(4, 1, 1), new int[]{6,4,2,0});
    }

}
