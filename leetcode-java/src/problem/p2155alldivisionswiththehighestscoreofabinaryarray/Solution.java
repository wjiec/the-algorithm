package problem.p2155alldivisionswiththehighestscoreofabinaryarray;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 2155. All Divisions With the Highest Score of a Binary Array
 *
 * https://leetcode.cn/problems/all-divisions-with-the-highest-score-of-a-binary-array/
 *
 * You are given a 0-indexed binary array nums of length n. nums can be divided at index i (where 0 <= i <= n)
 * into two arrays (possibly empty) numsleft and numsright:
 *
 * numsleft has all the elements of nums between index 0 and i - 1 (inclusive), while numsright has
 * all the elements of nums between index i and n - 1 (inclusive).
 *
 * If i == 0, numsleft is empty, while numsright has all the elements of nums.
 * If i == n, numsleft has all the elements of nums, while numsright is empty.
 *
 * The division score of an index i is the sum of the number of 0's in numsleft and the number of 1's in numsright.
 *
 * Return all distinct indices that have the highest possible division score. You may return the answer in any order.
 */

public class Solution {

    public List<Integer> maxScoreIndices(int[] nums) {
        int ones = 0, n = nums.length;
        for (var v : nums) ones += v;

        int nl = 0, nr = ones, max = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i != 0) {
                nr -= nums[i - 1];
                nl += nums[i - 1] == 0 ? 1 : 0;
            }

            if (nl + nr > max) { max = nl + nr; ans.clear(); ans.add(i); }
            else if (nl + nr == max) ans.add(i);
        }

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().maxScoreIndices(new int[]{0,0,1,0}), List.of(2, 4));
        assert Checker.anyOrder(new Solution().maxScoreIndices(new int[]{0,0,0}), List.of(3));
        assert Checker.anyOrder(new Solution().maxScoreIndices(new int[]{1,1}), List.of(0));
    }

}
