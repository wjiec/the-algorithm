package weekly.w401.A;

/**
 * 3178. Find the Child Who Has the Ball After K Seconds
 *
 * https://leetcode.cn/contest/weekly-contest-401/problems/find-the-child-who-has-the-ball-after-k-seconds/
 *
 * You are given two positive integers n and k. There are n children numbered
 * from 0 to n - 1 standing in a queue in order from left to right.
 *
 * Initially, child 0 holds a ball and the direction of passing the
 * ball is towards the right direction. After each second, the child
 * holding the ball passes it to the child next to them.
 *
 * Once the ball reaches either end of the line, i.e. child 0 or child n - 1,
 * the direction of passing is reversed.
 *
 * Return the number of the child who receives the ball after k seconds.
 */

public class Solution {

    public int numberOfChild(int n, int k) {
        int ans = 0, dx = 1;
        for (int i = 0; i < k; i++) {
            ans += dx;
            if (ans == 0) dx = 1;
            if (ans == n - 1) dx = -1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
