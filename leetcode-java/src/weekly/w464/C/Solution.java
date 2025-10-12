package weekly.w464.C;

import common.Checker;

/**
 * Q3. Jump Game IX
 *
 * https://leetcode.cn/contest/weekly-contest-464/problems/jump-game-ix/
 *
 * You are given an integer array nums.
 *
 * From any index i, you can jump to another index j under the following rules:
 *
 * Jump to index j where j > i is allowed only if nums[j] < nums[i].
 * Jump to index j where j < i is allowed only if nums[j] > nums[i].
 *
 * For each index i, find the maximum value in nums that can be reached by following
 * any sequence of valid jumps starting at i.
 *
 * Return an array ans where ans[i] is the maximum value reachable starting from index i.
 */

public class Solution {

    public int[] maxValue(int[] nums) {
        // 满足以下任意一个条件, 则可以从当前位置 i 跳到位置 j
        //  - 在 i < j && nums[i] > nums[j] 时, 往后跳, 大 -> 小
        //  - 在 j < i && nums[j] > nums[i] 时, 往前跳, 小 -> 大
        //
        // 由于我们可以往前跳到更大的数, 往后只能跳到更小的数字上, 所以有:
        //  - 如果存在 nums[i] = x, nums[j] = y, 且 x > y
        //  - 那么 [i, j] 范围内的元素可以任意跳转
        //      - 在 (i, j) 之间的数如果 > y, 则可以直接从 j 跳转过去
        //      - 在 (i, j) 之间的数如果 <= y, 则可以先跳到 i, 再从 i 跳转过去
        //
        // 如果存在在范围 R2 = [l2, r2] 中的所有数均大于紧前范围 R1 = [l1, r2), 则无法从 R1 跳转到 R2
        //  - 找到左边最大的数小于右边最小的数, 则当前位置就是一个分割线(无法从分割线左边跳到分割线右边)
        int[] preMax = new int[nums.length]; preMax[0] = nums[0];
        for (int i = 1; i < nums.length; i++) preMax[i] = Math.max(preMax[i - 1], nums[i]);

        int sufMin = Integer.MAX_VALUE, ans = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 找到了一个分割线, 我们无法从位置 i 跳到 [i + 1, n) 的位置上, 所以答案就是 [?, i] 的最大值
            if (preMax[i] <= sufMin) ans = preMax[i];

            // 记录后缀最小值并保存答案
            sufMin = Math.min(sufMin, nums[i]); nums[i] = ans;
        }

        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxValue(new int[]{16,5,2,25,2}), new int[]{25,25,25,25,25});
        assert Checker.check(new Solution().maxValue(new int[]{30,21,5,35,24}), new int[]{35,35,35,35,35});

        assert Checker.check(new Solution().maxValue(new int[]{2,1,3}), new int[]{2,2,3});
        assert Checker.check(new Solution().maxValue(new int[]{2,3,1}), new int[]{3,3,3});
    }

}
