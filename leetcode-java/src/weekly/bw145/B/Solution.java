package weekly.bw145.B;

import java.util.List;

/**
 * 3376. Minimum Time to Break Locks I
 *
 * https://leetcode.cn/contest/biweekly-contest-145/problems/minimum-time-to-break-locks-i/
 *
 * Bob is stuck in a dungeon and must break n locks, each requiring some amount of energy to break.
 * The required energy for each lock is stored in an array called strength where strength[i] indicates
 * the energy needed to break the ith lock.
 *
 * To break a lock, Bob uses a sword with the following characteristics:
 *
 * The initial energy of the sword is 0.
 * The initial factor X by which the energy of the sword increases is 1.
 * Every minute, the energy of the sword increases by the current factor X.
 * To break the ith lock, the energy of the sword must reach at least strength[i].
 * After breaking a lock, the energy of the sword resets to 0, and the factor X increases by a given value K.
 * Your task is to determine the minimum time in minutes required for Bob to break all n locks and escape the dungeon.
 *
 * Return the minimum time required for Bob to break all n locks.
 */

public class Solution {

    public int findMinimumTime(List<Integer> strength, int K) {
        return dfs(strength, 0, 0, 1, K);
    }

    private int dfs(List<Integer> strength, int mask, int i, int d, int k) {
        if (i == strength.size()) return 0;

        // 每次必须选择打开一把锁
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < strength.size(); j++) {
            if ((mask & (1 << j)) != 0) continue;

            // 选择打开这把锁 j
            ans = Math.min(ans, (strength.get(j) + d - 1) / d + dfs(strength, mask | (1 << j), i + 1, d + k, k));
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findMinimumTime(List.of(3,4,1), 1) == 4;
        assert new Solution().findMinimumTime(List.of(2,5,4), 2) == 5;
    }

}
