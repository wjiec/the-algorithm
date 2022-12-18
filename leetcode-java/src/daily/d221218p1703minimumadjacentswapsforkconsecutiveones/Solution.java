package daily.d221218p1703minimumadjacentswapsforkconsecutiveones;

import java.util.ArrayList;
import java.util.List;

/**
 * 1703. Minimum Adjacent Swaps for K Consecutive Ones
 *
 * https://leetcode.cn/problems/minimum-adjacent-swaps-for-k-consecutive-ones
 *
 * You are given an integer array, nums, and an integer k. nums comprises of only 0's and 1's.
 * In one move, you can choose two adjacent indices and swap their values.
 *
 * Return the minimum number of moves required so that nums has k consecutive 1's.
 */

public class Solution {

    public int minMoves(int[] nums, int k) {
        List<Integer> prefix = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();

        prefix.add(0);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                distance.add(i - distance.size());
                prefix.add(prefix.get(prefix.size() - 1) + distance.get(distance.size() - 1));
            }
        }

        int n = distance.size(), ans = Integer.MAX_VALUE;
        for (int i = 0; i <= n - k; i++) {
            int mid = i + k / 2;
            int r = distance.get(mid);
            ans = Math.min(ans, (1 - k % 2) * r + (prefix.get(i + k) - prefix.get(mid + 1)) - (prefix.get(mid) - prefix.get(i)));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMoves(new int[]{1,0,0,1,0,1}, 2) == 1;
        assert new Solution().minMoves(new int[]{1,0,0,0,0,0,1,1}, 3) == 5;
        assert new Solution().minMoves(new int[]{1,1,0,1}, 2) == 0;
    }

}
