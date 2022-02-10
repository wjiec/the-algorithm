package problem.p1009complementofbase10integer;

/**
 * 1009. Complement of Base 10 Integer
 *
 * https://leetcode-cn.com/problems/complement-of-base-10-integer/
 *
 * Every non-negative integer n has a binary representation.  For example,
 * 5 can be represented as "101" in binary, 11 as "1011" in binary, and so on.
 *
 * Note that except for n = 0, there are no leading zeroes in any binary representation.
 *
 * The complement of a binary representation is the number in binary you
 * get when changing every 1 to a 0 and 0 to a 1.
 *
 * For example, the complement of "101" in binary is "010" in binary.
 *
 * For a given number n in base-10, return the complement of it's binary representation as a base-10 integer.
 */

public class Solution {

    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        int rs = 0, i = 0;
        while (n != 0) {
            rs |= (((n & 0x1) + 1) & 0x1) << i++;
            n >>= 1;
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().bitwiseComplement(5) == 2;
        assert new Solution().bitwiseComplement(7) == 0;
        assert new Solution().bitwiseComplement(10) == 5;
    }

}
