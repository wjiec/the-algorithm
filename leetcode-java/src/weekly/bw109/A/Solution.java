package weekly.bw109.A;

/**
 * 2784. Check if Array is Good
 *
 * https://leetcode.cn/contest/biweekly-contest-109/problems/check-if-array-is-good/
 *
 * You are given an integer array nums. We consider an array good if it is a permutation of an array base[n].
 *
 * base[n] = [1, 2, ..., n - 1, n, n] (in other words, it is an array of length n + 1 which
 * contains 1 to n - 1 exactly once, plus two occurrences of n). For example, base[1] = [1, 1] and
 * base[3] = [1, 2, 3, 3].
 *
 * Return true if the given array is good, otherwise return false.
 *
 * Note: A permutation of integers represents an arrangement of these numbers.
 */

public class Solution {

    public boolean isGood(int[] nums) {
        if (nums.length == 1) return false;
        int[] set = new int[nums.length + 1];
        for (var v : nums) {
            if (v >= set.length) return false;
            set[v]++;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (set[i] != 1) return false;
        }
        return set[nums.length - 1] == 2;
    }

    public static void main(String[] args) {
    }

}
