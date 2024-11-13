package weekly.bw143.D;

import common.PrettyPrinter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ability.Ability.Math.gcd;

/** @noinspection unchecked*/
public class Solution {

    public String smallestNumber(String num, long t) {
        // 数位乘积得到的数只能由 2 3 5 7 乘起来组成, 如果 t 的有一个 11 的质因子, 则无法分解
        long split = t; int factors = 0;
        for (var p : new int[]{2, 3, 5, 7}) {
            while (split % p == 0) { split /= p; factors++; }
        }
        // 存在非 2 3 5 7 的质因数, 无法分解
        if (split != 1) return "-1";

        // 计算得到我们一共有 factors 个因子, 则说明至少也需要 factors 位
        //  此时我们需要计算需要在 num 前面补多少个 0
        int padding = Math.max(1, factors + 1 - num.length());
        num = "0".repeat(padding) + num;

        char[] ans = new char[num.length()];
        Arrays.fill(ans, '0');

        Set<Long>[] seen = new Set[num.length()];
        Arrays.setAll(seen, i -> new HashSet<>());

        dfs(num.toCharArray(), 0, t, padding, true, ans, seen);
        PrettyPrinter.println(ans);

        int skip = 0;
        while (ans[skip] == '0') skip++;
        return new String(ans, skip, num.length() - skip);
    }

    // 数位搜索, 当前查找的是第 i 位, 当前需要的数位乘积是 t, 前面一共补了 padding 位的 0, limited 表示当前位的选择是否受到限制
    private boolean dfs(char[] chars, int i, long t, int padding, boolean limited, char[] ans, Set<Long>[] seen) {
        // 当到达末尾时, 表示我们已经找到了一种方案, 根据乘积的值是否为 1 判断是否找到正确的方案
        if (i == chars.length) return t == 1;

        // 如果当前位没有被限制, 同时到当前位时乘积 t 已经搜索过了, 那就直接返回
        if (!limited && !seen[i].add(t)) return false;

        // 如果当前位是补 0 的可以考虑直接跳过这一位的枚举
        if (limited && i < padding && dfs(chars, i + 1, t, padding, true, ans, seen)) return true;

        // 否则的话我们需要根据情况枚举当前数位可选的值
        for (int d = limited ? Math.max(1, chars[i] - '0') : 1; d < 10; d++) {
            // 如果当前位选择的是 d, 那么剩下的数位的乘积需要满足 t / gcd(t, d)
            //  - 要求是所有数位的乘积 p 能被 t 整除, 这也就意味着 gcd(p, t) = 1
            //  - 例如 t = 10:
            //      - 如果填 5, 则后续只需要是 3 的倍数即可
            //      - 如果填 6(2 * 3), 则后续只需要是 5 的倍数即可(6 中隐含了一个 2)
            if (dfs(chars, i + 1, t / gcd(t, d), padding, limited && d == (chars[i] - '0'), ans, seen)) {
                ans[i] = (char) ('0' + d);
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        assert new Solution().smallestNumber("78", 42).equals("167");
        assert new Solution().smallestNumber("10", 320).equals("588");

        assert new Solution().smallestNumber("1234", 256).equals("1488");
        assert new Solution().smallestNumber("12355", 50).equals("12355");
        assert new Solution().smallestNumber("11111", 26).equals("-1");
    }

}
