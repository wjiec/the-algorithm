package offer.p21diaozhengshuzushunxushiqishuweiyuoushuqianmianlcof;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 *
 * https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 *
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 */

public class Solution {

    public int[] exchange(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            while (l < r && nums[l] % 2 != 0) l++;
            while (l < r && nums[r] % 2 == 0) r--;

            int t = nums[l]; nums[l] = nums[r]; nums[r] = t;
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().exchange(new int[]{})));
        System.out.println(Arrays.toString(new Solution().exchange(new int[]{1, 2, 3, 4})));
        System.out.println(Arrays.toString(new Solution().exchange(new int[]{1, 3, 2, 4})));
    }

}
