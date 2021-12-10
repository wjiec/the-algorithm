package lcp.p6nayingbi;

/**
 * LCP 06. 拿硬币
 *
 * https://leetcode-cn.com/problems/na-ying-bi/
 *
 * 桌上有 n 堆力扣币，每堆的数量保存在数组 coins 中。我们每次可以选择任意一堆，拿走其中的一枚或者两枚，求拿完所有力扣币的最少次数。
 */

public class Solution {

    public int minCount(int[] coins) {
        int ans = 0;
        for (var n : coins) ans += (n / 2) + (n % 2);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minCount(new int[]{4,2,1}) == 4;
        assert new Solution().minCount(new int[]{2,3,10}) == 8;
    }

}
