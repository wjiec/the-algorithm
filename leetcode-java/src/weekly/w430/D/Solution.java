package weekly.w430.D;

import java.util.HashMap;
import java.util.Map;

import static ability.Ability.Math.pow;

public class Solution {

    private final int MOD = 1_000_000_007;

    // 构造长度为 n 的数组, 数组的值为 [1, m], 同时数组有 k 个相邻元素数值相同的元素对
    public int countGoodArrays(int n, int m, int k) {
        return (int) ((m * count(n - 1, m, k)) % MOD);
    }

    private final Map<Long, Long> memo = new HashMap<>();

    // isFirst 表示是否当前是否是第一个元素
    private long count(long n, long m, long k) {
        if (k >= n) return k == n ? 1 : 0; // 无法创建一个长度为 n 但是要求有 >= n 个元素对
        // 如果 k == 0, 那么我们每个相邻的元素都不能相同
        if (k == 0) return pow(m - 1, n, MOD);

        long key = (n << 32) | k;
        if (memo.containsKey(key)) return memo.get(key);

        // 可以选择与前面不同的值, 但是这样就无法创建一个相同的元素对了
        long ans = ((m - 1) * count(n - 1, m, k)) % MOD;
        // 选择与前面的值相同, 可以减少一个元素对, 那么当前值必须和前面的一样
        ans = (ans + count(n - 1, m, k - 1)) % MOD;

        memo.put(key, ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countGoodArrays(4, 2, 2) == 6;
        assert new Solution().countGoodArrays(3, 2, 1) == 4;
        assert new Solution().countGoodArrays(5, 2, 0) == 2;
    }

}
