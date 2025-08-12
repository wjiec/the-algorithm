package weekly.bw160.D;

import ability.Ability;

import java.util.ArrayList;
import java.util.List;

import static ability.Ability.Math.gcd;

/**
 * Q4. Minimum Stability Factor of Array
 *
 * https://leetcode.cn/contest/biweekly-contest-160/problems/minimum-stability-factor-of-array/
 *
 * You are given an integer array nums and an integer maxC.
 *
 * A subarray is called stable if the highest common factor (HCF) of all its elements is greater than or equal to 2.
 *
 * The stability factor of an array is defined as the length of its longest stable subarray.
 *
 * You may modify at most maxC elements of the array to any integer.
 *
 * Return the minimum possible stability factor of the array after at most maxC modifications.
 * If no stable subarray remains, return 0.
 *
 * Note:
 *
 * The highest common factor (HCF) of an array is the largest integer that evenly divides all the array elements.
 * A subarray of length 1 is stable if its only element is greater than or equal to 2, since HCF([x]) = x.
 */

public class Solution {

    public int minStable(int[] nums, int maxC) {
        int l = -1, r = nums.length / (maxC + 1);
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (check(nums, maxC, mid)) r = mid;
            else l = mid;
        }
        return r;
    }

    private boolean check(int[] nums, int maxC, int maxLen) {
        // [子数组 GCD, 最小的左端点]
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];

            // 计算以 i 为右端点的子数组 GCD
            for (var interval : intervals) {
                interval[0] = gcd(interval[0], v);
            }
            // nums[i] 单独作为一个数组
            intervals.add(new int[]{v, i});

            int len = 1;
            // 合并 GCD 相同的区域
            for (int j = 1; j < intervals.size(); j++) {
                if (intervals.get(j)[0] != intervals.get(j - 1)[0]) {
                    intervals.set(len++, intervals.get(j));
                }
            }
            intervals.subList(len, intervals.size()).clear();

            // 此时在 intervals 中, 越靠左边 GCD 越小
            //  - 我们只关心 GCD >= 2 的子数组
            if (intervals.get(0)[0] == 1) intervals.remove(0);

            // 此时 intervals[0] 的 GCD >= 2 且最长, 取区间左端点作为子数组的最小左端点
            if (!intervals.isEmpty() && i - intervals.get(0)[1] + 1 > maxLen) {
                if (maxC-- == 0) return false;
                // 修改当前数为 1, 此时所有 GCD 都为 1
                intervals.clear();
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
