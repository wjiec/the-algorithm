package problem.p1291sequentialdigits;

import common.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * 1291. Sequential Digits
 *
 * https://leetcode.cn/problems/sequential-digits/
 *
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 */

public class Solution {

    public List<Integer> sequentialDigits(int low, int high) {
        int ll = String.valueOf(low).length(), hl = String.valueOf(high).length();

        List<Integer> ans = new ArrayList<>();
        for (int i = ll; i <= hl; i++) {
            for (int j = 1; j < 10; j++) {
                int seq = seqNext(j, i);
                if (low <= seq && seq <= high && String.valueOf(seq).length() == i) {
                    ans.add(seq);
                }
            }
        }
        return ans;
    }

    private int seqNext(int b, int n) {
        for (int i = 1, x = b + 1; i < n && x < 10; i++, x++) {
            b = b * 10 + x;
        }
        return b;
    }

    public static void main(String[] args) {
        assert Checker.check(new Solution().sequentialDigits(100, 300), List.of(123,234));
        assert Checker.check(new Solution().sequentialDigits(1000, 13000),
            List.of(1234,2345,3456,4567,5678,6789,12345));
    }

}
