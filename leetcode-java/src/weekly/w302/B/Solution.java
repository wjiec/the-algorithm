package weekly.w302.B;

import java.util.*;

/**
 * 6164. Max Sum of a Pair With Equal Sum of Digits
 *
 * https://leetcode.cn/contest/weekly-contest-302/problems/max-sum-of-a-pair-with-equal-sum-of-digits/
 *
 * You are given a 0-indexed array nums consisting of positive integers. You can choose two
 * indices i and j, such that i != j, and the sum of digits of the number
 * nums[i] is equal to that of nums[j].
 *
 * Return the maximum value of nums[i] + nums[j] that you can obtain over all possible
 * indices i and j that satisfy the conditions.
 */

public class Solution {

    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (var n : nums)
            map.computeIfAbsent(bitSum(n), v -> new ArrayList<>())
                .add(n);

        int ans = -1;
        for (var list : map.values()) {
            if (list.size() >= 2) {
                Collections.sort(list);
                ans = Math.max(ans, list.get(list.size() - 1) + list.get(list.size() - 2));
            }
        }
        return ans;
    }

    private int bitSum(int n) {
        int sum = 0;
        for (; n != 0; n /= 10) {
            sum += n % 10;
        }
        return sum;
    }

    public static void main(String[] args) {
    }

}
