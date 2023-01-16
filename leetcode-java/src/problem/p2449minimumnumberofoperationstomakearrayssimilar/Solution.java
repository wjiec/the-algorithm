package problem.p2449minimumnumberofoperationstomakearrayssimilar;

import java.util.Arrays;

/**
 * 2449. Minimum Number of Operations to Make Arrays Similar
 *
 * https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar/description/
 *
 * You are given two positive integer arrays nums and target, of the same length.
 *
 * In one operation, you can choose any two distinct indices i and j where 0 <= i, j < nums.length and:
 *
 * set nums[i] = nums[i] + 2 and
 * set nums[j] = nums[j] - 2.
 *
 * Two arrays are considered to be similar if the frequency of each element is the same.
 *
 * Return the minimum number of operations required to make nums similar to target.
 * The test cases are generated such that nums can always be similar to target.
 */

public class Solution {

    public long makeSimilar(int[] nums, int[] target) {
        sort(nums); sort(target);

        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += Math.abs(nums[i] - target[i]);
        }
        return (ans / 2) / 2;
    }

    private void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 1) {
                nums[i] = -nums[i];
            }
        }
        Arrays.sort(nums);
    }

    public static void main(String[] args) {
        assert new Solution().makeSimilar(new int[]{8,12,6}, new int[]{2,14,10}) == 2;
        assert new Solution().makeSimilar(new int[]{1,2,5}, new int[]{4,1,3}) == 1;
        assert new Solution().makeSimilar(new int[]{1,1,1,1,1}, new int[]{1,1,1,1,1}) == 0;
    }

}
