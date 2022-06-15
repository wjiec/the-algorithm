package problem.p1010pairsofsongswithtotaldurationsdivisibleby60;

import java.util.HashMap;
import java.util.Map;

/**
 * 1010. Pairs of Songs With Total Durations Divisible by 60
 *
 * https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 *
 * You are given a list of songs where the ith song has a duration of time[i] seconds.
 *
 * Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
 * Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
 */

public class Solution {

    public int numPairsDivisibleBy60(int[] time) {
        int ans = 0;
        int[] mods = new int[60];
        for (var v : time) {
            int mod = v % 60;
            ans += mods[mod];
            mods[(60 - mod) % 60]++;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().numPairsDivisibleBy60(new int[]{30,20,150,100,40}) == 3;
        assert new Solution().numPairsDivisibleBy60(new int[]{60,60,60}) == 3;
    }

}
