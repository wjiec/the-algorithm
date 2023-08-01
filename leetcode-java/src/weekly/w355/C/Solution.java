package weekly.w355.C;

import java.util.List;

/**
 * 2790. Maximum Number of Groups With Increasing Length
 *
 * https://leetcode.cn/contest/weekly-contest-355/problems/maximum-number-of-groups-with-increasing-length/
 *
 * You are given a 0-indexed array usageLimits of length n.
 *
 * Your task is to create groups using numbers from 0 to n - 1, ensuring that each number, i, is used
 * no more than usageLimits[i] times in total across all groups.
 *
 * You must also satisfy the following conditions:
 *
 * Each group must consist of distinct numbers, meaning that no duplicate numbers are allowed within a single group.
 * Each group (except the first one) must have a length strictly greater than the previous group.
 * Return an integer denoting the maximum number of groups you can create while satisfying these conditions.
 */

public class Solution {

    public int maxIncreasingGroups(List<Integer> usageLimits) {
        usageLimits.sort(Integer::compare);

        long ans = 0, sum = 0;
        for (var v : usageLimits) {
            sum += v;
            // S = (a1 + an) * n / 2
            if (sum >= (ans + 2) * (ans + 1) / 2) ans++;
        }
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxIncreasingGroups(List.of(2, 1, 2)) == 2;
        assert new Solution().maxIncreasingGroups(List.of(2, 2)) == 2;
    }

}
