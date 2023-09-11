package weekly.w361.C;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2845. Count of Interesting Subarrays
 *
 * https://leetcode.cn/contest/weekly-contest-361/problems/count-of-interesting-subarrays/
 *
 * You are given a 0-indexed integer array nums, an integer modulo, and an integer k.
 *
 * Your task is to find the count of subarrays that are interesting.
 *
 * A subarray nums[l..r] is interesting if the following condition holds:
 *
 * Let cnt be the number of indices i in the range [l, r] such that nums[i] % modulo == k. Then, cnt % modulo == k.
 * Return an integer denoting the count of interesting subarrays.
 *
 * Note: A subarray is a contiguous non-empty sequence of elements within an array.
 */

public class Solution {

    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long ans = 0; int sum = 0;
        for (var num : nums) {
            if (num % modulo == k) {
                sum = (sum + 1) % modulo;
            }
            ans += map.getOrDefault((sum - k + modulo) % modulo, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countInterestingSubarrays(List.of(2, 7), 7, 0) == 1;
        assert new Solution().countInterestingSubarrays(List.of(7, 2), 7, 0) == 1;

        assert new Solution().countInterestingSubarrays(List.of(3,2,4), 2, 1) == 3;
        assert new Solution().countInterestingSubarrays(List.of(3,1,9,6), 3, 0) == 2;
    }

}
