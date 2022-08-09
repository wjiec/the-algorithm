package problem.p1432maxdifferenceyoucangetfromchanginganinteger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 1432. Max Difference You Can Get From Changing an Integer
 *
 * https://leetcode.cn/problems/max-difference-you-can-get-from-changing-an-integer/
 *
 * You are given an integer num. You will apply the following steps exactly two times:
 *
 * Pick a digit x (0 <= x <= 9).
 * Pick another digit y (0 <= y <= 9). The digit y can be equal to x.
 * Replace all the occurrences of x in the decimal representation of num by y.
 * The new integer cannot have any leading zeros, also the new integer cannot be 0.
 * Let a and b be the results of applying the operations to num the first and second times, respectively.
 *
 * Return the max difference between a and b.
 */

public class Solution {

    public int maxDiff(int num) {
        List<Integer> bits = new ArrayList<>();
        for (; num != 0; num /= 10) bits.add(num % 10);
        Collections.reverse(bits);

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (var bit : bits) max = Math.max(max, replace(bits, bit, 9));
        min = Math.min(min, replace(bits, bits.get(0), 1));
        for (int i = 1; i < bits.size(); i++) {
            min = Math.min(min, replace(bits, bits.get(i), Objects.equals(bits.get(i), bits.get(0)) ? 1 : 0));
        }
        return max - min;
    }

    private int replace(List<Integer> bits, int a, int b) {
        int val = 0;
        for (var bit : bits) val = val * 10 + (bit == a ? b : bit);
        return val;
    }

    public static void main(String[] args) {
        // 8808000
        assert new Solution().maxDiff(1101057) == 8808050;

        assert new Solution().maxDiff(555) == 888;
        assert new Solution().maxDiff(9) == 8;
    }

}
