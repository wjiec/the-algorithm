package lcp.p68;

/**
 * LCP 68. 美观的花束
 *
 * https://leetcode.cn/problems/1GxJYY/
 *
 * 力扣嘉年华的花店中从左至右摆放了一排鲜花，记录于整型一维矩阵 flowers 中每个数字表示该位置所种鲜花的品种编号。
 * 你可以选择一段区间的鲜花做成插花，且不能丢弃。
 *
 * 在你选择的插花中，如果每一品种的鲜花数量都不超过 cnt 朵，那么我们认为这束插花是 「美观的」。
 *
 * 例如：[5,5,5,6,6] 中品种为 5 的花有 3 朵， 品种为 6 的花有 2 朵，每一品种 的数量均不超过 3
 * 请返回在这一排鲜花中，共有多少种可选择的区间，使得插花是「美观的」。
 *
 * 注意：
 *
 * 答案需要以 1e9 + 7 (1000000007) 为底取模，如：计算初始结果为：1000000008，请返回 1
 */

public class Solution {

    public int beautifulBouquet(int[] flowers, int cnt) {
        int ans = 0, MOD = 1_000_000_007;
        int[] counts = new int[100_001];
        for (int l = 0, r = 0; r < flowers.length; r++) {
            int flower = flowers[r]; counts[flower]++;
            while (counts[flower] > cnt) counts[flowers[l++]]--;
            ans = (ans + r - l + 1) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().beautifulBouquet(new int[]{1,2,3,2}, 1) == 8;
        assert new Solution().beautifulBouquet(new int[]{5,3,3,3}, 2) == 8;
    }

}
