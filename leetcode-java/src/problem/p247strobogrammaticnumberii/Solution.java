package problem.p247strobogrammaticnumberii;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 247. Strobogrammatic Number II
 *
 * https://leetcode-cn.com/problems/strobogrammatic-number-ii/
 *
 * Given an integer n, return all the strobogrammatic numbers that are of length n.
 * You may return the answer in any order.
 *
 * A strobogrammatic number is a number that looks the same
 * when rotated 180 degrees (looked at upside down).
 */

public class Solution {

    public List<String> findStrobogrammatic(int n) {
        if (n == 1) return List.of("0", "1", "8");

        List<String> ans = new ArrayList<>();
        dfs(ans, new char[n], 0, n);
        return ans;
    }

    private void dfs(List<String> ans, char[] chars, int i, int n) {
        if (i == (n + 1) / 2) {
            for (int l = 0, r = n - 1; l < r; l++, r--) {
                switch (chars[l]) {
                    case '0', '1', '8' -> chars[r] = chars[l];
                    case '6' -> chars[r] = '9';
                    case '9' -> chars[r] = '6';
                }
            }
            ans.add(new String(chars));
            return;
        }

        char[] candidates;
        if (i == 0) candidates = new char[]{'1', '6', '8', '9'};
        else if (n % 2 == 1 && i == n / 2) candidates = new char[]{'0', '1', '8'};
        else candidates = new char[]{'0', '1', '6', '8', '9'};

        for (var c : candidates) {
            chars[i] = c;
            dfs(ans, chars, i + 1, n);
        }
    }

    public static void main(String[] args) {
        assert Checker.anyOrder(new Solution().findStrobogrammatic(1), List.of("0", "1", "8"));
        assert Checker.anyOrder(new Solution().findStrobogrammatic(2), List.of("11", "69", "88", "96"));
        assert Checker.anyOrder(new Solution().findStrobogrammatic(3), List.of(
            "101","111","181","609","619","689","808","818","888","906","916","986"
        ));
    }

}
