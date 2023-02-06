package weekly.bw97.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 2554. Maximum Number of Integers to Choose From a Range I
 *
 * https://leetcode.cn/problems/maximum-number-of-integers-to-choose-from-a-range-i/
 *
 * You are given an integer array banned and two integers n and maxSum.
 *
 * You are choosing some number of integers following the below rules:
 *
 * The chosen integers have to be in the range [1, n].
 * Each integer can be chosen at most once.
 * The chosen integers should not be in the array banned.
 * The sum of the chosen integers should not exceed maxSum.
 *
 * Return the maximum number of integers you can choose following the mentioned rules.
 */

public class Solution {

    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bans = new HashSet<>();
        for (var v : banned) bans.add(v);

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (bans.contains(i)) continue;
            if (maxSum - i < 0) break;

            maxSum -= i; ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
