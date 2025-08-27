package weekly.w460.D;

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
        int n = nums.length, MAX_N = 1 << n, MASK = MAX_N - 1;

        // xorSum[i] = {g1, g2, all} 表示 i 的位图中所有数的组合
        //  - (g1, g2) 表示分成两组的异或值, all 表示将所有数都进行 xor 的值
        long ans = 0;
        long[][] xorSum = new long[MAX_N][3];
        for (int i = 0; i < n; i++) {
            int bitset = 1 << i, v = nums[i];
            for (int mask = 0; mask < bitset; mask++) {
                // 自己作为一组或者加入之前的组
                xorSum[bitset | mask][2] = Math.max(xorSum[mask][2] ^ v, v);
                // 选择加入 g1 或者 g2
                if ((xorSum[mask][0] ^ v) + xorSum[mask][1] > xorSum[mask][0] + (xorSum[mask][1] ^ v)) {
                    xorSum[bitset | mask][0] = xorSum[mask][0] ^ v;
                    xorSum[bitset | mask][1] = xorSum[mask][1];
                } else {
                    xorSum[bitset | mask][0] = xorSum[mask][0];
                    xorSum[bitset | mask][1] = xorSum[mask][1] ^ v;
                }

                ans = Math.max(ans, Math.max(xorSum[bitset | mask][2], Math.max(xorSum[bitset | mask][0], xorSum[bitset | mask][1])));
            }
        }

        // 枚举所有组合的情况
        long[] and = new long[MAX_N];
        for (int i = 0; i < n; i++) {
            int bitset = 1 << i, v = nums[i];
            for (int mask = 0; mask < bitset; mask++) {
                and[bitset | mask] = and[mask] & v;

                int xor = MASK ^ (bitset | mask);
                ans = Math.max(ans, and[bitset | mask] + Math.max(xorSum[xor][0] + xorSum[xor][1], xorSum[xor][2]));
            }
        }

        System.out.println(ans);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximizeXorAndXor(new int[]{2, 3}) == 5;
        assert new Solution().maximizeXorAndXor(new int[]{1, 3, 2}) == 6;
        assert new Solution().maximizeXorAndXor(new int[]{2, 3, 6, 7}) == 15;
    }

}
