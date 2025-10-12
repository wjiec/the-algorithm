package weekly.w464.D;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Q4. Maximum Walls Destroyed by Robots
 *
 * https://leetcode.cn/contest/weekly-contest-464/problems/maximum-walls-destroyed-by-robots/
 *
 * There is an endless straight line populated with some robots and walls.
 * You are given integer arrays robots, distance, and walls:
 *
 * robots[i] is the position of the ith robot.
 * distance[i] is the maximum distance the ith robot's bullet can travel.
 * walls[j] is the position of the jth wall.
 *
 * Every robot has one bullet that can either fire to the left or the right at most distance[i] meters.
 *
 * A bullet destroys every wall in its path that lies within its range.
 * Robots are fixed obstacles: if a bullet hits another robot before reaching a wall,
 * it immediately stops at that robot and cannot continue.
 *
 * Return the maximum number of unique walls that can be destroyed by the robots.
 *
 * Notes:
 *
 * A wall and a robot may share the same position; the wall can be destroyed by the robot at that position.
 *
 * Robots are not destroyed by bullets.
 */

public class Solution {

    private record Robot(int x, int d) {}

    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        // 一个机器人 i 所在的位置是 x, 我们有以下规定
        //  - 它往右最多可以摧毁 min(r[i] + d[i], r[i + 1] - d[i + 1]), 也就是右边的墙壁尽可能让右边的机器人来摧毁
        //  - 它往左最多可以摧毁 max(r[i] - d[i], r[i - 1]), 也就是尽可能摧毁左边的墙壁, 但是不能超过左边机器人的位置
        Robot[] rs = new Robot[robots.length];
        for (int i = 0; i < robots.length; i++) rs[i] = new Robot(robots[i], distance[i]);
        Arrays.sort(rs, Comparator.comparing(r -> r.x)); Arrays.sort(walls);

        int[] memo = new int[robots.length * 2];
        Arrays.fill(memo, -1);

        return dfs(rs, walls, robots.length - 1, 1, memo);
    }

    // j = 0 表示机器人 i 往左射击, j = 1 表示机器人 i 往右设计
    private int dfs(Robot[] robots, int[] walls, int i, int j, int[] memos) {
        if (i < 0) return 0;
        if (memos[(i << 1) | j] != -1) return memos[(i << 1) | j];

        // 机器人 i 往左射击, 可以射击的墙的范围是 max(x - d, leftRobotX + 1)
        int leftMax = robots[i].x - robots[i].d;
        if (i > 0) leftMax = Math.max(leftMax, robots[i - 1].x + 1);

        // 往左射击, [leftMax, i) 的所有墙壁都可以摧毁
        int left = lowerBound(walls, leftMax);
        int curr = lowerBound(walls, robots[i].x + 1);
        int leftAns = dfs(robots, walls, i - 1, 0, memos) + curr - left;

        // 机器人 i 往右射击, 可以射击的墙的范围是 min(x + d, rightRobot_Left)
        int rightMax = robots[i].x + robots[i].d;
        if (i + 1 < robots.length) {
            int rightRobotLeft = robots[i + 1].x;
            // 右边的机器人正在朝左射击, 则右边机器人射击的距离是完整的
            if (j == 0) rightRobotLeft -= robots[i + 1].d;
            // 不能超过右边机器人已经射击掉的墙壁
            rightMax = Math.min(rightMax, rightRobotLeft - 1);
        }

        // 往右射击, (i, rightMax] 的所有墙壁都可以摧毁
        int right = lowerBound(walls, rightMax + 1);
        curr = lowerBound(walls, robots[i].x);
        int rightAns = dfs(robots, walls, i - 1, 1, memos) + right - curr;

        return memos[(i << 1) | j] = Math.max(leftAns, rightAns);
    }

    @SuppressWarnings("DuplicatedCode")
    private int lowerBound(int[] nums, int target) {
        int l = -1, r = nums.length;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] >= target) r = mid;
            else l = mid;
        }
        return r;
    }

    public static void main(String[] args) {
    }

}
