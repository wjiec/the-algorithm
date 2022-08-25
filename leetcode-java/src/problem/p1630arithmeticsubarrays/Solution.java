package problem.p1630arithmeticsubarrays;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1630. Arithmetic Subarrays
 *
 * https://leetcode.cn/problems/arithmetic-subarrays/
 *
 * A sequence of numbers is called arithmetic if it consists of at least two elements, and the
 * difference between every two consecutive elements is the same.
 *
 * More formally, a sequence s is arithmetic if and only if s[i+1] - s[i] == s[1] - s[0] for all valid i.
 * For example, these are arithmetic sequences:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 *
 * The following sequence is not arithmetic:
 * 1, 1, 2, 5, 7
 *
 * You are given an array of n integers, nums, and two arrays of m integers each, l and r, representing
 * the m range queries, where the ith query is the range [l[i], r[i]]. All the arrays are 0-indexed.
 *
 * Return a list of boolean elements answer, where answer[i] is true if the
 * subarray nums[l[i]], nums[l[i]+1], ... , nums[r[i]] can be rearranged to
 * form an arithmetic sequence, and false otherwise.
 */

public class Solution {

    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int a1 = Integer.MAX_VALUE, an = Integer.MIN_VALUE;
            for (int j = l[i]; j <= r[i]; j++) {
                a1 = Math.min(a1, nums[j]);
                an = Math.max(an, nums[j]);
            }

            int n = r[i] - l[i] + 1, d = (an - a1) / (n - 1);
            if (a1 == an || d == 0) { ans.add(a1 == an && d == 0); continue; }

            boolean ok = true;
            boolean[] uniq = new boolean[n];
            for (int j = l[i]; j <= r[i]; j++) {
                int ax = nums[j] - a1, x = ax / d;
                if (ax % d != 0 || x >= n || uniq[x]) {
                    ok = false; break;
                }
                uniq[x] = true;
            }
            ans.add(ok);
        }
        return ans;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().checkArithmeticSubarrays(
            new int[]{-3,-6,-8,-4,-2,-8,-6,0,0,0,0},
            new int[]{5,4,3,2,4,7,6,1,7},
            new int[]{6,5,6,3,7,10,7,4,10}
        ), List.of(true,true,true,true,false,true,true,true,true));

        assert Checker.check(new Solution().checkArithmeticSubarrays(
            new int[]{4,6,5,9,3,7}, new int[]{0,0,2}, new int[]{2,3,5}
        ), List.of(true,false,true));

        assert Checker.check(new Solution().checkArithmeticSubarrays(
            new int[]{-12,-9,-3,-12,-6,15,20,-25,-20,-15,-10},
            new int[]{0,1,6,4,8,7},
            new int[]{4,4,9,7,9,10}
        ), List.of(false,true,false,false,true,true));
    }

}
