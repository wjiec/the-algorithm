package weekly.w460.D;

import ability.Benchmark;

/**
 * Q4. Partition Array for Maximum XOR and AND
 *
 * https://leetcode.cn/contest/weekly-contest-460/problems/partition-array-for-maximum-xor-and-and/
 *
 * You are given an integer array nums.
 *
 * Partition the array into three (possibly empty) subsequences A, B, and C such that every
 * element of nums belongs to exactly one subsequence.
 *
 * Your goal is to maximize the value of: XOR(A) + AND(B) + XOR(C)
 *
 * where:
 *
 * XOR(arr) denotes the bitwise XOR of all elements in arr. If arr is empty, its value is defined as 0.
 * AND(arr) denotes the bitwise AND of all elements in arr. If arr is empty, its value is defined as 0.
 * Return the maximum value achievable.
 *
 * Note: If multiple partitions result in the same maximum sum, you can consider any one of them.
 */

public class Solution {

    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length, MAX_N = 1 << n, max = 0;
        for (var v : nums) max = Math.max(max, v);

        int[] a = new int[MAX_N], x = new int[MAX_N]; a[0] = -1;
        for (int i = 0; i < n; i++) {
            int v = nums[i], bit = 1 << i;
            for (int j = 0; j < bit; j++) {
                a[bit | j] = a[j] & v;
                x[bit | j] = x[j] ^ v;
            }
        }
        a[0] = 0; // 恢复原始状态

        long ans = 0; int sz = 32 - Integer.numberOfLeadingZeros(max);
        for (int b = 0; b < MAX_N; b++) {
            int s = (MAX_N - 1) ^ b;
            ans = Math.max(ans, a[b] + maxXor(s, x[s], nums, sz));
        }
        return ans;
    }

    private long maxXor(int mask, int xor, int[] nums, int sz) {
        XorBasis b = new XorBasis(sz);
        for (int i = 0; i < nums.length; i++) {
            if ((mask & (1 << i)) != 0) {
                b.insert(nums[i] & ~xor);
            }
        }
        return xor + b.maxXor() * 2L;
    }

    private static class XorBasis {
        private final int[] base;
        public XorBasis(int n) { base = new int[n]; }
        public void insert(int x) {
            while (x > 0) {
                int i = 31 - Integer.numberOfLeadingZeros(x); // x 的最高位
                if (base[i] == 0) { // x 和之前的基是线性无关的
                    base[i] = x; // 新增一个基, 最高位是 i
                    return;
                }
                // 保证参与 maxXor 的基的最高位是互不相同的, 方便我们做贪心
                x ^= base[i];
            }
        }
        public int maxXor() {
            int ans = 0;
            // 从高到低贪心, 越高的位越应该是 1
            // 由于每个位的基至多一个, 所以每个位只需要考虑异或一个基, 若能变大就应用
            for (int i = base.length - 1; i >= 0; i--) {
                ans = Math.max(ans, ans ^ base[i]);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Benchmark.benchmark("", () -> {
            assert new Solution().maximizeXorAndXor(new int[]{
                20194931,414512694,363723219,512267527,16746413,414429915,752676171,
                296435179,611918530,159845273,329829245,415075965,61816591,279334329,
                284479182,260505719,297930436,274359599,391282927
            }) == 2480998406L;
        });
        assert new Solution().maximizeXorAndXor(new int[]{67,235,126}) == 428;

        assert new Solution().maximizeXorAndXor(new int[]{2, 3}) == 5;
        assert new Solution().maximizeXorAndXor(new int[]{1, 3, 2}) == 6;
        assert new Solution().maximizeXorAndXor(new int[]{2, 3, 6, 7}) == 15;
    }

}
