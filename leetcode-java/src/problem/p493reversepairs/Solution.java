package problem.p493reversepairs;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 493. Reverse Pairs
 *
 * https://leetcode.cn/problems/reverse-pairs/
 *
 * Given an integer array nums, return the number of reverse pairs in the array.
 *
 * A reverse pair is a pair (i, j) where:
 *
 * 0 <= i < j < nums.length and
 * nums[i] > 2 * nums[j].
 */

public class Solution {

    public int reversePairs(int[] nums) {
        TreeSet<Long> unique = new TreeSet<>();
        for (var v : nums) unique.add(2L * v);

        TreeMap<Long, Integer> seq = new TreeMap<>();
        for (var u : unique) seq.put(u, seq.size() + 1);

        int ans = 0, n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            Map.Entry<Long, Integer> e = seq.lowerEntry((long) nums[i]);
            if (e != null) ans += query(e.getValue());
            update(seq.get(2L * nums[i]));
        }
        return ans;
    }

    private final int[] tree = new int[50010];

    private void update(int x) {
        while (x < tree.length) {
            tree[x] += 1;
            x += lowbit(x);
        }
    }

    private int query(int x) {
        int ans = 0;
        while (x != 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private int lowbit(int x) { return x & (-x); }

    public static void main(String[] args) {
        assert new Solution().reversePairs(new int[]{1,3,2,3,1}) == 2;
        assert new Solution().reversePairs(new int[]{2,4,3,5,1}) == 3;
    }

}
