package offer2.p4;

/**
 * 剑指 Offer II 004. 只出现一次的数字
 *
 * https://leetcode.cn/problems/WGki4K/
 *
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。
 * 请你找出并返回那个只出现了一次的元素。
 */

public class Solution {

    public int singleNumber(int[] nums) {
        int[] bits = new int[33];
        for (var v : nums) {
            for (int i = 0; v != 0; v >>>= 1, i++) {
                bits[i] += v & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().singleNumber(new int[]{-2,-2,1,1,4,1,4,4,-4,-2}) == -4;

        assert new Solution().singleNumber(new int[]{2,2,3,2}) == 2;
        assert new Solution().singleNumber(new int[]{0,1,0,1,0,1,100}) == 100;
    }

}
