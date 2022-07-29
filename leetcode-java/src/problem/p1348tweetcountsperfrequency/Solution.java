package problem.p1348tweetcountsperfrequency;

import common.Checker;

import java.util.*;

/**
 * 1348. Tweet Counts Per Frequency
 *
 * https://leetcode.cn/problems/tweet-counts-per-frequency/
 *
 * A social media company is trying to monitor activity on their site by analyzing the number of tweets
 * that occur in select periods of time. These periods can be partitioned into smaller time chunks
 * based on a certain frequency (every minute, hour, or day).
 *
 * For example, the period [10, 10000] (in seconds) would be partitioned into the
 * following time chunks with these frequencies:
 *
 * Every minute (60-second chunks): [10,69], [70,129], [130,189], ..., [9970,10000]
 * Every hour (3600-second chunks): [10, 3609], [3610,7209], [7210,10000]
 * Every day (86400-second chunks): [10,10000]
 * Notice that the last chunk may be shorter than the specified frequency's chunk size and will
 * always end with the end time of the period (10000 in the above example).
 *
 * Design and implement an API to help the company with their analysis.
 *
 * Implement the TweetCounts class:
 *
 * TweetCounts() Initializes the TweetCounts object.
 * void recordTweet(String tweetName, int time) Stores the tweetName at the recorded time (in seconds).
 * List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime)
 * Returns a list of integers representing the number of tweets with tweetName in each time chunk for
 * the given period of time [startTime, endTime] (in seconds) and frequency freq.
 * freq is one of "minute", "hour", or "day" representing a frequency of every minute, hour, or day respectively.
 */

public class Solution {

    private static class TweetCounts {
        private final Map<String, TreeMap<Integer, Integer>> tweets = new HashMap<>();
        public TweetCounts() {}

        public void recordTweet(String tweetName, int time) {
            tweets.computeIfAbsent(tweetName, v -> new TreeMap<>())
                .merge(time, 1, Integer::sum);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            int step = 0;
            switch (freq) {
                case "minute" -> step = 60;
                case "hour" -> step = 3600;
                case "day" -> step = 86400;
            }

            List<Integer> ans = new ArrayList<>();
            if (tweets.containsKey(tweetName)) {
                TreeMap<Integer, Integer> ts = tweets.get(tweetName);
                int s = startTime, e = Math.min(s + step, endTime + 1);
                while (s <= endTime) {
                    int c = 0; Map.Entry<Integer, Integer> x = ts.ceilingEntry(s);
                    while (x != null && x.getKey() < e) {
                        c += x.getValue();
                        x = ts.higherEntry(x.getKey());
                    }
                    ans.add(c);
                    s = e; e = Math.min(e + step, endTime + 1);
                }
            }

            return ans;
        }
    }

    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);
        assert Checker.check(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59), List.of(2));
        assert Checker.check(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60), List.of(2, 1));
        tweetCounts.recordTweet("tweet3", 120);
        assert Checker.check(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210), List.of(4));
    }

}
