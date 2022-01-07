package lcci.s17.p4missingnumberlcci;

/**
 * 面试题 17.04. 消失的数字
 *
 * https://leetcode-cn.com/problems/missing-number-lcci/
 *
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 */

public class Solution {

    public int missingNumber(int[] nums) {
        int sum = (nums.length + 1) * nums.length / 2;
        for (var n : nums) sum -= n;
        return sum;
    }

    public static void main(String[] args) {
        assert new Solution().missingNumber(new int[]{3,0,1}) == 2;
        assert new Solution().missingNumber(new int[]{9,6,4,2,3,5,7,0,1}) == 8;
    }

}
