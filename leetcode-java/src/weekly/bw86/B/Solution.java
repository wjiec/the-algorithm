package weekly.bw86.B;

import java.util.ArrayList;
import java.util.List;

/**
 * 6172. Strictly Palindromic Number
 *
 * https://leetcode.cn/contest/biweekly-contest-86/problems/strictly-palindromic-number/
 *
 * An integer n is strictly palindromic if, for every base b between 2 and n - 2 (inclusive), the
 * string representation of the integer n in base b is palindromic.
 *
 * Given an integer n, return true if n is strictly palindromic and false otherwise.
 *
 * A string is palindromic if it reads the same forward and backward.
 */

public class Solution {

    public boolean isStrictlyPalindromic(int n) {
        for (int i = 2; i <= n - 2; i++) {
            if (!check(n, i)) return false;
        }
        return true;
    }

    private boolean check(int n, int b) {
        List<Integer> bits = new ArrayList<>();
        while (n != 0) {
            bits.add(n % b);
            n /= b;
        }
        return check(bits);
    }

    private boolean check(List<Integer> bits) {
        int l = 0, r = bits.size() - 1;
        while (l <= r) {
            if (!bits.get(l).equals(bits.get(r))) return false;
            l++; r--;
        }
        return false;
    }

    public static void main(String[] args) {
    }

}
