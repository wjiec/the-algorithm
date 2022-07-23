package weekly.bw83.D;

import java.util.HashSet;
import java.util.Set;

/**
 * 6131. Shortest Impossible Sequence of Rolls
 *
 * https://leetcode.cn/contest/biweekly-contest-83/problems/shortest-impossible-sequence-of-rolls/
 *
 * You are given an integer array rolls of length n and an integer k.
 * You roll a k sided dice numbered from 1 to k, n times, where
 * the result of the ith roll is rolls[i].
 *
 * Return the length of the shortest sequence of rolls that cannot be taken from rolls.
 *
 * A sequence of rolls of length len is the result of rolling a k sided dice len times.
 *
 * Note that the sequence taken does not have to be consecutive as long as it is in order.
 */

public class Solution {

    public int shortestSequence(int[] rolls, int k) {
        int ans = 1;
        Set<Integer> set = new HashSet<>();
        for (int roll : rolls) {
            set.add(roll);
            if (set.size() == k) {
                set.clear(); ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().shortestSequence(new int[]{4,2,1,2,3,3,2,4,1}, 4) == 3;
        assert new Solution().shortestSequence(new int[]{1,1,2,2}, 2) == 2;
        assert new Solution().shortestSequence(new int[]{1,1,3,2,2,2,3,3}, 4) == 1;
    }

}
