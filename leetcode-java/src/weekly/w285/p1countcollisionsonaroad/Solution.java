package weekly.w285.p1countcollisionsonaroad;

import common.PrettyPrinter;

/**
 * 6028. Count Collisions on a Road
 *
 * https://leetcode-cn.com/contest/weekly-contest-285/problems/count-collisions-on-a-road/
 *
 * There are n cars on an infinitely long road. The cars are numbered from 0 to n - 1 from
 * left to right and each car is present at a unique point.
 *
 * You are given a 0-indexed string directions of length n. directions[i] can be either 'L', 'R',
 * or 'S' denoting whether the ith car is moving towards the left, towards the right,
 * or staying at its current point respectively. Each moving car has the same speed.
 *
 * The number of collisions can be calculated as follows:
 *
 * When two cars moving in opposite directions collide with each other, the number of collisions increases by 2.
 * When a moving car collides with a stationary car, the number of collisions increases by 1.
 * After a collision, the cars involved can no longer move and will stay at the point where they collided.
 * Other than that, cars cannot change their state or direction of motion.
 *
 * Return the total number of collisions that will happen on the road.
 */

public class Solution {

    public int countCollisions(String directions) {
        int ans = 0, n = directions.length();
        char[] chars = directions.toCharArray();
        for (int i = 0; i < n - 1; i++) {
            if (chars[i] == 'R' && chars[i + 1] == 'L') {
                ans += 2;
                chars[i] = chars[i + 1] = 'S';
            }
        }
        System.out.println(ans);
        PrettyPrinter.println(chars);

        for (int i = 1; i < n; i++) {
            if (chars[i] == 'L' && chars[i - 1] == 'S') {
                ans++;
                chars[i] = 'S';
            }
        }
        System.out.println(ans);
        PrettyPrinter.println(chars);

        for (int i = n - 2; i >= 0; i--) {
            if (chars[i] == 'R' && chars[i + 1] == 'S') {
                ans++;
                chars[i] = 'S';
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //                                                1    2  1 1  1 1 1  1   2  6      3
        assert new Solution().countCollisions("SSRSSRLLRSLLRSRSSRLRRRRLLRRLSSRR") == 20;

        assert new Solution().countCollisions("RLRSLL") == 5;
        assert new Solution().countCollisions("LLRR") == 0;
    }

}
