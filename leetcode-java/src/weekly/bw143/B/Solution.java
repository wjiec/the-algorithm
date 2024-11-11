package weekly.bw143.B;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3346. Maximum Frequency of an Element After Performing Operations I
 *
 * https://leetcode.cn/contest/biweekly-contest-143/problems/maximum-frequency-of-an-element-after-performing-operations-i/
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

/** @noinspection DuplicatedCode*/
public class Solution {

    // 每个数只能操作一次, 每次操作可以增加 [-k, k]
    public int maxFrequency(int[] nums, int k, int numOperations) {
        Arrays.sort(nums);

        int ans = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (var v : nums) ans = Math.max(ans, cnt.merge(v, 1, Integer::sum));

        // 排序之后, 整个数组可以变化的数字的范围是 [nums[0] - k, nums[-1] + k]
        //  - 枚举其中的每个数字 v, nums 中 < v 且 >= v - k 的数有 l 个, > v 且 <= v + k 的数有 r 个
        //  - 答案就是 min(l + r, numOperations)
        int n = nums.length;
        int min = nums[0] - k, max = nums[nums.length - 1] + k;
        // li 表示最后一个满足 nums[li] < v - k 的索引
        // ri 表示第一个满足 nums[ri] > v + k 的索引
        for (int v = min, li = -1, ri = 0; v <= max; v++) {
            while (li + 1 < n && nums[li + 1] < v - k) li++;
            while (ri < n && nums[ri] <= v + k) ri++;

            ans = Math.max(ans, cnt.getOrDefault(v, 0) + Math.min(numOperations, ri - li - 1 - cnt.getOrDefault(v, 0)));
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxFrequency(new int[]{3}, 2, 1) == 1;
        assert new Solution().maxFrequency(new int[]{23, 54}, 77, 1) == 2;
        assert new Solution().maxFrequency(new int[]{53, 88}, 27, 2) == 2;
        assert new Solution().maxFrequency(new int[]{2, 49}, 97, 0) == 1;

        assert new Solution().maxFrequency(new int[]{1, 4, 5}, 1, 2) == 2;
        assert new Solution().maxFrequency(new int[]{5,11,20,20}, 5, 1) == 2;
    }

}
