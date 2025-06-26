package weekly.bw158.D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4. Maximum Good Subtree Score
 *
 * https://leetcode.cn/contest/biweekly-contest-158/problems/maximum-good-subtree-score/
 *
 * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1.
 * Each node i has an integer value vals[i], and its parent is given by par[i].
 *
 * A subset of nodes within the subtree of a node is called good if every digit from 0 to 9 appears
 * at most once in the decimal representation of the values of the selected nodes.
 *
 * The score of a good subset is the sum of the values of its nodes.
 *
 * Define an array maxScore of length n, where maxScore[u] represents the maximum possible
 * sum of values of a good subset of nodes that belong to the subtree rooted at node u,
 * including u itself and all its descendants.
 *
 * Return the sum of all values in maxScore.
 *
 * Since the answer may be large, return it modulo 1e9 + 7.
 */

public class Solution {

    private static final int MASK = 1 << 10;
    private static final int MOD = 1_000_000_007;

    /** @noinspection unchecked*/
    private final List<Integer>[] g = new List[501];
    { Arrays.setAll(g, i -> new ArrayList<>()); }

    public int goodSubtreeSum(int[] vals, int[] par) {
        for (int i = 1; i < par.length; i++) g[par[i]].add(i);

        int ans = 0;
        for (var v : dfs(0, vals)) ans = (ans + v) % MOD;
        System.out.println(ans);
        return ans;
    }

    private int[] dfs(int curr, int[] vals) {
        int[] ans = new int[MASK];

        int digitSet = 0;
        boolean[] seen = new boolean[10];
        for (int v = vals[curr]; v != 0; v /= 10) {
            int digit = v % 10;
            if (seen[digit]) { digitSet = -1; break; }
            seen[digit] = true; digitSet |= 1 << (digit);
        }
        if (digitSet > 0) {
            ans[digitSet] = vals[curr];
        }

        for (var next : g[curr]) {
            int[] all = dfs(next, vals);
            for (int i = 0; i < MASK; i++) {
                ans[i] = (ans[i] + all[i]) % MOD;
            }

            // 此时可以进行组合
            if (digitSet > 0) {
                for (int j = 1; j < MASK; j++) {
                    if ((digitSet & j) == 0) {
                        ans[digitSet] = (ans[digitSet] + all[j]) % MOD;
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().goodSubtreeSum(new int[]{2, 3}, new int[]{-1, 0}) == 8;
        assert new Solution().goodSubtreeSum(new int[]{1, 5, 2}, new int[]{-1, 0, 0}) == 15;
        assert new Solution().goodSubtreeSum(new int[]{34, 1, 2}, new int[]{-1, 0, 1}) == 42;
        assert new Solution().goodSubtreeSum(new int[]{3, 22, 5}, new int[]{-1, 0, 1}) == 18;
    }

}
