package weekly.w360.B;

import java.util.HashSet;
import java.util.Set;

/**
 * 2834. Find the Minimum Possible Sum of a Beautiful Array
 *
 * https://leetcode.cn/contest/weekly-contest-360/problems/find-the-minimum-possible-sum-of-a-beautiful-array/
 *
 * You are given positive integers n and target.
 *
 * An array nums is beautiful if it meets the following conditions:
 *
 * nums.length == n.
 * nums consists of pairwise distinct positive integers.
 * There doesn't exist two distinct indices, i and j, in the range [0, n - 1], such that nums[i] + nums[j] == target.
 * Return the minimum possible sum that a beautiful array could have.
 */

public class Solution {

    public long minimumPossibleSum(int n, int target) {
        long ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 1; set.size() < n; i++) {
            if (!set.contains(target - i) && set.add(i)) {
                ans += i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
