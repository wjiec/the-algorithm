package weekly.w433.D;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 3430. Maximum and Minimum Sums of at Most Size K Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-433/problems/maximum-and-minimum-sums-of-at-most-size-k-subarrays/
 *
 * You are given an integer array nums and a positive integer k. Return the sum of the
 * maximum and minimum elements of all subarrays with at most k elements.
 */

public class Solution {

    public long minMaxSubarraySum(int[] nums, int k) {
        int n = nums.length;

        // 最多有 k 个元素的子数组最值之和, 找到左右两侧的大于或小于当前值的位置
        int[] rGt = new int[n]; Arrays.fill(rGt, n); // 右侧大于当前值的位置
        int[] lGt = new int[n]; Arrays.fill(lGt, -1); // 左侧大于当前值的位置
        int[] rLt = new int[n]; Arrays.fill(rLt, n); // 右侧小于当前值的位置
        int[] lLt = new int[n]; Arrays.fill(lLt, -1); // 左侧小于当前值的位置

        Deque<Integer> gt = new ArrayDeque<>();
        Deque<Integer> lt = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 如果当前值大于栈内已保存的值, 那说明找到了一个比左侧数大的位置
            while (!gt.isEmpty() && nums[gt.peek()] <= nums[i]) {
                int removed = gt.pop();
                if (nums[removed] != nums[i]) rGt[removed] = i;
            }
            if (!gt.isEmpty()) lGt[i] = gt.peek();
            gt.push(i);

            // 如果当前值小于栈内已保存的值, 那说明找到了一个比左侧数小的位置
            while (!lt.isEmpty() && nums[lt.peek()] >= nums[i]) {
                int removed = lt.pop();
                if (nums[removed] != nums[i]) rLt[removed] = i;
            }
            if (!lt.isEmpty()) lLt[i] = lt.peek();
            lt.push(i);
        }

        // 然后枚举每一个数, 找到当前数作为最小值和最大值能覆盖的范围是多少
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long v = nums[i];
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 如果当前数作为最大值
            // 在 (l, r) 范围内 nums[i] 都可以作为最大值
            ans += count(i - lGt[i] - 1, rGt[i] - i - 1, k) * v;

            // 如果当前数作为最小值
            // 在 (l, r) 范围内 nums[i] 都可以作为最小值
            ans += count(i - lLt[i] - 1, rLt[i] - i - 1, k) * v;
        }

        return ans;
    }

    // 某个位置左侧有 l 个数, 右侧有 r 个数, 求包含当前位置最长子数组长度为 k 的方案数有多少个
    public static long count(int l, int r, int k) {
        long ans = 1 + Math.min(l, k - 1) + Math.min(r, k - 1);
        for (int i = 1; i <= Math.min(l, k - 2); i++) {
            // 已经有了 i + 1 个数字, 右侧还需要有 k - i - 1 个数字
            ans += Math.min(r, k - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minMaxSubarraySum(new int[]{-7,-7}, 2) == -42;

        assert new Solution().minMaxSubarraySum(new int[]{1,2,3}, 2) == 20;
        assert new Solution().minMaxSubarraySum(new int[]{1,-3,1}, 2) == -6;
    }

}
