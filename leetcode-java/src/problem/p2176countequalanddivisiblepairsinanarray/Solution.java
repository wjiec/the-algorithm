package problem.p2176countequalanddivisiblepairsinanarray;

/**
 * 2176. Count Equal and Divisible Pairs in an Array
 *
 * https://leetcode-cn.com/problems/count-equal-and-divisible-pairs-in-an-array/
 *
 * Given a 0-indexed integer array nums of length n and an integer k, return the number of pairs (i, j)
 * where 0 <= i < j < n, such that nums[i] == nums[j] and (i * j) is divisible by k.
 */

public class Solution {

    public int countPairs(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j] && (i * j) % k == 0) ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countPairs(new int[]{3,1,2,2,2,1,3}, 2) == 4;
        assert new Solution().countPairs(new int[]{1,2,3,4}, 1) == 0;
    }

}
