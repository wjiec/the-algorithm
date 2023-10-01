package weekly.bw114.A;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 8038. Minimum Operations to Collect Elements
 *
 * https://leetcode.cn/contest/biweekly-contest-114/problems/minimum-operations-to-collect-elements/
 *
 * You are given an array nums of positive integers and an integer k.
 *
 * In one operation, you can remove the last element of the array and add it to your collection.
 *
 * Return the minimum number of operations needed to collect elements 1, 2, ..., k.
 */

public class Solution {

    public int minOperations(List<Integer> nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 1; i <= k; i++) set.add(i);

        int n = nums.size();
        for (int i = 1; i <= n; i++) {
            if (set.remove(nums.get(n - i))) {
                if (set.isEmpty()) return i;
            }
        }
        return -1; // unreached
    }

    public static void main(String[] args) {
    }

}
