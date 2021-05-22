package daily.d210522p810chalkboardxorgame;

/**
 * 810. Chalkboard XOR Game
 *
 * https://leetcode-cn.com/problems/chalkboard-xor-game/
 *
 * We are given non-negative integers nums[i] which are written on a chalkboard.
 * Alice and Bob take turns erasing exactly one number from the chalkboard,
 * with Alice starting first.
 * If erasing a number causes the bitwise XOR of all the elements of the chalkboard to become 0,
 * then that player loses.  (Also, we'll say the bitwise XOR of one element is that element itself,
 * and the bitwise XOR of no elements is 0.)
 *
 * Also, if any player starts their turn with the bitwise XOR of
 * all the elements of the chalkboard equal to 0, then that player wins.
 *
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 */

public class Solution {

    public boolean xorGame(int[] nums) {
        if (nums.length % 2 == 0) {
            return true;
        }

        int xor = 0;
        for (var n : nums) {
            xor ^= n;
        }
        return xor == 0;
    }

    public static void main(String[] args) {
        assert new Solution().xorGame(new int[]{1,1,2});
    }

}
