package problem.p1395countnumberofteams;

/**
 * 1395. Count Number of Teams
 *
 * https://leetcode.cn/problems/count-number-of-teams/
 *
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k])
 * where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 */

public class Solution {

    public int numTeams(int[] rating) {
        int ans = 0, n = rating.length;
        for (int j = 1; j < n - 1; j++) {
            int lti = 0, gti = 0;
            for (int i = 0; i < j; i++) {
                if (rating[i] > rating[j]) gti++;
                if (rating[i] < rating[j]) lti++;
            }

            int ltk = 0, gtk = 0;
            for (int k = j + 1; k < n; k++) {
                if (rating[k] > rating[j]) gtk++;
                if (rating[k] < rating[j]) ltk++;
            }

            ans += lti * gtk + gti * ltk;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numTeams(new int[]{2,5,3,4,1}) == 3;
        assert new Solution().numTeams(new int[]{2,1,3}) == 0;
        assert new Solution().numTeams(new int[]{1,2,3,4}) == 4;
    }

}
