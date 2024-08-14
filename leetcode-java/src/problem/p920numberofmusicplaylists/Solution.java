package problem.p920numberofmusicplaylists;

/**
 * 920. Number of Music Playlists
 *
 * https://leetcode.cn/problems/number-of-music-playlists/description/
 *
 * Your music player contains n different songs. You want to listen to goal
 * songs (not necessarily different) during your trip.
 *
 * To avoid boredom, you will create a playlist so that:
 *
 * Every song is played at least once.
 * A song can only be played again only if k other songs have been played.
 * Given n, goal, and k, return the number of possible playlists that you can create.
 *
 * Since the answer can be very large, return it modulo 109 + 7.
 */

public class Solution {

    private static final long MOD = 1_000_000_007;

    // 0 <= k < n <= goal <= 100
    public int numMusicPlaylists(int n, int goal, int k) {
        final long MOD = 1_000_000_007;

        // dp[i][j] 表示使用 j 首不同的歌播放 i 次的方案数
        long[][] dp = new long[goal + 1][n + 1]; dp[0][0] = 1;
        for (int i = 1; i <= goal; i++) {
            for (int j = 1; j <= n; j++) {
                // 如果最后这首歌没有播放过, 那么可以选择 n - j + 1 首歌
                dp[i][j] += dp[i - 1][j - 1] * (n - j + 1);
                // 如果最后这首歌播放过, 那就只能选择 j - k 首歌
                if (j > k) dp[i][j] += dp[i - 1][j] * (j - k);

                dp[i][j] %= MOD;
            }
        }

        return (int) dp[goal][n];
    }

    public static void main(String[] args) {
        assert new Solution().numMusicPlaylists(55, 99, 1) == 884400356;

        assert new Solution().numMusicPlaylists(3, 3, 1) == 6;
        assert new Solution().numMusicPlaylists(2, 3, 0) == 6;
        assert new Solution().numMusicPlaylists(2, 3, 1) == 2;
    }

}
