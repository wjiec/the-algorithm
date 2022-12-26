package problemv2.p6273maximumenemyfortsthatcanbecaptured;

/**
 * 6273. Maximum Enemy Forts That Can Be Captured
 *
 * https://leetcode.cn/problems/maximum-enemy-forts-that-can-be-captured/
 *
 * You are given a 0-indexed integer array forts of length n representing
 * the positions of several forts. forts[i] can be -1, 0, or 1 where:
 *
 * -1 represents there is no fort at the ith position.
 * 0 indicates there is an enemy fort at the ith position.
 * 1 indicates the fort at the ith the position is under your command.
 * Now you have decided to move your army from one of your forts at position i
 * to an empty position j such that:
 *
 * 0 <= i, j <= n - 1
 * The army travels over enemy forts only. Formally, for all k
 * where min(i,j) < k < max(i,j), forts[k] == 0.
 *
 * While moving the army, all the enemy forts that come in the way are captured.
 *
 * Return the maximum number of enemy forts that can be captured. In case it is impossible
 * to move your army, or you do not have any fort under your command, return 0.
 */

public class Solution {

    public int captureForts(int[] forts) {
        int ans = 0, prevUser = -1, prevBlank = -1;
        for (int i = 0; i < forts.length; i++) {
            switch (forts[i]) {
                case 1 -> {
                    if (prevBlank != -1) {
                        ans = Math.max(ans, i - prevBlank - 1);
                    }
                    prevUser = i; prevBlank = -1;
                }
                case -1 -> {
                    if (prevUser != -1) {
                        ans = Math.max(ans, i - prevUser - 1);
                    }
                    prevBlank = i; prevUser = -1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().captureForts(new int[]{1,0,0,-1,0,0,-1,0,0,1}) == 2;

        assert new Solution().captureForts(new int[]{1,0,0,-1,0,0,0,0,1}) == 4;
        assert new Solution().captureForts(new int[]{0,0,1,-1}) == 0;
    }

}
