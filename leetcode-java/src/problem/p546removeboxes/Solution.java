package problem.p546removeboxes;

/**
 * 546. Remove Boxes
 *
 * https://leetcode.cn/problems/remove-boxes/
 *
 * You are given several boxes with different colors represented by different positive numbers.
 *
 * You may experience several rounds to remove boxes until there is no box left.
 * Each time you can choose some continuous boxes with the same color(i.e., composed
 * of k boxes, k >= 1), remove them and get k * k points.
 *
 * Return the maximum points you can get.
 */

public class Solution {

    private final int[][][] dp = new int[101][101][101];

    public int removeBoxes(int[] boxes) {
        return dfs(boxes, 0, boxes.length - 1, 0);
    }

    // dp[l][r][k] 表示移除区间 [l, r) 且移除右侧等与 boxes[r - 1] 的
    // k 个元素组成的最大积分
    private int dfs(int[] boxes, int l, int r, int k) {
        if (l > r) return 0;

        if (dp[l][r][k] == 0) {
            dp[l][r][k] = dfs(boxes, l, r - 1, 0) + (k + 1) * (k + 1);
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    dp[l][r][k] = Math.max(dp[l][r][k], dfs(boxes, l, i, k + 1) + dfs(boxes, i + 1, r - 1, 0));
                }
            }
        }
        return dp[l][r][k];
    }

    public static void main(String[] args) {
        assert new Solution().removeBoxes(new int[]{1,3,2,2,2,3,4,3,1}) == 23;
        assert new Solution().removeBoxes(new int[]{1,1,1}) == 9;
        assert new Solution().removeBoxes(new int[]{1}) == 1;
    }

}
