package weekly.w380.A;

import java.util.HashMap;
import java.util.Map;

/**
 * 3005. Count Elements With Maximum Frequency
 *
 * https://leetcode.cn/contest/weekly-contest-380/problems/count-elements-with-maximum-frequency/
 *
 * You are given an array nums consisting of positive integers.
 *
 * Return the total frequencies of elements in nums such that those elements all have the maximum frequency.
 *
 * The frequency of an element is the number of occurrences of that element in the array.
 */

public class Solution {

    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        int ans = 0, curr = 0;
        for (var kv : map.entrySet()) {
            if (kv.getValue() > curr) {
                ans = curr = kv.getValue();
            } else if (kv.getValue() == curr) {
                ans += curr;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
