package problem.p2379minimumrecolorstogetkconsecutiveblackblocks;

/**
 * 2379. Minimum Recolors to Get K Consecutive Black Blocks
 *
 * https://leetcode.cn/problems/minimum-recolors-to-get-k-consecutive-black-blocks/
 *
 * You are given a 0-indexed string blocks of length n, where blocks[i] is either 'W' or 'B', representing
 * the color of the ith block. The characters 'W' and 'B' denote the colors white and black, respectively.
 *
 * You are also given an integer k, which is the desired number of consecutive black blocks.
 *
 * In one operation, you can recolor a white block such that it becomes a black block.
 *
 * Return the minimum number of operations needed such that there is at least
 * one occurrence of k consecutive black blocks.
 */

public class Solution {

    public int minimumRecolors(String blocks, int k) {
        int ans = Integer.MAX_VALUE, white = 0;
        for (int l = 0, r = 0; r < blocks.length(); r++) {
            white += blocks.charAt(r) == 'W' ? 1 : 0;
            if (r - l + 1 > k) white += blocks.charAt(l++) == 'W' ? -1 : 0;
            if (r - l + 1 == k) ans = Math.min(ans, white);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumRecolors("WBBWWBBWBW", 7) == 3;
        assert new Solution().minimumRecolors("WBWBBBW", 2) == 0;
    }

}
