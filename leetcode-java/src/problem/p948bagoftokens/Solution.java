package problem.p948bagoftokens;

import java.util.Arrays;

/**
 * 948. Bag of Tokens
 *
 * https://leetcode.cn/problems/bag-of-tokens/
 *
 * You have an initial power of power, an initial score of 0, and a bag of tokens
 * where tokens[i] is the value of the ith token (0-indexed).
 *
 * Your goal is to maximize your total score by potentially playing each token in one of two ways:
 *
 * If your current power is at least tokens[i], you may play the ith token face up,
 * losing tokens[i] power and gaining 1 score.
 *
 * If your current score is at least 1, you may play the ith token face down,
 * gaining tokens[i] power and losing 1 score.
 *
 * Each token may be played at most once and in any order. You do not have to play all the tokens.
 *
 * Return the largest possible score you can achieve after playing any number of tokens.
 */

public class Solution {

    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int l = 0, r = tokens.length - 1, score = 0, ans = 0;
        while (l <= r && (power >= tokens[l] || score > 0)) {
            // 尽可能多的兑换小的token
            while (l <= r && power >= tokens[l]) {
                power -= tokens[l++];
                score++;
            }

            if (score > ans) ans = score;
            // 如果power不足, 则从最大的开始兑换
            if (l <= r && score > 0) {
                power += tokens[r--];
                score--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().bagOfTokensScore(new int[]{100}, 50) == 0;
        assert new Solution().bagOfTokensScore(new int[]{100,200}, 150) == 1;
        assert new Solution().bagOfTokensScore(new int[]{100,200,300,400}, 200) == 2;
    }

}
