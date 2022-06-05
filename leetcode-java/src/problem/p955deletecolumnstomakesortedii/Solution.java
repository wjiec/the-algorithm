package problem.p955deletecolumnstomakesortedii;

import java.util.Arrays;

/**
 * 955. Delete Columns to Make Sorted II
 *
 * https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/
 *
 * You are given an array of n strings strs, all of the same length.
 *
 * We may choose any deletion indices, and we delete all the characters in those indices for each string.
 *
 * For example, if we have strs = ["abcdef","uvwxyz"] and deletion indices {0, 2, 3},
 * then the final array after deletions is ["bef", "vyz"].
 *
 * Suppose we chose a set of deletion indices answer such that after deletions, the final array has its
 * elements in lexicographic order (i.e., strs[0] <= strs[1] <= strs[2] <= ... <= strs[n - 1]).
 * Return the minimum possible value of answer.length.
 */

public class Solution {

    public int minDeletionSize(String[] strs) {
        int ans = 0, n = strs[0].length();
        String[] curr = new String[strs.length];
        for (int i = 0; i < n; i++) {
            String[] next = Arrays.copyOf(curr, curr.length);
            for (int j = 0; j < curr.length; j++) {
                next[j] += strs[i].charAt(j);
            }
            if (sorted(next)) curr = next;
            else ans++;
        }
        return ans;
    }

    private boolean sorted(String[] strs) {
        for (int i = 1, n = strs.length; i < n; i++) {
            if (strs[i].compareTo(strs[i - 1]) < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        assert new Solution().minDeletionSize(new String[]{"ca","bb","ac"}) == 1;
        assert new Solution().minDeletionSize(new String[]{"xc","yb","za"}) == 0;
        assert new Solution().minDeletionSize(new String[]{"zyx","wvu","tsr"}) == 3;
    }

}
