package problem.p944deletecolumnstomakesorted;

/**
 * 944. Delete Columns to Make Sorted
 *
 * https://leetcode-cn.com/problems/delete-columns-to-make-sorted/
 *
 * You are given an array of n strings strs, all of the same length.
 *
 * The strings can be arranged such that there is one on each line, making a grid.
 * For example, strs = ["abc", "bce", "cae"] can be arranged as:
 *
 * abc
 * bce
 * cae
 *
 * You want to delete the columns that are not sorted lexicographically.
 * In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted
 * while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
 *
 * Return the number of columns that you will delete.
 */

public class Solution {

    public int minDeletionSize(String[] strs) {
        int ans = 0, l = strs.length, n = strs[0].length();
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < l; j++) {
                if (strs[j].charAt(i) < strs[j - 1].charAt(i)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().minDeletionSize(new String[]{"cba","daf","ghi"}) == 1;
        assert new Solution().minDeletionSize(new String[]{"a","b"}) == 0;
        assert new Solution().minDeletionSize(new String[]{"zyx","wvu","tsr"}) == 3;
    }

}
