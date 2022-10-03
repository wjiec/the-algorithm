package problem.p1997firstdaywhereyouhavebeeninalltherooms;

/**
 * 1997. First Day Where You Have Been in All the Rooms
 *
 * https://leetcode.cn/problems/first-day-where-you-have-been-in-all-the-rooms/
 *
 * There are n rooms you need to visit, labeled from 0 to n - 1.
 * Each day is labeled, starting from 0. You will go in and visit one room a day.
 *
 * Initially on day 0, you visit room 0. The order you visit the rooms for the coming
 * days is determined by the following rules and a given 0-indexed array nextVisit of length n:
 *
 * Assuming that on a day, you visit room i,
 * if you have been in room i an odd number of times (including the current visit), on the
 * next day you will visit a room with a lower or equal room number specified by nextVisit[i]
 * where 0 <= nextVisit[i] <= i;
 *
 * if you have been in room i an even number of times (including the current visit), on the
 * next day you will visit room (i + 1) mod n.
 *
 * Return the label of the first day where you have been in all the rooms.
 * It can be shown that such a day exists. Since the answer may be very large, return
 * it modulo 109 + 7.
 */

public class Solution {

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        final int MOD = 1_000_000_007;
        int[] dp = new int[nextVisit.length];
        for (int i = 0; i < nextVisit.length - 1; i++) {
            int x = nextVisit[i];
            int v = (2 + dp[i] - dp[x] + MOD) % MOD;
            dp[i + 1] = (dp[i] + v) % MOD;
        }
        return dp[nextVisit.length - 1];
    }

    public static void main(String[] args) {
        assert new Solution().firstDayBeenInAllRooms(new int[]{0,0}) == 2;
        assert new Solution().firstDayBeenInAllRooms(new int[]{0,0,2}) == 6;
        assert new Solution().firstDayBeenInAllRooms(new int[]{0,1,2,0}) == 6;
    }

}
