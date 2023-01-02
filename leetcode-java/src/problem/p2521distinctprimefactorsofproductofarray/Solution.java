package problem.p2521distinctprimefactorsofproductofarray;

import java.util.HashSet;
import java.util.Set;

/**
 * 2521. Distinct Prime Factors of Product of Array
 *
 * https://leetcode.cn/problems/distinct-prime-factors-of-product-of-array/
 *
 * Given an array of positive integers nums, return the number of distinct prime
 * factors in the product of the elements of nums.
 *
 * Note that:
 *
 * A number greater than 1 is called prime if it is divisible by only 1 and itself.
 * An integer val1 is a factor of another integer val2 if val2 / val1 is an integer.
 */

public class Solution {

    public int distinctPrimeFactors(int[] nums) {
        Set<Integer>[] map = new Set[1001];
        Set<Integer> primes = filters(1001);
        for (int i = 2; i < map.length; i++) {
            map[i] = new HashSet<>();

            int sqrtI = (int) Math.sqrt(i);
            for (int j = 2; j <= sqrtI; j++) {
                if (i % j == 0) {
                    if (primes.contains(j)) map[i].add(j);
                    if (primes.contains(i / j)) map[i].add(i / j);
                }
            }
        }

        Set<Integer> ans = new HashSet<>();
        for (var v : nums) {
            ans.addAll(map[v]);
            if (primes.contains(v)) ans.add(v);
        }
        return ans.size();
    }

    private Set<Integer> filters(int n) {
        Set<Integer> ans = new HashSet<>(); ans.add(2);

        boolean[] filtered = new boolean[n / 2]; // 2 * i + 1
        for (int i = 1; i < filtered.length; i++) {
            if (filtered[i]) continue;

            int curr = 2 * i + 1; ans.add(curr);
            for (int j = 2; j * curr < n; j++) {
                if ((curr * j) % 2 == 0) continue;
                filtered[((curr * j) - 1) / 2] = true;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().distinctPrimeFactors(new int[]{2,4,3,7,10,6}) == 4;
        assert new Solution().distinctPrimeFactors(new int[]{2,4,8,16}) == 1;
    }

}
