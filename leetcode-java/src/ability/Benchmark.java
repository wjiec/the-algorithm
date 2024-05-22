package ability;

import java.util.Date;

public class Benchmark {

    // 以毫秒为单位打印执行 3 次 runnable 的平均耗时
    public static void benchmark(String name, Runnable runnable) {
        benchmark(name, runnable, 3);
    }

    // 以毫秒为单位打印执行 times 次 runnable 的平均耗时
    public static void benchmark(String name, Runnable runnable, int times) {
        System.out.printf("[%s] average time spend = %.6fms\n", name, benchmark(runnable, times));
    }

    // 以毫秒为单位返回执行 times 次 runnable 的平均耗时
    private static double benchmark(Runnable runnable, int times) {
        double total = 0;
        for (int i = 0; i < times; i++) {
            long start = new Date().getTime();
            runnable.run();
            total += new Date().getTime() - start;
        }
        return total / times;
    }

}
