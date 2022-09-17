package weekly.bw87.B;

import java.util.Arrays;

/**
 * 6185. Maximum Matching of Players With Trainers
 *
 * https://leetcode.cn/contest/biweekly-contest-87/problems/maximum-matching-of-players-with-trainers/
 *
 * You are given a 0-indexed integer array players, where players[i] represents
 * the ability of the ith player.
 *
 * You are also given a 0-indexed integer array trainers, where trainers[j]
 * represents the training capacity of the jth trainer.
 *
 * The ith player can match with the jth trainer if the player's ability is less than or equal
 * to the trainer's training capacity. Additionally, the ith player can be matched with at
 * most one trainer, and the jth trainer can be matched with at most one player.
 *
 * Return the maximum number of matchings between players and trainers that satisfy these conditions.
 */

public class Solution {

    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players); Arrays.sort(trainers);

        int ans = 0;
        for (int i = 0, j = 0; i < players.length && j < trainers.length; ) {
            if (players[i] <= trainers[j]) { ans++; i++; }
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
