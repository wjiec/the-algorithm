package problem.p1884eggdropwith2eggsandnfloors;

/**
 * 1884. Egg Drop With 2 Eggs and N Floors
 *
 * https://leetcode.cn/problems/egg-drop-with-2-eggs-and-n-floors/
 *
 * You are given two identical eggs and you have access to a building
 * with n floors labeled from 1 to n.
 *
 * You know that there exists a floor f where 0 <= f <= n such that any egg dropped
 * at a floor higher than f will break, and any egg dropped at or
 * below floor f will not break.
 *
 * In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n).
 * If the egg breaks, you can no longer use it. However, if the egg does not break, you may
 * reuse it in future moves.
 *
 * Return the minimum number of moves that you need to determine with certainty
 * what the value of f is.
 */

public class Solution {

    public int twoEggDrop(int n) {
        int ans = 1, total = 1;
        while (total < n) {
            total += ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().twoEggDrop(2) == 2;
        assert new Solution().twoEggDrop(100) == 14;
        assert new Solution().twoEggDrop(1000) == 45;
    }

}
