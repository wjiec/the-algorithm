package problem.p983minimumcostfortickets;

public class Solution {

    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        for (int i = 1, j = 0; i < dp.length; i++) {
            dp[i] = dp[i - 1];
            if (j < days.length && days[j] == i) {
                dp[i] = dp[i - 1] + costs[0];
                dp[i] = Math.min(dp[i], dp[Math.max(i - 7, 0)] + costs[1]);
                dp[i] = Math.min(dp[i], dp[Math.max(i - 30, 0)] + costs[2]);
                j++;
            }
        }
        return dp[365];
    }

    public static void main(String[] args) {
        assert new Solution().mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{7,2,15}) == 6;

        assert new Solution().mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2,7,15}) == 11;
        assert new Solution().mincostTickets(new int[]{1,2,3,4,5,6,7,8,9,10,30,31}, new int[]{2,7,15}) == 17;
    }

}
