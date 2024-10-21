package weekly.w419.C;

/**
 * 3320. Count The Number of Winning Sequences
 *
 * https://leetcode.cn/contest/weekly-contest-419/problems/count-the-number-of-winning-sequences/
 *
 * Alice and Bob are playing a fantasy battle game consisting of n rounds where they
 * summon one of three magical creatures each round: a Fire Dragon, a Water Serpent,
 * or an Earth Golem. In each round, players simultaneously summon their creature
 * and are awarded points as follows:
 *
 * If one player summons a Fire Dragon and the other summons an Earth Golem, the player
 * who summoned the Fire Dragon is awarded a point.
 *
 * If one player summons a Water Serpent and the other summons a Fire Dragon, the player
 * who summoned the Water Serpent is awarded a point.
 *
 * If one player summons an Earth Golem and the other summons a Water Serpent, the player
 * who summoned the Earth Golem is awarded a point.
 *
 * If both players summon the same creature, no player is awarded a point.
 * You are given a string s consisting of n characters 'F', 'W', and 'E', representing
 * the sequence of creatures Alice will summon in each round:
 *
 * If s[i] == 'F', Alice summons a Fire Dragon.
 * If s[i] == 'W', Alice summons a Water Serpent.
 * If s[i] == 'E', Alice summons an Earth Golem.
 *
 * Bob’s sequence of moves is unknown, but it is guaranteed that Bob will never summon
 * the same creature in two consecutive rounds. Bob beats Alice if the total number of
 * points awarded to Bob after n rounds is strictly greater than the points awarded to Alice.
 *
 * Return the number of distinct sequences Bob can use to beat Alice.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final int Fi = 0, Ei = 1, Wi = 2;
    private final char[] creatures = new char[]{'F', 'E', 'W'};
    private int toi(char c) { return c == 'F' ? Fi : (c == 'E' ? Ei : Wi); }

    // F > E, E > W, W > F
    public int countWinningSequences(String s) {
        final int MOD = 1_000_000_007;
        final int n = s.length(), n2 = n * 2 + 1;

        // dp[i][j] 表示在当前这一轮出 i 时得分为 j 的方案数
        int[][] dp = new int[3][n2];

        // 初始化第一轮的值
        char[] chars = s.toCharArray();
        for (var bob : creatures) {
            int score = chars[0] == bob ? 0 : (win(bob, chars[0]) ? 1 : -1);
            dp[toi(bob)][n + score] = 1;
        }

        // 枚举之后每一轮 alice 的出牌
        for (int i = 1; i < chars.length; i++) {
            char alice = chars[i];

            int[][] curr = new int[3][n2];

            // 枚举上一轮 bob 出的牌, 可以是 F, E, W 中的一个
            for (var prev : creatures) {
                // 枚举本轮 bob 出的牌, 可以是 F, E, W 中的一个, 但是不能与上一轮的相同
                for (var bob : creatures) {
                    if (prev == bob) continue;
                    int score = alice == bob ? 0 : (win(bob, alice) ? 1 : -1);

                    // 枚举上一轮的所有可能得分, 并计算本轮得分
                    for (int j = 0; j < n2; j++) {
                        if (j + score < 0 || j + score >= n2) continue;

                        // 由于bob不能两次出相同的牌
                        curr[toi(bob)][j + score] = (curr[toi(bob)][j + score] + dp[toi(prev)][j]) % MOD;
                    }
                }
            }

            dp = curr;
        }

        int ans = 0;
        for (var row : dp) for (int j = n + 1; j < n2; j++) ans = (ans + row[j]) % MOD;
        return ans;
    }

    private boolean win(char bob, char alice) {
        return (bob == 'F' && alice == 'E') || (bob == 'W' && alice == 'F') || (bob == 'E' && alice == 'W');
    }

    public static void main(String[] args) {
        assert new Solution().countWinningSequences("EEFWEWWFFFWEEWEFFEEEFEFEEFWFEWEFEWWWFFEEEFFFE") == 197576205;

        assert new Solution().countWinningSequences("FFF") == 3;
        assert new Solution().countWinningSequences("FWEFW") == 18;
    }

}
