package problem.p1296dividearrayinsetsofkconsecutivenumbers;

import java.util.TreeMap;

/**
 * 1296. Divide Array in Sets of K Consecutive Numbers
 *
 * https://leetcode.cn/problems/divide-array-in-sets-of-k-consecutive-numbers/
 *
 * Given an array of integers nums and a positive integer k, check whether
 * it is possible to divide this array into sets of k consecutive numbers.
 *
 * Return true if it is possible. Otherwise, return false.
 */

public class Solution {

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        while (!map.isEmpty()) {
            int key = map.firstKey();
            int cnt = map.get(key);
            for (int i = 0; i < k; i++) {
                int nc = map.getOrDefault(key + i, 0);
                if (nc > cnt) map.put(key + i, nc - cnt);
                else if (nc == cnt) map.remove(key + i);
                else return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isPossibleDivide(new int[]{1,2,3,3,4,4,5,6}, 4);
        assert new Solution().isPossibleDivide(new int[]{3,2,1,2,3,4,3,4,5,9,10,11}, 3);
        assert new Solution().isPossibleDivide(new int[]{3,3,2,2,1,1}, 3);
        assert !new Solution().isPossibleDivide(new int[]{1,2,3,4}, 3);
    }

}
