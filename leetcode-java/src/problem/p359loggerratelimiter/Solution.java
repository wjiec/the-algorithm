package problem.p359loggerratelimiter;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 359. Logger Rate Limiter
 *
 * https://leetcode-cn.com/problems/logger-rate-limiter/
 *
 * Design a logger system that receives a stream of messages along with their timestamps.
 * Each unique message should only be printed at most every 10 seconds (i.e. a message printed
 * at timestamp t will prevent other identical messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Implement the Logger class:
 *
 * Logger() Initializes the logger object.
 *
 * bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be
 * printed in the given timestamp, otherwise returns false.
 */

public class Solution {

    private static class Pair {
        private final int timestamp;
        private final String message;
        public Pair(int timestamp, String message) {
            this.timestamp = timestamp;
            this.message = message;
        }
    }

    private static class Logger {
        private final Queue<Pair> window;
        private final Map<String, Pair> map;
        public Logger() { map = new HashMap<>(); window = new ArrayDeque<>(); }
        public boolean shouldPrintMessage(int timestamp, String message) {
            Pair pair = new Pair(timestamp, message); window.add(pair);
            while (!window.isEmpty() && window.peek().timestamp <= timestamp - 10) {
                map.remove(window.remove().message);
            }

            if (!map.containsKey(message)) {
                map.put(message, pair);
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Logger logger = new Logger();
        assert logger.shouldPrintMessage(1, "foo");
        assert logger.shouldPrintMessage(2, "bar");
        assert !logger.shouldPrintMessage(3, "foo");
        assert !logger.shouldPrintMessage(8, "bar");
        assert !logger.shouldPrintMessage(10, "foo");
        assert logger.shouldPrintMessage(11, "foo");
    }

}
