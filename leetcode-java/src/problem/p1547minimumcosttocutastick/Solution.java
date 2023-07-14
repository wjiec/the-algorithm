package problem.p1547minimumcosttocutastick;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 1547. Minimum Cost to Cut a Stick
 *
 * https://leetcode.cn/problems/minimum-cost-to-cut-a-stick/
 *
 * Given a wooden stick of length n units. The stick is labelled from 0 to n.
 * For example, a stick of length 6 is labelled as follows:
 *
 * Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.
 *
 * You should perform the cuts in order, you can change the order of the cuts as you wish.
 *
 * The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts.
 * When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the
 * length of the stick before the cut). Please refer to the first example for a better explanation.
 *
 * Return the minimum total cost of the cuts.
 */

public class Solution {

    public int minCost(int n, int[] cuts) {
        TreeSet<Integer> s = new TreeSet<>();
        for (var v : cuts) s.add(v);
        return dfs(0, n, s);
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    private int dfs(int l, int r, TreeSet<Integer> s) {
        long key = ((long) l << 32) | r;
        if (!memo.containsKey(key)) {
            int ans = Integer.MAX_VALUE;
            Integer cut = s.higher(l);
            while (cut != null && cut < r) {
                ans = Math.min(ans, r - l + dfs(l, cut, s) + dfs(cut, r, s));
                cut = s.higher(cut);
            }
            memo.put(key, ans == Integer.MAX_VALUE ? 0 : ans);
        }
        return memo.get(key);
    }

    private static class DP {
        public int minCost(int n, int[] cuts) {
            Arrays.sort(cuts);
            int m = cuts.length;
            int[] cts = new int[m + 2];
            System.arraycopy(cuts, 0, cts, 1, m);
            cts[m + 1] = n;

            int[][] dp = new int[m + 2][m + 2];
            for (int l = m; l >= 1; --l) {
                for (int r = l; r <= m; ++r) {
                    dp[l][r] = l == r ? 0 : Integer.MAX_VALUE;
                    for (int cur = l; cur <= r; ++cur) {
                        dp[l][r] = Math.min(dp[l][r], dp[l][cur - 1] + dp[cur + 1][r]);
                    }
                    dp[l][r] += cts[r + 1] - cts[l - 1];
                }
            }
            return dp[1][m];
        }
    }

    public static void main(String[] args) {
    }

}
