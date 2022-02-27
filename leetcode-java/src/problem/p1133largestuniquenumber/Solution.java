package problem.p1133largestuniquenumber;

import java.util.HashMap;
import java.util.Map;

/**
 * 1133. Largest Unique Number
 *
 * https://leetcode-cn.com/problems/largest-unique-number/
 *
 * Given an integer array nums, return the largest integer that only occurs once. If no integer occurs once, return -1.
 */

public class Solution {

    public int largestUniqueNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        int ans = -1;
        for (var e : map.entrySet()) if (e.getValue() == 1) ans = Math.max(ans, e.getKey());
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().largestUniqueNumber(new int[]{5,7,3,9,4,9,8,3,1}) == 8;
        assert new Solution().largestUniqueNumber(new int[]{9,9,8,8}) == -1;
    }

}
