package daily.d220108p89graycode;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. Gray Code
 *
 * https://leetcode-cn.com/problems/gray-code/
 *
 * An n-bit gray code sequence is a sequence of 2n integers where:
 *
 * Every integer is in the inclusive range [0, 2n - 1],
 * The first integer is 0,
 * An integer appears no more than once in the sequence,
 * The binary representation of every pair of adjacent integers differs by exactly one bit, and
 * The binary representation of the first and last integers differs by exactly one bit.
 *
 * Given an integer n, return any valid n-bit gray code sequence.
 */

public class Solution {

    public List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>(); ans.add(0);
        for (int i = 1; i <= n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(ans.get(j) | (1 << (i - 1)));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        assert new Solution().grayCode(1).equals(List.of(0, 1));
        assert new Solution().grayCode(2).equals(List.of(0, 1, 3, 2));
    }

}
