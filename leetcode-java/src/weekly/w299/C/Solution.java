package weekly.w299.C;

/**
 * 5229. Maximum Score Of Spliced Array
 *
 * https://leetcode.cn/contest/weekly-contest-299/problems/maximum-score-of-spliced-array/
 *
 * You are given two 0-indexed integer arrays nums1 and nums2, both of length n.
 *
 * You can choose two integers left and right where 0 <= left <= right < n and swap the
 * subarray nums1[left...right] with the subarray nums2[left...right].
 *
 * For example, if nums1 = [1,2,3,4,5] and nums2 = [11,12,13,14,15] and you choose left = 1
 * and right = 2, nums1 becomes [1,12,13,4,5] and nums2 becomes [11,2,3,14,15].
 *
 * You may choose to apply the mentioned operation once or not do anything.
 *
 * The score of the arrays is the maximum of sum(nums1) and sum(nums2), where sum(arr) is
 * the sum of all the elements in the array arr.
 *
 * Return the maximum possible score.
 *
 * A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes
 * the subarray that contains the elements of nums between indices left and right (inclusive).
 */

public class Solution {

    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, n = nums1.length;
        for (var x : nums1) s1 += x;
        for (var x : nums2) s2 += x;

        int[] diff = new int[n];
        for (int i = 0; i < n; i++) diff[i] = nums1[i] - nums2[i];

        int sum1 = 0, min1 = 0, max1 = 0;
        for (int i = 0; i < n; i++) {
            sum1 += diff[i];
            max1 = Math.max(sum1 - min1, max1);
            min1 = Math.min(min1, sum1);
        }

        int sum2 = 0, min2 = 0, max2 = 0;
        for (int i = 0; i < n; i++) {
            sum2 += diff[i];
            min2 = Math.min(sum2 - max2, min2);
            max2 = Math.max(max2, sum2);
        }
        return Math.max(s2 + max1, s1 - min2);
    }

    public static void main(String[] args) {
        assert new Solution().maximumsSplicedArray(new int[]{60,60,60}, new int[]{10,90,10}) == 210;
        assert new Solution().maximumsSplicedArray(new int[]{20,40,20,70,30}, new int[]{50,20,50,40,20}) == 220;
    }

}
