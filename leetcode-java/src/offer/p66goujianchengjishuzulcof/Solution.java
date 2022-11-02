package offer.p66goujianchengjishuzulcof;

import common.Checker;

/**
 * 剑指 Offer 66. 构建乘积数组
 *
 * https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/
 *
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 *
 * 不能使用除法。
 */

public class Solution {

    public int[] constructArr(int[] a) {
        if (a.length == 0) return new int[0];

        int[] ans = new int[a.length]; ans[0] = 1;
        for (int i = 1, v = 1; i < a.length; i++) {
            v *= a[i - 1]; ans[i] = v;
        }
        for (int i = a.length - 2, v = 1; i >= 0; i--) {
            v *= a[i + 1]; ans[i] *= v;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().constructArr(new int[]{7, 2, 2, 4, 2, 1, 8, 8, 9, 6, 8, 9, 6, 3, 2, 1}),
            new int[]{286654464,1003290624,1003290624,501645312,1003290624,2006581248,250822656,250822656,222953472,334430208,250822656,222953472,334430208,668860416,1003290624,2006581248});
        assert Checker.check(new Solution().constructArr(new int[]{1,2,3,4,5}), new int[]{120,60,40,30,24});
    }

}
