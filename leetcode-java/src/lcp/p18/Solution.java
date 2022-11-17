package lcp.p18;

import java.util.Arrays;

/**
 * LCP 18. 早餐组合
 *
 * https://leetcode-cn.com/problems/2vYnGI/
 *
 * 小扣在秋日市集选择了一家早餐摊位，一维整型数组 staple 中记录了每种主食的价格，一维整型数组 drinks 中记录了每种饮料的价格。
 *
 * 小扣的计划选择一份主食和一款饮料，且花费不超过 x 元。请返回小扣共有多少种购买方案。
 *
 * 注意：答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple); Arrays.sort(drinks);

        int ans = 0, MOD = 1000000007;
        for (int i = staple.length - 1;  i >= 0; i--) {
            if (staple[i] < x) {
                ans = (ans + count(drinks, x - staple[i])) % MOD;
            }
        }
        return ans;
    }

    private int count(int[] drinks, int x) {
        int l = 0, r = drinks.length;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (drinks[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        assert new Solution().breakfastNumber(new int[]{10,20,5}, new int[]{5,5,2}, 15) == 6;
        assert new Solution().breakfastNumber(new int[]{2,1,1}, new int[]{8,9,5,1}, 9) == 8;
    }

}
