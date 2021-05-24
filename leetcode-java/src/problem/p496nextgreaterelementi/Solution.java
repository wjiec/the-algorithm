package problem.p496nextgreaterelementi;

import common.Checker;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 496. Next Greater Element I
 *
 * https://leetcode-cn.com/problems/next-greater-element-i/
 *
 * You are given two integer arrays nums1 and nums2 both of unique elements, where nums1 is a subset of nums2.
 *
 * Find all the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
 * If it does not exist, return -1 for this number.
 */

public class Solution {

    // @TODO
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int sz1 = nums1.length, sz2 = nums2.length;

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int i = 0; i < sz2; i++) {
            while (!stack.isEmpty() && stack.peekLast() < nums2[i]) {
                mapping.put(stack.removeLast(), nums2[i]);
            }
            stack.addLast(nums2[i]);
        }

        int[] rs = new int[sz1];
        for (int i = 0; i < sz1; i++) {
            rs[i] = mapping.getOrDefault(nums1[i], -1);
        }

        return rs;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().nextGreaterElement(new int[]{4,1,2}, new int[]{1,3,4,2}), new int[]{-1,3,-1});
        assert Checker.check(new Solution().nextGreaterElement(new int[]{2,4}, new int[]{1,2,3,4}), new int[]{3,-1});
    }

}
