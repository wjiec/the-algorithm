package weekly.w459.C;

import java.util.ArrayList;
import java.util.List;

/**
 * Q3. Number of Integers With Popcount-Depth Equal to K II©leetcode
 *
 * https://leetcode.cn/contest/weekly-contest-459/problems/number-of-integers-with-popcount-depth-equal-to-k-ii/
 *
 * You are given an integer array nums.
 *
 * For any positive integer x, define the following sequence:
 *
 * p0 = x
 * pi+1 = popcount(pi) for all i >= 0, where popcount(y) is the number of set
 * bits (1's) in the binary representation of y.
 *
 * This sequence will eventually reach the value 1.
 *
 * The popcount-depth of x is defined as the smallest integer d >= 0 such that pd = 1.
 *
 * For example, if x = 7 (binary representation "111"). Then, the
 * sequence is: 7 → 3 → 2 → 1, so the popcount-depth of 7 is 3.
 *
 * You are also given a 2D integer array queries, where each queries[i] is either:
 *
 * [1, l, r, k] - Determine the number of indices j such that l <= j <= r and
 * the popcount-depth of nums[j] is equal to k.
 *
 * [2, idx, val] - Update nums[idx] to val.
 *
 * Return an integer array answer, where answer[i] is the number of indices
 * for the ith query of type [1, l, r, k].
 */

public class Solution {

    public int[] popcountDepth(long[] nums, long[][] queries) {
        // 使用大概 7 个树状数组来保存 depth = k 的数量关系
        int[] ds = new int[nums.length];
        int[][] trees = new int[7][nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            update(trees[(ds[i] = depth(nums[i]))], i + 1, 1);
        }

        List<Integer> ans = new ArrayList<>();
        for (var query : queries) {
            if (query[0] == 2) { // 更新操作
                int idx = (int) query[1]; long val = query[2];
                // 先删除旧的
                update(trees[ds[idx]], idx + 1, -1);
                // 增加新的
                update(trees[(ds[idx] = depth(val))], idx + 1, 1);
            } else {
                int l = (int) (query[1] + 1), r = (int) (query[2] + 1), k = (int) query[3];
                ans.add(query(trees[k], r) - query(trees[k], l - 1));
            }
        }

        int[] copy = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) copy[i] = ans.get(i);
        return copy;
    }

    private void update(int[] tree, int i, int v) {
        while (i < tree.length) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    private int query(int[] tree, int i) {
        int ans = 0;
        while (i > 0) {
            ans += tree[i];
            i -= lowbit(i);
        }
        return ans;
    }

    private int lowbit(int v) { return v & -v; }

    private int depth(long v) {
        return v == 1 ? 0 : depth(Long.bitCount(v)) + 1;
    }

    public static void main(String[] args) {
    }

}
