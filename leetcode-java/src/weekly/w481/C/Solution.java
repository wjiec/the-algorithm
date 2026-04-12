package weekly.w481.C;

import java.util.HashMap;
import java.util.Map;

/**
 * Q3. Minimum Swaps to Avoid Forbidden Values
 *
 * https://leetcode.cn/contest/weekly-contest-481/problems/minimum-swaps-to-avoid-forbidden-values/
 *
 * You are given two integer arrays, nums and forbidden, each of length n.
 *
 * You may perform the following operation any number of times (including zero):
 *
 * Choose two distinct indices i and j, and swap nums[i] with nums[j].
 *
 * Return the minimum number of swaps required such that, for every index i,
 * the value of nums[i] is not equal to forbidden[i]. If no amount of swaps can
 * ensure that every index avoids its forbidden value, return -1.
 */

public class Solution {

    public int minSwaps(int[] nums, int[] forbidden) {
        // 对于所有的 i 都要满足 nums[i] != forbidden[i], 求最小交换操作次数
        //  - 统计 nums 和 forbidden 中数的频率, 如果频率超过 n, 那么就是无解的
        //      - 如果频率等于 n, 那么这个数可以一半在 nums 左边, 一半在 forbidden 右边
        //      - 如果超过的话, 必定有数是会重叠的
        //  - 如果 nums[i] == forbidden[i], 那么可以考虑交换两组相同的数对 i & j, 此时可以消除 2 对重复的
        //      - 此时需要 ceil(sumif(nums[i] == forbidden[i]) / 2) 次操作
        int badPairs = 0, n = nums.length, maxBadPairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> badFreq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (freq.merge(nums[i], 1, Integer::sum) > n) return -1;
            if (freq.merge(forbidden[i], 1, Integer::sum) > n) return -1;
            if (nums[i] == forbidden[i]) {
                badPairs++;
                maxBadPairs = Math.max(maxBadPairs, badFreq.merge(nums[i], 1, Integer::sum));
            }
        }

        return Math.max(maxBadPairs, (badPairs + 1) >> 1);
    }

    public static void main(String[] args) {
    }

}
