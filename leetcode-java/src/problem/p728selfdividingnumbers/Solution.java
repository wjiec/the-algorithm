package problem.p728selfdividingnumbers;

import java.util.ArrayList;
import java.util.List;

/**
 * 728. Self Dividing Numbers
 *
 * https://leetcode-cn.com/problems/self-dividing-numbers/
 *
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * A self-dividing number is not allowed to contain the digit zero.
 *
 * Given two integers left and right, return a list of all the self-dividing numbers in the range [left, right].
 */

public class Solution {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (; left <= right; left++) {
            if (isSelfDividingNumber(left)) {
                ans.add(left);
            }
        }
        return ans;
    }

    private boolean isSelfDividingNumber(int v) {
        for (int n = v; n != 0; n /= 10) {
            if (n % 10 == 0 || v % (n % 10) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().selfDividingNumbers(66, 708));
        assert new Solution().selfDividingNumbers(1, 22).equals(List.of(1,2,3,4,5,6,7,8,9,11,12,15,22));
        assert new Solution().selfDividingNumbers(47, 85).equals(List.of(48,55,66,77));
    }

}
