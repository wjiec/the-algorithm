package problem.p330patchingarray;

import common.TODO;
import common.Tag;

/**
 * 330. Patching Array
 *
 * https://leetcode.cn/problems/patching-array/
 *
 * Given a sorted integer array nums and an integer n, add/patch elements
 * to the array such that any number in the range [1, n] inclusive can be
 * formed by the sum of some elements in the array.
 *
 * Return the minimum number of patches required.
 */

public class Solution {

    @TODO
    @Tag({"数字填充", "数学"})
    public int minPatches(int[] nums, int n) {
        // 对于正整数 x，如果区间 [1, x−1] 内的所有数字都已经被覆盖，且 x 在数组中
        // 则区间 [1, 2x − 1] 内的所有数字也都被覆盖了。
        long x = 1;
        int ans = 0, i = 0;
        while (x <= n) {
            if (i < nums.length && nums[i] <= x) x += nums[i++];
            else { x *= 2; ans++; }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minPatches(new int[]{1, 3}, 6) == 1;
        assert new Solution().minPatches(new int[]{1, 5, 10}, 20) == 2;
        assert new Solution().minPatches(new int[]{1, 2, 2}, 5) == 0;
        assert new Solution().minPatches(new int[]{1, 2, 2}, 65536) == 14;
        assert new Solution().minPatches(new int[]{1, 2, 2}, Integer.MAX_VALUE) == 14;
    }

}
