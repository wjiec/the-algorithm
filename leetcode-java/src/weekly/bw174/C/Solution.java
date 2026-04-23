package weekly.bw174.C;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Q3. Number of Alternating XOR Partitions
 *
 * https://leetcode.cn/contest/biweekly-contest-174/problems/number-of-alternating-xor-partitions/
 *
 * You are given an integer array nums and two distinct integers target1 and target2.
 *
 * A partition of nums splits it into one or more contiguous, non-empty blocks that cover the entire array without overlap.
 *
 * A partition is valid if the bitwise XOR of elements in its blocks alternates between target1 and target2, starting with target1.
 *
 * Formally, for blocks b1, b2, …:
 *
 * XOR(b1) = target1
 * XOR(b2) = target2 (if it exists)
 * XOR(b3) = target1, and so on.
 * Return the number of valid partitions of nums, modulo 109 + 7.
 *
 * Note: A single block is valid if its XOR equals target1.
 */

public class Solution {

    public int alternatingXOR(int[] nums, int target1, int target2) {
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> (a + b) % 1_000_000_007;
        // 分块之后, 每一块的按位异或的值在 target1 和 target2 之间轮转
        //  - 每一个数字, 要么是加入之前的块, 要么是自己单独成为一块
        //  - 一定要是从 target1 开始的
        Map<Integer, Integer> dp1 = new HashMap<>(); dp1.put(nums[0], 1);
        Map<Integer, Integer> dp2 = new HashMap<>();
        for (int i = 1; i < nums.length; i++) {
            // 当前这一位数字, 可以选择加入前一个区块
            Map<Integer, Integer> next1 = new HashMap<>();
            Map<Integer, Integer> next2 = new HashMap<>();
            for (var kv : dp1.entrySet()) next1.merge(kv.getKey() ^ nums[i], kv.getValue(), sum);
            for (var kv : dp2.entrySet()) next2.merge(kv.getKey() ^ nums[i], kv.getValue(), sum);
            // 如果前一个满足了 target1 或者 target2 可以转移到另一边
            next1.merge(nums[i], dp2.getOrDefault(target2, 0), sum);
            next2.merge(nums[i], dp1.getOrDefault(target1, 0), sum);

            dp1 = next1; dp2 = next2;
        }

        return (dp1.getOrDefault(target1, 0) + dp2.getOrDefault(target2, 0)) % 1_000_000_007;
    }

    public static void main(String[] args) {
        assert new Solution().alternatingXOR(new int[]{2,3,1,4}, 1, 5) == 1;
        assert new Solution().alternatingXOR(new int[]{1,0,0}, 1, 0) == 3;
        assert new Solution().alternatingXOR(new int[]{7}, 1, 7) == 0;
    }

}
