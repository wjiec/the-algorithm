package problem.p1748sumofuniqueelements;

/**
 * 1748. Sum of Unique Elements
 *
 * https://leetcode-cn.com/problems/sum-of-unique-elements/
 *
 * You are given an integer array nums. The unique elements of an array
 * are the elements that appear exactly once in the array.
 *
 * Return the sum of all the unique elements of nums.
 */

public class Solution {

    public int sumOfUnique(int[] nums) {
        Boolean[] map = new Boolean[101];
        for (var n : nums) {
            if (map[n] == null) map[n] = Boolean.FALSE;
            else map[n] = Boolean.TRUE;
        }

        int ans = 0;
        for (int i = 1; i < map.length; i++) {
            if (map[i] == Boolean.FALSE) ans += i;
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().sumOfUnique(new int[]{1,2,3,2}) == 4;
        assert new Solution().sumOfUnique(new int[]{1,1,1,1,1}) == 0;
        assert new Solution().sumOfUnique(new int[]{1,2,3,4,5}) == 15;
    }

}
