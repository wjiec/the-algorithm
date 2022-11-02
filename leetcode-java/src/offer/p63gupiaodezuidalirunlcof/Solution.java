package offer.p63gupiaodezuidalirunlcof;

/**
 * 剑指 Offer 63. 股票的最大利润
 *
 * https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/
 *
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 */

public class Solution {

    public int maxProfit(int[] prices) {
        int ans = 0, min = Integer.MAX_VALUE;
        for (var price : prices) {
            min = Math.min(min, price);
            ans = Math.max(ans, price - min);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxProfit(new int[]{7,1,5,3,6,4}) == 5;
        assert new Solution().maxProfit(new int[]{7,6,4,3,1}) == 0;
    }

}
