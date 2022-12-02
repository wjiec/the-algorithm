package lcci.s16.p19pondsizeslcci;

import common.Checker;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 面试题 16.19. 水域大小
 *
 * https://leetcode.cn/problems/pond-sizes-lcci/
 *
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。
 * 由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。
 * 编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 */

public class Solution {

    public int[] pondSizes(int[][] land) {
        int m = land.length, n = land[0].length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 0) {
                    list.add(walk(land, i, j));
                }
            }
        }

        list.sort(Integer::compare);
        int[] ans = new int[list.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }

        return ans;
    }

    private final int[][] dirs = new int[][]{
        {-1, -1}, {-1, +0}, {-1, +1},
        {+0, -1}, /* OO */  {+0, +1},
        {+1, -1}, {+1, +0}, {+1, +1}
    };

    private int walk(int[][] land, int x, int y) {
        land[x][y] = -1;
        int size = 0, m = land.length, n = land[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (queue.add(new int[]{x, y}); !queue.isEmpty(); ) {
            int[] curr = queue.remove(); size++;
            for (var dir : dirs) {
                int dx = curr[0] + dir[0], dy = curr[1] + dir[1];
                if (dx >= 0 && dx < m && dy >= 0 && dy < n && land[dx][dy] == 0) {
                    land[dx][dy] = -1; queue.add(new int[]{dx, dy});
                }
            }
        }
        return size;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().pondSizes(new int[][]{
            {0,2,1,0},
            {0,1,0,1},
            {1,1,0,1},
            {0,1,0,1}
        }), new int[]{1,2,4});
    }

}
