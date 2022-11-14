package daily.d221114p805splitarraywithsameaverage;

import common.TODO;

import java.util.HashSet;
import java.util.Set;

/**
 * 805. Split Array With Same Average
 *
 * https://leetcode.cn/problems/split-array-with-same-average/
 *
 * You are given an integer array nums.
 *
 * You should move each element of nums into one of the two arrays A and B such that
 * A and B are non-empty, and average(A) == average(B).
 *
 * Return true if it is possible to achieve that and false otherwise.
 *
 * Note that for an array arr, average(arr) is the sum of all the elements of arr
 * over the length of arr.
 */

public class Solution {

    @TODO
    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1) return false;

        int n = nums.length, sum = 0;
        for (var v : nums) sum += v;

        int half = n / 2;
        boolean ok = false;
        for (int i = 1; i <= half; i++) {
            if (sum * i % n == 0) {
                ok = true; break;
            }
        }
        if (!ok) return false;

        Set<Integer>[] dp = new Set[half + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new HashSet<>();
        }

        dp[0].add(0);
        for (var v : nums) {
            for (int i = half; i >= 1; i--) {
                for (int x : dp[i - 1]) {
                    int curr = x + v;
                    if (curr * n == sum * i) {
                        return true;
                    }
                    dp[i].add(curr);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().splitArraySameAverage(new int[]{1,2,3,4,5,6,7,8});
        assert !new Solution().splitArraySameAverage(new int[]{3,1});
    }

}
