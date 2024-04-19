package problem.p2366minimumreplacementstosortthearray;

/**
 * 2366. Minimum Replacements to Sort the Array
 *
 * https://leetcode.cn/problems/minimum-replacements-to-sort-the-array
 *
 * You are given a 0-indexed integer array nums. In one operation you can replace any
 * element of the array with any two elements that sum to it.
 *
 * For example, consider nums = [5,6,7]. In one operation, we can replace nums[1] with 2
 * and 4 and convert nums to [5,2,4,7].
 *
 * Return the minimum number of operations to make an array that is sorted in non-decreasing order.
 */

public class Solution {

    public long minimumReplacement(int[] nums) {
        long ans = 0; int prev = Integer.MAX_VALUE / 2;
        for (int i = nums.length - 1; i >= 0; i--) {
            // 如果 nums[i] > prev, 那么就需要拆分当前数, 拆分出来的数应该小于等于 prev
            //  = nums[i] = a1 + a2 + a3 + ... + an <= n * prev (a[i] <= prev)
            //  => n >= ⌈nums[i] / prev⌉
            //
            // 为了使操作数 (n - 1) 尽可能小, 所以应该取等号, 即:
            //  => n = ⌈nums[i] / prev⌉
            //
            // 为了让拆分出的最小数最大, 拆分的最小数为 ⌊nums[i] / x⌋
            int div = (nums[i] - 1) / prev;
            ans += div;
            prev = nums[i] / (div + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumReplacement(new int[]{
            368,112,2,282,349,127,36,98,371,79,309,221,175,262,224,215,230, 250,
            84,269,384,328,118,97,17,105,342,344,242,160,394,17,120,335, 76,101,
            260,244,378,375,164,190,320,376,197,398,353,138,362,38,54, 172,3,300,
            264,165,251,24,312,355,237,314,397,101,117,268,36,165,373, 269,351,67,
            263,332,296,13,118,294,159,137,82,288,250,131,354,261,192, 111,16,139,
            261,295,112,121,234,335,256,303,328,242,260,346,22,277,179,223
        }) == 17748;
        assert new Solution().minimumReplacement(new int[]{12,9,7,6,17,19,21}) == 6;

        assert new Solution().minimumReplacement(new int[]{3, 9, 3}) == 2;
        assert new Solution().minimumReplacement(new int[]{1,2,3,4,5}) == 0;
    }

}
