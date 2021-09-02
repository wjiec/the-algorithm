package problem.p1309decryptstringfromalphabettointegermapping;

/**
 * 1309. Decrypt String from Alphabet to Integer Mapping
 *
 * https://leetcode-cn.com/problems/decrypt-string-from-alphabet-to-integer-mapping/
 *
 * Given a string s formed by digits ('0' - '9') and '#' .
 *
 * We want to map s to English lowercase characters as follows:
 *
 * Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * Characters ('j' to 'z') are represented by ('10#' to '26#') respectively. 
 * Return the string formed after mapping.
 *
 * It's guaranteed that a unique mapping will always exist.
 */

public class Solution {

    public String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                sb.append((char) ('`' + ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0'))));
                i -= 2;
            } else {
                sb.append((char)('`' + (s.charAt(i) - '0')));
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().freqAlphabets("10#11#12").equals("jkab");
        assert new Solution().freqAlphabets("1326#").equals("acz");
        assert new Solution().freqAlphabets("25#").equals("y");
        assert new Solution().freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#")
            .equals("abcdefghijklmnopqrstuvwxyz");
    }

}
