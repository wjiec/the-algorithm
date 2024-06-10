package problem.p1916countwaystobuildroomsinanantcolony;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1916. Count Ways to Build Rooms in an Ant Colony
 *
 * https://leetcode.cn/problems/count-ways-to-build-rooms-in-an-ant-colony
 *
 * You are an ant tasked with adding n new rooms numbered 0 to n-1 to your colony.
 * You are given the expansion plan as a 0-indexed integer array of length n, prevRoom,
 * where prevRoom[i] indicates that you must build room prevRoom[i] before building room i,
 * and these two rooms must be connected directly. Room 0 is already built, so prevRoom[0] = -1.
 *
 * The expansion plan is given such that once all the rooms are built, every room will be reachable from room 0.
 *
 * You can only build one room at a time, and you can travel freely between rooms you have already
 * built only if they are connected. You can choose to build any room as long as its previous
 * room is already built.
 *
 * Return the number of different orders you can build all the rooms in. Since the answer
 * may be large, return it modulo 10^9 + 7.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private long[] fac = null;
    private long[] inv = null;
    private final int MOD = 1_000_000_007;
    private final Map<Integer, List<Integer>> g = new HashMap<>();

    public int waysToBuildRooms(int[] prevRoom) {
        fac = new long[prevRoom.length];
        inv = new long[prevRoom.length];

        fac[0] = inv[0] = 1;
        for (int i = 1; i < prevRoom.length; i++) {
            fac[i] = (fac[i - 1] * i) % MOD;
            inv[i] = pow(fac[i], MOD - 2, MOD);
            g.computeIfAbsent(prevRoom[i], k -> new ArrayList<>()).add(i);
        }

        dp = new long[prevRoom.length];
        cnt = new int[prevRoom.length];
        dfs(0);

        return (int) (dp[0]);
    }

    private long[] dp = null;
    private int[] cnt = null;

    private void dfs(int curr) {
        dp[curr] = 1;
        if (g.containsKey(curr)) {
            for (var next : g.get(curr)) {
                dfs(next);

                dp[curr] = (((dp[curr] * dp[next]) % MOD) * inv[cnt[next]]) % MOD;
                cnt[curr] += cnt[next];
            }
        }
        dp[curr] = (dp[curr] * fac[cnt[curr]]) % MOD;
        ++cnt[curr];
    }

    // 快速幂算法, 求 (base ^ pow) % mod 的值
    public static long pow(long base, long pow, long mod) {
        long ans = 1;
        while (pow > 0) {
            if ((pow & 1L) != 0) {
                ans = (ans * base) % mod;
            }

            base = (base * base) % mod;
            pow >>= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
