package weekly.bw80.A;

/**
 * 6095. Strong Password Checker II
 *
 * https://leetcode.cn/contest/biweekly-contest-80/problems/strong-password-checker-ii/
 *
 * A password is said to be strong if it satisfies all the following criteria:
 *
 * It has at least 8 characters.
 * It contains at least one lowercase letter.
 * It contains at least one uppercase letter.
 * It contains at least one digit.
 * It contains at least one special character. The special characters are the characters
 * in the following string: "!@#$%^&*()-+".
 * It does not contain 2 of the same character in adjacent positions
 * (i.e., "aab" violates this condition, but "aba" does not).
 *
 * Given a string password, return true if it is a strong password. Otherwise, return false.
 */

public class Solution {

    public boolean strongPasswordCheckerII(String password) {
        if (password.length() < 8) return false;
        boolean digit = false, lower = false, upper = false, symbol = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isDigit(c)) digit = true;
            if (Character.isLowerCase(c)) lower = true;
            if (Character.isUpperCase(c)) upper = true;
            if ("!@#$%^&*()-+".indexOf(c) != -1) symbol = true;
            if (i != 0 && c == password.charAt(i - 1)) return false;
        }
        return digit && lower && upper && symbol;
    }

    public static void main(String[] args) {
    }

}
