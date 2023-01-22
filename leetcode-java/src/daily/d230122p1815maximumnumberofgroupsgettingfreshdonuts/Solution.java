package daily.d230122p1815maximumnumberofgroupsgettingfreshdonuts;

import java.util.HashMap;
import java.util.Map;

/**
 * 1815. Maximum Number of Groups Getting Fresh Donuts
 *
 * https://leetcode.cn/problems/maximum-number-of-groups-getting-fresh-donuts
 *
 * There is a donuts shop that bakes donuts in batches of batchSize. They have a rule
 * where they must serve all of the donuts of a batch before serving any donuts of the
 * next batch. You are given an integer batchSize and an integer array groups, where
 * groups[i] denotes that there is a group of groups[i] customers that will visit the shop.
 *
 * Each customer will get exactly one donut.
 *
 * When a group visits the shop, all customers of the group must be served before serving
 * any of the following groups. A group will be happy if they all get fresh donuts.
 * That is, the first customer of the group does not receive a donut that was left over
 * from the previous group.
 *
 * You can freely rearrange the ordering of the groups. Return the maximum possible
 * number of happy groups after rearranging the groups.
 */

public class Solution {

    private static final int STATE_WIDTH = 5;
    private static final int STATE_MASK = (1 << STATE_WIDTH) - 1;

    public int maxHappyGroups(int batchSize, int[] groups) {
        int[] reminder = new int[batchSize];
        for (var g : groups) reminder[g % batchSize]++;

        long state = 0;
        for (int i = 1; i < batchSize; i++) {
            state = (state << STATE_WIDTH) | reminder[i];
        }

        return dfs(state, batchSize) + reminder[0];
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int dfs(long state, int batchSize) {
        if (state == 0) return 0;

        if (!memo.containsKey(state)) {
            long total = 0;
            for (int i = 1; i < batchSize; i++) {
                long amount = (state >> ((i - 1) * STATE_WIDTH)) & STATE_MASK;
                total += i * amount;
            }

            int best = 0;
            for (int i = 1; i < batchSize; i++) {
                long amount = (state >> ((i - 1) * STATE_WIDTH)) & STATE_MASK;
                if (amount > 0) {
                    int result = dfs(state - (1L << ((i - 1) * STATE_WIDTH)), batchSize);
                    if ((total - i) % batchSize == 0) result++;
                    best = Math.max(best, result);
                }
            }
            memo.put(state, best);
        }
        return memo.get(state);
    }

    public static void main(String[] args) {
        assert new Solution().maxHappyGroups(3, new int[]{1,2,3,4,5,6}) == 4;
        assert new Solution().maxHappyGroups(4, new int[]{1,3,2,5,2,2,1,6}) == 4;
    }

}
