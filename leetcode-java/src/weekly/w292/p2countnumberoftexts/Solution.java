package weekly.w292.p2countnumberoftexts;

/**
 * 6058. Count Number of Texts
 *
 * https://leetcode-cn.com/contest/weekly-contest-292/problems/count-number-of-texts/
 *
 * Alice is texting Bob using her phone. The mapping of digits to letters is shown in the figure below.
 *
 * In order to add a letter, Alice has to press the key of the corresponding digit i times,
 * where i is the position of the letter in the key.
 *
 * For example, to add the letter 's', Alice has to press '7' four times. Similarly,
 * to add the letter 'k', Alice has to press '5' twice.
 *
 * Note that the digits '0' and '1' do not map to any letters, so Alice does not use them.
 * However, due to an error in transmission, Bob did not receive Alice's text message
 * but received a string of pressed keys instead.
 *
 * For example, when Alice sent the message "bob", Bob received the string "2266622".
 * Given a string pressedKeys representing the string received by Bob, return the total
 * number of possible text messages Alice could have sent.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

public class Solution {

    private final int MOD = (int) (1e9 + 7);
    private final int[] count = new int[]{
        0,
        0, 3, 3,
        3, 3, 3,
        4, 3, 4
    };

    public int countTexts(String pressedKeys) {
        char[] keys = pressedKeys.toCharArray();
        int[] dp = new int[pressedKeys.length() + 1]; dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = (dp[i] + dp[i - 1]) % MOD;
            int index = keys[i - 1] - '0';
            for (int j = 1, x = i - 2; j < count[index] && x >= 0; j++, x--) {
                if (keys[x] == keys[i - 1]) {
                    dp[i] = (dp[i] + dp[x]) % MOD;
                } else break;
            }
        }

        return dp[pressedKeys.length()];
    }

    public static void main(String[] args) {
        assert new Solution().countTexts("344644885") == 8;
        assert new Solution().countTexts("444479999555588866") == 3136;

        // 2
        assert new Solution().countTexts("2") == 1;
        // 2 2
        // 22
        assert new Solution().countTexts("22") == 2;
        // 2 2 2
        // 22 2
        // 2 22
        // 222
        assert new Solution().countTexts("222") == 4;
        // 2 2 2 3
        // 22 2 3
        // 2 22 3
        // 222 3
        assert new Solution().countTexts("2223") == 4;
        // 2 2 2 3 3
        // 22 2 3 3
        // 2 22 3 3
        // 222 3
        // 2 2 2 33
        // 22 2 33
        // 2 22 33
        // 222 33
        assert new Solution().countTexts("22233") == 8;
        assert new Solution().countTexts("222222222222222222222222222222222222") == 82876089;
    }

}
