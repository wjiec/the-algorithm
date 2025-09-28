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
        // 反向思考:
        //  - 大的数的答案可以传播给后面较小的数的位置
        //  - 小的数的答案可以传播到前面较大的数的位置
        return nums;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().maxValue(new int[]{16,5,2,25,2}), new int[]{25,25,25,25,25});
        assert Checker.check(new Solution().maxValue(new int[]{30,21,5,35,24}), new int[]{35,35,35,35,35});

        assert Checker.check(new Solution().maxValue(new int[]{2,1,3}), new int[]{2,2,3});
        assert Checker.check(new Solution().maxValue(new int[]{2,3,1}), new int[]{3,3,3});
    }

}
