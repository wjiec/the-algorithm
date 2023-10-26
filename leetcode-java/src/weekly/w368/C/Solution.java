package weekly.w368.C;

import java.util.HashMap;
import java.util.Map;

/**
 * 2910. Minimum Number of Groups to Create a Valid Assignment
 *
 * https://leetcode.cn/contest/weekly-contest-368/problems/minimum-number-of-groups-to-create-a-valid-assignment/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * We want to group the indices so for each index i in the range [0, n - 1], it is assigned to exactly one group.
 *
 * A group assignment is valid if the following conditions hold:
 *
 * For every group g, all indices i assigned to group g have the same value in nums.
 * For any two groups g1 and g2, the difference between the number of indices assigned to g1 and g2 should not exceed 1.
 * Return an integer denoting the minimum number of groups needed to create a valid group assignment.
 */

public class Solution {

    public int minGroupsForValidAssignment(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        int minCount = 0;
        for (var v : map.values()) minCount = Math.max(minCount, v);

        for (int avg = minCount; avg > 0; avg--) {
            int groups = 0;
            for (var v : map.values()) {
                if (v / avg < v % avg) {
                    groups = 0; break;
                }
                groups += (v + avg) / (avg + 1);
            }
            if (groups != 0) return groups;
        }

        return -1; // unreached
    }

    public static void main(String[] args) {
        // 1 1  1 1  2 2 2  3 3
        assert new Solution().minGroupsForValidAssignment(new int[]{1,1,1,3,1,2,2,2,3}) == 4;
    }

}
