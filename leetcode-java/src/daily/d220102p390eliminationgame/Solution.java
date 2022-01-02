package daily.d220102p390eliminationgame;

/**
 * 390. Elimination Game
 *
 * https://leetcode-cn.com/problems/elimination-game/
 *
 * You have a list arr of all integers in the range [1, n] sorted in a strictly increasing order.
 *
 * Apply the following algorithm on arr:
 *
 * Starting from left to right, remove the first number and every other number
 * afterward until you reach the end of the list.
 *
 * Repeat the previous step again, but this time from right to left,
 * remove the rightmost number and every other number from the remaining numbers.
 *
 * Keep repeating the steps again, alternating left to right and right to left, until a single number remains.
 *
 * Given the integer n, return the last number that remains in arr.
 */

public class Solution {

    public int lastRemaining(int n) {
        int a1 = 1, k = 0, step = 1;
        while (n > 1) {
            if (k % 2 == 0) {
                a1 = a1 + step;
            } else {
                a1 = (n % 2 == 0) ? a1 : a1 + step;
            }

            k++;
            n >>= 1;
            step <<= 1;
        }
        return a1;
    }

    public static void main(String[] args) {
        assert new Solution().lastRemaining(9) == 6;
        assert new Solution().lastRemaining(1) == 1;
    }

}
