package problem.p2498frogjumpii;

/**
 * 2498. Frog Jump II
 *
 * https://leetcode.cn/problems/frog-jump-ii/
 *
 * You are given a 0-indexed integer array stones sorted in strictly increasing
 * order representing the positions of stones in a river.
 *
 * A frog, initially on the first stone, wants to travel to the last stone and
 * then return to the first stone. However, it can jump to any stone at most once.
 *
 * The length of a jump is the absolute difference between the position of the stone
 * the frog is currently on and the position of the stone to which the frog jumps.
 *
 * More formally, if the frog is at stones[i] and is jumping to stones[j], the
 * length of the jump is |stones[i] - stones[j]|.
 *
 * The cost of a path is the maximum length of a jump among all jumps in the path.
 *
 * Return the minimum cost of a path for the frog.
 */

public class Solution {

    public int maxJump(int[] stones) {
        int ans = stones[1] - stones[0];
        for (int i = 2; i < stones.length; i++) {
            ans = Math.max(ans, stones[i] - stones[i - 2]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxJump(new int[]{0,2,5,6,7}) == 5;
        assert new Solution().maxJump(new int[]{0,3,9}) == 9;
        assert new Solution().maxJump(new int[]{0,1,2,3,4,5,6,7}) == 2;
    }

}
