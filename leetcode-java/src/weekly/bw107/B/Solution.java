package weekly.bw107.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 2745. Construct the Longest New String
 *
 * https://leetcode.cn/contest/biweekly-contest-107/problems/construct-the-longest-new-string/
 *
 * You are given three integers x, y, and z.
 *
 * You have x strings equal to "AA", y strings equal to "BB", and z strings equal to "AB".
 * You want to choose some (possibly all or none) of these strings and concactenate them
 * in some order to form a new string. This new string must not contain "AAA" or "BBB" as a substring.
 *
 * Return the maximum possible length of the new string.
 *
 * A substring is a contiguous non-empty sequence of characters within a string.
 */

public class Solution {

    public int longestString(int x, int y, int z) {
        return longestString(x, y, z, -1, 0);
    }

    private final Map<Integer, Integer> memo = new HashMap<>();

    private int longestString(int x, int y, int z, int p, int s) {
        int key = (x << 24) | (y << 16) | (z << 8) | p;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = s;
        if (x > 0 && (p == -1 || p == 1 || p == 2))
            ans = Math.max(ans, longestString(x - 1, y, z, 0, s + 2));

        if (y > 0 && (p == -1 || p == 0))
            ans = Math.max(ans, longestString(x, y - 1, z, 1, s + 2));

        if (z > 0 && (p == -1 || p == 1 || p == 2))
            ans = Math.max(ans, longestString(x, y, z - 1, 2, s + 2));

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
    }

}
