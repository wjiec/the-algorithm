package problem.p1116printzeroevenodd;

import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 *
 * https://leetcode.cn/problems/print-zero-even-odd/
 *
 * You have a function printNumber that can be called with an integer parameter and prints it to the console.
 *
 * For example, calling printNumber(7) prints 7 to the console.
 * You are given an instance of the class ZeroEvenOdd that has three functions: zero, even, and odd. The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A: calls zero() that should only output 0's.
 * Thread B: calls even() that should only output even numbers.
 * Thread C: calls odd() that should only output odd numbers.
 * Modify the given class to output the series "010203040506..."
 * where the length of the series must be 2n.
 *
 * Implement the ZeroEvenOdd class:
 *
 * ZeroEvenOdd(int n) Initializes the object with the number n that represents
 * the numbers that should be printed.
 *
 * void zero(printNumber) Calls printNumber to output one zero.
 * void even(printNumber) Calls printNumber to output one even number.
 * void odd(printNumber) Calls printNumber to output one odd number.
 */

@SuppressWarnings("ClassCanBeRecord")
public class Solution {

    private static class ZeroEvenOdd {
        private volatile int count;
        private volatile int seq = 1;
        private volatile boolean zero = true;
        private volatile boolean even = true;
        private final Object bus = new Object();
        public ZeroEvenOdd(int n) { this.count = 2 * n; }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (count > 0) {
                synchronized (bus) {
                    while (!zero) bus.wait();
                    --count;

                    printNumber.accept(0);
                    zero = false;
                    even = !even;
                    bus.notifyAll();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while (count > 0) {
                synchronized (bus) {
                    while (!even) bus.wait();
                    --count;

                    printNumber.accept(seq++);
                    zero = true;
                    bus.notifyAll();
                }
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while (count > 0) {
                synchronized (bus) {
                    while (even) bus.wait();
                    --count;

                    printNumber.accept(seq++);
                    zero = true;
                    bus.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd z = new ZeroEvenOdd(10);

        new Thread(() -> {
            try {
                z.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                z.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                z.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
