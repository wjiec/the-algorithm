package weekly.bw74.p0dividearrayintoequalpairs;

import java.util.HashMap;
import java.util.Map;

/**
 * 6020. Divide Array Into Equal Pairs
 *
 * https://leetcode-cn.com/problems/divide-array-into-equal-pairs/
 *
 * You are given an integer array nums consisting of 2 * n integers.
 *
 * You need to divide nums into n pairs such that:
 *
 * Each element belongs to exactly one pair.
 * The elements present in a pair are equal.
 * Return true if nums can be divided into n pairs, otherwise return false.
 */

public class Solution {

    public boolean divideArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (var n : nums) map.merge(n, 1, Integer::sum);

        for (var c : map.values()) {
            if (c % 2 != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().divideArray(new int[]{3,2,3,2,2,2});
        assert !new Solution().divideArray(new int[]{1,2,3,4});
    }

}
