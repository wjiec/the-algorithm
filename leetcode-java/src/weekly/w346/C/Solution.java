package weekly.w346.C;

import java.util.HashSet;
import java.util.Set;

/**
 * 6441. Find the Punishment Number of an Integer
 *
 * https://leetcode.cn/contest/weekly-contest-346/problems/find-the-punishment-number-of-an-integer/
 *
 * Given a positive integer n, return the punishment number of n.
 *
 * The punishment number of n is defined as the sum of the squares of all integers i such that:
 *
 * 1 <= i <= n
 * The decimal representation of i * i can be partitioned into contiguous substrings such that
 * the sum of the integer values of these substrings equals i.
 */

public class Solution {

    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (split(i * i).contains(i)) {
                ans += i * i;
            }
        }
        return ans;
    }

    private final Set<Integer>[] memo = new Set[1_000_001];

    private Set<Integer> split(int i) {
        if (memo[i] == null) {
            memo[i] = new HashSet<>();
            memo[i].add(i);

            if (i >= 10) {
                for (int x = 10; x <= i; x *= 10) {
                    int a = i / x, b = i % x;
                    for (var m : split(a)) {
                        for (var n : split(b)) {
                            if (m + n <= 1000) {
                                memo[i].add(m + n);
                            }
                        }
                    }
                }
            }
        }
        return memo[i];
    }

    public static void main(String[] args) {
        System.out.println(new Solution().split(100));
    }

}
