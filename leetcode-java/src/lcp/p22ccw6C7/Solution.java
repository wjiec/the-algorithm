package lcp.p22ccw6C7;

/**
 * LCP 22. 黑白方格画
 *
 * https://leetcode-cn.com/problems/ccw6C7/
 *
 * 小扣注意到秋日市集上有一个创作黑白方格画的摊位。摊主给每个顾客提供一个固定在墙上的白色画板，画板不能转动。
 *
 * 画板上有 n * n 的网格。
 *
 * 绘画规则为，小扣可以选择任意多行以及任意多列的格子涂成黑色（选择的整行、整列均需涂成黑色），所选行数、列数均可为 0。
 *
 * 小扣希望最终的成品上需要有 k 个黑色格子，请返回小扣共有多少种涂色方案。
 *
 * 注意：两个方案中任意一个相同位置的格子颜色不同，就视为不同的方案。
 */

public class Solution {

    public int paintingPlan(int n, int k) {
        if (k == 0 || k == n * n) return 1;
        if (k < n || k > n * n) return 0;

        int ans = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if ((i + j) * n - i * j == k) {
                    ans += count(i, n) * count(j, n);
                }
            }
        }
        return ans;
    }

    private int count(int x, int y) {
        if (x == 0) return 1;

        int c = 1;
        for (int i = 0; i < x; i++) {
            c *= (y - i);
        }
        for (int i = 1; i <= x; i++) {
            c /= i;
        }
        return c;
    }

    public static void main(String[] args) {
        assert new Solution().paintingPlan(2, 2) == 4;
        assert new Solution().paintingPlan(2, 1) == 0;
        assert new Solution().paintingPlan(2, 4) == 1;
    }

}
