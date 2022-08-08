package problem.p1423maximumpointsyoucanobtainfromcards;

/**
 * 1423. Maximum Points You Can Obtain from Cards
 *
 * https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards/
 *
 * There are several cards arranged in a row, and each card has an associated number of points.
 * The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row.
 * You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 */

public class Solution {

    public int maxScore(int[] cardPoints, int k) {
        int ans = 0, n = cardPoints.length;
        if (k == 1) return Math.max(cardPoints[0], cardPoints[n - 1]);

        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) sum[i] = sum[i - 1] + cardPoints[i - 1];
        if (k == n) return sum[n];

        for (int i = 0, j = n - 1, r = 0; i <= k; i++, j--) {
            ans = Math.max(ans, r + sum[k - i]);
            r += cardPoints[j];
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxScore(new int[]{96,90,41,82,39,74,64,50,30}, 8) == 536;

        assert new Solution().maxScore(new int[]{1,2,3,4,5,6,1}, 3) == 12;
        assert new Solution().maxScore(new int[]{2,2,2}, 2) == 4;
        assert new Solution().maxScore(new int[]{9,7,7,9,7,7,9}, 7) == 55;
        assert new Solution().maxScore(new int[]{1,1000,1}, 1) == 1;
        assert new Solution().maxScore(new int[]{1,79,80,1,1,1,200,1}, 3) == 202;
    }

}
