package weekly.w354.C;

import ability.Ability;

import java.util.List;
import java.util.Set;

/**
 * 2780. Minimum Index of a Valid Split
 *
 * https://leetcode.cn/contest/weekly-contest-354/problems/minimum-index-of-a-valid-split/
 *
 * An element x of an integer array arr of length m is dominant if freq(x) * 2 > m, where freq(x) is the
 * number of occurrences of x in arr. Note that this definition implies that arr can have at most
 * one dominant element.
 *
 * You are given a 0-indexed integer array nums of length n with one dominant element.
 *
 * You can split nums at an index i into two arrays nums[0, ..., i] and nums[i + 1, ..., n - 1], but the
 * split is only valid if:
 *
 * 0 <= i < n - 1
 * nums[0, ..., i], and nums[i + 1, ..., n - 1] have the same dominant element.
 * Here, nums[i, ..., j] denotes the subarray of nums starting at index i and ending at index j, both
 * ends being inclusive. Particularly, if j < i then nums[i, ..., j] denotes an empty subarray.
 *
 * Return the minimum index of a valid split. If no valid split exists, return -1.
 */

public class Solution {

    public int minimumIndex(List<Integer> nums) {
        Ability.Frequency<Integer> left = new Ability.Frequency<>();
        Ability.Frequency<Integer> right = new Ability.Frequency<>();
        for (var v : nums) right.push(v);

        for (int i = 0; i < nums.size() - 1; i++) {
            left.push(nums.get(i));
            right.remove(nums.get(i));

            Set<Integer> ls = left.lastSet(), rs = right.lastSet();
            if (ls.size() == 1 && rs.size() == 1) {
                if (ls.containsAll(rs)) return nums.get(i);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }

}
