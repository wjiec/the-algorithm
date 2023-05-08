package weekly.w344.B;

import java.util.HashMap;
import java.util.Map;

/**
 * 6417. Frequency Tracker
 *
 * https://leetcode.cn/contest/weekly-contest-344/problems/frequency-tracker/
 *
 * Design a data structure that keeps track of the values in it and answers
 * some queries regarding their frequencies.
 *
 * Implement the FrequencyTracker class.
 *
 * FrequencyTracker(): Initializes the FrequencyTracker object with an empty array initially.
 * void add(int number): Adds number to the data structure.
 *
 * void deleteOne(int number): Deletes one occurence of number from the data structure.
 * The data structure may not contain number, and in this case nothing is deleted.
 *
 * bool hasFrequency(int frequency): Returns true if there is a number in the data
 * structure that occurs frequency number of times, otherwise, it returns false.
 */

public class Solution {

    private static class FrequencyTracker {
        private final Map<Integer, Integer> bkt = new HashMap<>();
        private final Map<Integer, Integer> cnt = new HashMap<>();
        public FrequencyTracker() {}

        public void add(int number) {
            int curr = bkt.merge(number, 1, Integer::sum);
            if (curr - 1 > 0) cnt.merge(curr - 1, -1, FrequencyTracker::sum);
            cnt.merge(curr, 1, FrequencyTracker::sum);
        }

        public void deleteOne(int number) {
            if (bkt.containsKey(number)) {
                Integer curr = bkt.merge(number, -1, FrequencyTracker::sum);
                if (curr == null) curr = 0;
                cnt.merge(curr + 1, -1, FrequencyTracker::sum);
                if (curr != 0) cnt.merge(curr, 1, FrequencyTracker::sum);
            }
        }

        public boolean hasFrequency(int frequency) { return cnt.containsKey(frequency); }

        public static Integer sum(Integer a, Integer b) { return a + b == 0 ? null : a + b; }
    }


    public static void main(String[] args) {
    }

}
