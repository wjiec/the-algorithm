package weekly.bw142.D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 3333. Find the Original Typed String II
 *
 * https://leetcode.cn/problems/find-the-original-typed-string-ii/
 *
 * Alice is attempting to type a specific string on her computer. However, she tends to be
 * clumsy and may press a key for too long, resulting in a character being typed multiple times.
 *
 * You are given a string word, which represents the final output displayed on Alice's screen.
 *
 * You are also given a positive integer k.
 *
 * Return the total number of possible original strings that Alice might have intended to type,
 * if she was trying to type a string of size at least k.
 *
 * Since the answer may be very large, return it modulo 109 + 7.
 */

@SuppressWarnings("DuplicatedCode")
public class Solution {

    private final int MOD = 1_000_000_007;

    // Alice 输入字符串的长度至少为 k
    //  - 可以转换为所有可能的长度 - 不超过 k 的方案数
    public int possibleStringCount(String word, int k) {
        // 查到所有可能发生重复输入的每组字符的数量
        List<Integer> groups = new ArrayList<>();
        char prev = word.charAt(0); int count = 0;
        for (var c : word.toCharArray()) {
            if (c != prev) {
                groups.add(count);
                prev = c; count = 1;
            } else count++;
        }
        groups.add(count);

        // 所有可能的长度方案数
        long perm = 1;
        for (var g : groups) perm = (perm * g) % MOD;

        // 如果组数大于 k, 那就说明不管怎么组合都满足条件
        if (groups.size() >= k) return (int) perm;
        return (int) ((perm - dfs(groups, 0, 0, k) + MOD) % MOD);
    }

    private final Map<Long, Integer> memo = new HashMap<>();

    // 当前在位置 i 且已保留的字符数为 c, 要求至多不超过 k
    private int dfs(List<Integer> groups, int i, int c, int k) {
        if (c >= k) return 0;
        if (i == groups.size()) return 1;

        long key = ((long) i << 32) | c;
        if (memo.containsKey(key)) return memo.get(key);

        int ans = 0;
        // 枚举当前位置可以是几个字符, 至多 min(groups[i], c) 个, 至少 1 个
        for (int j = groups.get(i); j > 0; j--) {
            ans = (ans + dfs(groups, i + 1, c + j, k)) % MOD;
        }

        memo.put(key, ans);
        return ans;
    }

    // dfs 中的循环实际上就是求前缀和, 这里可以改为迭代形式
    private static class DynamicProgramming {
        public int possibleStringCount(String word, int k) {
            final int MOD = 1_000_000_007;
            List<Integer> groups = new ArrayList<>();
            char prev = word.charAt(0); int count = 0;
            for (var c : word.toCharArray()) {
                if (c != prev) {
                    groups.add(count);
                    prev = c; count = 1;
                } else count++;
            }
            groups.add(count);

            long perm = 1;
            for (var g : groups) perm = (perm * g) % MOD;
            if (groups.size() >= k) return (int) perm;

            // dp[i] 表示当前长度为 i 的方案数量
            long[] dp = new long[k]; dp[0] = 1;
            for (var g : groups) {
                // 计算 dp 的前缀和
                for (int i = 1; i < k; i++) {
                    dp[i] = (dp[i] + dp[i - 1]) % MOD;
                }

                long[] next = new long[k];
                // 对于当前组, 可以从 dp[i - (g - 1), i - 1] 进行转移
                for (int i = 1; i < k; i++) {
                    next[i] = dp[i - 1] - (i - g - 1 >= 0 ? dp[i - g - 1] : 0);
                }

                dp = next;
            }

            long mostK = 0;
            for (var v : dp) mostK = (mostK + v) % MOD;
            return (int) ((perm - mostK + MOD) % MOD);
        }
    }

    public static void main(String[] args) {
        assert new Solution().possibleStringCount("aabbccdd", 7) == 5;
        assert new Solution().possibleStringCount("aabbccdd", 8) == 1;
        assert new Solution().possibleStringCount("aaabbb", 3) == 8;

        assert new DynamicProgramming().possibleStringCount("dvvpssguuellxxdduuddggxyccqqookkhhoowwyssoobbtnnddfweeccmoqqjgleaazziijjzzbbjjkkkossllueexmiiyyodvvl", 88) == 135884609;
        assert new DynamicProgramming().possibleStringCount("aabbccdd", 7) == 5;
        assert new DynamicProgramming().possibleStringCount("aabbccdd", 8) == 1;
        assert new DynamicProgramming().possibleStringCount("aaabbb", 3) == 8;
    }

}
