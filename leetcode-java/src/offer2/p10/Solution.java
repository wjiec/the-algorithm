package offer2.p10;

import common.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为 k 的子数组
 *
 * https://leetcode.cn/problems/QTMn0o/
 *
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 */

public class Solution {

    @Tag({"带负数的连续子数组和"})
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>(); map.put(0, 1);
        int ans = 0, s = 0;
        for (var v : nums) {
            s += v;
            ans += map.getOrDefault(s - k, 0);
            map.merge(s, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().subarraySum(new int[]{-1,-1,1}, 0) == 1;
        assert new Solution().subarraySum(new int[]{1}, 0) == 0;

        assert new Solution().subarraySum(new int[]{1,1,1}, 2) == 2;
        assert new Solution().subarraySum(new int[]{1,2,3}, 3) == 2;
    }

}
