package problem.p1758minimumchangestomakealternatingbinarystring;

/**
 * 1758. Minimum Changes To Make Alternating Binary String
 *
 * https://leetcode-cn.com/problems/minimum-changes-to-make-alternating-binary-string/
 *
 * You are given a string s consisting only of the characters '0' and '1'.
 *
 * In one operation, you can change any '0' to '1' or vice versa.
 *
 * The string is called alternating if no two adjacent characters are equal.
 *
 * For example, the string "010" is alternating, while the string "0100" is not.
 *
 * Return the minimum number of operations needed to make s alternating.
 */

public class Solution {

    public int minOperations(String s) {
        char[] chars = s.toCharArray();
        return Math.min(minOperations(chars, '0'), minOperations(chars, '1'));
    }

    private int minOperations(char[] chars, char first) {
        int count = 0, val = first == '0' ? 0 : 1;
        for (var c : chars) {
            if (c - '0' != (val % 2)) count++;
            val++;
        }
        return count;
    }

    public static void main(String[] args) {
        assert new Solution().minOperations("0100") == 1;
        assert new Solution().minOperations("10") == 0;
        assert new Solution().minOperations("1111") == 2;
    }

}
