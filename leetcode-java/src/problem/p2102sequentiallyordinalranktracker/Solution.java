package problem.p2102sequentiallyordinalranktracker;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 2102. Sequentially Ordinal Rank Tracker
 *
 * https://leetcode.cn/problems/sequentially-ordinal-rank-tracker/
 *
 * A scenic location is represented by its name and attractiveness score, where name is a unique string
 * among all locations and score is an integer. Locations can be ranked from the best to the worst.
 *
 * The higher the score, the better the location. If the scores of two locations are equal, then the
 * location with the lexicographically smaller name is better.
 *
 * You are building a system that tracks the ranking of locations with the system initially starting
 * with no locations. It supports:
 *
 * Adding scenic locations, one at a time.
 * Querying the ith best location of all locations already added, where i is the number of times
 * the system has been queried (including the current query).
 *
 * For example, when the system is queried for the 4th time, it returns the 4th best location of all
 * locations already added.
 *
 * Note that the test data are generated so that at any time, the number of queries does not exceed
 * the number of locations added to the system.
 *
 * Implement the SORTracker class:
 *
 * SORTracker() Initializes the tracker system.
 * void add(string name, int score) Adds a scenic location with name and score to the system.
 * string get() Queries and returns the ith best location, where i is the number of times this
 * method has been invoked (including this invocation).
 */

public class Solution {

    private static class SORTracker {
        private int cursor = 0;
        private String curr = "";
        private final TreeMap<Integer, TreeSet<String>> rank = new TreeMap<>();
        public SORTracker() { rank.put(cursor, new TreeSet<>() {{ add(curr); }}); }

        public void add(String name, int score) {
            rank.computeIfAbsent(score, k -> new TreeSet<>()).add(name);
            // 如果插入的值比当前游标的位置大, 那么游标就需要往前移
            //  - 往前移动需要分数更大或者字典序越小
            //  - 如果移动到某一个分数的头部, 则需要找到更大的分数的最大字典序的位置
            if (score > cursor || (score == cursor && name.compareTo(curr) < 0)) {
                if ((curr = rank.get(cursor).lower(curr)) == null) {
                    cursor = rank.higherKey(cursor);
                    curr = rank.get(cursor).last();
                }
            }
        }

        public String get() {
            String ans = curr;
            // 取出一个序号之后, 需要往后移动
            //  - 往后移动需要分数更小或者字典序越大
            //  - 如果移动到某一个分数的尾部, 则需要找到更小的分数的最小字典序的位置
            if ((curr = rank.get(cursor).higher(curr)) == null) {
                cursor = rank.lowerKey(cursor);
                curr = rank.get(cursor).first();
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        SORTracker tracker = new SORTracker();
        tracker.add("bradford", 2);
        tracker.add("branford", 3);
        assert tracker.get().equals("branford");
        tracker.add("alps", 2);
        assert tracker.get().equals("alps");
        tracker.add("orland", 2);
        assert tracker.get().equals("bradford");
        tracker.add("orlando", 3);
        assert tracker.get().equals("bradford");
        tracker.add("alpine", 2);
        assert tracker.get().equals("bradford");
        assert tracker.get().equals("orland");
    }

}
