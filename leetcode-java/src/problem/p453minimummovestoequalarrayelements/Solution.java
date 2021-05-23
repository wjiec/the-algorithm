package problem.p453minimummovestoequalarrayelements;

import java.util.Arrays;

/**
 * 453. Minimum Moves to Equal Array Elements
 *
 * Given an integer array nums of size n,
 * return the minimum number of moves required to make all array elements equal.
 *
 * In one move, you can increment n - 1 elements of the array by 1.
 */

public class Solution {

    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int cnt = 0, sz = nums.length;
        for (int i = sz - 1; i > 0; i--) {
            cnt += nums[i] - nums[0];
        }
        return cnt;
    }

    public static void main(String[] args) {
        assert new Solution().minMoves(new int[]{1,2,3}) == 3;
        assert new Solution().minMoves(new int[]{1,1,1}) == 0;
    }

}
