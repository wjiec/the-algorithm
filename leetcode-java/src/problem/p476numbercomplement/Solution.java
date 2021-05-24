package problem.p476numbercomplement;

/**
 * 476. Number Complement
 *
 * https://leetcode-cn.com/problems/number-complement/
 *
 * Given a positive integer num, output its complement number.
 * The complement strategy is to flip the bits of its binary representation.
 */

public class Solution {

    public int findComplement(int num) {
        if (num == 0) return 1;
        int rs = 0, i = 0;
        while (num != 0) {
            rs |= (((num & 0x1) + 1) & 0x1) << i++;
            num >>= 1;
        }
        return rs;
    }

    public static void main(String[] args) {
        assert new Solution().findComplement(5) == 2;
        assert new Solution().findComplement(2) == 1;
        assert new Solution().findComplement(1) == 0;
    }

}
