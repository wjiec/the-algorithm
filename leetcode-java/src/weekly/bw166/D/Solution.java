package weekly.bw166.D;

import java.util.*;

/**
 * Q4. Maximize Alternating Sum Using Swaps
 *
 * https://leetcode.cn/contest/biweekly-contest-166/problems/maximize-alternating-sum-using-swaps/
 *
 * You are given an integer array nums.
 *
 * You want to maximize the alternating sum of nums, which is defined as the value
 * obtained by adding elements at even indices and subtracting elements at odd indices.
 * That is, nums[0] - nums[1] + nums[2] - nums[3]...
 *
 * You are also given a 2D integer array swaps where swaps[i] = [pi, qi]. For each
 * pair [pi, qi] in swaps, you are allowed to swap the elements at indices pi and qi.
 *
 * These swaps can be performed any number of times and in any order.
 *
 * Return the maximum possible alternating sum of nums.
 */

public class Solution {

    private static class UnionFind {
        // 记录每个节点的父节点是谁, 默认都指向自己
        private final int[] parent;
        private final int[] count;

        // 初始化并查集, 设置每个节点的父节点为自己
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = new int[n];
            Arrays.fill(count, 1);
        }

        // 合并两个节点
        public boolean union(int a, int b) {
            int fa = find(a), fb = find(b);
            if (fa == fb) return false;

            parent[fa] = fb;
            count[fb] += count[fa];
            return true;
        }

        // 查找指定节点的父节点同时压缩树
        public int find(int v) {
            while (v != parent[v]) {
                parent[v] = parent[parent[v]];
                v = parent[v];
            }
            return v;
        }

        public int count(int v) {
            return count[find(v)];
        }
    }

    public long maxAlternatingSum(int[] nums, int[][] swaps) {
        // 偶数下标的和 - 奇数下标的和
        //  - 也就是偶数下标尽可能大, 奇数下标尽可能小
        //
        // 如果 swaps[i] = {x, y} 相连, 那就是这个组内的都可以相互交换
        //  - 使用并查集连接所有连通分量
        //  - 按数字大小排序, 大的数优先分给偶数下标
        //      - 实际就是并查集里同一个连通分量的总和 - 后奇数下标数量的和
        UnionFind uf = new UnionFind(nums.length);
        for (var swap : swaps) uf.union(swap[0], swap[1]);

        long ans = 0;
        Map<Integer, Integer> oddCount = new HashMap<>();
        Map<Integer, List<Integer>> groupNums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (uf.count(i) == 1) { // 没有交换
                if ((i & 1) == 0) ans += nums[i];
                else ans -= nums[i];
            } else {
                // 否则的话加入到集合中并记录奇数下标的数量
                oddCount.merge(uf.find(i), i & 1, Integer::sum);
                groupNums.computeIfAbsent(uf.find(i), k -> new ArrayList<>()).add(nums[i]);
            }
        }

        // 每一组进行排序并计算最大可能和
        for (var group : oddCount.entrySet()) {
            int oc = group.getValue();
            var list = groupNums.get(group.getKey());
            list.sort(Integer::compare);
            // 最小的 oc 个数需要作为奇数下标
            for (int i = 0; i < list.size(); i++) {
                if (i < oc) ans -= list.get(i);
                else ans += list.get(i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxAlternatingSum(new int[]{1, 2}, new int[][]{{0, 1}}) == 1;
    }

}
