package problem.p1546maximumnumberofnonoverlappingsubarrayswithsumequalstarget;

import java.util.HashSet;
import java.util.Set;

/**
 * 1546. Maximum Number of Non-Overlapping Subarrays With Sum Equals Target
 *
 * https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/
 *
 * Given an array nums and an integer target, return the maximum number of non-empty non-overlapping subarrays
 * such that the sum of values in each subarray is equal to target.
 */

public class Solution {

    public int maxNonOverlapping(int[] nums, int target) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>(); set.add(0);

            int sum = 0;
            while (i < n) {
                sum += nums[i];
                if (set.contains(sum - target)) {
                    ans++; break;
                } else {
                    set.add(sum); i++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().maxNonOverlapping(new int[]{-5,5,-4,5,4}, 5) == 2;

        assert new Solution().maxNonOverlapping(new int[]{1,1,1,1,1}, 2) == 2;
        assert new Solution().maxNonOverlapping(new int[]{-1,3,5,1,4,2,-9}, 6) == 2;
        assert new Solution().maxNonOverlapping(new int[]{-2,6,6,3,5,4,1,2,8}, 10) == 3;
        assert new Solution().maxNonOverlapping(new int[]{0,0,0}, 0) == 3;
    }

}
