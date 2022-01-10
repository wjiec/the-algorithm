package problem.p2119anumberafteradoublereversal;

/**
 * 2119. A Number After a Double Reversal
 *
 * https://leetcode-cn.com/problems/a-number-after-a-double-reversal/
 *
 * Reversing an integer means to reverse all its digits.
 *
 * For example, reversing 2021 gives 1202. Reversing 12300 gives 321 as the leading zeros are not retained.
 *
 * Given an integer num, reverse num to get reversed1, then reverse reversed1 to get reversed2.
 *
 * Return true if reversed2 equals num. Otherwise return false.
 */

public class Solution {

    public boolean isSameAfterReversals(int num) {
        return num % 10 != 0 || num == 0;
    }

    public static void main(String[] args) {
        assert new Solution().isSameAfterReversals(526);
        assert !new Solution().isSameAfterReversals(1800);
        assert new Solution().isSameAfterReversals(0);
    }

}
