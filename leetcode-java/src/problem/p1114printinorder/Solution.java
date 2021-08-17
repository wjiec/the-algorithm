package problem.p1114printinorder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1114. Print in Order
 *
 * https://leetcode-cn.com/problems/print-in-order/
 *
 * The same instance of Foo will be passed to three different threads.
 *
 * Thread A will call first(), thread B will call second(), and thread C will call third().
 *
 * Design a mechanism and modify the program to ensure that second() is executed after first(),
 * and third() is executed after second().
 *
 * Note:
 *
 * We do not know how the threads will be scheduled in the operating system,
 * even though the numbers in the input seem to imply the ordering.
 *
 * The input format you see is mainly to ensure our tests' comprehensiveness.
 */

public class Solution {

    static class Foo {
        private final AtomicInteger seq = new AtomicInteger();
        public Foo() {}
        public void first(Runnable printFirst) throws InterruptedException {
            printFirst.run();
            seq.incrementAndGet();
        }
        public void second(Runnable printSecond) throws InterruptedException {
            while (!seq.compareAndSet(1, 2)) { }
            printSecond.run();
        }
        public void third(Runnable printThird) throws InterruptedException {
            while (!seq.compareAndSet(2, 3)) { }
            printThird.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var foo = new Foo();
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch wait = new CountDownLatch(3);
        new Thread(() -> {
            try {
                latch.await();
                foo.first(() -> System.out.println("first"));
            } catch (InterruptedException ignored) {}

            wait.countDown();
        }).start();
        new Thread(() -> {
            try {
                latch.await();
                foo.second(() -> System.out.println("second"));
            } catch (InterruptedException ignored) {}

            wait.countDown();
        }).start();
        new Thread(() -> {
            try {
                latch.await();
                foo.third(() -> System.out.println("third"));
            } catch (InterruptedException ignored) {}

            wait.countDown();
        }).start();

        latch.countDown();
        wait.await();
    }

}
