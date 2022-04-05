package problem.p481magicalstring;

/**
 * 481. Magical String
 *
 * https://leetcode-cn.com/problems/magical-string/
 *
 * A magical string s consists of only '1' and '2' and obeys the following rules:
 *
 * The string s is magical because concatenating the number of contiguous
 * occurrences of characters '1' and '2' generates the string s itself.
 * The first few elements of s is s = "1221121221221121122……". If we group the consecutive 1's and 2's in s,
 * it will be "1 22 11 2 1 22 1 22 11 2 11 22 ......" and the occurrences of 1's or 2's in each group are
 * "1 2 2 1 1 2 1 2 2 1 2 2 ......". You can see that the occurrence sequence is s itself.
 *
 * Given an integer n, return the number of 1's in the first n number in the magical string s.
 */

public class Solution {

    public int magicalString(int n) {
        int ans = 1, l = 2, r = 3;
        StringBuilder sb = new StringBuilder("122");
        for (boolean isOne = true; r < n; isOne = !isOne, l++) {
            if (sb.charAt(l) == '1') sb.append(isOne ? 1 : 2);
            else sb.append(isOne ? 11 : 22);

            r += sb.charAt(l) - '0'; // sb的长度
            if (isOne) ans += sb.charAt(l) - '0'; // 增加1的长度
            if (r > n && isOne) ans--; // 修正超出的
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().magicalString(9) == 4;
        assert new Solution().magicalString(7) == 4;
        assert new Solution().magicalString(4) == 2;

        assert new Solution().magicalString(6) == 3;
        assert new Solution().magicalString(1) == 1;
    }

}
