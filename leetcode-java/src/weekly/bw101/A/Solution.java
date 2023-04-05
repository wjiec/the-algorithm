package weekly.bw101.A;

/**
 * 2605. Form Smallest Number From Two Digit Arrays
 *
 * https://leetcode.cn/contest/biweekly-contest-101/problems/form-smallest-number-from-two-digit-arrays/
 *
 * Given two arrays of unique digits nums1 and nums2, return the smallest
 * number that contains at least one digit from each array.
 */

public class Solution {

    public int minNumber(int[] nums1, int[] nums2) {
        int[] map = new int[10];
        for (var v : nums1) map[v] |= 1;
        for (var v : nums2) map[v] |= 2;

        int a = 0, b = 0;
        for (int i = 0; i < map.length; i++) {
            if (map[i] == 3) return i;
            if (a == 0 && map[i] == 1) a = i;
            if (b == 0 && map[i] == 2) b = i;
        }
        return a < b ? (a * 10 + b) : (b * 10 + a);
    }

    public static void main(String[] args) {
    }

}
