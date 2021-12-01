package problem.p1995countspecialquadruplets;

/**
 * 1995. Count Special Quadruplets
 *
 * https://leetcode-cn.com/problems/count-special-quadruplets/
 *
 * Given a 0-indexed integer array nums, return the number of distinct quadruplets (a, b, c, d) such that:
 *
 * nums[a] + nums[b] + nums[c] == nums[d], and
 * a < b < c < d
 */

public class Solution {

    public int countQuadruplets(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 3; i++) {
            for (int j = i + 1; j < nums.length - 2; j++) {
                for (int k = j + 1; k < nums.length - 1; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        if (nums[i] + nums[j] + nums[k] == nums[l]) ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().countQuadruplets(new int[]{1,2,3,6}) == 1;
        assert new Solution().countQuadruplets(new int[]{3,3,6,4,5}) == 0;
        assert new Solution().countQuadruplets(new int[]{1,1,1,3,5}) == 4;
    }

}
