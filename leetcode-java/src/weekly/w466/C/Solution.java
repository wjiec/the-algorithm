package weekly.w466.C;

import common.Tag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Q3. Count Bowl Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-466/problems/count-bowl-subarrays/
 *
 * You are given an integer array nums with distinct elements.
 *
 * A subarray nums[l...r] of nums is called a bowl if:
 *
 * The subarray has length at least 3. That is, r - l + 1 >= 3.
 * The minimum of its two ends is strictly greater than the maximum of all elements
 * in between. That is, min(nums[l], nums[r]) > max(nums[l + 1], ..., nums[r - 1]).
 *
 * Return the number of bowl subarrays in nums.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    public long bowlSubarrays(int[] nums) {
        // 长度至少为 3 的子数组, 两端的最小值大于中间所有元素的最大值
        //  - min(l, r) > max(l + 1, l + 2, ..., r - 1)
        //
        // 数组中的数都是不同的, 所有没有 >= 的情况, 可以使用 TreeSet
        //
        // 枚举碗子数组的最小端位置 i, 我们找到第一个大于 nums[i] 的位置 j 作为碗的最大端
        //  - 也就是 [i, j] 子数组构成了一个碗子数组
        //      - 以 i 为碗端点, (j, n) 的所有位置都是不可行的, 因为存在 nums[j] > nums[i] (中间大于两端)
        //
        // 我们需要对于每一个枚举的位置 i, 在左右两边找到第一个大于 nums[i] 的位置 j, 且 |i - j| > 1

        long ans = count(nums);
        for (int l = 0, r = nums.length - 1; l < r; l++, r--) {
            int t = nums[l]; nums[l] = nums[r]; nums[r] = t;
        }
        return ans + count(nums);
    }

    private long count(int[] nums) {
        int[] gtL = new int[nums.length]; Arrays.fill(gtL, -1);
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && nums[dq.peek()] < nums[i]) {
                gtL[dq.remove()] = i;
            }
            dq.push(i);
        }

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (gtL[i] != -1 && gtL[i] - i > 1) ans++;
        }
        return ans;
    }

    @Tag({"单调栈", "比当前位置大的左右位置"})
    private static class Optimization {
        public long bowlSubarrays(int[] nums) {
            int top = -1; long ans = 0;
            int[] st = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                while (top >= 0 && nums[st[top]] < nums[i]) {
                    // 当前值大于栈顶元素, 说明找到了一个在 top 右侧比较大的元素
                    if (i - st[top--] > 1) ans++;
                }

                // 如果此时在循环外, 则说明不满足循环, 也就是 top (左侧)是比当前位置大的元素
                if (top >= 0 && i - st[top] > 1) ans++;
                st[++top] = i;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().bowlSubarrays(new int[]{2,5,3,1,4}) == 2;
        assert new Solution().bowlSubarrays(new int[]{5,1,2,3,4}) == 3;
        assert new Solution().bowlSubarrays(new int[]{1000000000,999999999,999999998}) == 0;
    }
    
}
