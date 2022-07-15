package problem.p1226thediningphilosophers;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 1226. The Dining Philosophers
 *
 * https://leetcode.cn/problems/the-dining-philosophers/
 *
 * Five silent philosophers sit at a round table with bowls of spaghetti.
 * Forks are placed between each pair of adjacent philosophers.
 *
 * Each philosopher must alternately think and eat. However, a philosopher can only eat spaghetti
 * when they have both left and right forks. Each fork can be held by only one philosopher and
 * so a philosopher can use the fork only if it is not being used by another philosopher.
 *
 * After an individual philosopher finishes eating, they need to put down both forks so that
 * the forks become available to others. A philosopher can take the fork on their right or the
 * one on their left as they become available, but cannot start eating before getting both forks.
 *
 * Eating is not limited by the remaining amounts of spaghetti or stomach space; an infinite
 * supply and an infinite demand are assumed.
 *
 * Design a discipline of behaviour (a concurrent algorithm) such that no philosopher will
 * starve; i.e., each can forever continue to alternate between eating and thinking, assuming
 * that no philosopher can know when others may want to eat or think.
 */

public class Solution {

    private static class DiningPhilosophers {
        private final ReentrantLock lock = new ReentrantLock();
        public DiningPhilosophers() {}

        // call the run() method of any runnable to execute its code
        public void wantsToEat(int philosopher,
                               Runnable pickLeftFork,
                               Runnable pickRightFork,
                               Runnable eat,
                               Runnable putLeftFork,
                               Runnable putRightFork) throws InterruptedException {
            lock.lock();
            pickLeftFork.run();
            pickRightFork.run();
            eat.run();
            putLeftFork.run();
            putRightFork.run();
            lock.unlock();
        }
    }

}
