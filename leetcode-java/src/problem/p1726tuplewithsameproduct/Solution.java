package problem.p1726tuplewithsameproduct;

import common.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * 1726. Tuple with Same Product
 *
 * https://leetcode.cn/problems/tuple-with-same-product/
 *
 * Given an array nums of distinct positive integers, return the number of tuples (a, b, c, d)
 * such that a * b = c * d where a, b, c, and d are elements of nums, and a != b != c != d.
 */

public class Solution {

    @Tag({"乘法", "四元组"})
    public int tupleSameProduct(int[] nums) {
        int ans = 0, n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                ans += (map.merge(nums[i] * nums[j], 1, Integer::sum) - 1) * 8;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().tupleSameProduct(new int[]{2,3,4,6}) == 8;
        assert new Solution().tupleSameProduct(new int[]{1,2,4,5,10}) == 16;
    }

}
