package weekly.bw143.C;

/**
 * 3347. Maximum Frequency of an Element After Performing Operations II
 *
 * https://leetcode.cn/contest/biweekly-contest-143/problems/maximum-frequency-of-an-element-after-performing-operations-ii/
 *
 * You are given an integer array nums and two integers k and numOperations.
 *
 * You must perform an operation numOperations times on nums, where in each operation you:
 *
 * Select an index i that was not selected in any previous operations.
 * Add an integer in the range [-k, k] to nums[i].
 *
 * Return the maximum possible frequency of any element in nums after performing the operations.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/** @noinspection DuplicatedCode*/
public class Solution {

    // 每个数只能操作一次, 每次操作可以增加 [-k, k]
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;

        // 先考虑统计在 nums 中的数作为值最大可以出现多少个相同的
        //  - i 指针表示当前所选择的需要变成的值
        //  - l 指针表示左起第一个可以满足 nums[i] - k <= nums[l] 的位置
        //  - r 指针表示右边第一个满足 nums[r] > nums[i] + k 的位置
        //  - c 表示当前有多少个与 nums[i] 相同的数字
        for (int l = 0, r = 0, i = 0, c = 0; i < n; i++) {
            c++;
            // 如果出现相同的数字则跳过
            if (i < n - 1 && nums[i] == nums[i + 1]) { continue; }

            // 找到左边第一个满足 nums[l] >= nums[i] - k 的位置
            while (nums[l] < nums[i] - k) l++;
            // 找到右边第一个满足 nums[r] > nums[i] + k 的位置
            while (r < n && nums[r] <= nums[i] + k) r++;

            // 此时范围 [l, r) 都是可以转换为数字 nums[i] 的, 并且我们已经有 c 个 nums[i]
            //  - 我们一共有 r - l - c 个数字需要进行转换, 但是最多只能操作 op 次
            ans = Math.max(ans, c + Math.min(r - l - c, numOperations));
            // 重置 c 的个数
            c = 0;
        }

        // 如果此时答案数已经大于 op, 则下面的计算(最大值为 op)可以跳过
        if (ans >= numOperations) return ans;

        // 对于数组 [10, 20] 且 k = 5, 我们是通过两边往中间移动得到 15 的方式获得最大的频率
        //  - 对于有边界的值 r, 左边界的值就为 r - 2k, 在范围 [r - 2k, r] 的数都可以进行操作得到相同的值
        //  - 此时 l 表示左侧第一个在在范围 r - 2k 内的位置
        for (int l = 0, r = 0; r < n; r++) {
            while (nums[l] < nums[r] - 2 * k) l++;

            // 此时, l 的位置是第一个满足范围 [r - 2k, r] 的位置, 所以我们共有 r - l + 1 个数可以
            // 变化为一个不在 nums 中的数, 答案既为 max(op, r - l + 1)
            ans = Math.max(ans, Math.min(r - l + 1, numOperations));
        }

        return ans;
    }

    private static class DiffArray {
        // 也可以通过差分数组的形式进行计算
        //  - 对于每一个数 a, 他可以变成 [a - k, a + k] 范围内的任意一个数
        public int maxFrequency(int[] nums, int k, int numOperations) {
            Map<Integer, Integer> count = new HashMap<>();
            TreeMap<Integer, Integer> diff = new TreeMap<>();
            for (var v : nums) {
                count.merge(v, 1, Integer::sum);
                diff.merge(v, 0, Integer::sum);
                diff.merge(v - k, 1, Integer::sum);
                diff.merge(v + k + 1, -1, Integer::sum);
            }

            int ans = 0, sum = 0;
            for (var e : diff.entrySet()) {
                sum += e.getValue();

                int freq = count.getOrDefault(e.getKey(), 0);
                ans = Math.max(ans, freq + Math.min(sum - freq, numOperations));
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxFrequency(new int[]{23, 54}, 77, 1) == 2;
        assert new Solution().maxFrequency(new int[]{2,2,2}, 2, 1) == 3;
        assert new Solution().maxFrequency(new int[]{3}, 2, 1) == 1;
        assert new Solution().maxFrequency(new int[]{53, 88}, 27, 2) == 2;
        assert new Solution().maxFrequency(new int[]{2, 49}, 97, 0) == 1;

        assert new Solution().maxFrequency(new int[]{1, 4, 5}, 1, 2) == 2;
        assert new Solution().maxFrequency(new int[]{5,11,20,20}, 5, 1) == 2;

        assert new DiffArray().maxFrequency(new int[]{23, 54}, 77, 1) == 2;
        assert new DiffArray().maxFrequency(new int[]{2,2,2}, 2, 1) == 3;
        assert new DiffArray().maxFrequency(new int[]{3}, 2, 1) == 1;
        assert new DiffArray().maxFrequency(new int[]{53, 88}, 27, 2) == 2;
        assert new DiffArray().maxFrequency(new int[]{2, 49}, 97, 0) == 1;
        assert new DiffArray().maxFrequency(new int[]{1, 4, 5}, 1, 2) == 2;
        assert new DiffArray().maxFrequency(new int[]{5,11,20,20}, 5, 1) == 2;
    }

}
