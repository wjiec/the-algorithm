package problem.p1512numberofgoodpairs;

import java.util.HashMap;
import java.util.Map;

/**
 * 1512. Number of Good Pairs
 *
 * https://leetcode-cn.com/problems/number-of-good-pairs/
 *
 * Given an array of integers nums, return the number of good pairs.
 *
 * A pair (i, j) is called good if nums[i] == nums[j] and i < j.
 */

public class Solution {

    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        int ans = 0;
        for (var val : map.values()) if (val != 1) ans += (val - 1) * val / 2;
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numIdenticalPairs(new int[]{1,2,3,1,1,3}) == 4;
        assert new Solution().numIdenticalPairs(new int[]{1,1,1,1}) == 6;
        assert new Solution().numIdenticalPairs(new int[]{1,2,3}) == 0;
    }

}
