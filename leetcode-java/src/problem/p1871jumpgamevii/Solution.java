package problem.p1871jumpgamevii;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1871. Jump Game VII
 *
 * https://leetcode.cn/problems/jump-game-vii/
 *
 * You are given a 0-indexed binary string s and two integers minJump and maxJump.
 * In the beginning, you are standing at index 0, which is equal to '0'.
 * You can move from index i to index j if the following conditions are fulfilled:
 *
 * i + minJump <= j <= min(i + maxJump, s.length - 1), and
 * s[j] == '0'.
 * Return true if you can reach index s.length - 1 in s, or false otherwise.
 */

public class Solution {

    public boolean canReach(String s, int minJump, int maxJump) {
        char[] chars = s.toCharArray();
        Queue<Integer> queue = new ArrayDeque<>(); queue.add(0);

        boolean[] dp = new boolean[s.length()]; dp[0] = true;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == '0') {
                int l = i - maxJump, r = i - minJump;
                while (!queue.isEmpty() && queue.peek() < l) queue.remove();
                if (!queue.isEmpty() && queue.peek() <= r) {
                    dp[i] = true; queue.add(i);
                }
            }
        }
        return dp[chars.length - 1];
    }

    public static void main(String[] args) {
        assert !new Solution().canReach("0000000000", 8, 8);
        assert new Solution().canReach("011010", 2, 3);
        assert !new Solution().canReach("01101110", 2, 3);
    }

}
