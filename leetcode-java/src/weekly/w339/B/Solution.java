package weekly.w339.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 2610. Convert an Array Into a 2D Array With Conditions
 *
 * https://leetcode.cn/contest/weekly-contest-339/problems/convert-an-array-into-a-2d-array-with-conditions/
 *
 * You are given an integer array nums. You need to create a 2D array from nums satisfying the following conditions:
 *
 * The 2D array should contain only the elements of the array nums.
 * Each row in the 2D array contains distinct integers.
 * The number of rows in the 2D array should be minimal.
 * Return the resulting array. If there are multiple answers, return any of them.
 *
 * Note that the 2D array can have a different number of elements on each row.
 */

public class Solution {

    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int[] count = new int[nums.length + 1];
        for (var v : nums) {
            int c = count[v]++;
            if (c == ans.size()) ans.add(new ArrayList<>());
            ans.get(c).add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
