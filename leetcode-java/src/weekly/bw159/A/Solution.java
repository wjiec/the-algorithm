package weekly.bw159.A;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

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

    public static void main(String[] args) {
        assert new Solution().minSwaps(new int[]{4,5,6,8}) == -1;
        assert new Solution().minSwaps(new int[]{2,4,6,5,7}) == 3;
        assert new Solution().minSwaps(new int[]{2,4,5,7}) == 1;
        assert new Solution().minSwaps(new int[]{1,2,3}) == 0;
    }

}
