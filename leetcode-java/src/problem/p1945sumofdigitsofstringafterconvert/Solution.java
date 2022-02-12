package problem.p1945sumofdigitsofstringafterconvert;

/**
 * 1945. Sum of Digits of String After Convert
 *
 * https://leetcode-cn.com/problems/sum-of-digits-of-string-after-convert/
 *
 * You are given a string s consisting of lowercase English letters, and an integer k.
 *
 * First, convert s into an integer by replacing each letter with its position in the alphabet
 * (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26).
 *
 * Then, transform the integer by replacing it with the sum of its digits.
 *
 * Repeat the transform operation k times in total.
 *
 * For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
 *
 * Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 * Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
 * Transform #2: 17 ➝ 1 + 7 ➝ 8
 * Return the resulting integer after performing the operations described above.
 */

public class Solution {

    public int getLucky(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (var c : s.toCharArray()) sb.append(c - 'a' + 1);
        return lucky(sb.toString(), k);
    }

    private int lucky(String s, int k) {
        if (k == 0) return Integer.valueOf(s, 10);

        int sum = 0;
        for (var c : s.toCharArray()) sum += (c - '0');
        return lucky(String.valueOf(sum), k - 1);
    }

    public static void main(String[] args) {
        assert new Solution().getLucky("iiii", 1) == 36;
        assert new Solution().getLucky("leetcode", 2) == 6;
        assert new Solution().getLucky("zbax", 2) == 8;
    }

}
