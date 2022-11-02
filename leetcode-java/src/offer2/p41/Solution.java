package offer2.p41;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 剑指 Offer II 041. 滑动窗口的平均值
 *
 * https://leetcode-cn.com/problems/qIsx9U/
 *
 * 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 *
 * 实现 MovingAverage 类：
 *
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，
 * 请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 */

public class Solution {

    private static class MovingAverage {
        private int sum = 0;
        private final int window;
        private final Queue<Integer> queue = new ArrayDeque<>();

        public MovingAverage(int size) { window = size; }

        public double next(int val) {
            if (queue.size() == window) sum -= queue.remove();
            queue.add(val); sum += val;
            return (double) sum / queue.size();
        }
    }

    public static void main(String[] args) {
        MovingAverage average = new MovingAverage(3);
        assert average.next(1) == 1;
        assert average.next(10) == 5.5;
        assert average.next(3) == 4.666666666666667;
        assert average.next(5) == 6.0;
    }

}
