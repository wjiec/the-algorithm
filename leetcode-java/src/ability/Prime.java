package ability;

import java.util.Random;

@SuppressWarnings({
    "unused",
    "DuplicatedCode",
    "UnusedReturnValue",
    "ResultOfMethodCallIgnored"
})
public class Prime {

    // 粗略估算小于等于 n 的实数中有多少个质数
    // see https://en.wikipedia.org/wiki/Prime-counting_function
    public static int count(int n) {
        return (int) (1.25506 * (n / Math.log(n)));
    }

    // 线性筛获取小于等于 n 中的每个质数, 每个合数只会被标记一次
    public static int[] euler(int n) {
        // [count, p1, p2, ...]
        int[] primes = new int[count(n) + 1];
        primes[++primes[0]] = 2;

        boolean[] skipped = new boolean[n / 2 + 1];
        for (int i = 3; i <= n; i += 2) {
            if (!skipped[i / 2]) primes[++primes[0]] = i;
            for (int j = 1; j <= primes[0] && i * primes[j] <= n; j++) {
                int next = i * primes[j];
                if (next % 2 == 1) skipped[next / 2] = true;
                if (i % primes[j] == 0) break;
            }
        }

        return primes;
    }

    // 前 12 个质数用于判断另一个数 n 是否为质数
    private static final int[] PRIME_TESTS = new int[]{
        2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37
    };

    // Miller-Rabin 素数测试, 返回 n 是否是质数
    public static boolean isPrime(int n) {
        if (n < 3 || n % 2 == 0) return n == 2;

        int a = n - 1, b = 0;
        while (a % 2 == 0) { a /= 2; b++; }

        for (int i = 0, j; i < PRIME_TESTS.length; i++) {
            if (PRIME_TESTS[i] >= n) return PRIME_TESTS[i] == n;

            long v = Ability.Math.pow(PRIME_TESTS[i], a, n);
            if (v == 1) continue;

            for (j = 0; j < b; j++) {
                if (v == n - 1) break;
                v = (v * v) % n;
            }
            if (j >= b) return false;
        }

        return true;
    }

    // Miller-Rabin 素数测试, 对 n 进行 k 轮测试, 以检查
    // 其是否为质数(建议是不少于 8 轮, 且不宜过大, 推荐 12 轮)
    public static boolean isPrime(int n, int k) {
        if (n < 3 || n % 2 == 0) return n == 2;

        int a = n - 1, b = 0;
        while (a % 2 == 0) { a /= 2; b++; }

        for (int i = 0, j; i < k; i++) {
            long r = new Random().nextInt(n - 2) + 2;
            long v = Ability.Math.pow(r, a, n);
            if (v == 1) continue;

            for (j = 0; j < b; j++) {
                if (v == n - 1) break;
                v = (v * v) % n;
            }
            if (j >= b) return false;
        }

        return true;
    }

    // 朴素解法, 可适用于较小的数据范围
    private static class Simple {
        // 朴素解法: 枚举 2 到 sqrt(n) 之间的所有实数, 判断
        // 其是否是 n 的约数
        public static boolean isPrime(int n) {
            if (n < 3 || n % 2 == 0) return n == 2;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("Euler 1_000_000", () -> euler(1_000_000));
        Benchmark.benchmark("Miller-Rabin 1_000_000 rand 12", () -> {
            for (int i = 0; i < 1_000_000; i++) isPrime(i, 12);
        });
        Benchmark.benchmark("Miller-Rabin 1_000_000 prime 12", () -> {
            for (int i = 0; i < 1_000_000; i++) isPrime(i);
        });
    }

}
