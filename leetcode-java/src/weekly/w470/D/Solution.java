package weekly.w470.D;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Q4. Count No-Zero Pairs That Sum to N
 *
 * https://leetcode.cn/contest/weekly-contest-470/problems/count-no-zero-pairs-that-sum-to-n/
 *
 * A no-zero integer is a positive integer that does not contain the digit 0 in its decimal representation.
 *
 * Given an integer n, count the number of pairs (a, b) where:
 *  - a and b are no-zero integers.
 *  - a + b = n
 *
 * Return an integer denoting the number of such pairs.
 */

@SuppressWarnings("unchecked")
public class Solution {

    public long countNoZeroPairs(long n) {
        int nl = String.valueOf(n).length();
        // 构造数对 (a, b) 使得 a + b == n, 且 a 和 b 都是无零整数
        return dfs(n, Long.parseLong("1"+"0".repeat(nl - 1)), true, true);
    }

    private final Map<Long, Long>[] memo = new Map[20];
    { Arrays.setAll(memo, i -> new HashMap<>()); }

    // 当前正在找的是第 base 位上的整数方案, 返回满足条件的方案数
    private long dfs(long n, long base, boolean af, boolean bf) {
        if (n == 0) return base == 0 ? 1 : 0;
        if (base == 0) return 0;
        if (!af && !bf && memo[(int) (Math.log10(base))].containsKey(n)) {
            return memo[(int) (Math.log10(base))].get(n);
        }

        long ans = 0;
        // 枚举 a 当前位所选择整数 ad 和 b 当前位所选择的整数 bd
        for (int ad = af ? 0 : 1; ad < 10; ad++) {
            for (int bd = bf ? 0 : 1; bd < 10; bd++) {
                long curr = (ad + bd) * base;
                if (curr <= n) ans += dfs(n - curr, base / 10, af && ad == 0, bf && bd == 0);
            }
        }

        if (!af && !bf) memo[(int) (Math.log10(base))].put(n, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countNoZeroPairs(12) == 9;

        assert new Solution().countNoZeroPairs(2) == 1;
        assert new Solution().countNoZeroPairs(3) == 2;
        assert new Solution().countNoZeroPairs(11) == 8;
    }

}
