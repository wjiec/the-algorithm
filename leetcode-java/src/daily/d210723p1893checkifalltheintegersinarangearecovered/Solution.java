package daily.d210723p1893checkifalltheintegersinarangearecovered;

/**
 * 1893. Check if All the Integers in a Range Are Covered
 *
 * https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered/
 *
 * You are given a 2D integer array ranges and two integers left and right.
 * Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.
 *
 * Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges.
 * Return false otherwise.
 *
 * An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.
 */

public class Solution {

    public boolean isCovered(int[][] ranges, int left, int right) {
        boolean[] valid = new boolean[51];
        for (var range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) valid[i] = true;
        }

        for (int i = left; i <= right; i++) {
            if (!valid[i]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().isCovered(new int[][]{
            {1,2}, {3,4}, {5,6}
        }, 2, 5);

        assert !new Solution().isCovered(new int[][]{
            {1,10}, {10,20}
        }, 21, 21);
    }

}
