package problem.p1195fizzbuzzmultithreaded;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * 1195. Fizz Buzz Multithreaded
 *
 * https://leetcode.cn/problems/fizz-buzz-multithreaded/
 *
 * You have the four functions:
 *
 * printFizz that prints the word "fizz" to the console,
 * printBuzz that prints the word "buzz" to the console,
 * printFizzBuzz that prints the word "fizzbuzz" to the console, and
 * printNumber that prints a given integer to the console.
 * You are given an instance of the class FizzBuzz that has four functions: fizz, buzz, fizzbuzz and number.
 * The same instance of FizzBuzz will be passed to four different threads:
 *
 * Thread A: calls fizz() that should output the word "fizz".
 * Thread B: calls buzz() that should output the word "buzz".
 * Thread C: calls fizzbuzz() that should output the word "fizzbuzz".
 * Thread D: calls number() that should only output the integers.
 * Modify the given class to output the series [1, 2, "fizz", 4, "buzz", ...] where
 * the ith token (1-indexed) of the series is:
 *
 * "fizzbuzz" if i is divisible by 3 and 5,
 * "fizz" if i is divisible by 3 and not 5,
 * "buzz" if i is divisible by 5 and not 3, or
 * i if i is not divisible by 3 or 5.
 * Implement the FizzBuzz class:
 *
 * FizzBuzz(int n) Initializes the object with the number n that represents the
 * length of the sequence that should be printed.
 *
 * void fizz(printFizz) Calls printFizz to output "fizz".
 * void buzz(printBuzz) Calls printBuzz to output "buzz".
 * void fizzbuzz(printFizzBuzz) Calls printFizzBuzz to output "fizzbuzz".
 * void number(printNumber) Calls printnumber to output the numbers.
 */

public class Solution {

    private static class FizzBuzz {
        private final int n;
        private volatile int count = 1;
        private final Lock lock = new ReentrantLock();
        private final Condition cond = lock.newCondition();
        public FizzBuzz(int n) { this.n = n; }

        public void fizz(Runnable printFizz) throws InterruptedException {
            while (count <= n) {
                lock.lock();
                if (count % 3 == 0 && count % 5 != 0) {
                    printFizz.run();
                    count++; cond.signalAll();
                } else cond.await();
                lock.unlock();
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (count <= n) {
                lock.lock();
                if (count % 3 != 0 && count % 5 == 0) {
                    printBuzz.run();
                    count++; cond.signalAll();
                } else cond.await();
                lock.unlock();
            }
        }

        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (count <= n) {
                lock.lock();
                if (count % 3 == 0 && count % 5 == 0) {
                    printFizzBuzz.run();
                    count++; cond.signalAll();
                } else cond.await();
                lock.unlock();
            }
        }

        public void number(IntConsumer printNumber) throws InterruptedException {
            while (count <= n) {
                lock.lock();
                if (count % 3 != 0 && count % 5 != 0) {
                    printNumber.accept(count);
                    count++; cond.signalAll();
                } else cond.await();
                lock.unlock();
            }
        }
    }

}
