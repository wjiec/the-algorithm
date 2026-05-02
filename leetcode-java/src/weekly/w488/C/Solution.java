package weekly.w488.C;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Q3. Count Subarrays With Cost Less Than or Equal to K
 *
 * https://leetcode.cn/contest/weekly-contest-488/problems/count-subarrays-with-cost-less-than-or-equal-to-k/
 *
 * You are given an integer array nums, and an integer k.
 *
 * For any subarray nums[l..r], define its cost as:
 *
 * cost = (max(nums[l..r]) - min(nums[l..r])) * (r - l + 1).
 *
 * Return an integer denoting the number of subarrays of nums whose cost is less than or equal to k.
 */

public class Solution {

    public long countSubarrays(int[] nums, long k) {
        // 找到所有的子数组, 使得 (max(nums[l..r]) - min(nums[l..r])) * (r - l + 1) <= k
        //  - 随着子数组的变长, 值也变得越大; 就可以用滑动窗口来解决
        long ans = 0;
        Deque<Integer> maxQ = new ArrayDeque<>(), minQ = new ArrayDeque<>();
        for (int l = 0, r = 0; r < nums.length; r++) {
            while (!maxQ.isEmpty() && nums[maxQ.getLast()] <= nums[r]) maxQ.removeLast(); maxQ.add(r);
            while (!minQ.isEmpty() && nums[minQ.getLast()] >= nums[r]) minQ.removeLast(); minQ.add(r);

            // 如果窗口内不满足要求, 则移动左端点
            while ((nums[maxQ.getFirst()] - nums[minQ.getFirst()]) * (r - l + 1L) > k) {
                l++;
                while (!minQ.isEmpty() && minQ.getFirst() < l) minQ.removeFirst();
                while (!maxQ.isEmpty() && maxQ.getFirst() < l) maxQ.removeFirst();
            }
            ans += r - l + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
