package offer2.p57;

import ability.Ability;

import java.util.TreeMap;

/**
 * 剑指 Offer II 057. 值和下标之差都在给定的范围内
 *
 * https://leetcode.cn/problems/7WqeDu/
 *
 * 给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使
 * 得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeMap<Long, Integer> map = new TreeMap<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            Long lo = map.ceilingKey((long) nums[r] - t);
            if (lo != null && lo <= (long) nums[r] + t) return true;

            map.merge((long) nums[r], 1, Integer::sum);
            if (r - l + 1 > k) map.merge((long) nums[l++], 1, Ability::subtract);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsNearbyAlmostDuplicate(new int[]{1,2,3,1}, 3, 0);
        assert new Solution().containsNearbyAlmostDuplicate(new int[]{1,0,1,1}, 1, 2);
        assert !new Solution().containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2, 3);
    }

}
