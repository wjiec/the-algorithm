package weekly.w351.C;

import java.util.ArrayList;
import java.util.List;

/**
 * 2750. Ways to Split Array Into Good Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-351/problems/ways-to-split-array-into-good-subarrays/
 *
 * You are given a binary array nums.
 *
 * A subarray of an array is good if it contains exactly one element with the value 1.
 *
 * Return an integer denoting the number of ways to split the array nums into good subarrays.
 * As the number may be too large, return it modulo 109 + 7.
 *
 * A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public int numberOfGoodSubarraySplits(int[] nums) {
        List<Integer> index = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) index.add(i);
        }
        if (index.size() == 0) return 0;
        if (index.size() == 1) return 1;

        long ans = 1;
        for (int i = 1; i < index.size(); i++) {
            ans *= index.get(i) - index.get(i - 1);
            ans %= 1_000_000_007;
        }
        return (int) (ans % 1_000_000_007);
    }

    public static void main(String[] args) {
    }

}
