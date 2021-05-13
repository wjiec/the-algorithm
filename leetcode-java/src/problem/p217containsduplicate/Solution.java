package problem.p217containsduplicate;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 217. Contains Duplicate
 *
 * https://leetcode-cn.com/problems/contains-duplicate/
 *
 * Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 */

public class Solution {

    public boolean containsDuplicate(int[] nums) {
        Map<Integer, Boolean> ns = new HashMap<>();
        for (var n : nums) {
            if (ns.containsKey(n)) {
                return true;
            }
            ns.put(n, true);
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().containsDuplicate(new int[]{1,2,3,1});
        assert !new Solution().containsDuplicate(new int[]{1,2,3,4});
        assert new Solution().containsDuplicate(new int[]{1,1,1,3,3,4,3,2,4,2});
    }

}
