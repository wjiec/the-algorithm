package problem.p1115printfoobaralternately;

/**
 * 1115. Print FooBar Alternately
 *
 * https://leetcode.cn/problems/print-foobar-alternately/
 *
 * Suppose you are given the following code:
 *
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *     for (int i = 0; i < n; i++) {
 *       print("bar");
 *     }
 *   }
 * }
 * The same instance of FooBar will be passed to two different threads:
 *
 * thread A will call foo(), while
 * thread B will call bar().
 * Modify the given program to output "foobar" n times.
 */

public class Solution {

    private static class FooBar {
        private final int n;
        private volatile boolean foo = true;
        private final Object bus = new Object();
        public FooBar(int n) { this.n = n; }

        public void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (bus) {
                    while (!foo) {
                        bus.wait();
                    }

                    printFoo.run();
                    foo = false;
                    bus.notifyAll();
                }
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                synchronized (bus) {
                    while (foo) {
                        bus.wait();
                    }

                    printBar.run();
                    foo = true;
                    bus.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar fb = new FooBar(10);
        new Thread(() -> {
            try {
                fb.foo(() -> System.out.print("foo"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fb.bar(() -> System.out.println("bar"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);
    }

}
