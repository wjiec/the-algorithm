package weekly.bw114.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 100032. Minimum Number of Operations to Make Array Empty
 *
 * https://leetcode.cn/contest/biweekly-contest-114/problems/minimum-number-of-operations-to-make-array-empty/
 *
 * You are given a 0-indexed array nums consisting of positive integers.
 *
 * There are two types of operations that you can apply on the array any number of times:
 *
 * Choose two elements with equal values and delete them from the array.
 * Choose three elements with equal values and delete them from the array.
 * Return the minimum number of operations required to make the array empty, or -1 if it is not possible.
 */

public class Solution {

    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        int ans = 0;
        for (var v : map.values()) {
            if (v == 1) return -1;
            switch (v % 3) {
                case 0 -> ans += v / 3;
                case 2 -> ans += v / 3 + 1;
                case 1 -> ans += v / 3 - 1 + 2;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
