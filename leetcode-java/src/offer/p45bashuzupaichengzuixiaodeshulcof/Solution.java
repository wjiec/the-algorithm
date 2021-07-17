package offer.p45bashuzupaichengzuixiaodeshulcof;

import java.util.Arrays;

/**
 * 剑指 Offer 45. 把数组排成最小的数
 *
 * https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
 *
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 */

public class Solution {

    private static class Number implements Comparable<Number> {
        private int len;
        private final int[] digits = new int[32];
        public Number(int val) {
            for (; val != 0; val /= 10) digits[len++] = val % 10;
            for (int l = 0, r = len - 1, mid = len / 2; l < mid; l++, r--) {
                int t = digits[l];
                digits[l] = digits[r];
                digits[r] = t;
            }
        }

        @Override
        public String toString() {
            if (len == 0) return "0";

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) sb.append(digits[i]);
            return sb.toString();
        }

        @Override
        public int compareTo(Number o) {
            return (toString() + o.toString()).compareTo(o.toString() + toString());
        }
    }

    public String minNumber(int[] nums) {
        Number[] numbers = new Number[nums.length];
        for (int i = 0, l = nums.length; i < l; i++) {
            numbers[i] = new Number(nums[i]);
        }

        Arrays.sort(numbers);
        StringBuilder sb = new StringBuilder();
        for (var number : numbers) {
            sb.append(number);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().minNumber(new int[]{121,12}).equals("12112");
        assert new Solution().minNumber(new int[]{10,2,0}).equals("0102");
        assert new Solution().minNumber(new int[]{10,2,0,99,123,444,77,10,20,30,55}).equals("0101012320230444557799");

        assert new Solution().minNumber(new int[]{10,2}).equals("102");
        assert new Solution().minNumber(new int[]{3,30,34,5,9}).equals("3033459");
    }

}
