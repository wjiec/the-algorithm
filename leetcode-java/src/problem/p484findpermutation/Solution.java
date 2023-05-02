package problem.p484findpermutation;

import common.PrettyPrinter;

/**
 * 484. Find Permutation
 *
 * https://leetcode.cn/problems/find-permutation/description/
 *
 * A permutation perm of n integers of all the integers in the range [1, n] can be
 * represented as a string s of length n - 1 where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 * Given a string s, reconstruct the lexicographically smallest permutation perm and return it.
 */

public class Solution {

    public int[] findPermutation(String s) {
        int[] ans = new int[s.length() + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = i + 1;
        }

        for (int i = 1; i <= s.length(); i++) {
            int l = i;
            while (i <= s.length() && s.charAt(i - 1) == 'D') i++;
            reverse(ans, l - 1, i - 1);
        }
        return ans;
    }

    private void reverse(int[] array, int l, int r) {
        for (; l < r; l++, r--) {
            int temp = array[l];
            array[l] = array[r];
            array[r] = temp;
        }
    }

    public static void main(String[] args) {
        PrettyPrinter.println(new Solution().findPermutation("ID"));
        PrettyPrinter.println(new Solution().findPermutation("D"));
        PrettyPrinter.println(new Solution().findPermutation("DDDIII"));

        PrettyPrinter.println(new Solution().findPermutation("I"));
        PrettyPrinter.println(new Solution().findPermutation("DI"));
    }

}
