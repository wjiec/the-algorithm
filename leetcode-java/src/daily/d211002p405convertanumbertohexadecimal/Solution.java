package daily.d211002p405convertanumbertohexadecimal;

/**
 * 405. Convert a Number to Hexadecimal
 *
 * https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/
 *
 * Given an integer num, return a string representing its hexadecimal representation.
 *
 * For negative integers, two’s complement method is used.
 *
 * All the letters in the answer string should be lowercase characters,
 * and there should not be any leading zeros in the answer except for the zero itself.
 *
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 */

public class Solution {

    private final char[] chars = new char[]{
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    public String toHex(int num) {
        if (num == 0) return "0";
        long val = num > 0 ? num : (1L << 32) + num;
        StringBuilder sb = new StringBuilder();
        for (; val != 0; val /= 16) {
            sb.append(chars[(int) (val % 16)]);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().toHex(26).equals("1a");
        assert new Solution().toHex(27).equals("1b");
        assert new Solution().toHex(-1).equals("ffffffff");
    }

}
