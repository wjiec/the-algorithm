package weekly.bw81.D;

import java.util.HashMap;
import java.util.Map;

/**
 * 6107. Number of Distinct Roll Sequences
 *
 * https://leetcode.cn/contest/biweekly-contest-81/problems/number-of-distinct-roll-sequences/
 *
 * You are given an integer n. You roll a fair 6-sided dice n times. Determine the total number of distinct
 * sequences of rolls possible such that the following conditions are satisfied:
 *
 * The greatest common divisor of any adjacent values in the sequence is equal to 1.
 * There is at least a gap of 2 rolls between equal valued rolls. More formally, if the value of the ith
 * roll is equal to the value of the jth roll, then abs(i - j) > 2.
 *
 * Return the total number of distinct sequences possible. Since the answer may be very
 * large, return it modulo 109 + 7.
 *
 * Two sequences are considered distinct if at least one element is different.
 */

public class Solution {

    public int distinctSequences(int n) {
        this.n = n;
        return (int) dfs(0, 7, 7);
    }

    private int n;
    private final Map<Integer, Long> map = new HashMap<>();

    private long dfs(int i, int p, int pp) {
        if (i == n) return 1;
        int k = (i << 16) | (p << 8) | pp;
        if (map.containsKey(k)) return map.get(k);

        long ans = 0;
        for (int x = 1; x <= 6; x++) {
            if (x == p || x == pp) continue;
            if (x % 2 == 0 && p % 2 == 0) continue;
            if (x % 3 == 0 && p % 3 == 0) continue;
            ans += dfs(i + 1, x, p);
        }

        ans = ans % 1_000_000_007;
        map.put(k, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().distinctSequences(1) == 6;
        assert new Solution().distinctSequences(2) == 22;
        assert new Solution().distinctSequences(4) == 184;
    }

}
