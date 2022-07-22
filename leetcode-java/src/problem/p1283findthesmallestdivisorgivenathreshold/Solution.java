package problem.p1283findthesmallestdivisorgivenathreshold;

/**
 * 1283. Find the Smallest Divisor Given a Threshold
 *
 * https://leetcode.cn/problems/find-the-smallest-divisor-given-a-threshold/
 *
 * Given an array of integers nums and an integer threshold, we will choose a positive integer
 * divisor, divide all the array by it, and sum the division's result. Find the smallest divisor
 * such that the result mentioned above is less than or equal to threshold.
 *
 * Each result of the division is rounded to the nearest integer greater than or
 * equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
 *
 * The test cases are generated so that there will be an answer.
 */

public class Solution {

    public int smallestDivisor(int[] nums, int threshold) {
        // l除出来是大的, r除出来是小的
        int l = 1, r = 0, ans = 1;
        for (var n : nums) if (n > r) r = n;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int sum = divSum(nums, mid);
            // 如果sum大于阈值, 则需要增大l
            if (sum <= threshold) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }

    private int divSum(int[] nums, int d) {
        int s = 0;
        for (var n : nums) s += (n + d - 1) / d;
        return s;
    }

    public static void main(String[] args) {
        assert new Solution().smallestDivisor(new int[]{1,2,5,9}, 6) == 5;
        assert new Solution().smallestDivisor(new int[]{2,3,5,7,11}, 11) == 3;
        assert new Solution().smallestDivisor(new int[]{19}, 5) == 4;
    }

}
