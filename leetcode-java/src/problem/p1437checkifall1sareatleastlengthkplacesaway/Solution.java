package problem.p1437checkifall1sareatleastlengthkplacesaway;

/**
 * 1437. Check If All 1's Are at Least Length K Places Away
 *
 * https://leetcode-cn.com/problems/check-if-all-1s-are-at-least-length-k-places-away/
 *
 * Given an array nums of 0s and 1s and an integer k,
 * return True if all 1's are at least k places away from each other,
 * otherwise return False.
 */

public class Solution {

    public boolean kLengthApart(int[] nums, int k) {
        int l = nums.length, prev = -1;
        for (int i = 0; i < l; i++) {
            if (nums[i] == 1) {
                if (prev != -1) {
                    if (i - prev - 1 < k)
                        return false;
                }
                prev = i;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().kLengthApart(new int[]{1,0,0,0,1,0,0,1}, 2);
        assert !new Solution().kLengthApart(new int[]{1,0,0,1,0,1}, 2);
        assert new Solution().kLengthApart(new int[]{1,1,1,1,1}, 0);
        assert new Solution().kLengthApart(new int[]{0,1,0,1}, 1);
    }

}
