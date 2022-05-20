package problem.p842splitarrayintofibonaccisequence;

import common.TODO;

import java.util.ArrayList;
import java.util.List;

/**
 * 842. Split Array into Fibonacci Sequence
 *
 * https://leetcode.cn/problems/split-array-into-fibonacci-sequence/
 *
 * You are given a string of digits num, such as "123456579".
 * We can split it into a Fibonacci-like sequence [123, 456, 579].
 *
 * Formally, a Fibonacci-like sequence is a list f of non-negative integers such that:
 *
 * 0 <= f[i] < 231, (that is, each integer fits in a 32-bit signed integer type),
 * f.length >= 3, and f[i] + f[i + 1] == f[i + 2] for all 0 <= i < f.length - 2.
 * Note that when splitting the string into pieces, each piece must not have extra leading zeroes,
 * except if the piece is the number 0 itself.
 *
 * Return any Fibonacci-like sequence split from num, or return [] if it cannot be done.
 */

public class Solution {

    @TODO
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        backtrack(num.toCharArray(), 0, 0, 0, ans);
        return ans;
    }

    private boolean backtrack(char[] chars, int i, long sum, long prev, List<Integer> ans) {
        if (i == chars.length) return ans.size() >= 3;

        long curr = 0;
        for (int j = i; j < chars.length; j++) {
            if (j > i && chars[i] == '0') break;

            curr = curr * 10 + chars[j] - '0';
            if (curr > Integer.MAX_VALUE) break;

            if (ans.size() >= 2) {
                if (curr < sum) continue;
                else if (curr > sum) break;
            }

            ans.add((int) curr);
            if (backtrack(chars, j + 1, prev + curr, curr, ans)) return true;
            else ans.remove(ans.size() - 1);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().splitIntoFibonacci("1101111"));
        System.out.println(new Solution().splitIntoFibonacci("112358130"));
        System.out.println(new Solution().splitIntoFibonacci("0123"));
    }

}
