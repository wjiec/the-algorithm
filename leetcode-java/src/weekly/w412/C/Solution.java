package weekly.w412.C;

import common.Checker;

import java.util.PriorityQueue;

/**
 * 3266. Final Array State After K Multiplication Operations II
 *
 * https://leetcode.cn/problems/final-array-state-after-k-multiplication-operations-ii/
 *
 * You are given an integer array nums, an integer k, and an integer multiplier.
 *
 * You need to perform k operations on nums. In each operation:
 *
 * Find the minimum value x in nums. If there are multiple occurrences of the minimum
 * value, select the one that appears first.
 *
 * Replace the selected minimum value x with x * multiplier.
 *
 * After the k operations, apply modulo 109 + 7 to every value in nums.
 *
 * Return an integer array denoting the final state of nums after performing all k
 * operations and then applying the modulo.
 */

public class Solution {

    private static final int MOD = 1_000_000_007;

    // 对于满足 x <= y <= z 的数组, 如果有 x * m > z
    //  则之后的每次操作都是依次在 x, y, z 逐次进行
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        if (multiplier == 1) return nums;

        int max = nums[0], n = nums.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return Long.compare(a[1], b[1]);
            return Long.compare(a[0], b[0]);
        });
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            pq.add(new long[]{i, nums[i]});
        }

        // 首先让所有的数字都处于接近的位置, 使其满足 x * m > z
        while (!pq.isEmpty() && pq.peek()[1] < max && k > 0) {
            var curr = pq.remove();
            curr[1] *= multiplier; // 这里不能取模, 防止出现 x * m > max && (x * m) % mod < max
            pq.add(curr); k--;
        }

        // 对于剩下的, 每次都需要执行 k / n 次, 其中最小的 k % n 个还需额外执行一次
        int div = k / n, remain = k % n;
        for (int i = 0; !pq.isEmpty(); i++) {
            var curr = pq.remove();
            nums[(int) curr[0]] = (int) (((curr[1] % MOD) * pow(multiplier, div + (i < remain ? 1 : 0))) % MOD);
        }

        return nums;
    }

    private static int pow(long a, long b) {
        long ans = 1, curr = a;
        for (; b != 0; b >>= 1) {
            if ((b & 1) == 1) ans = (ans * curr) % MOD;
            curr = (curr * curr) % MOD;
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().getFinalState(new int[]{66307295,441787703,589039035,322281864}, 900900704, 641725), new int[]{664480092,763599523,886046925,47878852});
        assert Checker.check(new Solution().getFinalState(new int[]{161209470}, 56851412, 39846), new int[]{198168519});
        assert Checker.check(new Solution().getFinalState(new int[]{2}, 4, 4), new int[]{512});

        assert Checker.check(new Solution().getFinalState(new int[]{2,1,3,5,6}, 5, 2), new int[]{8,4,6,5,6});
        assert Checker.check(new Solution().getFinalState(new int[]{100000,2000}, 2, 1000000), new int[]{999999307,999999993});
    }

}
