package problem.p2426numberofpairssatisfyinginequality;

/**
 * 2426. Number of Pairs Satisfying Inequality
 *
 * https://leetcode.cn/problems/number-of-pairs-satisfying-inequality/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2, each of size n, and an integer diff.
 *
 * Find the number of pairs (i, j) such that:
 *
 * 0 <= i < j <= n - 1 and
 * nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
 *
 * Return the number of pairs that satisfy the conditions.
 */

public class Solution {

    private final int[] tree = new int[100_100];

    // nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff
    // nums1[i] - nums2[i] <= nums1[j] - nums2[j] + diff
    public long numberOfPairs(int[] nums1, int[] nums2, int diff) {
        long ans = 0, n = nums1.length;
        for (int i = 0; i < n; i++) {
            int curr = nums1[i] - nums2[i] + 50000;
            ans += query(curr + diff);
            update(curr);
        }

        return ans;
    }

    private void update(int k) {
        while (k < tree.length) {
            tree[k] += 1;
            k += lowbit(k);
        }
    }

    private int query(int k) {
        int ans = 0;
        while (k > 0) {
            ans += tree[k];
            k -= lowbit(k);
        }
        return ans;
    }

    private int lowbit(int v) { return v & -v; }

    public static void main(String[] args) {
    }

}
