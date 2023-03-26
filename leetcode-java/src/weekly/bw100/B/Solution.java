package weekly.bw100.B;

import java.util.TreeMap;

/**
 * 2592. Maximize Greatness of an Array
 *
 * https://leetcode.cn/contest/biweekly-contest-100/problems/maximize-greatness-of-an-array/
 *
 * You are given a 0-indexed integer array nums.
 * You are allowed to permute nums into a new array perm of your choosing.
 *
 * We define the greatness of nums be the number of indices 0 <= i < nums.length for which perm[i] > nums[i].
 *
 * Return the maximum possible greatness you can achieve after permuting nums.
 */

public class Solution {

    public int maximizeGreatness(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        int ans = 0;
        for (int num : nums) {
            Integer k = map.higherKey(num);
            if (k != null) {
                ans++;
                map.merge(k, -1, (a, b) -> a + b == 0 ? null : a + b);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
