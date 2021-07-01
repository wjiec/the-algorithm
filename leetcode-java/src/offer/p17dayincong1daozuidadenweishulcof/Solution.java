package offer.p17dayincong1daozuidadenweishulcof;

import java.util.Arrays;

/**
 * 剑指 Offer 17. 打印从1到最大的n位数
 *
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 *
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 */

public class Solution {

    public int[] printNumbers(int n) {
        if (n == 0) return new int[]{};

        int max = 1;
        for (int i = 0; i < n; i++) max *= 10;

        int[] ans = new int[max - 1];
        for (int i = 1; i < max; i++) {
            ans[i - 1] = i;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution().printNumbers(0)));
        System.out.println(Arrays.toString(new Solution().printNumbers(1)));
        System.out.println(Arrays.toString(new Solution().printNumbers(2)));
        System.out.println(Arrays.toString(new Solution().printNumbers(3)));
        System.out.println(Arrays.toString(new Solution().printNumbers(4)));
    }

}
