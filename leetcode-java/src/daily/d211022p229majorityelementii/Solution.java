package daily.d211022p229majorityelementii;

import java.util.ArrayList;
import java.util.List;

/**
 * 229. Majority Element II
 *
 * https://leetcode-cn.com/problems/majority-element-ii/
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 */

public class Solution {

    public List<Integer> majorityElement(int[] nums) {
        int el1 = 0, el2 = 0, vo1 = 0, vo2 = 0;
        for (var num : nums) {
            if (vo1 > 0 && num == el1) vo1++;
            else if (vo2 > 0 && num == el2) vo2++;
            else if (vo1 == 0) { el1 = num; vo1++; }
            else if (vo2 == 0) { el2 = num; vo2++; }
            else { vo1--; vo2--; }
        }

        int c1 = 0, c2 = 0;
        for (var num : nums) {
            if (vo1 > 0 && num == el1) c1++;
            if (vo2 > 0 && num == el2) c2++;
        }

        List<Integer> ans = new ArrayList<>();
        if (vo1 > 0 && c1 > nums.length / 3) ans.add(el1);
        if (vo2 > 0 && c2 > nums.length / 3) ans.add(el2);
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().majorityElement(new int[]{3,2,3}).equals(List.of(3));
        assert new Solution().majorityElement(new int[]{1}).equals(List.of(1));
        assert new Solution().majorityElement(new int[]{1,2}).equals(List.of(1,2));
    }

}
