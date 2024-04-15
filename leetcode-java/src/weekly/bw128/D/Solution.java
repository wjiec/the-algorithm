package weekly.bw128.D;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 3113. Find the Number of Subarrays Where Boundary Elements Are Maximum
 *
 * https://leetcode.cn/contest/biweekly-contest-128/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum/
 *
 * You are given an array of positive integers nums.
 *
 * Return the number of subarrays of nums, where the first and the last
 * elements of the subarray are equal to the largest element in the subarray.
 */

public class Solution {

    public long numberOfSubarrays(int[] nums) {
        TreeMap<Integer, List<Integer>> pos = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            pos.computeIfAbsent(nums[i], v -> new ArrayList<>()).add(i);
        }

        long ans = 0;
        TreeSet<Integer> seen = new TreeSet<>();
        for (var k : pos.descendingKeySet()) {
            ans += seg(pos.get(k), seen);
            seen.addAll(pos.get(k));
        }

        return ans;
    }

    private long seg(List<Integer> choice, TreeSet<Integer> baned) {
        if (baned.isEmpty()) return pattern(choice.size());

        long ans = 0, curr = 1;
        for (int i = 1; i < choice.size(); i++) {
            Integer hi = baned.higher(choice.get(i - 1));
            if (hi != null && hi <= choice.get(i)) {
                ans += pattern(curr);
                curr = 1;
            } else curr++;
        }

        return ans + pattern(curr);
    }

    private long pattern(long n) { return (1 + n) * n / 2; }

    public static void main(String[] args) {
        assert new Solution().numberOfSubarrays(new int[]{6,26,6}) == 3;

        assert new Solution().numberOfSubarrays(new int[]{1,4,3,3,2}) == 6;
        assert new Solution().numberOfSubarrays(new int[]{3,3,3}) == 6;
        assert new Solution().numberOfSubarrays(new int[]{1}) == 1;
    }

}
