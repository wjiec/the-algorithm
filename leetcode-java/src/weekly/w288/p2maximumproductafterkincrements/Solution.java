package weekly.w288.p2maximumproductafterkincrements;

import java.util.PriorityQueue;

/**
 * 6039. Maximum Product After K Increments
 *
 * https://leetcode-cn.com/contest/weekly-contest-288/problems/maximum-product-after-k-increments/
 *
 * You are given an array of non-negative integers nums and an integer k. In one operation,
 * you may choose any element from nums and increment it by 1.
 *
 * Return the maximum product of nums after at most k operations. Since the answer may be very large,
 * return it modulo 109 + 7.
 */

public class Solution {

    public int maximumProduct(int[] nums, int k) {
        if (nums.length == 1) return nums[0] + k;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (var n : nums) queue.add(n);
        while (k > 0) {
            int a = queue.remove();
            int d = queue.peek() - a;
            if (d + 1 <= k) {
                k -= d + 1;
                queue.add(queue.peek() + 1);
            } else {
                queue.add(a + k);
                k = 0;
            }
        }

        long ans = 1, MOD = (int) (1e9 + 7);
        for (var n : queue) ans = (ans * n) % MOD;
        return (int) ans;
    }

    public static void main(String[] args) {
        assert new Solution().maximumProduct(new int[]{24,5,64,53,26,38}, 54) == 180820950;
        assert new Solution().maximumProduct(new int[]{6,4,5,7,8,4,5}, 4) == 302400;
        assert new Solution().maximumProduct(new int[]{8,7,7,9}, 8) == 9000;

        assert new Solution().maximumProduct(new int[]{0,4}, 5) == 20;
        assert new Solution().maximumProduct(new int[]{6,3,3,2}, 2) == 216;
    }

}
