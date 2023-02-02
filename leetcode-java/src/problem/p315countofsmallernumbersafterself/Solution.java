package problem.p315countofsmallernumbersafterself;

import common.Checker;

import java.util.*;

/**
 * 315. Count of Smaller Numbers After Self
 *
 * https://leetcode.cn/problems/count-of-smaller-numbers-after-self/
 *
 * Given an integer array nums, return an integer array counts
 * where counts[i] is the number of smaller elements
 * to the right of nums[i].
 */

public class Solution {

    public List<Integer> countSmaller(int[] nums) {
        Set<Integer> unique = new HashSet<>();
        for (var v : nums) unique.add(v);

        int idx = 0;
        int[] sorted = new int[unique.size()];
        for (var v : unique) sorted[idx++] = v;
        Arrays.sort(sorted);

        Map<Integer, Integer> idMap = new HashMap<>();
        for (var v : nums) idMap.putIfAbsent(v, Arrays.binarySearch(sorted, v) + 1);
        tree = new int[idMap.size() + 10];

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) ans.add(0);
        for (int i = nums.length - 1; i >= 0; i--) {
            ans.set(i, query(idMap.get(nums[i]) - 1));
            add(idMap.get(nums[i]));
        }
        return ans;
    }

    private int[] tree = null;

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

    private int lowbit(int x) { return x & -x; }

    public static void main(String[] args) {
        assert Checker.check(new Solution().countSmaller(new int[]{5,2,6,1}), List.of(2,1,1,0));
        assert Checker.check(new Solution().countSmaller(new int[]{-1}), List.of(0));
        assert Checker.check(new Solution().countSmaller(new int[]{-1, -1}), List.of(0, 0));
        assert Checker.check(new Solution().countSmaller(new int[]{101,6,5,99,3,2,1}), List.of(6, 4, 3, 3, 2, 1, 0));
    }

}
