package daily.d220110p306additivenumber;

/**
 * 306. Additive Number
 *
 * https://leetcode-cn.com/problems/additive-number/
 *
 * An additive number is a string whose digits can form an additive sequence.
 *
 * A valid additive sequence should contain at least three numbers. Except for the first two numbers,
 * each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits, return true if it is an additive number or false otherwise.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 */

public class Solution {

    public boolean isAdditiveNumber(String num) {
        for (int i = 0; i < num.length(); i++) {
            for (int j = i + 1; j < num.length(); j++) {
                if ((i > 0 && num.charAt(0) == '0') || j > i + 1 && num.charAt(i + 1) == '0') continue;
                if (j + 1 >= num.length()) break;

                long a = Long.parseLong(num, 0, i + 1, 10);
                long b = Long.parseLong(num, i + 1, j + 1, 10);
                if (check(a, b, num, 0, j + 1)) return true;
            }
        }
        return false;
    }

    private boolean check(long a, long b, String num, long curr, int index) {
        curr = curr * 10 + num.charAt(index) - '0';
        if (curr == a + b) {
            if (index == num.length() - 1) return true;
            else return check(b, curr, num, 0, index + 1);
        } else if (curr != 0 && curr < a + b && index < num.length() - 1) {
            return check(a, b, num, curr, index + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        assert !new Solution().isAdditiveNumber("10");

        assert new Solution().isAdditiveNumber("112358");
        assert new Solution().isAdditiveNumber("199100199");
    }

}
