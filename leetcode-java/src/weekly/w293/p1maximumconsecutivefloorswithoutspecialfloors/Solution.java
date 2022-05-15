package weekly.w293.p1maximumconsecutivefloorswithoutspecialfloors;

import java.util.Arrays;

/**
 * 6064. Maximum Consecutive Floors Without Special Floors
 *
 * https://leetcode.cn/contest/weekly-contest-293/problems/maximum-consecutive-floors-without-special-floors/
 *
 * Alice manages a company and has rented some floors of a building as office space.
 * Alice has decided some of these floors should be special floors, used for relaxation only.
 *
 * You are given two integers bottom and top, which denote that Alice has rented all the floors
 * from bottom to top (inclusive). You are also given the integer array special,
 * where special[i] denotes a special floor that Alice has designated for relaxation.
 *
 * Return the maximum number of consecutive floors without a special floor.
 */

public class Solution {

    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);
        int ans = 0, prev = bottom - 1;
        for (var spec : special) {
            if (spec > top) break;
            ans = Math.max(ans, spec - prev - 1);
            prev = spec;
        }
        ans = Math.max(ans, top - prev);
        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxConsecutive(2, 9, new int[]{4,6}) == 3;
        assert new Solution().maxConsecutive(6, 8, new int[]{7,6,8}) == 0;
        assert new Solution().maxConsecutive(6, 10, new int[]{6,7,20,100}) == 3;
    }

}
