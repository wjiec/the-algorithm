package daily.d211026p496nextgreaterelementi;

import common.Checker;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 496. Next Greater Element I
 *
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * The next greater element of some element x in an array is the first greater element
 * that is to the right of x in the same array.
 *
 * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
 *
 * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and
 * determine the next greater element of nums2[j] in nums2.
 *
 * If there is no next greater element, then the answer for this query is -1.
 *
 * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
 */

public class Solution {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }

            map.put(nums2[i], stack.isEmpty() ? -1 : stack.peek());
            stack.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2}), new int[]{-1,3,-1});
        assert Checker.check(new Solution().nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4}), new int[]{3,-1});
    }

}
