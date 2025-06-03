package weekly.bw156.B;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * Q2. Minimum Operations to Convert All Elements to Zero
 *
 * https://leetcode.cn/contest/biweekly-contest-156/problems/minimum-operations-to-convert-all-elements-to-zero/
 *
 * You are given an array nums of size n, consisting of non-negative integers.
 *
 * Your task is to apply some (possibly zero) operations on the array so that all elements become 0.
 *
 * In one operation, you can select a subarray [i, j] (where 0 <= i <= j < n) and set all
 * occurrences of the minimum non-negative integer in that subarray to 0.
 *
 * Return the minimum number of operations required to make all elements in the array 0.
 */

public class Solution {

    /** @noinspection DuplicatedCode*/
    private static class SegmentTree {
        private final int n;
        private final int[] tree;
        public SegmentTree(int[] nums) {
            n = nums.length;
            tree = new int[nums.length * 4];
            build(nums, 1, 0, nums.length - 1);
        }
        private void maintain(int p) { tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]); }
        private void build(int[] nums, int p, int l, int r) {
            if (l == r) { tree[p] = nums[l]; return; }

            int mid = l + (r - l) / 2;
            build(nums, p * 2, l, mid);
            build(nums, p * 2 + 1, mid + 1, r);
            maintain(p);
        }
        public void update(int i, int v) { update(1, 0, n - 1, i, v); }
        private void update(int p, int l, int r, int i, int v) {
            if (l == r) { tree[p] = v; return; }

            int mid = l + (r - l) / 2;
            if (i <= mid) update(p * 2, l, mid, i, v);
            else update(p * 2 + 1, mid + 1, r, i, v);
            maintain(p);
        }
        private int query(int ql, int qr) { return query(1, 0, n - 1, ql, qr); }
        private int query(int p, int l, int r, int ql, int qr) {
            if (ql > r || qr < l) return Integer.MAX_VALUE;
            if (ql <= l && r <= qr) return tree[p];

            int ans = Integer.MAX_VALUE, mid = l + (r - l) / 2;
            ans = Math.min(ans, query(p * 2, l, mid, ql, qr));
            ans = Math.min(ans, query(p * 2 + 1, mid + 1, r, ql, qr));
            return ans;
        }
    }

    private final Map<Integer, TreeSet<Integer>> m = new HashMap<>();

    // 选择一个子数组, 将子数组中的最小非负整数变为0
    public int minOperations(int[] nums) {
        int zeros = 0;
        SegmentTree st = new SegmentTree(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zeros++;
            m.computeIfAbsent(nums[i], k -> new TreeSet<>()).add(i);
        }
        if (m.size() - (zeros != 0 ? 1 : 0) == nums.length - zeros) return nums.length - zeros;

        int ans = 0;
        for (int i = 0, p = -1; i <= nums.length; i++) {
            if (i == nums.length || nums[i] == 0) {
                if (p + 1 < i) ans += dfs(p + 1, i - 1, st);
                p = i;
            }
        }
        return ans;
    }

    private int dfs(int l, int r, SegmentTree st) {
        if (l > r) return 0;

        int ans = 1;
        var grp = m.get(st.query(l, r)); // 找到最小值对应的索引数组
        // 更新所有的最小值并递归
        for (int p = l - 1; true; ) {
            Integer next = grp.higher(p);
            if (next == null || next > r) {
                ans += dfs(p + 1, r, st);
                break;
            }
            st.update(next, 0);
            ans += dfs(p + 1, next - 1, st);
            p = next;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations(new int[]{1,2,3}) == 3;
        assert new Solution().minOperations(new int[]{4,4,0}) == 1;

        assert new Solution().minOperations(new int[]{1,2,1,2,1,2}) == 4;
        assert new Solution().minOperations(new int[]{0, 2}) == 1;
        assert new Solution().minOperations(new int[]{3,1,2,1}) == 3;
    }

}
