package weekly.w482.C;

/**
 * Q3. Smallest All-Ones Multiple
 *
 * https://leetcode.cn/contest/weekly-contest-482/problems/smallest-all-ones-multiple/
 *
 * You are given a positive integer k.
 *
 * Find the smallest integer n divisible by k that consists of only the digit 1
 * in its decimal representation (e.g., 1, 11, 111, ...).
 *
 * Return an integer denoting the number of digits in the decimal representation of n.
 * If no such n exists, return -1.
 */

public class Solution {

    public int minAllOneMultiple(int k) {
        // 首先排除一下条件
        //  - 由于 n 个 1 组成的数字肯定是奇数, 所以无法被偶数整除
        //  - 由于 5 的倍数都是 0 或者 5, 所以肯定也无法被整除
        if (k % 2 == 0 || k % 5 == 0) return -1;

        // 假设 x * k 的结果是"最小全 1 倍数", 那么根据以下等式
        //  - x = x1 * 1 + x2 * 10 + x3 * 100 + ...
        //  - x * k = (x1 + x2 * 10 + x3 * 100 + ...) * k = 111...
        //          = x1k + 100x2k + 1000x3k + ...
        //
        // 那我们从个位数开始找所有的 x1, x2, x3, ...
        //  - 如果个位数要是 1, 那么与 k 的个位数 k1 相乘必定要是 k1 * x1 = *1
        //  - 枚举所有的可能结果 *1, 找到 x1 应该填什么
        //  - 此时枚举十位, 我们有第一位的进位 r1, 还有第一位选择的数 x1, 此时对于这一位, 如果要得到 111...
        //      - 那么必须是 k2 * (r1 + x1 * 10 + x2) = *1
        //  - 一直枚举直到答案为 111...
        return dfs(k, 0);
    }

    private int dfs(int k, int raise) {
        if (raise > 0) {
            int ones = 0, vr = raise;
            while (vr % 10 == 1) { vr /= 10; ones++; }
            if (vr == 0) return ones;
        }

        // 枚举当前位填的数是 x
        int ans = Integer.MAX_VALUE;
        for (int x = 0; x < 10; x++) {
            int r1 = raise + x * k;
            if (r1 % 10 == 1) {
                int curr = dfs(k, r1 / 10);
                if (curr >= 0) ans = Math.min(ans, curr + 1);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        assert new Solution().minAllOneMultiple(3) == 3;
        assert new Solution().minAllOneMultiple(7) == 6;
        assert new Solution().minAllOneMultiple(2) == -1;
    }

}
