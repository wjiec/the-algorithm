package weekly.w335.C;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 2584. Split the Array to Make Coprime Products
 *
 * https://leetcode.cn/contest/weekly-contest-335/problems/split-the-array-to-make-coprime-products/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * A split at an index i where 0 <= i <= n - 2 is called valid if the
 * product of the first i + 1 elements and the product of the
 * remaining elements are coprime.
 *
 * For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid
 * because 2 and 9 are coprime, while a split at the index i = 1 is not valid
 * because 6 and 3 are not coprime. A split at the index i = 2 is not valid
 * because i == n - 1.
 *
 * Return the smallest index i at which the array can be split validly or -1
 * if there is no such split.
 *
 * Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where
 * gcd(val1, val2) is the greatest common divisor of val1 and val2.
 */

public class Solution {

    public int findValidSplit(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> total = new HashMap<>();
        for (int val : nums) {
            int sqrt = (int) Math.sqrt(val);
            for (int x = 2; x <= sqrt; x++) {
                while (val % x == 0) {
                    val /= x;
                    total.merge(x, 1, Integer::sum);
                }
            }
            if (val != 1) total.merge(val, 1, Integer::sum);
        }

        Map<Integer, Integer> curr = new HashMap<>();
        for (int i = 0; i <= n - 2; i++) {
            int sqrt = (int) Math.sqrt(nums[i]);
            for (int x = 2; x <= sqrt; x++) {
                while (nums[i] % x == 0) {
                    nums[i] /= x;
                    curr.merge(x, 1, Integer::sum);
                }
            }
            if (nums[i] != 1) curr.merge(nums[i], 1, Integer::sum);

            boolean matches = true;
            for (var kv : curr.entrySet()) {
                if (!Objects.equals(kv.getValue(), total.get(kv.getKey()))) {
                    matches = false;
                    break;
                }
            }
            if (matches) return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        assert new Solution().findValidSplit(new int[]{4,7,15,8,3,5}) == -1;
        assert new Solution().findValidSplit(new int[]{4,7,8,15,3,5}) == 2;
    }

}
