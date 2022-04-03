package weekly.w287.p1findplayerswithzerooronelosses;

import java.util.*;

/**
 * 5235. Find Players With Zero or One Losses
 *
 * https://leetcode-cn.com/contest/weekly-contest-287/problems/find-players-with-zero-or-one-losses/
 *
 * You are given an integer array matches where matches[i] = [winneri, loseri] indicates that
 * the player winneri defeated player loseri in a match.
 *
 * Return a list answer of size 2 where:
 *
 * answer[0] is a list of all players that have not lost any matches.
 * answer[1] is a list of all players that have lost exactly one match.
 * The values in the two lists should be returned in increasing order.
 *
 * Note:
 *
 * You should only consider the players that have played at least one match.
 * The testcases will be generated such that no two matches will have the same outcome.
 */

public class Solution {

    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> winner = new HashSet<>();
        Map<Integer, Integer> loser = new HashMap<>();
        for (var match : matches) {
            winner.add(match[0]);
            loser.merge(match[1], 1, Integer::sum);
        }
        for (var match : matches) winner.remove(match[1]);

        List<Integer> wins = new ArrayList<>(winner);
        List<Integer> loses = new ArrayList<>();
        for (var kv : loser.entrySet()) {
            if (kv.getValue() == 1) {
                loses.add(kv.getKey());
            }
        }

        wins.sort(Integer::compare);
        loses.sort(Integer::compare);

        return List.of(wins, loses);
    }

}
