package weekly.w494.D;

import common.Tag;

import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Count Good Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-494/problems/count-good-subarrays/
 *
 * You are given an integer array nums.
 *
 * A subarray is called good if the bitwise OR of all its elements is equal to
 * at least one element present in that subarray.
 *
 * Return the number of good subarrays in nums.
 *
 * Here, the bitwise OR of two integers a and b is denoted by a | b.
 */

public class Solution {

    @Tag("LogTrick")
    public long countGoodSubarrays(int[] nums) {
        // 枚举右端点, 统计所有满足条件的左端点
        //  - 从右端点 r 开始, or 的特性使得枚举的 l 越小, 则包含的位就越多(只增不减)
        //      - 所以从 r 开始枚举子数组, 也就是至多 logU 种可能的 or 值
        //  - 我们只记录 or 值发生变化的位置即可
        //
        // 最后我们枚举每个发生变化的端点 l, 那么 [l, min(last[or], r)] 都是满足条件的子数组起点
        long ans = 0; int m = 0;
        int[][] orLeft = new int[nums.length][]; // [子数组异或值, 最小的左端点]
        Map<Integer, Integer> last = new HashMap<>(); // 数字最后出现的位置
        for (int r = 0; r < nums.length; r++) {
            last.put(nums[r], r); // 记录最后出现位置

            // 计算以 nums[r] 为右端点的子数组和值
            for (int j = 0; j < m; j++) orLeft[j][0] |= nums[r];
            // nums[r] 单独作为一个子数组
            orLeft[m++] = new int[]{nums[r], r};

            // 相同的 or 值我们只保存最左边的位置
            int newM = 1;
            for (int j = 1; j < m; j++) {
                if (orLeft[j][0] != orLeft[j - 1][0]) {
                    orLeft[newM++] = orLeft[j];
                }
            }
            m = newM;

            // 枚举所有的 or 值发生变化的左端点
            for (int j = 0; j < m; j++) {
                int or = orLeft[j][0], l = orLeft[j][1];

                // 为了防止重复计算, 我们只枚举当前这一相同 or 值的坐标范围
                int orR = j + 1 < m ? orLeft[j + 1][1] : r;
                int orL = last.getOrDefault(or, -1);
                // 只要这个数字的最后出现位置大于当前 or 值区间的左边位置, 那就说明有子数组可以选
                if (orL >= l) ans += Math.min(orR, orL) - l + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodSubarrays(new int[]{4,2,3}) == 4;
        assert new Solution().countGoodSubarrays(new int[]{1,3,1}) == 6;
    }

}
