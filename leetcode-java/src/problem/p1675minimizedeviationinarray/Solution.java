package problem.p1675minimizedeviationinarray;

import java.util.TreeSet;

/**
 * 1675. Minimize Deviation in Array
 *
 * https://leetcode.cn/problems/minimize-deviation-in-array
 *
 * You are given an array nums of n positive integers.
 *
 * You can perform two types of operations on any element of the array any number of times:
 *
 * If the element is even, divide it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation
 * on the last element, and the array will be [1,2,3,2].
 *
 * If the element is odd, multiply it by 2.
 * For example, if the array is [1,2,3,4], then you can do this operation
 * on the first element, and the array will be [2,2,3,4].
 *
 * The deviation of the array is the maximum difference between any two elements in the array.
 *
 * Return the minimum deviation the array can have after performing some number of operations.
 */

public class Solution {

    public int minimumDeviation(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (var v : nums) set.add(v % 2 == 0 ? v : (v * 2));

        int ans = set.last() - set.first();
        while (set.last() != 0 && set.last() % 2 == 0) {
            int max = set.last();
            set.remove(max); set.add(max / 2);
            ans = Math.min(ans, set.last() - set.first());
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minimumDeviation(new int[]{1,2,3,4}) == 1;
        assert new Solution().minimumDeviation(new int[]{4,1,5,20,3}) == 3;
        assert new Solution().minimumDeviation(new int[]{2,10,8}) == 3;
    }

}
