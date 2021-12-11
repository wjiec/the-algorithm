package daily.d211211p911onlineelection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 911. Online Election
 *
 * https://leetcode-cn.com/problems/online-election/
 *
 * You are given two integer arrays persons and times. In an election,
 * the ith vote was cast for persons[i] at time times[i].
 *
 * For each query at a time t, find the person that was leading the election at time t.
 *
 * Votes cast at time t will count towards our query.
 *
 * In the case of a tie, the most recent vote (among tied candidates) wins.
 *
 * Implement the TopVotedCandidate class:
 *
 * TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
 * int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.
 */

public class Solution {

    static class TopVotedCandidate {

        private final List<Integer> tops = new ArrayList<>();
        private final int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            this.times = times;

            Map<Integer, Integer> counts = new HashMap<>();
            int top = -1;
            counts.put(top, -1);

            for (int person : persons) {
                counts.merge(person, 1, Integer::sum);
                if (counts.get(person) >= counts.get(top)) {
                    top = person;
                }
                tops.add(top);
            }
        }

        public int q(int t) {
            int l = 0, r = times.length - 1;
            while (l < r) {
                int mid = l + (r - l + 1) / 2;
                if (times[mid] <= t) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            return tops.get(l);
        }
    }

    public static void main(String[] args) {
        TopVotedCandidate candidate = new TopVotedCandidate(new int[]{0, 1, 1, 0, 0, 1, 0},
            new int[]{0, 5, 10, 15, 20, 25, 30});

        assert candidate.q(3) == 0;
        assert candidate.q(12) == 1;
        assert candidate.q(25) == 1;
        assert candidate.q(15) == 0;
        assert candidate.q(24) == 0;
        assert candidate.q(8) == 1;
    }

}
