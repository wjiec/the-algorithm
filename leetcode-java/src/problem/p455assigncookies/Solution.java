package problem.p455assigncookies;

import java.util.Arrays;

/**
 * 455. Assign Cookies
 *
 * https://leetcode-cn.com/problems/assign-cookies/
 *
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 *
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with;
 * and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i,
 * and the child i will be content.
 * Your goal is to maximize the number of your content children and output the maximum number.
 */

public class Solution {

    public int findContentChildren(int[] g, int[] s) {
        int cnt = 0, sz1 = g.length, sz2 = s.length;
        if (sz1 == 0 || sz2 == 0) {
            return 0;
        }

        Arrays.sort(g);
        Arrays.sort(s);

        for (int i = 0, j = 0; i < sz1 && j < sz2; i++) {
            while (j < sz2 && s[j] < g[i]) j++;
            if (j < sz2) {
                cnt++;
                j++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        assert new Solution().findContentChildren(new int[]{1,2,3}, new int[]{1,1}) == 1;
        assert new Solution().findContentChildren(new int[]{1,2}, new int[]{1,2,3}) == 2;
        assert new Solution().findContentChildren(new int[]{1,2,3}, new int[]{3}) == 1;
    }

}
