package problem.p1271hexspeak;

/**
 * 1271. Hexspeak
 *
 * https://leetcode-cn.com/problems/hexspeak/
 *
 * A decimal number can be converted to its Hexspeak representation by first converting it
 * to an uppercase hexadecimal string, then replacing all occurrences of the digit '0' with
 * the letter 'O', and the digit '1' with the letter 'I'. Such a representation is valid if and
 * only if it consists only of the letters in the set {'A', 'B', 'C', 'D', 'E', 'F', 'I', 'O'}.
 *
 * Given a string num representing a decimal integer n, return the Hexspeak
 * representation of n if it is valid, otherwise return "ERROR".
 */

public class Solution {

    public String toHexspeak(String num) {
        StringBuilder sb = new StringBuilder();
        String hex = Long.toHexString(Long.parseLong(num));
        for (var c : hex.toCharArray()) {
            switch (c) {
                case '1' -> sb.append('I');
                case '0' -> sb.append('O');
                case 'a' -> sb.append('A');
                case 'b' -> sb.append('B');
                case 'c' -> sb.append('C');
                case 'd' -> sb.append('D');
                case 'e' -> sb.append('E');
                case 'f' -> sb.append('F');
                default -> {
                    return "ERROR";
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().toHexspeak("257").equals("IOI");
        assert new Solution().toHexspeak("3").equals("ERROR");
    }

}
