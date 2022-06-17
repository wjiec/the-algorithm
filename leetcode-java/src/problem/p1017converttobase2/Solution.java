package problem.p1017converttobase2;

/**
 * 1017. Convert to Base -2
 *
 * https://leetcode.cn/problems/convert-to-base-2/
 *
 * Given an integer n, return a binary string representing its representation in base -2.
 *
 * Note that the returned string should not have leading zeros unless the string is "0".
 */

public class Solution {

    public String baseNeg2(int n) {
        int ans = 1;
        while (ans > 0 && ans < n) ans = (ans << 2) + 1;
        return Integer.toBinaryString(ans ^ (ans - n));
    }

    public static void main(String[] args) {
        assert new Solution().baseNeg2(1).equals("1");
        assert new Solution().baseNeg2(2).equals("110");
        assert new Solution().baseNeg2(3).equals("111");
        assert new Solution().baseNeg2(4).equals("100");
        assert new Solution().baseNeg2(5).equals("101");
    }

}
