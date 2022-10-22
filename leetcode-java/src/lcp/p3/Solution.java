package lcp.p3;

/**
 * LCP 03. 机器人大冒险
 *
 * https://leetcode.cn/problems/programmable-robot/
 *
 * 力扣团队买了一个可编程机器人，机器人初始位置在原点(0, 0)。小伙伴事先给机器人
 * 输入一串指令command，机器人就会无限循环这条指令的步骤进行移动。指令有两种：
 *
 * U: 向y轴正方向移动一格
 * R: 向x轴正方向移动一格。
 * 不幸的是，在 xy 平面上还有一些障碍物，他们的坐标用obstacles表示。机器人一旦碰到障碍物就会被损毁。
 *
 * 给定终点坐标(x, y)，返回机器人能否完好地到达终点。如果能，返回true；否则返回false。
 */

public class Solution {

    public boolean robot(String command, int[][] obstacles, int x, int y) {
        int dx = 0, dy = 0;
        char[] chars = command.toCharArray();
        for (var c : chars) if (c == 'U') dy++; else dx++;

        if (!reached(chars, dx, dy, x, y)) return false;
        for (var obstacle : obstacles) {
            if (obstacle[0] > x || obstacle[1] > y) continue;
            if (reached(chars, dx, dy, obstacle[0], obstacle[1])) return false;
        }
        return true;
    }

    private boolean reached(char[] command, int dx, int dy, int x, int y) {
        int rounds = Math.min(x / dx, y / dy);

        int sx = rounds * dx, sy = rounds * dy;
        if (sx == x && sy == y) return true;

        for (var c : command) {
            if (c == 'U') sy++; else sx++;
            if (sx == x && sy == y) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().robot("URR", new int[][]{}, 3, 2);
        assert !new Solution().robot("URR", new int[][]{{2, 2}}, 3, 2);
        assert new Solution().robot("URR", new int[][]{{4, 2}}, 3, 2);
    }

}
