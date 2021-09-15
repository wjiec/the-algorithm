package problem.p1389createtargetarrayinthegivenorder;

import common.Checker;

import java.util.LinkedList;
import java.util.List;

/**
 * 1389. Create Target Array in the Given Order
 *
 * https://leetcode-cn.com/problems/create-target-array-in-the-given-order/
 *
 * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
 *
 * Initially target array is empty.
 * From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
 * Repeat the previous step until there are no elements to read in nums and index.
 * Return the target array.
 *
 * It is guaranteed that the insertion operations will be valid.
 */

public class Solution {

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }

        int[] ans = new int[nums.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().createTargetArray(new int[]{0,1,2,3,4}, new int[]{0,1,2,2,1}), new int[]{0,4,1,3,2});
        assert Checker.check(new Solution().createTargetArray(new int[]{1,2,3,4,0}, new int[]{0,1,2,3,0}), new int[]{0,1,2,3,4});
    }

}
