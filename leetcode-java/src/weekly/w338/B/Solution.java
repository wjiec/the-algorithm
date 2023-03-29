package weekly.w338.B;

import ability.Array;
import ability.Prime;

/**
 * 2601. Prime Subtraction Operation
 *
 * https://leetcode.cn/contest/weekly-contest-338/problems/prime-subtraction-operation/
 *
 * You are given a 0-indexed integer array nums of length n.
 *
 * You can perform the following operation as many times as you want:
 *
 * Pick an index i that you haven’t picked before, and pick a prime p strictly
 * less than nums[i], then subtract p from nums[i].
 *
 * Return true if you can make nums a strictly increasing array using the above
 * operation and false otherwise.
 *
 * A strictly increasing array is an array whose each element is strictly
 * greater than its preceding element.
 */

public class Solution {

    public boolean primeSubOperation(int[] nums) {
        int[] groups = Prime.euler(1000);
        int[] primes = new int[groups[0]];
        System.arraycopy(groups, 1, primes, 0, primes.length);

        for (int i = 0; i < nums.length; i++) {
            int prev = i == 0 ? 0 : nums[i - 1];
            int idx = Array.lower(primes, nums[i] - prev);
            if (idx != -1) nums[i] -= primes[idx];
            if (i != 0 && nums[i] <= nums[i - 1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().primeSubOperation(new int[]{998,2});

        assert new Solution().primeSubOperation(new int[]{4,9,6,10});
        assert new Solution().primeSubOperation(new int[]{6,8,11,12});
        assert !new Solution().primeSubOperation(new int[]{5,8,3});
    }

}
