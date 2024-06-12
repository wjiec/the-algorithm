package weekly.w401.C;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3180. Maximum Total Reward Using Operations I
 *
 * https://leetcode.cn/contest/weekly-contest-401/problems/maximum-total-reward-using-operations-i/
 *
 * You are given an integer array rewardValues of length n, representing the values of rewards.
 *
 * Initially, your total reward x is 0, and all indices are unmarked. You are allowed to perform
 * the following operation any number of times:
 *
 * Choose an unmarked index i from the range [0, n - 1].
 * If rewardValues[i] is greater than your current total reward x, then add
 * rewardValues[i] to x (i.e., x = x + rewardValues[i]), and mark the index i.
 *
 * Return an integer denoting the maximum total reward you can collect by performing the operations optimally.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);

        int ans = 0;
        for (var v : rewardValues) {
            ans = Math.max(ans, v + dfs(rewardValues, v));
        }
        return ans;
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int dfs(int[] values, int curr) {
        if (!memo.containsKey(curr)) {
            int idx = Arrays.binarySearch(values, curr + 1);
            if (idx < 0) idx = -idx - 1;

            int ans = 0;
            for (; idx < values.length; idx++) {
                ans = Math.max(ans, values[idx] + dfs(values, curr + values[idx]));
            }
            memo.put(curr, ans);
        }
        return memo.get(curr);
    }

    public static void main(String[] args) {
        assert new Solution().maxTotalReward(new int[]{1,5,9}) == 15;
        assert new Solution().maxTotalReward(new int[]{4,6}) == 10;

        assert new Solution().maxTotalReward(new int[]{1,1,3,3}) == 4;
        assert new Solution().maxTotalReward(new int[]{1,2,3,4,6}) == 11;
    }

}
