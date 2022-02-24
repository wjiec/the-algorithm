package problem.p346movingaveragefromdatastream;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 346. Moving Average from Data Stream
 *
 * https://leetcode-cn.com/problems/moving-average-from-data-stream/
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 */

public class Solution {

    private static class MovingAverage {
        private int total = 0;
        private final int size;
        private final Queue<Integer> queue = new ArrayDeque<>();

        public MovingAverage(int size) { this.size = size; }

        public double next(int val) {
            total += val; queue.add(val);
            if (queue.size() > size) total -= queue.remove();
            return (double) total / queue.size();
        }
    }

    public static void main(String[] args) {
        MovingAverage average = new MovingAverage(3);
        assert Checker.check(average.next(1), 1.0);
        assert Checker.check(average.next(10), 5.5);
        assert Checker.check(average.next(3), 4.6666666);
        assert Checker.check(average.next(5), 6.0);
    }

}
