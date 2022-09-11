package weekly.w310.A;

import java.util.TreeMap;

/**
 * 6176. Most Frequent Even Element
 *
 * https://leetcode.cn/contest/weekly-contest-310/problems/most-frequent-even-element/
 *
 * Given an integer array nums, return the most frequent even element.
 *
 * If there is a tie, return the smallest one. If there is no such element, return -1.
 */

public class Solution {

    public int mostFrequentEven(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var n : nums) {
            if (n % 2 == 0) {
                map.merge(n, 1, Integer::sum);
            }
        }

        int ans = -1, count = 0;
        for (var kv : map.entrySet()) {
            if (kv.getValue() > count) {
                ans = kv.getKey();
                count = kv.getValue();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
