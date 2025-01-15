package weekly.bw147.D;

import common.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3410. Maximize Subarray Sum After Removing All Occurrences of One Element
 *
 * https://leetcode.cn/contest/biweekly-contest-147/problems/maximize-subarray-sum-after-removing-all-occurrences-of-one-element/
 *
 * You are given an integer array nums.
 *
 * You can do the following operation on the array at most once:
 *
 * Choose any integer x such that nums remains non-empty on removing all occurrences of x.
 * Remove all occurrences of x from the array.
 *
 * Return the maximum subarray sum across all possible resulting arrays.
 */

public class Solution {

    // https://leetcode.cn/problems/maximum-subarray/solutions/228009/zui-da-zi-xu-he-by-leetcode-solution/
    private static class SegmentTree {
        // lSum 表示 [l,r] 内以 l 为左端点的最大子段和
        // rSum 表示 [l,r] 内以 r 为右端点的最大子段和
        // mSum 表示 [l,r] 内的最大子段和
        // iSum 表示 [l,r] 的区间和
        private record Node(long lSum, long rSum, long mSum, long iSum) {}

        private final Node[] tree;
        public SegmentTree(int[] nums) {
            tree = new Node[2 << (32 - Integer.numberOfLeadingZeros(nums.length))];
            build(nums, 1, 0, nums.length - 1);
        }

        private Node merge(Node l, Node r) {
            return new Node(
                Math.max(l.lSum, l.iSum + r.lSum),
                Math.max(r.rSum, r.iSum + l.rSum),
                Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum),
                l.iSum + r.iSum
            );
        }
        private void set(int target, long value) { tree[target] = new Node(value, value, value, value); }
        private void merge(int target) { tree[target] = merge(tree[target << 1], tree[(target << 1) | 1]); }

        private void build(int[] nums, int target, int l, int r) {
            if (l == r) { set(target, nums[l]); return; }

            int mid = l + (r - l) / 2;
            build(nums, target << 1, l, mid);
            build(nums, (target << 1) | 1, mid + 1, r);
            merge(target);
        }

        public void update(int target, int l, int r, int i, long val) {
            if (l == r) { set(target, val); return; }

            int mid = l + (r - l) / 2;
            if (i <= mid) update(target << 1, l, mid, i, val);
            else update((target << 1) | 1, mid + 1, r, i, val);
            merge(target);
        }

        private long answer() { return tree[1].mSum; }
    }

    @Tag({"最大子数组和", "线段树"})
    public long maxSubarraySum(int[] nums) {
        // 如果不删除元素, 就是求数组的最大子数组和, 即 https://leetcode.cn/problems/maximum-subarray
        // 使用分治的思想(即险段树)对最大子数组和进行求解, 对于删除操作我们将其变 0 操作实现.
        SegmentTree st = new SegmentTree(nums);
        long ans = st.answer(); // 不删除任何数
        if (ans <= 0) return ans; // 我们无法在一个全负数的数组里创造一个大于等于 0 的结果

        Map<Integer, List<Integer>> pos = new HashMap<>();
        for (int i = 0; i < nums.length; i++) pos.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        for (var e : pos.entrySet()) {
            for (var idx : e.getValue()) st.update(1, 0, nums.length - 1, idx, 0);
            ans = Math.max(ans, st.answer());
            for (var idx : e.getValue()) st.update(1, 0, nums.length - 1, idx, e.getKey());
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
