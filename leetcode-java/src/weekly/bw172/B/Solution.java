package weekly.bw172.B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q2. Maximum Sum of Three Numbers Divisible by Three
 *
 * https://leetcode.cn/contest/biweekly-contest-172/problems/maximum-sum-of-three-numbers-divisible-by-three/
 *
 * You are given an integer array nums.
 *
 * Your task is to choose exactly three integers from nums such that their sum is divisible by three.
 *
 * Return the maximum possible sum of such a triplet. If no such triplet exists, return 0.
 */

public class Solution {

    public int maximumSum(int[] nums) {
        // 有以下几种情况
        //  - 3 个数的余数都是 0
        //  - 3 个数的余数都是 1
        //  - 3 个数的余数都是 2
        //  - 余数分别为 0 1 2
        List<Integer>[] mods = new List[3];
        Arrays.setAll(mods, i -> new ArrayList<>());
        for (var v : nums) mods[v % 3].add(v);
        for (var v : mods) v.sort(Integer::compare);

        int ans = 0;
        for (var v : mods) {
            if (v.size() >= 3) {
                ans = Math.max(ans, v.get(v.size() - 1) + v.get(v.size() - 2) + v.get(v.size() - 3));
            }
        }
        if (!mods[0].isEmpty() && !mods[1].isEmpty() && !mods[2].isEmpty()) {
            ans = Math.max(ans, mods[0].get(mods[0].size() - 1) + mods[1].get(mods[1].size() - 1) + mods[2].get(mods[2].size() - 1));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
