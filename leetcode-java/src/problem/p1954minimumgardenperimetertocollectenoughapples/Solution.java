package problem.p1954minimumgardenperimetertocollectenoughapples;

import ability.Ability;

/**
 * 1954. Minimum Garden Perimeter to Collect Enough Apples
 *
 * https://leetcode.cn/problems/minimum-garden-perimeter-to-collect-enough-apples/
 *
 * In a garden represented as an infinite 2D grid, there is an apple tree planted at every integer coordinate.
 * The apple tree planted at an integer coordinate (i, j) has |i| + |j| apples growing on it.
 *
 * You will buy an axis-aligned square plot of land that is centered at (0, 0).
 *
 * Given an integer neededApples, return the minimum perimeter of a plot such that at least neededApples
 * apples are inside or on the perimeter of that plot.
 *
 * The value of |x| is defined as:
 *
 * x if x >= 0
 * -x if x < 0
 */

public class Solution {

    public long minimumPerimeter(long neededApples) {
        long curr = 0, n = 20001;
        for (long i = 1; i < n; i++) {
            // 4个角和位于坐标轴上的值
            curr += 4L * i + 4L * (i + i);
            // 除此之外的其他点, i+1, i+2, ..., i+i-1
            curr += 8 * Ability.Math.AP.sum(i + 1, i + i - 1, 1);
            if (curr >= neededApples) {
                return 8 * i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().minimumPerimeter(1) == 8;
        assert new Solution().minimumPerimeter(13) == 16;
        assert new Solution().minimumPerimeter(400) == 40;
        assert new Solution().minimumPerimeter(1000000000) == 5040;
    }

}
