package problem.p942distringmatch;

import common.Checker;

/**
 * 942. DI String Match
 *
 * https://leetcode-cn.com/problems/di-string-match/
 *
 * A permutation perm of n + 1 integers of all the integers in the
 * range [0, n] can be represented as a string s of length n where:
 *
 * s[i] == 'I' if perm[i] < perm[i + 1], and
 * s[i] == 'D' if perm[i] > perm[i + 1].
 *
 * Given a string s, reconstruct the permutation perm and return it.
 * If there are multiple valid permutations perm, return any of them.
 */

public class Solution {

    public int[] diStringMatch(String s) {
        int n = s.length(), l = 0, r = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') ans[i] = l++;
            else ans[i] = r--;
        }
        ans[n] = l;

        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().diStringMatch("IDID"), new int[]{0,4,1,3,2});
        assert Checker.check(new Solution().diStringMatch("III"), new int[]{0,1,2,3});
        assert Checker.check(new Solution().diStringMatch("DDI"), new int[]{3,2,0,1});
    }

}
