package weekly.w312.C;

import common.Checker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 6190. Find All Good Indices
 *
 * https://leetcode.cn/contest/weekly-contest-312/problems/find-all-good-indices/
 *
 * You are given a 0-indexed integer array nums of size n and a positive integer k.
 *
 * We call an index i in the range k <= i < n - k good if the following conditions are satisfied:
 *
 * The k elements that are just before the index i are in non-increasing order.
 * The k elements that are just after the index i are in non-decreasing order.
 * Return an array of all good indices sorted in increasing order.
 */

public class Solution {

    public List<Integer> goodIndices(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        Arrays.fill(prefix, 1); prefix[0] = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] <= nums[i - 1]) {
                prefix[i + 1] = prefix[i] + 1;
            }
        }

        int[] suffix = new int[nums.length];
        Arrays.fill(suffix, 1); suffix[nums.length - 1] = 0;
        for (int i = nums.length - 2; i > 0; i--) {
            if (nums[i] <= nums[i + 1]) {
                suffix[i - 1] = suffix[i] + 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (prefix[i] >= k && suffix[i] >= k) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().goodIndices(new int[]{
            478184,863008,716977,921182,182844,350527,541165,881224
        }, 1), List.of(1,2,3,4,5,6));

        assert Checker.check(new Solution().goodIndices(new int[]{
            878724,201541,179099,98437,35765,327555,475851,598885,849470,943442
        }, 4), List.of(4,5));

        assert Checker.check(new Solution().goodIndices(new int[]{2,1,1,1,3,4,1}, 2), List.of(2,3));
        assert Checker.check(new Solution().goodIndices(new int[]{2,1,1,2}, 2), List.of());
    }

}
