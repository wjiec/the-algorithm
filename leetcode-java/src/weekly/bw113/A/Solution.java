package weekly.bw113.A;

import java.util.List;

/**
 * 2855. Minimum Right Shifts to Sort the Array
 *
 * https://leetcode.cn/contest/biweekly-contest-113/problems/minimum-right-shifts-to-sort-the-array/
 *
 * You are given a 0-indexed array nums of length n containing distinct positive integers.
 *
 * Return the minimum number of right shifts required to sort nums and -1 if this is not possible.
 *
 * A right shift is defined as shifting the element at index i to index (i + 1) % n, for all indices.
 */

public class Solution {

    public int minimumRightShifts(List<Integer> nums) {
        if (sorted(nums)) return 0;
        for (int i = 1; i < nums.size(); i++) {
            nums.add(0, nums.remove(nums.size() - 1));
            if (sorted(nums)) return i;
        }
        return -1;
    }

    private boolean sorted(List<Integer> nums) {
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i) < nums.get(i - 1)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }

}
