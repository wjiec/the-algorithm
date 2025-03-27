package weekly.bw152.A;

import java.util.HashSet;
import java.util.Set;

/**
 * 3483. Unique 3-Digit Even Numbers
 *
 * https://leetcode.cn/contest/biweekly-contest-152/problems/unique-3-digit-even-numbers/
 *
 * You are given an array of digits called digits. Your task is to determine
 * the number of distinct three-digit even numbers that can be formed using these digits.
 *
 * Note: Each copy of a digit can only be used once per number, and there may not be leading zeros.
 */

public class Solution {

    public int totalNumbers(int[] digits) {
        Set<Integer> ans = new HashSet<>();
        for (int i = 0; i < digits.length; i++) {
            int v1 = digits[i];
            if (v1 == 0) continue;

            for (int j = 0; j < digits.length; j++) {
                if (j == i) continue;
                int v2 = v1 * 10 + digits[j];

                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j || digits[k] % 2 != 0) continue;
                    ans.add(v2 * 10 + digits[k]);
                }
            }
        }
        return ans.size();
    }

    public static void main(String[] args) {
    }

}
