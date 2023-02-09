package problem.p514freedomtrail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 514. Freedom Trail
 *
 * https://leetcode.cn/problems/freedom-trail/
 *
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial
 * called the "Freedom Trail Ring" and use the dial to spell a specific keyword to open the door.
 *
 * Given a string ring that represents the code engraved on the outer ring and another string
 * key that represents the keyword that needs to be spelled, return the minimum number of steps
 * to spell all the characters in the keyword.
 *
 * Initially, the first character of the ring is aligned at the "12:00" direction. You should
 * spell all the characters in key one by one by rotating ring clockwise or anticlockwise to
 * make each character of the string key aligned at the "12:00" direction and then by pressing
 * the center button.
 *
 * At the stage of rotating the ring to spell the key character key[i]:
 *
 * You can rotate the ring clockwise or anticlockwise by one place, which counts as one step.
 * The final purpose of the rotation is to align one of ring's characters at the "12:00" direction,
 * where this character must equal key[i].
 *
 * If the character key[i] has been aligned at the "12:00" direction, press the center button to spell,
 * which also counts as one step. After the pressing, you could begin to spell the next character
 * in the key (next stage). Otherwise, you have finished all the spelling.
 */

public class Solution {

    public int findRotateSteps(String ring, String key) {
        List<Integer>[] charIdx = new List[128];
        for (int i = 0; i < charIdx.length; i++)
            charIdx[i] = new ArrayList<>();

        int rl = ring.length(), kl = key.length();
        char[] rChars = ring.toCharArray();
        for (int i = 0; i < rl; i++) charIdx[rChars[i]].add(i);

        // dp[i][j] 表示从前往后 key[i] 与 ring[j] 的步数
        int[][] dp = new int[kl][rl];
        for (var row : dp) Arrays.fill(row, Integer.MAX_VALUE / 2);

        char[] kChars = key.toCharArray();
        for (var idx : charIdx[kChars[0]]) {
            dp[0][idx] = Math.min(idx, rl - idx) + 1;
        }

        for (int i = 1; i < kl; i++) {
            for (int j : charIdx[kChars[i]]) {
                for (int k : charIdx[kChars[i - 1]]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + Math.min(Math.abs(j - k), rl - Math.abs(j - k)) + 1);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < rl; j++) {
            ans = Math.min(ans, dp[kl - 1][j]);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().findRotateSteps("caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx") == 137;

        assert new Solution().findRotateSteps("pqwcx", "cpqwx") == 13;
        assert new Solution().findRotateSteps("abcde", "ade") == 6;

        assert new Solution().findRotateSteps("godding", "gd") == 4;
        assert new Solution().findRotateSteps("godding", "godding") == 13;
    }

}
