package weekly.w450.B;

import java.util.Arrays;

/**
 * Q2. Minimum Swaps to Sort by Digit Sum
 *
 * https://leetcode.cn/contest/weekly-contest-450/problems/minimum-swaps-to-sort-by-digit-sum/
 *
 * You are given an array nums of distinct positive integers. You need to sort the array
 * in increasing order based on the sum of the digits of each number.
 *
 * If two numbers have the same digit sum, the smaller number appears first in the sorted order.
 *
 * Return the minimum number of swaps required to rearrange nums into this sorted order.
 *
 * A swap is defined as exchanging the values at two distinct positions in the array.
 */

public class Solution {

    public int minSwaps(int[] nums) {
        int[] digitSum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            for (int x = nums[i]; x != 0; x /= 10) {
                digitSum[i] += x % 10;
            }
        }

        // sorted 中保存的是第 i 位是哪个索引
        Integer[] sorted = new Integer[nums.length];
        Arrays.setAll(sorted, i -> i);
        Arrays.sort(sorted, (a, b) -> digitSum[a] == digitSum[b] ? (nums[a] - nums[b]) : (digitSum[a] - digitSum[b]));

        // curr 保存当前第 i 位所保存的索引, 我们需要通过交换 curr 中的数, 将 curr 变成 sorted
        int[] curr = new int[nums.length];
        Arrays.setAll(curr, i -> i);

        // rank 保存 curr 的值在 curr 中的位置
        int[] rank = new int[nums.length];
        for (int i = 0; i < nums.length; i++) rank[curr[i]] = i;

        int ans = 0;
        // 现在需要让 curr 变成和 sorted 一样
        for (int i = 0; i < nums.length; i++) {
            if (curr[i] != sorted[i]) {
                // 当前是 curr[i], 需要找到 sorted[i] 在 curr 中的位置 j, 然后交换 i 和 j
                int j = rank[sorted[i]]; swap(curr, i, j);
                // 交换之后, 会导致 rank 数据发生变化, 现在 curr[i] 保存的是 sorted[i], 而原本的 curr[i] 被交换到 j 位置
                rank[sorted[i]] = i; rank[curr[j]] = j;
                ans++;
            }
        }

        return ans;
    }

    private void swap(int[] nums, int i, int j) { int t = nums[i]; nums[i] = nums[j]; nums[j] = t; }

    public static void main(String[] args) {
        assert new Solution().minSwaps(new int[]{277993448,416038674,616955867,539372283}) == 3;

        assert new Solution().minSwaps(new int[]{193644115,569655076,91600837}) == 2;
        assert new Solution().minSwaps(new int[]{268835996,65052660,415128775}) == 2;
    }

}
