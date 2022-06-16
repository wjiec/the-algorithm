package problem.p1014bestsightseeingpair;

/**
 * 1014. Best Sightseeing Pair
 *
 * https://leetcode.cn/problems/best-sightseeing-pair/
 *
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot.
 * Two sightseeing spots i and j have a distance j - i between them.
 *
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of
 * the values of the sightseeing spots, minus the distance between them.
 *
 * Return the maximum score of a pair of sightseeing spots.
 */

public class Solution {

    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, max = values[0];
        // (A[i] + i) + (A[j] - j)
        for (int i = 1; i < values.length; i++) {
            ans = Math.max(ans, max + values[i] - i);
            max = Math.max(max, values[i] + i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScoreSightseeingPair(new int[]{8,1,5,2,6}) == 11;
        assert new Solution().maxScoreSightseeingPair(new int[]{1,2}) == 2;
    }

}
