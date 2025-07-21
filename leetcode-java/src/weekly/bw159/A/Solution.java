package weekly.bw159.A;

import java.util.*;

/**
 * Q1. Minimum Adjacent Swaps to Alternate Parity
 *
 * https://leetcode.cn/contest/biweekly-contest-159/problems/minimum-adjacent-swaps-to-alternate-parity/
 *
 * You are given an array nums of distinct integers.
 *
 * In one operation, you can swap any two adjacent elements in the array.
 *
 * An arrangement of the array is considered valid if the parity of adjacent elements alternates,
 * meaning every pair of neighboring elements consists of one even and one odd number.
 *
 * Return the minimum number of adjacent swaps required to transform nums into any valid arrangement.
 *
 * If it is impossible to rearrange nums such that no two adjacent elements have the same parity, return -1.
 */

public class Solution {

    public int minSwaps(int[] nums) {
        Queue<Integer> odd = new ArrayDeque<>();
        Queue<Integer> even = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) even.add(i);
            else odd.add(i);
        }
        if (Math.abs(odd.size() - even.size()) > 1) return -1;

        // 如果将位置 j 的元素通过交换相邻元素的方式移动到 i
        //  - 需要操作 j - i 次
        //  - 交换之后相当于在当前 i 的位置插入 j
        //
        // 通过一个队列模拟
        if (odd.size() > even.size()) return minSwaps(odd, even, 1);
        else if (even.size() > odd.size()) return minSwaps(odd, even, 0);
        else {
            int oddStart = minSwaps(new ArrayDeque<>(odd), new ArrayDeque<>(even), 1);
            int evenStart = minSwaps(odd, even, 0);
            return Math.min(oddStart, evenStart);
        }
    }

    private int minSwaps(Queue<Integer> odd, Queue<Integer> even, int first) {
        int ans = 0, n = odd.size() + even.size();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < n; i++, first ^= 1) {
            if ((first & 1) == 0) {
                while (seen.contains(even.peek())) even.remove();
                int curr = even.remove(); seen.add(curr);
                if (curr > i) ans += curr - i;
            } else {
                while (seen.contains(odd.peek())) odd.remove();
                int curr = odd.remove(); seen.add(curr);
                if (curr > i) ans += curr - i;
            }
        }
        return ans;
    }

    private static class Optimization {
        public int minSwaps(int[] nums) {
            int n = nums.length;
            // 记录所有的 1 的位置, 然后枚举 1 的位置放在 0 2 4 ... 还是放在 1 3 5 ...
            //  - 可以枚举将每个 1 移动到应该要去的位置, 则剩下的空位肯定都是 0 满足要求的
            List<Integer> ones = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if ((nums[i] & 1) == 1) ones.add(i);
            }

            // 奇数的数量是 odd, 偶数的数量是 n - odd, 两者的差的绝对值
            // 不能超过 1, 也就是 |odd - (n - odd)| <= 1
            //  = |n -  2 * odd| <= 1
            if (Math.abs(nums.length - 2 * ones.size()) > 1) return -1;

            return Math.min(minSwaps(ones, 0, n), minSwaps(ones, 1, n));
        }

        private int minSwaps(List<Integer> ones, int start, int n) {
            // 计算当所有的奇数位于 start, start + 2, start + 4, ... 位置时的最小移动步数
            //  - 也就是从 start 位置开始计算, 能分出多少个相邻组, 每个相邻组就有 1 个位置
            //  - 只有一个元素的组也是符合要求的
            //
            // 对于 start 开始的位置, 我们可以将 start 之前的裁切掉, 此时就从 start 位置为 0 开始了
            //  - 剩下的长度时 n - start, 计算二元组的数量, 单独的也算
            //  - 也就是 (n - start + 1) / 2
            if ((n - start + 1) / 2 != ones.size()) return Integer.MAX_VALUE;

            int ans = 0;
            for (int i = start, j = 0; j < ones.size(); i += 2, j++) {
                ans += Math.abs(ones.get(j) - i);
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        assert new Solution().minSwaps(new int[]{4,5,6,8}) == -1;
        assert new Solution().minSwaps(new int[]{2,4,6,5,7}) == 3;
        assert new Solution().minSwaps(new int[]{2,4,5,7}) == 1;
        assert new Solution().minSwaps(new int[]{1,2,3}) == 0;
    }

}
