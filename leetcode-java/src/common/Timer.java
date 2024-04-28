package common;

import java.util.concurrent.Callable;

public class Timer {

    public static void stopwatch(Callable<Void> fn) {
        var start = System.currentTimeMillis();
        try { fn.call(); } catch (Exception ignored) {}
        var end = System.currentTimeMillis();

        System.out.printf("time cost: %d(ms)\n", end - start);
    }

}
