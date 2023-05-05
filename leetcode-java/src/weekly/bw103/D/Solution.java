package weekly.bw103.D;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2659. Make Array Empty
 *
 * https://leetcode.cn/contest/biweekly-contest-103/problems/make-array-empty/
 *
 * You are given an integer array nums containing distinct numbers, and you can perform the
 * following operations until the array is empty:
 *
 * If the first element has the smallest value, remove it
 * Otherwise, put the first element at the end of the array.
 * Return an integer denoting the number of operations it takes to make nums empty.
 */

public class Solution {

    public long countOperationsToEmptyArray(int[] nums) {
        int n = nums.length;
        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        Arrays.sort(sorted, Comparator.comparingInt(i -> nums[i]));

        long ans = n; int prev = 1;
        for (int i = 0; i < sorted.length; i++) {
            int x = sorted[i] + 1;
            if (x >= prev) ans += (x - prev) - query(prev, x);
            else ans += (n - prev) + (x - i) + query(x, prev - 1);
            add(x); prev = x;
        }
        return ans;
    }

    private final int[] tree = new int[100_100];

    private void add(int x) {
        while (x < tree.length) {
            tree[x]++;
            x += lowbit(x);
        }
    }

    private int query(int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private int query(int l, int r) { return query(r) - query(l - 1); }
    private int lowbit(int x) { return x & -x; }

    public static void main(String[] args) {
        assert new Solution().countOperationsToEmptyArray(new int[]{-15,-19,5}) == 5;
        assert new Solution().countOperationsToEmptyArray(new int[]{1}) == 1;
        assert new Solution().countOperationsToEmptyArray(new int[]{-17, -18}) == 3;

        assert new Solution().countOperationsToEmptyArray(new int[]{3,4,-1}) == 5;
        assert new Solution().countOperationsToEmptyArray(new int[]{1,2,4,3}) == 5;
        assert new Solution().countOperationsToEmptyArray(new int[]{1,2,3}) == 3;
    }

}
