package problem.p1535findthewinnerofanarraygame;

/**
 * 1535. Find the Winner of an Array Game
 *
 * https://leetcode.cn/problems/find-the-winner-of-an-array-game/
 *
 * Given an integer array arr of distinct integers and an integer k.
 *
 * A game will be played between the first two elements of the array (i.e. arr[0] and arr[1]).
 * In each round of the game, we compare arr[0] with arr[1], the larger integer wins and
 * remains at position 0, and the smaller integer moves to the end of the array.
 *
 * The game ends when an integer wins k consecutive rounds.
 *
 * Return the integer which will win the game.
 *
 * It is guaranteed that there will be a winner of the game.
 */

public class Solution {

    public int getWinner(int[] arr, int k) {
        int max = Math.max(arr[0], arr[1]);
        if (k == 1) return max;

        int ans = max, count = 1;
        for (int i = 2; i < arr.length; i++) {
            if (max > arr[i]) {
                if (++count == k) return max;
            } else {
                max = arr[i]; count = 1;
            }
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().getWinner(new int[]{2,1,3,5,4,6,7}, 2) == 5;
        assert new Solution().getWinner(new int[]{3,2,1}, 10) == 3;
        assert new Solution().getWinner(new int[]{1,9,8,2,3,7,6,4,5}, 7) == 9;
        assert new Solution().getWinner(new int[]{1,11,22,33,44,55,66,77,88,99}, 1000000000) == 99;
    }

}
