package problem.p1985findthekthlargestintegerinthearray;

import java.util.Arrays;

/**
 * 1985. Find the Kth Largest Integer in the Array
 *
 * https://leetcode.cn/problems/find-the-kth-largest-integer-in-the-array/
 *
 * You are given an array of strings nums and an integer k. Each string in nums
 * represents an integer without leading zeros.
 *
 * Return the string that represents the kth largest integer in nums.
 *
 * Note: Duplicate numbers should be counted distinctly. For example, if
 * nums is ["1","2","2"], "2" is the first largest integer, "2" is the
 * second-largest integer, and "1" is the third-largest integer.
 */

public class Solution {

    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (a, b) -> a.length() == b.length() ? b.compareTo(a) : b.length() - a.length());
        return nums[k - 1];
    }

    public static void main(String[] args) {
        assert new Solution().kthLargestNumber(new String[]{"3","6","7","10"}, 4).equals("3");
        assert new Solution().kthLargestNumber(new String[]{"2","21","12","1"}, 3).equals("2");
        assert new Solution().kthLargestNumber(new String[]{"0","0"}, 2).equals("0");
    }

}
