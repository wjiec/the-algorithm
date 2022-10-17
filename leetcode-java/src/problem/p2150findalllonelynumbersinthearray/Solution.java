package problem.p2150findalllonelynumbersinthearray;

import common.Checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2150. Find All Lonely Numbers in the Array
 *
 * https://leetcode.cn/problems/find-all-lonely-numbers-in-the-array/
 *
 * You are given an integer array nums. A number x is lonely when it appears
 * only once, and no adjacent numbers (i.e. x + 1 and x - 1) appear in the array.
 *
 * Return all lonely numbers in nums. You may return the answer in any order.
 */

public class Solution {

    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) map.merge(v, 1, Integer::sum);

        List<Integer> ans = new ArrayList<>();
        for (int v : nums) {
            if (map.get(v) == 1 && !map.containsKey(v - 1) && !map.containsKey(v + 1)) {
                ans.add(v);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findLonely(new int[]{10,6,5,8}), List.of(10,8));
        assert Checker.anyOrder(new Solution().findLonely(new int[]{1,3,5,3}), List.of(1,5));
    }

}
