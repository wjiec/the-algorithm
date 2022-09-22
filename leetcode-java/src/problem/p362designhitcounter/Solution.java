package problem.p362designhitcounter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 362. Design Hit Counter
 *
 * https://leetcode.cn/problems/design-hit-counter/
 *
 * Design a hit counter which counts the number of hits received
 * in the past 5 minutes (i.e., the past 300 seconds).
 *
 * Your system should accept a timestamp parameter (in seconds granularity), and
 * you may assume that calls are being made to the system in chronological order
 * (i.e., timestamp is monotonically increasing).
 * Several hits may arrive roughly at the same time.
 *
 * Implement the HitCounter class:
 *
 * HitCounter() Initializes the object of the hit counter system.
 * void hit(int timestamp) Records a hit that happened at timestamp (in seconds).
 * Several hits may happen at the same timestamp.
 * int getHits(int timestamp) Returns the number of hits in the past 5 minutes
 * from timestamp (i.e., the past 300 seconds).
 */

public class Solution {

    private static class HitCounter {
        private final Queue<Integer> hits = new ArrayDeque<>();
        public HitCounter() {}
        public void hit(int timestamp) { hits.add(timestamp); }

        public int getHits(int timestamp) {
            while (!hits.isEmpty() && hits.peek() + 300 <= timestamp) hits.remove();
            return hits.size();
        }
    }

    public static void main(String[] args) {
        HitCounter counter = new HitCounter();
        counter.hit(1);
        counter.hit(2);
        counter.hit(3);
        assert counter.getHits(4) == 3;
        counter.hit(300);
        assert counter.getHits(300) == 4;
        assert counter.getHits(301) == 3;
    }

}
