package weekly.w434.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 3434. Maximum Frequency After Subarray Operation
 *
 * https://leetcode.cn/contest/weekly-contest-434/problems/maximum-frequency-after-subarray-operation/
 *
 * You are given an array nums of length n. You are also given an integer k.
 *
 * You perform the following operation on nums once:
 *
 * Select a subarray nums[i..j] where 0 <= i <= j <= n - 1.
 * Select an integer x and add x to all the elements in nums[i..j].
 *
 * Find the maximum frequency of the value k after the operation.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    public int maxFrequency(int[] nums, int k) {
        int kc = 0, n = nums.length, mi = 50, mx = 0;
        for (var v : nums) {
            if (v == k) kc++;
            mi = Math.min(mi, v);
            mx = Math.max(mx, v);
        }

        // 一组相同数 v 中插入了一些 k, 找出一个范围 [l, r] 使得 v 的数量 - k 的数量最大化
        //  - 将连续的 v 变成一个出现频率, 连续的 k 变出 -出现频率
        //  - 现在的题变为最大子数组和(范围之外的得变成加法)
        int ans = kc;
        for (int v = mi; v <= mx; v++) {
            if (v == k) continue;

            List<Integer> list = new ArrayList<>();
            for (int i = 0, count = 0, prev = -1; i <= n; i++) {
                if (i == n || nums[i] == v || nums[i] == k) {
                    if (i == n || nums[i] != prev) {
                        if (prev == k) count = -count;
                        if (count != 0) list.add(count);
                        if (i != n) prev = nums[i];
                        count = 1;
                    } else count++;
                }
            }

            ans = Math.max(ans, maxFrequency(list, kc));
        }

        return ans;
    }

    private int maxFrequency(List<Integer> list, int kc) {
        int ans = kc, curr = 0;
        for (int v : list) {
            curr = Math.max(curr + v, v);
            ans = Math.max(ans, kc + curr);
        }

        return ans;
    }

    private static class Optimization {
        public int maxFrequency(int[] nums, int k) {
            int kc = 0, n = nums.length, mi = 50, mx = 0;
            for (var v : nums) {
                if (v == k) kc++;
                mi = Math.min(mi, v);
                mx = Math.max(mx, v);
            }

            int ans = kc;
            for (int v = mi; v <= mx; v++) {
                if (v == k) continue;

                for (int i = 0, count = 0, prev = -1, curr = 0; i <= n; i++) {
                    if (i == n || nums[i] == v || nums[i] == k) {
                        if (i == n || nums[i] != prev) {
                            // 检查是否需要取反
                            if (prev == k) count = -count;

                            // 求最大连续和
                            curr = Math.max(curr + count, count);
                            ans = Math.max(ans, curr + kc);

                            // 重置计数
                            count = 1; if (i < n) prev = nums[i];
                        } else count++;
                    }
                }
            }

            return ans;
        }
    }

    private static class DynamicProgramming {
        public int maxFrequency(int[] nums, int k) {
            // 使用状态机DP, 我们将数组分为 a0 a1 a2 三段, 其中 a1 是我们选中需要修改的子数组
            // a0 和 a2 则是所选中左右子数组两侧的未选中子数组. 有以下转移方程:
            //
            //  a0_i = a0_(i - 1) + (nums[i] == k ? 1 : 0)
            //
            //  a1_i = a0_(i - 1) + (nums[i] == target ? 1 : 0)
            //  a1_i = a1_(i - 1) + (nums[i] == target ? 1 : 0)
            //
            //  a2_i = a1_(i - 1) + (nums[i] == k ? 1 : 0)
            //  a2_i = a2_(i - 1) + (nums[i] == k ? 1 : 0)
            int ans = 0;
            for (int target = 1; target <= 50; target++) {
                int a0 = 0, a1 = -1, a2 = -1;
                for (var v : nums) {
                    a2 = Math.max(a1, a2) + (v == k ? 1 : 0);
                    a1 = Math.max(a0, a1) + (v == target ? 1 : 0);
                    a0 += (v == k ? 1 : 0);
                }
                ans = Math.max(ans, Math.max(Math.max(a0, a1), a2));
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new DynamicProgramming().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new DynamicProgramming().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new DynamicProgramming().maxFrequency(new int[]{1,2,3,4,5,6}, 1) == 2;

        assert new Optimization().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new Optimization().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new Optimization().maxFrequency(new int[]{1,2,3,4,5,6}, 1) == 2;

        assert new Solution().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new Solution().maxFrequency(new int[]{10,2,3,4,5,5,4,3,2,2}, 10) == 4;
        assert new Solution().maxFrequency(new int[]{1,2,3,4,5,6}, 1) == 2;
    }

}
