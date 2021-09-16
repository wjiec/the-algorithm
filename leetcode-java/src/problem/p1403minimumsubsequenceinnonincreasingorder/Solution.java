package problem.p1403minimumsubsequenceinnonincreasingorder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1403. Minimum Subsequence in Non-Increasing Order
 *
 * https://leetcode-cn.com/problems/minimum-subsequence-in-non-increasing-order/
 *
 * Given the array nums, obtain a subsequence of the array whose sum of elements is
 * strictly greater than the sum of the non included elements in such subsequence. 
 *
 * If there are multiple solutions, return the subsequence with minimum size
 * and if there still exist multiple solutions,
 * return the subsequence with the maximum total sum of all its elements.
 * A subsequence of an array can be obtained by erasing some (possibly zero) elements from the array. 
 *
 * Note that the solution with the given constraints is guaranteed to be unique.
 * Also return the answer sorted in non-increasing order.
 */

public class Solution {

    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);

        int total = 0, sum = 0;
        for (var n : nums) total += n;

        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i]; ans.add(nums[i]);
            if (sum > total - sum) break;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minSubsequence(new int[]{4,3,10,9,8}).equals(List.of(10,9));
        assert new Solution().minSubsequence(new int[]{4,4,7,6,7}).equals(List.of(7,7,6));
        assert new Solution().minSubsequence(new int[]{6}).equals(List.of(6));
    }

}
