package problem.p2364countnumberofbadpairs;

import java.util.HashMap;
import java.util.Map;

/**
 * 2364. Count Number of Bad Pairs
 *
 * https://leetcode.cn/problems/count-number-of-bad-pairs/
 *
 * You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair
 * if i < j and j - i != nums[j] - nums[i].
 *
 * Return the total number of bad pairs in nums.
 */

public class Solution {

    // i < j and j - i != nums[j] - nums[i]
    //
    // j - i != nums[j] - nums[i]
    // j - nums[j] != i - nums[i]
    public long countBadPairs(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.merge((long) (i - nums[i]), 1, Integer::sum);
        }

        long ans = 0, n = nums.length;
        for (var v : map.values()) ans += v * (n - v);
        return ans / 2;
    }

    public static void main(String[] args) {
        assert new Solution().countBadPairs(new int[]{4,1,3,3}) == 5;
        assert new Solution().countBadPairs(new int[]{1,2,3,4,5}) == 0;
    }

}
