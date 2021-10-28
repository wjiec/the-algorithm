package daily.d211028preorderedpowerof2;

import java.util.HashSet;
import java.util.Set;

/**
 * 869. Reordered Power of 2
 *
 * https://leetcode-cn.com/problems/reordered-power-of-2/
 *
 * You are given an integer n. We reorder the digits in any order (including the original order)
 * such that the leading digit is not zero.
 *
 * Return true if and only if we can do this so that the resulting number is a power of two.
 */

public class Solution {

    public boolean reorderedPowerOf2(int n) {
        Set<String> set = new HashSet<>();
        for (int i = 1; i < 1e9; i <<= 1) {
            set.add(n2s(i));
            set.add(String.valueOf(i));
        }

        if (set.contains(String.valueOf(n))) return true;
        return set.contains(n2s(n));
    }

    private String n2s(int n) {
        int[] ns = new int[10];
        for (; n != 0; n /= 10) {
            ns[n % 10]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(i).append('.').append(ns[i]).append(';');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        assert new Solution().reorderedPowerOf2(1);
        assert !new Solution().reorderedPowerOf2(10);
        assert new Solution().reorderedPowerOf2(16);
        assert !new Solution().reorderedPowerOf2(24);
        assert new Solution().reorderedPowerOf2(46);
    }

}
