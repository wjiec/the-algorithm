package weekly.w460.D;

import ability.Benchmark;
import common.Tag;

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

    @Tag({"线性基", "子集求最大异或和"})
    public long maximizeXorAndXor(int[] nums) {
        int n = nums.length, MAX_N = 1 << n, max = 0;
        for (var v : nums) max = Math.max(max, v);

        // 我们需要将数组分为三个部分, XOR(A) + AND(B) + XOR(C)
        //  - 我们选择枚举 B, 然后在剩余的元素集合 S 中找到最大的 XOR(A) + XOR(C)
        //
        // 异或操作只对数中的一个二进制位起作用, 在集合 S 上考虑元素每一个二进制位上 1 的数量
        //  - 如果 1 的数量为奇数, 则不管怎么分, A 和 C 在这一个二进制位上都是 [偶数 + 奇数] 的形式
        //      - 偶数个 1 异或起来为 0, 奇数个 1 异或之后为 1, 两者的和就是 1
        //  - 如果 1 的数量为偶数, 则 A 和 C 在这一个二进制位上要么是 [偶数 + 偶数] 的形式, 要么是 [奇数 + 奇数] 的形式
        //      - 如果是 [偶数 + 偶数] 的形式, 那么最终相加的结果为 0
        //      - 如果是 [奇数 + 奇数] 的形式, 那么最终相加的结果为 2
        //
        // 一个二进制位上 1 的数量要么是奇数要么就是偶数, 由于偶数数量的 1 最终都会被消掉, 所以 XOR(S) 其实就是
        // 所有二进制位上 1 的数量位奇数的所有数的异或和
        //
        // 如果一个二进制位上 1 的数量为偶数, 那么我们分成的两个子集合 A' 和 C' 在某一个二进制位上的奇偶性是相同的, 对于
        // 异或操作来说, 实际就可以认定为 XOR(A') == XOR(C')
        //
        // 所以最终的答案也就是 AND(B) + XOR(S) + 2 * XOR(A')
        //  - 枚举 AND(B) 的时候, 集合 S 是已知的, 也就是 XOR(S) 是一个定值
        //  - 问题变为如何在一个集合 S 中如何找到最大的 XOR(A')
        //
        // 我们使用线性基算法 (https://oi-wiki.org/math/linear-algebra/basis/) 来求解
        //  - 也就是对于一个集合 S, 我们可以使用有限的几个基来表示这个集合中的所有元素
        //  - 由于我们要求的是最大值, 那我们就可以从最大的基开始遍历, 只要使用这个基能变大, 那么我们就加上这个基

        int[] a = new int[MAX_N], x = new int[MAX_N]; a[0] = -1;
        // 预处理所有集合的 AND 值和 XOR 值
        for (int i = 0; i < n; i++) {
            int v = nums[i], bit = 1 << i;
            for (int j = 0; j < bit; j++) {
                a[bit | j] = a[j] & v;
                x[bit | j] = x[j] ^ v;
            }
        }
        a[0] = 0; // 恢复原始状态

        long ans = 0; int sz = 32 - Integer.numberOfLeadingZeros(max);
        // 接下来枚举 AND(B) 的值和在子集 S 中计算 XOR(A) + XOR(C) 的最大值
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
        private final int[] basis;
        public XorBasis(int n) { basis = new int[n]; }
        public void insert(int x) {
            while (x > 0) {
                int i = 31 - Integer.numberOfLeadingZeros(x); // x 的最高位
                if (basis[i] == 0) { // x 和之前的基是线性无关的
                    basis[i] = x; // 新增一个基, 最高位是 i
                    return;
                }
                // 保证参与 maxXor 的基的最高位是互不相同的, 方便我们做贪心
                x ^= basis[i];
            }
        }
        public int maxXor() {
            int ans = 0;
            // 从高到低贪心, 越高的位越应该是 1
            // 由于每个位的基至多一个, 所以每个位只需要考虑异或一个基, 若能变大就应用
            for (int i = basis.length - 1; i >= 0; i--) {
                ans = Math.max(ans, ans ^ basis[i]);
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
