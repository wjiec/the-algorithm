package weekly.bw139.D;

import common.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 3288. Length of the Longest Increasing Path
 *
 * https://leetcode.cn/problems/length-of-the-longest-increasing-path/
 *
 * You are given a 2D array of integers coordinates of length n and an integer k, where 0 <= k < n.
 *
 * coordinates[i] = [xi, yi] indicates the point (xi, yi) in a 2D plane.
 *
 * An increasing path of length m is defined as a list of points (x1, y1), (x2, y2), (x3, y3), ..., (xm, ym) such that:
 *
 * xi < xi + 1 and yi < yi + 1 for all i where 1 <= i < m.
 * (xi, yi) is in the given coordinates for all i where 1 <= i <= m.
 *
 * Return the maximum length of an increasing path that contains coordinates[k].
 */

public class Solution {

    @Tag({"最长递增子序列", "LIS", "二分优化的LIS"})
    public int maxPathLength(int[][] coordinates, int k) {
        int kx = coordinates[k][0], ky = coordinates[k][1];
        // 按 x 从小到大排序, 然后x就是自然递增的, 然后在在遍历 x 的过程中对 y 求 LIS (二分优化)
        Arrays.sort(coordinates, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (b[1] - a[1]));

        // 在使用二分优化的 LIS 时, 数组中保存的并不一定是合法的最小上升子序列, 但是长度是准确的
        //  二分之后替换的目的是为了让后面的数可以更容易的找到合适的位置以便扩展长度
        List<Integer> lis = new ArrayList<>();
        for (var curr : coordinates) {
            // 只检查满足所选择条件的点是否可以加入LIS
            if ((curr[0] < kx && curr[1] < ky) || (curr[0] > kx && curr[1] > ky)) {
                // lis 中保存的是 y 的递增序列, 此时我们在 x 递增上进行迭代
                // 对于相同的 x, 我们会不断使用新的比较小的 y 去替换之前选择的比较大的 y
                //  (0, 0), (1, 9), (1, 8), (2, 9)
                var idx = Collections.binarySearch(lis, curr[1]);
                if (idx < 0) idx = -idx - 1;

                if (idx < lis.size()) lis.set(idx, curr[1]);
                else lis.add(curr[1]);
            }
        }

        return lis.size() + 1; // 叠加第 k 个数
    }

    public static void main(String[] args) {
    }

}
