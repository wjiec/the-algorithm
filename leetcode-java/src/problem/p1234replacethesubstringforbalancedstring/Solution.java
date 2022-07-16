package problem.p1234replacethesubstringforbalancedstring;

/**
 * 1234. Replace the Substring for Balanced String
 *
 * https://leetcode.cn/problems/replace-the-substring-for-balanced-string/
 *
 * You are given a string s of length n containing only four kinds of characters: 'Q', 'W', 'E', and 'R'.
 *
 * A string is said to be balanced if each of its characters appears n / 4 times where n is the length of the string.
 *
 * Return the minimum length of the substring that can be replaced with any other string of the same
 * length to make s balanced. If s is already balanced, return 0.
 */

public class Solution {

    public int balancedString(String s) {
        int[] map = new int[128];
        for (var c : s.toCharArray()) map[c]++;

        int ans = Integer.MAX_VALUE, avg = s.length() / 4;
        map['Q'] = Math.max(0, map['Q'] - avg);
        map['W'] = Math.max(0, map['W'] - avg);
        map['E'] = Math.max(0, map['E'] - avg);
        map['R'] = Math.max(0, map['R'] - avg);
        if ((map['Q'] | map['W'] | map['E'] | map['R']) == 0) return 0;

        int[] curr = new int[128];
        for (int ll = 0, rr = 0; rr < s.length(); rr++) {
            curr[s.charAt(rr)]++;
            while (ll <= rr && gte(curr, map)) {
                ans = Math.min(ans, rr - ll + 1);
                curr[s.charAt(ll++)]--;
            }
        }

        return ans;
    }

    private boolean gte(int[] a, int[] b) {
        return a['Q'] >= b['Q'] && a['W'] >= b['W'] && a['E'] >= b['E'] && a['R'] >= b['R'];
    }

    public static void main(String[] args) {
        assert new Solution().balancedString("WWEQERQWQWWRWWERQWEQ") == 4;

        assert new Solution().balancedString("QWER") == 0;
        assert new Solution().balancedString("QQWE") == 1;
        assert new Solution().balancedString("QQQW") == 2;
        assert new Solution().balancedString("QQQQ") == 3;
        assert new Solution().balancedString("QQQQQQQQ") == 6;
    }

}
