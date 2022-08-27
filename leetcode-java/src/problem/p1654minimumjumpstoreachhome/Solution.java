package problem.p1654minimumjumpstoreachhome;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1654. Minimum Jumps to Reach Home
 *
 * https://leetcode.cn/problems/minimum-jumps-to-reach-home/
 *
 * A certain bug's home is on the x-axis at position x. Help them get there from position 0.
 *
 * The bug jumps according to the following rules:
 *
 * It can jump exactly a positions forward (to the right).
 * It can jump exactly b positions backward (to the left).
 * It cannot jump backward twice in a row.
 * It cannot jump to any forbidden positions.
 * The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.
 *
 * Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the
 * position forbidden[i], and integers a, b, and x, return the minimum number of jumps needed
 * for the bug to reach its home. If there is no possible sequence of jumps that lands
 * the bug on position x, return -1.
 */

public class Solution {

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        if (x == 0) return 0;

        // 1: right, 2: left, 3: left+right
        int[] visited = new int[6001]; visited[0] = 3;
        for (var ban : forbidden) visited[ban] = visited[ban] = 3;

        // [curr, step, type] 0: right, 1: left
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{0, 0, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.remove();

            int l = curr[0] - b, r = curr[0] + a;
            // 往右都是可以的, 除非超过界限
            if (r < visited.length && (visited[r] & 1) == 0) {
                if (r == x) return curr[1] + 1;

                visited[r] |= 1;
                queue.add(new int[]{r, curr[1] + 1, 0});
            }
            // 上一次是往右跳的本次才可以往左跳
            if (l >= 0 && (visited[l] & 2) == 0 && curr[2] == 0) {
                if (l == x) return curr[1] + 1;

                visited[l] |= 2;
                queue.add(new int[]{l, curr[1] + 1, 1});
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minimumJumps(new int[]{1998}, 1999, 2000, 2000) == 3998;

        assert new Solution().minimumJumps(new int[]{14,4,18,1,15}, 3, 15, 9) == 3;
        assert new Solution().minimumJumps(new int[]{8,3,16,6,12,20}, 15, 13, 11) == -1;
        assert new Solution().minimumJumps(new int[]{1,6,2,14,5,17,4}, 16, 9, 7) == 2;
    }

}
