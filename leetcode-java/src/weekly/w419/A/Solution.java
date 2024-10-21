package weekly.w419.A;

import java.util.*;

/**
 * 3318. Find X-Sum of All K-Long Subarrays I
 *
 * https://leetcode.cn/contest/weekly-contest-419/problems/find-x-sum-of-all-k-long-subarrays-i/
 *
 * You are given an array nums of n integers and two integers k and x.
 *
 * The x-sum of an array is calculated by the following procedure:
 *
 * Count the occurrences of all elements in the array.
 *
 * Keep only the occurrences of the top x most frequent elements. If two elements have the same
 * number of occurrences, the element with the bigger value is considered more frequent.
 *
 * Calculate the sum of the resulting array.
 *
 * Note that if an array has less than x distinct elements, its x-sum is the sum of the array.
 *
 * Return an integer array answer of length n - k + 1 where answer[i] is the x-sum of the subarray nums[i..i + k - 1].
 */

public class Solution {

    public int[] findXSum(int[] nums, int k, int x) {
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = xSum(nums, i, i + k, x);
        }
        return ans;
    }

    private int xSum(int[] nums, int l, int r, int x) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = l; i < r; i++) m.merge(nums[i], 1, Integer::sum);

        List<Integer> sorted = new ArrayList<>(m.keySet());
        sorted.sort((a, b) -> (int) m.get(a) != m.get(b) ? (m.get(b) - m.get(a)) : (b - a));

        Set<Integer> matches = new HashSet<>();
        for (int i = 0; i < x && i < sorted.size(); i++) {
            matches.add(sorted.get(i));
        }

        int ans = 0;
        for (int i = l; i < r; i++) if (matches.contains(nums[i])) ans += nums[i];
        return ans;
    }

    public static void main(String[] args) {
    }

}
