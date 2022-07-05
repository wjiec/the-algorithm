package problem.p1117buildingh2o;

import java.util.concurrent.Semaphore;

/**
 * 1117. Building H2O
 *
 * https://leetcode.cn/problems/building-h2o/
 *
 * There are two kinds of threads: oxygen and hydrogen. Your goal is to group these threads to form water molecules.
 *
 * There is a barrier where each thread has to wait until a complete molecule can be formed. Hydrogen and
 * oxygen threads will be given releaseHydrogen and releaseOxygen methods respectively, which will allow
 * them to pass the barrier. These threads should pass the barrier in groups of three, and they must
 * immediately bond with each other to form a water molecule. You must guarantee that all the threads from
 * one molecule bond before any other threads from the next molecule do.
 *
 * In other words:
 *
 * If an oxygen thread arrives at the barrier when no hydrogen threads are present, it
 * must wait for two hydrogen threads.
 * If a hydrogen thread arrives at the barrier when no other threads are present, it
 * must wait for an oxygen thread and another hydrogen thread.
 * We do not have to worry about matching the threads up explicitly; the threads do not
 * necessarily know which other threads they are paired up with. The key is that threads pass
 * the barriers in complete sets; thus, if we examine the sequence of threads that bind and divide
 * them into groups of three, each group should contain one oxygen and two hydrogen threads.
 *
 * Write synchronization code for oxygen and hydrogen molecules that enforces these constraints.
 */

public class Solution {

    private static class H2O {
        private final Semaphore oSemaphore = new Semaphore(0);
        private final Semaphore hSemaphore = new Semaphore(2);
        public H2O() {}

        public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
            hSemaphore.acquire();
            releaseHydrogen.run();
            oSemaphore.release();
        }

        public void oxygen(Runnable releaseOxygen) throws InterruptedException {
            oSemaphore.acquire(2);
            releaseOxygen.run();
            hSemaphore.release(2);
        }
    }

}
