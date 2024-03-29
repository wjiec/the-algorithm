package weekly.w340.B;

import common.Checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2615. Sum of Distances
 *
 * https://leetcode.cn/contest/weekly-contest-340/problems/sum-of-distances/
 *
 * You are given a 0-indexed integer array nums. There exists an array arr of length nums.length,
 * where arr[i] is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i.
 *
 * If there is no such j, set arr[i] to be 0.
 *
 * Return the array arr.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    public long[] distance(int[] nums) {
        Map<Integer, Long> sum = new HashMap<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            sum.merge(nums[i], (long) i, Long::sum);
            map.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }

        long[] ans = new long[nums.length];
        for (var kv : map.entrySet()) {
            List<Integer> vs = kv.getValue();
            long prefix = 0, suffix = sum.get(kv.getKey());
            for (int i = 0, n = vs.size(); i < n; i++) {
                int v = vs.get(i); suffix -= v;
                ans[v] = ((long) i * v - prefix) + (suffix - (n - i - 1L) * v);
                prefix += v;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().distance(new int[]{2,0,2,2,6,5,2}), new long[]{11,0,7,7,0,0,13});

        assert Checker.check(new Solution().distance(new int[]{1,3,1,1,2}), new long[]{5,0,3,4,0});
    }

}
