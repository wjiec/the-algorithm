package weekly.w398.B;

import common.Checker;

import java.util.Objects;
import java.util.TreeMap;

/**
 * 3152. Special Array II
 *
 * https://leetcode.cn/contest/weekly-contest-398/problems/special-array-ii/
 *
 * An array is considered special if every pair of its adjacent
 * elements contains two numbers with different parity.
 *
 * You are given an array of integer nums and a 2D integer matrix queries,
 * where for queries[i] = [fromi, toi] your task is to check that
 * subarray nums[fromi..toi] is special or not.
 *
 * Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
 */

public class Solution {

    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        TreeMap<Integer, Integer> ranges = new TreeMap<>();
        for (int i = 1, p = 0; i <= nums.length; i++) {
            if (i == nums.length || nums[i] % 2 == nums[i - 1] % 2) {
                ranges.put(p, i - 1); p = i;
            }
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = Objects.equals(ranges.floorKey(queries[i][0]), ranges.floorKey(queries[i][1]));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().isArraySpecial(new int[]{1,4}, new int[][]{{0, 1}}), new boolean[]{true});

        assert Checker.check(new Solution().isArraySpecial(new int[]{3,4,1,2,6}, new int[][]{{0, 4}}), new boolean[]{false});
        assert Checker.check(new Solution().isArraySpecial(new int[]{4,3,1,6}, new int[][]{{0, 2}, {2, 3}}), new boolean[]{false, true});
    }

}
