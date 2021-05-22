package problem.p448findallnumbersdisappearedinanarray;

import java.util.ArrayList;
import java.util.List;

/**
 * 448. Find All Numbers Disappeared in an Array
 *
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 *
 * Given an array nums of n integers where nums[i] is in the range [1, n],
 * return an array of all the integers in the range [1, n] that do not appear in nums.
 */

public class Solution {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        int sz = nums.length;
        for (var n : nums) {
            nums[(n - 1) % sz] += sz;
        }

        List<Integer> rs = new ArrayList<>();
        for (int i = 0; i < sz; i++) {
            if (nums[i] <= sz) {
                rs.add(i + 1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().findDisappearedNumbers(new int[]{4,3,2,7,8,2,3,1}).equals(List.of(5, 6));
    }

}
