package weekly.w463.B;

/**
 * Q2. XOR After Range Multiplication Queries I
 *
 * https://leetcode.cn/contest/weekly-contest-463/problems/xor-after-range-multiplication-queries-i/
 *
 * You are given an integer array nums of length n and a 2D integer array
 * queries of size q, where queries[i] = [li, ri, ki, vi].
 *
 * For each query, you must apply the following operations in order:
 *
 * Set idx = li.
 * While idx <= ri:
 * Update: nums[idx] = (nums[idx] * vi) % (109 + 7)
 * Set idx += ki.
 *
 * Return the bitwise XOR of all elements in nums after processing all queries.
 */

public class Solution {

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            long curr = nums[i];
            for (var q : queries) {
                int l = q[0], r = q[1], k = q[2], v = q[3];
                if (i >= l && i <= r && ((i - l) % k) == 0) curr = (curr * v) % 1_000_000_007;
            }
            ans ^= (int) curr;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
