package problem.p541reversestringii;

/**
 * 541. Reverse String II
 *
 * https://leetcode-cn.com/problems/reverse-string-ii/
 *
 * Given a string s and an integer k, reverse the first k characters
 * for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 */

public class Solution {

    public String reverseStr(String s, int k) {
        int sz = s.length();
        if (sz <= k) {
            return new StringBuilder().append(s).reverse().toString();
        }

        StringBuilder sb = new StringBuilder(sz);
        for (int i = 0; i < sz; i += 2 * k) {
            sb.append(new StringBuilder().append(s, i, Math.min(sz, i + k)).reverse().toString());
            if (i + k < sz) {
                sb.append(s, i + k, Math.min(sz, i + 2 * k));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reverseStr("abcdefg", 3).equals("cbadefg");
        assert new Solution().reverseStr("abcdefgh", 3).equals("cbadefhg");
        assert new Solution().reverseStr("abcdefghi", 3).equals("cbadefihg");
        assert new Solution().reverseStr("abcdefg", 2).equals("bacdfeg");
        assert new Solution().reverseStr("abcd", 2).equals("bacd");
    }

}
