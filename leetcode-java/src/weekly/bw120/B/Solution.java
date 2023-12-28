package weekly.bw120.B;

import java.util.Arrays;

/**
 * 2971. Find Polygon With the Largest Perimeter
 *
 * https://leetcode.cn/contest/biweekly-contest-120/problems/find-polygon-with-the-largest-perimeter/
 *
 * You are given an array of positive integers nums of length n.
 *
 * A polygon is a closed plane figure that has at least 3 sides. The longest
 * side of a polygon is smaller than the sum of its other sides.
 *
 * Conversely, if you have k (k >= 3) positive real numbers a1, a2, a3, ..., ak
 * where a1 <= a2 <= a3 <= ... <= ak and a1 + a2 + a3 + ... + ak-1 > ak, then
 * there always exists a polygon with k sides whose lengths are a1, a2, a3, ..., ak.
 *
 * The perimeter of a polygon is the sum of lengths of its sides.
 *
 * Return the largest possible perimeter of a polygon whose sides can be formed
 * from nums, or -1 if it is not possible to create a polygon.
 */

public class Solution {

    public long largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        long[] sum = new long[nums.length];
        for (long i = 0, s = 0; i < nums.length; i++) {
            sum[(int) i] = s += nums[(int) i];
        }

        long ans = -1;
        for (int i = nums.length - 1; i > 1; i--) {
            if (nums[i] < ans) continue;
            long other = sum[i - 1];
            if (other > nums[i]) ans = Math.max(ans, other + nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
