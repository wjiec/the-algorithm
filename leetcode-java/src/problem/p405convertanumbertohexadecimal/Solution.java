package problem.p405convertanumbertohexadecimal;

/**
 * 405. Convert a Number to Hexadecimal
 *
 * Given an integer num, return a string representing its hexadecimal representation.
 * For negative integers, two’s complement method is used.
 *
 * All the letters in the answer string should be lowercase characters,
 * and there should not be any leading zeros in the answer except for the zero itself.
 *
 * Note: You are not allowed to use any built-in library method to directly solve this problem.
 */

public class Solution {

    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

        while (sb.length() < 8 && num != 0) {
            sb.append(chars[num & 0xf]);
            num >>= 4;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().toHex(1).equals("1");
        assert new Solution().toHex(26).equals("1a");
        assert new Solution().toHex(-1).equals("ffffffff");
    }

}
