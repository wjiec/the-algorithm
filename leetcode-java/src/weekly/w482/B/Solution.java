package weekly.w482.B;

/**
 * Q2. Minimum Cost to Acquire Required Items
 *
 * https://leetcode.cn/contest/weekly-contest-482/problems/minimum-cost-to-acquire-required-items/
 *
 * You are given five integers cost1, cost2, costBoth, need1, and need2.
 *
 * There are three types of items available:
 *
 * An item of type 1 costs cost1 and contributes 1 unit to the type 1 requirement only.
 * An item of type 2 costs cost2 and contributes 1 unit to the type 2 requirement only.
 * An item of type 3 costs costBoth and contributes 1 unit to both type 1 and type 2 requirements.
 *
 * You must collect enough items so that the total contribution toward type 1 is at least need1
 * and the total contribution toward type 2 is at least need2.
 *
 * Return an integer representing the minimum possible total cost to achieve these requirements.
 */

public class Solution {

    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // 如果 costBoth < cost1 + cost2, 那么两个一起买就是最划算的, 否则不如单个买
        if (cost1 + cost2 < costBoth) {
            return (long) cost1 * need1 + (long) cost2 * need2;
        }

        // 否则我们有以下几种方案
        //  - 使用 costBoth 买到所有的 max(need1, need2)
        long ans = (long) costBoth * Math.max(need1, need2);
        //  - 使用 costBoth 买到所有的 min(need1, need2), 剩下的靠单个补齐
        long minNeed = Math.min(need1, need2);
        ans = Math.min(ans, minNeed * costBoth + cost1 * (need1 - minNeed) + cost2 * (need2 - minNeed));
        return ans;
    }

    public static void main(String[] args) {
    }

}
