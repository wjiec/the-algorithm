package lcci.s8.p10colorfilllcci;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 面试题 08.10. 颜色填充
 *
 * https://leetcode-cn.com/problems/color-fill-lcci/
 *
 * 编写函数，实现许多图片编辑软件都支持的「颜色填充」功能。
 *
 * 待填充的图像用二维数组 image 表示，元素为初始颜色值。初始坐标点的行坐标为 sr 列坐标为 sc。需要填充的新颜色为 newColor 。
 *
 * 「周围区域」是指颜色相同且在上、下、左、右四个方向上存在相连情况的若干元素。
 *
 * 请用新颜色填充初始坐标点的周围区域，并返回填充后的图像。
 */

public class Solution {

    private final int[] dx = new int[]{0, 1, 0, -1};
    private final int[] dy = new int[]{1, 0, -1, 0};

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int m = image.length, n = image[0].length;
        Queue<int[]> queue = new ArrayDeque<>(); queue.add(new int[]{sr, sc});
        while (!queue.isEmpty()) {
            var coordinate = queue.remove();
            int x = coordinate[0], y = coordinate[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (0 <= a && a < m && 0 <= b && b < n && image[a][b] == image[x][y] && image[a][b] != newColor) {
                    queue.add(new int[]{a, b});
                }
            }
            image[x][y] = newColor;
        }

        return image;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().floodFill(new int[][]{
            {1,1,1},
            {1,1,0},
            {1,0,1}
        }, 1, 1, 2), new int[][]{
            {2,2,2},
            {2,2,0},
            {2,0,1}
        });
    }

}
