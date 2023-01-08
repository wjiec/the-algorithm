package weekly.w327.B;

import java.util.PriorityQueue;

/**
 * 6285. Maximal Score After Applying K Operations
 *
 * https://leetcode.cn/contest/weekly-contest-327/problems/maximal-score-after-applying-k-operations/
 *
 * You are given a 0-indexed integer array nums and an integer k. You have a starting score of 0.
 *
 * In one operation:
 *
 * choose an index i such that 0 <= i < nums.length,
 * increase your score by nums[i], and
 * replace nums[i] with ceil(nums[i] / 3).
 * Return the maximum possible score you can attain after applying exactly k operations.
 *
 * The ceiling function ceil(val) is the least integer greater than or equal to val.
 */

public class Solution {

    public long maxKelements(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (var v : nums) pq.add(v);

        long ans = 0;
        for (; k > 0; k--) {
            int curr = pq.remove();
            ans += curr;
            pq.add((int) Math.ceil(curr / 3.0));
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
