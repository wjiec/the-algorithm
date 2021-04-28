package daily.p210428.sumofsquarenumbers;

import java.util.regex.Matcher;

/**
 * 633. Sum of Square Numbers
 *
 * https://leetcode-cn.com/problems/sum-of-square-numbers/
 *
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 */

public class Solution {

    public boolean judgeSquareSum(int c) {
        int max = (int)Math.sqrt(c);
        for (int i = 0; i <= max; i++) {
            if (Math.sqrt(c - i * i) % 1 == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum1(int c) {
        for (int l = 0, r = (int) Math.sqrt(c); l <= r; ) {
            int v = l * l + r * r;
            if (v == c) {
                return true;
            } else if (v <= c) {
                l++;
            } else {
                r--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        assert new Solution().judgeSquareSum(0);
        assert new Solution().judgeSquareSum(1);
        assert new Solution().judgeSquareSum(2);
        assert !new Solution().judgeSquareSum(3);
        assert new Solution().judgeSquareSum(4);
        assert new Solution().judgeSquareSum(5);

        assert new Solution().judgeSquareSum1(0);
        assert new Solution().judgeSquareSum1(1);
        assert new Solution().judgeSquareSum1(2);
        assert !new Solution().judgeSquareSum1(3);
        assert new Solution().judgeSquareSum1(4);
        assert new Solution().judgeSquareSum1(5);
    }

}
