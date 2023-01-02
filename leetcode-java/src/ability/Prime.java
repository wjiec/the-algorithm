package ability;

import common.PrettyPrinter;

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

    // Miller-Rabin 素数测试
    public static boolean isPrime(long n) {
        return true;
    }

    // 朴素解法, 可适用于较小的数据范围
    private static class Simple {
        // 朴素解法: 枚举 2 到 sqrt(n) 之间的所有实数, 判断
        // 其是否是 n 的约数
        public static boolean isPrime(int n) {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[] primes = euler(10);
        PrettyPrinter.println(primes);
    }

}
