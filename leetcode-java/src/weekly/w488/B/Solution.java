package weekly.w488.B;

import java.util.ArrayList;
import java.util.List;

/**
 * Q2. Merge Adjacent Equal Elements
 *
 * https://leetcode.cn/contest/weekly-contest-488/problems/merge-adjacent-equal-elements/
 *
 * You are given an integer array nums.
 *
 * You must repeatedly apply the following merge operation until no more changes can be made:
 *
 * If any two adjacent elements are equal, choose the leftmost such adjacent pair
 * in the current array and replace them with a single element equal to their sum.
 *
 * After each merge operation, the array size decreases by 1. Repeat the process
 * on the updated array until no more changes can be made.
 *
 * Return the final array after all possible merge operations.
 */

public class Solution {

    public List<Long> mergeAdjacent(int[] nums) {
        List<Long> ans = new ArrayList<>();
        for (long v : nums) {
            while (!ans.isEmpty() && ans.get(ans.size() - 1) == v) {
                ans.remove(ans.size() - 1); v += v;
            }
            ans.add(v);
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
