package problem.p506relativeranks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 506. Relative Ranks
 *
 * https://leetcode-cn.com/problems/relative-ranks/
 *
 * You are given an integer array score of size n, where score[i] is the score of the ith athlete in a competition.
 * All the scores are guaranteed to be unique.
 *
 * The athletes are placed based on their scores, where the 1st place athlete has the highest score,
 * the 2nd place athlete has the 2nd highest score, and so on. The placement of each athlete determines their rank:
 *
 * The 1st place athlete's rank is "Gold Medal".
 * The 2nd place athlete's rank is "Silver Medal".
 * The 3rd place athlete's rank is "Bronze Medal".
 *
 * For the 4th place to the nth place athlete,
 * their rank is their placement number (i.e., the xth place athlete's rank is "x").
 *
 * Return an array answer of size n where answer[i] is the rank of the ith athlete.
 */

public class Solution {

    public String[] findRelativeRanks(int[] score) {
        int sz = score.length;
        int[] ss = Arrays.copyOf(score, sz);

        Arrays.sort(ss);
        Map<Integer, Integer> rank = new HashMap<>();
        for (int i = sz - 1; i >= 0; i--) {
            rank.put(ss[i], sz - i);
        }

        String[] rs = new String[score.length];
        for (int i = 0; i < score.length; i++) {
            switch (rank.get(score[i])) {
                case 1:
                    rs[i] = "Gold Medal"; break;
                case 2:
                    rs[i] = "Silver Medal"; break;
                case 3:
                    rs[i] = "Bronze Medal"; break;
                default:
                    rs[i] = String.valueOf(rank.get(score[i]));
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert Arrays.deepEquals(new Solution().findRelativeRanks(new int[]{5, 4, 3, 2, 1}),
            new String[]{"Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"});
        assert Arrays.deepEquals(new Solution().findRelativeRanks(new int[]{2000, 4, 10000, 2, 1}),
            new String[]{"Silver Medal", "Bronze Medal", "Gold Medal", "4", "5"});
    }

}
