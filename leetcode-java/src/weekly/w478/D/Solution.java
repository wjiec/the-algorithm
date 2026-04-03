package weekly.w478.D;

import common.Checker;
import common.PrettyPrinter;

import java.util.Arrays;

/**
 * Q4. Minimum Operations to Equalize Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-478/problems/minimum-operations-to-equalize-subarrays/
 *
 * You are given an integer array nums and an integer k.
 *
 * In one operation, you can increase or decrease any element of nums by exactly k.
 *
 * You are also given a 2D integer array queries, where each queries[i] = [li, ri].
 *
 * For each query, find the minimum number of operations required to make all elements
 * in the subarray nums[li..ri] equal. If it is impossible, the answer for that query is -1.
 *
 * Return an array ans, where ans[i] is the answer for the ith query.
 */

public class Solution {

    private static class Node {
        private final int l, r; // 节点表示的区间范围
        private Node ln, rn; // 左右子节点
        private int count = 0; // 当前区间内数的数量
        private long sum = 0; // 当前区间内数的和

        public Node(int l, int r) { this(l, r, null, null, 0, 0); }
        public Node(int l, int r, Node ln, Node rn, int count, long sum) {
            this.l = l; this.r = r; this.ln = ln; this.rn = rn; this.count = count; this.sum = sum;
        }

        public static Node build(int l, int r) {
            Node node = new Node(l, r);
            if (l == r) return node;

            int mid = l + (r - l) / 2;
            node.ln = build(l, mid);
            node.rn = build(mid + 1, r);
            return node;
        }

        // 新增一个节点, 并保存历史信息
        public Node add(int i, int v) {
            Node node = new Node(l, r, ln, rn, count, sum);
            if (l == r) { node.count++; node.sum += v; return node; }

            int mid = l + (r - l) / 2;
            if (i <= mid) node.ln = node.ln.add(i, v);
            else node.rn = node.rn.add(i, v);

            return node.maintain();
        }

        // 找到在 [left, this] 区间内的第 k 小的数字
        public int kth(Node left, int k) {
            if (l == r) return l;

            int leftCount = ln.count - left.ln.count;
            if (k <= leftCount) return ln.kth(left.ln, k);
            return rn.kth(left.rn, k - leftCount);
        }

        // 查询在 [left, this] 区间内, 有多少个值小于 i, 且它们的和是多少
        public long[] query(Node left, int i) {
            if (r <= i) return new long[]{count - left.count, sum - left.sum};

            long[] ans = ln.query(left.ln, i);
            if (i > (l + (r - l) / 2)) {
                long[] rAns = rn.query(left.rn, i);
                ans[0] += rAns[0]; ans[1] += rAns[1];
            }
            return ans;
        }

        private Node maintain() {
            count = ln.count + rn.count;
            sum = ln.sum + rn.sum;
            return this;
        }
    }

    public long[] minOperations(int[] nums, int k, int[][] queries) {
        // 只能同时增加/减少 k, 所以在 [l, r] 范围内的数要 % k 同余, 否则无法达成
        //  - 可以将 nums[i] = v 变成 nums[i] = (v / k, v % k)
        //  - 如果余数相同此时就问题就变成在 [l, r] 范围内将所有的 v / k 增加/减少 1 修改为相同需要的次数是多少
        //
        // 1. 如何判断在区间 [l, r] 中的所有 v % k 都相同?
        //  - 使用 ST 表计算区间最大值以及最小值, 如果最大值与最小值相同则说明值都相同
        //
        // 2. 如何计算在区间 [l, r] 中的所有 v / k 执行多少次操作(增加/减少 1)后可以修改为一样的
        //  - 将所有数修改为中位数 mid 是最佳的方式, 此时的操作次数就是
        //      - <= mid: left_count * mid - left_sum
        //      - > mid: right_sum - right_count * mid
        //
        // 问题变成: 如何在区间 [l, r] 中找到第 (r - l + 1) / 2 小的元素
        //  - 可持久化线段树

        // 记录与左侧同余的最小下标
        int[] congruence = new int[nums.length];
        for (int i = 1; i < nums.length; i++) {
            congruence[i] = (nums[i] % k == nums[i - 1] % k) ? congruence[i - 1] : i;
        }
        int[] sorted = Arrays.stream(nums).distinct().sorted().toArray();

        // 构建可持久化线段树
        Node[] trees = new Node[nums.length + 1]; trees[0] = Node.build(0, nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            int j = Arrays.binarySearch(sorted, nums[i]);
            trees[i + 1] = trees[i].add(j, nums[i]);
        }

        long[] ans = new long[queries.length]; Arrays.fill(ans, -1);
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (congruence[r] > l) continue;

            // 计算中位数
            int size = ++r - l, si = trees[r].kth(trees[l], size / 2 + 1);
            int median = sorted[si]; // 得到原始中位数值

            long totalSum = trees[r].sum - trees[l].sum;
            // 找到所有小于 si 的数的数量以及总和
            long[] lessMedian = trees[r].query(trees[l], si);
            // 左侧需要移动的距离
            long distance = median * lessMedian[0] - lessMedian[1];
            // 右侧需要移动的距离
            distance += totalSum - lessMedian[1] - median * (size - lessMedian[0]);

            ans[i] = distance / k;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().minOperations(new int[]{1,4,7}, 3, new int[][]{{0,1},{0,2}}), new long[]{1,2});
    }

}
