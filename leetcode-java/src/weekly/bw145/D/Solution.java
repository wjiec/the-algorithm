package weekly.bw145.D;

import ability.Ability.UnionFind;

import java.util.Arrays;

/**
 * 3378. Count Connected Components in LCM Graph
 *
 * https://leetcode.cn/contest/biweekly-contest-145/problems/count-connected-components-in-lcm-graph/
 *
 * You are given an array of integers nums of size n and a positive integer threshold.
 *
 * There is a graph consisting of n nodes with the ith node having a value of nums[i].
 * Two nodes i and j in the graph are connected via an undirected edge if lcm(nums[i], nums[j]) <= threshold.
 *
 * Return the number of connected components in this graph.
 *
 * A connected component is a subgraph of a graph in which there exists a path between any
 * two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 *
 * The term lcm(a, b) denotes the least common multiple of a and b.
 */

public class Solution {

    public int countComponents(int[] nums, int threshold) {
        // 将 lcm(x, y) 转换为 x * y / gcd(x, y) 然后枚举 gcd
        //  - 即求 x * y / gcd(x, y) <= threshold 也就是 x * y <= threshold * gcd(x, y)
        //      - x 与 y 都是 gcd(x, y) 的倍数
        //
        // 直接枚举 gcd, 然后在数组中找到最小的 gcd 倍数作为 x, 然后在数组中找到其他所有的 y

        // 由于数组中的值不重复, 首先我们把数组转换为 {v: index} 形式
        // 由于小于等于 threshold 的数 x 才可以找到匹配的 y 使得 lcm(x, y) <= threshold
        int[] indices = new int[threshold + 1]; Arrays.fill(indices, -1);
        for (int i = 0; i < nums.length; i++) if (nums[i] <= threshold) indices[nums[i]] = i;

        int ans = nums.length;
        UnionFind uf = new UnionFind(nums.length);
        // 枚举 gcd 并在数组中找到最小的 gcd 倍数
        //
        // 我们从 1 开始遍历, 每次遍历的数量为 threshold / gcd
        //  - 所有的遍历次数加起来即 threshold / 1 + threshold / 2 + ... + threshold / threshold
        //  - 提取 threshold 则等式为 threshold * (1 + 1 / 2 + 1 / 3 + ... + 1 / threshold)
        //  - 后面的调和级数收敛于 logN, 则最终的时间复杂度为 O(N * logN)
        for (int gcd = 1; gcd <= threshold; gcd++) {
            int foundX = -1;
            // 由于 lcm(x, y) <= threshold, 所以我们只需要在 <= threshold 范围内寻找就行
            for (var curr = gcd; curr <= threshold; curr += gcd) {
                if (indices[curr] >= 0) { foundX = curr; break; }
            }
            if (foundX == -1) continue;

            // 找到所有与 x 匹配的 y, 上限 = threshold * gcd(x, y) / x
            int upper = (int) ((long) threshold * gcd / foundX);
            // 我们已经找到了最小的 gcd 倍数是 foundX, 则 foundY 需要从 foundX 的下一个数开始
            for (int foundY = foundX + gcd; foundY <= upper; foundY += gcd) {
                if (indices[foundY] != -1) {
                    if (uf.union(indices[foundX], indices[foundY])) ans--; // 连通块的个数减少了一个
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countComponents(new int[]{2,4,8,3,9}, 5) == 4;
        assert new Solution().countComponents(new int[]{2,4,8,3,9,12}, 10) == 2;
    }

}
