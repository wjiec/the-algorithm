package offer.p56shuzuzhongshuzichuxiandecishuiilcof;

/**
 * 剑指 Offer 56 - II. 数组中数字出现的次数 II
 *
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 *
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 */

public class Solution {

    public int singleNumber(int[] nums) {
        int[] binary = new int[32];
        for (var v : nums) {
            for (int i = 0; v != 0; i++, v >>= 1) {
                binary[i] += v & 1;
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (binary[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().singleNumber(new int[]{3,4,3,3}) == 4;
        assert new Solution().singleNumber(new int[]{9,1,7,9,7,9,7}) == 1;
    }

}
