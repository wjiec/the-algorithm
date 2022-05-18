package problem.p831maskingpersonalinformation;

/**
 * 831. Masking Personal Information
 *
 * https://leetcode.cn/problems/masking-personal-information/
 *
 * You are given a personal information string s, representing either an email address or a phone number.
 * Return the masked personal information using the below rules.
 *
 * Email address:
 *
 * An email address is:
 *
 * A name consisting of uppercase and lowercase English letters, followed by
 * The '@' symbol, followed by
 *
 * The domain consisting of uppercase and lowercase English letters with a dot '.'
 * somewhere in the middle (not the first or last character).
 *
 * To mask an email:
 *
 * The uppercase letters in the name and domain must be converted to lowercase letters.
 * The middle letters of the name (i.e., all but the first and last letters)
 * must be replaced by 5 asterisks "*****".
 *
 * Phone number:
 *
 * A phone number is formatted as follows:
 *
 * The phone number contains 10-13 digits.
 * The last 10 digits make up the local number.
 * The remaining 0-3 digits, in the beginning, make up the country code.
 * Separation characters from the set {'+', '-', '(', ')', ' '} separate the above digits in some way.
 * To mask a phone number:
 *
 * Remove all separation characters.
 * The masked phone number should have the form:
 * "***-***-XXXX" if the country code has 0 digits.
 * "+*-***-***-XXXX" if the country code has 1 digit.
 * "+**-***-***-XXXX" if the country code has 2 digits.
 * "+***-***-***-XXXX" if the country code has 3 digits.
 * "XXXX" is the last 4 digits of the local number.
 */

public class Solution {

    public String maskPII(String s) {
        if (Character.isLetter(s.charAt(0))) {
            int idx = 0;
            while (s.charAt(idx) != '@') idx++;
            return String.format("%c*****%c%s",
                Character.toLowerCase(s.charAt(0)),
                Character.toLowerCase(s.charAt(idx - 1)),
                s.substring(idx).toLowerCase()
            );
        }

        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1, n = 0; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                if (n++ < 4) sb.append(c); else sb.append('*');
                if ((n - 4) >= 0 && (n - 4) % 3 == 0) sb.append('-');
            }
        }

        if (sb.charAt(sb.length() - 1) == '-')
            sb.deleteCharAt(sb.length() - 1);
        if (sb.length() > 12) sb.append('+');

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        assert new Solution().maskPII("+(501321)-50-23431").equals("+***-***-***-3431");

        assert new Solution().maskPII("LeetCode@LeetCode.com").equals("l*****e@leetcode.com");
        assert new Solution().maskPII("AB@qq.com").equals("a*****b@qq.com");
        assert new Solution().maskPII("1(234)567-890").equals("***-***-7890");
        assert new Solution().maskPII("86-(10)12345678").equals("+**-***-***-5678");
    }

}
