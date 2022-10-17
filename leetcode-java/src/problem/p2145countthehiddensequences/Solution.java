package problem.p2145countthehiddensequences;

/**
 * 2145. Count the Hidden Sequences
 *
 * https://leetcode.cn/problems/count-the-hidden-sequences/
 *
 * You are given a 0-indexed array of n integers differences, which describes the differences between
 * each pair of consecutive integers of a hidden sequence of length (n + 1). More formally, call the
 * hidden sequence hidden, then we have that differences[i] = hidden[i + 1] - hidden[i].
 *
 * You are further given two integers lower and upper that describe the inclusive range of
 * values [lower, upper] that the hidden sequence can contain.
 *
 * For example, given differences = [1, -3, 4], lower = 1, upper = 6, the hidden sequence is a
 * sequence of length 4 whose elements are in between 1 and 6 (inclusive).
 * [3, 4, 1, 5] and [4, 5, 2, 6] are possible hidden sequences.
 * [5, 6, 3, 7] is not possible since it contains an element greater than 6.
 * [1, 2, 3, 4] is not possible since the differences are not correct.
 *
 * Return the number of possible hidden sequences there are. If there are no possible sequences, return 0.
 */

public class Solution {

    public int numberOfArrays(int[] differences, int lower, int upper) {
        long min = 0, max = 0, v = 0;
        for (var n : differences) {
            v += n;
            min = Math.min(min, v);
            max = Math.max(max, v);
        }

        long diff1 = max - min, diff2 = upper - lower;
        if (diff1 > diff2) return 0;
        return (int) (diff2 - diff1 + 1);
    }

    public static void main(String[] args) {
        assert new Solution().numberOfArrays(new int[]{83702,-5216}, -82788, 14602) == 13689;
        assert new Solution().numberOfArrays(new int[]{-40}, -46, 53) == 60;

        assert new Solution().numberOfArrays(new int[]{1,-3,4}, 1, 6) == 2;
        assert new Solution().numberOfArrays(new int[]{3,-4,5,1,-2}, -4, 5) == 4;
        assert new Solution().numberOfArrays(new int[]{4,-7,2}, 3, 6) == 0;
    }

}
