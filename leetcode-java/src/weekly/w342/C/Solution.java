package weekly.w342.C;

import common.Checker;

import java.util.TreeMap;

/**
 * 2653. Sliding Subarray Beauty
 *
 * https://leetcode.cn/contest/weekly-contest-342/problems/sliding-subarray-beauty/
 *
 * Given an integer array nums containing n integers, find the beauty of each subarray of size k.
 *
 * The beauty of a subarray is the xth smallest integer in the subarray if
 * it is negative, or 0 if there are fewer than x negative integers.
 *
 * Return an integer array containing n - k + 1 integers, which denote the
 * beauty of the subarrays in order from the first index in the array.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int lc = 0, rc = 0;
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();
        for (int i = 0; i < k - 1; i++) {
            lc++;
            l.merge(nums[i], 1, Integer::sum);
        }
        while (l.size() > x) {
            rc++; lc--;
            r.merge(removeLast(l), 1, Integer::sum);
        }

        int[] ans = new int[nums.length - k + 1];
        for (int i = k - 1; i < nums.length; i++) {
            lc++;
            l.merge(nums[i], 1, Integer::sum);
            while (lc > x) {
                lc--; rc++;
                r.merge(removeLast(l), 1, Integer::sum);
            }

            if (lc + rc > k) {
                int s = nums[i - k];
                if (l.containsKey(s)) { lc--; l.merge(s, -1, Solution::sum); }
                else { rc--; r.merge(s, -1, Solution::sum); }
                if (lc < x) {
                    lc++; rc--;
                    l.merge(removeFirst(r), 1, Solution::sum);
                }
            }

            ans[i - k + 1] = Math.min(l.lastKey(), 0);
        }
        return ans;
    }

    private int removeLast(TreeMap<Integer, Integer> map) {
        assert !map.isEmpty();

        int last = map.lastKey();
        map.merge(last, -1, Solution::sum);
        return last;
    }

    private int removeFirst(TreeMap<Integer, Integer> map) {
        assert !map.isEmpty();

        int last = map.firstKey();
        map.merge(last, -1, Solution::sum);
        return last;
    }

    private static Integer sum(Integer a, Integer b) { return (a + b == 0) ? null : (a + b); }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getSubarrayBeauty(new int[]{-3,1,2,-3,0,-3}, 2, 1), new int[]{-3, 0, -3, -3, -3});
        assert Checker.check(new Solution().getSubarrayBeauty(new int[]{-35,-33,41,41,27,-16}, 5, 5), new int[]{0, 0});
    }

}
