package daily.d210804p611validtrianglenumber;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number
 *
 * https://leetcode-cn.com/problems/valid-triangle-number/
 *
 * Given an integer array nums, return the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 */

public class Solution {

    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);

        int ans = 0, l = nums.length;
        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                int left = j + 1, right = l - 1, k = j;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (nums[mid] < nums[i] + nums[j]) {
                        k = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }

                ans += k - j;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().triangleNumber(new int[]{2,2,3,4}) == 3;
        assert new Solution().triangleNumber(new int[]{4,2,3,4}) == 4;
    }

}
