package weekly.w382.B;

import java.util.*;

/**
 * 3020. Find the Maximum Number of Elements in Subset
 *
 * https://leetcode.cn/contest/weekly-contest-382/problems/find-the-maximum-number-of-elements-in-subset/
 *
 * You are given an array of positive integers nums.
 *
 * You need to select a subset of nums which satisfies the following condition:
 *
 * You can place the selected elements in a 0-indexed array such that it follows the
 * pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be any
 * non-negative power of 2).
 *
 * For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
 *
 * Return the maximum number of elements in a subset that satisfies these conditions.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    // 1 2 4 8 16
    public int maximumLength(int[] nums) {
        TreeSet<Long> set = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (var v : nums) {
            set.add((long) v);
            map.merge(v, 1, Integer::sum);
        }

        int ans = 1;
        if (map.containsKey(1)) {
            int c = map.get(1);
            if (c % 2 == 1) ans = c;
            else ans = c - 1;
        }

        Set<Long> seen = new HashSet<>();
        for (var x : set) {
            if (x == 1) continue;
            if (seen.contains(x)) continue;

            int curr = 0;
            for (int i = 0; true; i++) {
                int v = (int) pow(x, 1L << i);
                int c = map.getOrDefault(v, 0);
                if (c == 0) break;

                seen.add(x);
                if (c == 1) { curr += c; break; }
                curr += 2;
            }
            ans = Math.max(ans, curr);
        }
        return ans % 2 == 0 ? (ans - 1) : ans;
    }

    public static long pow(long base, long pow) {
        long ans = 1;
        while (pow > 0 && pow < 1e10) {
            if ((pow & 1L) != 0) {
                ans = ans * base;
            }

            base = base * base;
            pow >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumLength(new int[]{1, 1}) == 1;
    }

}
