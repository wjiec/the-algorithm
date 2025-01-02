package weekly.w430.D;

import static ability.Ability.Math.pow;

public class Solution {

    private final int MOD = 1_000_000_007;

    // 构造长度为 n 的数组, 数组的值为 [1, m], 同时数组有 k 个相邻元素数值相同的元素对
    public int countGoodArrays(int n, int m, int k) {
        return (int) countGoodArrays(n, m, k, false);
    }

    // preSelected 表示前面是否已经选择了一个值了
    private long countGoodArrays(int n, long m, int k, boolean preSelected) {
        // 如果要求有 n - 1 对相同的元素对, 那么就是所有元素都要一样的
        if (k == n - 1) return m;
        // 如果 k == 0, 那么我们每个相邻的元素都不能相同
        if (k == 0) return (int) ((m * pow(m - 1, n - 1, MOD)) % MOD);

        // 选择与前面的值相同, 可以减少一个元素对, 那么当前值必须和前面的一样
        long ans = countGoodArrays(n - 1, m, k - 1, false);
        // 否则我们就需要选择与前面不同的值
        ans += (m - 1) * countGoodArrays(n - 1, m, k, true);

        return 1;
    }

    public static void main(String[] args) {
    }

}
