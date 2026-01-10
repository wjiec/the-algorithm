package weekly.w472.D;

import common.Tag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Longest Balanced Subarray II
 *
 * https://leetcode.cn/contest/weekly-contest-472/problems/longest-balanced-subarray-ii/
 *
 * You are given an integer array nums.
 *
 * A subarray is called balanced if the number of distinct even numbers in the
 * subarray is equal to the number of distinct odd numbers.
 *
 * Return the length of the longest balanced subarray.
 */

@SuppressWarnings("ExtractMethodRecommender")
public class Solution {

    @SuppressWarnings("DuplicatedCode")
    private static class LazySegmentTree {
        private static class Node {
            private int lazy = 0, min = 0, max = 0;
            // 叶子节点的 min == max, 非叶子节点会在后续的 maintain 中更新值
            public void update(int v) { lazy += v; min += v; max += v; }
        }

        private final int n;
        private final Node[] tree;
        public LazySegmentTree(int n) {
            this.n = n;
            tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(n - 1))];
            Arrays.setAll(tree, i -> new Node());
        }

        // 将当前节点的懒惰标记传播给子节点
        private void spread(int node) {
            if (tree[node].lazy != 0) {
                tree[node * 2].update(tree[node].lazy);
                tree[node * 2 + 1].update(tree[node].lazy);
                tree[node].lazy = 0;
            }
        }
        // 维护当前节点的信息
        private void maintain(int node) {
            tree[node].min = Math.min(tree[node * 2].min, tree[node * 2 + 1].min);
            tree[node].max = Math.max(tree[node * 2].max, tree[node * 2 + 1].max);
        }

        public void update(int ql, int qr, int v) { update(1, 0, n - 1, ql, qr, v); }
        private void update(int node, int l, int r, int ql, int qr, int v) {
            if (ql <= l && r <= qr) { tree[node].update(v); return; }

            spread(node);
            int mid = l + (r - l) / 2;
            if (ql <= mid) update(node * 2, l, mid, ql, qr, v);
            if (qr > mid) update(node * 2 + 1, mid + 1, r, ql, qr, v);
            maintain(node);
        }

        // 在线段树中查询 [ql, qr] 范围内第一个等于 target 的元素下标
        public int first(int ql, int qr, int target) { return first(1, 0, n - 1, ql, qr, target); }
        private int first(int node, int l, int r, int ql, int qr, int target) {
            // 如果查询区间不在当前节点的区间之内, 或者当前的目标值不在当前区间的 [min, max] 范围内, 则说明没找到
            if (qr < l || ql > r || target < tree[node].min || target > tree[node].max) return -1;
            if (l == r) return l; // 叶子节点就说明找到了

            spread(node);
            int mid = l + (r - l) / 2;
            // 先找左子树
            int ans = first(node * 2, l, mid, ql, qr, target);
            // 如果左子树中找不到, 再找右子树
            if (ans < 0) ans = first(node * 2 + 1, mid + 1, r, ql, qr, target);
            return ans;
        }
    }

    @Tag({"线段树二分", "介值定理"})
    public int longestBalanced(int[] nums) {
        // 我们考虑只保留最近一次出现的元素, 之前出现的元素就都变成 0 了(不影响前缀和的计算)
        //  - 对于出现的偶数我们将其记作 1, 出现的奇数我们将其记作 -1 (p525)
        //
        // 我们需要使用懒更新线段树来更新前缀和
        //  - 在首次遇到一个元素时, 根据奇偶性选择更新当前位置的数为 1 或者 -1
        //  - 当再次碰到一个元素时, 我们需要撤销前一次的修改, 设前一次的位置是 l, 当前的位置是 r
        //      - 撤销之前一次的修改在区间 [l, n) 时执行了 +1 或者 -1 的修改
        //      - 新增本次在 [r, n) 进行 +1 或者 -1 的操作
        //  - 实际上也就是撤销在 [l, r) 之间执行修改
        //
        // 执行完修改之后, 当前位置的前缀和为 s, 我们需要在前缀和数组中找到第一个 s 出现的位置
        //  - 我们需要在线段树中维护一个 min, max 值, 每次我们二分线段树进行查找是否有 [min, max] 的数
        //  - 由于我们的数组是从 [-1, 0, 1] 来生成的前缀和, 所以我们前缀和是连续的
        //      - 如果不是连续的(比如可以直接从 2 -> 4), 那么就需要用分块来求解
        int n = nums.length;
        LazySegmentTree st = new LazySegmentTree(n + 1);

        // 保存数字最后一次出现的位置
        int ans = 0, sum = 0;
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int v = nums[i - 1], d = v % 2 == 0 ? 1 : -1;
            if (!last.containsKey(v)) {
                sum += d; st.update(i, n, d);
            } else st.update(last.get(v), i - 1, -d);

            // 更新数字最后一次出现的位置
            last.put(v, i);

            // 在 [0, i) 找到第一个等于 sum 的位置
            int l = st.first(0, i - 1, sum);
            if (l >= 0) ans = Math.max(ans, i - l);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
