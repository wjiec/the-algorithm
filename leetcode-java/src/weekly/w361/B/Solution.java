package weekly.w361.B;

/**
 * 2844. Minimum Operations to Make a Special Number
 *
 * https://leetcode.cn/contest/weekly-contest-361/problems/minimum-operations-to-make-a-special-number/
 *
 * You are given a 0-indexed string num representing a non-negative integer.
 *
 * In one operation, you can pick any digit of num and delete it. Note that
 * if you delete all the digits of num, num becomes 0.
 *
 * Return the minimum number of operations required to make num special.
 *
 * An integer x is considered special if it is divisible by 25.
 */

public class Solution {

    public int minimumOperations(String num) {
        char[] chars = num.toCharArray();

        return Math.min(
            build(chars, '2', '5'),
            Math.min(
                build(chars, '5', '0'),
                Math.min(
                    build(chars, '7', '5'),
                    Math.min(
                        build(chars, '0', '0'),
                        all0(chars)
                    )
                )
            )
        );
    }

    private int build(char[] chars, char bef, char last) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == last) {
                for (int j = i - 1; j >= 0; j--) {
                    if (chars[j] == bef) {
                        return chars.length - j - 2;
                    }
                }
            }
        }
        return chars.length;
    }

    private int all0(char[] chars) {
        int ans = 0;
        for (var c : chars) {
            if (c != '0') ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }

}
