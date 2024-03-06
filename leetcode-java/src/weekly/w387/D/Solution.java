package weekly.w387.D;

import common.Checker;

import java.util.*;

/**
 * 3072. Distribute Elements Into Two Arrays II
 *
 * https://leetcode.cn/contest/weekly-contest-387/problems/distribute-elements-into-two-arrays-ii/
 *
 * You are given a 1-indexed array of integers nums of length n.
 *
 * We define a function greaterCount such that greaterCount(arr, val) returns the
 * number of elements in arr that are strictly greater than val.
 *
 * You need to distribute all the elements of nums between two arrays arr1 and arr2
 * using n operations.
 *
 * In the first operation, append nums[1] to arr1.
 * In the second operation, append nums[2] to arr2.
 *
 * Afterwards, in the ith operation:
 *
 * If greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]), append nums[i] to arr1.
 * If greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]), append nums[i] to arr2.
 * If greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]), append nums[i] to
 * the array with a lesser number of elements.
 * If there is still a tie, append nums[i] to arr1.
 * The array result is formed by concatenating the arrays arr1 and arr2.
 *
 * For example, if arr1 == [1,2,3] and arr2 == [4,5,6], then result = [1,2,3,4,5,6].
 *
 * Return the integer array result.
 */

public class Solution {

    public int[] resultArray(int[] nums) {
        buildMap(nums);

        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();

        a1.add(nums[0]); a2.add(nums[1]);
        update(tree1, getId(nums[0]));
        update(tree2, getId(nums[1]));

        for (int i = 2; i < nums.length; i++) {
            int g1 = greaterCount(a1.size(), tree1, nums[i]);
            int g2 = greaterCount(a2.size(), tree2, nums[i]);
            if (g1 > g2) { a1.add(nums[i]); update(tree1, getId(nums[i])); }
            else if (g1 < g2) { a2.add(nums[i]); update(tree2, getId(nums[i])); }
            else {
                if (a1.size() <= a2.size()) { a1.add(nums[i]); update(tree1, getId(nums[i])); }
                else { a2.add(nums[i]); update(tree2, getId(nums[i])); }
            }
        }

        int idx = 0;
        for (var v : a1) nums[idx++] = v;
        for (var v : a2) nums[idx++] = v;
        return nums;
    }

    private int greaterCount(int size, int[] tree, int x) {
        return size - query(tree, getId(x));
    }

    private int[] tree1 = null, tree2 = null;

    public void update(int[] tree, int x) {
        while (x < tree.length) {
            tree[x]++; x += lowbit(x);
        }
    }

    public int query(int[] tree, int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= lowbit(x);
        }
        return ans;
    }

    private int lowbit(int x) { return x & -x; }

    private final TreeSet<Integer> set = new TreeSet<>();
    private final Map<Integer, Integer> map = new HashMap<>();
    private int getId(int v) { return map.get(v); }
    private void buildMap(int[] nums) {
        for (var v : nums) set.add(v);
        for (var v : set) map.put(v, map.size() + 1);
        tree1 = new int[set.size() + 1];
        tree2 = new int[set.size() + 1];
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().resultArray(new int[]{2,1,3,3}), new int[]{2,3,1,3});
        assert Checker.check(new Solution().resultArray(new int[]{5,14,3,1,2}), new int[]{5,3,1,2,14});
        assert Checker.check(new Solution().resultArray(new int[]{3,3,3,3}), new int[]{3,3,3,3});
    }

}
