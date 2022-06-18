package problem.p1023camelcasematching;

import java.util.ArrayList;
import java.util.List;

/**
 * 1023. Camelcase Matching
 *
 * https://leetcode.cn/problems/camelcase-matching/
 *
 * Given an array of strings queries and a string pattern, return a boolean array answer where answer[i]
 * is true if queries[i] matches pattern, and false otherwise.
 *
 * A query word queries[i] matches pattern if you can insert lowercase English letters pattern so
 * that it equals the query. You may insert each character at any position
 * and you may not insert any characters.
 */

public class Solution {

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        for (var query : queries) {
            ans.add(isMatch(query, pattern));
        }
        System.out.println(ans);
        return ans;
    }

    private boolean isMatch(String a, String b) {
        int m = a.length(), n = b.length(), i = 0, j = 0;
        while (i < m || j < n) {
            if (i < m && j < n && a.charAt(i) == b.charAt(j)) { i++; j++; }
            else if (i < m && Character.isLowerCase(a.charAt(i))) i++;
            else return false;
        }
        return i == m && j == n;
    }

    public static void main(String[] args) {
        assert new Solution().camelMatch(new String[]{
            "mifeqvzphnrv","mieqxvrvhnrv","mhieqovhnryv","mieqekvhnrpv","miueqrvfhnrv","mieqpvhzntrv","gmimeqvphnrv","mieqvhqyunrv"
        }, "mieqvhnrv").equals(List.of(true,true,true,true,true,true,true,true));

        assert new Solution().camelMatch(new String[]{
            "FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"
        }, "FB").equals(List.of(true,false,true,true,false));

        assert new Solution().camelMatch(new String[]{
            "FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"
        }, "FoBa").equals(List.of(true,false,true,false,false));

        assert new Solution().camelMatch(new String[]{
            "FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"
        }, "FoBaT").equals(List.of(false,true,false,false,false));
    }

}
