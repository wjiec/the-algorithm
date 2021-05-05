package daily.d210505p740deleteandearn;

import java.util.HashMap;
import java.util.Map;

/**
 * 740. Delete and Earn
 *
 * https://leetcode-cn.com/problems/delete-and-earn/
 *
 * Given an array nums of integers, you can perform operations on the array.
 *
 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After,
 * you must delete every element equal to nums[i] - 1 or nums[i] + 1.
 *
 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
 */

public class Solution {

    // @TODO
    public int deleteAndEarn(int[] nums) {
        int max = 0;
        Map<Integer, Integer> ns = new HashMap<>();
        for (int num : nums) {
            max = Math.max(max, num);
            ns.merge(num, num, Integer::sum);
        }

        int l = ns.getOrDefault(0, 0), r = Math.max(ns.getOrDefault(0, 0), ns.getOrDefault(1, 0));
        for (int i = 2; i <= max; i++) {
            int t = r;
            r = Math.max(l + ns.getOrDefault(i, 0), r);
            l = t;
        }

        return r;
    }

    public static void main(String[] args) {
        assert new Solution().deleteAndEarn(new int[]{3,4,2}) == 6;
        assert new Solution().deleteAndEarn(new int[]{2,2,3,3,3,4}) == 9;
        assert new Solution().deleteAndEarn(new int[]{2,2,2,2,2,2,2,2,2,3,3,3,4}) == 22;
    }

}
