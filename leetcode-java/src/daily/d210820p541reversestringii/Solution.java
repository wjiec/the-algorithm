package daily.d210820p541reversestringii;

/**
 * 541. Reverse String II
 *
 * https://leetcode-cn.com/problems/reverse-string-ii/
 *
 * Given a string s and an integer k, reverse the first k characters
 * for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left, reverse all of them.
 *
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 */

public class Solution {

    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();

        boolean reverse = true;
        for (int i = 0, l = s.length(); i < l; i += k) {
            int end = Math.min(i + k, l);
            if (reverse) {
                for (int j = end - 1; j >= i; j--) {
                    sb.append(s.charAt(j));
                }
            } else sb.append(s, i, end);
            reverse = !reverse;
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
