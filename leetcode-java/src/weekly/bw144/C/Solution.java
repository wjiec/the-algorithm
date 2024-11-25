package weekly.bw144.C;

import common.Tag;

import java.util.Arrays;

/**
 * 3362. Zero Array Transformation III
 *
 * https://leetcode.cn/contest/biweekly-contest-144/problems/zero-array-transformation-iii/
 *
 * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri].
 *
 * Each queries[i] represents the following action on nums:
 *
 * Decrement the value at each index in the range [li, ri] in nums by at most 1.
 * The amount by which the value is decremented can be chosen independently for each index.
 * A Zero Array is an array with all its elements equal to 0.
 *
 * Return the maximum number of elements that can be removed from queries, such that nums can
 * still be converted to a zero array using the remaining queries. If it is not possible to
 * convert nums to a zero array, return -1.
 */

/** @noinspection DuplicatedCode*/
public class Solution {

    private int[] tree = null;
    private int[] lazy = null;

    @Tag({"最小值线段树", "线段树", "懒惰标记"})
    public int maxRemoval(int[] nums, int[][] queries) {
        int[] diff = new int[nums.length + 1];
        for (var query : queries) {
            diff[query[0]]++;
            diff[query[1] + 1]--;
        }

        int[] remain = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) diff[i] += diff[i - 1];
            if (diff[i] < nums[i]) return -1;
            remain[i] = diff[i] - nums[i];
        }

        tree = new int[nums.length * 4 + 5];
        lazy = new int[nums.length * 4 + 5];
        build(remain, 0, nums.length - 1, 1);
        Arrays.sort(queries, (a, b) -> {
            int al = a[1] - a[0] + 1;
            int bl = b[1] - b[0] + 1;
            if (al != bl) return al - bl;
            if (a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int ans = 0;
        for (var elem : queries) {
            if (query(elem[0], elem[1], 0, nums.length - 1, 1) > 0) {
                ans++; update(elem[0], elem[1], -1, 0, nums.length - 1, 1);
            }
        }

        return ans;
    }

    // 对 [s,t] 区间建立线段树,当前根的编号为 p
    private void build(int[] array, int s, int t, int p) {
        if (s == t) { tree[p] = array[s]; return; }

        int mid = s + (t - s) / 2;
        build(array, s, mid, p * 2);
        build(array, mid + 1, t, p * 2 + 1);
        tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]);
    }

    // [l, r] 为查询区间, [s, t] 为当前节点包含的区间, p 为当前节点的编号
    private int query(int l, int r, int s, int t, int p) {
        // 当前区间为询问区间的子集时直接返回当前区间的和
        if (l <= s && t <= r) return tree[p];

        int mid = s + (t - s) / 2, ans = Integer.MAX_VALUE;
        if (lazy[p] != 0) pushDown(s, t, p);

        if (l <= mid) ans = Math.min(ans, query(l, r, s, mid, p * 2));
        if (r > mid) ans = Math.min(ans, query(l, r, mid + 1, t, p * 2 + 1));
        return ans;
    }

    // [l, r] 为修改区间, c 为被修改的元素的变化量, [s, t] 为当前节点包含的区间, p 为当前节点的编号
    private void update(int l, int r, int c, int s, int t, int p) {
        if (l <= s && t <= r) { tree[p] += c; lazy[p] += c; return; }

        int mid = s + (t - s) / 2;
        // 如果当前节点的懒标记非空,则更新当前节点两个子节点的值和懒标记值
        if (lazy[p] != 0) pushDown(s, t, p);

        if (l <= mid) update(l, r, c, s, mid, p * 2);
        if (r > mid) update(l, r, c, mid + 1, t, p * 2 + 1);
        tree[p] = Math.min(tree[p * 2], tree[p * 2 + 1]);
    }

    // 向下传递懒惰标记
    private void pushDown(int s, int t, int p) {
        if (lazy[p] != 0) {
            // 如果当前节点的懒标记非空,则更新当前节点两个子节点的值和懒标记值
            tree[p * 2] += lazy[p];
            tree[p * 2 + 1] += lazy[p];

            // 将标记下传给子节点
            lazy[p * 2] += lazy[p];
            lazy[p * 2 + 1] += lazy[p];
            lazy[p] = 0;
        }
    }

    public static void main(String[] args) {
        assert new Solution().maxRemoval(new int[]{2,1,1,5,4,2,5}, new int[][]{{5,6},{3,5},{3,6},{1,6},{1,4},{0,6},{2,3},{3,5},{0,3},{4,5},{0,6},{1,2},{4,4},{0,1},{6,6},{0,6},{4,6},{0,1},{1,3}}) == 14;

        assert new Solution().maxRemoval(new int[]{1,3}, new int[][]{{1,1},{0,1},{1,1},{0,1}}) == 1;
        assert new Solution().maxRemoval(new int[]{2,0,2}, new int[][]{{0, 2}, {0, 2}, {1, 1}}) == 1;
        assert new Solution().maxRemoval(new int[]{1,1,1,1}, new int[][]{{1,3},{0,2},{1,3},{1,2}}) == 2;
        assert new Solution().maxRemoval(new int[]{1,2,3,4}, new int[][]{{0, 3}}) == -1;
    }

}
