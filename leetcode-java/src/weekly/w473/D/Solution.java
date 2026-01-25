package weekly.w473.D;

import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Count Distinct Subarrays Divisible by K in Sorted Array
 *
 * https://leetcode.cn/contest/weekly-contest-473/problems/count-distinct-subarrays-divisible-by-k-in-sorted-array/
 *
 * You are given an integer array nums sorted in non-descending order and a positive integer k.
 *
 * A subarray of nums is good if the sum of its elements is divisible by k.
 *
 * Return an integer denoting the number of distinct good subarrays of nums.
 *
 * Subarrays are distinct if their sequences of values are.
 * For example, there are 3 distinct subarrays in [1, 1, 1], namely [1], [1, 1], and [1, 1, 1].
 */

public class Solution {

    public long numGoodSubarrays(int[] nums, int k) {
        // 找到不同的子数组使得子数组的和可以被 k 整除
        //
        // 不考虑重复的话, 也就是找到子数组使得和可以被 k 整除
        //  - 也就是枚举右边, 统计前缀和对 k 取模之后同余的位置数量
        //
        // 如何去掉重复呢?
        //  - 如果 nums[r + 1] != nums[r] 那么必然不会发生重复
        //  - 重复只会发生在子数组内元素全相同的场景内, 单独计算

        // 不考虑重复情况下的子数组数量
        long ans = 0;
        Map<Integer, Integer> mod = new HashMap<>(); mod.put(0, 1);
        for (int r = 0, s = 0; r < nums.length; r++) {
            s = (s + nums[r]) % k;
            ans += mod.getOrDefault(s, 0);
            mod.merge(s, 1, Integer::sum);
        }

        // 现在需要去掉重复的, 也就是找到子数组内重复的元素组
        //  - 如果有 n 个 v, 计算满足子数组和可以被 k 整除的数量, 只单独计算一次
        for (int l = 0, r = 1; r <= nums.length; r++) {
            if (r == nums.length || nums[r] != nums[l]) {
                // 此时 nums[l...r) 是一个包含相同元素的组
                int n = r - l, v = nums[l];
                // 我们只统计相同数量大于 1 的组
                if (n > 1) {
                    // 如果 v 可以被 k 整除, 那么任意个 v 都是满足条件的
                    // 如果 k 可以被 v 整除, 那么只需要 k / v 个 v 就足够了
                    for (long c = 1; c <= n; c++) {
                        if ((c * v) % k == 0) {
                            // 我们从 n 个元素中选出连续 c 个共有 n - c + 1 种方案, 但是只能算一种
                            ans -= n - c;
                        }
                    }
                }

                // 重置位置
                l = r;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numGoodSubarrays(new int[]{1,1,4,4}, 2) == 5;
        assert new Solution().numGoodSubarrays(new int[]{1000000000,1000000000,1000000000}, 1000000000) == 3;

        assert new Solution().numGoodSubarrays(new int[]{1,2,3}, 3) == 3;
        assert new Solution().numGoodSubarrays(new int[]{2,2,2,2,2,2}, 6) == 2;
    }

}
